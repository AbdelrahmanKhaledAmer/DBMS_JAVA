package DBApp;

import java.io.File;
import java.io.FileWriter;
//import java.io.File;
//import java.io.FileOutputStream;
import java.io.IOException;
//import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class Table
{
	int numPages;
	String name;
	String[] colNames;
	String[] colTypes;
	Page last;
	boolean open;

	// First Step: Constructing a table - You should initialize the variables
	// given above -
	public Table(String name, String[] colNames, String[] colTypes, String key) throws IOException
	{
		this.numPages = 0;
		this.name = name;
		this.colNames = colNames;
		this.colTypes = colTypes;
		addPage(last);

		File dir = new File(name);
		dir.mkdir();
		File metadata = new File ("./" +name + "/"+name+".csv");

		PrintWriter out=new PrintWriter(metadata);
		for (int i = 0; i < colTypes.length; i++)
		{
			out.printf("%s,%s,%s,%s,%s\n",this.name,this.colNames[i],this.colTypes[i],(this.colNames[i].equals(key)?"True":"False"),"false");
		}
		out.flush();
		out.close();
	}

	// Function1: A function that creates a "Tables" folder in which the pages
	// of a table will be created
	// and adds a page into that folder
	public void addPage(Page p) throws IOException
	{
		last = new Page(colNames.length,this.name,this.numPages);
		numPages++;
	}

	// Function2: A function that inserts a record of strings into the last page
	// of the table if it is not full,
	// otherwise, it should add a new page into the folder and insert the record
	// into it
	public int insert(String[] record) throws ClassNotFoundException, IOException
	{
		if (last.isFull())
		{
			addPage(last);
			last.insert(record);
		}else{
			last.insert(record);
		}

		return last.current;
	}

	public boolean delete(String[] col, String[] val,String op)
	{
		int colNums [] = new int [col.length];
		for(int j=0 ; j < col.length ;j++)
		{
			for (int k = 0; k < colNames.length; k++)
			{
				if(col[j].equals(colNames[k]))
				{
					colNums[j]=k ;
				}
			}
		}
		
		for (int i = 0; i < numPages; i++)
		{
			Page page = new Page(colNames.length,this.name,i,"./" + name + "/" +name+ "_" + i+".csv");
			page.delete(colNums, val,op);
			page.getData();	
		}
		return true ;
	}

	// Testing Your Code
	public static void main(String[] args) throws IOException, ClassNotFoundException
	{
		String tName = "Student";
		String[] tColNames = { "ID", "Name", "GPA", "Age", "Year" };
		String[] tColTypes = { "int", "String", "double", "int", "int" };
		Table t = new Table(tName, tColNames, tColTypes, "ID");

		for (int i = 0; i < 300; i++)
		{
			String[] st = { "" + i, "Name" + i, "0." + i, "20", "3" };
			t.insert(st);
		}

		for (int i = 0; i < 300; i++)
		{
			String[] st = { "" + (i + 300), "Name" + (i + 300), "0." + (i + 300), "21", "4" };
			t.insert(st);
		}

		for (int i = 0; i < 300; i++)
		{
			String[] st = { "" + (i + 600), "Name" + (i + 600), "0." + (i + 600), "21", "3" };
			t.insert(st);
		}
		String col[] = {"ID","Name"};
		String val[] = {"1","Name1"};
		String col2[] = {"ID","Name"};
		String val2 []= {"5","Name6"};
		
		t.delete(col,val,"AND");
		t.delete(col2,val2,"OR");
		
		System.out.println(t);
	}
}