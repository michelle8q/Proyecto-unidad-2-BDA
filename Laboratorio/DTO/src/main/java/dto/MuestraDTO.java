/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 * Objeto de Transferencia de Datos (DTO) que representa una Muestra médica o de laboratorio.
 * Contiene la información básica para identificar el tipo de muestra que se va a analizar.
 *
 * @author luisf
 */
public class MuestraDTO {

    private int id;
    private String tipo;

    /**
     * Constructor por defecto de la clase MuestraDTO.
     */
    public MuestraDTO() {
    }

    /**
     * Constructor que inicializa todos los atributos de MuestraDTO.
     * * @param id   Identificador único de la muestra.
     * @param tipo El tipo o naturaleza de la muestra (ej. Sangre, Orina).
     */
    public MuestraDTO(int id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    /**
     * Obtiene el identificador de la muestra.
     * * @return El identificador de la muestra.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador de la muestra.
     * * @param id El nuevo identificador de la muestra.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el tipo de la muestra.
     * * @return El tipo de la muestra en formato de texto.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo de la muestra.
     * * @param tipo El nuevo tipo de la muestra.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}