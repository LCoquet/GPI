package ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * @brief Class IHM for the end menu
 * 
 * @author CHABOT Yohan, COQUET Leo, DE SOUSA Julia, GASTEBOIS Emma, HANG Alexandre, POUPET Maria-Lorena
 *
 */

public class EndGame extends JPanel {
	
	JFrame frame ;
	
	JPanel endMenu;
	
	JLabel printTime ;
	JLabel printNbCaught;
	JLabel printNbEscaped;
	
	JButton restartB;
	
	private Image add ;
	private Image supp ;
	private Image human ;
	private Image background ;

	public EndGame(JFrame f, int nbCaught, int nbEscaped, String time) {
		
		try {
			add = ImageIO.read(new File("ressources/img/plus.png"));
			supp = ImageIO.read(new File("ressources/img/minus.png"));
			human = ImageIO.read(new File("ressources/img/humans.png"));
			background = ImageIO.read(new File("ressources/img/Background.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		frame = f ;
		
		endMenu = this ;
		endMenu.setLayout(null);

		printTime = new JLabel(time);
		printTime.setFont(new Font("Serif", Font.PLAIN, 34));
		printTime.setForeground(Color.WHITE);
		
		Dimension sText = printTime.getPreferredSize();
		printTime.setBounds(frame.getWidth()/2 - sText.width/2, frame.getHeight()/2 - 200, sText.width, sText.height);
		endMenu.add(printTime);
		
		printNbCaught = new JLabel("Prisonniers attrapés : " + Integer.toString(nbCaught));
		printNbCaught.setFont(new Font("Serif", Font.PLAIN, 34));
		printNbCaught.setForeground(Color.WHITE);
		
		Dimension size = printNbCaught.getPreferredSize();
		printNbCaught.setBounds(frame.getWidth()/2 - size.width/2, frame.getHeight()/2 - 50, size.width, size.height);
		endMenu.add(printNbCaught);
		
		printNbEscaped = new JLabel("Prisonniers échappés : " + Integer.toString(nbEscaped));
		printNbEscaped.setFont(new Font("Serif", Font.PLAIN, 34));
		printNbEscaped.setForeground(Color.WHITE);
		
		Dimension size2 = printNbEscaped.getPreferredSize();
		printNbEscaped.setBounds(frame.getWidth()/2 - size2.width/2, frame.getHeight()/2 - 150, size2.width, size2.height);
		endMenu.add(printNbEscaped);
		
		restartB = new JButton("Rejouer");
		restartB.addActionListener(new InputRestart());
		
		Dimension s = restartB.getPreferredSize();
		restartB.setBounds(frame.getWidth()/2 - s.width/2, frame.getHeight()/2 + 50, s.width, s.height);
		endMenu.add(restartB);
		
		
		frame.add(endMenu);
		frame.setSize(616,639);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	
	
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		g.drawImage(background, 0, 0, frame.getSize().width, frame.getSize().height, 0, 0, frame.getSize().width, frame.getSize().height, null);
		g.drawImage(human, frame.getWidth()/2 - 15, frame.getHeight()/2 - 100 , frame.getWidth()/2 + 15, frame.getHeight()/2 - 70, 0, 0, 30, 30, null);
		g.drawImage(human,frame.getWidth()/2 - 15, frame.getHeight()/2 , frame.getWidth()/2 + 15, frame.getHeight()/2 + 30, 30, 0, 60, 30, null);
	}
	
	public class InputRestart implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			frame.remove(endMenu);
			frame.repaint();
			new Menu(frame);
		}
		
	}
}
