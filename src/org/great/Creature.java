package org.great;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 * 统一封装场景中出现角色的共同属性
 * 植物、僵尸、阳光 等的父类
 */
public class Creature {
	// 图片 和 坐标
	BufferedImage creature;//图片
	int x;
	int y;
	//画
	public void draw(Graphics g) {
		g.drawImage(creature, x, y, 200,172,null);
	}

}
