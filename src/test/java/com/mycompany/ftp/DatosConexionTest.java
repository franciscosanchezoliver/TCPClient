
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

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class DatosConexionTest {
    
    public DatosConexionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testEsNull() {
        System.out.println("esNull");
        Object objeto = null;
        DatosConexion datosConexion = new DatosConexion();
        assertTrue(datosConexion.esNull(objeto));
    }

    @Test
    public void testNoEsNull() {
        System.out.println("noEsNull");
        Object objeto = "tengo valor";
        DatosConexion datosConexion = new DatosConexion();
        assertTrue(datosConexion.noEsNull(objeto));
    }

    @Test
    public void testEsVacio() {
        System.out.println("esVacio");
        Object objeto = "";
        DatosConexion datosConexion = new DatosConexion();
        assertTrue(datosConexion.esVacio(objeto));
    }

    @Test
    public void testNoEsVacio() {
        System.out.println("noEsVacio");
        Object objeto = "no estoy vacio";
        DatosConexion datosConexion = new DatosConexion();
        assertTrue(datosConexion.noEsVacio(objeto));
    }

    @Test
    public void testTieneValor() {
        System.out.println("tieneValor");
        Object objeto = 21;
        DatosConexion datosConexion = new DatosConexion();
        assertTrue(datosConexion.tieneValor(objeto));
        objeto = "tengo valor";
        assertTrue(datosConexion.tieneValor(objeto));
    }

    @Test
    public void testNoTieneValor() {
        System.out.println("noTieneValor");
        Object objeto = null;
        DatosConexion datosConexion = new DatosConexion();
        assertTrue(datosConexion.noTieneValor(objeto));
        objeto = "";
        assertTrue(datosConexion.noTieneValor(objeto));
    }
    
}
