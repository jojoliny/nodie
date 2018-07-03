package entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import constant.ConstantParam;
import utils.ThreadUtil;

/**
 * 
 * @author LYY ÕÏ°­Îï ×©¿é
 */
public class Brick extends Rectangle implements Runnable {
	// Image img;
	// µ±Ç°Î»ÖÃ
	public static int currentX;

	public Brick(int trackY) {
		x = ConstantParam.backX;
		width = 5 + (int) (Math.random() * 5);
		height = 40 + (int) (Math.random() * 20);
		y = trackY - height;
		new Thread(this).start();
	}

	/** »­×©¿édrawBrick(»­±Ê,×ø±êY) **/
	public void drawBrick(Graphics g) {
		// fillRect(int x, int y, int width, int height);
		g.fillRect(x, y, width, height);
	}

	@Override
	public void run() {
		while (true) {
			ThreadUtil.sleep(10);
			x -= ConstantParam.briSpeed;
		}
	}

	@Override
	public String toString() {
		return "Brick [x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + "]";
	}

}
