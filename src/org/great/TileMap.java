package org.great;

import java.util.ArrayList;
import java.util.List;

//�����࣬ά����ǰ��������������ļ���
public class TileMap {
	// ��������ļ���
	List<Creature> creatures;

	public TileMap() {
		creatures = new ArrayList<Creature>();
	}

	// ���һ������
	public void addCreature(Creature creature) {
		synchronized (creatures) {// �߳���
			creatures.add(creature);
		}

	}

	// ɾ��һ������
	public void remove(Creature creature) {
		synchronized (creatures) {
			creatures.remove(creature);
		}

	}

}
