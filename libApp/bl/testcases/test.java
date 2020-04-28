import com.thinking.machines.library.bl.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.util.*;
class Test extends JFrame implements ActionListener
{
private Container c;
private JTable table;
private JScrollPane jsp;
private AuthorModel m;
private JTextField t1,t2,t3,t4,t5;
private JLabel l1,l2,l3,l4;
private JButton addButton,editButton,removeButton;
private JPanel p1,p2,p3,panel;
Test()
{
m=new AuthorModel();
table=new JTable(m);
table.setRowHeight(30);
Font f=new Font("Verdana",Font.PLAIN,15);
table.setFont(f);
jsp=new JScrollPane(table,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
setSize(500,400);
setDefaultCloseOperation(EXIT_ON_CLOSE);
Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
setLocation(d.width/2-250,d.height/2-200);
setVisible(true);
c=getContentPane();
c.setLayout(new FlowLayout());
t1=new JTextField(20);
t2=new JTextField(5);
t3=new JTextField(20);
t4=new JTextField(5);
t5=new JTextField(20);
l1=new JLabel("Name");
l2=new JLabel("Sr.No");
l3=new JLabel("Name");
l4=new JLabel("Code");
addButton=new JButton("Add");
editButton=new JButton("Edit");
removeButton=new JButton("Remove");
panel=new JPanel();
panel.add(table);
c.add(panel);
p1=new JPanel();
p1.setLayout(new GridLayout(3,4));
p1.add(l1);
p1.add(t1);
p1.add(new JLabel(" "));
p1.add(addButton);
p2=new JPanel();
p2.add(l2);
p2.add(t2);
p2.add(l3);
p2.add(t3);p2.add(editButton);
p3=new JPanel();
p3.add(l4);p3.add(t4);p3.add(removeButton);p3.add(t5);
addButton.addActionListener(this);
editButton.addActionListener(this);
removeButton.addActionListener(this);
c.add(jsp);
c.add(p1);c.add(p2);c.add(p3);
}
public void actionPerformed(ActionEvent ev)
{
AuthorInterface author;
try
{
if(ev.getSource()==addButton)
{
author=new Author(0,t1.getText());
m.addAuthor(author);
}
if(ev.getSource()==editButton)
{
author=new Author();
author.setCode(Integer.parseInt(t2.getText()));
author.setName(t3.getText());
m.updateAuthor(author);
}
if(ev.getSource()==removeButton)
{
m.removeAuthor(Integer.parseInt(t4.getText()));
}
}catch(BLException bl)
{
t5.setText(bl.getMessage());
}
}
public static void main(String gg[])
{
Test t=new Test();
}
}
