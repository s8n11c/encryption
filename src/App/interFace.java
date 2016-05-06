package App;

import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

public class interFace {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					interFace window = new interFace();
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
	public interFace() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 603, 363);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNew = new JMenu("New");
		menuBar.add(mnNew);
		
		JMenuItem mntmCipher = new JMenuItem("Cipher");
		mnNew.add(mntmCipher);
		mntmCipher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            		String[] ctxt= new String[2];
            		CIpher_text.main(ctxt);
            	
            }
            });
		JMenuItem mntmUser = new JMenuItem("USer");
		mnNew.add(mntmUser);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnNew.add(mntmExit);
		mntmExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		mntmUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            		String[] ctxt= new String[2];
            		register.main(ctxt);
            
            }
            });
		
		JMenu mnUsers = new JMenu("users");
		menuBar.add(mnUsers);
		mnUsers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            		String[] ctxt= new String[2];
            		Users.main(ctxt);
            	
            }
            });
		JMenuItem mntmAddUser = new JMenuItem("Add user");
		mnUsers.add(mntmAddUser);
		mntmAddUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            		String[] ctxt= new String[2];
            		register.main(ctxt);
            		
            }
            });
		JMenuItem mntmFindUser = new JMenuItem("Find user");
		mnUsers.add(mntmFindUser);
		mntmFindUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            		String[] ctxt= new String[2];
            		Users.main(ctxt);
            	
            }
            });
		JMenuItem mntmListAllUsers = new JMenuItem("list all users");
		mnUsers.add(mntmListAllUsers);
		mntmListAllUsers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            		String[] ctxt= new String[2];
            		Users.main(ctxt);
            	
            }
            });
		JMenuItem mntmLoginWithA = new JMenuItem("login with a different users");
		mnUsers.add(mntmLoginWithA);
			mntmLoginWithA.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent event) {
	            		String[] ctxt= new String[2];
	            		Entry.main(ctxt);
	            		frame.setVisible(false);
	            }
	            });
		JMenu mnNewMenu = new JMenu("Help");
		menuBar.add(mnNewMenu);
		
		JMenu mnAbout = new JMenu("about");
		menuBar.add(mnAbout);
		
	
		

	}
}
