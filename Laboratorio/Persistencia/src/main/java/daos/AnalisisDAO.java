/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import Excepciones.PersistenciaException;
import Interfaces.IAnalisisDAO;
import Interfaces.IConexionBD;
import itson.org.entidades.AnalisisEntidad;
import itson.org.entidades.MuestraEntidad;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author cinca
 */
public class AnalisisDAO implements IAnalisisDAO {

    private final IConexionBD conexionBD;

    public AnalisisDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    @Override
    public List<AnalisisEntidad> buscarTodos() {
        EntityManager entityManager = conexionBD.crearConexion();

        try {
            String jpql = "SELECT a FROM AnalisisEntidad a";
            TypedQuery<AnalisisEntidad> query = entityManager.createQuery(jpql, AnalisisEntidad.class);
            return query.getResultList();
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    @Override
    public void agregar(AnalisisEntidad analisis) {
        EntityManager em = conexionBD.crearConexion();
        try {
            em.getTransaction().begin();
            if (analisis.getMuestra() != null && analisis.getMuestra().getId() > 0) {
                
                MuestraEntidad muestraOficial = em.find(MuestraEntidad.class, analisis.getMuestra().getId());
                
                analisis.setMuestra(muestraOficial);
            }
            
            em.persist(analisis); 
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace(); 
        } finally {
            em.close();
        }

    }
}
