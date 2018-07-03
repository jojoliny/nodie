package ui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;

import constant.ConstantParam;
import listener.MyMouseListener;
import utils.ImageUtil;
import utils.ThreadUtil;

/**
 * @author LY ��ʼ����
 */
public class StartFrame extends ParentFrame {

	// ����
	Image headLine;
	// ����
	Image dbLine;
	// ��ʼ
	Image startGame1;
	Image startGame2;
	Image currentStart;
	public static boolean isOn = false;
	MyMouseListener ml;

	public StartFrame(String title, int x, int y, LayoutManager manager) {
		super(title, x, y, manager);

		headLine = ImageUtil.getImage("img/��ҳ����.gif");
		dbLine = ImageUtil.getImage("img/��ҳ����.gif");
		startGame1 = ImageUtil.getImage("img/��ť�ͷ�.gif");
		startGame2 = ImageUtil.getImage("img/��ť�ƹ�.gif");
		currentStart = startGame1;
		new Thread() {
			public void run() {
				while (true) {
					ThreadUtil.sleep(100);
					repaint();
				}
			}
		}.start();
	}

	public void initView() {
		if (isOn == true)
			currentStart = startGame2;
	}

	public void initListen() {

	}

	public void initDate() {
		ml = new MyMouseListener(this);
		this.addMouseListener(ml);
	}

	public void paint(Graphics g) {
		Image img = createImage(getWidth(), getHeight());
		Graphics gg = img.getGraphics();
		int headX = (ConstantParam.backX - 468) / 2;
		gg.drawImage(headLine, headX, 50, null);
		int dbX = (ConstantParam.backX - 430) / 2;
		gg.drawImage(dbLine, headX, 210, null);

		gg.drawImage(currentStart, (ConstantParam.backX - 180) / 2, 230, null);
		g.drawImage(img, 0, 0, null);

	}

}
