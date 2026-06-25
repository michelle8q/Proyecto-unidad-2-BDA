/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.entidades;

import java.time.LocalDateTime;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author cinca
 */
public class Entidades {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Conexion");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Conectando a la base de datos Laboratorio...");

            em.getTransaction().begin();

            // Cliente
            ClienteEntidad cliente = new ClienteEntidad();
            cliente.setNombre("Juan");
            cliente.setApellidoPaterno("Perez");
            cliente.setApellidoMaterno("Lopez");
            cliente.setSexo("Masculino");
            cliente.setFechaNacimiento("2000-05-10");
            cliente.setTipoSangre("O+");

            // Doctor
            DoctorEntidad doctor = new DoctorEntidad();
            doctor.setNombre("Carlos");
            doctor.setApellidoPaterno("Ramirez");
            doctor.setApellidoMaterno("Soto");
            doctor.setSexo("Masculino");

            // Muestra
            MuestraEntidad muestra = new MuestraEntidad();
            muestra.setTipo("Orina");

            // Analisis / Servicio de analisis
            AnalisisEntidad analisis = new AnalisisEntidad();
            analisis.setNombre("EGO");
            analisis.setNotaDescriptiva("Examen general de orina");
            analisis.setActivo(true);
            analisis.setMuestra(muestra);

            // Parametro
            ParametroEntidad parametro = new ParametroEntidad();
            parametro.setNombre("Glucosa");
            parametro.setOrden(1);
            parametro.setNota("Parametro de glucosa en orina");
            parametro.setUnidadMedida("mg/dL");
            parametro.setAnalisis(analisis);

            // Rango
            RangoEntidad rango = new RangoEntidad();
            rango.setEdadInicial(0);
            rango.setEdadFinal(99);
            rango.setRangoInicial(70.0f);
            rango.setRangoFinal(110.0f);
            rango.setSexo("Ambos");
            rango.setParametro(parametro);

            // Prueba / solicitud
            PruebaEntidad prueba = new PruebaEntidad();
            prueba.setFolio("FOLIO-001");
            prueba.setFechaHora(LocalDateTime.now());
            prueba.setCliente(cliente);
            prueba.setDoctor(doctor);

            // Tabla intermedia Prueba - Analisis
            DetallesPruebaEntidad pruebaAnalisis = new DetallesPruebaEntidad();
            pruebaAnalisis.setPrueba(prueba);
            pruebaAnalisis.setResultado(0);
            pruebaAnalisis.setParametro(parametro);

            // Persistir entidades base
            em.persist(cliente);
            em.persist(doctor);
            em.persist(muestra);
            em.persist(analisis);
            em.persist(parametro);
            em.persist(rango);
            em.persist(prueba);
            em.persist(pruebaAnalisis);

            em.getTransaction().commit();

            System.out.println("Entidades guardadas con exito.");

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            System.err.println("Hubo un error al ejecutar la prueba:");
            e.printStackTrace();

        } finally {
            em.close();
            emf.close();
        }
    }
}
