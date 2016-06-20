package App;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;

public class about_US {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					about_US window = new about_US();
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
	public about_US() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 450, 360);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("about US ");
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("<html>\n\n       <ul> \n               <li>  <a href=\"#\" > Yousef Ashraf Dawood ...  </a> </li> \n\t\t<li> <a href=\"#\"> Amr Mohamed awd.. </a> </li>\n\t\t<li> <a href=\"#\"> Ahmed Fathy abbas </a></li>\n\t\t<li> <a href=\"#\"> Mohamed Ahmed El7arby </a></li>\n       </ul>\n</html>");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("DejaVu Serif", Font.BOLD, 14));
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setBounds(24, 106, 471, 158);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblPreparedBy = new JLabel("<html><p>\n \n<center> <h1>prepared by </h1> </center> \n</p>\n</html>");
		lblPreparedBy.setVerticalAlignment(SwingConstants.TOP);
		lblPreparedBy.setFont(new Font("Dialog", Font.BOLD, 16));
		lblPreparedBy.setBounds(148, 12, 430, 64);
		frame.getContentPane().add(lblPreparedBy);
	}
}
