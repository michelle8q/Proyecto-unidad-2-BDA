package daos;

import Interfaces.IConexionBD;
import itson.org.entidades.ClienteEntidad;
import itson.org.entidades.DoctorEntidad;
import itson.org.entidades.MuestraEntidad;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

/**
 *
 * @author hp
 */
public class InsertsDAO {
    
    private final IConexionBD conexionBD;

    public InsertsDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    public void cargarCatalogoSemilla(List<DoctorEntidad> docs, List<ClienteEntidad> clis, List<MuestraEntidad> mue) throws PersistenceException {
        EntityManager em = conexionBD.crearConexion();
        try {
            em.getTransaction().begin();

            for (DoctorEntidad d : docs) {
                em.persist(d);
            }
            for (ClienteEntidad c : clis) {
                em.persist(c);
            }
            for (MuestraEntidad m : mue) {
                em.persist(m);
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenceException("Error al insertar el catálogo semilla", e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}

