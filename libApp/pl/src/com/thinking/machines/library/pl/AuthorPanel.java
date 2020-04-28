package com.thinking.machines.library.pl;
import com.thinking.machines.library.bl.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.filechooser.*;
import java.io.*;
import java.util.*;
class AuthorPanel extends JPanel implements ListSelectionListener,DocumentListener
{
private JLabel titleLabel,searchLabel,searchErrorLabel;
private JTextField searchTextField;
private JButton clearSearchTextFieldButton;
private JTable authorsTable;
private JScrollPane authorScrollPane;
private AuthorModel authorsModel;
private AuthorCRUDPanel authorCrudPanel;
AuthorInterface author;
private static int Add_Mode=1;
private static int Edit_Mode=2;
private static int Delete_Mode=3;
private static int ExportToPDF_Mode=4;
private static int View_Mode=5;
static int mode=5;
AuthorPanel()
{
initComponents();
setAppearance();
this.setViewMode();
authorCrudPanel.setViewMode();
}
public void initComponents()
{
titleLabel=new JLabel("Authors");
searchLabel=new JLabel("Search");
searchErrorLabel=new JLabel("No Error Found");
searchTextField=new JTextField();
clearSearchTextFieldButton=new JButton("x");
authorsModel=new AuthorModel();
authorsTable=new JTable(authorsModel);
authorScrollPane=new JScrollPane(authorsTable,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
authorCrudPanel=new AuthorCRUDPanel();
int x=0;
int y=0;
titleLabel.setBounds(x+5+5,y+5,85+10,20+10);
searchLabel.setBounds(x+5,y+5+30,30+20+5+2,20);
searchTextField.setBounds(x+5+40+20,y+5+30,270+50,20);
clearSearchTextFieldButton.setBounds(x+5+40+20+270+50,y+5+30,40,20);
searchErrorLabel.setBounds(x+5+80+270-50,y+5+20,30+20+5+40,10);
authorScrollPane.setBounds(x+5,y+5+30+30-10,400+50+15,300+20);
authorCrudPanel.setBounds(x+5,y+5+30+30+300+10,460+5,130+20+30+20);
add(titleLabel);
add(searchLabel);
add(searchTextField);
add(clearSearchTextFieldButton);
add(searchErrorLabel);
add(authorScrollPane);
add(authorCrudPanel);
addListeners();
}
public void setAppearance()
{
Font titleFont=new Font("Verdana",Font.BOLD,20);
Font font=new Font("Verdana",Font.PLAIN,15);
Font errorFont=new Font("Verdana",Font.PLAIN,10);
titleLabel.setFont(titleFont);
searchLabel.setFont(font);
searchErrorLabel.setFont(errorFont);
authorsTable.getColumnModel().getColumn(0).setPreferredWidth(70);
authorsTable.getColumnModel().getColumn(1).setPreferredWidth(150);
authorsTable.setRowHeight(30);
authorsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
setLayout(null);
}
public void addListeners()
{
searchTextField.getDocument().addDocumentListener(this);
authorsTable.getSelectionModel().addListSelectionListener(this);
}
public void valueChanged(ListSelectionEvent ev)
{
try
{
AuthorInterface selectedAuthor;
selectedAuthor=authorsModel.getAuthorAt(authorsTable.getSelectedRow());
this.author=selectedAuthor;
authorCrudPanel.authorLabel.setText(author.getName());
}catch(BLException be){}
}
public void search()
{
try
{
String s=searchTextField.getText().trim();
int index=authorsModel.getAuthorIndexByName(s,true,false);
authorsTable.setRowSelectionInterval(index,index);
authorsTable.scrollRectToVisible(new Rectangle(authorsTable.getCellRect(index,0,true)));
}catch(BLException be)
{
searchErrorLabel.setText(be.getMessage());
}
}
public void insertUpdate(DocumentEvent ev)
{
search();
}
public void removeUpdate(DocumentEvent ev)
{
//if(searchTextField.getText().trim().length()==0) authorsTable.clearSelection();
}
public void changedUpdate(DocumentEvent ev){}
public void setViewMode()
{
AuthorPanel.mode=5;
if(authorsModel.getRowCount()==0)
{
authorsTable.setEnabled(false);
searchTextField.setEnabled(false);
clearSearchTextFieldButton.setEnabled(false);
}
else
{
authorsTable.setEnabled(false);
searchTextField.setText("");
searchTextField.setEnabled(false);
clearSearchTextFieldButton.setEnabled(false);
}
}
public void setAddMode()
{
AuthorPanel.mode=1;
authorsTable.setEnabled(false);
searchTextField.setEnabled(false);
clearSearchTextFieldButton.setEnabled(false);
}
public void setEditMode()
{
AuthorPanel.mode=2;
authorsTable.setEnabled(true);
searchTextField.setEnabled(true);
clearSearchTextFieldButton.setEnabled(true);
}
public void setDeleteMode()
{
AuthorPanel.mode=3;
authorsTable.setEnabled(true);
searchTextField.setEnabled(true);
clearSearchTextFieldButton.setEnabled(true);
}
public void setExportToPDFMode()
{
AuthorPanel.mode=4;
authorsTable.setEnabled(false);
searchTextField.setEnabled(false);
clearSearchTextFieldButton.setEnabled(false);
}
public class AuthorCRUDPanel extends JPanel implements ActionListener
{
private JButton addButton,editButton,searchButton,cancelButton,exportToPDFButton,deleteButton;
private JPanel ButtonPanel;
private JLabel authorLabel;
private JTextField authorTextField;
public AuthorCRUDPanel()
{
initComponents();
addListeners();
setAppearance();
}
public void setAuthor(AuthorInterface author)
{
AuthorPanel.this.author=author;
}
public void initComponents()
{
addButton=new JButton("A");
editButton=new JButton("E");
cancelButton=new JButton("C");
deleteButton=new JButton("D");
exportToPDFButton=new JButton("P");
authorTextField=new JTextField();
authorLabel=new JLabel("Author");
ButtonPanel=new JPanel();
ButtonPanel.setSize(320,50);
ButtonPanel.setLayout(null);
int x=0,y=0;
addButton.setBounds(x+5,y+10+40,50,50);
editButton.setBounds(x+5+50+20,y+10+40,50,50);
cancelButton.setBounds(x+5+50+20+50+20,y+10+40,50,50);
deleteButton.setBounds(x+5+50+20+50+20+50+20,y+10+40,50,50);
exportToPDFButton.setBounds(x+5+50+20+50+20+50+20+50+20,y+10+40,50,50);
authorLabel.setBounds(x+5,y+10,70+5,15);
authorTextField.setBounds(x+5+60+30+10,y+10,220,20);
ButtonPanel.setBounds(460/2-300/2-15,30+5,350,130);
ButtonPanel.add(addButton);
ButtonPanel.add(editButton);
ButtonPanel.add(cancelButton);
ButtonPanel.add(deleteButton);
ButtonPanel.add(exportToPDFButton);
add(authorLabel);
add(authorTextField);
add(ButtonPanel);
}
/*public void search()
{
try
{
String s=authorTextField.getText().trim();
int index=authorsModel.getAuthorIndexByName(s,true,false);
authorsTable.setRowSelectionInterval(index,index);
authorsTable.scrollRectToVisible(new Rectangle(authorsTable.getCellRect(index,0,true))); // important concept to get the viewport by scrolling automatically
}catch(BLException be)
{
searchErrorLabel.setText(be.getMessage());
}
}*/
public void addListeners()
{
addButton.addActionListener(this);
editButton.addActionListener(this);
deleteButton.addActionListener(this);
exportToPDFButton.addActionListener(this);
cancelButton.addActionListener(this);
}
public void insertUpdate(DocumentEvent dv)
{
//this.search();
}
public void changedUpdate(DocumentEvent ev){}
public void removeUpdate(DocumentEvent ev){}
public void setViewMode()
{
authorLabel.setText("Author");
if(authorsModel.getRowCount()==0)
{
authorTextField.setVisible(false);
authorLabel.setVisible(true);
addButton.setEnabled(true);
editButton.setEnabled(false);
deleteButton.setEnabled(false);
cancelButton.setEnabled(false);
exportToPDFButton.setEnabled(false);
}
else
{
authorTextField.setVisible(false);
addButton.setEnabled(true);
editButton.setEnabled(true);
deleteButton.setEnabled(true);
cancelButton.setEnabled(true);
exportToPDFButton.setEnabled(true);
authorLabel.setVisible(true);
}
}
public void setAddMode()
{
addButton.setEnabled(true);
editButton.setEnabled(false);
deleteButton.setEnabled(false);              // When will the edit button and delete button be enabled 
cancelButton.setEnabled(true);
exportToPDFButton.setEnabled(false);
authorTextField.setVisible(true);
authorLabel.setVisible(true);
}
public void setEditMode()
{
addButton.setEnabled(false);
editButton.setEnabled(true);
deleteButton.setEnabled(false);
cancelButton.setEnabled(true);
exportToPDFButton.setEnabled(false);
authorTextField.setVisible(true);
authorLabel.setVisible(true);
}
public void setDeleteMode()
{
addButton.setEnabled(false);
editButton.setEnabled(false);
deleteButton.setEnabled(true);
cancelButton.setEnabled(true);
exportToPDFButton.setEnabled(false);
authorTextField.setVisible(false);
authorLabel.setVisible(true);
}
public void setExportToPDFMode()
{
addButton.setEnabled(false);
editButton.setEnabled(false);
deleteButton.setEnabled(false);
cancelButton.setEnabled(true);
exportToPDFButton.setEnabled(true);
authorTextField.setVisible(false);
authorLabel.setVisible(true);
}
public void actionPerformed(ActionEvent ev)
{
if(ev.getSource()==addButton)
{
if(AuthorPanel.mode!=Add_Mode)
{
addButton.setText("S");
authorTextField.setText("");
AuthorPanel.this.setAddMode();
this.setAddMode();
}
else
{
AuthorInterface newAuthor;
String s=authorTextField.getText().trim();
newAuthor=new Author();
newAuthor.setName(s);
this.setAuthor(newAuthor);
try
{
authorsModel.addAuthor(author);
authorLabel.setText(author.getName());
int index=AuthorPanel.this.authorsModel.getAuthorIndexByName(authorTextField.getText(),true,true);
authorsTable.setRowSelectionInterval(index,index);
authorsTable.scrollRectToVisible(new Rectangle(authorsTable.getCellRect(index,0,true)));
AuthorPanel.this.setViewMode();
this.setViewMode();
addButton.setText("A");
}catch(BLException be){}
}
}
if(ev.getSource()==editButton)
{
if(AuthorPanel.mode!=Edit_Mode)
{
editButton.setText("U");
AuthorPanel.this.setEditMode();
this.setEditMode();
}
else if(AuthorPanel.mode==Edit_Mode)
{
if(AuthorPanel.this.authorsTable.getSelectedRow()<0 || AuthorPanel.this.authorsTable.getSelectedRow()>=AuthorPanel.this.authorsModel.getRowCount())
{
JOptionPane.showMessageDialog(AuthorPanel.this,"Select Author to edit","Error",JOptionPane.WARNING_MESSAGE);
AuthorPanel.this.setViewMode();
this.setViewMode();
return;
}
AuthorInterface newAuthor=new Author();
try
{
newAuthor.setName(authorTextField.getText().trim());
newAuthor.setCode(author.getCode());
this.setAuthor(newAuthor);
authorsModel.updateAuthor(author);
authorLabel.setText(author.getName());
int index=AuthorPanel.this.authorsModel.getAuthorIndexByName(authorTextField.getText(),true,true);
authorsTable.setRowSelectionInterval(index,index);
authorsTable.scrollRectToVisible(new Rectangle(authorsTable.getCellRect(index,0,true)));
AuthorPanel.this.searchTextField.setText("");
AuthorPanel.this.setViewMode();
this.setViewMode();
editButton.setText("E");
}catch(BLException be){}
}
}
if(ev.getSource()==deleteButton)
{
if(AuthorPanel.mode!=Delete_Mode)
{
AuthorPanel.this.setDeleteMode();
this.setDeleteMode();
}
else
{
if(AuthorPanel.this.authorsTable.getSelectedRow()<0 || AuthorPanel.this.authorsTable.getSelectedRow()>=AuthorPanel.this.authorsModel.getRowCount())
{
//JOptionPane.showMessageDialog(AuthorPanel.this,"Select Author to delete","Error",JOptionPane.WARNING_MESSAGE);
AuthorPanel.this.setViewMode();
this.setViewMode();
return;
}
int dialogResult=JOptionPane.showConfirmDialog(AuthorPanel.this,"Delete"+author.getName(),"Confirmation",JOptionPane.YES_NO_OPTION);
if(dialogResult==JOptionPane.YES_OPTION)
{
try
{
authorsModel.removeAuthor(author.getCode());
authorLabel.setText(author.getName()+" Removed");
AuthorPanel.this.setViewMode();
this.setViewMode();
}catch(BLException be){}
}
}
}
if(ev.getSource()==cancelButton)
{
this.authorTextField.setText("");
AuthorPanel.this.setViewMode();
this.setViewMode();
}
if(ev.getSource()==exportToPDFButton)
{
AuthorPanel.this.setExportToPDFMode();
this.setExportToPDFMode();
JFileChooser jfc=new JFileChooser();
jfc.setCurrentDirectory(new File("."));
jfc.setAcceptAllFileFilterUsed(false);
jfc.setFileFilter(new FileNameExtensionFilter("PDF Files","pdf"));
int selectedOption=jfc.showSaveDialog(AuthorPanel.this);
if(selectedOption==jfc.APPROVE_OPTION)
{
File selectedFile=jfc.getSelectedFile();
String path=selectedFile.getAbsolutePath();
if(path.endsWith(".pdf")==false)
{
if(path.endsWith(".")==true) path=path+"pdf";
else path=path+".pdf";
jfc.setSelectedFile(new File(path));
}
try
{
com.itextpdf.text.Document document=new com.itextpdf.text.Document();
com.itextpdf.text.pdf.PdfWriter writer=com.itextpdf.text.pdf.PdfWriter.getInstance(document,new FileOutputStream(selectedFile.getName()));
document.open();
com.itextpdf.text.pdf.PdfContentByte cb=writer.getDirectContent();
com.itextpdf.text.pdf.PdfPTable table=null;
com.itextpdf.text.pdf.PdfPTable headerTable=null;
com.itextpdf.text.pdf.PdfPTable footer=null;
com.itextpdf.text.pdf.PdfPCell cell=null;
com.itextpdf.text.Paragraph para=null;
com.itextpdf.text.Image image=com.itextpdf.text.Image.getInstance(AuthorPanel.class.getResource("books.png"));
com.itextpdf.text.Font companyFont=com.itextpdf.text.FontFactory.getFont(com.itextpdf.text.FontFactory.HELVETICA,20,com.itextpdf.text.Font.BOLD,com.itextpdf.text.BaseColor.BLUE);
com.itextpdf.text.Font authorFont=com.itextpdf.text.FontFactory.getFont(com.itextpdf.text.FontFactory.HELVETICA,15,com.itextpdf.text.Font.BOLD,com.itextpdf.text.BaseColor.RED);
//com.itextpdf.text.Image image=com.itextpdf.text.Image.getInstance("books.png");
int i=0;
boolean np=true;
int ps=10;
while(i<authorsModel.getRowCount())
{
if(np==true)
{
headerTable=new com.itextpdf.text.pdf.PdfPTable(2);
headerTable.setTotalWidth(document.getPageSize().getWidth()-80);
headerTable.setWidths(new float[]{1,4});
image.scaleToFit(40,40);
cell=new com.itextpdf.text.pdf.PdfPCell(image);
cell.setBorder(com.itextpdf.text.pdf.PdfPCell.NO_BORDER);
headerTable.addCell(cell);
cell=new com.itextpdf.text.pdf.PdfPCell(new com.itextpdf.text.Phrase("Company",companyFont));
cell.setBorder(com.itextpdf.text.pdf.PdfPCell.NO_BORDER);
cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
headerTable.addCell(cell);
headerTable.writeSelectedRows(0,-1,25,document.getPageSize().getHeight()-10,cb);
para=new com.itextpdf.text.Paragraph("Author",authorFont);
para.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
document.add(para);
para=new com.itextpdf.text.Paragraph("Page:"+writer.getPageNumber());
para.setAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);
document.add(para);
table=new com.itextpdf.text.pdf.PdfPTable(2);
table.setTotalWidth(330);
table.setWidths(new float[]{1,3});
cell=new com.itextpdf.text.pdf.PdfPCell(new com.itextpdf.text.Phrase("S.No"));
cell.setBackgroundColor(com.itextpdf.text.BaseColor.GRAY);
cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
table.addCell(cell);
cell=new com.itextpdf.text.pdf.PdfPCell(new com.itextpdf.text.Phrase("Author"));
cell.setBackgroundColor(com.itextpdf.text.BaseColor.GRAY);
cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
table.addCell(cell);
np=false;
}
//document.open();
cell=new com.itextpdf.text.pdf.PdfPCell(new com.itextpdf.text.Phrase(String.valueOf(i+1)));
cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);
table.addCell(cell);
cell=new com.itextpdf.text.pdf.PdfPCell(new com.itextpdf.text.Phrase(authorsModel.getAuthorAt(i).getName()));
cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_LEFT); 
table.addCell(cell);
i++;
if(i%ps==0 || i==authorsModel.getRowCount())
{
// build footer
//document.add(table);
table.writeSelectedRows(0,-1,(document.getPageSize().getWidth()-table.getTotalWidth())/2,(document.getPageSize().getHeight()-table.getTotalHeight())/2,cb);
footer=new com.itextpdf.text.pdf.PdfPTable(3);
footer.setTotalWidth(document.getPageSize().getWidth()-60);
footer.setWidths(new float[]{1,1,1});
cell=new com.itextpdf.text.pdf.PdfPCell(new com.itextpdf.text.Phrase("Software By:"));
cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);
cell.setBorder(com.itextpdf.text.pdf.PdfPCell.NO_BORDER);
footer.addCell(cell);
cell=new com.itextpdf.text.pdf.PdfPCell(new com.itextpdf.text.Phrase("Ibrahim Anis"));
cell.setBorder(com.itextpdf.text.pdf.PdfPCell.NO_BORDER);
cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
footer.addCell(cell);
cell=new com.itextpdf.text.pdf.PdfPCell(new com.itextpdf.text.Phrase(String.valueOf(Calendar.getInstance().getTime())));
cell.setBorder(com.itextpdf.text.pdf.PdfPCell.NO_BORDER);
cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);
footer.addCell(cell);
footer.writeSelectedRows(0,-1,20,document.getPageSize().getHeight()-table.getTotalHeight()-175,cb);
if(i==authorsModel.getRowCount()) break;
document.newPage();
np=true;
}
}
document.close();
}catch(Exception e)
{
System.out.println(e);
}
}
}
}
public void setAppearance()
{
setSize(470,200);
Font font=new Font("Verdana",Font.PLAIN,15);
authorLabel.setFont(font);
setLayout(null);
}
}
}
