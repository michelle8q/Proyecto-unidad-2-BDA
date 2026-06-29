
package Interfaces;

import itson.org.entidades.DoctorEntidad;
import java.util.List;
import javax.persistence.PersistenceException;

/**
 *
 * @author hp
 */
public interface IDoctorDAO {
    
    /**
     * Buscar todos los doctores en la base de datos
     * @return una lista con todos los doctores
     */
    public List<DoctorEntidad> buscarTodos() throws PersistenceException;
}
