/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.negocio;

import Interfaces.IConexionBD;
import Interfaces.IPruebaDAO;
import conexiones.ConexionBD;
import daos.PruebaDAO;
import dto.AnalisisDTO;
import dto.PruebaDTO;
import excepciones.NegocioException;
import interfaces.IPruebaNegocio;
import itson.org.entidades.PruebaEntidad;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cinca
 */
public class PruebaNegocio implements IPruebaNegocio {
    private final IPruebaDAO pruebaDAO;

    /**
     * Constructor que inicializa la conexión y se la inyecta al DAO.
     */
    public PruebaNegocio() {
        IConexionBD conexion = new ConexionBD();
        this.pruebaDAO = new PruebaDAO(conexion);

    }
    
    @Override
    public List<PruebaDTO> obtenerPruebaParaTabla() throws NegocioException {
        //List<PruebaEntidad> entidades = pruebaDAO.buscarTodos();
        

       // List<PruebaDTO> listaDTOs = new ArrayList<>();

       // for (PruebaEntidad entidad : entidades) {
        //    PruebaDTO dto = new PruebaDTO();
         //   AnalisisDTO analisis = new AnalisisDTO();

         //   dto.setAnalisis(entidad.getDetalles().);
         //   dto.setNombre(entidad.getNombre());
          //  dto.setNotaDescriptiva(entidad.getNotaDescriptiva());
//
          //  if (entidad.getMuestra() != null) {
           //     MuestraDTO muestraDTO = new MuestraDTO();
           //     muestraDTO.setId(entidad.getMuestra().getId());
           //     muestraDTO.setTipo(entidad.getMuestra().getTipo());
                
           //     dto.setMuestra(muestraDTO);
           // } else {
           //     MuestraDTO muestraVacia = new MuestraDTO(0, "Sin muestra");
            //    dto.setMuestra(muestraVacia);
            //}

          //  listaDTOs.add(dto);
        //
//
       // return listaDTOs;
        return null;
        //List<PruebaEntidad> entidades = pruebaDAO.buscarTodos();
        

       // List<PruebaDTO> listaDTOs = new ArrayList<>();

       // for (PruebaEntidad entidad : entidades) {
        //    PruebaDTO dto = new PruebaDTO();
         //   AnalisisDTO analisis = new AnalisisDTO();

         //   dto.setAnalisis(entidad.getDetalles().);
         //   dto.setNombre(entidad.getNombre());
          //  dto.setNotaDescriptiva(entidad.getNotaDescriptiva());
//
          //  if (entidad.getMuestra() != null) {
           //     MuestraDTO muestraDTO = new MuestraDTO();
           //     muestraDTO.setId(entidad.getMuestra().getId());
           //     muestraDTO.setTipo(entidad.getMuestra().getTipo());
                
           //     dto.setMuestra(muestraDTO);
           // } else {
           //     MuestraDTO muestraVacia = new MuestraDTO(0, "Sin muestra");
            //    dto.setMuestra(muestraVacia);
            //}

          //  listaDTOs.add(dto);
        //
//
       // return listaDTOs;
    }
}
