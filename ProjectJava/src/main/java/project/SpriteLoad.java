package project;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SpriteLoad {

   public String path;
   public int szerokosc;
   public int wysokosc;
   public boolean szablon;
   public int[] pixels;
   public BufferedImage image = null;


   public SpriteLoad(String path, boolean szablon) {
      this.szablon = szablon;

      try {
         this.image = ImageIO.read(SpriteLoad.class.getResourceAsStream(path));
      } catch (IOException e) {
         e.printStackTrace();
      }

      if(this.image != null) {
         this.szerokosc = this.image.getWidth();
         this.wysokosc = this.image.getHeight();
         this.pixels = this.image.getRGB(0, 0, this.szerokosc, this.wysokosc, (int[])null, 0, this.szerokosc);
         if(szablon) {
            for(int i = 0; i < this.pixels.length; ++i) {
               this.pixels[i] = (this.pixels[i] & 255) / 64;
            }
         }

      }
   }
}