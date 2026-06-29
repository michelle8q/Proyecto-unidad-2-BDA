/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import Excepciones.PersistenciaException;
import Interfaces.IConexionBD;
import Interfaces.IParametroDAO;
import itson.org.entidades.ParametroEntidad;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Implementación de la interfaz {@link IParametroDAO}. Maneja el acceso a datos
 * para los parámetros evaluados dentro de los análisis.
 *
 * @author luisf
 */
public class ParametroDAO implements IParametroDAO {

    /**
     * Instancia de la conexión a la base de datos.
     */
    private final IConexionBD conexionBD;

    /**
     * Constructor que recibe la instancia de conexión a la base de datos.
     *
     * @param conexionBD la conexión a la base de datos
     */
    public ParametroDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    /**
     * Busca un parámetro por su ID.
     *
     * @param id el ID del parámetro
     * @return el parámetro encontrado
     * @throws PersistenciaException si no se encuentra o hay error de
     * validación/conexión
     */
    @Override
    public ParametroEntidad buscarPorID(Long id) throws PersistenciaException {
        EntityManager entityManager = conexionBD.crearConexion();
        try {
            if (id == null || id <= 0) {
                throw new PersistenciaException("El ID del parámetro no es válido");
            }
            ParametroEntidad parametro = entityManager.find(ParametroEntidad.class, id);
            if (parametro == null) {
                throw new PersistenciaException("No se encontró el parámetro con ID: " + id);
            }
            return parametro;
        } catch (Exception ex) {
            throw new PersistenciaException("Error al buscar parámetro por ID: " + ex.getMessage());
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    /**
     * Obtiene todos los parámetros de un análisis específico, empleando
     * Criteria API y ordenando los resultados por el atributo "orden".
     *
     * @param idAnalisis el ID del análisis
     * @return lista de parámetros del análisis ordenados
     * @throws PersistenciaException si ocurre un error o el ID es inválido
     */
    @Override
    public List<ParametroEntidad> buscarPorAnalisis(int idAnalisis) throws PersistenciaException {
        EntityManager entityManager = conexionBD.crearConexion();
        try {
            if (idAnalisis <= 0) {
                throw new PersistenciaException("El ID del análisis no es válido");
            }
            CriteriaBuilder criteria = entityManager.getCriteriaBuilder();
            CriteriaQuery<ParametroEntidad> query = criteria.createQuery(ParametroEntidad.class);
            Root<ParametroEntidad> parametro = query.from(ParametroEntidad.class);
            query.select(parametro);
            query.where(criteria.equal(parametro.get("analisis").get("id"), idAnalisis));
            query.orderBy(criteria.asc(parametro.get("orden")));

            TypedQuery<ParametroEntidad> typedQuery = entityManager.createQuery(query);
            return typedQuery.getResultList();
        } catch (Exception ex) {
            throw new PersistenciaException("Error al buscar parámetros por análisis: " + ex.getMessage());
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    /**
     * Obtiene todos los parámetros disponibles registrados en la base de datos,
     * organizados de forma ascendente según su número de orden.
     *
     * @return lista de todos los parámetros
     * @throws PersistenciaException si ocurre un error durante la extracción de
     * datos
     */
    @Override
    public List<ParametroEntidad> buscarTodos() throws PersistenciaException {
        EntityManager entityManager = conexionBD.crearConexion();
        try {
            String jpql = "SELECT p FROM ParametroEntidad p ORDER BY p.orden ASC";
            TypedQuery<ParametroEntidad> query = entityManager.createQuery(jpql, ParametroEntidad.class);
            return query.getResultList();
        } catch (Exception ex) {
            throw new PersistenciaException("Error al obtener todos los parámetros: " + ex.getMessage());
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    /**
     * Recupera una lista de parámetros de un análisis utilizando una consulta
     * directa en JPQL. Los resultados vienen ordenados ascendentemente mediante
     * el campo de ordenamiento lógico de la entidad.
     *
     * @param idAnalisis Identificador del análisis padre.
     * @return Lista de entidades {@link ParametroEntidad}.
     * @throws PersistenceException Si ocurre un error durante el procesamiento
     * de la consulta JPA.
     */
    @Override
    public List<ParametroEntidad> buscarPorAnalisisId(int idAnalisis) throws PersistenceException {
        EntityManager em = conexionBD.crearConexion();
        try {
            String jpql = "SELECT p FROM ParametroEntidad p WHERE p.analisis.id = :idAnalisis ORDER BY p.orden ASC";

            TypedQuery<ParametroEntidad> query = em.createQuery(jpql, ParametroEntidad.class);
            query.setParameter("idAnalisis", idAnalisis);

            return query.getResultList();
        } catch (Exception e) {
            throw new PersistenceException("Error al buscar parámetros por análisis", e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}
