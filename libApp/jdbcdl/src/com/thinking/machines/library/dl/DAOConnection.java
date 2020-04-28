package com.thinking.machines.library.dl;
import java.io.*;
import java.sql.*;
public class DAOConnection
{
public final static String connectionString;
static 
{
String s="";
try
{
File file=new File("db.confg"); // db.confg  file will be searched in working folder i.e pl testcase folder right now.
if(file.exists()==false)
{
System.out.println("Chali");
throw new DAOException("file db.confg missing");
}
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("connection string missing");
}
s=randomAccessFile.readLine();
randomAccessFile.close();
}catch(DAOException dao)
{
System.out.println(dao.getMessage());
}
catch(IOException io)
{
System.out.println(io.getMessage());
}
connectionString=s;
}
public static Connection getConnection() throws DAOException
{
try
{
Connection connection=DriverManager.getConnection(connectionString);
return connection;
}catch(SQLException sql)
{
throw new DAOException(sql.getMessage());
}
}
}


