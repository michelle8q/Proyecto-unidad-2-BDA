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
public class PruebaDTO {
    
    private AnalisisDTO analisis;
    private List<ParametroDTO> parametros;
    private String unidad;
    private String rangoReferencia;
    private float resultado;
    private String observaciones;

    public PruebaDTO() {
    }

    
    public PruebaDTO(AnalisisDTO analisis, List<ParametroDTO> parametros, String unidad, String rangoReferencia, float resultado, String observaciones) {
        this.analisis = analisis;
        this.parametros = parametros;
        this.unidad = unidad;
        this.rangoReferencia = rangoReferencia;
        this.resultado = resultado;
        this.observaciones = observaciones;
    }

    public AnalisisDTO getAnalisis() {
        return analisis;
    }

    public void setAnalisis(AnalisisDTO analisis) {
        this.analisis = analisis;
    }

    public List<ParametroDTO> getParametros() {
        return parametros;
    }

    public void setParametros(List<ParametroDTO> parametros) {
        this.parametros = parametros;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getRangoReferencia() {
        return rangoReferencia;
    }

    public void setRangoReferencia(String rangoReferencia) {
        this.rangoReferencia = rangoReferencia;
    }

    public float getResultado() {
        return resultado;
    }

    public void setResultado(float resultado) {
        this.resultado = resultado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    
}
