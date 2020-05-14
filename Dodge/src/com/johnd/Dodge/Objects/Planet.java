package com.johnd.Dodge.Objects;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.johnd.Dodge.ImageLoader.ImageLoad;

public class Planet extends GameObject {

	private ImageLoad loader = new ImageLoad();

	public Planet(float x, float y, ID id) {
		super(x, y, id);
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(loader.loadImage("planet2"), (int) x, (int) y, 60, 60, null);

	}

	@Override
	public void tick() {

	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 55, 55);
	}

}
