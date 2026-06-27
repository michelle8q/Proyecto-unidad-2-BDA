/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import Excepciones.PersistenciaException;
import Interfaces.IConexionBD;
import Interfaces.IPruebaDAO;
import itson.org.entidades.PruebaEntidad;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Implementación de la interfaz IPruebaDAO para la gestión de pruebas solicitadas en la
 * base de datos utilizando JPA.
 *
 * Esta clase contiene la lógica necesaria para realizar operaciones CRUD sobre
 * la entidad Prueba, manejando transacciones y excepciones de persistencia.
 *
 * Se apoya en la clase ConexionBD para obtener instancias de EntityManager.
 * 
 * @author cinca
 */
public class PruebaDAO implements IPruebaDAO {
    
    /**
    * Atributo que representa la instancia única que se usará de la clase.
    */
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

    @Override
    public List<PruebaEntidad> buscarTodos() {
       EntityManager entityManager = conexionBD.crearConexion();

        try {
            String jpql = "SELECT a FROM PruebaEntidad a";
            TypedQuery<PruebaEntidad> query = entityManager.createQuery(jpql, PruebaEntidad.class);
            return query.getResultList();
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    @Override
    public PruebaEntidad buscarPorFolio(String folio) throws PersistenciaException {
        EntityManager em = conexionBD.crearConexion();

        try {
            CriteriaBuilder criteria = em.getCriteriaBuilder();

            CriteriaQuery<PruebaEntidad> query = criteria.createQuery(PruebaEntidad.class);
            Root<PruebaEntidad> prueba = query.from(PruebaEntidad.class);
             query.select(prueba);

            query.where(criteria.equal(prueba.get("folio"), folio));

        return em.createQuery(query).getSingleResult();

        } catch (Exception e) {
            throw new PersistenciaException( "Error al buscar la prueba por folio: " + e.getMessage());

        } finally {
            em.close();
        }
    }
    
}
