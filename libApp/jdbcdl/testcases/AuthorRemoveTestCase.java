import com.thinking.machines.library.dl.*;
import java.io.*;
class AuthorRemoveTestCase 
{
public static void main(String gg[])
{
if(gg.length!=1)
{
System.out.println("Usage: code");
return;
}
try
{
AuthorDAOInterface authorDAO=new AuthorDAO();
authorDAO.remove(Integer.parseInt(gg[0]));
System.out.println("Author deleted with code as "+gg[0]);
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}
