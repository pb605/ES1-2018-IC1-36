package BDA;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.restfb.Connection;
import com.restfb.json.JsonArray;
import com.restfb.json.JsonValue;
import com.restfb.types.Post;

import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.FlowLayout;
import javax.swing.JButton;

public class InterfaceFacebook {
	private JTable table;
	private InterfaceGeral geral;

	public InterfaceFacebook(InterfaceGeral ig) {
		geral = ig;

		JFrame contentPanel = new JFrame("Facebook");
		
		contentPanel.setVisible(true);
		contentPanel.setBounds(100, 100, 561, 372);
		contentPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPanel.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPanel.getContentPane().add(panel, BorderLayout.SOUTH);
		
		
		JButton buttonBack = new JButton("Back");
		panel.add(buttonBack);
		buttonBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				contentPanel.dispose();
				geral.openagain();
				
			}
		});
		
		
		JScrollPane scrollPane = new JScrollPane();
		contentPanel.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
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
		DefaultTableModel ManModel = new DefaultTableModel(data, new String[] {"Mensagem", "Data", "User"});
		
		table.setModel(ManModel);
		table.getColumnModel().getColumn(0).setPreferredWidth(250);
	}
}

