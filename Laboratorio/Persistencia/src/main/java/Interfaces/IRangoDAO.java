/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import Excepciones.PersistenciaException;
import itson.org.entidades.RangoEntidad;

/**
 *
 * @author cinca
 */
public interface IRangoDAO {
    RangoEntidad buscarPorParametroYSexo(int idParametro, String sexo) throws PersistenciaException;
}
