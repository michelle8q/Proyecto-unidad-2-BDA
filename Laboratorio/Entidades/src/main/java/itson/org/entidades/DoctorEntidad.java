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
@Table(name="doctores")
public class DoctorEntidad implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name = "nombres", nullable = false, length = 50)
    private String nombre;
    
    @Column(name = "apellidoPaterno", nullable = false, length = 50)
    private String apellidoPaterno;
    
    @Column(name = "apellidoMaterno", nullable = false, length = 50)
    private String apellidoMaterno;
    
    @Column(name = "sexo", nullable = false, length = 50)
    private String sexo;
    
    @OneToMany(mappedBy = "doctor")
    private List<PruebaEntidad> pruebas;
    
    
    public DoctorEntidad() {
    }

    public DoctorEntidad(int id, String nombre, String apellidoPaterno, String apellidoMaterno, String sexo, List<PruebaEntidad> pruebas) {
        this.id = id;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.sexo = sexo;
        this.pruebas = pruebas;
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

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    
    public List<PruebaEntidad> getPruebas() {
        return pruebas;
    }

    public void setPruebas(List<PruebaEntidad> pruebas) {
        this.pruebas = pruebas;
    }
    
    public String toString() {
        return this.nombre + " " + this.apellidoPaterno;
    }
    
}
