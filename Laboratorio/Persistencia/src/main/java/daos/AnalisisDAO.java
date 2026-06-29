/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import Excepciones.PersistenciaException;
import Interfaces.IAnalisisDAO;
import Interfaces.IConexionBD;
import itson.org.entidades.AnalisisEntidad;
import itson.org.entidades.MuestraEntidad;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 * Implementación de la interfaz {@link IAnalisisDAO}. Gestiona las operaciones
 * de persistencia para la entidad Análisis utilizando JPA (Java Persistence
 * API).
 *
 * @author cinca luisf
 */
public class AnalisisDAO implements IAnalisisDAO {

    private final IConexionBD conexionBD;

    /**
     * Constructor que inicializa el DAO con una conexión a la base de datos.
     *
     * @param conexionBD Interfaz de conexión para generar el EntityManager.
     */
    public AnalisisDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    /**
     * Ejecuta una consulta JPQL para obtener todos los análisis registrados.
     *
     * @return Una lista de entidades {@link AnalisisEntidad}.
     */
    @Override
    public List<AnalisisEntidad> buscarTodos() {
        EntityManager entityManager = conexionBD.crearConexion();

        try {
            String jpql = "SELECT a FROM AnalisisEntidad a";
            TypedQuery<AnalisisEntidad> query = entityManager.createQuery(jpql, AnalisisEntidad.class);
            return query.getResultList();
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    /**
     * Inserta un nuevo análisis en la base de datos. Si el análisis incluye una
     * muestra previamente existente, asegura de vincularla correctamente.
     *
     * @param analisis La entidad {@link AnalisisEntidad} a persistir.
     */
    @Override
    public void agregar(AnalisisEntidad analisis) {
        EntityManager em = conexionBD.crearConexion();
        try {
            em.getTransaction().begin();

            // Verifica si la muestra ya tiene un ID para adjuntarla al contexto de persistencia actual
            if (analisis.getMuestra() != null && analisis.getMuestra().getId() > 0) {
                MuestraEntidad muestraOficial = em.find(MuestraEntidad.class, analisis.getMuestra().getId());
                analisis.setMuestra(muestraOficial);
            }

            em.persist(analisis);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    /**
     * Busca un análisis por su identificador primario.
     *
     * @param id Identificador numérico del análisis.
     * @return La entidad correspondiente, o null si no se encuentra.
     */
    @Override
    public AnalisisEntidad buscarPorId(int id) {
        EntityManager em = conexionBD.crearConexion();
        try {
            return em.find(AnalisisEntidad.class, id);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    /**
     * Sincroniza los cambios realizados en una entidad Análisis con la base de
     * datos.
     *
     * @param analisis La entidad {@link AnalisisEntidad} modificada.
     */
    @Override
    public void actualizar(AnalisisEntidad analisis) {
        EntityManager em = conexionBD.crearConexion();
        try {
            em.getTransaction().begin();
            em.merge(analisis);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    /**
     * Busca análisis mediante un criterio de texto ingresado, realizando una
     * búsqueda de coincidencias parciales (LIKE) en el nombre, nota descriptiva
     * y tipo de muestra.
     *
     * @param parametro Cadena de texto a buscar.
     * @return Una lista de {@link AnalisisEntidad} filtrada.
     */
    @Override
    public List<AnalisisEntidad> buscarPorParametro(String parametro) {
        EntityManager em = conexionBD.crearConexion();
        try {
            String jpql = "SELECT DISTINCT a FROM AnalisisEntidad a "
                    + "LEFT JOIN a.muestra m "
                    + "WHERE LOWER(a.nombre) LIKE LOWER(:param) "
                    + "OR LOWER(a.notaDescriptiva) LIKE LOWER(:param) "
                    + "OR LOWER(m.tipo) LIKE LOWER(:param)";

            TypedQuery<AnalisisEntidad> query = em.createQuery(jpql, AnalisisEntidad.class);
            query.setParameter("param", "%" + parametro + "%");

            return query.getResultList();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}
