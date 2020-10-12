package ec.edu.uce.algoritmos.pojos;


import java.util.Date;

/**
 * PROYECTO: Dada una clase Persona y sus atributos realizamos tanto el
 * ingreso,editar,buscar e imprimir los elementos que se almacena en un arreglo
 * para proceder a buscar aplicando busqueda binaria, interpolacion y fibonacci.
 *
 * Clase Pojo, en la cuàl inicializamos los campos de cada elemento
 * perteneciente a una persona
 *
 * FECHA:12-DIC-2016
 *
 * @author: 1725149502 David Morales 1312960444 Michael Ponce Cevallos
 * 1721468021 Estefania Portilla
 * @version 1.0.0
 *
 */
public class Persona implements Comparable<Persona> {

    /**
     * Campos bàsicos o atributos pertenecientes a una persona
     */
    private String id;
    protected String nombre;
    protected byte edad;
    private Date fechaNacimiento;
    private Double sueldo;
    protected String email;
    public static final char PAIS = 'E'; //no modificable

    /**
     * Constructor de la clase persona
     *
     * @param id de una persona
     * @param nombre de una persona
     * @param edad de una persona
     * @param fechaNacimiento de una persona
     * @param sueldo de una persona
     * @param email de una persona
     */
    public Persona(String id, String nombre, byte edad, Date fechaNacimiento, Double sueldo, String email) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.fechaNacimiento = fechaNacimiento;
        this.sueldo = sueldo;
        this.email = email;
    }

    public Persona() {

    }

    /**
     * Mètodo Get del Id
     *
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * Mètodo Set del Id
     *
     * @param id de una persona
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Mètodo Get del nombre
     *
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Mètodo Set del nombre
     *
     * @param nombre de una persona
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Mètodo Get de la edad
     *
     * @return edad
     */
    public byte getEdad() {
        return edad;
    }

    /**
     * Mètodo Set de la edad
     *
     * @param edad de una persona
     */
    public void setEdad(byte edad) {
        this.edad = edad;
    }

    /**
     * Mètodo Get de la fecha de nacimeinto
     *
     * @return fecha de nacimiento
     */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Mètodo Set de la fecha de nacimeinto
     *
     * @param fechaNacimiento de una persona
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Mètodo get del sueldo
     *
     * @return sueldo
     */
    public Double getSueldo() {
        return sueldo;
    }

    /**
     * Mètodo Set del sueldo
     *
     * @param sueldo de una persona
     */
    public void setSueldo(Double sueldo) {
        this.sueldo = sueldo;
    }

    /**
     * Mètodo Get del email
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Mètodo Set del email
     *
     * @param email de una persona
     */
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int compareTo(Persona o) {
        return this.id.compareToIgnoreCase(o.getId());
    }
}
