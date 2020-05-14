package com.johnd.Dodge.Main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;

import com.johnd.Dodge.ImageLoader.ImageLoad;
import com.johnd.Dodge.Input.KeyHandler;
import com.johnd.Dodge.Objects.Bullet;
import com.johnd.Dodge.Objects.Enemy;
import com.johnd.Dodge.Objects.Handler;
import com.johnd.Dodge.Objects.ID;
import com.johnd.Dodge.Objects.Planet;
import com.johnd.Dodge.Objects.Player;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1758654802685304644L;

	// Variables
	public static final int WIDTH = 800, HEIGHT = 600;
	private String title = "Dodge";
	private boolean isRunning = false;
	private Thread thread;
	private int xPlayer = WIDTH / 2 - 25, yPlayer = HEIGHT - 50 - 60;

	public static int leben = 3;

	// Instances
	private Handler handler;
	private KeyHandler keyHandler;
	private ImageLoad loader;

	public Game() {
		Window window = new Window(WIDTH, HEIGHT, title, this);

		init();
		start();

	}

	public void init() {
		handler = new Handler();

		keyHandler = new KeyHandler(handler);

		loader = new ImageLoad();

		this.addKeyListener(keyHandler);

		handler.addObject(new Player(xPlayer, yPlayer, ID.PLAYER, keyHandler, handler));

		handler.addObject(new Enemy(30, 400, ID.ENEMY));
		handler.addObject(new Enemy(400, 300, ID.ENEMY));
		handler.addObject(new Enemy(10, 200, ID.ENEMY));
		handler.addObject(new Enemy(700, 100, ID.ENEMY));
		handler.addObject(new Enemy(400, 400, ID.ENEMY));
		handler.addObject(new Enemy(100, 300, ID.ENEMY));
		handler.addObject(new Enemy(600, 200, ID.ENEMY));
		handler.addObject(new Enemy(200, 100, ID.ENEMY));
		// Planet/Ziel
		handler.addObject(new Planet(WIDTH / 2 - 30, 10, ID.PLANET));

		if (keyHandler.key[4]) {
			handler.addObject(new Bullet(200, 200, ID.BULLET, handler));
		}

	}

	@Override
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double fps = 60.0;
		double ns = 1000000000 / fps;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;

		while (isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frames = 0;
			}
		}
		stop();
	}

	public synchronized void start() {
		if (isRunning) {
			return;
		}

		thread = new Thread(this);
		thread.start();
		isRunning = true;
	}

	public synchronized void stop() {
		if (!isRunning) {
			return;
		}

		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void tick() {
		handler.tick();
	}

	public void render() {
		BufferStrategy bs = this.getBufferStrategy();

		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		Graphics2D gd = (Graphics2D) g;

		gd.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		//draw
		g.drawImage(loader.loadImageJPG("background"), 0, 0, WIDTH, HEIGHT, null);

		g.setColor(Color.WHITE);
		g.drawLine(0, 40, 110, 40);

		g.drawLine(110, 40, 160, 0);

		g.setColor(Color.WHITE);
		g.setFont(new Font("serif", Font.BOLD, 17));
		g.drawString("Leben: " + leben, 50, 20);

		handler.render(g);

		//show
		bs.show();
		g.dispose();
	}

	public static void main(String[] args) {
		new Game();
	}

}
