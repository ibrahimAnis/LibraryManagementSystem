import java.io.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
class Table
{
public static void main(String arg[])
{
try
{
Document document=new Document();
document.setPageSize(PageSize.A5);
document.setMargins(36,72,108,180);
document.setMarginMirroring(true);
 //Anchor anchor=new Anchor("Table",Font.FontFamily.TIMES_ROMAN,25,Font.NORMAL,BaseColor.RED); wrongly typed 
Anchor anchor=new Anchor("Table");
Paragraph preface=new Paragraph();
//addEmptyLine(preface,1);
PdfWriter.getInstance(document,new FileOutputStream("table.pdf"));
document.open();
PdfPTable table=new PdfPTable(3);

PdfPCell c1=new PdfPCell(new Phrase("S.No"));
PdfPCell c2=new PdfPCell(new Phrase("Name"));
c1.setRowspan(2);
c2.setRowSpan(1);
c1.setColspan(2);
c1.setHorizontalAlignment(Element.ALIGN_CENTER);
c2.setHorizontalAlignment(Element.ALIGN_CENTER);
table.addCell(c1);
table.addCell(c2);
table.addCell("101");
table.addCell("Ibrahim Anis");
table.addCell("102");
table.addCell("103");
table.addCell("Sherlyn Cooper");

document.add(anchor);
document.add(table);
document.close();
}catch(Exception e)
{
System.out.println(e);
}
}
}



