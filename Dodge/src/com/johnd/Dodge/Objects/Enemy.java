package com.johnd.Dodge.Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JProgressBar;

import com.johnd.Dodge.ImageLoader.ImageLoad;
import com.johnd.Dodge.Main.Game;

public class Enemy extends GameObject {

	private ImageLoad loader = new ImageLoad();

	private boolean direction = true;

	public Enemy(int x, int y, ID id) {
		super(x, y, id);

		velX = 3f;

	}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(255, 255, 255, 0));

		g.drawImage(loader.loadImage("asteroid"), (int) x, (int) y, 50, 50, null);

	}

	@Override
	public void tick() {
		if (x + 30 >= Game.WIDTH) {
			direction = false;
		}

		if (x <= 0) {
			direction = true;
		}

		if (direction) {
			x += velX;
		} else if (!direction) {
			x -= velX;
		}
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 50, 50);
	}

}
