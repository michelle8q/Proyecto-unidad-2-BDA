/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entidad JPA que representa la tabla "rangos" en la base de datos. Modela los
 * límites numéricos esperados/sanos de un parámetro evaluado, segmentados por
 * edad (mínima y máxima) y sexo.
 *
 * @author cinca luisf
 */
@Entity
@Table(name = "rangos")
public class RangoEntidad implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "edadInicial", nullable = false)
    private int edadInicial;

    @Column(name = "edadFinal", nullable = false)
    private int edadFinal;

    @Column(name = "rangoInicial", nullable = false)
    private float rangoInicial;

    @Column(name = "rangoFinal", nullable = false)
    private float rangoFinal;

    @Column(name = "sexo", nullable = false, length = 20)
    private String sexo;

    @ManyToOne
    @JoinColumn(name = "idParametro", nullable = false)
    private ParametroEntidad parametro;

    /**
     * Constructor por defecto requerido por JPA.
     */
    public RangoEntidad() {
    }

    /**
     * Constructor que inicializa todos los atributos del rango.
     *
     * * @param id Identificador único del rango.
     * @param edadInicial Edad mínima para la que aplica el rango.
     * @param edadFinal Edad máxima para la que aplica el rango.
     * @param rangoInicial Valor mínimo considerado normal.
     * @param rangoFinal Valor máximo considerado normal.
     * @param sexo Sexo del paciente aplicable al rango.
     * @param parametro Entidad {@link ParametroEntidad} al que pertenece este
     * rango.
     */
    public RangoEntidad(int id, int edadInicial, int edadFinal, float rangoInicial, float rangoFinal, String sexo, ParametroEntidad parametro) {
        this.id = id;
        this.edadInicial = edadInicial;
        this.edadFinal = edadFinal;
        this.rangoInicial = rangoInicial;
        this.rangoFinal = rangoFinal;
        this.sexo = sexo;
        this.parametro = parametro;
    }

    /**
     * Obtiene el identificador del rango.
     *
     * @return El identificador.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador del rango.
     *
     * @param id El nuevo identificador.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene la edad inicial (mínima) aplicable.
     *
     * @return La edad inicial.
     */
    public int getEdadInicial() {
        return edadInicial;
    }

    /**
     * Establece la edad inicial (mínima) aplicable.
     *
     * @param edadInicial La nueva edad inicial.
     */
    public void setEdadInicial(int edadInicial) {
        this.edadInicial = edadInicial;
    }

    /**
     * Obtiene la edad final (máxima) aplicable.
     *
     * @return La edad final.
     */
    public int getEdadFinal() {
        return edadFinal;
    }

    /**
     * Establece la edad final (máxima) aplicable.
     *
     * @param edadFinal La nueva edad final.
     */
    public void setEdadFinal(int edadFinal) {
        this.edadFinal = edadFinal;
    }

    /**
     * Obtiene el límite inferior del rango.
     *
     * @return El valor inicial del rango.
     */
    public float getRangoInicial() {
        return rangoInicial;
    }

    /**
     * Establece el límite inferior del rango.
     *
     * @param rangoInicial El nuevo valor inicial del rango.
     */
    public void setRangoInicial(float rangoInicial) {
        this.rangoInicial = rangoInicial;
    }

    /**
     * Obtiene el límite superior del rango.
     *
     * @return El valor final del rango.
     */
    public float getRangoFinal() {
        return rangoFinal;
    }

    /**
     * Establece el límite superior del rango.
     *
     * @param rangoFinal El nuevo valor final del rango.
     */
    public void setRangoFinal(float rangoFinal) {
        this.rangoFinal = rangoFinal;
    }

    /**
     * Obtiene el sexo para el cual es válido el rango.
     *
     * @return El sexo aplicable.
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * Establece el sexo para el cual es válido el rango.
     *
     * @param sexo El nuevo sexo.
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    /**
     * Obtiene el parámetro al que pertenece este rango.
     *
     * @return La entidad {@link ParametroEntidad} asociada.
     */
    public ParametroEntidad getParametro() {
        return parametro;
    }

    /**
     * Establece el parámetro al que pertenece este rango.
     *
     * @param parametro La nueva entidad {@link ParametroEntidad}.
     */
    public void setParametro(ParametroEntidad parametro) {
        this.parametro = parametro;
    }
}
