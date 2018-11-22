package BDA;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class InterfaceEnviarEmail {

	private JFrame frame;
	private JTextField textField_para;
	private JTextField textField_assunto;

	/**
	 * Launch the application.
	 */
	public InterfaceEnviarEmail(String user, String pass) {
		
		frame = new JFrame("Enviar Email");
		frame.setVisible(true);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 450, 300);
		
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Para:");
		lblNewLabel.setBounds(10, 11, 46, 14);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Assunto:");
		lblNewLabel_1.setBounds(10, 36, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);

		textField_para = new JTextField();
		textField_para.setBounds(69, 8, 336, 20);
		frame.getContentPane().add(textField_para);
		textField_para.setColumns(10);

		textField_assunto = new JTextField();
		textField_assunto.setBounds(69, 33, 336, 20);
		frame.getContentPane().add(textField_assunto);
		textField_assunto.setColumns(10);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(67, 64, 342, 155);
		frame.getContentPane().add(textArea);

		JLabel lblTexto = new JLabel("Texto:");
		lblTexto.setBounds(10, 69, 46, 14);
		frame.add(lblTexto);
		
		JButton btnNewButton = new JButton("Enviar");
		btnNewButton.setBounds(96, 230, 89, 23);
		frame.add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				EnviarEmailApi enviar = new EnviarEmailApi(user, pass, textField_para.getText(), user, textField_assunto.getText(), textArea.getText());
				
			}
		});
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.setBounds(230, 230, 89, 23);
		frame.add(btnNewButton_1);
	}
}
