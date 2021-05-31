package GUI;

import Domain.User;
import Utility.MyUtility;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Isabel
 */
public class JIFLogIn extends JInternalFrame implements ActionListener{
    
    private JLabel jlbUserName, jlbPassword;
    private JTextField tfUserName, tfPassword;
    private JButton btnAccept, btnCancel;

    public JIFLogIn() {
        super("Iniciar Sesion");
	this.setSize(240, 180);
        this.setLocation(370, 250);
        this.setLayout(null);
        this.setResizable(false);
        init();
        this.setVisible(true);
    }
    
    public void init() {
        this.jlbUserName = new JLabel("Nombre");
        this.jlbPassword = new JLabel("Contraseña");
        this.tfUserName = new JTextField();
        this.tfPassword = new JPasswordField();
        this.btnAccept = new JButton("Ingresar");
        this.btnCancel = new JButton("Salir");

        this.jlbUserName.setBounds(5, 5, 100, 30);
        this.tfUserName.setBounds(115, 5, 100, 30);
        this.jlbPassword.setBounds(5, 50, 100, 30);
        this.tfPassword.setBounds(115, 50, 100, 30);
        this.btnAccept.setBounds(5, 100, 100, 30);
        this.btnCancel.setBounds(115, 100, 100, 30);

        this.btnAccept.addActionListener(this);
        this.btnCancel.addActionListener(this);

        this.add(this.jlbUserName);
        this.add(this.jlbPassword);
        this.add(this.tfUserName);
        this.add(this.tfPassword);
        this.add(this.btnAccept);
        this.add(this.btnCancel);
	}
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnCancel) {
            this.dispose();
        } else {
            if (e.getSource() == this.btnAccept) {
                String estado;
                try {
                    estado = MainWindow.client.logInUser(tfUserName.getText(), tfPassword.getText());
                    User userTemp= new User(tfUserName.getText(), tfPassword.getText());
                    if(estado.equals(MyUtility.EXISTE)){
                        JOptionPane.showMessageDialog(this, "Se ingresó correctamente");
                        MainWindow.user = userTemp;
                        MainWindow.iniciado = true;
                        this.dispose();
                    }else if(estado.equals(MyUtility.NOEXISTE)){
                        JOptionPane.showMessageDialog(this, "Este usuario no existe, verifique sus datos");
                    }
                } catch (IOException ex) {
                    System.out.println("GUI.JIFLogIn.actionPerformed() "+ex.toString());
//                    Logger.getLogger(JIFLogIn.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
        }

    }
}

    

