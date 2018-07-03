package constant;

import java.util.ArrayList;

import entity.Brick;
import utils.ThreadUtil;

/**
 * 
 * @author LYY 单例模式 懒汉
 *
 */

public class EntitySingleton {
	/** 第一步：不能给别人new **/
	private EntitySingleton() {

	}

	static EntitySingleton instance;

	ArrayList<Brick> list;
	public int time;

	public ArrayList<Brick> getBrick() {
		if (list == null) {
			list = new ArrayList<Brick>();
			new Thread() {
				public void run() {
					while (true) {
						time = (int) (1000 + (Math.random() * 1000));
						ThreadUtil.sleep(time);
						Brick brick1 = new Brick(ConstantParam.track1);
						list.add(brick1);
						ThreadUtil.sleep(time - 1000);
						Brick brick2 = new Brick(ConstantParam.track2);
						list.add(brick2);
						ThreadUtil.sleep(time - 500);
						Brick brick3 = new Brick(ConstantParam.track3);
						list.add(brick3);
					}

				}
			}.start();
		}
		return list;
	}

	public static EntitySingleton getInstance() {
		if (instance == null) {
			synchronized (EntitySingleton.class) {
				if (instance == null) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					instance = new EntitySingleton();
				}
			}
		}
		return instance;
	}

}
