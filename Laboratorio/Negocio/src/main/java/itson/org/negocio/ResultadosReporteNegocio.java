/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.negocio;

import Excepciones.PersistenciaException;
import Interfaces.IConexionBD;
import Interfaces.IPruebaDAO;
import conexiones.ConexionBD;
import daos.PruebaDAO;
import dto.ResultadoReporteDTO;
import excepciones.NegocioException;
import interfaces.IResultadosReporteNegocio;
import itson.org.entidades.AnalisisEntidad;
import itson.org.entidades.DetallesPruebaEntidad;
import itson.org.entidades.ParametroEntidad;
import itson.org.entidades.PruebaEntidad;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase de negocio encargada de generar los datos necesarios para el reporte
 * de resultados de pruebas de laboratorio.
 *
 * Implementa la interfaz IResultadosReporteNegocio y se apoya en el DAO de
 * pruebas para obtener la información de la base de datos, transformándola
 * en DTOs listos para ser consumidos por el generador de reportes.
 *
 * @author cinca
 */
public class ResultadosReporteNegocio implements IResultadosReporteNegocio {
    
    private final IPruebaDAO pruebaDAO;
    
    /**
     * Constructor que inicializa la conexión y se la inyecta al DAO de pruebas.
     */
    public ResultadosReporteNegocio() {
        IConexionBD conexion = new ConexionBD();
        this.pruebaDAO = new PruebaDAO(conexion);  
    }
    
    /**
     * Genera una lista de DTOs con la información necesaria para imprimir el
     * reporte de resultados de una prueba de laboratorio.
     *
     * Por cada detalle de la prueba se construye un DTO con los datos del
     * paciente, doctor, análisis, parámetro y resultado correspondiente.
     * 
     * Se formatea la fecha y hora para que se muestre en formato en el reporte
     * generado por Jasper.
     *
     * @param idPrueba identificador de la prueba a reportar
     * @return lista de ResultadoReporteDTO con los datos del reporte
     * @throws NegocioException si el id no es válido, la prueba no existe, o no hay resultados registrados
     */
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
                
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                dto.setFechaHora(prueba.getFechaHora().format(formatter));

                dto.setMuestra(parametro.getAnalisis().getMuestra().getTipo());
                
                dto.setPaciente(prueba.getCliente().getNombre());
                dto.setApellidoPaterno(prueba.getCliente().getApellidoPaterno());
                dto.setApellidoMaterno(prueba.getCliente().getApellidoMaterno());
                dto.setSexoPaciente(prueba.getCliente().getSexo());
                
                dto.setDoctor(prueba.getDoctor().getNombre());
                dto.setApellidoPaternoDoctor(prueba.getDoctor().getApellidoPaterno());
                dto.setApellidoMaternoDoctor(prueba.getDoctor().getApellidoMaterno());
                
                dto.setUnidadMedida(parametro.getUnidadMedida());
                dto.setParametro(parametro.getNombre());
               
                dto.setAnalisis(analisis.getNombre());
                
                dto.setResultado(String.valueOf(detalle.getResultado()));    
                String fechaNacimiento = prueba.getCliente().getFechaNacimiento();
                
                LocalDate fechaNac = LocalDate.parse(fechaNacimiento);
                int edad = Period.between(fechaNac, LocalDate.now()).getYears();
                dto.setEdad(String.valueOf(edad) + " años");
                
                resultadosReporte.add(dto);
            }
            
            validarListaDeResultados(resultadosReporte);
            return resultadosReporte;

        } catch (PersistenciaException ex) {
                throw new NegocioException("Error al obtener la prueba: " + ex.getMessage());
        }  
    }
      
    /**
     * Valida que el id de la prueba sea un valor positivo.
     *
     * @param idPrueba identificador a validar
     * @throws NegocioException si el id es menor o igual a cero
     */
    private void validarDatos(int idPrueba) throws NegocioException {
        if(idPrueba <= 0) {
            throw new NegocioException("El id de la prueba no es valido.");
        }
    }
    
    /**
     * Valida que la prueba obtenida de la base de datos no sea nula.
     *
     * @param prueba entidad a validar
     * @throws NegocioException si la prueba es null
     */
    private void validarPrueba(PruebaEntidad prueba) throws NegocioException {
        if(prueba == null) {
            throw new NegocioException("La prueba no existe.");
        }
    }
    
    /**
     * Valida que la lista de resultados generada no esté vacía.
     *
     * @param lista lista de DTOs a validar
     * @throws NegocioException si la lista es null o está vacía
     */
    private void validarListaDeResultados(List<ResultadoReporteDTO> lista) throws NegocioException {
        if(lista == null || lista.isEmpty()) {
            throw new NegocioException("No hay resultados para crear el reporte.");
        }
    }
}
