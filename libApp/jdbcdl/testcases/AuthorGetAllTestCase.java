import com.thinking.machines.library.dl.*;
import java.util.*;
class AuthorGetAllTestCase
{
public static void main(String gg[])
{
try
{
LinkedList<AuthorInterface> authors;
AuthorDAOInterface authorDAO=new AuthorDAO();
authors=authorDAO.getAll(AuthorInterface.NAME);
for(AuthorInterface author:authors)
{
System.out.println(author.getCode()+":"+author.getName());
}
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}
