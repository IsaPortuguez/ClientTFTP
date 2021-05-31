package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Isabel
 */
public class JIFImages extends JInternalFrame implements ActionListener{
    
    private JLabel jlbDownload;
    private JTextField jtfDownload;
    private JButton btnUpload, btnDownload, btnCancel;
    private JFileChooser jchooser = null;
    private JIFTable tablePanel;
    private File image = null;
    private int imageSize = 0;
    private String imageName = "";
    
    

    public JIFImages() {
        super("| Imagenes | Usuario: "+MainWindow.user.getUserName()+" | ");
	this.setSize(750, 400);
        this.setLocation(110, 150);
        this.setLayout(null);
        this.setResizable(false);
        init();
        this.setVisible(true);
    }
    
    public void init() {
        this.jlbDownload = new JLabel("Bajar Archivo");
        this.jtfDownload = new JTextField();
        this.btnUpload = new JButton("Subir...");
        this.btnDownload = new JButton("Bajar");
        this.btnCancel = new JButton("Salir");
        this.tablePanel = new JIFTable();

        this.btnUpload.setBounds(500, 100, 100, 30);
        this.jlbDownload.setBounds(300, 300, 100, 30);
        this.jtfDownload.setBounds(400, 300, 200, 30);
        this.btnDownload.setBounds(630, 300, 100, 30);
        this.btnCancel.setBounds(5, 300, 100, 30);

        this.btnUpload.addActionListener(this);
        this.btnDownload.addActionListener(this);
        this.btnCancel.addActionListener(this);

        this.add(this.jlbDownload);
        this.add(this.jtfDownload);
        this.add(this.btnUpload);
        this.add(this.btnDownload);
        this.add(this.btnCancel);
        this.add(tablePanel);
	}
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnCancel) {
            this.dispose();
        } else {
            if (e.getSource() == this.btnUpload) {
                try {
                    MainWindow.client.sendImage();
                    chooseImage();
                    this.dispose();
                } catch (IOException ex) {
                    System.out.println("GUI.JIFImages.actionPerformed() "+ex.toString());
//                    Logger.getLogger(JIFImages.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                if(e.getSource() == this.btnDownload){
                    MainWindow.client.sendImageName(this.jtfDownload.getText());
                    receiveImage();
                    this.dispose();
                }
            } 
        }
    }
    
    public void chooseImage() throws IOException{
        this.jchooser = new JFileChooser();
        this.jchooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int returnVal = this.jchooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("Imagen Seleccionada "+ this.jchooser.getSelectedFile().getAbsolutePath());
            sendImage(this.jchooser.getSelectedFile().getAbsolutePath());
            JOptionPane.showMessageDialog(this, "Subiendo...");
        }
    }
    
    public void sendImage(String imagePath) throws IOException{
        
        this.image = new File(imagePath);
        this.imageSize = (int) this.image.length();

        MainWindow.client.getDataOutputStream().writeUTF(this.image.getName());
        MainWindow.client.getDataOutputStream().writeInt(this.imageSize);

        FileInputStream fileInputStream = new FileInputStream(imagePath);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

        byte[] buffer = new byte[this.imageSize];

        bufferedInputStream.read(buffer);

        for (int i = 0; i < buffer.length; i++) {
            MainWindow.client.getBufferedOutputStream().write(buffer[i]);
        }

        bufferedInputStream.close();

        MainWindow.client.getBufferedOutputStream().flush();
        MainWindow.client.getDataOutputStream().flush();
        
    }
    
    public void receiveImage(){
        
        try {
            this.imageName = MainWindow.client.getDataInputStream().readUTF();
            System.out.println("Img name: " +this.imageName);
            this.imageSize = MainWindow.client.getDataInputStream().readInt();
            System.out.println("Img size: " +this.imageSize);

            this.jchooser = new JFileChooser();
            this.jchooser.setFileSelectionMode(JFileChooser.SAVE_DIALOG);
            int returnVal = this.jchooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {

                JOptionPane.showMessageDialog(this, "Directorio Seleccionado " + this.jchooser.getSelectedFile().getAbsolutePath());

                FileOutputStream fileOutputStream = new FileOutputStream(this.jchooser.getSelectedFile().getAbsolutePath() + "\\" + this.imageName);

                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);

                BufferedInputStream bufferedInputStream = MainWindow.client.getBufferedInputStream();

                byte[] buffer = new byte[imageSize];

                for (int i = 0; i < buffer.length; i++) {
                    buffer[i] = (byte) bufferedInputStream.read();
                }

                bufferedOutputStream.write(buffer);

                bufferedOutputStream.flush();
                fileOutputStream.close();

            }

        } catch (IOException ex) {
            System.out.println("GUI.JIFImages.receiveImage() " + ex.toString());
//                Logger.getLogger(JIFImages.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
