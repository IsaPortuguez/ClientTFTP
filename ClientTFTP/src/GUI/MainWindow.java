package GUI;

import Client.Client;
import Domain.User;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author Isabel
 */
public class MainWindow extends JFrame implements ActionListener{
    
    private JMenuBar jMenuBar;
    private JMenu jMenuAdd, jMenuImages;
    private JMenuItem jmiCheckIn, jmiLogIn, jmiUpDown;
    public static JDesktopPane desktop;  
    public static Client client;
    public static User user;
    public static boolean iniciado = false;

    public MainWindow(Client client) {
        super();
        this.client = client;
        this.setSize(1000, 770);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        init();
        this.setVisible(true);
    }
    
    public void init() {

        this.user = new User();
        this.jMenuAdd = new JMenu("Usuario");
        this.jMenuImages = new JMenu("Imagen");
        this.jMenuBar = new JMenuBar();
        this.jmiCheckIn = new JMenuItem("Registrarse");
        this.jmiLogIn = new JMenuItem("Iniciar Sesion");
        this.jmiUpDown = new JMenuItem("Subir/Bajar");
        this.desktop = new JDesktopPane();

        this.jMenuBar.setBounds(0, 0, this.getWidth(), 20);
        this.desktop.setBounds(0, 20, this.getWidth(), getHeight());

        this.jmiLogIn.addActionListener(this);
        this.jmiCheckIn.addActionListener(this);
        this.jmiUpDown.addActionListener(this);

        this.jMenuAdd.add(this.jmiCheckIn);
        this.jMenuAdd.add(this.jmiLogIn);
        this.jMenuImages.add(this.jmiUpDown);

        this.jMenuBar.add(this.jMenuAdd);
        this.jMenuBar.add(this.jMenuImages);

        this.add(this.jMenuBar);
        this.add(this.desktop);

    }// init
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.jmiCheckIn) {
            JIFCheckIn jifCheckIn = new JIFCheckIn();
            this.desktop.add(jifCheckIn);
        } else if (e.getSource() == this.jmiLogIn) {
            JIFLogIn jifLogIn = new JIFLogIn();
            this.desktop.add(jifLogIn);
        }else if(e.getSource() == this.jmiUpDown){
            if(this.iniciado == true){
                JIFImages jifImages = new JIFImages();
                this.desktop.add(jifImages);
            }else{
                JOptionPane.showMessageDialog(null, "Necesita iniciar sesión para poder usar esta opción");
            }
        }
    }
    
}
