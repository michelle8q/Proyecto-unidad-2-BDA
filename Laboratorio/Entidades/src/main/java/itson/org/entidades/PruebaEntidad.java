/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.entidades;

import java.io.Serializable;
import java.time.LocalDateTime;
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
@Table(name="pruebas")
public class PruebaEntidad implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    
    private int id;
    @Column(name = "folio", nullable = false, length = 50)
    private String folio;
    
    @Column(name = "fechaHora", nullable = false)
    private LocalDateTime fechaHora; 
    
    @ManyToOne
    @JoinColumn(name = "idDoctor", nullable = false)
    private DoctorEntidad doctor;
    
    @ManyToOne
    @JoinColumn(name = "idCliente", nullable = false)
    private ClienteEntidad cliente;
    
    @OneToMany(mappedBy = "prueba", cascade = CascadeType.PERSIST)
    private List<PruebaAnalisisEntidad> analisis;

    public PruebaEntidad() {
    }

    public PruebaEntidad(int id, String folio, LocalDateTime fechaHora, DoctorEntidad doctor, ClienteEntidad cliente, List<PruebaAnalisisEntidad> analisis) {
        this.id = id;
        this.folio = folio;
        this.fechaHora = fechaHora;
        this.doctor = doctor;
        this.cliente = cliente;
        this.analisis = analisis;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public DoctorEntidad getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorEntidad doctor) {
        this.doctor = doctor;
    }

    public List<PruebaAnalisisEntidad> getAnalisis() {
        return analisis;
    }

    public void setAnalisis(List<PruebaAnalisisEntidad> analisis) {
        this.analisis = analisis;
    }

    public ClienteEntidad getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntidad cliente) {
        this.cliente = cliente;
    }
    
    
}
