/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaces;

import dto.AnalisisDTO;
import java.util.List;

/**
 *
 * @author luisf
 */
public interface IAnalisisNegocio {

    List<AnalisisDTO> obtenerAnalisisParaTabla();

    void guardarAnalisis(dto.AnalisisDTO analisisDTO) throws Exception; 

}
