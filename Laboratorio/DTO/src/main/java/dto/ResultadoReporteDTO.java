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
    private String apellidoPaterno;
    private String apellidoMaterno;
    
    private String sexoPaciente;
    private String doctor;
    private String apellidoPaternoDoctor;
    private String apellidoMaternoDoctor;

    private String analisis;
    private int orden;
    private String parametro;
    private String resultado;
    private String unidadMedida;
    private String valoresNormales;
    private String muestra;
    private String edad;

    public ResultadoReporteDTO() {
    }


    public ResultadoReporteDTO(String folio, String fechaHora, String paciente, String apellidoPaterno, String apellidoMaterno, String sexoPaciente, String doctor, String apellidoPaternoDoctor, String apellidoMaternoDoctor, String analisis, int orden, String parametro, String resultado, String unidadMedida, String valoresNormales, String muestra, String edad) {
        this.folio = folio;
        this.fechaHora = fechaHora;
        this.paciente = paciente;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.sexoPaciente = sexoPaciente;
        this.doctor = doctor;
        this.apellidoPaternoDoctor = apellidoPaternoDoctor;
        this.apellidoMaternoDoctor = apellidoMaternoDoctor;
        this.analisis = analisis;
        this.orden = orden;
        this.parametro = parametro;
        this.resultado = resultado;
        this.unidadMedida = unidadMedida;
        this.valoresNormales = valoresNormales;
        this.muestra = muestra;
        this.edad = edad;
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

    public String getMuestra() {
        return muestra;
    }

    public void setMuestra(String muestra) {
        this.muestra = muestra;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getApellidoPaternoDoctor() {
        return apellidoPaternoDoctor;
    }

    public void setApellidoPaternoDoctor(String apellidoPaternoDoctor) {
        this.apellidoPaternoDoctor = apellidoPaternoDoctor;
    }

    public String getApellidoMaternoDoctor() {
        return apellidoMaternoDoctor;
    }

    public void setApellidoMaternoDoctor(String apellidoMaternoDoctor) {
        this.apellidoMaternoDoctor = apellidoMaternoDoctor;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }
    
    
}
