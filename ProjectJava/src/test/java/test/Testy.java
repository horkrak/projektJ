package test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

import java.awt.event.MouseEvent;

import project.*;

import org.junit.Test;


public class Testy {
	
	 @Test
	    public void test1(){
	        Game test1 = new Game();
	        boolean t1 = test1.sluchacz;
	        assertEquals(true,t1 );
	        
	    }
	 @Test
	 	public void test2(){
		 	Game test2 = new Game();
		 	int t2 = test2.SZE_SPR;
		 	assertEquals(30,t2);
	 }
	 @Test
	 	public void test3(){
		 	Game test3 = new Game();
		 	int t3 = test3.WYS_SPR;
		 	assertEquals(30,t3);
	 }
	 @Test
	 	public void test4(){
		 	Game test4 = new Game();
		 	int t4 = test4.szePla;
		 	assertEquals(23,t4);	 
	 }
	@Test
	 	public void test5(){
		 	Game test5 = new Game();
		 	int t5 = test5.wysPla;
		 	assertEquals(17,t5);	 
	}
}
