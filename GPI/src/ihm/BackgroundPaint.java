package ihm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BackgroundPaint {

	private Graphics g;
	private Image tiles;
	
	public BackgroundPaint(Graphics g) {
		this.g = g;
		try {
            tiles = ImageIO.read(new File("ressources/img/tileset.png"));
        } catch (IOException e) {
            e.printStackTrace();
        } 
	}
	
	public void paint(char[][] map) {
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
		g.setColor(Color.white);
		//g.drawString(i + "," + j, j*30, i*30+15);
	}
}
