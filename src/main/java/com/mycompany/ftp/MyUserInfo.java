
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


import com.jcraft.jsch.UserInfo;

/**
 *  Esta clase es necesaria para poder usar la libreria JSCH
 * 
 * @author Death
 */
class MyUserInfo implements UserInfo {

    private DatosConexion datosConexion;
    
    public MyUserInfo(DatosConexion datosConexion) {
        this.datosConexion = datosConexion;
    }

    @Override
    public String getPassphrase() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.datosConexion.getPassword();
    }

    @Override
    public boolean promptPassword(String string) {
        return true;
    }

    @Override
    public boolean promptPassphrase(String string) {
        return true;
    }

    @Override
    public boolean promptYesNo(String string) {
        return true;
    }

    @Override
    public void showMessage(String string) {
       
    }
    
}
