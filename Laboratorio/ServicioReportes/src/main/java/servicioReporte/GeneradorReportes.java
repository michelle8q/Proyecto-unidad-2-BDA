/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicioReporte;

import dto.ResultadoReporteDTO;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 * Servicio encargado de generar y visualizar reportes de resultados de pruebas
 * de laboratorio utilizando la biblioteca JasperReports.
 * 
 * Esta clase pertenece al modulo de ServicioReportes, separado de las capas
 * de Negocio y Persistencia.
 * 
 * Se ubica en un modulo porque:
 * Persistencia solo debe conocer entidades y operaciones de base de datos.
 * Negocio solo debe transformar y validar datos,
 * la generación de reportes es un servicio externo que depende de JasperReports, 
 * una libreria que no tiene relación con la lógica de negocio ni con la base de datos.
 * 
 * @author cinca
 */
public class GeneradorReportes {
    
     /**
     * Genera y muestra el reporte de resultados de una prueba de laboratorio.
     *
     * Carga el archivo compilado de JasperReports (.jasper) desde el classpath,
     * lo llena con la lista de DTOs recibida y lo despliega en el visualizador de
     * JasperReports.
     *
     * @param resultados lista de ResultadoReporteDTO con los datos a mostrar en el reporte
     * 
     */
    public void generarReportePrueba(List<ResultadoReporteDTO> resultados) {
        try {
            
            JRBeanCollectionDataSource datos = new JRBeanCollectionDataSource(resultados);
            InputStream reporte = getClass().getResourceAsStream("/ReportePrueba.jasper");
            
            // Para obtener la imagen
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("logo", getClass().getResourceAsStream("/imss-logo.png"));
            
            System.out.println("Reporte: " + reporte); // agrega esto
            JasperPrint reporteLleno = JasperFillManager.fillReport(reporte, parametros, datos);
            JasperViewer.viewReport(reporteLleno, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
