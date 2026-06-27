/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import Excepciones.PersistenciaException;
import Interfaces.IConexionBD;
import Interfaces.IDetallesPruebaDAO;
import itson.org.entidades.DetallesPruebaEntidad;
import javax.persistence.EntityManager;

/**
 * 
 * @author cinca
 */
public class DetallesPruebaDAO implements IDetallesPruebaDAO {
    
    /**
    * Atributo que representa la instancia única que se usará de la clase.
    */
    private final IConexionBD conexionBD;

    public DetallesPruebaDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    /**
     * Guarda los detalles de una prueba en la base de datos.Este método permite registrar los resultados de una prueba previamente solicitada. 
     * Utiliza el método merge para sincronizar el estado del objeto con la base de datos.
     * Maneja transacciones y rollback en caso de error.
     * 
     * @param detalle objeto que contiene la información de la prueba a persistir
     * @return el detalle de la prueba.
     * @throws Excepciones.PersistenciaException persistenciaException si ocurre 
     * un error durante la operación de persistencia.
     */
    @Override
    public DetallesPruebaEntidad guardar(DetallesPruebaEntidad detalle) throws PersistenciaException {
        EntityManager entityManager = conexionBD.crearConexion();
        try {        
            entityManager.getTransaction().begin();
            entityManager.merge(detalle);
            entityManager.getTransaction().commit();

            return detalle;
        
        } catch (Exception ex) {
            if (entityManager != null && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al guardar el detalle de la prueba: " + ex.getMessage());

        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }
    
    /**
     * Busca el detalle de una prueba por su id
     * 
     * Utiliza el método find para buscar los detalles especificos de una prueba.
     * 
     * @param id el ID es el detalle a buscar.
     * @return el detalle encontrado.
     * @throws Excepciones.PersistenciaException persistenciaException si ocurre 
     * un error durante la operación de persistencia.
     */
    @Override
    public DetallesPruebaEntidad buscarPorID(int id) throws PersistenciaException {
    EntityManager em = conexionBD.crearConexion();
    try {
        DetallesPruebaEntidad detalle = em.find(DetallesPruebaEntidad.class, id);
        if (detalle == null) {
            throw new PersistenciaException("No se encontro el detalle con id: " + id);
        }
        return detalle;
    } catch (PersistenciaException e) {
        throw new PersistenciaException("Error al buscar el detalle: " + e.getMessage());
    } finally {
        em.close();
    }
}
    
}
