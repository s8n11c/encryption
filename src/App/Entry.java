package App;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Entry {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/users";
	static final String USER = "sonic";
	static final String PASS = "sonic";
	public static JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
public void enter () throws ClassNotFoundException, SQLException{
	boolean flag=false;
	if(new String ("admin").equals(textField.getText())&&new String("admin").equals( new String (passwordField.getPassword() ) )) {
		flag=true;
		
		frame.setVisible(false);
		interFace ad = new interFace();
		String gz[] = null;
		ad.main(gz); 
		return ;
}
	Class.forName("com.mysql.jdbc.Driver");
	java.sql.Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
	java.sql.Statement state=conn.createStatement();
	String sql ="select * from users";
	java.sql.ResultSet res =  state.executeQuery(sql);
	
	while(res.next()){
		if(res.getString("firstname").equals(textField.getText())&&res.getString("password").equals( new String (passwordField.getPassword() ) )) {
	    		flag=true;
	    		
	    		frame.setVisible(false);
    			interFace ad = new interFace();
    			String gz[] = null;
    			ad.main(gz); 
}}
	
	if(flag==false)
		JOptionPane.showMessageDialog(null,"username or password is not correct");
}
	public boolean usernamechecker(String username){
	
		if(username==(username.toLowerCase())){
			if(username.matches("^[a-zA-Z0-9]*$")){
				return true;
			}else {
				return false;
			}
	
		}
	return false;}
	
	
	/**
	 * Launch the application.
	 */
	static Entry window =null;
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				window	= new Entry();
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
	public Entry() {
		initialize();
	}
	static JPanel panel ;
	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		panel= new JPanel();
		panel.setBounds(47, 28, 362, 233);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(124, 52, 171, 27);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblUsername = new JLabel("username:");
		lblUsername.setBounds(24, 58, 88, 15);
		panel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("password");
		lblPassword.setBounds(24, 115, 75, 15);
		panel.add(lblPassword);
		
		passwordField = new JPasswordField();
		
		passwordField.setBounds(124, 109, 171, 27);
		panel.add(passwordField);
		
		JButton btnOk = new JButton("OK");
		btnOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				on_button_pressed();
			}
		
			
		});
		btnOk.setBounds(101, 148, 100, 27);
		panel.add(btnOk);
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				  if (e.getKeyCode()==KeyEvent.VK_ENTER){
					  	on_button_pressed();
				  }
			}
		});
		
	
		//0000000000000000000000000000000000000000000000000000000000000000
		
	}
	public void on_button_pressed(){
		if(textField.getText().equals("") || passwordField.getPassword().equals("")){
			JOptionPane.showMessageDialog(null, "username or password is empty");
		}else 
		if(usernamechecker(textField.getText())){
			 
			
		 try {
			enter();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		}else{
			JOptionPane.showMessageDialog(null, " make your username only small 1-9 A-z charachters ");
			
		}
		
	}
}
