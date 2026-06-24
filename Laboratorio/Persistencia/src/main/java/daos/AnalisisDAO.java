/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import Excepciones.PersistenciaException;
import Interfaces.IAnalisisDAO;
import Interfaces.IConexionBD;
import itson.org.entidades.AnalisisEntidad;

/**
 *
 * @author cinca
 */
public class AnalisisDAO implements IAnalisisDAO {
    
    private final IConexionBD conexionBD;

    public AnalisisDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    @Override
    public AnalisisEntidad guardar(AnalisisEntidad nuevoAnalisis) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
