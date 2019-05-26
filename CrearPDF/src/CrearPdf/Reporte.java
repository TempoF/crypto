package CrearPdf;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import java.io.*;
import java.util.ArrayList;

public class Reporte {

    private static final Font Salto = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);    
    private static final Font Encabezados = new Font(Font.FontFamily.TIMES_ROMAN, 22, Font.BOLD, BaseColor.BLUE);
    private static final Font Celdas = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD, BaseColor.GRAY);
    ArrayList<String> Candidatos = new ArrayList<>();
    ArrayList<String> Partido = new ArrayList<>();
    ArrayList<String> Porcentaje = new ArrayList<>();
    private static final String Banner = "Img/Banner.png";
    
    public void AgregarContenido(String candidato, String partido, String porcentaje)
    {
        this.Candidatos.add(candidato);
        this.Partido.add(partido);
        this.Porcentaje.add(porcentaje);
    }
    
    public void createPDF(File pdfNewFile) {
        // We create the document and set the file name.        
        // Creamos el documento e indicamos el nombre del fichero.
        try {
            Document document = new Document();
            try {

                PdfWriter.getInstance(document, new FileOutputStream(pdfNewFile));

            } catch (FileNotFoundException fileNotFoundException) {
                System.out.println("(No se encontró el fichero para generar el pdf)" + fileNotFoundException);
            }
            document.open();
            // We add metadata to PDF
            // Añadimos los metadatos del PDF
            document.addTitle("Reporte del resultado las elecciones");
            document.addKeywords("Java, Votex, Cryptography");
            document.addAuthor("Votex");
            document.addCreator("Votex");
            
            // First page
            // Primera página 
            
            //chapter.add(new Paragraph("This is the paragraph", paragraphFont));
            // We add an image (Añadimos una imagen)
            Image image;
            try {
                image = Image.getInstance(Banner);
                float altura = image.getHeight();
                image.setAbsolutePosition(0, 842 - altura);
                document.add(image);
            } catch (BadElementException ex) {
                System.out.println("Image BadElementException" +  ex);
            } catch (IOException ex) {
                System.out.println("Image IOException " +  ex);
            }
            

            Paragraph saltoBanner = new Paragraph("\n\n\n\n\n\n\n\n", Salto);
            Integer numColumns = 3;
            //Integer numRows = this.Candidatos.size();
            Integer numRows = 4;
            PdfPTable table = new PdfPTable(numColumns); 
            PdfPCell columnHeader;
            table.setWidthPercentage(95);
            float[] medidaCeldas = {50,25,25};
            table.setWidths(medidaCeldas);
            
            //Agregar Encabezados por cada columna
            PdfPCell celda;
            celda = new PdfPCell (new Paragraph("Candidato",Encabezados));
            celda.setBorder(0);
            columnHeader = celda;
            columnHeader.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            table.addCell(columnHeader);
            
            celda = new PdfPCell (new Paragraph("Partido",Encabezados));
            celda.setBorder(0);
            columnHeader = celda;
            columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(columnHeader);
            
            celda = new PdfPCell (new Paragraph("Porcentaje",Encabezados));
            celda.setBorder(0);
            columnHeader = celda;
            columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(columnHeader);
            
            table.setHeaderRows(1);
            
            
            
            //Agregar Celdas
            for(int i = 0; i < this.Candidatos.size();i++)
            {
                celda = new PdfPCell (new Paragraph(this.Candidatos.get(i),Celdas));
                celda.setBorder(0);
                table.addCell(celda);
                
                celda = new PdfPCell (new Paragraph(this.Partido.get(i),Celdas));
                celda.setBorder(0);
                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(celda);
            
                celda = new PdfPCell (new Paragraph(this.Porcentaje.get(i),Celdas));
                celda.setBorder(0);
                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(celda);
                 
            }
            //
            
            // We add the table (Añadimos la tabla)
            // We add the paragraph with the table (Añadimos el elemento con la tabla).
            //document.add(paragraph);
            document.add(saltoBanner);
            document.add(table);
            
            document.close();
            System.out.println("¡Se ha generado el PDF!");
        } catch (DocumentException documentException) {
            System.out.println("Se ha producido un error al generar un documento : " + documentException);
        }
    }
}
