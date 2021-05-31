/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Domain.User;
import Utility.MyUtility;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
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
        private DataOutputStream dataOutputStream = null;
        private DataInputStream dataInputStream = null;
        private BufferedOutputStream bufferedOutputStream = null;
        private BufferedInputStream bufferedInputStream = null;
        private String[] files;

	public Client(int socketPortNumber, String address) throws IOException {
		this.socketPortNumber = socketPortNumber;
		this.clienteConectado = true;
		this.socket = new Socket(address, this.socketPortNumber);
                this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
                this.dataInputStream = new DataInputStream(socket.getInputStream());
                this.bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());
                this.bufferedInputStream = new BufferedInputStream(socket.getInputStream());
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
                System.out.println("Client.Client.run() "+ex.toString());
//                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
            while (true){
                
                try {
                    this.sleep(500);
                } catch (InterruptedException ex) {
                    System.out.println("Client.Client.run() "+ex.toString());
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

        public void sendImageName(String imageName){
            
            this.send.println(MyUtility.DESCARGARDESERVER);
            this.send.println(imageName);
            
        }
        
        public void sendImage(){
            
            this.send.println(MyUtility.GUARDARENSERVER);
            
        }
        
        public String[] getDirectories() throws IOException {
        this.send.println(MyUtility.OBTENERDIRECTORIOS);
        String size = this.receive.readLine();
        files = new String[Integer.parseInt(size)];
        for (int i = 0; i < Integer.parseInt(size); i++) {
            files[i] = this.receive.readLine();
        }
        return files;
    }
        
        public DataOutputStream getDataOutputStream() {
            return dataOutputStream;
        }

        public void setDataOutputStream(DataOutputStream dataOutputStream) {
            this.dataOutputStream = dataOutputStream;
        }

        public BufferedOutputStream getBufferedOutputStream() {
            return bufferedOutputStream;
        }

        public void setBufferedOutputStream(BufferedOutputStream bufferedOutputStream) {
            this.bufferedOutputStream = bufferedOutputStream;
        }

        public DataInputStream getDataInputStream() {
            return dataInputStream;
        }

        public void setDataInputStream(DataInputStream dataInputStream) {
            this.dataInputStream = dataInputStream;
        }

        public BufferedInputStream getBufferedInputStream() {
            return bufferedInputStream;
        }

        public void setBufferedInputStream(BufferedInputStream bufferedInputStream) {
            this.bufferedInputStream = bufferedInputStream;
        }
        
        
        
}
