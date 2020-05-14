package com.johnd.Dodge.Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import com.johnd.Dodge.ImageLoader.ImageLoad;
import com.johnd.Dodge.Input.KeyHandler;
import com.johnd.Dodge.Main.Game;

public class Player extends GameObject {

	private float acc = 1f;
	private float dcc = 0.5f;

	private ImageLoad loader = new ImageLoad();

	// Instance
	private KeyHandler keyHandler;
	private Handler handler;

	// Variablen
	private int temp = 0;

	public Player(int x, int y, ID id, KeyHandler keyHandler, Handler handler) {
		super(x, y, id);
		this.keyHandler = keyHandler;
		this.handler = handler;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(40, 139, 148, 0));

		g.drawImage(loader.loadImage("raumschiff"), (int) x, (int) y, 50, 70, null);

	}

	/*
	 * Movement
	 * 
	 * 0 = rechts, 1 = links, 2 = springen
	 */

	@Override
	public void tick() {
		// Movement
		x += velX;
		y += velY;

		if (keyHandler.key[0]) {
			if (y > -15) {
				velY -= acc;
			} else {
				velY = 0;
			}
		} else if (keyHandler.key[1]) {
			if (y < Game.HEIGHT - 94) {
				velY += acc;
			} else {
				velY = 0;
			}
		} else if (!keyHandler.key[0] && !keyHandler.key[1]) {
			if (velY > 0) {
				velY -= dcc;
			} else if (velY < 0) {
				velY += dcc;
			}
		}

		if (keyHandler.key[2]) {
			if (x < Game.WIDTH - 57) {
				velX += acc;
			} else {
				velX = 0;
			}
		} else if (keyHandler.key[3]) {
			if (x > 0) {
				velX -= acc;
			} else {
				velX = 0;
			}
		} else if (!keyHandler.key[2] && !keyHandler.key[3]) {
			if (velX > 0) {
				velX -= dcc;
			} else if (velX < 0) {
				velX += dcc;
			}
		}

		velX = getMinMaxValue(velX, 5, -5);
		velY = getMinMaxValue(velY, 5, -5);

		// Collision with enemy
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ID.ENEMY) {
				if (getBounds().intersects(tempObject.getBounds())) {
					temp++;

					if (temp > 22) {

						Game.leben = Game.leben - 1;
						temp = 0;

					}

				}
			}
		}

		// Collision with planet
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ID.PLANET) {
				if (getBounds().intersects(tempObject.getBounds())) {
					System.out.println("Du hast gewonnen");
					System.exit(0);
				}
			}
		}

	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 50, 70);
	}

	public float getMinMaxValue(float value, float max, float min) {
		if (value >= max) {
			value = max;
		} else if (value <= min) {
			value = min;
		}

		return value;
	}

}
