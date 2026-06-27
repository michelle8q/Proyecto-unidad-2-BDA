/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import Excepciones.PersistenciaException;
import Interfaces.IConexionBD;
import Interfaces.IDetallesPruebaDAO;
import itson.org.entidades.DetallesPruebaEntidad;
import itson.org.entidades.ParametroEntidad;
import itson.org.entidades.PruebaEntidad;
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
     * Guarda los detalles de una prueba en la base de datos.
     * Este método permite registrar los resultados de una prueba previamente solicitada.
     * 
     * Utiliza el método merge para sincronizar el estado del objeto con la base de datos.
     * Maneja transacciones y rollback en caso de error.
     * 
     * @param detalle objeto que contiene la información de la prueba a persistir
     * @throws Excepciones.PersistenciaException persistenciaException si ocurre 
     * un error durante la operación de persistencia.
     */
    @Override
    public void agregar(DetallesPruebaEntidad detalle) throws PersistenciaException {
        EntityManager em = conexionBD.crearConexion();
        try {

            em.getTransaction().begin();

            if (detalle.getPrueba() != null && detalle.getPrueba().getId() > 0) {
                PruebaEntidad prueba = em.find(PruebaEntidad.class, detalle.getPrueba().getId());

                detalle.setPrueba(prueba);
            }

            if (detalle.getParametro() != null && detalle.getParametro().getId() > 0) {
                ParametroEntidad parametro = em.find(ParametroEntidad.class, detalle.getParametro().getId());

                detalle.setParametro(parametro);
            }

            em.merge(detalle);

            em.getTransaction().commit();

        } catch (Exception e) {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            
            throw new PersistenciaException("Error al actualizar el detalle de la prueba: " + e.getMessage());

        } finally {
            em.close();
        }
    }
    
}
