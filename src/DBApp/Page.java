package DBApp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import DBApp.Page;
import pagingConfiguration.configuration;

public class Page
{
	
	String [][] data;
	boolean [] deleted;
	int current;
	File page;
	
	//First Step: Constructing a page - You should initialize the variables given above -
	public Page(int noCol,String name,int num)
	{
		File page = new File ("./" + name + "/" +name+ "_" + num+".csv");
		this.data = new String[configuration.pageSize][noCol];
		this.deleted = new boolean[configuration.pageSize];
		for(int i = 0 ; i < deleted.length ; i++)
		{
			deleted[i] = false;
		}
		this.current = 0;
		this.page = page;
	}
	
	public Page(int noCol, String name, int num, String path)
	{
		try
		{
			FileInputStream page = new FileInputStream (path);
			this.data = new String[configuration.pageSize][noCol];
			this.current = 0;
			BufferedReader rdr = new BufferedReader(new InputStreamReader(page));
			String in;
			this.deleted = new boolean[configuration.pageSize];
			int i = 0;
			while((in = rdr.readLine())!=null)
			{
				
				String a[] = in.split(",");
				
				for(int j=0 ; j<a.length;j++)
				{
					if(j != a.length-1)
					{
						
						data[i][j]=a[j];
					}
				}
				if(a[a.length-1].equals("True"))
				{
					deleted[i]=true ;
				}else{
					deleted[i]=false ;
				}
				
				i++;
				current++;
				
			}
			this.page = new File (path);
			rdr.close();
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}
	}

	//Function1: A function that checks if the page is full
	public boolean isFull()
	{
		return current==configuration.pageSize;
	}

	//Function2: Inserting a record into the page
	public boolean insert(String [] val) throws IOException
	{
		FileWriter writer = new FileWriter(page.getPath(),true);
		for (int i = 0; i < val.length; i++)
		{
			writer.write(val[i]);
			if(i != val.length-1)
			{
				writer.write(",");
			}
		}
		writer.write(", False");
		writer.write("\n");
		writer.flush();
		writer.close();
		data[current] = val;
		deleted[current]= true ;
		current++;
		
		return true;
	}

	//Function3: Inserting a set of records into a page - It will use Function2-
	public void  getData()
	{
		PrintWriter out;
		try {
			out = new PrintWriter(page);
			String salem = "";
			for (int i = 0; i < current; i++)
			{
				for(int j=0 ; j< data[i].length;j++)
				{
					salem+=data[i][j]+",";
				}
				String s = (deleted[i])?"True":"False";
				salem +=s+"\n";
			}
			out.print(salem);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	public void delete(int []col,String []val ,String op)
	{
		for (int i = 0; i < current; i++)
		{	
			if(op.equals("AND"))
			{
				boolean flag = true ;
				for(int j=0 ; j < val.length;j++)
				{
					if(!(data[i][col[j]].equals(val[j])))
						flag = false ;
				}
				if(flag)
				{
					deleted[i]=true ;
				}
			}
			else if(op.equals("OR"))
			{
				boolean flag = false ;
				for(int j=0 ; j < val.length;j++)
				{
					if(data[i][col[j]].equals(val[j]))
						flag = true ;
				}
				if(flag)
				{
					deleted[i]=true ;
				}
			}	
		}
	}
}