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

import com.mycompany.ftp.gui.VentanaFTP;
import java.io.IOException;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DatosConexion {

    private String servidor;
    private String puerto;
    private String usuario;
    private String password;
    private String archivoRemoto;
    private String archivoLocal;
    private String protocolo;    
    
    private GestorArchivos gestorArchivos = new GestorArchivos();
    
    //Ruta del archivo de configuracion
    private final String RUTA_DIRECTORIO = System.getProperty("user.home") + "\\Documents\\FTPAutoloader";
    private final String RUTA_ARCHIVO =  RUTA_DIRECTORIO +"\\configuracion.txt";     

    public DatosConexion(){
    }
    
    public DatosConexion(String servidor, String puerto, String usuario, String password, String archivoRemoto, String archivoLocal, String protocolo) {
        this.servidor = servidor;
        this.puerto = puerto;
        this.usuario = usuario;
        this.password = password;
        this.archivoRemoto = archivoRemoto;
        this.archivoLocal = archivoLocal;
        this.protocolo = protocolo;
    }
    /**
     * Constructor que recibe elementos de la interfaz como parametros
     */
    public DatosConexion (JTextField servidor , JTextField puerto, JTextField usuario, JTextField password, JTextField archivoRemoto, 
            JTextField archivoLocal, JComboBox protocolo){
        this.servidor = (String) (tieneValor(servidor.getText()) ? servidor.getText() : null);
        this.puerto = (String) (tieneValor(puerto.getText()) ? puerto.getText() : null);
        this.usuario = (String) (tieneValor(usuario.getText()) ? usuario.getText() : null);
        this.password = (String) (tieneValor(password.getText()) ? password.getText() : null);
        this.archivoRemoto = (String) (tieneValor(archivoRemoto.getText()) ? archivoRemoto.getText() : null);
        this.archivoLocal = (String) (tieneValor(archivoLocal.getText()) ? archivoLocal.getText() : null);
        this.protocolo = (String) (tieneValor(protocolo.getSelectedItem().toString()) ? protocolo.getSelectedItem().toString() : null);
    }    
    
    /**
     * Crea el directorio y el archivo de configuracion en caso de que no existan previamente
     */    
    public void crearArchivoConfiguracion(){
        //si no existe el directorio entonces lo creamos
        if(gestorArchivos.noExisteDirectorio(RUTA_DIRECTORIO))
            gestorArchivos.crearDirectorio(RUTA_DIRECTORIO);
        
        //si no existe el archivo de configuracion entonces lo creamos
        if(gestorArchivos.noExisteArchivo(RUTA_ARCHIVO))
            gestorArchivos.crearArchivo(RUTA_ARCHIVO);        
    }
    
    /**
     * Carga el contenido del archivo de configuracion
     */
    public void cargar(){
        String contenido = gestorArchivos.leerArchivo(this.RUTA_ARCHIVO);
        JSONParser parser = new JSONParser();
        Object obj;
        try {
            obj = parser.parse(contenido);
            JSONObject array = (JSONObject)obj;
            
            this.servidor = (String) (tieneValor(array.get("servidor")) ? array.get("servidor") : null);
            this.puerto = (String) (tieneValor(array.get("puerto")) ? array.get("puerto") : null);
            this.usuario = (String) (tieneValor(array.get("usuario")) ? array.get("usuario") : null);
            this.password = (String) (tieneValor(array.get("password")) ? array.get("password") : null);
            this.archivoRemoto = (String) (tieneValor(array.get("archivo_remoto")) ? array.get("archivo_remoto") : null);
            this.archivoLocal = (String) (tieneValor(array.get("archivo_local")) ? array.get("archivo_local") : null);
            this.protocolo = (String) (tieneValor(array.get("protocolo")) ? array.get("protocolo") : null);
        } catch (ParseException e) {
            System.err.println("Error al parsear el archivo de configuracion.");
            e.printStackTrace();
        }                
    }    
    
    /**
     * Escribe en disco la ultima configuracion ejecutada en formato JSON
     */
    public void guardar() {
        JSONObject obj = new JSONObject();
        obj.put("servidor", this.servidor);
        obj.put("puerto" , this.puerto);
        obj.put("usuario", this.usuario);
        obj.put("password", this.password);
        obj.put("archivo_remoto", this.archivoRemoto);
        obj.put("archivo_local", this.archivoLocal);
        obj.put("protocolo", this.protocolo);
        
        StringWriter out = new StringWriter();
        try {
            obj.writeJSONString(out);
        } catch (IOException ex) {
            Logger.getLogger(VentanaFTP.class.getName()).log(Level.SEVERE, null, ex);
        }
        String jsonText = out.toString();
        gestorArchivos.escribirArchivo(jsonText, RUTA_ARCHIVO);    
    }       
    
    
    /**
     * COMPARACIONES
     */
    public boolean esNull(Object objeto){
        return objeto == null;
    }
    
    public boolean noEsNull(Object objecto){
        return !esNull(objecto);
    }
    
    public boolean esVacio(Object objecto){
        return objecto.equals("");
    }
    
    public boolean noEsVacio(Object objeto){
        return !esVacio(objeto);
    }
    
    //Tendra valor si no es null ni ""
    public boolean tieneValor(Object objeto){
        return noEsNull(objeto) && noEsVacio(objeto);
    }
    
    public boolean noTieneValor(Object objeto){
        return !tieneValor(objeto);
    }    
    
    
    /**
     * GETS Y SETS
     */

    public String getServidor() {
        return servidor;
    }

    public void setServidor(String servidor) {
        this.servidor = servidor;
    }

    public String getPuerto() {
        return puerto;
    }

    public void setPuerto(String puerto) {
        this.puerto = puerto;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getArchivoLocal() {
        return archivoLocal;
    }

    public String getArchivoRemoto() {
        return archivoRemoto;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setArchivoLocal(String archivoLocal) {
        this.archivoLocal = archivoLocal;
    }

    public void setArchivoRemoto(String archivoRemoto) {
        this.archivoRemoto = archivoRemoto;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }
    
    
    
}
