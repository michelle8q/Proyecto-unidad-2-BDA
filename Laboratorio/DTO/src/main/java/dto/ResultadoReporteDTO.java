/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author cinca
 * 
 * DTO dedicada a lo que necesitara el servicio generador de reportes para desplegar los datos en el reporte.
 * 
 */
public class ResultadoReporteDTO {
    private String folio;
    private String fechaHora;
    private String paciente;
    private String sexoPaciente;
    private String doctor;

    private String analisis;
    private int orden;
    private String parametro;
    private String resultado;
    private String unidadMedida;
    private String valoresNormales;

    public ResultadoReporteDTO() {
    }

    public ResultadoReporteDTO(String folio, String fechaHora, String paciente, String sexoPaciente, String doctor, String analisis, int orden, String parametro, String resultado, String unidadMedida, String valoresNormales) {
        this.folio = folio;
        this.fechaHora = fechaHora;
        this.paciente = paciente;
        this.sexoPaciente = sexoPaciente;
        this.doctor = doctor;
        this.analisis = analisis;
        this.orden = orden;
        this.parametro = parametro;
        this.resultado = resultado;
        this.unidadMedida = unidadMedida;
        this.valoresNormales = valoresNormales;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }

    public String getSexoPaciente() {
        return sexoPaciente;
    }

    public void setSexoPaciente(String sexoPaciente) {
        this.sexoPaciente = sexoPaciente;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getAnalisis() {
        return analisis;
    }

    public void setAnalisis(String analisis) {
        this.analisis = analisis;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public String getParametro() {
        return parametro;
    }

    public void setParametro(String parametro) {
        this.parametro = parametro;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public String getValoresNormales() {
        return valoresNormales;
    }

    public void setValoresNormales(String valoresNormales) {
        this.valoresNormales = valoresNormales;
    }
    
    
}
