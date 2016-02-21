package project;

import project.SpriteLoad;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;

public class Sprite implements DrukujEkran {

   public SpriteLoad sprite;
   public int[] paleta = new int[4];
   public boolean odbicieX = false;
   public boolean odbicieY = false;
   public int szEkran;
   public int wyEkran;
   public int szMapy;
   public int wyMapy;
   public int szSprite;
   public int wySprite;
   public int ileSprite;


   public Sprite(SpriteLoad sprite, int szS, int wyS) {
      this.sprite = sprite;
      this.szSprite = szS;
      this.wySprite = wyS;
      this.szMapy = sprite.szerokosc / szS;
      this.wyMapy = sprite.wysokosc / wyS;
      this.ileSprite = this.wyMapy * this.szMapy;
   }

   public void setSprite(int szS, int wyS) {
      this.szSprite = szS;
      this.wySprite = wyS;
      this.szMapy = this.sprite.szerokosc / szS;
      this.wyMapy = this.sprite.wysokosc / wyS;
      this.ileSprite = this.wyMapy * this.szMapy;
   }

   public void setEkran(int sz, int wy) {
      this.szEkran = sz;
      this.wyEkran = wy;
   }

   public void druk(int[] ekranPixels, int x, int y, int kratka) {
      int xKratka = kratka % this.szMapy;
      int yKratka = kratka / this.szMapy;
      int kratkaOffset = xKratka * this.szSprite + yKratka * this.wySprite * this.sprite.szerokosc;

      for(int yy = 0; yy < this.wySprite; ++yy) {
         int ySprite = yy;
         if(this.odbicieY) {
            ySprite = this.wySprite - 1 - yy;
         }

         if(yy + y >= 0 && yy + y < this.wyEkran) {
            for(int xx = 0; xx < this.szSprite; ++xx) {
               int xSprite = xx;
               if(this.odbicieX) {
                  xSprite = this.szSprite - 1 - xx;
               }

               if(xx + x >= 0 && xx + x < this.szEkran) {
                  if(this.sprite.szablon) {
                     int indexPalety = this.sprite.pixels[xSprite + ySprite * this.sprite.szerokosc + kratkaOffset];
                     boolean kolor = true;
                     if(indexPalety < 4) {
                        int var14 = this.paleta[indexPalety];
                        if(this.paleta[indexPalety] >= 0) {
                           ekranPixels[xx + x + (yy + y) * this.szEkran] = var14;
                        }
                     }
                  } else {
                     ekranPixels[xx + x + (yy + y) * this.szEkran] = this.sprite.pixels[xSprite + ySprite * this.sprite.szerokosc + kratkaOffset];
                  }
               }
            }
         }
      }

   }

   public void druk(Graphics2D g2, int x, int y, int kratka) {
      int ix = kratka % this.szMapy;
      int iy = kratka / this.szMapy;
      g2.drawImage(this.sprite.image, x, y, x + this.szSprite, y + this.wySprite, ix * this.szSprite, iy * this.wySprite, ix * this.szSprite + this.szSprite, iy * this.wySprite + this.wySprite, (ImageObserver)null);
   }

   public void druk(Graphics2D g2, int x, int y, int sz, int wy, int kratka) {
      int ix = kratka % this.szMapy;
      int iy = kratka / this.szMapy;
      g2.drawImage(this.sprite.image, x, y, x + sz, y + wy, ix * this.szSprite, iy * this.wySprite, ix * this.szSprite + this.szSprite, iy * this.wySprite + this.wySprite, (ImageObserver)null);
   }

   public void druk(Graphics2D g2, int x, int y, int ix, int iy) {
      g2.drawImage(this.sprite.image, x, y, x + this.szSprite, y + this.wySprite, ix * this.szSprite, iy * this.wySprite, ix * this.szSprite + this.szSprite, iy * this.wySprite + this.wySprite, (ImageObserver)null);
   }

   public void druk(Graphics2D g2, int x, int y, int ix, int iy, int szerokosc, int wysokosc) {
      g2.drawImage(this.sprite.image, x, y, x + szerokosc, y + wysokosc, ix, iy, ix + szerokosc, iy + wysokosc, (ImageObserver)null);
   }

   public void druk(Graphics2D g2, int x, int y, int szerokosc, int wysokosc, int ix, int iy, int iSzerokosc, int iWysokosc) {
      g2.drawImage(this.sprite.image, x, y, x + szerokosc, y + wysokosc, ix, iy, ix + iSzerokosc, iy + iWysokosc, (ImageObserver)null);
   }

   public void druk(Graphics2D g2, int x, int y) {
      g2.drawImage(this.sprite.image, x, y, this.sprite.szerokosc, this.sprite.wysokosc, (ImageObserver)null);
   }
}