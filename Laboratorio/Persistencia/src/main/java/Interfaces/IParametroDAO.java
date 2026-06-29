/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaces;

import Excepciones.PersistenciaException;
import itson.org.entidades.ParametroEntidad;
import java.util.List;
import javax.persistence.PersistenceException;

/**
 * Interfaz que define las operaciones de persistencia para la entidad
 * {@link ParametroEntidad}. Proporciona métodos para acceder a los datos de los
 * parámetros que componen un análisis.
 *
 * @author luisf
 */
public interface IParametroDAO {

    /**
     * Busca un parámetro específico por su identificador primario (ID).
     *
     * @param id El identificador único del parámetro.
     * @return La entidad {@link ParametroEntidad} encontrada.
     * @throws PersistenciaException Si ocurre un error de conexión o el ID no
     * existe.
     */
    ParametroEntidad buscarPorID(Long id) throws PersistenciaException;

    /**
     * Recupera una lista de parámetros vinculados a un análisis, utilizando
     * Criteria API.
     *
     * @param idAnalisis El identificador numérico del análisis.
     * @return Una lista de entidades {@link ParametroEntidad}.
     * @throws PersistenciaException Si ocurre un error durante la consulta.
     */
    List<ParametroEntidad> buscarPorAnalisis(int idAnalisis) throws PersistenciaException;

    /**
     * Obtiene una lista con todos los parámetros registrados en el sistema.
     *
     * @return Una lista de entidades {@link ParametroEntidad}.
     * @throws PersistenciaException Si ocurre un error al realizar la consulta.
     */
    List<ParametroEntidad> buscarTodos() throws PersistenciaException;

    /**
     * Recupera una lista de parámetros vinculados a un análisis específico,
     * utilizando JPQL. Los resultados se devuelven ordenados por el atributo
     * "orden" de la entidad.
     *
     * @param idAnalisis El identificador numérico del análisis.
     * @return Una lista de entidades {@link ParametroEntidad} ordenadas de
     * forma ascendente.
     * @throws PersistenceException Si ocurre un error en la capa de
     * persistencia de JPA.
     */
    List<ParametroEntidad> buscarPorAnalisisId(int idAnalisis) throws PersistenceException;
}
