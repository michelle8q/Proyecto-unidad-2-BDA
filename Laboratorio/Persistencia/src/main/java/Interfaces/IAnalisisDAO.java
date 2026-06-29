/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import Excepciones.PersistenciaException;
import itson.org.entidades.AnalisisEntidad;
import java.util.List;

/**
 * Interfaz que define las operaciones de persistencia para la entidad
 * {@link AnalisisEntidad}. Proporciona los métodos necesarios para el acceso y
 * manipulación de datos de los análisis en la base de datos.
 *
 * @author cinca
 */
public interface IAnalisisDAO {

    /**
     * Recupera todos los registros de análisis almacenados en la base de datos.
     *
     * @return Una lista de objetos {@link AnalisisEntidad}.
     */
    List<AnalisisEntidad> buscarTodos();

    /**
     * Persiste un nuevo registro de análisis en la base de datos.
     *
     * @param analisis La entidad {@link AnalisisEntidad} a guardar.
     */
    void agregar(AnalisisEntidad analisis);

    /**
     * Busca y recupera un análisis específico utilizando su identificador
     * único.
     *
     * @param id El identificador único del análisis en la base de datos.
     * @return La entidad {@link AnalisisEntidad} encontrada, o null si no
     * existe.
     */
    AnalisisEntidad buscarPorId(int id);

    /**
     * Actualiza la información de un análisis existente en la base de datos.
     *
     * @param analisis La entidad {@link AnalisisEntidad} con los datos
     * modificados.
     */
    void actualizar(AnalisisEntidad analisis);

    /**
     * Busca análisis en la base de datos que coincidan parcial o totalmente con
     * un texto específico. La búsqueda evalúa el nombre del análisis, su nota
     * descriptiva y el tipo de muestra asociada.
     *
     * @param parametro La cadena de texto a buscar.
     * @return Una lista de entidades {@link AnalisisEntidad} que coinciden con
     * el criterio de búsqueda.
     */
    List<AnalisisEntidad> buscarPorParametro(String parametro);

}
