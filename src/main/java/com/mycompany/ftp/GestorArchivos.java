/**
 * 
                                              ...
                                            ;::::;
                                          ;::::; :;
                                        ;:::::'   :;
                                       ;:::::;     ;.
                                      ,:::::'       ;           OOO\
                                      ::::::;       ;          OOOOO\
                                      ;:::::;       ;         OOOOOOOO
                                     ,;::::::;     ;'         / OOOOOOO
                                   ;:::::::::`. ,,,;.        /  / DOOOOOO
                                 .';:::::::::::::::::;,     /  /     DOOOO
                                ,::::::;::::::;;;;::::;,   /  /        DOOO
                               ;`::::::`'::::::;;;::::: ,#/  /          DOOO
                               :`:::::::`;::::::;;::: ;::#  /            DOOO
                               ::`:::::::`;:::::::: ;::::# /              DOO
                               `:`:::::::`;:::::: ;::::::#/               DOO
                                :::`:::::::`;; ;:::::::::##                OO
                                ::::`:::::::`;::::::::;:::#                OO
                                `:::::`::::::::::::;'`:;::#                O
                                 `:::::`::::::::;' /  / `:#
                                  ::::::`:::::;'  /  /   `#

 */



package com.mycompany.ftp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GestorArchivos {
    
    LectorFichero lector; //utilizado para leer archivos
    EscritorFichero escritor; //utilizado para escribir archivos
    
    /**
     * Lee un fichero de texto y devuelve su contenido
     */
    public String leerArchivo(String ruta){
        lector = new LectorFichero(ruta);
        return lector.leer();
    }
    /**
     * Escribe en un archivo el mensaje que se le pase
     */    
    public void escribirArchivo(String mensaje , String ruta){
        escritor = new EscritorFichero();
        escritor.escribir(mensaje, ruta);
    }
    
    public void crearDirectorio(String ruta) {
        if(noExisteDirectorio(ruta)){
            File theDir = new File(ruta);
            theDir.mkdir();
        }
    }
    
    public boolean existeDirectorio(String ruta){
        File f = new File(ruta);
        return f.exists() && f.isDirectory();
    }
    
    public boolean noExisteDirectorio(String ruta){
        return !existeDirectorio(ruta);
    }
    
    public void crearArchivo(String ruta) {
        PrintWriter writer;
        try {
            writer = new PrintWriter(ruta, "UTF-8");
            writer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GestorArchivos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(GestorArchivos.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    public boolean existeArchivo(String ruta) {
        File f = new File(ruta);
        return f.exists() && !f.isDirectory();
    }
    
    public boolean noExisteArchivo(String ruta){
        return !existeArchivo(ruta);
    }
    

}
