package processing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import data.Guardian;
import data.Prisoner;

public class PaintVisitor implements Visitor {

	private Graphics g;
	private Image persos;
	private Image tiles;
	
	public PaintVisitor(Graphics g) {
		this.g = g;
		try {
            tiles = ImageIO.read(new File("GPI/ressources/img/tileset.png"));
        } catch (IOException e) {
            e.printStackTrace();
        } 
	}
	
	public void visit(Prisoner prisoner) {
		g.setColor(Color.ORANGE);
		g.fillRect(prisoner.getPos()[1]*30, prisoner.getPos()[0]*30, 30, 30);
	}
	
	public void visit(Guardian guardian) {
		g.setColor(Color.BLUE);
		g.fillRect(guardian.getPos()[1]*30, guardian.getPos()[0]*30, 30, 30);
	}
	
	public void visit(char[][] map) {
		for(int i = 0; i < 20; i ++)
			for(int j = 0; j < 20; j ++)
				paintImage(map[i][j], i, j);
	}
	
	private void paintImage(char c, int i, int j) {
		int x = 0;
		int y = 0;
		
		if(c == 'd') {
			g.drawImage(tiles, j*30, i*30, j*30+30, i*30+30, x, y, x+30, y+30, null);
			x = 30;
		}
		else if(c == 'w')
			x = 60;
		g.drawImage(tiles, j*30, i*30, j*30+30, i*30+30, x, y, x+30, y+30, null);
	}
	
}
