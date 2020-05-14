package com.johnd.Dodge.Main;

import java.awt.Dimension;

import javax.swing.JFrame;

import com.johnd.Dodge.Objects.GameObject;
import com.johnd.Dodge.Objects.Handler;
import com.johnd.Dodge.Objects.ID;

public class Window {

	private Handler handler;

	public Window(int width, int height, String title, Game game) {
		JFrame frame = new JFrame(title);

		frame.setSize(width, height);
		frame.setMinimumSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.requestFocus();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(game);

		// set the frame visible
		frame.setVisible(true);
	}

}
