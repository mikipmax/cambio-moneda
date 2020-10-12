package ec.edu.uce.algoritmos.clases;

import ec.edu.uce.algoritmos.pojos.Persona;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;


public class AdministrarElementos {

    public static AdministrarElementos adm = new AdministrarElementos();
    Persona per;
    Vector<Persona> pers = new Vector<>(); 
    DateFormat fen = new SimpleDateFormat("dd-MM-yyyy");

    
    public void nuevo(String id, String nombre, byte edad, Date fnc, Double suel, String eml) {
        Persona person = new Persona(id, nombre, edad, fnc, suel, eml);
        pers.add(person);

    }

   
    public void editar(String id, String nombre, byte edad, Date fnc, Double suel, String eml, int index) {
        pers.get(index).setId(id);
        pers.get(index).setNombre(nombre);
        pers.get(index).setEdad(edad);
        pers.get(index).setFechaNacimiento(fnc);
        pers.get(index).setSueldo(suel);
        pers.get(index).setEmail(eml);

    }

   
    public void eliminar(int index) {
        pers.removeElementAt(index);
    }

    
    public static void quicksort(int arreglo[], int izq, int der) {

        int pivote = arreglo[izq];
        int i = izq;
        int j = der;
        int aux;

        while (i < j) {

            while (arreglo[i] <= pivote && i < j) {
                i++;
            }

            while (arreglo[j] > pivote) {
                j--;
            }

            if (i < j) {
                aux = arreglo[i];
                arreglo[i] = arreglo[j];
                arreglo[j] = aux;
            }
        }
        arreglo[izq] = arreglo[j];
        arreglo[j] = pivote;
        if (izq < j - 1) {
            quicksort(arreglo, izq, j - 1);
        }
        if (j + 1 < der) {
            quicksort(arreglo, j + 1, der);
        }
    }

   
    public static void quicksort(double arreglo[], int izq, int der) {

        double pivote = arreglo[izq];
        int i = izq;
        int j = der;
        double aux;

        while (i < j) {

            while (arreglo[i] <= pivote && i < j) {
                i++;
            }

            while (arreglo[j] > pivote) {
                j--;
            }

            if (i < j) {
                aux = arreglo[i];
                arreglo[i] = arreglo[j];
                arreglo[j] = aux;
            }
        }
        arreglo[izq] = arreglo[j];
        arreglo[j] = pivote;
        if (izq < j - 1) {
            quicksort(arreglo, izq, j - 1);
        }
        if (j + 1 < der) {
            quicksort(arreglo, j + 1, der);
        }
    }

     public int busquedaB(int vector[], int dato) {
        int n = vector.length;
        int centro, inf = 0, sup = n - 1;
        while (inf <= sup) {
            centro = (sup + inf) / 2;
            if (dato == vector[centro]) {
                return centro;
            } else if (dato < vector[centro]) {
                sup = centro - 1;
            } else {
                inf = centro + 1;
            }
        }
        return -1;
    }

   
    public static int busquedaB(String[] arreglo, String dato) {

        int inicio = 0;
        int fin = arreglo.length - 1;
        int pos;
        while (inicio <= fin) {
            pos = (inicio + fin) / 2;
            if (arreglo[pos].compareTo(dato) == 0) {
                return pos;
            } else if (arreglo[pos].compareTo(dato) < 0) {
                inicio = pos + 1;
            } else {
                fin = pos - 1;
            }
        }
        return -1;
    }

    
    public static int busquedaInterpolacion(int arr[], int clave) {
        int izq, der, presunto;
        izq = 0;
        der = arr.length - 1;
        while ((arr[der] >= clave) && (arr[izq] < clave)) {
            
            presunto = izq + ((clave - arr[izq]) * (der - izq) / (arr[der] - arr[izq]));
            if (clave > arr[presunto]) {
                izq = presunto + 1;
            } else {
                if (clave < arr[presunto]) {
                    der = presunto - 1;
                } else {
                    izq = presunto;
                }
            }
        }
        if (arr[izq] == clave) {
            return izq;
        } else {
            return -1;
        }
    }

    public static int min(int x, int y) {
        return (x <= y) ? x : y;
    }

    public static int busquedaFibonacci(double arr[], double x) {
       
        int n = arr.length;
        int fibMMm2 = 0;  
        int fibMMm1 = 1;   
        int fibM = fibMMm2 + fibMMm1;  

        while (fibM < n) {
            fibMMm2 = fibMMm1;
            fibMMm1 = fibM;
            fibM = fibMMm2 + fibMMm1;
        }

        int offset = -1;

        while (fibM > 1) {

            int i = min(offset + fibMMm2, n - 1);

            if (arr[i] < x) {
                fibM = fibMMm1;
                fibMMm1 = fibMMm2;
                fibMMm2 = fibM - fibMMm1;
                offset = i;
            } else if (arr[i] > x) {
                fibM = fibMMm2;
                fibMMm1 = fibMMm1 - fibMMm2;
                fibMMm2 = fibM - fibMMm1;
            } else {
                return i;
            }
        }

        
        if (fibMMm1 != 0 && arr[offset + 1] == x) {
            return offset + 1;
        }

        return -1;
    }

}
