/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import Excepciones.PersistenciaException;
import Interfaces.IConexionBD;
import Interfaces.IRangoDAO;
import itson.org.entidades.RangoEntidad;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author cinca
 */
public class RangoDAO implements IRangoDAO {
    
    /**
    * Atributo que representa la instancia que se usará de la clase.
    */
    private final IConexionBD conexionBD;   
    
    public RangoDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }
    
    @Override
    public RangoEntidad buscarPorParametroYSexo(int idParametro, String sexo) throws PersistenciaException {
        EntityManager em = conexionBD.crearConexion();
        try {
            String jpql = "SELECT r FROM RangoEntidad r WHERE r.parametro.id = :idParametro AND r.sexo = :sexo";
            List<RangoEntidad> resultados = em.createQuery(jpql, RangoEntidad.class)
                    .setParameter("idParametro", idParametro)
                    .setParameter("sexo", sexo)
                    .getResultList();

            return resultados.isEmpty() ? null : resultados.get(0);

        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar el rango: " + e.getMessage());
        } finally {
            em.close();
        }
    }
}
