package listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import constant.ConstantParam;
import constant.Start;
import ui.GameFrame;
import ui.StartFrame;

public class MyMouseListener implements MouseListener {
	StartFrame sf;

	public MyMouseListener(StartFrame sf) {
		this.sf = sf;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// 180*60
		int a = (ConstantParam.backX - 180) / 2;
		int b = 230;
		if (e.getX() > a && e.getX() < a + 180 && e.getY() > b && e.getY() < b + 60) {
			StartFrame.isOn = true;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (StartFrame.isOn) {
			Start.sf.dispose();
			GameFrame gf = new GameFrame("一个都不能死", ConstantParam.backX, ConstantParam.backY, null);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
