package App;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;

import javax.print.event.PrintJobAdapter;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.DefaultEditorKit;

import AutoKey.AutoKey;
import DES.STR_DES;
import Monoalphabetic.Monoalphabetic;
import Playfair.Playfair;
import RowTranspsition.RowTransposition;

public class CIpher_text {

	private JFrame frmCiphering;
	private JTextField key_text;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CIpher_text window = new CIpher_text();
					window.frmCiphering.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CIpher_text() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCiphering = new JFrame();
		frmCiphering.setTitle("Ciphering");
		frmCiphering.setBounds(100, 100, 637, 386);
		//frmCiphering.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCiphering.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(12, 12, 568, 320);
		frmCiphering.getContentPane().add(panel);
	ButtonGroup	BG=new ButtonGroup();
		JButton button_1 = new JButton("Encrypt ");
	
		button_1.setBounds(111, 160, 117, 25);
		panel.add(button_1);
		
		key_text = new JTextField();
		key_text.setColumns(10);
		key_text.setBounds(438, 148, 53, 40);
		panel.add(key_text);
		
		JLabel label_1 = new JLabel("key");
		label_1.setBounds(388, 152, 48, 40);
		panel.add(label_1);
		
		JButton button_2 = new JButton("Decrypt");
		
		button_2.setBounds(240, 160, 117, 25);
		panel.add(button_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 12, 517, 129);
		panel.add(scrollPane);
		
		JTextPane plain_text = new JTextPane();
		scrollPane.setViewportView(plain_text);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(22, 196, 507, 112);
		panel.add(scrollPane_1);
		
		JTextPane cipher_text = new JTextPane();
		scrollPane_1.setViewportView(cipher_text);
		
		JMenuBar menuBar = new JMenuBar();
		frmCiphering.setJMenuBar(menuBar);
		
		JMenu mnAlgorithm = new JMenu("Algorithm");
		menuBar.add(mnAlgorithm);
		
		JCheckBoxMenuItem chckbxmntmCeaser = new JCheckBoxMenuItem("Ceaser");
		mnAlgorithm.add(chckbxmntmCeaser);
		BG.add(chckbxmntmCeaser);
		JCheckBoxMenuItem chckbxmntmRielfence = new JCheckBoxMenuItem("RielFence");
		mnAlgorithm.add(chckbxmntmRielfence);
		BG.add(chckbxmntmRielfence);
		JCheckBoxMenuItem chckbxmntmRowtransposition = new JCheckBoxMenuItem("RowTransposition");
		mnAlgorithm.add(chckbxmntmRowtransposition);
		BG.add(chckbxmntmRowtransposition);
		JCheckBoxMenuItem chckbxmntmVigenere = new JCheckBoxMenuItem("Vigenere");
		mnAlgorithm.add(chckbxmntmVigenere);
		BG.add(chckbxmntmVigenere);
		JCheckBoxMenuItem chckbxmntmMonoalphabetic = new JCheckBoxMenuItem("Monoalphabetic");
		mnAlgorithm.add(chckbxmntmMonoalphabetic);
		BG.add(chckbxmntmMonoalphabetic);
		JCheckBoxMenuItem chckbxmntmDes = new JCheckBoxMenuItem("DES");
		mnAlgorithm.add(chckbxmntmDes);
		BG.add(chckbxmntmDes);
		JCheckBoxMenuItem chckbxmntmAutokey = new JCheckBoxMenuItem("AutoKey");
		mnAlgorithm.add(chckbxmntmAutokey);
		BG.add(chckbxmntmAutokey);
		JCheckBoxMenuItem chckbxmntmAes = new JCheckBoxMenuItem("AES");
		BG.add(chckbxmntmAes);
		mnAlgorithm.add(chckbxmntmAes);
		
		JCheckBoxMenuItem chckbxmntmPlayfair = new JCheckBoxMenuItem("playfair");
		mnAlgorithm.add(chckbxmntmPlayfair);
		BG.add(chckbxmntmPlayfair);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmOpenFile = new JMenuItem("Open FIle");
		mntmOpenFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	File file;
				JFileChooser jfc = new JFileChooser();
				int returnval = jfc.showOpenDialog(null);
				if(returnval==JFileChooser.APPROVE_OPTION){
					file=jfc.getSelectedFile();
					try {
						FileReader fr = new FileReader(file);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
					try {
						for(String part :Files.readAllLines(file.toPath())){
							plain_text.setText(plain_text.getText()+part+"\n");
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}else {
					//nothing to show here 
				}
            }
        });
		mnFile.add(mntmOpenFile);
		
		JMenuItem mntmSaveTo = new JMenuItem("Save to");
		mnFile.add(mntmSaveTo);
			
			mntmSaveTo.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent event) {
	            	JFileChooser fileChooser = new JFileChooser();
	            	FileFilter ft = new FileNameExtensionFilter("text files","txt");
	            	fileChooser.setDialogTitle("Specify a file to save");    

	            	
	            		fileChooser.addChoosableFileFilter(ft);
	            		
	            		int userSelection = fileChooser.showSaveDialog(null);
	            		
	            		
	            	if (userSelection == JFileChooser.APPROVE_OPTION) {
	            		
	            	    File fileToSave = fileChooser.getSelectedFile();
	            	    
	            	    String filename= fileToSave.toString();
	            	    
	            	    try {
							FileWriter dat = new FileWriter(filename);
							String alletxt=cipher_text.getText();
							dat.write(alletxt);
							JOptionPane.showMessageDialog(null,"saved");
							dat.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	            	    
	            	    
	            	    	 
	            	}
	            }
	        });
		JMenuItem mntmPrint = new JMenuItem("Print");
		mnFile.add(mntmPrint);
		mntmPrint.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Printing
				BufferedWriter out;
				try {
					out = new BufferedWriter(new FileWriter("1.txt"));
					out.write("dddd");
					out.flush();
					out.close();
					File ff =  new File("1.txt");
					
				
					PrinterJob pj = PrinterJob.getPrinterJob();
					
					if(pj.printDialog()){
						 try {pj.print();}
					        catch (PrinterException exc) {
					            System.out.println(exc);
					         }
						}
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				

			}
		});
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		
		JMenu mnEdite = new JMenu("Edit");
		menuBar.add(mnEdite);
		
		JMenuItem mntmCut = new JMenuItem(new DefaultEditorKit.CutAction());
		mnEdite.add(mntmCut);
		
		JMenuItem mntmCopy = new JMenuItem(new DefaultEditorKit().copyAction);
		mnEdite.add(mntmCopy);
		
		JMenuItem mntmPaste = new JMenuItem(new DefaultEditorKit.PasteAction());
		mnEdite.add(mntmPaste);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(chckbxmntmCeaser.isSelected()){
					int key=Integer.parseInt(key_text.getText());
					if(key==0){
						JOptionPane.showMessageDialog(null, "the key is empty");
						return;
					}
					cipher_text.setText(ceaser.Caesar.encrypt(plain_text.getText(), key));
				}else if(chckbxmntmAutokey.isSelected()){
					
	
						String key=key_text.getText();
						if(key.equals(null)){
							JOptionPane.showMessageDialog(null, "the key is empty");
							return;
						}
						cipher_text.setText(AutoKey.encrypt(plain_text.getText(), key));
						
				}else if(chckbxmntmPlayfair.isSelected()){
				
						String key=key_text.getText();
						if(key.equals(null)){
							JOptionPane.showMessageDialog(null, "the key is empty");
							return;
						}
						cipher_text.setText(Playfair.encrypt(plain_text.getText(), key));
				}else if(chckbxmntmRielfence.isSelected()){
					
						int key=Integer.parseInt(key_text.getText());
						if(key==0){
							JOptionPane.showMessageDialog(null, "the key is empty");
							return;
						}
						cipher_text.setText(rielFence.RielFence.encrypt(plain_text.getText(), key));
				}else if(chckbxmntmRowtransposition.isSelected()){
					
				
						int key[]=new int [key_text.getText().length()];
						
						int i=0 ; while (i<key_text.getText().length()){key[i]=(int)key_text.getText().charAt(i++);}
						if(key.length<0){
							JOptionPane.showMessageDialog(null, "the key is empty");
							return;
						}
						cipher_text.setText(RowTransposition.encrypt(plain_text.getText(), key));
				}else if(chckbxmntmVigenere.isSelected()){
					
				
						String key=key_text.getText();
						if(key.equals(null)){
							JOptionPane.showMessageDialog(null, "the key is empty");
							return;
						}
						cipher_text.setText(vigenere.Vignere.encrypt(plain_text.getText(), key));
					
				}else if(chckbxmntmAes.isSelected()){
			
						String key=key_text.getText();
						if(key.equals(null)){
							JOptionPane.showMessageDialog(null, "the key is empty");
							return;
						}
						try {
							cipher_text.setText(AES.FINAL_AES.encrypt(plain_text.getText(), key));
						} catch (Exception e1) {
							// TODO Auto-generated catch block
						   JOptionPane.showMessageDialog(null, e1.getMessage());
						}
					
				}else if(chckbxmntmDes.isSelected()){
					
						String key=key_text.getText();
						if(key.equals(null)){
							JOptionPane.showMessageDialog(null, "the key is empty");
							return;
						}
						try {
							DES.STR_DES t = new STR_DES();
							cipher_text.setText(t.encrypt(plain_text.getText(), key));
						} catch (UnsupportedEncodingException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				
			}else if(chckbxmntmMonoalphabetic.isSelected()) {
	
				String key=key_text.getText();
					if(key.equals(null)){
						JOptionPane.showMessageDialog(null, "the key is empty");
						return;
					}
			
				//
					cipher_text.setText(Monoalphabetic.encrypt(plain_text.getText()).get_left());
					key_text.setText(Monoalphabetic.encrypt(plain_text.getText()).get_right());
			}else{
				JOptionPane.showMessageDialog(null,"you didn't select an algorithm");
			}
			}
		});
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(chckbxmntmCeaser.isSelected()){
					int key=Integer.parseInt(key_text.getText());
					if(key==0){
						JOptionPane.showMessageDialog(null, "the key is empty");
						return;
					}
					plain_text.setText(ceaser.Caesar.decrypt(cipher_text.getText(), key));
				}else if(chckbxmntmAutokey.isSelected()){
					
	
						String key=key_text.getText();
						if(key.equals(null)){
							JOptionPane.showMessageDialog(null, "the key is empty");
							return;
						}
						plain_text.setText(AutoKey.decrypt(cipher_text.getText(), key));
						
				}else if(chckbxmntmPlayfair.isSelected()){
				
						String key=key_text.getText();
						if(key.equals(null)){
							JOptionPane.showMessageDialog(null, "the key is empty");
							return;
						}
						plain_text.setText(Playfair.decrypt(cipher_text.getText(), key));
				}else if(chckbxmntmRielfence.isSelected()){
					
						int key=Integer.parseInt(key_text.getText());
						if(key==0){
							JOptionPane.showMessageDialog(null, "the key is empty");
							return;
						}
						plain_text.setText(rielFence.RielFence.decrypt(cipher_text.getText(), key));
				}else if(chckbxmntmRowtransposition.isSelected()){
					
				
						int key[]=new int [key_text.getText().length()];
						
						int i=0 ; while (i<key_text.getText().length()){key[i]=(int)key_text.getText().charAt(i++);}
						if(key.length<0){
							JOptionPane.showMessageDialog(null, "the key is empty");
							return;
						}
						plain_text.setText(RowTransposition.decrypt(cipher_text.getText(), key));
				}else if(chckbxmntmVigenere.isSelected()){
					
				
						String key=key_text.getText();
						if(key.equals(null)){
							JOptionPane.showMessageDialog(null, "the key is empty");
							return;
						}
						plain_text.setText(vigenere.Vignere.decrypt(cipher_text.getText(), key));
					
				}else if(chckbxmntmAes.isSelected()){
			
						String key=key_text.getText();
						if(key.equals(null)){
							JOptionPane.showMessageDialog(null, "the key is empty");
							return;
						}
						try {
							plain_text.setText(AES.FINAL_AES.decrypt(cipher_text.getText(), key));
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null,e1.getMessage());
						}
					
				}else if(chckbxmntmDes.isSelected()){
					
						String key=key_text.getText();
						if(key.equals(null)){
							JOptionPane.showMessageDialog(null, "the key is empty");
							return;
						}
						try {
							DES.STR_DES t = new STR_DES();
							plain_text.setText(t.decrypt(cipher_text.getText(), key));
						} catch (UnsupportedEncodingException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				
			}else if(chckbxmntmMonoalphabetic.isSelected()) {
	
				String key=key_text.getText();
					if(key.equals(null)){
						JOptionPane.showMessageDialog(null, "the key is empty");
						return;
					}
			
				//
					plain_text.setText(Monoalphabetic.decrypt(cipher_text.getText(),key_text.getText()));
					
			}else{
				JOptionPane.showMessageDialog(null,"you didn't select an algorithm");
			}
			
			}
		});
		}
	}
