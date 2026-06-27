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
 *
 * @author cinca
 */
@Entity
@Table(name="rangos")
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

    public RangoEntidad() {
    }

    public RangoEntidad(int id, int edadInicial, int edadFinal, float rangoInicial, float rangoFinal, String sexo, ParametroEntidad parametro) {
        this.id = id;
        this.edadInicial = edadInicial;
        this.edadFinal = edadFinal;
        this.rangoInicial = rangoInicial;
        this.rangoFinal = rangoFinal;
        this.sexo = sexo;
        this.parametro = parametro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public ParametroEntidad getParametro() {
        return parametro;
    }

    public void setParametro(ParametroEntidad parametro) {
        this.parametro = parametro;
    }
    
    
    
}
