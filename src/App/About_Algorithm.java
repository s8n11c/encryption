package App;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;

public class About_Algorithm {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					About_Algorithm window = new About_Algorithm();
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
	public About_Algorithm() {
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
		
		JLabel lblNewLabel = new JLabel("<html><center> <h1>About algorithm</h1> </center>\n\n  <p>      <b> we got afew encryption algorithm  such as:</b>\n<br />\n<p>\n<ul>\n<li > <a href=\"http://\" >Caesar</a> </li>\n<li><a href=\"http://\" > Monoalphabetic</a></li>\n<li><a href=\"http://\" > Playfair</a></li>\n<li><a href=\"http://\" >Riel Fence </a></li>\n<li><a href=\"http://\" >  DES</a></li>\n<li><a href=\"http://\"> AES</a></li>\n<li><a href=\"http://\"> Row Transposition</a></li>\n<li><a href=\"http://\"> Auto key </a></li>\n<li><a href=\"http://\">Vigenere </a></li>\n</ul> \n\n</html>");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setBounds(0, 0, 436, 256);
		frame.getContentPane().add(lblNewLabel);
	}

}
