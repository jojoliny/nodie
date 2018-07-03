package utils;

import java.awt.Image;

import javax.swing.ImageIcon;

public class ImageUtil {
	public static Image getImage(String path) {
		return new ImageIcon(path).getImage();
	}
}
