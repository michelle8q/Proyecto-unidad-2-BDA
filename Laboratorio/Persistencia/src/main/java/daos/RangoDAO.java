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
 * Implementación de la interfaz IRangoDAO para la gestión de rangos de
 * referencia en la base de datos utilizando JPA.
 *
 * Permite buscar el rango de valores normales de un parámetro filtrando
 * por sexo del paciente, lo que permite mostrar el rango correcto en la
 * pantalla de ingreso de resultados y en el reporte.
 *
 * @author cinca luisf
 */
public class RangoDAO implements IRangoDAO {
    
    /**
    * Atributo que representa la instancia que se usará de la clase.
    */
    private final IConexionBD conexionBD;   
    
    /**
     * Constructor que recibe la conexión a la base de datos.
     *
     * @param conexionBD instancia de la conexión a utilizar
     */
    public RangoDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }
    
    /**
     * Busca el rango de referencia de un parámetro filtrando por sexo del
     * paciente.
     *
     * Utiliza JPQL para consultar el rango cuyo parámetro coincida con el
     * id recibido y que el sexo coincida con el del paciente o sea "ambos". 
     *
     * @param idParametro identificador del parámetro a buscar
     * @param sexo sexo del paciente para filtrar el rango correspondiente
     * @return RangoEntidad con los valores de referencia, o null si no existe
     * @throws PersistenciaException si ocurre un error durante la consulta
     */
    @Override
    public RangoEntidad buscarPorParametroYSexo(int idParametro, String sexo) throws PersistenciaException {
        EntityManager em = conexionBD.crearConexion();
        try {
            String jpql = "SELECT r FROM RangoEntidad r WHERE r.parametro.id = :idParametro AND (r.sexo = :sexo OR r.sexo = 'Ambos')";
            List<RangoEntidad> resultados = em.createQuery(jpql, RangoEntidad.class)
                    .setParameter("idParametro", idParametro)
                    .setParameter("sexo", sexo)
                    .getResultList();

            if (resultados.isEmpty()) {
                return null;
            }
            
            return resultados.get(0);

        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar el rango: " + e.getMessage());
        } finally {
            em.close();
        }
    }
}
