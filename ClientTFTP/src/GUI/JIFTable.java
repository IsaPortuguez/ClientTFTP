/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Utility.MyUtility;
import java.awt.Graphics;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Isabel
 */
public class JIFTable extends JPanel{
    
    JScrollPane scroll;
	JTable table;
	String[] directories;

	public JIFTable() {
		this.setLayout(null);
		this.setBounds(100, 10, 360, 200);
		try {
			this.directories = MainWindow.client.getDirectories();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		init();
		this.setVisible(true);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		try {

			getDirectories();
		} catch (IOException ex) {
			Logger.getLogger(JIFTable.class.getName()).log(Level.SEVERE, null, ex);
		}
		repaint();
	}

	public void init() {

		String[] colName = { "Nombre archivo" };
		if (table == null) {
			table = new JTable() {
				@Override
				public boolean isCellEditable(int nRow, int nCol) {
					return false;
				}
			};
		}

		DefaultTableModel contactTableModel = (DefaultTableModel) table.getModel();
		contactTableModel.setColumnIdentifiers(colName);

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		table.setBounds(0, 0, 359, 200);

		scroll = new JScrollPane(table);
		scroll.setBounds(0, 0, 359, 200);
		scroll.setVisible(true);
		this.add(scroll);

	}

	public void getDirectories() throws IOException {

//		MainWindow.client.getDataOutputStream().writeUTF(MyUtility.OBTENERDIRECTORIOS);	
		DefaultTableModel defaultTableModel = (DefaultTableModel) this.table.getModel();

		clean();

		for (int i = 0; i < this.directories.length; i++) {

			String[] data = new String[1];
			data[0] = this.directories[i];
			defaultTableModel.addRow(data);

		}

	}

	public void clean() {
		DefaultTableModel tb = (DefaultTableModel) this.table.getModel();
		int a = this.table.getRowCount() - 1;
		for (int i = a; i >= 0; i--) {
			tb.removeRow(tb.getRowCount() - 1);
		}
	}

	public JTable getTable() {
		return this.table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}
    
}
