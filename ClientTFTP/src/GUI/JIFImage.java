/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

/**
 *
 * @author Isabel
 */
public class JIFImage extends JInternalFrame implements ActionListener{
    
    public static JDesktopPane desktop; 
    private JButton btnCancel;

    public JIFImage() {
        super("| Imagen | Usuario: "+MainWindow.user.getUserName()+" | ");
	this.setSize(750, 600);
        this.setLocation(110, 50);
        this.setLayout(null);
        this.setResizable(false);
        init();
        this.setVisible(true);
    }
    
    public void init(){
        this.btnCancel = new JButton("Salir");
        this.desktop = new JDesktopPane();

        this.btnCancel.setBounds(5, 500, 100, 30);
        this.desktop.setBounds(0, 20, this.getWidth(), getHeight()-200);

        this.btnCancel.addActionListener(this);
        
        this.add(this.btnCancel);
        this.add(this.desktop);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnCancel) {
            this.dispose();
        } 
    }
    
}
