/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.entidades;

import java.io.Serializable;
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
@Table(name="pruebaAnalisis")
public class PruebaAnalisisEntidad implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idPrueba", nullable = false)
    private PruebaEntidad prueba;

    @ManyToOne
    @JoinColumn(name = "idAnalisis", nullable = false)
    private AnalisisEntidad analisis;

    public PruebaAnalisisEntidad() {
    }

    public PruebaAnalisisEntidad(Long id, PruebaEntidad prueba, AnalisisEntidad analisis) {
        this.id = id;
        this.prueba = prueba;
        this.analisis = analisis;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PruebaEntidad getPrueba() {
        return prueba;
    }

    public void setPrueba(PruebaEntidad prueba) {
        this.prueba = prueba;
    }

    public AnalisisEntidad getAnalisis() {
        return analisis;
    }

    public void setAnalisis(AnalisisEntidad analisis) {
        this.analisis = analisis;
    }
    
    
    
}
