package map;

import java.util.Random;

import enemies.Bat;
import enemies.Hiena;
import enemies.Life;
import enemies.Snake;
import enemies.Space;
import player.Player;
import setup.myConstants;

public class Creator implements Runnable, myConstants {

	private Maps map;
	private Player p;
	private Thread t;
	private Random r=new Random();
	private int num;
	
	public Creator(Maps map, Player p){
		this.map=map;
		this.p=p;
		new EnemiesMovement(map, p);
		t= new Thread(this,"Lanes");
		t.start();
	}
	
	@Override
	public void run() {
		try{
			while(true){
				int n=r.nextInt(100), i=r.nextInt(10), dir=(i%2)*2-1, pos;
				if(dir==1) pos=-15; else pos=20+10*ESCALA;
				n=enemySelect(n);
				Space s;String st;
				switch(n){
					case 0: st="Snake"; s= new Snake(++i,dir, pos, num++); break;
					case 1: st="Hiena";s= new Hiena(++i,dir, pos, num++);break;
					case 2: st="Bonus";s= new Life(++i,dir, pos, num++);break;
					case 3: st="Bat";s= new Bat(++i,dir, pos, num++);break;
					default: st="Snake";s= new Snake(++i,dir, pos, num++); break;
				}
				 map.add(st, s);
				Thread.sleep(500-5*p.getLevel());
			}
		}catch(Exception e){}

	}
	
	private int enemySelect(int n){
		if(n>90-p.getLevel()) n=1; 
		else if(n<=p.getLevel()/10) n=2; 
		else if(n<5+p.getLevel()) n=3;
		else n=0;
		return n;
	}

}
