package player;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Comparator;
import java.util.TreeSet;
import java.util.Vector;

import map.Maps;
import setup.Queue;
import setup.myConstants;
import enemies.Space;

public class Player implements myConstants{

	private Point pos;
	private Maps map;
	private int[] prog;
	private TreeSet<HighScore> highscores;
	private boolean stop;
	private int speed, level, lives, space;
	private int north= KeyEvent.VK_UP;
	private int south= KeyEvent.VK_DOWN;
	private int east= KeyEvent.VK_RIGHT;
	private int west= KeyEvent.VK_LEFT;
	
	public Player(){
		speed=15;
		level=1;
		lives=3;
		space=10*ESCALA;
		highscores=new TreeSet<HighScore>(new Comparator<HighScore>() {
			public int compare(HighScore h1, HighScore h2) {
				int n = h2.getLevel()-h1.getLevel();
				if(n==0) n=1;
				return n;
			}
		});
		prog= new int[1];
		prog[0]=0;
		pos=new Point(ESCALA*5,11);
	}
	
	public void move(Point dir){
		if(stop) return;
		pos.x+=dir.x*speed;
		pos.y+=dir.y;
		if(pos.x<0) pos.x=0;
		else if(pos.x>ESCALA*9) pos.x=ESCALA*9;
		if(pos.y<0) pos.y=0;
		else if(pos.y>map.lenght())pos.y=map.lenght();
		if(pos.y==0)finished();
		checkTouch();
	}
	
	public void kill(){
		lives--;
		startPosition();
		if(lives==0){
			addHighScore();
			map.gameOver();
		}
		new Died(this);
	}
	
	private void addHighScore(){
		try{
			
		ObjectInputStream obj_in = new ObjectInputStream (new FileInputStream("highscores.sav"));
		Object o = obj_in.readObject();
		obj_in.close();
		
		if(o instanceof TreeSet) highscores=(TreeSet<HighScore>)o;
		
		highscores.add(new HighScore("Player", level));
		if(highscores.size()>5) highscores.pollLast();
		
		
		ObjectOutputStream obj_out = new ObjectOutputStream (new FileOutputStream("highscores.sav"));
		obj_out.writeObject( highscores );
		obj_out.close();
		}catch(Exception e){};
				
	}
	
	public TreeSet<HighScore> highscores(){
		return highscores;
	}
	
	private void finished(){
		int x=pos.x+ESCALA/2;
		startPosition();
		for(int i=0 ; i<houses() ; i++)
			if(x>i*space && x<(i+1)*space) prog[i]=1;
		for(int i=0; i<houses(); i++) if(prog[i]==0) return;
		levelUp();
	}
	
	public boolean reached(int i){
		return prog[i]==1;
	}
	
	public void reStart(){
		level=1;
		lives=3;
		space=10*ESCALA;
		prog= new int[1];
		prog[0]=0;
		map.reStart();
	}
	
	public int houses(){
		int n;
		if(level==1) n=1;
		else if(level<6) n=2;
		else if(level<11) n=3;
		else if(level<21) n=4;
		else if(level<31) n=5;
		else n=6;
		return n;
	}
	
	public void stop(boolean b){
		stop=b;
	}
	
	public int space(){
		return space;
	}
	
	private void levelUp(){
		level++;
		space=10*ESCALA/houses();
		prog=new int[houses()];
		for(int i=0 ; i<houses() ; i++) prog[i]=0;
	}
	
	private void startPosition(){
		pos=new Point(ESCALA*5,11);
	}
	
	private void checkTouch(){
		for(Queue<Space> q: map.enemies())
			for(Space s: q){
				if(s.getLane()==pos.y && samePos(s)) map.touched(s);
			}
	}
	
	private boolean samePos(Space s){
		return (pos.x+ESCALA>s.getPos() && pos.x<s.getPos()) || (pos.x<s.getPos()+ESCALA && pos.x+ESCALA>s.getPos()+ESCALA);
	}
	
	public void addLife(){
		lives++;
	}
	
	public int getX(){
		return pos.x;
	}
	public int getY(){
		return pos.y;
	}
	
	public int getLevel(){
		return level;
	}
	
	public int getLives(){
		return lives;
	}
	
	public int getNorth() {
		return north;
	}

	public void setNorth(int north) {
		this.north = north;
	}

	public int getSouth() {
		return south;
	}

	public void setSouth(int south) {
		this.south = south;
	}

	public int getEast() {
		return east;
	}

	public void setEast(int east) {
		this.east = east;
	}

	public int getWest() {
		return west;
	}

	public void setWest(int west) {
		this.west = west;
	}
	
	public void setMap(Maps map){
		this.map=map;
	}
}
