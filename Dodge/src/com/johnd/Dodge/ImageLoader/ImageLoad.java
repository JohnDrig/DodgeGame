package com.johnd.Dodge.ImageLoader;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoad {

	public ImageLoad() {

	}

	public BufferedImage loadImage(String name) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("res/" + name + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (image != null) {
			return image;
		} else {
			return null;
		}
	}

	public BufferedImage loadImageJPG(String name) {
		BufferedImage image = null;

		try {
			image = ImageIO.read(new File("res/" + name + ".jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (image != null) {
			return image;
		} else {
			return null;
		}

	}

}
