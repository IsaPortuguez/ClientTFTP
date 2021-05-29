/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienttftp;

import Client.Client;
import GUI.MainWindow;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author Isabel
 */
public class ClientTFTP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        String ip = JOptionPane.showInputDialog(null, "Ingrese la ip");
        Client myClient = new Client(69, ip);
        myClient.start();
        MainWindow window = new MainWindow(myClient);
    }
    
}
