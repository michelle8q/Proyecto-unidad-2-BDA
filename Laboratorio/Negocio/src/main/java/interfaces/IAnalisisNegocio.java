package interfaces;

import dto.AnalisisDTO;
import excepciones.NegocioException;
import itson.org.entidades.ParametroEntidad;
import java.util.List;
import javax.persistence.PersistenceException;

/**
 * Interfaz que define las operaciones de lógica de negocio aplicables a los Análisis.
 * Sirve como puente entre la capa de presentación y la capa de acceso a datos (DAO).
 *
 * @author luisf
 */
public interface IAnalisisNegocio {

    /**
     * Obtiene una lista de todos los análisis registrados, formateados como
     * DTOs para ser mostrados en una tabla de la interfaz gráfica.
     *
     * * @return Una lista de objetos {@link AnalisisDTO}.
     */
    List<AnalisisDTO> obtenerAnalisisParaTabla();

    /**
     * Procesa y guarda un nuevo análisis en el sistema. Convierte el DTO y sus
     * listas anidadas (parámetros y rangos) en entidades antes de enviarlos a
     * la capa de persistencia.
     *
     * * @param analisisDTO El objeto de transferencia de datos con la
     * información del análisis a guardar.
     * @throws Exception Si ocurre un error de validación (ej. no tiene muestra)
     * o un error en la base de datos.
     */
    void guardarAnalisis(dto.AnalisisDTO analisisDTO) throws Exception;

    /**
     * Alterna el estado lógico de un análisis (de activo a inactivo y
     * viceversa).
     *
     * * @param idAnalisis El identificador único del análisis a modificar.
     * @throws Exception Si el análisis no existe o si ocurre un error en la
     * base de datos.
     */
    void cambiarEstadoAnalisis(int idAnalisis) throws Exception;

    /**
     * Busca análisis que coincidan con un criterio o parámetro de búsqueda
     * específico.
     *
     * * @param parametroBusqueda El texto o criterio a buscar.
     * @return Una lista de objetos {@link AnalisisDTO} que coinciden con la
     * búsqueda.
     */
    List<AnalisisDTO> buscarAnalisisPorParametro(String parametroBusqueda);

    /**
     * Obtiene una lista general de todos los análisis registrados en el
     * sistema.
     *
     * * @return Una lista de objetos {@link AnalisisDTO}.
     */
    List<AnalisisDTO> obtenerAnalisis();

    /**
     * Obtiene todos los parámetros asociados a un análisis específico mediante
     * su ID.
     *
     * * @param idAnalisis El identificador del análisis.
     * @return Una lista de entidades {@link ParametroEntidad} pertenecientes al
     * análisis.
     * @throws NegocioException Si ocurre un error al consultar la base de
     * datos.
     */
    List<ParametroEntidad> obtenerParametrosPorAnalisisId(int idAnalisis) throws NegocioException;
}
