
package crearpdf;
import CrearPdf.Reporte;
import java.io.File;
public class CrearPDF {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Reporte reporte = new Reporte();
        reporte.AgregarContenido("Pedro Pica", "Picapiedra","52%");
        reporte.AgregarContenido("Pablo", "Marmol", "48%");
        reporte.AgregarContenido("xxxx", "yyy", "0%");
        reporte.createPDF(new File("Prueba.pdf"));
    }
    
}
