package com.johnd.Dodge.Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.johnd.Dodge.ImageLoader.ImageLoad;

public class Bullet extends GameObject {

	private ImageLoad loader = new ImageLoad();
	
	private Handler handler;

	public Bullet(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		velY = 7;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.RED);

		g.drawImage(loader.loadImage("bullet"), (int) x, (int) y, 10, 30, null);
	}

	@Override
	public void tick() {
		y -= velY;
		
		if(y < 0) {
			handler.removeObject(this);
		}
		
		//Collision
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.ENEMY) {
				if(getBounds().intersects(tempObject.getBounds())) {
					handler.removeObject(tempObject);
				}
			}
		}
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 20, 20);
	}

}
