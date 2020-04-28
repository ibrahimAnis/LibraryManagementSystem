import com.thinking.machines.library.dl.*;
import java.io.*;
class AuthorGetByName 
{
public static void main(String gg[])
{
if(gg.length!=1)
{
System.out.println("Usage: name");
return;
}
try
{
AuthorDAOInterface authorDAO=new AuthorDAO();
AuthorInterface author=new Author();
author=authorDAO.getByName(gg[0]);
System.out.println(author.getCode()+": "+author.getName());
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}
