/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dto.PruebaDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author cinca
 */
public interface IPruebaNegocio {
    List<PruebaDTO> obtenerPruebaParaTabla() throws NegocioException;
}
