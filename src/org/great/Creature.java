package org.great;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 * ͳһ��װ�����г��ֽ�ɫ�Ĺ�ͬ����
 * ֲ���ʬ������ �ȵĸ���
 */
public class Creature {
	// ͼƬ �� ����
	BufferedImage creature;//ͼƬ
	int x;
	int y;
	//��
	public void draw(Graphics g) {
		g.drawImage(creature, x, y, 200,172,null);
	}

}
