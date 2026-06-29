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
 *
 * @author cinca
 */
@Entity
@Table(name="muestras")
public class MuestraEntidad implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name = "tipo", nullable = false, length = 50)
    private String tipo;
    
    @OneToMany(mappedBy = "muestra")
    private List<AnalisisEntidad> analisis;

    public MuestraEntidad() {
    }

    public MuestraEntidad(int id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }
    
    
    public MuestraEntidad(int id, String tipo, List<AnalisisEntidad> analisis) {
        this.id = id;
        this.tipo = tipo;
        this.analisis = analisis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<AnalisisEntidad> getAnalisis() {
        return analisis;
    }

    public void setAnalisis(List<AnalisisEntidad> analisis) {
        this.analisis = analisis;
    }
    
    
    
}
