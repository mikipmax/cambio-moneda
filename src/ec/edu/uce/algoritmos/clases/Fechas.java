package ec.edu.uce.algoritmos.clases;

/**
 * PROYECTO: Programa de simulación de un sistema de máquina de cambio de
 * monedas
 *
 * Clase Fecha, importante para el modo Administrador FECHA:10-FEB-2017
 *
 * @author: 1725149502 David Morales 1312960444 Michael Ponce Cevallos
 * 1721468021 Estefania Portilla
 * @version 1.0.0
 */
public class Fechas {

    private float Dinero;
    private String Hora;
    private String fecha;

    /**
     * Constructor de la Clase Fecha
     *
     * @param Dinero
     * @param Hora
     * @param fecha
     */
    public Fechas(float Dinero, String Hora, String fecha) {
        this.Dinero = Dinero;
        this.Hora = Hora;
        this.fecha = fecha;
    }

    public float getDinero() {
        return Dinero;
    }

    public void setDinero(float Dinero) {
        this.Dinero = Dinero;
    }

    public String getHora() {
        return Hora;
    }

    public void setHora(String Hora) {
        this.Hora = Hora;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
