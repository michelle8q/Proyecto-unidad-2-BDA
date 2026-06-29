
package interfaces;

import dto.AnalisisDTO;
import excepciones.NegocioException;
import itson.org.entidades.ParametroEntidad;
import java.util.List;
import javax.persistence.PersistenceException;

/**
 *
 * @author luisf
 */
public interface IAnalisisNegocio {

    List<AnalisisDTO> obtenerAnalisisParaTabla();

    void guardarAnalisis(dto.AnalisisDTO analisisDTO) throws Exception;

    void cambiarEstadoAnalisis(int idAnalisis) throws Exception;

    List<AnalisisDTO> buscarAnalisisPorParametro(String parametroBusqueda);

    List<AnalisisDTO> obtenerAnalisis();
    
    List<ParametroEntidad> obtenerParametrosPorAnalisisId(int idAnalisis) throws NegocioException;
}
