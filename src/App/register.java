package App;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class register {

	private JFrame frame;
	private JTextField name;
	private JTextField lastname;
	private JTextField address;
	private JTextField email;
	private JTextField image;
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/users";
	static final String USER = "sonic";
	static final String PASS = "sonic";
	private JTextField password;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					register window = new register();
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
	public register() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 546, 367);
	//	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		name = new JTextField();
		name.setBounds(177, 28, 114, 19);
		frame.getContentPane().add(name);
		name.setColumns(10);
		
		lastname = new JTextField();
		lastname.setBounds(177, 72, 114, 19);
		frame.getContentPane().add(lastname);
		lastname.setColumns(10);
		
		address = new JTextField();
		address.setBounds(177, 119, 114, 19);
		frame.getContentPane().add(address);
		address.setColumns(10);
		
		email = new JTextField();
		email.setBounds(177, 167, 114, 19);
		frame.getContentPane().add(email);
		email.setColumns(10);
		
		image = new JTextField();
		image.setBounds(177, 228, 163, 19);
		frame.getContentPane().add(image);
		image.setColumns(10);
		
		JLabel lblName = new JLabel("name");
		lblName.setBounds(51, 30, 70, 15);
		frame.getContentPane().add(lblName);
		
		JLabel lblLastname = new JLabel("lastname");
		lblLastname.setBounds(40, 74, 70, 15);
		frame.getContentPane().add(lblLastname);
		
		JLabel lblAddress = new JLabel("address");
		lblAddress.setBounds(40, 121, 70, 15);
		frame.getContentPane().add(lblAddress);
		
		JLabel lblEmail = new JLabel("email");
		lblEmail.setBounds(51, 169, 70, 15);
		frame.getContentPane().add(lblEmail);
		
		JLabel lblImage = new JLabel("image");
		lblImage.setBounds(51, 230, 70, 15);
		frame.getContentPane().add(lblImage);
		
		JButton btnBrowse = new JButton("browse");
		btnBrowse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
		     	JFileChooser fileChooser = new JFileChooser();
            	FileFilter ft = new FileNameExtensionFilter("images files","jpg");
            	
            	fileChooser.setDialogTitle("Select your photo");    

            		fileChooser.addChoosableFileFilter(ft);
            		
            		int userSelection = fileChooser.showOpenDialog(null);
            		
            		
            	if (userSelection == 0) {
            		
            	    File file = fileChooser.getSelectedFile();
            	    
            	    String filename= file.toString();
            	    
            	    image.setText(filename);
            	    
            
				}else{
					JOptionPane.showMessageDialog(null, "it should be an image ");
				}
            	}
		});
		btnBrowse.setBounds(352, 225, 117, 25);
		frame.getContentPane().add(btnBrowse);
		
		JButton btnRegister = new JButton("register");
		btnRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 	//register
			
				if(username_checker(name.getText())){
					if(username_checker(address.getText())){
						if(username_checker(lastname.getText())){
							if(isValidEmailAddress(email.getText())){
								if(password_checker(password.getText())){
									if(image_checker(image.getText())){
										try {
											Class.forName("com.mysql.jdbc.Driver");
										} catch (ClassNotFoundException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
										try{
										java.sql.Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
										java.sql.Statement state=conn.createStatement();
										
										String sql = "insert into users(firstname,lastname,address,email,image,password)"+" values(?,?,?,?,?,?)";
										PreparedStatement preparedStmt = conn.prepareStatement(sql);
										File file = new  File(image.getText());
									      preparedStmt.setString (1, name.getText());
									      preparedStmt.setString (2, lastname.getText());
									      preparedStmt.setString (3, address.getText());
									      preparedStmt.setString(4, email.getText());
									      
									      FileInputStream fis = new FileInputStream(file);
									      preparedStmt.setBinaryStream (5,fis,(int)file.length());
									      preparedStmt.setString(6, password.getText());
									      // execute the preparedstatement
									      preparedStmt.executeUpdate();
									  
									      fis.close();
									      preparedStmt.close();
										} catch (SQLException | IOException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
										
									}
							}
							}
						}
					}
				}
			
			}});
		btnRegister.setBounds(177, 287, 117, 25);
		frame.getContentPane().add(btnRegister);
		
		password = new JTextField();
		password.setBounds(177, 197, 114, 19);
		frame.getContentPane().add(password);
		password.setColumns(10);
		
		JLabel lblPassword = new JLabel("password");
		lblPassword.setBounds(28, 203, 70, 15);
		frame.getContentPane().add(lblPassword);
	}
	public boolean isValidEmailAddress(String email) {
	           String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
	           java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
	           java.util.regex.Matcher m = p.matcher(email);
	           return m.matches();
	    }
	private boolean username_checker(String username){
		if(username.equals("")) return false;
		if(username==(username.toLowerCase())){
			if(username.matches("^[a-zA-Z0-9]*$")){
				return true;
			}else {
				return false;
			}
	
		}
		return false;
		
	}
		private boolean password_checker(String password){ if (password.equals(""))return false;
		return true;
		
		}
		private boolean image_checker(String path){
			if(path.equals("")) return false;
		   if(new File(path).exists()&& !new File(path).isDirectory())
			   return true;
			
		
		return false;
	}
}