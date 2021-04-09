package ihm;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Menu extends JPanel{
	
	private JFrame frame ;
	private JPanel menuPanel ;
	private JButton buttonStart ;
	
	private JButton buttonAddP ;
	private JButton buttonSuppP ;
	private JLabel printNbG ;
	private JLabel ImgG ;
	
	
	private JButton buttonAddG ;
	private JButton buttonSuppG ;
	private JLabel printNbP ;
	private JLabel ImgP ;
	
	private JLabel printTitle ;
	
	private GridBagConstraints c ;
	
	private Image add ;
	private Image supp ;
	
	//Variable a send
	
	private int nbrGuardian ;
	private int nbrPrisoner ;
	
	private static final String TITLE = "Guardians" ;
	
	public Menu(){
		frame = new JFrame(TITLE);
		menuPanel = new JPanel();
		
		nbrGuardian = 1 ;
		nbrPrisoner = 1 ;
		
		try {
			add = ImageIO.read(new File("ressources/img/plus.png"));
			supp = ImageIO.read(new File("ressources/img/minus.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		init();
		
		frame.add(menuPanel);
		frame.setSize(616,639);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	
	public void init() {
		menuPanel.setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		
		c.anchor = GridBagConstraints.CENTER;
		c.weightx = 0.2 ;
		c.weighty = 0.2 ;
		
		printTitle = new JLabel(TITLE);
		printTitle.setFont(new Font("Serif", Font.PLAIN, 48));
		c.gridx = 0 ;
		c.gridy = 0 ;
		c.gridwidth = 3 ;
		menuPanel.add(printTitle, c);
		
		c.gridwidth = 1 ;

		buttonSuppG = new JButton() ;
		paintButtonMinus(buttonSuppG);
		buttonSuppG.addActionListener(new InputSuppG());
		c.gridx = 0 ;
		c.gridy = 2 ;
		menuPanel.add(buttonSuppG, c);
		
		printNbG = new JLabel("Guardian : \n" + Integer.toString(nbrGuardian));
		c.gridx = 1 ;
		c.gridy = 2 ;
		menuPanel.add(printNbG, c);
		
		buttonAddG = new JButton();
		paintButtonPlus(buttonAddG);
		buttonAddG.addActionListener(new InputAddG());
		c.weightx = 0.2 ;
		c.gridx = 2 ;
		c.gridy = 2 ;
		menuPanel.add(buttonAddG, c);
		
		buttonSuppP = new JButton() ;
		paintButtonMinus(buttonSuppP);
		buttonSuppP.addActionListener(new InputSuppP());
		c.weightx = 0.2 ;
		c.gridx = 0 ;
		c.gridy = 4 ;
		menuPanel.add(buttonSuppP, c);
		
		printNbP = new JLabel("Prisoner : \n" + Integer.toString(nbrPrisoner));
		c.gridx = 1 ;
		c.gridy = 4 ;
		menuPanel.add(printNbP, c);
		
		buttonAddP = new JButton();
		paintButtonPlus(buttonAddP);
		buttonAddP.addActionListener(new InputAddP());
		c.weightx = 0.2 ;
		c.gridx = 2 ;
		c.gridy = 4 ;
		menuPanel.add(buttonAddP, c);
		
		buttonStart = new JButton("Start");
		buttonStart.addActionListener(new InputStart());
		c.gridx = 1 ;
		c.gridy = 5 ;
		menuPanel.add(buttonStart, c);
	}
	
	public void paintButtonPlus(JButton b) {
		b.setIcon(new ImageIcon(add));
		b.setMargin(new Insets(0, 0, 0, 0));
		// to add a different background
		b.setContentAreaFilled(false);
		// to remove the border
		b.setBorder(null);
	}
	
	public void paintButtonMinus(JButton b) {
		b.setIcon(new ImageIcon(supp));
		b.setMargin(new Insets(0, 0, 0, 0));
		// to add a different background
		b.setContentAreaFilled(false);
		// to remove the border
		b.setBorder(null);
	}
	
	public class InputStart implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			frame.remove(menuPanel);
			frame.repaint();
			Simulation sim = new Simulation(frame, nbrGuardian, nbrPrisoner);
			Thread t = new Thread(sim);
			t.start();
		}
		
	}
	
	public class InputAddG implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(nbrGuardian < 50) {
				nbrGuardian ++ ;
//				System.out.println(nbrGuardian);
				printNbG.setText("Guardian : \n" + Integer.toString(nbrGuardian));
				printNbG.repaint();
			}
		}
		
	}
	
	public class InputAddP implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(nbrPrisoner < 50) {
				nbrPrisoner ++ ;
//				System.out.println(nbrPrisoner);
				printNbP.setText("Prisoner : \n" + Integer.toString(nbrPrisoner));
				printNbP.repaint();
			}
		}
		
	}
	
	public class InputSuppG implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(nbrGuardian > 1) {
				nbrGuardian -- ;
//				System.out.println(nbrGuardian);
				printNbG.setText("Guardian : \n" + Integer.toString(nbrGuardian));
				printNbG.repaint();
			}
		}
		
	}
	
	public class InputSuppP implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(nbrPrisoner > 1) {
				nbrPrisoner -- ;
//				System.out.println(nbrPrisoner);
				printNbP.setText("Prisoner : \n" + Integer.toString(nbrPrisoner));
				printNbP.repaint();
			}
		}
		
	}
	
	public static void main(String []args) {
		new Menu();
	}

}

