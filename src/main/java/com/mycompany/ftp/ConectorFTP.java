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

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class ConectorFTP extends Conector {

    private FTPClient ftp;

    public ConectorFTP(DatosConexion datosConexion) {
        super.setDatosConexion(datosConexion);
        ftp = new FTPClient();
    }

    @Override
    public boolean conectar() {
        boolean conectado;
        String servidor = super.getDatosConexion().getServidor();
        String puerto = super.getDatosConexion().getPuerto();
        String usuario = super.getDatosConexion().getUsuario();
        String clave = super.getDatosConexion().getPassword();
        try {
            ftp.connect(servidor, Integer.parseInt(puerto));
            ftp.login(usuario, clave);
            conectado = true;
        } catch (IOException ex) {
            System.err.println("Error al conectar con el servidor");
            Logger.getLogger(ConectorFTP.class.getName()).log(Level.SEVERE, null, ex);
            conectado = false;
        }
        return conectado;
    }

    @Override
    public boolean descargarArchivo(String rutaRemota, String rutaLocal) {
        boolean descargado;
        try {
            ftp.enterLocalPassiveMode();
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            File archivoDescargado = new File(rutaLocal);
            OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(archivoDescargado));
            descargado = ftp.retrieveFile(rutaRemota, outputStream);
            outputStream.close();
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
            ftp.enterLocalPassiveMode();
            ftp.setFileType(FTP.BINARY_FILE_TYPE);


            File firstLocalFile = new File(rutaLocal);
 
            String firstRemoteFile = rutaRemota;
            InputStream inputStream = new FileInputStream(firstLocalFile);
 
            System.out.println("Start uploading first file");
            boolean done = ftp.storeFile(firstRemoteFile, inputStream);
            inputStream.close();
            if (done) {
                System.out.println("The first file is uploaded successfully.");
            }
                    
            subido = true;           
        } catch (IOException ex) {
            System.err.println("Error al subir el archivo.");
            ex.printStackTrace();
            subido = false;
        } 
        
        return subido;
    }

    @Override
    public boolean desconectar() {
        boolean desconectado = false;
        if (ftp.isConnected()) {
            try {
                ftp.logout();
                ftp.disconnect();
                desconectado = true;
            } catch (Exception e) {
                System.err.println("Error al desconectar.");
                e.printStackTrace();
                desconectado = false;
            }
        }
        return desconectado;
    }

}
