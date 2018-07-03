package ui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;

import constant.ConstantParam;
import constant.EntitySingleton;
import entity.Brick;
import entity.SmallPerson;
import listener.MyKeyListener;
import utils.MusicPlayTool;
import utils.ThreadUtil;

public class GameFrame extends ParentFrame {
	SmallPerson sP1;
	SmallPerson sP2;
	SmallPerson sP3;
	Brick brick;
	public MusicPlayTool musicPlayTool;

	public GameFrame(String title, int x, int y, LayoutManager manager) {
		super(title, x, y, manager);
		musicPlayTool = new MusicPlayTool("music/bg.wav", true);
		new Thread() {
			public void run() {
				while (true) {
					ThreadUtil.sleep(10);
					if (ConstantParam.Game_Is_Die) {
						musicPlayTool.stopMusic();
						new MusicPlayTool("music/die.wav", false);
					}
					repaint();
				}
			}
		}.start();
	}

	public void initView() {

	}

	public void initListen() {
		MyKeyListener listener1 = new MyKeyListener(sP1, sP2, sP3);
		this.addKeyListener(listener1);
	}

	/*
	 * @see com.ly.mario.ui.ParentFrame#initDate()
	 */
	public void initDate() {
		sP1 = new SmallPerson((ConstantParam.P1_Y), 1);
		sP2 = new SmallPerson((ConstantParam.P2_Y), 2);
		sP3 = new SmallPerson((ConstantParam.P3_Y), 3);

	}

	public void paint(Graphics g) {
		/** ¶þ¼¶»º´æ **/
		Image img = createImage(getWidth(), getHeight());
		Graphics img_g = img.getGraphics();
		sP1.drawP1(img_g);
		sP2.drawP2(img_g);
		sP3.drawP3(img_g);
		for (int i = 0; i < EntitySingleton.getInstance().getBrick().size(); i++) {
			Brick brick = EntitySingleton.getInstance().getBrick().get(i);
			brick.drawBrick(img_g);
		}
		for (int i = 0; i < EntitySingleton.getInstance().getBrick().size(); i++) {
			Brick brick = EntitySingleton.getInstance().getBrick().get(i);
			if (brick.x < 0)
				EntitySingleton.getInstance().getBrick().remove(brick);
		}
		img_g.fillRect(0, ConstantParam.track1, getWidth(), 6);
		img_g.fillRect(0, ConstantParam.track2, getWidth(), 6);
		img_g.fillRect(0, ConstantParam.track3, getWidth(), 6);
		g.drawImage(img, 0, 0, null);

	}

}
