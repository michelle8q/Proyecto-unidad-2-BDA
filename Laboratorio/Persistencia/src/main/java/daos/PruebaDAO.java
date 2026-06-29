package daos;

import Excepciones.PersistenciaException;
import Interfaces.IConexionBD;
import Interfaces.IPruebaDAO;
import itson.org.entidades.PruebaEntidad;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Implementación de la interfaz IPruebaDAO para la gestión de solicitudes en la
 * base de datos utilizando JPA.
 *
 * Esta clase contiene la lógica necesaria para realizar operaciones de buscar
 * sobre la entidad Prueba, manejando transacciones y excepciones de
 * persistencia.
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

    /**
     * Constructor que recibe la conexión a la base de datos.
     *
     * @param conexionBD instancia de la conexión a utilizar
     */
    public PruebaDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    /**
     * Busca una prueba por su identificador unico.
     *
     * @param id identificador de la prueba a buscar
     * @return PruebaEntidad encontrada
     * @throws PersistenciaException si no se encuentra la prueba o si ocurre un
     * error durante la consulta
     *
     */
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

    /**
     * Obtiene todas las pruebas registradas en la base de datos.
     *
     * @return lista de PruebaEntidad con todas las pruebas existentes
     */
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

    /**
     * Busca una prueba por su numero de folio utilizando CriteriaBuilder.
     *
     * Se uso CriteriaBuilder para que el compilador detecte errores en los
     * nombres de los campos en tiempo de compilación.
     *
     * @param folio folio de la prueba a buscar
     * @return PruebaEntidad encontrada
     * @throws PersistenciaException si no se encuentra la prueba o si ocurre un
     * error durante la consulta
     *
     */
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
            throw new PersistenciaException("Error al buscar la prueba por folio: " + e.getMessage());

        } finally {
            em.close();
        }
    }

    @Override
    public PruebaEntidad registrar(PruebaEntidad prueba) throws PersistenciaException {
        EntityManager em = conexionBD.crearConexion();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            em.persist(prueba);

            tx.commit();
            
            return prueba;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            throw e;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }

        }

    }
}
