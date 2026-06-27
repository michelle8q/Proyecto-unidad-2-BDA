/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import Excepciones.PersistenciaException;
import itson.org.entidades.DetallesPruebaEntidad;

/**
 *
 * @author cinca
 */
public interface IDetallesPruebaDAO {
    void agregar(DetallesPruebaEntidad detalles) throws PersistenciaException;
}
