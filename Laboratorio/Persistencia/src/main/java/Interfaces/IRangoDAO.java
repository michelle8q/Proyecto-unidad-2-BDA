/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import Excepciones.PersistenciaException;
import itson.org.entidades.RangoEntidad;

/**
 * Contrato para las operaciones de acceso a datos de rangos de referencia.
 * 
 * @author cinca
 */
public interface IRangoDAO {
    
    /**
     * Busca el rango de referencia de un parámetro segun el sexo del paciente.
     *
     * @param idParametro identificador del parámetro
     * @param sexo sexo del paciente
     * @return RangoEntidad encontrada, o null si no existe
     * @throws PersistenciaException si ocurre un error en la consulta
     */
    RangoEntidad buscarPorParametroYSexo(int idParametro, String sexo) throws PersistenciaException;
}
