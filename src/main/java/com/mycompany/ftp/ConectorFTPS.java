
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

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConectorFTPS extends Conector{

    private JSch ftps;
    private Session session;
    private Channel canal;
    private ChannelSftp canalSFTP;
    
    public ConectorFTPS(DatosConexion datosConexion) {
        super.setDatosConexion(datosConexion);
        ftps = new JSch();
    }
    
    @Override
    public boolean conectar() {
        boolean conectado;
        try {
            String servidor = super.getDatosConexion().getServidor();
            String usuario = super.getDatosConexion().getUsuario();
            String clave = super.getDatosConexion().getPassword();
            session = ftps.getSession(usuario, servidor);
            {
                UserInfo ui = new MyUserInfo(super.getDatosConexion());
                session.setUserInfo(ui);
                session.setPassword(clave);
            }
            session.connect();
            canal = session.openChannel("sftp");
            canal.connect();
            conectado = true;
        } catch (JSchException ex) {
            System.err.println("Error al conectar con el servidor");
            Logger.getLogger(ConectorFTPS.class.getName()).log(Level.SEVERE, null, ex);
            conectado = false;
        }
        return conectado;
    }

    @Override
    public boolean descargarArchivo(String rutaRemota, String rutaLocal) {
        boolean descargado = false;
        try {
            canalSFTP = (ChannelSftp) canal;
            canalSFTP.get(rutaRemota, rutaLocal);
            descargado = true;            
        } catch (Exception e) {
            System.err.println("Error al descargar el archivo.");
            e.printStackTrace();
            descargado = false;
        }
        return descargado;
    }
    
    @Override
    public boolean subirArchivo(String rutaLocal, String rutaRemota) {
        boolean subido = false;
        try {
            canalSFTP = (ChannelSftp) canal;
            canalSFTP.put(rutaLocal, rutaRemota);
            subido = true;            
        } catch (Exception e) {
            System.err.println("Error al descargar el archivo.");
            e.printStackTrace();
            subido = false;
        }
        return subido;
    }

    @Override
    public boolean desconectar() {
        canalSFTP.exit();
        session.disconnect();
        return true;
    }


    
}
