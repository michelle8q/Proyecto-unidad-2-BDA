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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author cinca
 */
@Entity
@Table(name="parametros")
public class ParametroEntidad implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    
    @OneToMany(mappedBy = "parametro")
    private List<RangoEntidad> rangos;

    public ParametroEntidad() {
    }


    public ParametroEntidad(Long id, String nombre, String nota, String unidadMedida, int orden, AnalisisEntidad analisis, List<RangoEntidad> rangos) {
        this.id = id;
        this.nombre = nombre;
        this.nota = nota;
        this.unidadMedida = unidadMedida;
        this.orden = orden;
        this.analisis = analisis;
        this.rangos = rangos;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public List<RangoEntidad> getRangos() {
        return rangos;
    }

    public void setRangos(List<RangoEntidad> rangos) {
        this.rangos = rangos;
    }

    public AnalisisEntidad getAnalisis() {
        return analisis;
    }

    public void setAnalisis(AnalisisEntidad analisis) {
        this.analisis = analisis;
    }
   
}
