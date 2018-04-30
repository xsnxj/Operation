package com.operation.myComponent;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;

import javax.swing.ImageIcon;

/**
 * Ĭ�� BorderLayout
 */
public class PicBackPane extends BackPane {
	Image img;

	public PicBackPane(String picName) {
		super();
		this.setLayout(new BorderLayout());
		img = new ImageIcon(picName).getImage();
		loadImage(img, 0);
	}

	private void loadImage(Image image, int ID) {
		if (image != null) {
			MediaTracker tracker = new MediaTracker(this);
			tracker.addImage(image, ID);
			try {
				tracker.waitForID(ID);
			} catch (InterruptedException _ex) {
			}
		}
	}

	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		// ����͸����
		AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f); // ���һ������������͸���� �� �� ���һ������Ϊ 0.1f
																						// ʱ ���ʵ�͸���Ⱦ�Ϊ90%
		g2d.setComposite(ac);

	}

	@Override
	protected void paintComponent(Graphics g) {
//		draw(g);
		super.paintComponent(g);
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	}
}