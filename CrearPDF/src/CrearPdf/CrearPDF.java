
package crearpdf;
import CrearPdf.Reporte;
import java.io.File;
public class CrearPDF {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Reporte reporte = new Reporte();
        reporte.AgregarDatos("Messengure", "Equipo 1",(float)12.2);
        reporte.AgregarDatos("Escomonedas","Equipo 2",(float)49.2);
        reporte.AgregarDatos("Hidden Text","Equipo 3", (float)30.7);
        reporte.AgregarDatos("VoteESCOM", "Equipo 4", (float)59.1);
        reporte.AgregarDatos("Secret Sharer", "Equipo 5", (float)8.6);
        reporte.AgregarDatos("XXIOT", "Equipo 6", (float)12.0);
        reporte.AgregarDatos("Cyber Digital Cipher","Equipo 7",(float)32.0);
        reporte.AgregarDatos("Light Keychain","Equipo 8",(float)22.3);
        reporte.AgregarDatos("Votex","Equipo 9",(float)999.99);
        //reporte.AgregarDatos("","",(float));
        reporte.createPDF(new File("Prueba.pdf"));
    }
    
}
