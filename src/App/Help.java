package App;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Font;

public class Help {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Help window = new Help();
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
	public Help() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 403);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("<html>\n<p> <h1> <u> Help </u> </h1> </p>\n\n</html>");
		lblNewLabel.setBounds(181, 0, 104, 66);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("<html> \n<hl>\n</html>");
		lblNewLabel_1.setBounds(12, 73, 412, 15);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("<html> \n <ul><li>\n<p style=\"font-size:12px; color:blue\"> if you have an account , you should login to the project \nif not , you can create a new account and login to the project </p> </li>\n<br> <br>\n<li>\n<p style=\"font-size:12px; color:blue\">   we create this project to help people to encrypt their messages with deffierent encryption techniques   </p> </li>\n</ul>\n</html>");
		lblNewLabel_2.setFont(new Font("URW Bookman L", Font.BOLD, 12));
		lblNewLabel_2.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_2.setBounds(12, 92, 412, 245);
		frame.getContentPane().add(lblNewLabel_2);
	}

}
