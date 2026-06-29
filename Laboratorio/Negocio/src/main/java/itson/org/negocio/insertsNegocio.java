package itson.org.negocio;

import Interfaces.IConexionBD;
import conexiones.ConexionBD;
import daos.InsertsDAO;
import excepciones.NegocioException;
import itson.org.entidades.ClienteEntidad;
import itson.org.entidades.DoctorEntidad;
import itson.org.entidades.MuestraEntidad;
import java.util.List;
import javax.persistence.PersistenceException;

/**
 *
 * @author hp
 */
public class insertsNegocio {
      private InsertsDAO insertsDAO;

    /**
     * Constructor que inicializa la conexión y se la inyecta al DAO.
     */
    public insertsNegocio() {
        IConexionBD conexion = new ConexionBD();
        this.insertsDAO = new InsertsDAO(conexion);
    }

    public void cargarCatalogoSemilla(List<DoctorEntidad> docs, List<ClienteEntidad> clis, List<MuestraEntidad> mue) throws NegocioException {
        try {
            insertsDAO.cargarCatalogoSemilla(docs, clis, mue);
        } catch (PersistenceException e) {
            throw new NegocioException("Error en persistencia al cargar semilla: " + e.getMessage());
        }
    }
}
