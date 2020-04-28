import java.io.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
public class ImageAbsolutePositionPDF
{
public static void main(String arg[])
{
try
{
Document doc=new Document();
PdfWriter writer=PdfWriter.getInstance(doc,new FileOutputStream("imageToAbsltPos.pdf"));
doc.open();
Image image=Image.getInstance("myImage.jpg");
System.out.println("height:"+doc.getPageSize().getHeight()+"width:"+doc.getPageSize().getWidth()+"image height"+image.getHeight());

image.setAbsolutePosition(0+50,doc.getPageSize().getHeight()-50);
image.scaleToFit(50,50);   // height of the page doc.getPageSize().getHeight()
doc.add(image);
doc.close();
}catch(Exception e)
{
System.out.println(e);
}
}
}
