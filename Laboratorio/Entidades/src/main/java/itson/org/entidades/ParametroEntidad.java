/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entidad JPA que representa la tabla "parametros" en la base de datos. Define
 * un parámetro de salud a ser medido dentro de un análisis, junto con su unidad
 * de medida y la lista de rangos esperados.
 *
 * @author cinca luisf
 */
@Entity
@Table(name = "parametros")
public class ParametroEntidad implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "nota", nullable = false, length = 50)
    private String nota;

    @Column(name = "unidadMedida", nullable = false, length = 50)
    private String unidadMedida;

    @Column(name = "orden", nullable = false)
    private int orden;

    @ManyToOne
    @JoinColumn(name = "idAnalisis", nullable = false)
    private AnalisisEntidad analisis;

    @OneToMany(mappedBy = "parametro", cascade = CascadeType.PERSIST)
    private List<RangoEntidad> rangos;

    /**
     * Constructor por defecto requerido por JPA.
     */
    public ParametroEntidad() {
    }

    /**
     * Constructor que inicializa todos los atributos del parámetro.
     *
     * * @param id Identificador único del parámetro.
     * @param nombre Nombre del parámetro.
     * @param nota Nota o descripción adicional del parámetro.
     * @param unidadMedida Unidad en la que se cuantifica el parámetro (ej.
     * mg/dL).
     * @param orden Posición en la que debe mostrarse el parámetro.
     * @param analisis Entidad {@link AnalisisEntidad} a la que pertenece el
     * parámetro.
     * @param rangos Lista de objetos {@link RangoEntidad} que definen los
     * valores de referencia.
     */
    public ParametroEntidad(int id, String nombre, String nota, String unidadMedida, int orden, AnalisisEntidad analisis, List<RangoEntidad> rangos) {
        this.id = id;
        this.nombre = nombre;
        this.nota = nota;
        this.unidadMedida = unidadMedida;
        this.orden = orden;
        this.analisis = analisis;
        this.rangos = rangos;
    }

    /**
     * Obtiene el identificador del parámetro.
     *
     * @return El identificador.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador del parámetro.
     *
     * @param id El nuevo identificador.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del parámetro.
     *
     * @return El nombre.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del parámetro.
     *
     * @param nombre El nuevo nombre.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la nota descriptiva del parámetro.
     *
     * @return La nota asociada.
     */
    public String getNota() {
        return nota;
    }

    /**
     * Establece la nota descriptiva del parámetro.
     *
     * @param nota La nueva nota.
     */
    public void setNota(String nota) {
        this.nota = nota;
    }

    /**
     * Obtiene la unidad de medida utilizada para este parámetro.
     *
     * @return La unidad de medida.
     */
    public String getUnidadMedida() {
        return unidadMedida;
    }

    /**
     * Establece la unidad de medida utilizada para este parámetro.
     *
     * @param unidadMedida La nueva unidad de medida.
     */
    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    /**
     * Obtiene el orden de visualización de este parámetro dentro de un
     * análisis.
     *
     * @return El número de orden.
     */
    public int getOrden() {
        return orden;
    }

    /**
     * Establece el orden de visualización de este parámetro.
     *
     * @param orden El nuevo número de orden.
     */
    public void setOrden(int orden) {
        this.orden = orden;
    }

    /**
     * Obtiene la lista de rangos esperados para este parámetro.
     *
     * @return La lista de entidades {@link RangoEntidad}.
     */
    public List<RangoEntidad> getRangos() {
        return rangos;
    }

    /**
     * Establece la lista de rangos de referencia para este parámetro.
     *
     * @param rangos La nueva lista de rangos.
     */
    public void setRangos(List<RangoEntidad> rangos) {
        this.rangos = rangos;
    }

    /**
     * Obtiene el análisis clínico al que pertenece este parámetro.
     *
     * @return La entidad {@link AnalisisEntidad} padre.
     */
    public AnalisisEntidad getAnalisis() {
        return analisis;
    }

    /**
     * Establece el análisis clínico al que pertenece este parámetro.
     *
     * @param analisis La nueva entidad {@link AnalisisEntidad}.
     */
    public void setAnalisis(AnalisisEntidad analisis) {
        this.analisis = analisis;
    }
}
