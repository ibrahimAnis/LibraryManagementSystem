import com.thinking.machines.library.dl.*;
import java.io.*;
class AuthorUpdateTestCase 
{
public static void main(String gg[])
{
if(gg.length!=2)
{
System.out.println("Usage: Code Name");
return;
}
try
{
AuthorInterface author;
author=new Author();
author.setCode(Integer.parseInt(gg[0]));
author.setName(gg[1]);
AuthorDAOInterface authorDAO=new AuthorDAO();
authorDAO.update(author);
System.out.println("Author updated with name as "+author.getName());
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}
