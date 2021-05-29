/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Domain.User;
import Utility.MyUtility;
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
public class Client extends Thread {
    
	private int socketPortNumber;
	private PrintStream send;
	private BufferedReader receive;
	private Socket socket;
	private boolean clienteConectado;

	public Client(int socketPortNumber, String address) throws IOException {
		this.socketPortNumber = socketPortNumber;
		this.clienteConectado = true;
		this.socket = new Socket(address, this.socketPortNumber);
		this.send = new PrintStream(this.socket.getOutputStream());
		this.receive = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
	}// constructor
        
        @Override
	public void run() {
            this.send.println("Soy el Cliente");
            try {
                String elementoString = this.receive.readLine();
                System.out.println("Recibido " + elementoString);
            } catch (IOException ex) {
                System.out.println("Client.Client.run() "+ex);
//                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
            while (true){
                
                try {
                    this.sleep(500);
                } catch (InterruptedException ex) {
                    System.out.println("Client.Client.run() "+ex);
//                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            } // while

	}// run
        
        public String checkInUser(String username, String password) throws IOException {
            
                this.send.println(MyUtility.ADDNEWUSER);
		this.send.println(username);
                this.send.println(password);
                String string = receive.readLine();
                
                return string;
	}

	public String logInUser(String username, String password) throws IOException {
            
                this.send.println(MyUtility.LOGINUSER);
		this.send.println(username);
                this.send.println(password);
		String string = receive.readLine();

		return string;
	}
        
}
