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
@Table(name="detallesPrueba")
public class DetallesPruebaEntidad implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "idPrueba", nullable = false)
    private PruebaEntidad prueba;
    
    @ManyToOne
    @JoinColumn(name = "idParametro", nullable = false)
    private ParametroEntidad parametro;

    @Column(name = "resultado", nullable = false)
    private float resultado;
    
    @Column(name = "observaciones", nullable = false, length = 200)
    private String observaciones;

    public DetallesPruebaEntidad() {
    }

    public DetallesPruebaEntidad(int id, PruebaEntidad prueba, ParametroEntidad parametro, float resultado, String observaciones) {
        this.id = id;
        this.prueba = prueba;
        this.parametro = parametro;
        this.resultado = resultado;
        this.observaciones = observaciones;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PruebaEntidad getPrueba() {
        return prueba;
    }

    public void setPrueba(PruebaEntidad prueba) {
        this.prueba = prueba;
    }

    public float getResultado() {
        return resultado;
    }

    public void setResultado(float resultado) {
        this.resultado = resultado;
    }

    public ParametroEntidad getParametro() {
        return parametro;
    }

    public void setParametro(ParametroEntidad parametro) {
        this.parametro = parametro;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

   
    
}
