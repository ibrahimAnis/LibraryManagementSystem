package com.thinking.machines.library.pl;
import com.thinking.machines.library.bl.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class AuthorFrame extends JFrame
{
private Container container;
private AuthorPanel authorPanel;
public AuthorFrame()
{
super(Application.TITLE);
initComponents();
addListeners();
}
public void initComponents()
{
container=getContentPane();
authorPanel=new AuthorPanel();
setAppearance();
container.add(authorPanel);
}
public void setAppearance()
{
setSize(500,600);
setDefaultCloseOperation(EXIT_ON_CLOSE);
Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
setLocation(d.width/2-this.getWidth()/2,d.height/2-this.getHeight()/2);
authorPanel.setBorder(BorderFactory.createLineBorder(Color.red));
container.setLayout(null);
authorPanel.setBounds(5+5,5,475,580);
}
public void addListeners()
{

}
}
