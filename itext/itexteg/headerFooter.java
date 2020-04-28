/*    public PdfPTable getTable(DatabaseConnection connection, Date day)
        throws SQLException, DocumentException, IOException {
    	// Create a table with 7 columns
        PdfPTable table = new PdfPTable(new float[] { 2, 1, 2, 5, 1, 3, 2 });
        table.setWidthPercentage(100f);
        table.getDefaultCell().setUseAscender(true);
        table.getDefaultCell().setUseDescender(true);
        // Add the first header row
        Font f = new Font();
        f.setColor(BaseColor.WHITE);
        PdfPCell cell = new PdfPCell(new Phrase(day.toString(), f));
        cell.setBackgroundColor(BaseColor.BLACK);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(7);
        table.addCell(cell);
        // Add the second header row twice
        table.getDefaultCell().setBackgroundColor(BaseColor.LIGHT_GRAY);
        for (int i = 0; i < 2; i++) {
            table.addCell("Location");
            table.addCell("Time");
            table.addCell("Run Length");
            table.addCell("Title");
            table.addCell("Year");
            table.addCell("Directors");
            table.addCell("Countries");
        }
        table.getDefaultCell().setBackgroundColor(null);
        // There are three special rows
        table.setHeaderRows(3);
        // One of them is a footer
        table.setFooterRows(1);
        // Now let's loop over the screenings
        List<Screening> screenings = PojoFactory.getScreenings(connection, day);
        Movie movie;
        for (Screening screening : screenings) {
            movie = screening.getMovie();
            table.addCell(screening.getLocation());
            table.addCell(String.format("%1$tH:%1$tM", screening.getTime()));
            table.addCell(String.format("%d '", movie.getDuration()));
            table.addCell(movie.getMovieTitle());
            table.addCell(String.valueOf(movie.getYear()));
            cell = new PdfPCell();
            cell.setUseAscender(true);
            cell.setUseDescender(true);
            cell.addElement(PojoToElementFactory.getDirectorList(movie));
            table.addCell(cell);
            cell = new PdfPCell();
            cell.setUseAscender(true);
            cell.setUseDescender(true);
            cell.addElement(PojoToElementFactory.getCountryList(movie));
            table.addCell(cell);
        }
        return table;
    }
 
    /**
     * Main method.
     * @param args no arguments needed
     * @throws DocumentException 
     * @throws IOException
     * @throws SQLException
     
    public static void main(String[] args)
        throws SQLException, DocumentException, IOException {
        new HeaderFooter1().createPdf(RESULT);
    }
}




import java.io.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
public class HeaderFooter
{
public static void main(String arg[])
{
Document document=new Document();
PdfWriter writer=PdfWriter.getInstance(document,new FileOutputStream("HeaderFooter.java"));
PdfPTable table=new PdfPTable();
}
}*/

 
import java.io.FileOutputStream;
import java.io.IOException;
 
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
 
class Spacing {
 
    /** The resulting PDF file. */
    public static final String RESULT = "spacing.pdf";
 
    /**
     * Main method.
     * @param    args    no arguments needed
     * @throws DocumentException 
     * @throws IOException
     */
    public static void main(String[] args)
        throws DocumentException, IOException {
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter.getInstance(document, new FileOutputStream(RESULT));
        // step 3
        document.open();
        // step 4
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        Phrase p = new Phrase(
            "Dr. iText or: How I Learned to Stop Worrying " +
            "and Love the Portable Document Format.");
        PdfPCell cell = new PdfPCell(p);
        table.addCell("default leading / spacing");
        table.addCell(cell);
        table.addCell("absolute leading: 20");
        cell.setLeading(20f, 0f);
        table.addCell(cell);
        table.addCell("absolute leading: 3; relative leading: 1.2");
        cell.setLeading(3f, 1.2f);
        table.addCell(cell);
        table.addCell("absolute leading: 0; relative leading: 1.2");
        cell.setLeading(0f, 1.2f);
        table.addCell(cell);
        table.addCell("no leading at all");
        cell.setLeading(0f, 0f);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(
            "Dr. iText or: How I Learned to Stop Worrying and Love PDF"));
        table.addCell("padding 10");
        cell.setPadding(10);
        table.addCell(cell);
        table.addCell("padding 0");
        cell.setPadding(0);
        table.addCell(cell);
        table.addCell("different padding for left, right, top and bottom");
        cell.setPaddingLeft(20);
        cell.setPaddingRight(50);
        cell.setPaddingTop(0);
        cell.setPaddingBottom(5);
        table.addCell(cell);
        p = new Phrase("iText in Action Second Edition");
        table.getDefaultCell().setPadding(2);
        table.getDefaultCell().setUseAscender(false);
        table.getDefaultCell().setUseDescender(false);
        table.addCell("padding 2; no ascender, no descender");
        table.addCell(p);
        table.getDefaultCell().setUseAscender(true);
        table.getDefaultCell().setUseDescender(false);
        table.addCell("padding 2; ascender, no descender");
        table.addCell(p);
        table.getDefaultCell().setUseAscender(false);
        table.getDefaultCell().setUseDescender(true);
        table.addCell("padding 2; descender, no ascender");
        table.addCell(p);
        table.getDefaultCell().setUseAscender(true);
        table.getDefaultCell().setUseDescender(true);
        table.addCell("padding 2; ascender and descender");
        cell.setPadding(2);
        cell.setUseAscender(true);
        cell.setUseDescender(true);
        table.addCell(p);
        document.add(table);
        // step 5
        document.close();
    }
}
