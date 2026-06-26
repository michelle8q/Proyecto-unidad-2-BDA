/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import Excepciones.PersistenciaException;
import Interfaces.IConexionBD;
import Interfaces.IPruebaDAO;
import itson.org.entidades.PruebaEntidad;
import javax.persistence.EntityManager;

/**
 *
 * @author cinca
 */
public class PruebaDAO implements IPruebaDAO {
    
    private final IConexionBD conexionBD;

    public PruebaDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }
    
    @Override
    public PruebaEntidad buscarPorID(int id) throws PersistenciaException {
        EntityManager entityManager = conexionBD.crearConexion();
        try {
            PruebaEntidad prueba = entityManager.find(PruebaEntidad.class, id);

            if (prueba == null) {
                throw new PersistenciaException("No se encontró la prueba con el id: " + id);
            }
            
            return prueba;
            
        } catch (Exception ex) {
            throw new PersistenciaException("Error al buscar la prueba por ID" + ex.getMessage());
            
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }

    }
    
}
