package App;

import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.jws.Oneway;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Users {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/users";
	static final String USER = "sonic";
	static final String PASS = "sonic";
	
	
	
	private JFrame frame;
	private JTable table;
	private JTextField search;
	private JLabel lblName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Users window = new Users();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public Users() throws ClassNotFoundException, SQLException, IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 * @throws IOException 
	 */
	private Vector<Object> get_data() throws ClassNotFoundException, SQLException, IOException{
		String data[][] = new String[5][5];
		Vector<Object> rows = new Vector<Object>();
		Class.forName("com.mysql.jdbc.Driver");
		java.sql.Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		java.sql.Statement state=conn.createStatement();
		
		String sql = "select * from users";
		
		java.sql.ResultSet res =  state.executeQuery(sql);
		
		while(res.next()){
			Vector<Object> newRow = new Vector<Object>();

		    for (int i = 1; i <= 6; i++) {
		        if (i == 6) {  // ... whatever column is your image column
		            Blob blob = res.getBlob("image");
		            int blobLength = (int) blob.length();  

		            byte[] bytes = blob.getBytes(1, blobLength);
		            blob.free();
		            BufferedImage img = ImageIO.read(new ByteArrayInputStream(bytes));
		            ImageIcon icon = new ImageIcon(img);  
		            newRow.addElement(icon);  
		        } else {
		            newRow.addElement(res.getObject(i));
		        }
		        
		}
		    rows.addElement(newRow);
		}
		return rows;
	}
	private void initialize() throws ClassNotFoundException, SQLException, IOException {
		frame = new JFrame();

		Vector<Object> columnNames = new Vector<Object>(); 
		columnNames.addElement("ID");
		columnNames.addElement("firstname");
		columnNames.addElement("lastname");
		columnNames.addElement("email");
		columnNames.addElement("address");
		columnNames.addElement("image");
		
		// Create some data
		String dataValues[][]=null;
		frame.setBounds(100, 100, 768, 616);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		table = new JTable(get_data(),columnNames);	
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		
		search = new JTextField();
		search.setColumns(10);
		
		lblName = new JLabel("NAME:");
		
		JButton btnSearch = new JButton("SEARCH");
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(search.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "the search fiels is empty");
					return;
				}
				try {
					Class.forName("com.mysql.jdbc.Driver");

					java.sql.Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
					java.sql.Statement state=conn.createStatement();
					String sql = "select * from users";
					
					java.sql.ResultSet res =  state.executeQuery(sql);
					boolean flag=false;
					while(res.next()){
						if(res.getString("firstname").equals(search.getText())) {
					    		flag=true;
				    			finder ad = new finder();
				    			String gz[] = {res.getString("firstname")};
				    			ad.main(gz); 
				}}
					if(flag==false){
						JOptionPane.showMessageDialog(null, "the proper name is not found here ");
					}
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
			}
			
		});
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblName, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGap(31)
									.addComponent(search, GroupLayout.PREFERRED_SIZE, 335, GroupLayout.PREFERRED_SIZE)
									.addGap(320))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(168)
							.addComponent(btnSearch)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 284, GroupLayout.PREFERRED_SIZE)
					.addGap(102)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(search, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
						.addComponent(lblName))
					.addGap(45)
					.addComponent(btnSearch, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(67))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
