package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    
    private JLabel jlbUpload, jlbDownload;
    private JFileChooser jchooserUpload, jchooserDownload;
    private JButton btnUpload, btnDownload, btnCancel;

    public JIFImages() {
        super("Imagenes");
	this.setSize(750, 500);
        this.setLocation(110, 50);
        this.setLayout(null);
        this.setResizable(false);
        init();
        this.setVisible(true);
    }
    
    public void init() {
        this.jlbUpload = new JLabel("Subir Archivo");
        this.jlbDownload = new JLabel("Bajar Archivo");
        this.jchooserUpload= new JFileChooser();
        this.jchooserDownload = new JFileChooser();
        this.btnUpload = new JButton("Subir");
        this.btnDownload = new JButton("Bajar");
        this.btnCancel = new JButton("Volver");

        this.jlbUpload.setBounds(200, 20, 100, 30);
        this.jchooserUpload.setBounds(350, 20, 200, 100);
        this.btnUpload.setBounds(200, 135, 100, 30);
        this.jlbDownload.setBounds(200, 230, 100, 30);
        this.jchooserDownload.setBounds(350, 230, 200, 100);
        this.btnDownload.setBounds(200, 340, 100, 30);
        this.btnCancel.setBounds(5, 430, 100, 30);

        this.btnUpload.addActionListener(this);
        this.btnDownload.addActionListener(this);
        this.btnCancel.addActionListener(this);

        this.add(this.jlbUpload);
        this.add(this.jlbDownload);
        this.add(this.jchooserUpload);
        this.add(this.jchooserDownload);
        this.add(this.btnUpload);
        this.add(this.btnDownload);
        this.add(this.btnCancel);
	}
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnCancel) {
            this.dispose();
        } else {
            if (e.getSource() == this.btnUpload) {
                JOptionPane.showMessageDialog(this, "Subiendo...");
            }else{
                if(e.getSource() == this.btnDownload){
                    JOptionPane.showMessageDialog(this, "Bajando...");
                }
            } 
        }
    }
    
}
