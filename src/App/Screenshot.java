package App;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Screenshot {
	private DaemonThread myThread = null;
    int count = 0;
    VideoCapture webSource = null;

    Mat framer = new Mat();
    MatOfByte mem = new MatOfByte();
    private JFrame frame;
    JPanel panel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		 System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Screenshot window = new Screenshot();
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
	public Screenshot() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 664, 417);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		 panel = new JPanel();
		panel.setBounds(12, 12, 626, 315);
		frame.getContentPane().add(panel);
		
		JButton btnStart = new JButton("Start");

		btnStart.setBounds(12, 339, 117, 25);
		frame.getContentPane().add(btnStart);
		
		JButton btnPause = new JButton("Pause");
		btnPause.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				myThread.runnable = false;
	            btnPause.setEnabled(false);   
	            btnStart.setEnabled(true);
	            
	            webSource.release();			

			}
		});
		btnPause.setBounds(184, 339, 117, 25);
		frame.getContentPane().add(btnPause);
		
		JButton btnCapture = new JButton("Capture");
		btnCapture.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			        
			        Imgcodecs.imwrite("", framer);
			    
			}
		});
		btnCapture.setBounds(421, 339, 117, 25);
		frame.getContentPane().add(btnCapture);
		
		btnStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				webSource =new VideoCapture(0);
				  myThread = new DaemonThread();
				            Thread t = new Thread(myThread);
				            t.setDaemon(true);
				            myThread.runnable = true;
				            t.start();
							 btnStart.setEnabled(false);  //start button
				            btnPause.setEnabled(true);  // stop button
			}
		});
	}
	
	 class DaemonThread implements Runnable
	    {
	    protected volatile boolean runnable = false;

	    @Override
	    public  void run()
	    {
	        synchronized(this)
	        {
	            while(runnable)
	            {
	                if(webSource.grab())
	                {
			    	try
	                        {
	                            webSource.retrieve(framer);
				    Imgcodecs.imencode(".bmp", framer, mem);
				    Image im = ImageIO.read(new ByteArrayInputStream(mem.toArray()));

				    BufferedImage buff = (BufferedImage) im;
				    Graphics g=panel.getGraphics();

				    if (g.drawImage(buff, 0, 0, panel.getWidth(), panel.getHeight() -150 , 0, 0, buff.getWidth(), buff.getHeight(), null))
				    
				    if(runnable == false)
	                            {
				    	System.out.println("Going to wait()");
				    	this.wait();
				    }
				 }
				 catch(Exception ex)
	                         {
				    System.out.println("Error");
	                         }
	                }
	            }
	        }
	     }
	   }
}
