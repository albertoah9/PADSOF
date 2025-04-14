import es.uam.eps.padsof.invoices.*;

import java.io.File;
import java.io.FilenameFilter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SistemaDeFacturacion {

    private static final String HISTORIAL_FACTURAS = "C:/downloads/";  

    // Método para generar una factura en PDF
    public void generarFactura(IInvoiceInfo invoiceInfo) {
        try {
            // Genera un nombre único para el archivo basado en la fecha y hora actual
            String nombreArchivo = generarNombreArchivoUnico();

            // Ruta completa para guardar el archivo
            String outputPath = HISTORIAL_FACTURAS + nombreArchivo;

            // Llamar al método createInvoice de InvoiceSystem para generar el PDF
            InvoiceSystem.createInvoice(invoiceInfo, outputPath);
            System.out.println("Factura generada con éxito en: " + outputPath);
        } catch (NonExistentFileException e) {
            System.err.println("Error: El archivo especificado no existe. " + e.getMessage());
        } catch (UnsupportedImageTypeException e) {
            System.err.println("Error: El tipo de imagen no es compatible. " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Se ha producido un error inesperado: " + e.getMessage());
        }
    }

    private String generarNombreArchivoUnico() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String fechaHora = sdf.format(new Date());
        return "factura_" + fechaHora + ".pdf";
    }

    // Método para listar todas las facturas generadas
   public void listarFacturas() {
        File carpetaFacturas = new File(HISTORIAL_FACTURAS);
        if (!carpetaFacturas.exists() || !carpetaFacturas.isDirectory()) {
            System.out.println("No se ha encontrado el directorio de facturas.");
            return;
        }

        // Crear un filtro que solo permita los archivos .pdf
        File[] archivos = carpetaFacturas.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".pdf");  
            }
        });

        if (archivos != null && archivos.length > 0) {
            System.out.println("Facturas generadas:");
            for (File archivo : archivos) {
                System.out.println(archivo.getName());
            }
        } else {
            System.out.println("No se han generado facturas.");
        }
    }

    //Falta agregar logica necesaria implementar cuando se haga la parte grafica
    // Método para descargar una factura (solo muestra la ruta del archivo en este ejemplo)
    public void descargarFactura(String nombreFactura) {
        File archivoFactura = new File(HISTORIAL_FACTURAS + nombreFactura);
        if (archivoFactura.exists()) {
            System.out.println("Factura descargada: " + archivoFactura.getAbsolutePath());
        } else {
            System.out.println("La factura no existe: " + nombreFactura);
        }
    }
}
