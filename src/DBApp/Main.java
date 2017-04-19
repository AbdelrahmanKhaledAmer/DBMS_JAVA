package DBApp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.KeyStore.Entry;
import java.util.Hashtable;
import java.util.Set;

public class Main
{
	//TODO No indices?
	public void init( )
	{
		
	}

	/**
	* 	A method to create a table/relation.
	*	@param	strTableName: name of table to be created
	*	@param	htblColNameType: a hashtable of column names and column types , for example ("ID", "java.lang.Integer") where ID is key and the value
	*							 is java.lang.Integer
	*	@param	strKeyColName: the name of the key column, for example "ID"
	*/
	public static void createTable(String strTableName, Hashtable<String,String> htblColNameType, String strKeyColName)  throws DBException
	{
//		Set<String>keys=htblColNameType.keySet();
//		String colNames[]=new String[keys.size()];
//		String colTypes[]=new String[keys.size()];
//		int i=0;
//		for(String key:keys)
//		{
//			String val=htblColNameType.get(key);
//			colNames[i]=key;
//			colTypes[i++]=val;
//		}
//		
//		try
//		{
//			Table t=new Table(strTableName, colNames, colTypes, strKeyColName);
//		}catch (IOException e){
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	/** 
	*	A method to insert a tuple into an existing table
	*	@param	strTableName: name of table to insert into
	*	@param	htblColNameValue: a hashtable of column names and values for each column, for example, ("ID", "50011") where ID is 
	*								the name of the column and 50011 is the value to be inserted.
	*/
	public void insertIntoTable(String strTableName, Hashtable<String,String> htblColNameValue)  throws DBException
	{
		//TODO always insert in last page?
	}

	/** 
	*	A method to delete one or more tuple(s) from an existing table
	*	@param	strTableName: name of table to delete from
	*	@param	htblColNameValue: a hashtable of column names and values for each column, for example, ("ID", "50011") where ID is 
	*								the name of the column and 50011 is the value to be used for identifying row to be deleted.
	*	@param	strOperator: possible values are AND or OR to combine the keys in htblColNameValue
	*/
	public void deleteFromTable(String strTableName, Hashtable<String,String> htblColNameValue, String strOperator) throws DBException
	{
		//TODO Check all pages?
	}

	public static void main( String[] strArgs ) throws DBException, ClassNotFoundException, IOException
	{
//		Hashtable h = new Hashtable();
//		h.put("Key", "java.lang.String");
//		h.put("Num", "java.lang.Integer");
//		createTable("Test",h,"key");
//		String[] s1 = {"1","100"};
//		String[] s2 = {"2","101"};
//		t.insert(s1);
//		t.insert(s2);
	}
}