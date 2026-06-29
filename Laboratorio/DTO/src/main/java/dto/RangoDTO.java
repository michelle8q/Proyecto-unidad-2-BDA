/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 * Objeto de Transferencia de Datos (DTO) que representa los límites de un rango
 * de referencia. Define los valores normales o esperados de un parámetro
 * clínico, segmentados por edad y sexo del paciente.
 *
 * @author luisf
 */
public class RangoDTO {

    private int edadInicial;
    private int edadFinal;
    private float rangoInicial;
    private float rangoFinal;
    private String sexo;

    /**
     * Constructor por defecto de la clase RangoDTO.
     */
    public RangoDTO() {
    }

    /**
     * Constructor que inicializa todos los atributos de RangoDTO.
     *
     * * @param edadInicial La edad mínima para la cual aplica este rango.
     * @param edadFinal La edad máxima para la cual aplica este rango.
     * @param rangoInicial El valor mínimo considerado normal/aceptable.
     * @param rangoFinal El valor máximo considerado normal/aceptable.
     * @param sexo El sexo para el cual aplica este rango (ej. "M", "F").
     */
    public RangoDTO(int edadInicial, int edadFinal, float rangoInicial, float rangoFinal, String sexo) {
        this.edadInicial = edadInicial;
        this.edadFinal = edadFinal;
        this.rangoInicial = rangoInicial;
        this.rangoFinal = rangoFinal;
        this.sexo = sexo;
    }

    /**
     * Obtiene la edad inicial (mínima) del rango.
     *
     * * @return La edad inicial.
     */
    public int getEdadInicial() {
        return edadInicial;
    }

    /**
     * Establece la edad inicial (mínima) del rango.
     *
     * * @param edadInicial La nueva edad inicial.
     */
    public void setEdadInicial(int edadInicial) {
        this.edadInicial = edadInicial;
    }

    /**
     * Obtiene la edad final (máxima) del rango.
     *
     * * @return La edad final.
     */
    public int getEdadFinal() {
        return edadFinal;
    }

    /**
     * Establece la edad final (máxima) del rango.
     *
     * * @param edadFinal La nueva edad final.
     */
    public void setEdadFinal(int edadFinal) {
        this.edadFinal = edadFinal;
    }

    /**
     * Obtiene el valor mínimo aceptable del rango.
     *
     * * @return El valor mínimo del rango.
     */
    public float getRangoInicial() {
        return rangoInicial;
    }

    /**
     * Establece el valor mínimo aceptable del rango.
     *
     * * @param rangoInicial El nuevo valor mínimo del rango.
     */
    public void setRangoInicial(float rangoInicial) {
        this.rangoInicial = rangoInicial;
    }

    /**
     * Obtiene el valor máximo aceptable del rango.
     *
     * * @return El valor máximo del rango.
     */
    public float getRangoFinal() {
        return rangoFinal;
    }

    /**
     * Establece el valor máximo aceptable del rango.
     *
     * * @param rangoFinal El nuevo valor máximo del rango.
     */
    public void setRangoFinal(float rangoFinal) {
        this.rangoFinal = rangoFinal;
    }

    /**
     * Obtiene el sexo para el cual aplica el rango.
     *
     * * @return El sexo especificado para el rango.
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * Establece el sexo para el cual aplica el rango.
     *
     * * @param sexo El nuevo sexo aplicable al rango.
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
