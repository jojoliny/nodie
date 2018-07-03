package listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import constant.ConstantParam;
import entity.SmallPerson;

public class MyKeyListener implements KeyListener {
	SmallPerson sp1;
	SmallPerson sp2;
	SmallPerson sp3;
	public int keyCode;

	public MyKeyListener(SmallPerson sp1, SmallPerson sp2, SmallPerson sp3) {
		this.sp1 = sp1;
		this.sp2 = sp2;
		this.sp3 = sp3;
	}

	// 键入某个键时调用此方法。
	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// char keyChar = e.getKeyChar();
		// System.out.println(keyChar);
		keyCode = e.getKeyCode();
		// System.out.println(keyCode);
		if (keyCode == 83)// s
			sp1.isUp1 = true;
		if (keyCode == 74)// j
			sp2.isUp2 = true;
		if (keyCode == 75)// k
			sp3.isUp3 = true;
		if (keyCode == 32)// k
			ConstantParam.Game_Is_Pause = !ConstantParam.Game_Is_Pause;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == 83)// s
			sp1.isUp1 = false;
		if (keyCode == 74)// j
			sp2.isUp2 = false;
		if (keyCode == 75)// k
			sp3.isUp3 = false;
	}

}
