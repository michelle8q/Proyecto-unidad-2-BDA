
package daos;

import Interfaces.IConexionBD;
import Interfaces.IDoctorDAO;
import itson.org.entidades.DoctorEntidad;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

/**
 *
 * @author hp
 */
public class DoctorDAO implements IDoctorDAO{
    
    private final IConexionBD conexionBD;

    public DoctorDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    @Override
    public List<DoctorEntidad> buscarTodos() throws PersistenceException {
        EntityManager em = conexionBD.crearConexion();
        try {
            String jpql = "SELECT d FROM DoctorEntidad d";
            TypedQuery<DoctorEntidad> query = em.createQuery(jpql, DoctorEntidad.class);
            return query.getResultList();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
    
}
