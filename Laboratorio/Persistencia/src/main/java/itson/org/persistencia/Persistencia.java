/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package itson.org.persistencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author cinca
 */
public class Persistencia {

    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            System.out.println("Intentando conectar...");

            emf = Persistence.createEntityManagerFactory("Conexion");
            em = emf.createEntityManager();

            
            Object resultado = em.createNativeQuery("SELECT 1").getSingleResult();

            System.out.println("Conexion exitosa.");
            System.out.println("Resultado de prueba: " + resultado);

        } catch (Exception e) {
            System.err.println("No se pudo conectar.");
            e.printStackTrace();

        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }

            if (emf != null && emf.isOpen()) {
                emf.close();
            }
        }
    
    }
}
