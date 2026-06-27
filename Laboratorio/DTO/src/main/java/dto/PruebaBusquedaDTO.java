/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.util.List;

/**
 *
 * @author cinca
 */
public class PruebaBusquedaDTO {
    private int idPrueba;
    private String folio;
    private String nombreCliente;
    private String nombreDoctor;
    private List<DetallesPruebaDTO> detalles;

    public PruebaBusquedaDTO() {
    }

    public PruebaBusquedaDTO(int idPrueba, String folio, String nombreCliente, String nombreDoctor, List<DetallesPruebaDTO> detalles) {
        this.idPrueba = idPrueba;
        this.folio = folio;
        this.nombreCliente = nombreCliente;
        this.nombreDoctor = nombreDoctor;
        this.detalles = detalles;
    }

    public int getIdPrueba() {
        return idPrueba;
    }

    public void setIdPrueba(int idPrueba) {
        this.idPrueba = idPrueba;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getNombreDoctor() {
        return nombreDoctor;
    }

    public void setNombreDoctor(String nombreDoctor) {
        this.nombreDoctor = nombreDoctor;
    }

    public List<DetallesPruebaDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallesPruebaDTO> detalles) {
        this.detalles = detalles;
    }
    
    
}
