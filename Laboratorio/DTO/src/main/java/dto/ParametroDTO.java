/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Objeto de Transferencia de Datos (DTO) que representa un Parámetro a evaluar.
 * Contiene el nombre del estudio, notas relevantes, la unidad de medida, el
 * orden de presentación y la lista de rangos de referencia aplicables.
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

    /**
     * Constructor por defecto de la clase ParametroDTO.
     */
    public ParametroDTO() {
    }

    /**
     * Constructor que inicializa los atributos básicos de ParametroDTO e
     * inicializa la lista de rangos vacía.
     *
     * * @param nombre El nombre del parámetro.
     * @param nota Nota o descripción adicional asociada al parámetro.
     * @param unidadMedida La unidad de medida en la que se expresa el parámetro
     * (ej. mg/dL).
     * @param orden El orden de presentación o clasificación del parámetro.
     * @param tipoValor El tipo de valor esperado para este parámetro.
     */
    public ParametroDTO(String nombre, String nota, String unidadMedida, int orden, String tipoValor) {
        this.nombre = nombre;
        this.nota = nota;
        this.unidadMedida = unidadMedida;
        this.orden = orden;
        this.tipoValor = tipoValor;
        this.rangos = new ArrayList<>();
    }

    /**
     * Obtiene el nombre del parámetro.
     *
     * * @return El nombre del parámetro.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del parámetro.
     *
     * * @param nombre El nuevo nombre del parámetro.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la nota adicional del parámetro.
     *
     * * @return La nota del parámetro.
     */
    public String getNota() {
        return nota;
    }

    /**
     * Establece una nota adicional para el parámetro.
     *
     * * @param nota La nueva nota del parámetro.
     */
    public void setNota(String nota) {
        this.nota = nota;
    }

    /**
     * Obtiene la unidad de medida del parámetro.
     *
     * * @return La unidad de medida.
     */
    public String getUnidadMedida() {
        return unidadMedida;
    }

    /**
     * Establece la unidad de medida del parámetro.
     *
     * * @param unidadMedida La nueva unidad de medida.
     */
    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    /**
     * Obtiene el orden de presentación del parámetro.
     *
     * * @return El orden.
     */
    public int getOrden() {
        return orden;
    }

    /**
     * Establece el orden de presentación del parámetro.
     *
     * * @param orden El nuevo número de orden.
     */
    public void setOrden(int orden) {
        this.orden = orden;
    }

    /**
     * Obtiene el tipo de valor que registra este parámetro.
     *
     * * @return El tipo de valor.
     */
    public String getTipoValor() {
        return tipoValor;
    }

    /**
     * Establece el tipo de valor para este parámetro.
     *
     * * @param tipoValor El nuevo tipo de valor.
     */
    public void setTipoValor(String tipoValor) {
        this.tipoValor = tipoValor;
    }

    /**
     * Obtiene la lista de rangos asociados a este parámetro.
     *
     * * @return Una lista con los rangos de referencia.
     */
    public List<RangoDTO> getRangos() {
        return rangos;
    }

    /**
     * Establece la lista completa de rangos de referencia para el parámetro.
     *
     * * @param rangos La nueva lista de objetos RangoDTO.
     */
    public void setRangos(List<RangoDTO> rangos) {
        this.rangos = rangos;
    }
}
