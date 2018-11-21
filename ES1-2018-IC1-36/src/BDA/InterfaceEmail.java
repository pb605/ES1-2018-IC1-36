package BDA;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.JTable;
import javax.swing.JButton;

public class InterfaceEmail {

	private JPanel contentPane;
	private JTable table;
	private InterfaceGeral geral;

	public InterfaceEmail(InterfaceGeral ig) {
		geral = ig; 
		JFrame frame = new JFrame("Email");
		frame.setVisible(true);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);

		JButton btnNewButton_1 = new JButton("Novo Email");
		panel.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {	
				frame.dispose();
				InterfaceEnviarEmail ienviar = new InterfaceEnviarEmail("", "");
				
			}
		});
		

		JButton buttonBack = new JButton("Back");
		panel.add(buttonBack);
		buttonBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {	
				frame.dispose();
				geral.openagain();
				
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
	
	public void fillJlist(JSONArray jarray) {
		System.out.println("passou2");
		
		System.out.println(jarray.length());

		Object[][] data = new Object[jarray.length()][2];
		for (int i = 0 ; i<jarray.length(); i++) {
			try {
				JSONObject obj = jarray.getJSONObject(i);
				System.out.println("ola");
				data[i][0] = obj.get("message");
				data[i][1] = obj.get("data");
				System.out.println(obj);

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		DefaultTableModel ManModel = new DefaultTableModel(data, new String[] {"Mensagem", "Data"});
		
		table.setModel(ManModel);
		table.getColumnModel().getColumn(0).setPreferredWidth(450);
	}


}
