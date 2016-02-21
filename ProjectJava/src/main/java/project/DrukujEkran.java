package project;

import java.awt.Graphics2D;

public interface DrukujEkran {
	void druk(int[] ekranPixels, int x, int y, int kratka);
	void druk(Graphics2D g2, int x, int y, int kratka);
	void druk(Graphics2D g2, int x, int y, int sz, int wy, int kratka);
	void druk(Graphics2D g2, int x, int y, int ix, int iy);
	void druk(Graphics2D g2, int x, int y, int ix, int iy, int szerokosc, int wysokosc);
	void druk(Graphics2D g2, int x, int y, int szerokosc, int wysokosc, int ix, int iy, int iSzerokosc, int iWysokosc);
	void druk(Graphics2D g2, int x, int y);

}
