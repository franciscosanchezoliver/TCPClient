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

public abstract class Trabajador implements Runnable{

    private Thread hilo;
    private boolean ejecutandose ;

    /**
     * Sobrescribe lo que quieres que haga el hilo
     */
    @Override
    public abstract void run();
    
    /**
     * Comienza el hilo
     */
    public void trabajar(){
        //System.out.println("Empezando a trabajar " + getClass().getName());
        this.hilo = new Thread(this);
        this.ejecutandose = true;
        this.hilo.start();
    }
    
    public void terminar(){
        boolean parar = true;
        this.ejecutandose = false;
        
        //intentamos parar el hilo
        while(parar){
            try{
                //System.out.println("Parando de trabajar " + getClass().getName());
                this.hilo.join(); //para el hilo
                parar = false;
            }catch(InterruptedException e){
                System.err.println("Error al parar el trabajador " + getClass().getName());
                e.printStackTrace();
                break;
            }
        }
    }
    
    public boolean estaEjecutandose(){
        return this.ejecutandose;
    }
    
}
