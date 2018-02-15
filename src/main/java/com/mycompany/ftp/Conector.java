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


public abstract class Conector {
    
    //Los datos para poder conectarse con el servidor
    private DatosConexion datosConexion;
            
    
    //Conecta con el servidor FTP/FTPS
    public abstract boolean conectar();
    
    /**
     *
     * @param rutaRemota: ruta donde esta el archivo que nos queremos descargar
     * del servidor
     * @param rutaLocal: ruta donde queremos guardar el archivo una vez que lo
     * hayamos descargado
     * @return
     */  
    public abstract boolean descargarArchivo(String rutaRemota, String rutaLocal);
    
    //Sube un archivo a la ruta que se le indique
    public abstract boolean subirArchivo(String rutaLocal, String rutaRemota);
    
    //Desconecta del servidor
    public abstract boolean desconectar();
    
    //Get y Set 
    public void setDatosConexion(DatosConexion datosConexion){
        this.datosConexion = datosConexion;
    }
    public DatosConexion getDatosConexion(){
        return this.datosConexion;
    }
    
}
