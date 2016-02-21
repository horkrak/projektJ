package project;

import project.Game;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class Start<T> extends JFrame implements ActionListener {

   public JMenuBar menu;
   public JMenu mGra;
   public JMenu mPomoc;
   public JMenu mNowaGra;
   public JMenuItem iLatwa;
   public JMenuItem iSrednia;
   public JMenuItem iTrudna;
   public JMenuItem iExit;
   public JMenuItem iPomoc;
   public JMenuItem iAutor;
   public static Game gra;
   public ImageIcon ikonka;
   
   public Start() {
	  
	  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  this.setTitle("Saper - Normalna (40)");
      ikonka = new ImageIcon(this.getClass().getResource("/images/bomba.gif"));
      this.setIconImage(ikonka.getImage());
      this.menu = new JMenuBar();
      this.mGra = new JMenu("Gra");
      this.mPomoc = new JMenu("Pomoc");
      this.mNowaGra = new JMenu("Nowa Gra");
      this.iPomoc = new JMenuItem("Jak graæ");
      this.iAutor = new JMenuItem("Informacje");
      this.iLatwa = new JMenuItem("£atwa");
      this.iSrednia = new JMenuItem("Normalna");
      this.iTrudna = new JMenuItem("Trudna");
      this.iExit = new JMenuItem("Exit");
      gra = new Game();
      Medium M = new Medium();      
      M.nowaGra(gra);
      this.mNowaGra.add(this.iLatwa);
      this.mNowaGra.add(this.iSrednia);
      this.mNowaGra.add(this.iTrudna);
      this.mPomoc.add(this.iPomoc);
      this.mPomoc.add(this.iAutor);
      this.mGra.add(this.mNowaGra);
      this.mGra.add(this.iExit);
      this.menu.add(this.mGra);
      this.menu.add(this.mPomoc);
      this.setJMenuBar(this.menu);
      this.add(gra);
      this.pack();
      this.iExit.addActionListener(this);
      this.iLatwa.addActionListener(this);
      this.iSrednia.addActionListener(this);
      this.iTrudna.addActionListener(this);
      this.iPomoc.addActionListener(this);
      this.iAutor.addActionListener(this);
      this.setLocationRelativeTo((Component)null);
      this.setResizable(false);
      this.setVisible(true);
   }

   public static void main(String[] args) {	   
       new Start();
   }

   public void actionPerformed(ActionEvent e) {
      Object cel = e.getSource();
      if(cel == this.iExit) {
         System.exit(0);
      } 
      else if(cel == this.iLatwa) {
         this.setTitle("Saper - £atwa (20 bomb)");

    
         Easy l = new Easy();
         l.nowaGra(gra);
         if(!Game.sluchacz) {
             gra.addMouseListener(gra);
          }

      //   gra.paint(gra.getGraphics());
      } 
      else if(cel == this.iSrednia) {
         this.setTitle("Saper - Normalna (35 bomb)");

         Medium l = new Medium();
         l.nowaGra(gra);
         if(!Game.sluchacz) {
             gra.addMouseListener(gra);
          }

         //gra.paint(gra.getGraphics());
      } 
      else if(cel == this.iTrudna) {
         this.setTitle("Saper - Trudna (50 bomb)");

         Hard l = new Hard();
         l.nowaGra(gra);
         if(!Game.sluchacz) {
            gra.addMouseListener(gra);
         }

         //gra.paint(gra.getGraphics());
      } 
      else if(cel == this.iPomoc) {
         JOptionPane.showMessageDialog((Component)null, "Gra polega na odkrywaniu na planszy poszczególnych pól w taki sposób, aby nie natrafiæ na minê.        \nNa ka¿dym z odkrytych pól napisana jest liczba min, które bezpoœrednio stykaj¹ siê z danym polem\n(od zera do oœmiu). Jeœli oznaczymy dane pole flag¹, jest ono zabezpieczone przed ods³oniêciem,\ndziêki czemu przez przypadek nie ods³onimy miny.", "Jak graæ", 1);
      } 
      else if(cel == this.iAutor) {
       JOptionPane.showMessageDialog((Component)null, "Autor: S³awomir Chochorek", "Informacje", 1, ikonka);
      }

   }
}