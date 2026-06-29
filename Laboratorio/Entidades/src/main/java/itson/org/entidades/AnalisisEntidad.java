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
 * Entidad JPA que representa la tabla "analisis" en la base de datos. Contiene
 * la información general de un análisis clínico o de laboratorio. Implementa
 * {@link Serializable} para su correcta persistencia.
 *
 * @author cinca luisf
 */
@Entity
@Table(name = "analisis")
public class AnalisisEntidad implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "notaDescriptiva", nullable = false, length = 150)
    private String notaDescriptiva;

    @Column(name = "activo", nullable = false)
    private boolean activo;

    @ManyToOne
    @JoinColumn(name = "idMuestra", nullable = false)
    private MuestraEntidad muestra;

    @OneToMany(mappedBy = "analisis", cascade = CascadeType.PERSIST)
    private List<ParametroEntidad> parametros;

    /**
     * Constructor por defecto requerido por JPA.
     */
    public AnalisisEntidad() {
    }

    /**
     * Constructor con todos los atributos básicos de la entidad.
     *
     * * @param id Identificador único del análisis.
     * @param nombre Nombre del análisis.
     * @param notaDescriptiva Descripción o nota del análisis.
     * @param activo Estado actual del análisis (true para activo, false para
     * inactivo).
     * @param muestra Entidad {@link MuestraEntidad} asociada (relación muchos a
     * uno).
     */
    public AnalisisEntidad(int id, String nombre, String notaDescriptiva, boolean activo, MuestraEntidad muestra) {
        this.id = id;
        this.nombre = nombre;
        this.notaDescriptiva = notaDescriptiva;
        this.activo = activo;
        this.muestra = muestra;
    }

    /**
     * Constructor alternativo sin el estado "activo".
     *
     * * @param id Identificador único del análisis.
     * @param nombre Nombre del análisis.
     * @param notaDescriptiva Descripción o nota del análisis.
     * @param muestra Entidad {@link MuestraEntidad} asociada.
     */
    public AnalisisEntidad(int id, String nombre, String notaDescriptiva, MuestraEntidad muestra) {
        this.id = id;
        this.nombre = nombre;
        this.notaDescriptiva = notaDescriptiva;
        this.muestra = muestra;
    }

    /**
     * Obtiene el identificador del análisis.
     *
     * @return El identificador generado en la base de datos.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador del análisis.
     *
     * @param id El nuevo identificador.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del análisis.
     *
     * @return El nombre del análisis.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del análisis.
     *
     * @param nombre El nuevo nombre.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la nota descriptiva del análisis.
     *
     * @return La nota descriptiva.
     */
    public String getNotaDescriptiva() {
        return notaDescriptiva;
    }

    /**
     * Establece la nota descriptiva del análisis.
     *
     * @param notaDescriptiva La nueva nota descriptiva.
     */
    public void setNotaDescriptiva(String notaDescriptiva) {
        this.notaDescriptiva = notaDescriptiva;
    }

    /**
     * Obtiene la entidad {@link MuestraEntidad} asociada al análisis.
     *
     * @return La muestra vinculada.
     */
    public MuestraEntidad getMuestra() {
        return muestra;
    }

    /**
     * Establece la muestra asociada al análisis.
     *
     * @param muestra La nueva entidad {@link MuestraEntidad}.
     */
    public void setMuestra(MuestraEntidad muestra) {
        this.muestra = muestra;
    }

    /**
     * Verifica si el análisis se encuentra activo.
     *
     * @return true si está activo, false en caso contrario.
     */
    public boolean isActivo() {
        return activo;
    }

    /**
     * Establece el estado de actividad del análisis.
     *
     * @param activo El nuevo estado (true o false).
     */
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    /**
     * Obtiene la lista de parámetros (entidades {@link ParametroEntidad})
     * vinculados al análisis.
     *
     * @return La lista de parámetros asociados.
     */
    public List<ParametroEntidad> getParametros() {
        return parametros;
    }

    /**
     * Establece la lista de parámetros asociados a este análisis.
     *
     * @param parametros La nueva lista de entidades {@link ParametroEntidad}.
     */
    public void setParametros(List<ParametroEntidad> parametros) {
        this.parametros = parametros;
    }
}
