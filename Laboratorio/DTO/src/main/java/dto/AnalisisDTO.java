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
public class AnalisisDTO {

    private int id;
    private String nombre;
    private String notaDescriptiva;
    private MuestraDTO muestra;
    private List<ParametroDTO> parametros;

    public AnalisisDTO() {
        this.parametros = new ArrayList<>();
    }

    public AnalisisDTO(int id, String nombre, String notaDescriptiva, MuestraDTO muestra) {
        this.id = id;
        this.nombre = nombre;
        this.notaDescriptiva = notaDescriptiva;
        this.muestra = muestra;
        this.parametros = new ArrayList<>();

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNotaDescriptiva() {
        return notaDescriptiva;
    }

    public void setNotaDescriptiva(String notaDescriptiva) {
        this.notaDescriptiva = notaDescriptiva;
    }

    public MuestraDTO getMuestra() {
        return muestra;
    }

    public void setMuestra(MuestraDTO muestra) {
        this.muestra = muestra;
    }

    public List<ParametroDTO> getParametros() {
        return parametros;
    }

    public void setParametros(List<ParametroDTO> parametros) {
        this.parametros = parametros;
    }

    public void agregarParametro(ParametroDTO parametro) {
        this.parametros.add(parametro);
    }

}
