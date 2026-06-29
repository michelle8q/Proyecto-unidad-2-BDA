/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.negocio;

import Interfaces.IAnalisisDAO;
import Interfaces.IConexionBD;
import Interfaces.IParametroDAO;
import conexiones.ConexionBD;
import daos.AnalisisDAO;
import daos.ParametroDAO;
import dto.AnalisisDTO;
import dto.MuestraDTO;
import excepciones.NegocioException;
import interfaces.IAnalisisNegocio;
import itson.org.entidades.AnalisisEntidad;
import itson.org.entidades.MuestraEntidad;
import itson.org.entidades.ParametroEntidad;
import itson.org.entidades.RangoEntidad;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

/**
 * Clase que implementa la lógica de negocio para la gestión de Análisis
 * Clínicos. Se encarga de coordinar la conversión de datos (DTOs a Entidades y
 * viceversa) y comunicarse con la capa DAO para la persistencia de datos.
 *
 * @author luisf
 */
public class AnalisisNegocio implements IAnalisisNegocio {

    private final IAnalisisDAO analisisDAO;
    private final IParametroDAO parametroDAO;

    /**
     * Constructor por defecto que inicializa la conexión a la base de datos y
     * se la inyecta a los DAOs correspondientes.
     */
    public AnalisisNegocio() {
        IConexionBD conexion = new ConexionBD();
        this.analisisDAO = new AnalisisDAO(conexion);
        this.parametroDAO = new ParametroDAO(conexion);
    }

    /**
     * Recupera todos los análisis de la base de datos y los convierte en DTOs
     * preparados para ser mostrados en tablas de la interfaz de usuario.
     *
     * @return Lista de {@link AnalisisDTO} con la información principal.
     */
    @Override
    public List<AnalisisDTO> obtenerAnalisisParaTabla() {
        List<AnalisisEntidad> entidades = analisisDAO.buscarTodos();

        List<AnalisisDTO> listaDTOs = new ArrayList<>();

        for (AnalisisEntidad entidad : entidades) {
            AnalisisDTO dto = new AnalisisDTO();

            dto.setId(entidad.getId());
            dto.setNombre(entidad.getNombre());
            dto.setNotaDescriptiva(entidad.getNotaDescriptiva());
            dto.setActivo(entidad.isActivo());

            if (entidad.getMuestra() != null) {
                MuestraDTO muestraDTO = new MuestraDTO();
                muestraDTO.setId(entidad.getMuestra().getId());
                muestraDTO.setTipo(entidad.getMuestra().getTipo());

                dto.setMuestra(muestraDTO);
            } else {
                MuestraDTO muestraVacia = new MuestraDTO(0, "Sin muestra");
                dto.setMuestra(muestraVacia);
            }

            listaDTOs.add(dto);
        }

        return listaDTOs;
    }

    /**
     * Valida, transforma y guarda un nuevo análisis junto a su cascada de
     * dependencias (Muestra, Parámetros y Rangos).
     *
     * @param analisisDTO El DTO que contiene toda la estructura del análisis a
     * guardar.
     * @throws Exception Si el DTO no contiene una muestra asignada o si falla
     * la base de datos.
     */
    @Override
    public void guardarAnalisis(AnalisisDTO analisisDTO) throws Exception {

        AnalisisEntidad analisisEntidad = new AnalisisEntidad();
        analisisEntidad.setNombre(analisisDTO.getNombre());
        analisisEntidad.setNotaDescriptiva(analisisDTO.getNotaDescriptiva());
        analisisEntidad.setActivo(true);

        if (analisisDTO.getMuestra() != null) {
            MuestraEntidad muestraEntidad = new MuestraEntidad();
            muestraEntidad.setId(analisisDTO.getMuestra().getId());
            muestraEntidad.setTipo(analisisDTO.getMuestra().getTipo());

            analisisEntidad.setMuestra(muestraEntidad);
        } else {
            throw new Exception("El análisis debe tener una muestra asignada obligatoriamente.");
        }

        List<ParametroEntidad> parametrosEntidad = new java.util.ArrayList<>();
        if (analisisDTO.getParametros() != null) {
            for (dto.ParametroDTO paramDTO : analisisDTO.getParametros()) {
                ParametroEntidad paramEntidad = new ParametroEntidad();
                paramEntidad.setNombre(paramDTO.getNombre());
                paramEntidad.setNota(paramDTO.getNota());
                paramEntidad.setUnidadMedida(paramDTO.getUnidadMedida());
                paramEntidad.setOrden(paramDTO.getOrden());

                paramEntidad.setAnalisis(analisisEntidad);

                List<RangoEntidad> rangosEntidad = new java.util.ArrayList<>();
                if (paramDTO.getRangos() != null) {
                    for (dto.RangoDTO rangoDTO : paramDTO.getRangos()) {
                        RangoEntidad rangoEntidad = new RangoEntidad();
                        rangoEntidad.setEdadInicial(rangoDTO.getEdadInicial());
                        rangoEntidad.setEdadFinal(rangoDTO.getEdadFinal());
                        rangoEntidad.setRangoInicial(rangoDTO.getRangoInicial());
                        rangoEntidad.setRangoFinal(rangoDTO.getRangoFinal());
                        rangoEntidad.setSexo(rangoDTO.getSexo());

                        rangoEntidad.setParametro(paramEntidad);

                        rangosEntidad.add(rangoEntidad);
                    }
                }
                paramEntidad.setRangos(rangosEntidad);

                parametrosEntidad.add(paramEntidad);
            }
        }
        analisisEntidad.setParametros(parametrosEntidad);

        analisisDAO.agregar(analisisEntidad);
    }

    /**
     * Busca un análisis por su identificador e invierte su estado actual
     * (activo/inactivo).
     *
     * @param idAnalisis Identificador numérico del análisis en la base de
     * datos.
     * @throws Exception Si el análisis no es encontrado o si la actualización
     * falla.
     */
    @Override
    public void cambiarEstadoAnalisis(int idAnalisis) throws Exception {
        try {
            AnalisisEntidad entidad = analisisDAO.buscarPorId(idAnalisis);

            if (entidad == null) {
                throw new Exception("El análisis con ID " + idAnalisis + " no existe.");
            }

            entidad.setActivo(!entidad.isActivo());

            analisisDAO.actualizar(entidad);

        } catch (Exception ex) {
            throw new Exception("Error al cambiar el estado del análisis: " + ex.getMessage());
        }
    }

    /**
     * Recupera una lista de análisis que coincidan parcial o totalmente con el
     * texto de búsqueda proporcionado. Si la búsqueda está vacía, devuelve
     * todos los análisis.
     *
     * @param parametroBusqueda Cadena de texto utilizada como filtro.
     * @return Lista de {@link AnalisisDTO} que cumplen con el criterio.
     */
    @Override
    public List<AnalisisDTO> buscarAnalisisPorParametro(String parametroBusqueda) {
        if (parametroBusqueda == null || parametroBusqueda.trim().isEmpty()) {
            return obtenerAnalisisParaTabla();
        }

        List<AnalisisEntidad> entidades = analisisDAO.buscarPorParametro(parametroBusqueda);
        return convertirEntidadesToDTOs(entidades);
    }

    /**
     * Método auxiliar privado para convertir una lista de entidades en una
     * lista de DTOs. Se encarga de mapear los datos básicos y la muestra (si
     * existe).
     *
     * @param entidades Lista de objetos {@link AnalisisEntidad} obtenidos de la
     * BD.
     * @return Lista equivalente de objetos {@link AnalisisDTO}.
     */
    private List<AnalisisDTO> convertirEntidadesToDTOs(List<AnalisisEntidad> entidades) {
        List<AnalisisDTO> listaDTOs = new ArrayList<>();

        for (AnalisisEntidad entidad : entidades) {
            AnalisisDTO dto = new AnalisisDTO();

            dto.setId(entidad.getId());
            dto.setNombre(entidad.getNombre());
            dto.setNotaDescriptiva(entidad.getNotaDescriptiva());
            dto.setActivo(entidad.isActivo());

            if (entidad.getMuestra() != null) {
                MuestraDTO muestraDTO = new MuestraDTO();
                muestraDTO.setId(entidad.getMuestra().getId());
                muestraDTO.setTipo(entidad.getMuestra().getTipo());

                dto.setMuestra(muestraDTO);
            } else {
                MuestraDTO muestraVacia = new MuestraDTO(0, "Sin muestra");
                dto.setMuestra(muestraVacia);
            }

            listaDTOs.add(dto);
        }

        return listaDTOs;
    }

    /**
     * Obtiene una lista completa de análisis y los devuelve mapeados como DTOs.
     *
     * @return Lista general de todos los objetos {@link AnalisisDTO}.
     */
    @Override
    public List<AnalisisDTO> obtenerAnalisis() {
        List<AnalisisEntidad> analisis = analisisDAO.buscarTodos();
        return convertirEntidadesToDTOs(analisis);
    }

    /**
     * Consulta y devuelve directamente los parámetros (entidades) asociados a
     * un identificador de análisis específico.
     *
     * @param idAnalisis Identificador del análisis del que se quieren obtener
     * los parámetros.
     * @return Lista de objetos {@link ParametroEntidad}.
     * @throws NegocioException Si ocurre un error en la capa DAO durante la
     * consulta.
     */
    @Override
    public List<ParametroEntidad> obtenerParametrosPorAnalisisId(int idAnalisis) throws NegocioException {
        return parametroDAO.buscarPorAnalisisId(idAnalisis);
    }

}
