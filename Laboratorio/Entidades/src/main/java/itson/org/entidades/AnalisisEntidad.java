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
 *
 * @author cinca
 */
@Entity
@Table(name="analisis")
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
    

    public AnalisisEntidad() {
    }

    public AnalisisEntidad(int id, String nombre, String notaDescriptiva, boolean activo, MuestraEntidad muestra) {
        this.id = id;
        this.nombre = nombre;
        this.notaDescriptiva = notaDescriptiva;
        this.activo = activo;
        this.muestra = muestra;
    }
    
    

    public AnalisisEntidad(int id, String nombre, String notaDescriptiva, MuestraEntidad muestra) {
        this.id = id;
        this.nombre = nombre;
        this.notaDescriptiva = notaDescriptiva;
        this.muestra = muestra;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNotaDescriptiva() {
        return notaDescriptiva;
    }

    public void setNotaDescriptiva(String notaDescriptiva) {
        this.notaDescriptiva = notaDescriptiva;
    }

    public MuestraEntidad getMuestra() {
        return muestra;
    }

    public void setMuestra(MuestraEntidad muestra) {
        this.muestra = muestra;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public List<ParametroEntidad> getParametros() {
        return parametros;
    }

    public void setParametros(List<ParametroEntidad> parametros) {
        this.parametros = parametros;
    }
    
}
