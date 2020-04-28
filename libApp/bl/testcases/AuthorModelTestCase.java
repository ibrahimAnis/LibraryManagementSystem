import com.thinking.machines.library.bl.*;	
import javax.swing.*;
import java.awt.*;
import java.util.*;
import javax.swing.table.*;
class AuthorModelTestCase extends JFrame
{
private Container c;
private JTable table;
private JScrollPane jsp;
private AuthorModel m;
AuthorModelTestCase()
{
m=new AuthorModel();
c=getContentPane();
table=new JTable(m);
Font f=new Font("Verdana",Font.PLAIN,16);
table.setFont(f);
table.setRowHeight(30);
jsp=new JScrollPane(table,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
c.setLayout(new BorderLayout());
c.add(jsp,BorderLayout.CENTER);
setDefaultCloseOperation(EXIT_ON_CLOSE);
setSize(500,400);
Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
setLocation(d.width/2-250,d.height/2-200);
setVisible(true);
}
public static void main(String gg[])
{
AuthorModelTestCase a=new AuthorModelTestCase();
}
}


