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
 *
 * @author luisf
 */
public class AnalisisNegocio implements IAnalisisNegocio {

    private final IAnalisisDAO analisisDAO;
    private final IParametroDAO parametroDAO;

    /**
     * Constructor que inicializa la conexión y se la inyecta al DAO.
     */
    public AnalisisNegocio() {
        IConexionBD conexion = new ConexionBD();
        this.analisisDAO = new AnalisisDAO(conexion);
        this.parametroDAO = new ParametroDAO(conexion);
    }

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

    @Override
    public List<AnalisisDTO> buscarAnalisisPorParametro(String parametroBusqueda) {
        if (parametroBusqueda == null || parametroBusqueda.trim().isEmpty()) {
            return obtenerAnalisisParaTabla();
        }

        List<AnalisisEntidad> entidades = analisisDAO.buscarPorParametro(parametroBusqueda);
        return convertirEntidadesToDTOs(entidades);
    }

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

    @Override
    public List<AnalisisDTO> obtenerAnalisis() {
        List<AnalisisEntidad> analisis = analisisDAO.buscarTodos();
        return convertirEntidadesToDTOs(analisis);
    }

    @Override
    public List<ParametroEntidad> obtenerParametrosPorAnalisisId(int idAnalisis) throws NegocioException {
        return parametroDAO.buscarPorAnalisisId(idAnalisis);
    }

}
