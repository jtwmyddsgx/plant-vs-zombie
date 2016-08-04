package org.great;

import java.util.ArrayList;
import java.util.List;

//场景类，维护当前场景里所有物体的集合
public class TileMap {
	// 所有物体的集合
	List<Creature> creatures;

	public TileMap() {
		creatures = new ArrayList<Creature>();
	}

	// 添加一个物体
	public void addCreature(Creature creature) {
		synchronized (creatures) {// 线程锁
			creatures.add(creature);
		}

	}

	// 删除一个物体
	public void remove(Creature creature) {
		synchronized (creatures) {
			creatures.remove(creature);
		}

	}

}
