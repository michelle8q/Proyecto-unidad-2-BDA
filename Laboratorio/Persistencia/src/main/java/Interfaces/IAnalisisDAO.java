/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import Excepciones.PersistenciaException;
import itson.org.entidades.AnalisisEntidad;
import java.util.List;

/**
 *
 * @author cinca
 */
public interface IAnalisisDAO {

    List<AnalisisEntidad> buscarTodos();

    void agregar(AnalisisEntidad analisis);

    AnalisisEntidad buscarPorId(int id);

    void actualizar(AnalisisEntidad analisis);
    
    List<AnalisisEntidad> buscarPorParametro(String parametro);
    
}
