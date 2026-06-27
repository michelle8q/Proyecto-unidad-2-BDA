/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaces;

import Excepciones.PersistenciaException;
import itson.org.entidades.ParametroEntidad;
import java.util.List;

/**
 *
 * @author luisf
 */
public interface IParametroDAO {

    ParametroEntidad buscarPorID(Long id) throws PersistenciaException;

    List<ParametroEntidad> buscarPorAnalisis(int idAnalisis) throws PersistenciaException;

    List<ParametroEntidad> buscarTodos() throws PersistenciaException;
}
