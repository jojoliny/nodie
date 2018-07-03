package utils;

import constant.ConstantParam;

public class ThreadUtil {

	public static void sleep(long millis) {
		// 暂停处理
		while (ConstantParam.Game_Is_Pause || ConstantParam.Game_Is_Die) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// 统一异常处理
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
