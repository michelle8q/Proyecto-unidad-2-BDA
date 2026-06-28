/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dto.ResultadoReporteDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 * Contrato para la generación del reporte de resultados de pruebas.
 * 
 * @author cinca
 */
public interface IResultadosReporteNegocio {
    
    /**
     * Genera la lista de DTOs necesaria para construir el reporte de resultados.
     *
     * @param idPrueba identificador de la prueba a reportar
     * @return lista de ResultadoReporteDTO con los datos del reporte
     * @throws NegocioException si el id es invalido o no hay resultados
     */
    List<ResultadoReporteDTO> generarReporteDTO(int idPrueba) throws NegocioException;
}
