package com.thinking.machines.library.dl;
import java.sql.*;
import java.util.*;
public class AuthorDAO implements AuthorDAOInterface
{
public void add(AuthorInterface authorInterface) throws DAOException
{
String name=authorInterface.getName();
Connection connection=DAOConnection.getConnection();
try
{
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select 1 as xyz from author where name=?");
preparedStatement.setString(1,name);
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Author : "+name+" exists.");
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("insert into author (name) values(?)",Statement.RETURN_GENERATED_KEYS);
preparedStatement.setString(1,name);
preparedStatement.executeUpdate();
resultSet=preparedStatement.getGeneratedKeys();
resultSet.next();
authorInterface.setCode(resultSet.getInt(1));
resultSet.close();
preparedStatement.close();
connection.close();
}catch(Exception exception)
{
System.out.println("jdbcdl AuthorDAO");
throw new DAOException(exception.getMessage()); // remove after testing
}
finally
{
try
{
if(connection!=null) connection.close();
}catch(SQLException sql)
{
//sql.printStackTrace();
System.out.println(sql);
}
}
}
public void update(AuthorInterface authorInterface) throws DAOException
{
String name=authorInterface.getName();
int code=authorInterface.getCode();
Connection connection=DAOConnection.getConnection();
try
{
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select 1 as xyz from author where code=?");
preparedStatement.setInt(1,code);
ResultSet resultSet;
resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid author code : "+code);
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("select 1 as xyz from author where name=? and code<>?");
preparedStatement.setString(1,name);
preparedStatement.setInt(2,code);
resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Author : "+name+" exists.");
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("update author set name=? where code=?");
preparedStatement.setString(1,name);
preparedStatement.setInt(2,code);
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();
}catch(Exception exception)
{
System.out.println(exception); // remove after testing
throw new DAOException(exception.getMessage()); // remove after testing
}
}
public void remove(int code) throws DAOException
{
Connection connection=DAOConnection.getConnection();
try
{
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select 1 as xyz from author where code=?");
preparedStatement.setInt(1,code);
ResultSet resultSet;
resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid author code : "+code);
}
resultSet.close();
preparedStatement.close();
// later on, you need to check if any book exists against this author
// if yes, throw exception
preparedStatement=connection.prepareStatement("delete from author where code=?");
preparedStatement.setInt(1,code);
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();
}catch(Exception exception)
{
System.out.println(exception); // remove after testing
throw new DAOException(exception.getMessage()); // remove after testing
}
}
public LinkedList<AuthorInterface> getAll(AuthorInterface.OrderBy sortOn) throws DAOException
{
Connection connection=DAOConnection.getConnection();
System.out.println("Connection Established:");
try
{
Statement statement=connection.createStatement();
ResultSet resultSet;
if(sortOn==AuthorInterface.NAME)
{
resultSet=statement.executeQuery("select * from author order by name");
}
else
{
resultSet=statement.executeQuery("select * from author order by code");
}
AuthorInterface authorInterface;
LinkedList<AuthorInterface> authors;
authors=new LinkedList<AuthorInterface>();
while(resultSet.next())
{
authorInterface=new Author();
authorInterface.setCode(resultSet.getInt("code"));
authorInterface.setName(resultSet.getString("name").trim());
authors.add(authorInterface);
}
resultSet.close();
statement.close();
connection.close();
if(authors.size()==0)
{
throw new DAOException("No authors");
}
return authors;
}catch(SQLException sqlException)
{
System.out.println(sqlException);
throw new DAOException(sqlException.getMessage());
}
}
public LinkedList<AuthorInterface> getAll() throws DAOException
{
Connection connection=DAOConnection.getConnection();
System.out.println("Connection has been established");
try
{
Statement statement=connection.createStatement();
ResultSet resultSet=statement.executeQuery("select * from author order by name");
AuthorInterface authorInterface;
LinkedList<AuthorInterface> authors;
authors=new LinkedList<AuthorInterface>();
while(resultSet.next())
{
authorInterface=new Author();
authorInterface.setCode(resultSet.getInt("code"));
authorInterface.setName(resultSet.getString("name").trim());
authors.add(authorInterface);
}
for(AuthorInterface author:authors)
{
System.out.println(author.getName());
}
resultSet.close();
statement.close();
connection.close();
if(authors.size()==0)
{
throw new DAOException("No authors");
}
return authors;
}catch(SQLException sqlException)
{
System.out.println(sqlException);
throw new DAOException(sqlException.getMessage());
}
}
public AuthorInterface getByCode(int code) throws DAOException
{
Connection connection=DAOConnection.getConnection();
try
{
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select name from author where code=?");
preparedStatement.setInt(1,code);
ResultSet resultSet;
resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid author code : "+code);
}
AuthorInterface authorInterface=new Author();
authorInterface.setCode(code);
authorInterface.setName(resultSet.getString("name").trim());
resultSet.close();
preparedStatement.close();
connection.close();
return authorInterface;
}catch(SQLException sqlException)
{
System.out.println(sqlException);
throw new DAOException(sqlException.getMessage());
}
}
public AuthorInterface getByName(String name) throws DAOException
{
Connection connection=DAOConnection.getConnection();
try
{
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select code from author where name=?");
preparedStatement.setString(1,name);
ResultSet resultSet;
resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid author name : "+name);
}
AuthorInterface authorInterface=new Author();
authorInterface.setCode(resultSet.getInt("code"));
authorInterface.setName(name);
resultSet.close();
preparedStatement.close();
connection.close();
return authorInterface;
}catch(SQLException sqlException)
{
System.out.println(sqlException);
throw new DAOException(sqlException.getMessage());
}
}
public long getCount() throws DAOException
{
Connection connection=DAOConnection.getConnection();
try
{
Statement statement=connection.createStatement();
ResultSet resultSet=statement.executeQuery("select count(*) as cnt from author");
resultSet.next();
long count=resultSet.getLong("cnt");
resultSet.close();
statement.close();
connection.close();
return count;
}catch(SQLException sqlException)
{
System.out.println(sqlException);
throw new DAOException(sqlException.getMessage());
}
}
}
