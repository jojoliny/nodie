package entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.List;

import constant.ConstantParam;
import constant.EntitySingleton;
import utils.ImageUtil;
import utils.MusicPlayTool;
import utils.ThreadUtil;

public class SmallPerson extends Rectangle implements Runnable {

	public SmallPerson(int y, int k) {
		this.x = ConstantParam.P_X;
		this.y = y;
		this.width = 30;
		this.height = 46;
		P1 = ImageUtil.getImage("img/跑步的小人1.gif");
		P2 = ImageUtil.getImage("img/跑步的小人2.gif");
		P3 = ImageUtil.getImage("img/跑步的小人3.gif");
		this.k = k;
		/** 开启自己 **/
		new Thread(this).start();

	}

	/** 画自己 **/
	public void drawP1(Graphics g) {
		g.drawImage(P1, x, y, null);
	}

	public void drawP2(Graphics g) {
		g.drawImage(P2, x, y, null);
	}

	public void drawP3(Graphics g) {
		g.drawImage(P3, x, y, null);
	}

	Image P1;
	Image P2;
	Image P3;
	int k;
	public int speedY = 15;
	public boolean isUp1 = false;
	public boolean isUp2 = false;
	public boolean isUp3 = false;
	public boolean isJump = false;
	public boolean isWork = false;

	public void jump(int ky) {
		new Thread() {
			public void run() {
				for (int i = 0; i < 7; i++) {
					if (!isHit()) {
						ThreadUtil.sleep(100);
						y -= speedY;
						isWork = true;
					} else
						break;
				}

				for (int i = 0; y < ky; i++) {
					if (!isHit()) {
						ThreadUtil.sleep(100);
						y += speedY;
						isWork = true;
					} else
						break;
				}
				isWork = false;
			}
		}.start();
	}

	public boolean isHit() {
		Rectangle rectangle = null;
		rectangle = new Rectangle(x + ConstantParam.briSpeed, y, width, height);

		List<Brick> entitys = EntitySingleton.getInstance().getBrick();
		for (int i = 0; i < entitys.size(); i++) {
			Brick entity = entitys.get(i);

			// System.out.println(entity);
			// System.out.println(this);
			boolean isIntersects = entity.intersects(rectangle);
			if (isIntersects) // 撞到了
			{
				die();
				return true;
			}
		}
		return false;
	}

	private void die() {
		ConstantParam.Game_Is_Die = true;
	}

	@Override
	public void run() {
		while (true) {
			ThreadUtil.sleep(10);
			isHit();
			if (!isWork) {
				if ((isUp1 && k == 1) || (isUp2 && k == 2) || (isUp3 && k == 3)) {
					new MusicPlayTool("music/jump.wav", false);
					if (isJump == false) {
						if (k == 1) {
							jump(ConstantParam.P1_Y);
						}
						if (k == 2) {
							jump(ConstantParam.P2_Y);
						}
						if (k == 3) {
							jump(ConstantParam.P3_Y);

						}
						isJump = true;
					}
				} else
					isJump = false;
			}
		}
	}

	@Override
	public String toString() {
		return "SmallPerson [x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + "]";
	}

}
