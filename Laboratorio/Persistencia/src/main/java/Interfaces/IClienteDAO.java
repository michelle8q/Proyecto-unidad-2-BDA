
package Interfaces;

import itson.org.entidades.ClienteEntidad;
import java.util.List;
import javax.persistence.PersistenceException;

/**
 *
 * @author hp
 */
public interface IClienteDAO {
    
     /**
     * Buscar todos los clientes en la base de datos
     * @return una lista con todos los clientes
     */
     public List<ClienteEntidad> buscarTodos() throws PersistenceException;
}
