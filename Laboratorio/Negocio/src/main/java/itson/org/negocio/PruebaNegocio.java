/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.negocio;

import Excepciones.PersistenciaException;
import Interfaces.IConexionBD;
import Interfaces.IDetallesPruebaDAO;
import Interfaces.IPruebaDAO;
import Interfaces.IRangoDAO;
import conexiones.ConexionBD;
import daos.DetallesPruebaDAO;
import daos.PruebaDAO;
import daos.RangoDAO;
import dto.PruebaBusquedaDTO;
import dto.DetallesPruebaDTO;
import excepciones.NegocioException;
import interfaces.IPruebaNegocio;
import itson.org.entidades.AnalisisEntidad;
import itson.org.entidades.DetallesPruebaEntidad;
import itson.org.entidades.ParametroEntidad;
import itson.org.entidades.PruebaEntidad;
import itson.org.entidades.RangoEntidad;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase de negocio encargada de gestionar las pruebas de laboratorio.
 *
 * Implementa la interfaz IPruebaNegocio y actúa entre la presentación y la persistencia. 
 * 
 * Se encarga de buscar una prueba por folio para mostrarla en pantalla, y guardar los resultados
 * ingresados por el usuario.
 *
 * Transforma entidades en DTOs para que la presentación no tenga dependencia
 * con JPA, y transforma DTOs en entidades al momento de persistir.
 *
 * @author cinca
 * 
 */
public class PruebaNegocio implements IPruebaNegocio {
    private final IPruebaDAO pruebaDAO;
    private final IDetallesPruebaDAO detallesPruebaDAO;
    private final IRangoDAO rangoDAO;
    
    /**
     * Constructor que inicializa la conexión y se la inyecta al DAO.
     */
    public PruebaNegocio() {
        IConexionBD conexion = new ConexionBD();
        this.pruebaDAO = new PruebaDAO(conexion);
        this.detallesPruebaDAO = new DetallesPruebaDAO(conexion);
        this.rangoDAO = new RangoDAO(conexion);
    }
    
    /**
     * Guarda los resultados ingresados por el usuario para cada detalle de
     * una prueba.
     *
     * Por cada DTO recibido, busca el detalle correspondiente en la base de
     * datos por su ID y actualiza su resultado y observaciones.
     *
     * @param detallesDTO lista de DTOs con los resultados y observaciones 
     * ingresados por el usuario
     * 
     * @throws NegocioException si la lista está vacía o si ocurre un error
     * durante la persistencia
     */
    @Override
    public void guardarResultados(List<DetallesPruebaDTO> detallesDTO) throws NegocioException {
        if (detallesDTO == null || detallesDTO.isEmpty()) {
            throw new NegocioException("No hay resultados para guardar.");
        }

        try {
            for (DetallesPruebaDTO dto : detallesDTO) {

                DetallesPruebaEntidad detalle = detallesPruebaDAO.buscarPorID(dto.getIdDetalle());
                detalle.setResultado(dto.getResultado());
                detalle.setObservaciones(dto.getObservaciones());
                detallesPruebaDAO.guardar(detalle);
            }
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al guardar los resultados: " + ex.getMessage());
        }
    }
    
    /**
     * Busca una prueba por su folio y retorna sus datos junto con los detalles
     * de cada parámetro, incluyendo el rango de referencia según el sexo del
     * paciente.
     *
     * Transforma la PruebaEntidad y sus DetallesPruebaEntidad en un
     * PruebaBusquedaDTO listo para mostrar en la pantalla de ingreso de
     * resultados.
     *
     * @param folio folio de la prueba a buscar
     * @return PruebaBusquedaDTO con los datos de la prueba y sus parámetros
     * @throws NegocioException si el folio está vacío, la prueba no existe,
     * no tiene parámetros registrados, o si ocurre un error de persistencia
     * 
     */
    @Override
    public PruebaBusquedaDTO buscarPorFolio(String folio) throws NegocioException {
        if (folio == null || folio.isBlank()) {
            throw new NegocioException("El folio no puede estar vacio.");
        }

        try {
            PruebaEntidad prueba = pruebaDAO.buscarPorFolio(folio);

            List<DetallesPruebaEntidad> detalles = prueba.getDetalles();
            if (detalles == null || detalles.isEmpty()) {
                throw new NegocioException("La prueba no tiene parámetros registrados.");
            }

            String sexoPaciente = prueba.getCliente().getSexo();

            List<DetallesPruebaDTO> detallesDTO = new ArrayList<>();
            for (DetallesPruebaEntidad detalle : detalles) {
                ParametroEntidad parametro = detalle.getParametro();
                AnalisisEntidad analisis = parametro.getAnalisis();

                DetallesPruebaDTO dto = new DetallesPruebaDTO();
                dto.setIdDetalle(detalle.getId());
                dto.setAnalisis(analisis.getNombre());
                dto.setParametro(parametro.getNombre());
                dto.setUnidadMedida(parametro.getUnidadMedida());

                RangoEntidad rango = rangoDAO.buscarPorParametroYSexo(parametro.getId(), sexoPaciente);
                if (rango != null) {
                    dto.setRangoReferencia(rango.getEdadInicial() + " -- " + rango.getEdadFinal());
                } else {
                    dto.setRangoReferencia("Sin rango definido");
                }

                dto.setResultado(detalle.getResultado());
                dto.setObservaciones(detalle.getObservaciones());
                detallesDTO.add(dto);
            }

            PruebaBusquedaDTO pruebaDTO = new PruebaBusquedaDTO();
            pruebaDTO.setIdPrueba(prueba.getId());
            pruebaDTO.setFolio(prueba.getFolio());
            pruebaDTO.setNombreCliente(prueba.getCliente().getNombre());
            pruebaDTO.setNombreDoctor(prueba.getDoctor().getNombre());
            pruebaDTO.setDetalles(detallesDTO);

            return pruebaDTO;

        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al buscar la prueba: " + ex.getMessage());
        }
    }
}
