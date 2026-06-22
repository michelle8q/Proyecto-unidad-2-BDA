/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.entidades;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author cinca
 */
public class Entidades {
    public static void main(String[] args) {
        // "LaboratorioPU" debe ser el nombre de tu Persistence Unit en el persistence.xml
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Conexion");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Conectando a la base de datos Laboratorio...");
            
            // 1. Iniciar la transacción
            em.getTransaction().begin();

            // 2. Instanciar tu entidad (Cámbiolo por el nombre real de tu clase @Entity)
            // Ejemplo ficticio:
            // Cliente cliente = new Cliente();
            // cliente.setNombre("Prueba DB2");

            // 3. Persistir (guardar) la entidad en la base de datos
            // em.persist(cliente);

            // 4. Confirmar la transacción
            em.getTransaction().commit();
            
            System.out.println("¡Entidad ejecutada y guardada con éxito, carnal!");

        } catch (Exception e) {
            // Si algo truena, hacemos rollback para no dejar cochinero en la BD
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("¡Valió barriga! Hubo un error al ejecutar la entidad:");
            e.printStackTrace();
        } finally {
            // Cerramos los recursos sí o sí
            em.close();
            emf.close();
        }
    }
}
