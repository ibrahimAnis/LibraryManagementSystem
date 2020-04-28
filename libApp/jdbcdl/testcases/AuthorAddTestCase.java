import com.thinking.machines.library.dl.*;
import java.io.*;
class AuthorAddTestCase 
{
public static void main(String gg[])
{
if(gg.length!=1)
{
System.out.println("Usage: Name");
return;
}
try
{
AuthorInterface author;
author=new Author();
author.setName(gg[0]);
AuthorDAOInterface authorDAO=new AuthorDAO();
authorDAO.add(author);
System.out.println("Author added with code as "+author.getCode());
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}
