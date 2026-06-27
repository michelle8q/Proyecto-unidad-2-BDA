/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dto.ResultadoReporteDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author cinca
 */
public interface IResultadosReporteNegocio {
    List<ResultadoReporteDTO> generarReporteDTO(int idPrueba) throws NegocioException;
}
