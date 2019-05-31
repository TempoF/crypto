package CrearPdf;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import java.io.*;
import java.util.ArrayList;

public class Reporte {
    private static final int cantidad = 9;
    private static final Font Salto = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);    
    private static final Font Encabezados = new Font(Font.FontFamily.TIMES_ROMAN, 22, Font.BOLD, BaseColor.BLUE);
    private static final Font Footer = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK);
    private static final Font Textos = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD, BaseColor.BLACK);
    private static final Font Celdas = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD, BaseColor.GRAY);
    String [] Candidatos = new String[cantidad];
    String [] Partido = new String[cantidad];
    float [] Porcentaje = new float[cantidad];
    int num = 0;
    private static final String Banner = "Img/Banner.png";
    
    public void AgregarDatos(String candidato, String partido, float porcentaje)
    {
        this.Candidatos[num] = candidato;
        this.Partido[num] = partido;
        this.Porcentaje[num] = porcentaje;
        num++;
    }
    public void ordenar()
    {
        for (int i = 0 ; i < this.Porcentaje.length - 1 ; i++) {
            int max = i;
 
            for (int j = i + 1 ; j < this.Porcentaje.length ; j++) {
                if (this.Porcentaje[j] > this.Porcentaje[max]) {
                    max = j;    
                }
            }
 
            if (i != max) {
                float aux = this.Porcentaje[i];
                String auxc = this.Candidatos[i];
                String auxp = this.Partido[i];
                this.Porcentaje[i] = this.Porcentaje[max];
                this.Candidatos[i] = this.Candidatos[max];
                this.Partido[i] = this.Partido[max];
                this.Porcentaje[max] = aux;
                this.Candidatos[max] = auxc;
                this.Partido[max] = auxp;
            }
        }
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
            
            Paragraph Encabezado = new Paragraph("\n Los resultados de las votaciones realizadas el 1 de Junio de 2019 son: \n\n\n\n", Textos);
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
            
            
            ordenar();
            //Agregar Celdas
            for(int i = 0; i < this.Candidatos.length;i++)
            {
                celda = new PdfPCell (new Paragraph(this.Candidatos[i],Celdas));
                celda.setBorder(0);
                table.addCell(celda);
                
                celda = new PdfPCell (new Paragraph(this.Partido[i],Celdas));
                celda.setBorder(0);
                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(celda);
                celda = new PdfPCell (new Paragraph(Float.toString(this.Porcentaje[i])+"%",Celdas));
                celda.setBorder(0);
                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(celda);
                 
            }
            Paragraph footer = new Paragraph("\n\n\n\n\n\n\n\n\n\nLos votos emitidos son anónimos y los resultados se han obtenido con Votex.\nVotex® Sistema de votaciones electrónicas.\nCryptography - Grupo 3CM2\nExpoESCOM 2019  ", Footer);
            //
            
            // We add the table (Añadimos la tabla)
            // We add the paragraph with the table (Añadimos el elemento con la tabla).
            //document.add(paragraph);
            document.add(saltoBanner);
            document.add(Encabezado);
            document.add(table);
            document.add(footer);
            
            document.close();
            System.out.println("¡Se ha generado el PDF!");
        } catch (DocumentException documentException) {
            System.out.println("Se ha producido un error al generar un documento : " + documentException);
        }
    }
}
