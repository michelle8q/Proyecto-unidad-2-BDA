/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dto.PruebaBusquedaDTO;
import dto.DetallesPruebaDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author cinca
 */
public interface IPruebaNegocio {
   void guardarResultados(List<DetallesPruebaDTO> detallesDTO) throws NegocioException;
   PruebaBusquedaDTO buscarPorFolio(String folio) throws NegocioException;
}
