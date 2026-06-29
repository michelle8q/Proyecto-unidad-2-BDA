
package Interfaces;

import Excepciones.PersistenciaException;
import itson.org.entidades.DetallesPruebaEntidad;

/**
 *
 * @author cinca
 */
public interface IDetallesPruebaDAO {
    
    /**
     * guardar detalles de la prueba
     * @param detalles DetallesPruebaEntidad detalles de a asignar a la prueba
     * @return DetallesPruebaEntidad que se registro
     * @throws PersistenciaException en caso de que no se puedar guardar
     */
    DetallesPruebaEntidad guardar(DetallesPruebaEntidad detalles) throws PersistenciaException;
    
    /**
     * buscar detalles de un apruea por su id
     * @param id id de los detalles a buscar
     * @return DetallesPruebaEntidad que se encontro
     * @throws PersistenciaException en caso de que haya algun error
     */
    DetallesPruebaEntidad buscarPorID(int id) throws PersistenciaException;
    
    
}
