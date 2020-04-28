import java.io.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
public class drawLine
{
public static void main(String gg[])
{
try
{
Document doc=new Document();
PdfWriter writer=PdfWriter.getInstance(doc,new FileOutputStream("drawLine.pdf"));
doc.open();
PdfContentByte canvas=writer.getDirectContent();
CMYKColor magentaColor = new CMYKColor(0.0f,0.0f,0.33f,0.0f);
canvas.setColorStroke(magentaColor);
	canvas.moveTo(0,0);
        canvas.lineTo(25,12);
       // canvas.lineTo(559, 36);
       // canvas.lineTo(559, 806);
        canvas.closePathStroke();
        doc.close();
}catch(Exception e){}
}

}

