package comunicacionentreprocesos;

import java.io.*;
import java.util.Map;
import java.util.Set;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import com.sun.management.UnixOperatingSystemMXBean;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Ejercicio 3
 * En cualquier lenguaje de programación donde sea permitido, 
 * utilizar llamadas al sistema para realizar las siguientes acciones.
 * 1. Abrir un archivo.
 * 2. Leer un archivo.
 * 3. Obtener el número máximo de descriptores de archivos permitidos.
 * 4. Cambiar el límite máximo.
 * 5. Obtener varibles de entorno del sistema.
 * 6. Iniciar un proceso nuevo.
 * 7. Bifurcar el proceso actual.
 * 8. Obtener el PID y nombre del proceso actual y del proceso bifurcado.
 * 9. Bloquear un proceso.
 * 10. Destruir un proceso.
 * @author Gabriela Sandoval Cruz
 */
public class ComunicacionEntreProcesos {

    public static void main(String[] args) {
        String mensaje = "Llamadas al sistema en java";
        String textoArchivo = "";

        try
        {
            File archivo = new File("ComunicacionEntreProcesos.txt");
            FileWriter escribir = new FileWriter(archivo,true);
            escribir.write(mensaje);
            escribir.close();

            FileReader lector = new FileReader("ComunicacionEntreProcesos.txt");
            BufferedReader contenido = new BufferedReader(lector);
            
            System.out.println("----------Archivo-----------");
            while((textoArchivo = contenido.readLine())!= null)
            {
                System.out.println(textoArchivo);
            }
        } catch(Exception e)
        {
            System.out.println("Error");
        }

        ArchivosDescriptivos();
        VariablesDeEntorno();
        //Proceso();
    }

    public static void ArchivosDescriptivos() {
        System.out.println("----------Archivos descriptivos-----------");
        OperatingSystemMXBean os = ManagementFactory.getOperatingSystemMXBean();
        if (os instanceof UnixOperatingSystemMXBean) {
            System.out.println("El número máximo de archivos descriptores es: "
               + ((UnixOperatingSystemMXBean) os).getMaxFileDescriptorCount());
        }
    }

    public static void VariablesDeEntorno(){
        String user = System.getProperty("user");
        String password = System.getProperty("password");        
        
        System.out.println("----------Variables de entorno del sistema-----------");
        System.out.println("user = "+user);
        System.out.println("password = "+password);

        String myVar = System.getenv().get("MI_VARIABLE");
        System.out.println("MI_VARIABLE="+myVar);
     
        Map<String,String> env = System.getenv();
        Set<String> keys = env.keySet();
        for (String key: keys){
           System.out.println(key + " = "+env.get(key));
        }
    }

//    public static void Proceso() throws IOException, InterruptedException {
//        ProcessBuilder processBuilder = new ProcessBuilder("/opt/google/chrome/chrome");
//        Process process1 = processBuilder.start();
//        long pid1 = process1.pid();
//        System.out.println(pid1);
//
//        Process process2 = Runtime.getRuntime().exec("/opt/google/chrome/chrome");
//        long pid2 = process2.pid();
//        System.out.println(pid2);
//
//        process2.destroy();
//        process2.waitFor();
//   }
}
