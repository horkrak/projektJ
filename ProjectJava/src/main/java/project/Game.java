package project;

import project.Sprite;
import project.Start;
import project.SpriteLoad;
import java.awt.Canvas;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.Random;
import javax.swing.JOptionPane;

public class Game extends Canvas implements MouseListener, MouseMotionListener {

   private final int BOMBA = 9;
   public static final int SZE_SPR = 30;
   public static final int WYS_SPR = 30;
   public static int szePla = 23;
   public static int wysPla = 17;
   private int bomby;
   private BufferedImage image;
   private Graphics2D grafika;
   private Sprite sprite;
   private byte[][] poleMinowe;
   private byte[][] poleTrawa;
   private Random rand;
   private boolean klik;
   private int mX;
   private int mY;
   public static boolean sluchacz;
   private boolean[][] pole;


   public Game() {
      this.image = new BufferedImage(szePla * 30, wysPla * 30, 1);
      this.grafika = (Graphics2D)this.image.getGraphics();
      this.rand = new Random();
      this.pole = new boolean[szePla][wysPla];
      this.setPreferredSize(new Dimension(szePla * 30, wysPla * 30));
      this.sprite = new Sprite(new SpriteLoad("/images/saper.png", false), 30, 30);
      this.poleMinowe = new byte[szePla][wysPla];
      this.poleTrawa = new byte[szePla][wysPla];
      this.addMouseListener(this);
      sluchacz = true;
      this.addMouseMotionListener(this);
   }

   public void drawPlansza() {
      for(int x = 0; x < szePla; ++x) {
         for(int y = 0; y < wysPla; ++y) {
            this.sprite.druk(this.grafika, x * 30, y * 30, this.poleMinowe[x][y]);
            if(this.poleTrawa[x][y] != 0) {
               this.sprite.druk(this.grafika, x * 30, y * 30, this.poleTrawa[x][y]);
            }
         }
      }

      this.naEkran();
   }

   public void naEkran() {
      Graphics g = this.getGraphics();
      g.drawImage(this.image, 0, 0, szePla * 30, wysPla * 30, (ImageObserver)null);
      g.dispose();
   }

   public void paint(Graphics g) {
      super.paint(g);
      this.drawPlansza();
   }

   public void rozmieszczenieBomb(int b) {
	  this.setBomby(b);
      boolean rx = false;
      boolean ry = false;

      int index;
      int x;
      for(index = 0; index < szePla; ++index) {
         for(x = 0; x < wysPla; ++x) {
            this.poleMinowe[index][x] = 0;
            this.poleTrawa[index][x] = 12;
         }
      }

      for(index = 0; index < getBomby(); ++index) {
         int var6;
         int var7;
         do {
            var6 = this.rand.nextInt(szePla);
            var7 = this.rand.nextInt(wysPla);
         } while(this.poleMinowe[var6][var7] == 9);

         this.poleMinowe[var6][var7] = 9;
      }

      for(x = 0; x < szePla; ++x) {
         for(int y = 0; y < wysPla; ++y) {
            if(this.poleMinowe[x][y] != 9) {
               index = 0;
               if(this.getBomba(x - 1, y - 1)) {
                  ++index;
               }

               if(this.getBomba(x, y - 1)) {
                  ++index;
               }

               if(this.getBomba(x + 1, y - 1)) {
                  ++index;
               }

               if(this.getBomba(x - 1, y)) {
                  ++index;
               }

               if(this.getBomba(x + 1, y)) {
                  ++index;
               }

               if(this.getBomba(x - 1, y + 1)) {
                  ++index;
               }

               if(this.getBomba(x, y + 1)) {
                  ++index;
               }

               if(this.getBomba(x + 1, y + 1)) {
                  ++index;
               }

               this.poleMinowe[x][y] = (byte)index;
            }
         }
      }

   }

   public boolean getBomba(int x, int y) {
      return x >= 0 && y >= 0 && x < szePla && y < wysPla && this.poleMinowe[x][y] == 9;
   }

   public void mouseClicked(MouseEvent e) {}

   public void mousePressed(MouseEvent e) {
      this.klik = true;
      this.mX = e.getX() / 30;
      this.mY = e.getY() / 30;
      if(this.poleTrawa[this.mX][this.mY] == 12) {
         this.poleTrawa[this.mX][this.mY] = 13;
      }

      this.drawPlansza();
   }

   public void mouseReleased(MouseEvent e) {
      int mx = e.getX() / 30;
      int my = e.getY() / 30;
      if(e.getButton() == 3) {
         if(this.poleTrawa[mx][my] != 13 && this.poleTrawa[mx][my] != 15) {
            if(this.poleTrawa[mx][my] == 14) {
               this.poleTrawa[mx][my] = 12;
            }
         } else {
            this.poleTrawa[mx][my] = 14;
         }
      } else if(e.getButton() == 2) {
         if(this.poleTrawa[mx][my] != 15) {
            this.poleTrawa[mx][my] = 15;
         } else if(this.poleTrawa[mx][my] == 15) {
            this.poleTrawa[mx][my] = 12;
         }
      } else if(e.getButton() == 1) {
         if(this.poleTrawa[mx][my] == 13 && this.poleMinowe[mx][my] == 0) {
            this.czystePole(mx, my);
         } else if(this.poleTrawa[mx][my] == 13) {
            this.poleTrawa[mx][my] = 0;
         }

         if(this.poleMinowe[mx][my] == 9) {
            this.bum(mx, my);
         }
      }

      this.klik = false;
      this.drawPlansza();
      this.wygrana();
   }

   public void czystePole(int x, int y) {
      int index;
      int ix;
      for(index = 0; index < szePla; ++index) {
         for(ix = 0; ix < wysPla; ++ix) {
            this.pole[index][ix] = false;
         }
      }

      this.pole[x][y] = true;

      int iy;
      do {
         index = 0;

         for(ix = 0; ix < szePla; ++ix) {
            for(iy = 0; iy < wysPla; ++iy) {
               if(this.pole[ix][iy]) {
                  if(this.limit(ix - 1, iy) && this.poleMinowe[ix - 1][iy] == 0 && !this.pole[ix - 1][iy]) {
                     ++index;
                     this.pole[ix - 1][iy] = true;
                  }

                  if(this.limit(ix + 1, iy) && this.poleMinowe[ix + 1][iy] == 0 && !this.pole[ix + 1][iy]) {
                     ++index;
                     this.pole[ix + 1][iy] = true;
                  }

                  if(this.limit(ix, iy - 1) && this.poleMinowe[ix][iy - 1] == 0 && !this.pole[ix][iy - 1]) {
                     ++index;
                     this.pole[ix][iy - 1] = true;
                  }

                  if(this.limit(ix, iy + 1) && this.poleMinowe[ix][iy + 1] == 0 && !this.pole[ix][iy + 1]) {
                     ++index;
                     this.pole[ix][iy + 1] = true;
                  }
               }
            }
         }
      } while(index != 0);

      for(ix = 0; ix < szePla; ++ix) {
         for(iy = 0; iy < wysPla; ++iy) {
            if(this.pole[ix][iy]) {
               this.poleTrawa[ix][iy] = 0;
               if(this.limit(ix - 1, iy)) {
                  this.poleTrawa[ix - 1][iy] = 0;
               }

               if(this.limit(ix + 1, iy)) {
                  this.poleTrawa[ix + 1][iy] = 0;
               }

               if(this.limit(ix, iy - 1)) {
                  this.poleTrawa[ix][iy - 1] = 0;
               }

               if(this.limit(ix, iy + 1)) {
                  this.poleTrawa[ix][iy + 1] = 0;
               }

               if(this.limit(ix - 1, iy - 1)) {
                  this.poleTrawa[ix - 1][iy - 1] = 0;
               }

               if(this.limit(ix + 1, iy + 1)) {
                  this.poleTrawa[ix + 1][iy + 1] = 0;
               }

               if(this.limit(ix - 1, iy + 1)) {
                  this.poleTrawa[ix - 1][iy + 1] = 0;
               }

               if(this.limit(ix + 1, iy - 1)) {
                  this.poleTrawa[ix + 1][iy - 1] = 0;
               }
            }
         }
      }

   }

   private boolean limit(int x, int y) {
      return x >= 0 && y >= 0 && x < szePla && y < wysPla;
   }

   public void mouseEntered(MouseEvent e) {}

   public void mouseExited(MouseEvent e) {}

   public void mouseDragged(MouseEvent e) {
      if(this.klik) {
         if(this.poleTrawa[this.mX][this.mY] == 13) {
            this.poleTrawa[this.mX][this.mY] = 12;
         }

         this.mX = e.getX() / 30;
         this.mY = e.getY() / 30;
         if(this.poleTrawa[this.mX][this.mY] == 12) {
            this.poleTrawa[this.mX][this.mY] = 13;
         }

         this.drawPlansza();
      }

   }

   public void mouseMoved(MouseEvent e) {}

   public void bum(int x, int y) {
      this.poleMinowe[x][y] = 11;

      for(int ix = 0; ix < szePla; ++ix) {
         for(int iy = 0; iy < wysPla; ++iy) {
            if(this.poleTrawa[ix][iy] == 14 && this.poleMinowe[ix][iy] != 9) {
               this.poleMinowe[ix][iy] = 10;
               this.poleTrawa[ix][iy] = 0;
            }

            if(this.poleMinowe[ix][iy] == 9 && this.poleTrawa[ix][iy] == 12) {
               this.poleTrawa[ix][iy] = 0;
            }
         }
      }

      this.drawPlansza();
      this.removeMouseListener(this);
      sluchacz = false;
   }

   public void wygrana() {
      int iTrawa = 0;
      int iflaga = 0;

      for(int x = 0; x < szePla; ++x) {
         for(int y = 0; y < wysPla; ++y) {
            if(this.poleTrawa[x][y] == 12) {
               ++iTrawa;
            } else if(this.poleTrawa[x][y] == 14) {
               ++iflaga;
            }
         }
      }

      if(iTrawa == 0 && iflaga == getBomby()) {
         this.removeMouseListener(this);
         sluchacz = false;
         JOptionPane.showMessageDialog((Component)null, "Wygra³eœ rozminowa³eœ pole", "GRATULACJE", 1);
      }

   }

public int getBomby() {
	return bomby;
}

public void setBomby(int bomby) {
	this.bomby = bomby;
}
}