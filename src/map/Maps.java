package map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import player.HighScore;
import player.Player;
import setup.Queue;
import setup.myConstants;
import enemies.Bonus;
import enemies.Space;

public class Maps implements myConstants{

	private Player p;
	private Map<String, Queue<Space>> enemies;
	private int lenght;
	private boolean over;
	
	public Maps(Player p){
		this.p=p;
		lenght=11;
		enemies=new TreeMap<String, Queue<Space>>();
		enemies.put("Snake", new Queue<Space>());
		enemies.put("Hiena", new Queue<Space>());
		enemies.put("Bat", new Queue<Space>());
		enemies.put("Bonus", new Queue<Space>());
		new Creator(this, p);
	}
	
	public void add(String st, Space s){
		enemies.get(st).push(s);
	}
	
	public void remove(String s){
		enemies.get(s).pop();
	}
	
	public void gameOver(){
		over=true;
	}
	
	public void reStart(){
		over=false;
	}
	
	public boolean isOver(){
		return over;
	}
	
	public int totalEnemies(){
		return 3;
	}
	
	public Collection<Queue<Space>> enemies(){
		return enemies.values();
	}
	
	public void touched(Space s){
		if(s.getName()!="Bonus") p.kill();
		else{
			((Bonus)s).benefit(p);
			remove("Bonus");
		}
	}
	
	public void drawAll(Graphics g, Image[] img){
		if(!over){
			for(Queue<Space> q: enemies.values())
				for(Space s: q)
					s.draw(g, img);
		}else{
			g.setColor(Color.BLUE);
			g.drawString("GAME OVER", 150, 30);
			g.drawString("Press SPACE to play again", 140, 30+ESCALA*10);
			g.setColor(Color.RED);
			g.drawString("HIGHSCORES", 140, 30+ESCALA*2);
			int i=1;
			for(HighScore hs:p.highscores())
				g.drawString(i + ": " + hs.getName() + "- " + hs.getLevel(), 140, 30+ESCALA*((i++)+2));
		}
		
	}
	
	public int lenght(){
		return lenght;
	}
}
