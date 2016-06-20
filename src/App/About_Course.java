package App;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;

public class About_Course {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					About_Course window = new About_Course();
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
	public About_Course() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 487, 336);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setTitle("about the course");
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("<html>\n<br> <br>\n<b> <p><a style=\"font-size:14px\"> we get this course produced by: </a><br>\n<br><a style=\"color:blue\">\n Dr/Mohamed el sharkawy <br>  Dr/ Ahmed el sayed  </p></b>\n</a>\n<br>\n<p>and we've  studied in this course  a set of cipher techniques:  </p>\n<br> Like\n<ul>\n\t<li> Caesar </li>\n\t<li> AES </li>\n\t<li> DES </li>\n\t<li> Playfair </li>\n\t<li> Monoalphabetic </li>\n\t\t<li> AutoKey </li>\n\n\n</ul> \n<br>\n<br>\n</html>");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setBounds(25, 0, 448, 292);
		frame.getContentPane().add(lblNewLabel);
	}

}
