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

package com.mycompany.ftp.gui;

import com.mycompany.excepciones.ProtocoloExcepcion;
import com.mycompany.ftp.Conector;
import com.mycompany.ftp.ConectorFTP;
import com.mycompany.ftp.ConectorFTPS;
import com.mycompany.ftp.DatosConexion;
import com.mycompany.ftp.GestorArchivos;
import com.mycompany.ftp.Trabajador;
import java.awt.Color;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;


public class VentanaFTP extends javax.swing.JFrame {

    //Velocidad a la que parpadea cuando se esta descargado o subiendo algo
    private final int VELOCIDAD_PARPADEO = 300;
    private Trabajador trabajadorParpadeo;
    
    //Guarda los datos de la conexion establecida con el servidor
    private DatosConexion datosConexion;
    
    //realiza todas las operaciones relacionadas con los archivos
    private GestorArchivos gestorArchivos;
    
 
    
    /**
     * Constructor de la ventana
     */
    public VentanaFTP() {
        
        /**
         * Inicializa los hilos que sean encesarios
         */
        inicializarTrabajadores();
        
        /**
         * Inicializa los componentes de la interfaz
         */
        initComponents();
        
        /**
         * Carga los datos previos de la ventana
         */
        cargarDatosPrevios();        
        
    }

    private void inicializarTrabajadores(){
        /**
         * Parpadea una luz cuando se descarga o se sube un archivo
         */
        trabajadorParpadeo = new Trabajador() {
            private Runnable blinker = new Runnable() {
                Color color = Color.green;

                @Override
                public void run() {
                    lblLuz.setBackground(color);
                    color = (color == Color.green) ? Color.red : Color.green;
                }
            };

            @Override
            public void run() {
                while (estaEjecutandose()) {
                    try {
                        SwingUtilities.invokeAndWait(blinker);
                        Thread.sleep(VELOCIDAD_PARPADEO);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(VentanaFTP.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InvocationTargetException ex) {
                        Logger.getLogger(VentanaFTP.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
                
    }
    
    /**
     * Carga los datos de los campos de la ultima ejecucion, se utiliza para no tener que escribir cada vez que se abra el programa
     * los datos en los campos
     */
    private void cargarDatosPrevios(){
        //cargamos el archivo de configuracion
        datosConexion = new DatosConexion();
        datosConexion.cargar();
        //rellena los campos de la interfaz
        cargarDatosInterfaz(datosConexion);
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblArchivoRemoto = new javax.swing.JLabel();
        lblArchivoLocal = new javax.swing.JLabel();
        tfArchivoRemoto = new javax.swing.JTextField();
        tfArchivoLocal = new javax.swing.JTextField();
        btnSubirAhora = new javax.swing.JButton();
        cbProtocolo = new javax.swing.JComboBox();
        btnDescargarAhora = new javax.swing.JButton();
        lblProtocolo = new javax.swing.JLabel();
        lblLuz = new javax.swing.JLabel();
        tfPassword = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        tfUsuario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tfPuerto = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tfServidor = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("FtpAutoUploader");
        setPreferredSize(new java.awt.Dimension(728, 500));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblArchivoRemoto.setText("Archivo remoto:");
        getContentPane().add(lblArchivoRemoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, -1, -1));

        lblArchivoLocal.setText("Archivo local:");
        getContentPane().add(lblArchivoLocal, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));
        getContentPane().add(tfArchivoRemoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 160, 470, -1));
        getContentPane().add(tfArchivoLocal, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 190, 470, -1));

        btnSubirAhora.setText("Subir");
        btnSubirAhora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubirAhoraActionPerformed(evt);
            }
        });
        getContentPane().add(btnSubirAhora, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 230, -1, -1));

        cbProtocolo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "FTP", "FTPS" }));
        cbProtocolo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbProtocoloActionPerformed(evt);
            }
        });
        getContentPane().add(cbProtocolo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, -1, -1));

        btnDescargarAhora.setText("Descargar");
        btnDescargarAhora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDescargarAhoraActionPerformed(evt);
            }
        });
        getContentPane().add(btnDescargarAhora, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, -1, -1));

        lblProtocolo.setText("Protocolo:");
        getContentPane().add(lblProtocolo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, 20));

        lblLuz.setBackground(new java.awt.Color(255, 0, 0));
        lblLuz.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lblLuz.setOpaque(true);
        lblLuz.setPreferredSize(new java.awt.Dimension(15, 15));
        getContentPane().add(lblLuz, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 30, 20, 20));
        getContentPane().add(tfPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(101, 87, 88, -1));

        jLabel1.setText("Password:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 89, -1, -1));
        getContentPane().add(tfUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(101, 62, 88, -1));

        jLabel2.setText("Usuario:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 64, -1, -1));
        getContentPane().add(tfPuerto, new org.netbeans.lib.awtextra.AbsoluteConstraints(101, 37, 39, -1));

        jLabel4.setText("Puerto:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 39, -1, -1));
        getContentPane().add(tfServidor, new org.netbeans.lib.awtextra.AbsoluteConstraints(101, 12, 143, -1));

        jLabel5.setText("Servidor:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 14, -1, -1));

        setBounds(0, 0, 591, 289);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Sube un archivo de local al servidor
     */
    private void btnSubirAhoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubirAhoraActionPerformed
        //Escribe en el fichero de configuracion los valores de los campos
        datosConexion = new DatosConexion(tfServidor, tfPuerto, tfUsuario, tfPassword, tfArchivoRemoto, tfArchivoLocal, cbProtocolo);
        datosConexion.guardar();

        //Se crea un hilo para ejecutar la subida del archivo
        Trabajador trabajador = new Trabajador() {
            @Override
            public void run() {
                try {
                    //Elegimos el protocolo utilizado para comunicarnos con el servidor
                    Conector conector = null;
                    switch (datosConexion.getProtocolo()) {
                        case "FTP":
                            conector = new ConectorFTP(datosConexion);
                            break;
                        case "FTPS":
                            conector = new ConectorFTPS(datosConexion);
                            break;
                        default:
                            //Lanzamos una excepcion porque el protocolo elegido no es correcto
                            throw new ProtocoloExcepcion("Protocolo incorrecto.");
                    }

                    //activamos el efecto de parpadeo
                    parpadearLuz(); 
                    //conectamos con el servidor FTP
                    conector.conectar();
                    //Descargamos el archivo a local
                    conector.subirArchivo(datosConexion.getArchivoLocal(), datosConexion.getArchivoRemoto());
                    //Desconectamos del servidor
                    conector.desconectar();
                    //desactivamos el efecto de parpadeo
                    apagarParpadeo();
                } catch (ProtocoloExcepcion ex) {
                    Logger.getLogger(VentanaFTP.class.getName()).log(Level.SEVERE, null, ex);
                }
                //terminamos el hilo de subida
                terminar();
            }
        };
        //arrancamos el hilo de subida
        trabajador.trabajar();
    }//GEN-LAST:event_btnSubirAhoraActionPerformed

    private void cbProtocoloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbProtocoloActionPerformed
    }//GEN-LAST:event_cbProtocoloActionPerformed

    /**
     * Descarga un archivo del servidor a local
     */
    private void btnDescargarAhoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDescargarAhoraActionPerformed
        //Escribe en el fichero de configuracion los valores de los campos
        datosConexion = new DatosConexion(tfServidor, tfPuerto, tfUsuario, tfPassword, tfArchivoRemoto, tfArchivoLocal, cbProtocolo);
        datosConexion.guardar();
            
        Trabajador trabajador = new Trabajador() {
            @Override
            public void run() {
                try {
                    Conector conector = null;
                    switch (datosConexion.getProtocolo()) {
                        case "FTP":
                            conector = new ConectorFTP(datosConexion);
                            break;
                        case "FTPS":
                            conector = new ConectorFTPS(datosConexion);
                            break;
                        default:
                            //Lanzamos una excepcion porque el protocolo elegido no es correcto
                            throw new ProtocoloExcepcion("Protocolo incorrecto.");
                    }
                    //activamos el efecto de parpadeo
                    parpadearLuz(); 
                    //conectamos con el servidor FTP
                    conector.conectar(); 
                    //Descargamos el archivo a local
                    conector.descargarArchivo(datosConexion.getArchivoRemoto(), datosConexion.getArchivoLocal()); 
                    //Desconectamos del servidor
                    conector.desconectar(); 
                    //desactivamos el efecto de parpadeo
                    apagarParpadeo();
                } catch (ProtocoloExcepcion ex) {
                    Logger.getLogger(VentanaFTP.class.getName()).log(Level.SEVERE, null, ex);
                }
                //terminamos el hilo de descarga
                terminar();
            }

        };
        //arranca el hilo de descarga
        trabajador.trabajar();  
    }//GEN-LAST:event_btnDescargarAhoraActionPerformed

    /**
     * Cuando se esta subiendo o descargado un archivo se pondra una luz a "parpadear" para que el usuario vea que algo esta pasando
     */
    private void parpadearLuz() {
        trabajadorParpadeo.trabajar();
    }
    
    /**
     * Apaga el efecto del parpadeo
     */
    private void apagarParpadeo(){
        trabajadorParpadeo.terminar();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaFTP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaFTP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaFTP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaFTP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaFTP().setVisible(true);
            }
        });
    }
    
    //escribimos los valores en los campos de la interfaz
    private void cargarDatosInterfaz(DatosConexion datosConexion) {
        tfServidor.setText( datosConexion.getServidor() != null ? datosConexion.getServidor() : "" );
        tfPuerto.setText( datosConexion.getPuerto()!= null ? datosConexion.getPuerto(): "" );
        tfUsuario.setText( datosConexion.getUsuario()!= null ? datosConexion.getUsuario(): "" );
        tfPassword.setText( datosConexion.getPassword()!= null ? datosConexion.getPassword(): "" );
        tfArchivoRemoto.setText( datosConexion.getArchivoRemoto()!= null ? datosConexion.getArchivoRemoto(): "" );
        tfArchivoLocal.setText( datosConexion.getArchivoLocal()!= null ? datosConexion.getArchivoLocal(): "" );
        cbProtocolo.setSelectedItem(datosConexion.getProtocolo()!= null ? datosConexion.getProtocolo(): "FTP" );
    }    
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDescargarAhora;
    private javax.swing.JButton btnSubirAhora;
    private javax.swing.JComboBox cbProtocolo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel lblArchivoLocal;
    private javax.swing.JLabel lblArchivoRemoto;
    private javax.swing.JLabel lblLuz;
    private javax.swing.JLabel lblProtocolo;
    private javax.swing.JTextField tfArchivoLocal;
    private javax.swing.JTextField tfArchivoRemoto;
    private javax.swing.JTextField tfPassword;
    private javax.swing.JTextField tfPuerto;
    private javax.swing.JTextField tfServidor;
    private javax.swing.JTextField tfUsuario;
    // End of variables declaration//GEN-END:variables


}
