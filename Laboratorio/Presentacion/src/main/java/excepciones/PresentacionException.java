/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excepciones;

/**
 * Excepcion personalizada para errores ocurridos en la capa de presentacion.
 * 
 * Se lanza cuando ocurre un error al procesar la entrada del usuario como datos
 * invalidos.
 * 
 * @author cinca
 */
public class PresentacionException extends Exception {
    
    /**
     * Constructor que recibe el mensaje descriptivo del error.
     *
     * @param mensaje descripcion del error de persistencia
     */
    public PresentacionException(String mensaje) {
        super(mensaje);
    }
}
