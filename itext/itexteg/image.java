/*import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*; 
import java.io.*;
public class ImagesNextToEachOther 
{
    public static final String DEST = "../imageNext.pdf";
    public static final String IMG = "myImage.jpg";
 
    public static void main(String[] args) throws IOException,
            DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new ImagesNextToEachOther().createPdf(DEST);
    }
 
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.addCell(createImageCell(IMG1));
        table.addCell(createImageCell(IMG2));
        document.add(table);
        document.close();
    }
 
    public static PdfPCell createImageCell(String path) throws DocumentException, IOException {
        Image img = Image.getInstance(path);
        PdfPCell cell = new PdfPCell(img, true);
        return cell;
    }
}

*/

import java.io.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
class insertImage
{
public final static String imagePath="myImage.jpg";
public static void main(String arg[])
{
try
{
Document doc=new Document();
PdfWriter.getInstance(doc,new FileOutputStream("image.pdf"));
doc.open();
PdfPTable table=new PdfPTable(1);
table.setWidthPercentage(100);
table.addCell(new PdfPCell(Image.getInstance(imagePath,true)));
doc.add(table);
doc.close();
}catch(Exception e){}
}
}



