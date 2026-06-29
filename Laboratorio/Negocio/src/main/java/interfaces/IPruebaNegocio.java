/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dto.PruebaBusquedaDTO;
import dto.DetallesPruebaDTO;
import excepciones.NegocioException;
import itson.org.entidades.ClienteEntidad;
import itson.org.entidades.DoctorEntidad;
import itson.org.entidades.ParametroEntidad;
import itson.org.entidades.PruebaEntidad;
import java.util.List;

/**
 * Contrato para las operaciones de negocio relacionadas con pruebas de laboratorio.
 * 
 * @author cinca
 */
public interface IPruebaNegocio {
    
    /**
     * Guarda los resultados ingresados por el usuario para una prueba.
     *
     * @param detallesDTO lista de DTOs con los resultados a guardar
     * @throws NegocioException si la lista está vacia o hay error al guardar
     */
   void guardarResultados(List<DetallesPruebaDTO> detallesDTO) throws NegocioException;
   
    /**
     * Busca una prueba por folio y retorna sus datos para mostrar en pantalla.
     *
     * @param folio folio de la prueba
     * @return PruebaBusquedaDTO con los datos de la prueba y sus parámetros
     * @throws NegocioException si el folio es inválido o la prueba no existe
     */
   PruebaBusquedaDTO buscarPorFolio(String folio) throws NegocioException;
   
   /**
    * obtener todos los doctoes
    * @return lista de todos los doctores
    * @throws NegocioException en caso de alhun error
    */
    public List<DoctorEntidad> obtenerTodosDoctores() throws NegocioException;
    
    /**
    * obtener todos los clientes
    * @return lista de todos los clientes
    * @throws NegocioException en caso de alhun error
    */
    public List<ClienteEntidad> obtenerTodosClientes() throws NegocioException;
    
    /**
     * obtener de un analisis
     * @param idAnalisis analisis del que consultamos parametros
     * @return listra de parametros
     * @throws NegocioException en caso de algun error
     */
    public List<ParametroEntidad> obtenerParametrosPorAnalisisId(int idAnalisis) throws NegocioException;
    
    /**
     * guarda solicitud de analisis
     * @param solicitud a guardar
     * @throws NegocioException en caso de algun error
     */
    void guardarSolicitud(PruebaEntidad solicitud) throws NegocioException;
}
