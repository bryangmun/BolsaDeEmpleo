/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad Ean (Bogot� - Colombia)
 * Departamento de Tecnolog�a de la Informaci�n y Comunicaciones
 * Licenciado bajo el esquema Academic Free License version 2.1
 * <p>
 * Basado en un Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: Bolsa de Empleo
 * Fecha: 11 de marzo de 2021
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package universidadean.empleo.mundo;

import java.util.ArrayList;

/**
 * Es la clase que se encarga de manejar y organizar los aspirantes <br>
 * <b>inv: </b> <br>
 * aspirantes != null <br>
 * En el vector de aspirantes no hay dos o m�s con el mismo nombre
 */
public class BolsaDeEmpleo {
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la lista que contiene todos los aspirantes
     */
    private ArrayList<Aspirante> aspirantes;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye una nueva bolsa de emplea sin aspirantes.
     */
    public BolsaDeEmpleo() {
        aspirantes = new ArrayList<>();
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Retorna una lista de aspirantes. La lista retornada no es la misma que la almacenada en esta clase, pero si tiene el mismo orden.
     *
     * @return lista de aspirantes
     */
    public ArrayList<Aspirante> darAspirantes() {
        ArrayList<Aspirante> copia = new ArrayList<>(aspirantes);
        return copia;
    }

    /**
     * Agrega un nuevo aspirante a la bolsa
     *
     * @param nombreA           El nombre del aspirante - nombreA != null
     * @param profesionA        La profesi�n del aspirante - profesionA es uno de estos { ADMINISTRADOR, INGENIERO_INDUSTRIAL, CONTADOR, ECONOMISTA }
     * @param aniosExperienciaA A�os de experiencia del aspirante - aniosExperienciaA > 0
     * @param edadA             La edad del aspirante - edadA > 0
     * @param telefonoA         El tel�fono del aspirante - telefonoA != null
     * @param imagenA           La ruta a la imagen del aspirante - imagenA != null
     * @return Se retorn� true si el aspirante fue adicionado o false de lo contrario
     */

    public boolean agregarAspirante(String nombreA, String profesionA, int aniosExperienciaA, int edadA, String telefonoA, String imagenA) {
        int aspiranteBuscado = buscarAspirante(nombreA);
        boolean agregado = false;
        if (aspiranteBuscado == -1) {
            Aspirante nuevoAspirante = new Aspirante(nombreA, profesionA, aniosExperienciaA, edadA, telefonoA, imagenA);
            aspirantes.add(nuevoAspirante);
            agregado = true;
        }

        return agregado;
    }

    /**
     * Organiza la lista de aspirantes por nombre usando el algoritmo de burbuja. <br>
     * <b>post: </b>La lista de aspirantes est� ordenada por nombre (orden ascendente).
     */
    public void ordenarPorNombre() {
        // TODO: Realizar el ejercicio correspondiente
        int n = aspirantes.size();
        Aspirante temp;
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n-1; j++)
            {
               if (aspirantes.get(j).darNombre().compareTo(aspirantes.get(j+1).darNombre()) > 0) {
                temp = aspirantes.get(j);
                aspirantes.set(j, aspirantes.get(j+1));
                aspirantes.set(j+1, temp);
               }
            }
        }
    }

    /**
     * Organiza la lista de aspirantes por edad usando el algoritmo de selecci�n <br>
     * <b>post: </b>La lista de aspirantes est� ordenada por edad
     */
    public void ordenarPorEdad() {
        // TODO: Realizar el ejercicio correspondiente
        int n = aspirantes.size();
        Aspirante temp;
        for (int i = 0; i < n-1; i++)
        {
            int index = i;
            for (int j = i+1; j < n; j++)
            {
                if (aspirantes.get(j).darEdad() < aspirantes.get(index).darEdad()) {
                    index = j;
                }
            }
            temp = aspirantes.get(index);
            aspirantes.set(index, aspirantes.get(i));
            aspirantes.set(i, temp);



        }
    }

    /**
     * Organiza la lista de aspirantes por profesi�n usando el algoritmo de burbuja <br>
     * <b>post: </b>El conjunto de aspirantes esta ordenado por profesi�n
     */
    public void ordenarPorProfesion() {
        // TODO: Realizar el ejercicio correspondiente
        int n = aspirantes.size();
        Aspirante temp;
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n-1; j++)
            {
                if (aspirantes.get(j).darProfesion().compareTo(aspirantes.get(j+1).darProfesion()) > 0) {
                    temp = aspirantes.get(j);
                    aspirantes.set(j, aspirantes.get(j+1));
                    aspirantes.set(j+1, temp);
                }
            }
        }
    }

    /**
     * Organiza la lista de aspirantes por a�os de experiencia usando el algoritmo de inserci�n <br>
     * <b>post: </b>La lista de aspirantes est� ordenada por a�os de experiencia
     */
    public void ordenarPorAniosDeExperiencia() {
        // TODO: Realizar el ejercicio correspondiente
        int n = aspirantes.size();
        for (int i =1; i < n; i++)
        {
            Aspirante a = aspirantes.get(i);
            int j = i-1;
            while ((j >= 0 ) && (aspirantes.get(j).darAniosExperiencia() > a.darAniosExperiencia())) {
                aspirantes.set(j+1, aspirantes.get(j));
            j--;
            }
            aspirantes.set(j+1, a);

        }
    }

    /**
     * Busca un Aspirante seg�n su nombre y retorna la posici�n en la que se encuentra. <br>
     *
     * @param nombre El nombre del aspirante buscado - nombre!=null
     * @return Se retorn� la posici�n donde se encuentra un aspirante con el nombre dado. Si no se encuentra ning�n aspirante con ese nombre se retorn� -1.
     */
    public int buscarAspirante(String nombre) {
        int posicion = -1;
        for (int i = 0; i < aspirantes.size(); i++) {
            if (aspirantes.get(i).darNombre().equals(nombre)){
                posicion = i;
                return posicion;
            }

        }
        return -1;
    }



    /**
     * Busca un aspirante utilizando una b�squeda binaria. <br>
     * <b>pre: </b> La lista de aspirantes se encuentra ordenada por nombre. <br>
     *
     * @param nombre es el nombre del aspirante que se va a buscar - nombre!=null
     * @return Se retorn� la posici�n del aspirante con el nombre dado. Si el aspirante no existe se retorn� -1.
     */
    public int buscarBinarioPorNombre(String nombre) {
        int posicion = -1;
        int ini = 0;
        int fin = aspirantes.size() - 1;
        while ( ini <= fin){
            int mid = ini + ( fin - ini) /2;
            if (aspirantes.get(mid).darNombre().compareTo(nombre) == 0) {
                posicion = mid;
                return posicion;
            }
            if (aspirantes.get(mid).darNombre().compareTo(nombre) < 0){
                ini = mid + 1;
            }
            else{
                fin = mid - 1;
            }

        }

        // TODO: Realizar el ejercicio correspondiente
        return -1;
        }


    /**
     * Busca el aspirante que tenga la menor edad en la bolsa.
     *
     * @return Se retorn� la posici�n donde se encuentra el aspirante m�s joven. Si no hay aspirantes en la bolsa se retorn� -1
     */
    public int buscarAspiranteMasJoven() {
        int posicion = -1;
        int masjoven = 100;

        for (int i = 0; i < aspirantes.size(); i++) {
            if (aspirantes.get(i).darEdad() < masjoven){
                masjoven=aspirantes.get(i).darEdad();
                posicion=i;
            }

        }
        return posicion;
        // TODO: Realizar el ejercicio correspondiente
    }

    /**
     * Busca el aspirante que tenga la mayor edad en la bolsa.
     *
     * @return Se retorn� la posici�n donde se encuentra el aspirante con m�s edad. Si no hay aspirantes en la bolsa se retorn� -1
     */
    public int buscarAspiranteMayorEdad() {
        int posicion = -1;
        int mayoredad = 0;

        for (int i = 0; i < aspirantes.size(); i++) {
            if (aspirantes.get(i).darEdad() > mayoredad){
                mayoredad=aspirantes.get(i).darEdad();
                posicion=i;
            }

        }
        return posicion;
        // TODO: Realizar el ejercicio correspondiente
    }

    /**
     * Busca el aspirante con m�s a�os de experiencia en la bolsa.
     *
     * @return Se retorn� la posici�n donde se encuentra el aspirante con mayor experiencia. Si no hay aspirantes en la bolsa se retorn� -1
     */
    public int buscarAspiranteMayorExperiencia() {
        int posicion = -1;
        int masexp = 0;

        for (int i = 0; i < aspirantes.size(); i++) {
            if (aspirantes.get(i).darAniosExperiencia() > masexp){
                masexp=aspirantes.get(i).darAniosExperiencia();
                posicion=i;
            }

        }
        // TODO: Realizar el ejercicio correspondiente

        return posicion;
    }

    /**
     * Contrata a un aspirante.<br>
     * <b>post: </b>Se elimin� el aspirante de la lista de aspirantes.
     *
     * @param nombre El nombre del aspirante a contratar - nombre!=null
     * @return Se retorn� true si el aspirante estaba registrado en la bolsa o false de lo contrario
     */
    public boolean contratarAspirante(String nombre) {
        boolean contratado = false;

        for (int i = 0; i < aspirantes.size(); i++) {
            if (aspirantes.get(i).darNombre().equals(nombre)){
               contratado = true;
               aspirantes.remove(i);
            }
        }
        // TODO: Realizar el ejercicio correspondiente

        return contratado;
    }

    /**
     * Elimina todos los aspirantes de la bolsa cuyos a�os de experiencia <br>
     * son menores a la cantidad de a�os especificada <br>
     *
     * @param aniosExperiencia La cantidad de a�os con relaci�n a la cual se van a eliminar los aspirantes. aniosExperiencia>=0
     * @return La cantidad de aspirantes que fueron eliminados
     */
    public int eliminarAspirantesPorExperiencia(int aniosExperiencia) {
        int eliminados = 0;

        int cont = 0;
        for (int i = 0; i < aspirantes.size(); i++) {
            if (aspirantes.get(i).darAniosExperiencia() < aniosExperiencia){
                eliminados++;

            }
        }
        aspirantes.removeIf(aspirantes -> aspirantes.darAniosExperiencia() < aniosExperiencia);
        // TODO: Realizar el ejercicio correspondiente

        return eliminados;
    }

}