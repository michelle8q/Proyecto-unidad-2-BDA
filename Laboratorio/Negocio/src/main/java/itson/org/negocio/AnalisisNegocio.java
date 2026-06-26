/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.negocio;

import Interfaces.IAnalisisDAO;
import Interfaces.IConexionBD;
import conexiones.ConexionBD;
import daos.AnalisisDAO;
import dto.AnalisisDTO;
import dto.MuestraDTO;
import interfaces.IAnalisisNegocio;
import itson.org.entidades.AnalisisEntidad;
import itson.org.entidades.MuestraEntidad;
import itson.org.entidades.ParametroEntidad;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luisf
 */
public class AnalisisNegocio implements IAnalisisNegocio {

    private final IAnalisisDAO analisisDAO;

    /**
     * Constructor que inicializa la conexión y se la inyecta al DAO.
     */
    public AnalisisNegocio() {
        IConexionBD conexion = new ConexionBD();
        this.analisisDAO = new AnalisisDAO(conexion);

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

                parametrosEntidad.add(paramEntidad);
            }
        }
        analisisEntidad.setParametros(parametrosEntidad);

        analisisDAO.agregar(analisisEntidad);
    }
}
