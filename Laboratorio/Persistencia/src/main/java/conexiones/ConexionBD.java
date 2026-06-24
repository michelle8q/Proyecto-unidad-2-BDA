/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexiones;

import Interfaces.IConexionBD;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**
 *
 * @author cinca
 */
public class ConexionBD implements IConexionBD {
    @Override
    public EntityManager crearConexion() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Conexion");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager;
    }
}
