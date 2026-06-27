/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author luisf
 */
public class RangoDTO {
    private int edadInicial;
    private int edadFinal;
    private float rangoInicial;
    private float rangoFinal;
    private String sexo;

    public RangoDTO() {
    }

    public RangoDTO(int edadInicial, int edadFinal, float rangoInicial, float rangoFinal, String sexo) {
        this.edadInicial = edadInicial;
        this.edadFinal = edadFinal;
        this.rangoInicial = rangoInicial;
        this.rangoFinal = rangoFinal;
        this.sexo = sexo;
    }

    public int getEdadInicial() {
        return edadInicial;
    }

    public void setEdadInicial(int edadInicial) {
        this.edadInicial = edadInicial;
    }

    public int getEdadFinal() {
        return edadFinal;
    }

    public void setEdadFinal(int edadFinal) {
        this.edadFinal = edadFinal;
    }

    public float getRangoInicial() {
        return rangoInicial;
    }

    public void setRangoInicial(float rangoInicial) {
        this.rangoInicial = rangoInicial;
    }

    public float getRangoFinal() {
        return rangoFinal;
    }

    public void setRangoFinal(float rangoFinal) {
        this.rangoFinal = rangoFinal;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
