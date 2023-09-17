package org.infantaelena.ies.ad;

import java.io.File;
import java.io.IOException;

public class ejercicio1_1 {

    public static void main(String[] args) {



    }

    public static String[] listarDirectorio(){
        // Crea un método listarDirectorio() que devuelva una array con el listado del contenido
        // (archivos y carpetas) de directorio actual. ¿Este método debería ser dinámico o estático? ¿Por qué?

        String userDir = System.getProperty("user.dir");
        File directorioActual = new File(userDir);

        String[] lista = null;

        try {
            lista = directorioActual.list();
        }catch (SecurityException e){
            System.err.println("Fallo en la ejecución por falta de permisos de lectura.");
        }

        //Me tomo la libertad de escribir un mensaje de aviso en caso de que esté vacío
        if (lista == null){
            System.out.println("El directorio está vacío");
        }

        return lista;
    }

    public static String[] listarDirectorio(String directorio){
        //Crea un método listarDirectorio(String directorio) que devuelva una array con el listado del contenido del
        //directorio indicado como argumento siempre y cuando este sea un directorio y no un archivo. Pruébalo
        //pasándole al menos una ruta absoluta y una relativa. ¿Qué devolvería en caso de que la ruta que nos
        //proporcionan no se correspondiera con un directorio?

        File dir = new File(directorio);

        String[] lista = null;

        try {
            lista = dir.list();
        }catch (SecurityException e){
            System.err.println("Fallo en la ejecución por falta de permisos de lectura.");
        }

        /*
        Código inicial. Resulta redundante después de leer la documentación. List ya devuelve null si no es un directorio.
        if (file.isDirectory()){
            return file.list();
        }else{
            return null;
        }*/

        //Me tomo la libertad de escribir un mensaje de aviso en caso de que esté vacío
        if (lista == null){
            System.out.println("El directorio está vacío");
        }

        return lista;
    }

    public static boolean existeFichero(String directorio, String fichero){
        //Crea un método existeFichero(String directorio, String fichero) que compruebe si existe dicho fichero en el
        //directorio indicado.

        String[] ficheros = listarDirectorio(fichero);

        if (ficheros != null){
            for (String str : ficheros){
                if (fichero.equals(str)){
                    return true;
                }
            }
        }

        return false;
    }

    public static void generarArchivo(String directorio){
        //Crea un método generarArchivo que a partir de una ruta que se le pase como argumento, cree un archivo txt con
        // nombre TunombreTuapellido en la ruta en la que se le ha proporcionado. Presta atención a los posibles errores
        // que puedan darse. ¿Qué pasa si la ruta no existe? ¿Puedes solucionarlo?

        File dir = new File(directorio);

        if (!dir.exists()){
            if (dir.mkdirs()){
                System.out.println("El directorio no existe y no se puede crear.");
                return;
                //Me pareció demasiado lanzar la excepción
                //throw new RuntimeException();
            }
        }

        File fichero = new File(dir, "DavidAyllon.txt");

        try {
            if (fichero.createNewFile()){
                System.out.println("Fichero creado");
            }else{
                System.out.println("El fichero ya existe");
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (SecurityException e){
            System.err.println("Fallo en la ejecución por falta de permisos de lectura.");
        }

    }
    public static void renombrarArchivo(String path){
        //Crea un método renombrarArchivo que coja un archivo cuyo path absoluto se le pase como argumento y lo renombre
        // añadiendo delante DAM2. Pruébalo con el archivo creado en el ejercicio anterior. El archivo antiguo, ¿desaparece?


        File fichero = new File(path);
        File ficheroRenombrado = new File(fichero.getParent(), "DAM2_"+fichero.getName());

        fichero.renameTo(ficheroRenombrado);
    }

    public static void borrarArchivo(String path){
        //Crea un método que se llame borrarArchivo que reciba un path absoluto y elimine el archivo indicado.
        //Pruébalo con el archivo del ejercicio anterior. En la clase File hay métodos para cambiar los atributos del
        //archivo. Prueba a modificar el método haciendo el archivo de solo lectura antes de eliminarlo.
        //¿Qué sucede? ¿Por qué?

        File fichero = new File(path);

        fichero.delete();

    }

    public static void eliminarDirectorio(String ruta){
        //Crea un método eliminarDirectorio que reciba una ruta y elimine el directorio indicado por ella.
        //¿Elimina directorios con contenido? ¿Cómo se puede solucionar? Modifica el método para que elimine directorios
        //que contengan solo archivos o estén vacíos e indique que no puede hacerlo si contienen otros directorios.



    }

    //Crea una clase que herede de java.io.File y le añada un método que se llame showInfo(). Este método devolverá un
    //String con la siguiente información del fichero:
    //Nombre.
    //Ruta.
    //Ruta absoluta.
    //¿Se puede leer?
    //¿Se puede escribir?
    //Tamaño.
    //¿Es un directorio? En caso afirmativo mostrará los contenidos del mismo.
    //¿Es un fichero?
    //Cada elemento debe ir en una línea y llevar delante un texto que indique a qué
    //se refiere.



}

