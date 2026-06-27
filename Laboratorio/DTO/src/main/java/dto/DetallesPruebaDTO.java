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
public class DetallesPruebaDTO {
    
    private int idDetalle;         
    private String analisis;
    private String parametro;
    private String unidadMedida;
    private String rangoReferencia; 
    private float resultado;       
    private String observaciones;

    public DetallesPruebaDTO() {
    }

    public DetallesPruebaDTO(int idDetalle, String analisis, String parametro, String unidadMedida, String rangoReferencia, float resultado, String observaciones) {
        this.idDetalle = idDetalle;
        this.analisis = analisis;
        this.parametro = parametro;
        this.unidadMedida = unidadMedida;
        this.rangoReferencia = rangoReferencia;
        this.resultado = resultado;
        this.observaciones = observaciones;
    }

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public String getAnalisis() {
        return analisis;
    }

    public void setAnalisis(String analisis) {
        this.analisis = analisis;
    }

    public String getParametro() {
        return parametro;
    }

    public void setParametro(String parametro) {
        this.parametro = parametro;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
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