/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.negocio;

import Excepciones.PersistenciaException;
import Interfaces.IPruebaDAO;
import dto.ResultadoReporteDTO;
import excepciones.NegocioException;
import interfaces.IResultadosReporteNegocio;
import itson.org.entidades.AnalisisEntidad;
import itson.org.entidades.DetallesPruebaEntidad;
import itson.org.entidades.ParametroEntidad;
import itson.org.entidades.PruebaEntidad;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cinca
 */
public class ResultadosReporteNegocio implements IResultadosReporteNegocio {
    
    private final IPruebaDAO pruebaDAO;

    public ResultadosReporteNegocio(IPruebaDAO pruebaDAO) {
      
        this.pruebaDAO = pruebaDAO;  
    }
    
    
    @Override
    public List<ResultadoReporteDTO> generarReporteDTO(int idPrueba) throws NegocioException {
        
        validarDatos(idPrueba);

        try {
            PruebaEntidad prueba = pruebaDAO.buscarPorID(idPrueba);
            validarPrueba(prueba);
            
            List<DetallesPruebaEntidad> detalles = prueba.getDetalles();

            List<ResultadoReporteDTO> resultadosReporte = new ArrayList<>();
            
            for (DetallesPruebaEntidad detalle : prueba.getDetalles()) {
                ResultadoReporteDTO dto = new ResultadoReporteDTO();
                
                ParametroEntidad parametro = detalle.getParametro();
                AnalisisEntidad analisis = parametro.getAnalisis();
                
                dto.setFolio(prueba.getFolio());
                dto.setFechaHora(prueba.getFechaHora().toString());
                
                dto.setPaciente(prueba.getCliente().getNombre());
                dto.setSexoPaciente(prueba.getCliente().getSexo());
                
                dto.setDoctor(prueba.getDoctor().getNombre());
                
                
                dto.setUnidadMedida(parametro.getUnidadMedida());
                dto.setOrden(parametro.getOrden());
                dto.setParametro(parametro.getNombre());
               
                dto.setAnalisis(analisis.getNombre());
                
                dto.setResultado(String.valueOf(detalle.getResultado()));    
                
                resultadosReporte.add(dto);
            }
            
            validarListaDeResultados(resultadosReporte);
            return resultadosReporte;

        } catch (PersistenciaException ex) {
                throw new NegocioException("Error al obtener la prueba: " + ex.getMessage());
        }  
    }
      
    
    private void validarDatos(int idPrueba) throws NegocioException {
        if(idPrueba <= 0) {
            throw new NegocioException("El id de la prueba no es valido.");
        }
    }
    
    private void validarPrueba(PruebaEntidad prueba) throws NegocioException {
        if(prueba == null) {
            throw new NegocioException("La prueba no existe.");
        }
    }
    
    private void validarListaDeResultados(List<ResultadoReporteDTO> lista) throws NegocioException {
        if(lista == null || lista.isEmpty()) {
            throw new NegocioException("No hay resultados para crear el reporte.");
        }
    }
}
