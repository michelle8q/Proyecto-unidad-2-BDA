/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Objeto de Transferencia de Datos (DTO) que representa un Análisis completo.
 * Agrupa la información de una prueba de laboratorio, incluyendo la muestra
 * requerida y la colección de parámetros que se van a evaluar.
 *
 * @author luisf
 */
public class AnalisisDTO {

    private int id;
    private String nombre;
    private String notaDescriptiva;
    private MuestraDTO muestra;
    private List<ParametroDTO> parametros;
    private boolean activo;

    /**
     * Constructor por defecto de la clase AnalisisDTO. Inicializa la lista de
     * parámetros vacía.
     */
    public AnalisisDTO() {
        this.parametros = new ArrayList<>();
    }

    /**
     * Constructor que inicializa los atributos de AnalisisDTO. Inicializa la
     * lista de parámetros vacía.
     *
     * * @param id El identificador único del análisis.
     * @param nombre El nombre del análisis.
     * @param notaDescriptiva Una descripción o nota informativa sobre el
     * análisis.
     * @param muestra El objeto MuestraDTO asociado al análisis.
     */
    public AnalisisDTO(int id, String nombre, String notaDescriptiva, MuestraDTO muestra) {
        this.id = id;
        this.nombre = nombre;
        this.notaDescriptiva = notaDescriptiva;
        this.muestra = muestra;
        this.parametros = new ArrayList<>();
    }

    /**
     * Obtiene el identificador del análisis.
     *
     * * @return El identificador del análisis.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador del análisis.
     *
     * * @param id El nuevo identificador.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del análisis.
     *
     * * @return El nombre del análisis.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del análisis.
     *
     * * @param nombre El nuevo nombre del análisis.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la nota descriptiva del análisis.
     *
     * * @return La nota descriptiva.
     */
    public String getNotaDescriptiva() {
        return notaDescriptiva;
    }

    /**
     * Establece la nota descriptiva del análisis.
     *
     * * @param notaDescriptiva La nueva nota descriptiva.
     */
    public void setNotaDescriptiva(String notaDescriptiva) {
        this.notaDescriptiva = notaDescriptiva;
    }

    /**
     * Obtiene la muestra médica asociada a este análisis.
     *
     * * @return El objeto MuestraDTO.
     */
    public MuestraDTO getMuestra() {
        return muestra;
    }

    /**
     * Establece la muestra médica para este análisis.
     *
     * * @param muestra El nuevo objeto MuestraDTO.
     */
    public void setMuestra(MuestraDTO muestra) {
        this.muestra = muestra;
    }

    /**
     * Obtiene la lista de parámetros que componen este análisis.
     *
     * * @return La lista de objetos ParametroDTO.
     */
    public List<ParametroDTO> getParametros() {
        return parametros;
    }

    /**
     * Establece la lista completa de parámetros para el análisis.
     *
     * * @param parametros La nueva lista de objetos ParametroDTO.
     */
    public void setParametros(List<ParametroDTO> parametros) {
        this.parametros = parametros;
    }

    /**
     * Agrega un nuevo parámetro a la lista existente de parámetros del
     * análisis.
     *
     * * @param parametro El objeto ParametroDTO a agregar.
     */
    public void agregarParametro(ParametroDTO parametro) {
        this.parametros.add(parametro);
    }

    /**
     * Indica si el análisis se encuentra activo o disponible.
     *
     * * @return true si el análisis está activo, false en caso contrario.
     */
    public boolean isActivo() {
        return activo;
    }

    /**
     * Establece el estado activo o inactivo del análisis.
     *
     * * @param activo El nuevo estado del análisis (true para activo, false
     * para inactivo).
     */
    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
