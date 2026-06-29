
package daos;

import Interfaces.IClienteDAO;
import Interfaces.IConexionBD;
import itson.org.entidades.ClienteEntidad;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

/**
 *
 * @author hp
 */
public class ClienteDAO implements IClienteDAO{

    private final IConexionBD conexionBD;

    public ClienteDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    @Override
    public List<ClienteEntidad> buscarTodos() throws PersistenceException {
        EntityManager em = conexionBD.crearConexion();
        try {
            String jpql = "SELECT d FROM DoctorEntidad d";
            TypedQuery<ClienteEntidad> query = em.createQuery(jpql, ClienteEntidad.class);
            return query.getResultList();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
    
}
