package App;

import java.awt.EventQueue;

import javax.naming.ldap.Rdn;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ceaser.Caesar;

import java.awt.List;
import java.awt.Panel;
import java.awt.RenderingHints.Key;

import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.CardLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import javax.swing.JMenu;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JMenuBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Main_test {
public List l ;
	private JFrame frame;
	public File file;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main_test window = new Main_test();
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
	public Main_test() {
		initialize();
		
		imaging();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	JPanel panel ;
	private void imaging(){
		
			
	}
	JList list ;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	public ButtonGroup BG;
	private String method="";
	JRadioButtonMenuItem rdbtnmntmCaesar ;
	private JTextField textField_3;
	private JTextField textField_4;
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 649, 393);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Global Encryption ");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setBounds(240, 12, 134, 15);
		frame.getContentPane().add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(429, 0, 93, 36);
		frame.getContentPane().add(scrollPane);
		
		
		DefaultListModel model = new DefaultListModel();
		list=new JList(model);
		list.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				String current = list.getSelectedValue().toString();
				CardLayout cardlayout = (CardLayout) panel.getLayout();
				if(current.equals("file")){
					
					cardlayout.show(panel, "files");
				}else{
					
					cardlayout.show(panel, "messages");
				}
				
			}
		});
		model.addElement("file");
		model.addElement("message");
		scrollPane.setViewportView(list);
		
		panel= new JPanel();
		panel.setBounds(24, 53, 571, 213);
		frame.getContentPane().add(panel);
		panel.setLayout(new CardLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, "files");
		panel_1.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(12, 37, 436, 35);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JButton btnBrowse = new JButton("Browse");
		
		btnBrowse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			// TODO select the file to encrypt DDDDDD
				
				JFileChooser jfc = new JFileChooser();
				int returnval = jfc.showOpenDialog(null);
				if(returnval==JFileChooser.APPROVE_OPTION){
					file=jfc.getSelectedFile();
					
				textField.setText(file.getAbsolutePath());
				
				
					
				}else {
					//nothing to show here 
				}
			}
		});
		btnBrowse.setBounds(460, 34, 99, 38);
		panel_1.add(btnBrowse);
		
		JLabel lblTheEncryptedOutput = new JLabel("");
		lblTheEncryptedOutput.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			//TODO decrypt the file 
			
				if(file.equals(null)){JOptionPane.showMessageDialog(null, "you need to add a file ");}
				
				if(rdbtnmntmCaesar.isSelected()){
					
					      if(textField_2.getText().matches("\\b\\d+\\b")){
					    	  System.out.println("decryption started");
					      }else{
					    	  JOptionPane.showMessageDialog(null, "read the manuall for information about the key you in ");
					      }
					
				}
				
			}
		});
		lblTheEncryptedOutput.setVerticalAlignment(SwingConstants.TOP);
		lblTheEncryptedOutput.setBounds(12, 166, 547, 35);
		panel_1.add(lblTheEncryptedOutput);
		
		JButton btnEncrypt = new JButton("Encrypt ");
		btnEncrypt.addMouseListener(new MouseAdapter() {
			@Override 
			//TODO encryption to the file that choosed 
			public void mouseClicked(MouseEvent e) {
				//common regex for matching intergers 
				
				if(file==null){JOptionPane.showMessageDialog(null, "you didn't select any file "); return;}
			if(rdbtnmntmCaesar.isSelected() ){
			
				//TODO Caesar encryption , decryption  
			
					if(textField_2.getText().matches("\\b\\d+\\b")){
				
						//TODO encrypt file 
					try {
						FileInputStream in = new FileInputStream(file.getAbsolutePath());
						int c ;
						//note that this will overwrite the file if it's exists X_X
						
						PrintWriter writer = new PrintWriter(file.getParent()+"/"+"enc"+file.getName());
						
						String buf =new String();
						
						while((c=in.read())!=-1){
						buf+=(char)c;
						}
						String out= Caesar.encrypt(buf, Integer.parseInt(textField_2.getText()));
						int S=0;
						while(S<out.length()){
							writer.write(out.charAt(S++));
						}
						
						writer.close();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
			       }else{ 
			    	   JOptionPane.showMessageDialog(null, "read the manual for information about the key ");
			       			}
		}else{
			JOptionPane.showMessageDialog(null, "you didn't choose a method !!");
		}
			}
			
		});
		btnEncrypt.setBounds(104, 104, 117, 25);
		panel_1.add(btnEncrypt);
		
		textField_2 = new JTextField();
		textField_2.setBounds(349, 84, 80, 45);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblKey = new JLabel("key");
		lblKey.setBounds(302, 84, 48, 40);
		panel_1.add(lblKey);
		
		JButton btnDecrypt = new JButton("Decrypt");
		btnDecrypt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(file==null){JOptionPane.showMessageDialog(null, "you didn't select any file "); return;}
			if(rdbtnmntmCaesar.isSelected() ){
			
				//TODO Caesar encryption , decryption  
			
					if(textField_2.getText().matches("\\b\\d+\\b")){
				
						//TODO encrypt file 
					try {
						FileInputStream in = new FileInputStream(file.getAbsolutePath());
						int c ;
						//note that this will overwrite the file if it's exists X_X
						
						PrintWriter writer = new PrintWriter(file.getParent()+"/"+"dec:"+file.getName());
						
						String buf =new String();
						
						while((c=in.read())!=-1){
						buf+=(char)c;
						}
						String out= Caesar.decrypt(buf, Integer.parseInt(textField_2.getText()));
						int S=0;
						while(S<out.length()){
							writer.write(out.charAt(S++));
						}
						
						writer.close();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
			       }else{ 
			    	   JOptionPane.showMessageDialog(null, "read the manual for information about the key ");
			       			}
		}else{
			JOptionPane.showMessageDialog(null, "you didn't choose a method !!");
		}
			}
			
		});
		btnDecrypt.setBounds(104, 141, 117, 25);
		panel_1.add(btnDecrypt);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, "messages");
		panel_2.setLayout(null);
		
		textField_1 = new JTextField();
		textField_1.setBounds(12, 0, 467, 88);
		panel_2.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("encrypt");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO encrypt the message 

				
				if(rdbtnmntmCaesar.isSelected()){
					
					      if(textField_4.getText().matches("\\b\\d+\\b")){
					    	int i =0 ; String buf ="";
					    
					    		buf+=Caesar.alpha_encrypt(textField_1.getText(),Integer.parseInt(textField_4.getText()));
					      textField_3.setText(buf);
					      }else{
					    	  JOptionPane.showMessageDialog(null, "read the manuall for information about the key you in ");
					      }
					
				}else{
					JOptionPane.showMessageDialog(null, "select encryption method plz ");
				}
				
				
			}
		});
		btnNewButton.setVerticalAlignment(SwingConstants.TOP);
		btnNewButton.setBounds(480, 0, 109, 43);
		panel_2.add(btnNewButton);
		
		textField_3 = new JTextField();
		textField_3.setBounds(12, 113, 467, 88);
		panel_2.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("decryption");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				
				if(rdbtnmntmCaesar.isSelected()){
					
					      if(textField_4.getText().matches("\\b\\d+\\b")){
					    	 String buf ="";
					    
					    		buf+=Caesar.alpha_decrypt(textField_3.getText(),Integer.parseInt(textField_4.getText()));
					      textField_1.setText(buf);
					      }else{
					    	  JOptionPane.showMessageDialog(null, "read the manuall for information about the key you in ");
					      }
					
				}else{
					JOptionPane.showMessageDialog(null, "select encryption method plz ");
				}
				
				
			}
		});
		btnNewButton_1.setBounds(483, 151, 100, 50);
		panel_2.add(btnNewButton_1);
		
		textField_4 = new JTextField();
		textField_4.setBounds(496, 73, 63, 43);
		panel_2.add(textField_4);
		textField_4.setColumns(10);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnMethod = new JMenu("method");
		menuBar.add(mnMethod);
		BG=new ButtonGroup();
		 rdbtnmntmCaesar = new JRadioButtonMenuItem("Caesar");
	
		mnMethod.add(rdbtnmntmCaesar);
		BG.add(rdbtnmntmCaesar);
	}
}
