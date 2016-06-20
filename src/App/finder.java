package App;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;

public class finder {
	JLabel image = new JLabel("");
	
	JLabel lblName = new JLabel("Name :");
	
	JLabel lblLastname = new JLabel("LastName:");
	
	JLabel lblEmail = new JLabel("Email:");
	
	JLabel lblname = new JLabel("");
	
	JLabel lastname = new JLabel("");
	
	JLabel lblAddress = new JLabel("address:");
	
	JLabel lbladdress = new JLabel("");
	
	JLabel email = new JLabel("");
	private JFrame frame;
	public String name;
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/users";
	static final String USER = "sonic";
	static final String PASS = "sonic";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					finder window = new finder();
					window.name=args[0];
					window.assigner();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public finder() throws ClassNotFoundException, SQLException {
		initialize();
	}
	public void  assigner() throws ClassNotFoundException, SQLException{
		JOptionPane.showMessageDialog(null, this.name);
		Class.forName("com.mysql.jdbc.Driver");
		java.sql.Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		java.sql.Statement state=conn.createStatement();
		String sql ="select * from users where firstname="+"'"+name+"'";
		java.sql.ResultSet res =  state.executeQuery(sql);
		boolean flag=false;
		while(res.next()){
		    lblname.setText(res.getString("firstname"));
		    lastname.setText(res.getString("lastname"));
		    lbladdress.setText(res.getString("address"));
		    email.setText(res.getString("email"));
		    byte[] img_bytes =res.getBytes("image");
		    Image img = Toolkit.getDefaultToolkit().createImage(img_bytes);
		    ImageIcon icon= new ImageIcon(img);
		    
 		   image.setIcon(icon);
		}
		
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	private void initialize() throws ClassNotFoundException, SQLException {
		frame = new JFrame();
		frame.setBounds(100, 100, 589, 236);
	//	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(image, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
					.addGap(82)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(27)
							.addComponent(lblName)
							.addGap(18)
							.addComponent(lblname, GroupLayout.PREFERRED_SIZE, 248, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblAddress)
								.addComponent(lblLastname)
								.addComponent(lblEmail))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(27)
									.addComponent(lbladdress, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(email, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE)
										.addComponent(lastname, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
					.addContainerGap(46, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(image, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
									.addGap(38))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblName)
									.addGap(18)
									.addComponent(lblLastname)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblAddress)
										.addComponent(lbladdress))
									.addGap(27))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(20)
							.addComponent(lblname)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lastname)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmail)
						.addComponent(email))
					.addContainerGap(120, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);

	
	}
	
}
