package com.johnd.Dodge.Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.johnd.Dodge.Objects.Bullet;
import com.johnd.Dodge.Objects.GameObject;
import com.johnd.Dodge.Objects.Handler;
import com.johnd.Dodge.Objects.ID;

public class KeyHandler implements KeyListener {

	public boolean key[] = new boolean[5];

	public static boolean isShooting = false;

	private Handler handler;

	public KeyHandler(Handler handler) {
		this.handler = handler;
	}

	/*
	 * 0 = up 1 = down 2 = right 3 = left 5 = shoot
	 */

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W) {
			key[0] = true;
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			key[1] = true;
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			key[2] = true;
		} else if (e.getKeyCode() == KeyEvent.VK_A) {
			key[3] = true;
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE && !isShooting) {
			isShooting = true;

			for (int i = 0; i < handler.object.size(); i++) {
				GameObject tempObject = handler.object.get(i);

				if (tempObject.getId() == ID.PLAYER) {
					handler.addObject(
							new Bullet(handler.object.get(i).getX() + 20, handler.object.get(i).getY(), ID.BULLET, handler));
				}

			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W) {
			key[0] = false;
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			key[1] = false;
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			key[2] = false;
		} else if (e.getKeyCode() == KeyEvent.VK_A) {
			key[3] = false;
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			isShooting = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
