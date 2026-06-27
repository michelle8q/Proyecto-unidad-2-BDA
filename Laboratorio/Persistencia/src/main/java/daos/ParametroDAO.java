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
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
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
     * @throws PersistenciaException si no se encuentra o hay error
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
     * Obtiene todos los parámetros de un análisis específico, ordenados por
     * orden.
     *
     * @param idAnalisis el ID del análisis
     * @return lista de parámetros del análisis ordenados
     * @throws PersistenciaException si ocurre un error
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
     * Obtiene todos los parámetros disponibles.
     *
     * @return lista de todos los parámetros
     * @throws PersistenciaException si ocurre un error
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
}
