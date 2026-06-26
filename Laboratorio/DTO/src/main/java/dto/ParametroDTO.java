/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luisf
 */
public class ParametroDTO {

    private String nombre;
    private String nota;
    private String unidadMedida;
    private int orden;
    private String tipoValor;

    private List<RangoDTO> rangos;

    public ParametroDTO() {
    }

    public ParametroDTO(String nombre, String nota, String unidadMedida, int orden, String tipoValor) {
        this.nombre = nombre;
        this.nota = nota;
        this.unidadMedida = unidadMedida;
        this.orden = orden;
        this.tipoValor = tipoValor;
        this.rangos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public String getTipoValor() {
        return tipoValor;
    }

    public void setTipoValor(String tipoValor) {
        this.tipoValor = tipoValor;
    }

    
    public List<RangoDTO> getRangos() {
        return rangos;
    }

    public void setRangos(List<RangoDTO> rangos) {
        this.rangos = rangos;
    }
}
