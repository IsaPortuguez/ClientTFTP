/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientetftp;

import Cliente.Cliente;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author Isabel
 */
public class ClienteTFTP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        String ip = JOptionPane.showInputDialog(null, "Ingrese la ip");
        Cliente myClient = new Cliente(69, ip);
        myClient.start();
    }
    
}
