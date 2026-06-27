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
 *
 * @author cinca
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
