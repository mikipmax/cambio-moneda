package ec.edu.uce.algoritmos.pojos;

/**
 * PROYECTO: Programa de simulación de un sistema de máquina de cambio de
 * monedas
 *
 * Clase Pojo, en la cuàl inicializamos los atributos de una Moneda
 *
 * FECHA:16-FEB-2017
 *
 * @author: 1725149502 David Morales 1312960444 Michael Ponce Cevallos
 * 1721468021 Estefania Portilla
 * @version 1.0.0
 *
 */
public class Moneda {

    Moneda[] moneda = new Moneda[11];
    private int cantidad;
    private double denominacion;

    /**
     * Constructor de la clase Moneda
     *
     * @param denominacion indica el tipo de moneda
     * @param cantidad indica la cantidad que se dispone de la moneda
     */
    public Moneda(double denominacion, int cantidad) {
        this.cantidad = cantidad;
        this.denominacion = denominacion;

    }
    /*
     Mëtodo Getter para obtener la cantidad
     */

    public int getCantidad() {
        return cantidad;
    }
    /*
     Método setter para enviar una cantidad
     */

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    /*
     Método Getter para obtener la denominación de la moneda
     */

    public double getDenominacion() {
        return denominacion;
    }
    /*
     Método Setter para enviar una Denominación
     */

    public void setDenominacion(double denominacion) {
        this.denominacion = denominacion;
    }
   /**
    * Mëtodo toString de la clase
    * @return la denominación y la cantidad
    */
    @Override
    public String toString() {
        return denominacion + "|" + cantidad;
    }

}
