package gameState;

import main.*;
import tilemap.*;
import entity.*;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Level1State extends GameState {

	private TileMap tileMap;
	private Background bg;

	Robot r;
	int c;

	private Player player;

	public Level1State(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}

	public void init() {

		c = 0;
		try {
			r = new Robot();
		} catch (AWTException e) {
			throw new RuntimeException(e);
		}

		tileMap = new TileMap(16);
		tileMap.loadTiles("/Tilesets/sunny_tileset.gif");
		tileMap.loadMap("/Maps/level1.map");
		tileMap.setPosition(0, 0);
		tileMap.setTween(1);

		bg = new Background("/Backgrounds/back.png", 0.1);

		player = new Player(tileMap);
		player.setPosition(160, 160);

	}

	public void update() {
		if (c == 30) {
			double movement = Game.brain.getOutput();
			switch ((int)movement) {
				case 0:
					r.keyPress(KeyEvent.VK_D);
					r.keyPress(KeyEvent.VK_W);
					break;
				case 1:
					r.keyPress(KeyEvent.VK_D);
			}
			c = 0;
		} else if (c == 29) {
			r.keyRelease(KeyEvent.VK_D);
			r.keyRelease(KeyEvent.VK_W);
			c++;
		} else {
			c++;
		}

		// update player
		player.update();
		tileMap.setPosition(GamePanel.WIDTH / 2 - player.getx(), GamePanel.HEIGHT / 2 - player.gety());

	}

	public void draw(Graphics2D g) {

		// draw bg
		bg.draw(g);

		// draw tilemap
		tileMap.draw(g);

		// draw player
		player.draw(g);

	}

	public void keyPressed(int k) {
		if (k == KeyEvent.VK_A)
			player.setLeft(true);
		if (k == KeyEvent.VK_D)
			player.setRight(true);
		if (k == KeyEvent.VK_S)
			player.setDown(true);
		if (k == KeyEvent.VK_W)
			player.setJumping(true);
		if (k == KeyEvent.VK_UP)
			player.setJumping(true);
		if (k == KeyEvent.VK_RIGHT)
			player.setRight(true);
		if (k == KeyEvent.VK_DOWN)
			player.setDown(true);
		if (k == KeyEvent.VK_LEFT)
			player.setLeft(true);
	}

	public void keyReleased(int k) {
		if (k == KeyEvent.VK_A)
			player.setLeft(false);
		if (k == KeyEvent.VK_D)
			player.setRight(false);
		if (k == KeyEvent.VK_D);
			player.setDown(false);
		if (k == KeyEvent.VK_W)
			player.setJumping(false);
		if (k == KeyEvent.VK_UP)
			player.setJumping(false);
		if (k == KeyEvent.VK_RIGHT)
			player.setRight(false);
		if (k == KeyEvent.VK_DOWN)
			player.setDown(false);
		if (k == KeyEvent.VK_LEFT)
			player.setLeft(false);
	}
}
