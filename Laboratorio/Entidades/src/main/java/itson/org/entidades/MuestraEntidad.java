/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entidad JPA que representa la tabla "muestras" en la base de datos. Define el
 * tipo de muestra médica requerida y mantiene una relación de uno a muchos con
 * los análisis que la utilizan.
 *
 * @author cinca luisf
 */
@Entity
@Table(name = "muestras")
public class MuestraEntidad implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "tipo", nullable = false, length = 50)
    private String tipo;

    @OneToMany(mappedBy = "muestra")
    private List<AnalisisEntidad> analisis;

    /**
     * Constructor por defecto requerido por JPA.
     */
    public MuestraEntidad() {
    }

    /**
     * Constructor que inicializa todos los atributos de la entidad Muestra.
     *
     * * @param id Identificador único de la muestra.
     * @param tipo El tipo de muestra (ej. "Sangre", "Orina").
     * @param analisis Lista de análisis que requieren este tipo de muestra.
     */
    public MuestraEntidad(int id, String tipo, List<AnalisisEntidad> analisis) {
        this.id = id;
        this.tipo = tipo;
        this.analisis = analisis;
    }

    /**
     * Obtiene el identificador de la muestra.
     *
     * @return El identificador.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador de la muestra.
     *
     * @param id El nuevo identificador.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el tipo de la muestra.
     *
     * @return El tipo de la muestra.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo de la muestra.
     *
     * @param tipo El nuevo tipo de la muestra.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Obtiene la lista de entidades {@link AnalisisEntidad} que dependen de
     * esta muestra.
     *
     * @return La lista de análisis.
     */
    public List<AnalisisEntidad> getAnalisis() {
        return analisis;
    }

    /**
     * Establece la lista de análisis vinculados a esta muestra.
     *
     * @param analisis La nueva lista de entidades {@link AnalisisEntidad}.
     */
    public void setAnalisis(List<AnalisisEntidad> analisis) {
        this.analisis = analisis;
    }
}
