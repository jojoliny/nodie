package constant;

import ui.StartFrame;

public class Start {
	public static StartFrame sf;

	public static void main(String[] args) {
		// GameFrame gf = new GameFrame("一个都不能死", ConstantParam.backX,
		// ConstantParam.backY, null);
		sf = new StartFrame("一个都不能死", ConstantParam.backX, ConstantParam.backY, null);
	}
}
