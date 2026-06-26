/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import Excepciones.PersistenciaException;
import itson.org.entidades.PruebaEntidad;

/**
 *
 * @author cinca
 */
public interface IPruebaDAO {
    PruebaEntidad buscarPorID(int id) throws PersistenciaException;
}
