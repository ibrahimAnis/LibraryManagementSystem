import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.*;
public class abcd
{
public static void main(String arg[])
{
try
{
Document doc=new Document();  // can't write new Document("50 Goodreads") It's an error
PdfWriter.getInstance(doc,new FileOutputStream("abcd.pdf"));
doc.open();
Anchor a=new Anchor("Anchor");

//Paragraph par=new Paragraph("Hello World");
Paragraph par=new Paragraph("Hello World",new Font(Font.FontFamily.TIMES_ROMAN,45,Font.BOLD));
doc.addTitle("50 Goodreads"); // see the top bar
//doc.addSubject("Top Novelists"); // don't know

doc.add(a);
doc.add(par);
doc.close();
}catch(DocumentException e)
{
e.printStackTrace();
}
}
}

