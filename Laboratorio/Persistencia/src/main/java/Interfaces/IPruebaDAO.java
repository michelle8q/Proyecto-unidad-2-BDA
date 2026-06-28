/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import Excepciones.PersistenciaException;
import itson.org.entidades.PruebaEntidad;
import java.util.List;

/**
 * Contrato para las operaciones de acceso a datos de pruebas de laboratorio.
 * 
 * @author cinca
 */
public interface IPruebaDAO {
    
    /**
     * Busca una prueba por su identificador nuico.
     *
     * @param id identificador de la prueba
     * @return PruebaEntidad encontrada
     * @throws PersistenciaException si no existe o hay error en la consulta
     */
    PruebaEntidad buscarPorID(int id) throws PersistenciaException;
    
    /**
     * Obtiene todas las pruebas registradas.
     *
     * @return lista de PruebaEntidad
     */
    List<PruebaEntidad> buscarTodos();
    
    /**
     * Busca una prueba por su folio.
     *
     * @param folio folio de la prueba
     * @return PruebaEntidad encontrada
     * @throws PersistenciaException si no existe o hay error en la consulta
     */
    PruebaEntidad buscarPorFolio(String folio) throws PersistenciaException;

}
