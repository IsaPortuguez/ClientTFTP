/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.lang.model.element.Element;

/**
 *
 * @author Isabel
 */
public class Cliente extends Thread {
    
	private int socketPortNumber;
	private PrintStream send;
	private BufferedReader receive;
	private Socket socket;
	private boolean clienteConectado;

	public Cliente(int socketPortNumber, String address) throws IOException {
		this.socketPortNumber = socketPortNumber;
		this.clienteConectado = true;
		this.socket = new Socket(address, this.socketPortNumber);
		this.send = new PrintStream(this.socket.getOutputStream());
		this.receive = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
	}// constructor
        
        @Override
	public void run() {
            this.send.println("Soy el Cliente");
		while (true) {
			
                    try {
                        String elementoString = this.receive.readLine();
                        System.out.println("Recibido "+elementoString);
                    } catch (IOException ex) {
                        Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        
		} // while

	}// run
        
}
