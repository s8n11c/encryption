package Monoalphabetic;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserEntry {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserEntry window = new UserEntry();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UserEntry() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(33, 23, 257, 81);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("encrypt");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			 if(textField.getText()==null){
				 JOptionPane.showMessageDialog(null,"error");
				 return;
			 }
			 pair C = Monoalphabetic.encrypt(textField.getText());
        System.out.println("the encrypted message is:"+C.get_left());
        System.out.println("the key is "+C.get_right());
			}
		});

		btnNewButton.setBounds(315, 23, 117, 81);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblMonoap = new JLabel("MONOALPHABETIC");
		lblMonoap.setBounds(33, 0, 182, 15);
		frame.getContentPane().add(lblMonoap);
		
		textField_1 = new JTextField();
		textField_1.setBounds(33, 126, 257, 64);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnDecrypt = new JButton("decrypt");
		btnDecrypt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			if(textField_1.getText()==null){
			JOptionPane.showMessageDialog(null,"error");
			return;}
			System.out.println(Monoalphabetic.decrypt(textField_1.getText(), textField_2.getText()));
			}
			
		});
		btnDecrypt.setBounds(315, 130, 117, 25);
		frame.getContentPane().add(btnDecrypt);
		
		textField_2 = new JTextField();
		textField_2.setBounds(43, 202, 247, 19);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblKey = new JLabel("key");
		lblKey.setBounds(12, 202, 70, 15);
		frame.getContentPane().add(lblKey);
	}
}
