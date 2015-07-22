package setup;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.Timer;

import map.Maps;
import player.Player;

public class mainCanvas extends Canvas implements KeyListener, myConstants, MouseListener, ActionListener{

	private static final long serialVersionUID = 1L;
	boolean repaintInProgress = false;
	private Maps map;
	private Player p;
	private Image[] img;
	private JFrame pane;
	private JButton restart;
	
	mainCanvas(JFrame pane) {
		setIgnoreRepaint(true);
		addKeyListener(this);
		addMouseListener(this);
		
		p = new Player();
		map= new Maps(p);
		p.setMap(map);
		
		img= new Image[19];
		loadImages();
		
		this.pane=pane;
		restart= new JButton();
		restart.addActionListener(this);
		restart.setBounds(145, 10+ESCALA+10, ESCALA, 50);
		
		mainChrono chrono = new mainChrono(this);
		new Timer(16, chrono).start();
	}
	
	private void loadImages(){
		try{
		img[0]=loadImage("images/red hair.png");
		img[1]=loadImage("images/red hair back.png");
		img[2]=loadImage("images/red hair der.png");
		img[3]=loadImage("images/red hair izq.png");
		img[4]=loadImage("images/pasto.png");
		img[5]=loadImage("images/bat.png");
		img[6]=loadImage("images/bush.png");
		img[7]=loadImage("images/hiena.png");
		img[8]=loadImage("images/ladrillos.png");
		img[9]=loadImage("images/roca.png");
		img[10]=loadImage("images/snake-chica.png");
		img[11]=loadImage("images/snake.png");
		img[12]=loadImage("images/vendedor.png");
		img[13]=loadImage("images/bat.png");
		img[14]=loadImage("images/pasto2.png");
		img[15]=loadImage("images/pasto3.png");
		img[16]=loadImage("images/roca2.png");
		img[17]=loadImage("images/snake grande.png");
		img[18]=loadImage("images/cruz.png");
		}catch(IOException e){}
	}
	
	
	public static Image loadImage(String fileName) throws IOException {
		InputStream stream = ClassLoader.getSystemResourceAsStream(fileName);
		if (stream == null) {	
			return ImageIO.read(new File(fileName)); 
		} else {
			return ImageIO.read(stream);
		}
	}
	
	public void myRepaint() {
		if(repaintInProgress)
			return;
		repaintInProgress = true;
		BufferStrategy strategy = getBufferStrategy();
		Graphics g = strategy.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, 20+ESCALA*10, 20+ESCALA*13);
		g.setColor(Color.CYAN);
		g.fillRect(10, 10+ESCALA, 10*ESCALA, 11*ESCALA);
		g.setColor(Color.WHITE);
		g.fillRect(10, 10, 10*ESCALA, ESCALA*2);
		g.fillRect(10, 10+12*ESCALA, 10*ESCALA, ESCALA);
		g.setColor(Color.GREEN);
		int space=p.space(), j=0;
		for(int i=0 ; i<p.houses() ; i++) if(p.reached(i)) g.fillRect(10+i*space, 10+ESCALA, space, ESCALA);
		g.setColor(Color.BLACK);
		for(int i=1;i<13;i++){
			g.drawLine(10, 10+ESCALA*i, 10*ESCALA+10,10+ESCALA*i);
		}
		
		while(j++<p.houses()) g.drawLine(10+j*space, 10+ESCALA, 10+j*space, 10+2*ESCALA);
		g.drawImage(img[0],10+p.getX(),10+(p.getY()+1)*ESCALA,null);
		
		map.drawAll(g, img);
		if(map.isOver()) pane.add(restart);
		
		g.setColor(Color.BLACK);
		g.drawString("Level: " + p.getLevel(), 18, 30);
		g.drawString("Lives: ", 100, 30);
		g.setColor(Color.RED);
		for(int i=0;i<p.getLives();i++)
			g.fillOval(142+i*25, 18, 20, 20);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 10, 20+13*ESCALA);
		g.fillRect(0, 0, 20+10*ESCALA, 10);
		g.fillRect(0, 10+13*ESCALA, 20+10*ESCALA, 10);
		g.fillRect(10+10*ESCALA, 0, 10, 20+13*ESCALA);
		
		strategy.show();
		Toolkit.getDefaultToolkit().sync();
		repaintInProgress = false;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent a) {
		p.reStart();
		pane.remove(restart);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key=e.getKeyCode();
		if(p.getLives()==0) {
			if(key!=KeyEvent.VK_SPACE) return;
			else p.reStart();
		}
		if(key==p.getNorth()) p.move(new Point(0,-1));
		else if(key==p.getSouth())p.move(new Point(0,1));
		else if(key==p.getEast())p.move(new Point(1,0));
		else if(key==p.getWest())p.move(new Point(-1,0));
		
	}
	
	@Override public void mouseClicked(MouseEvent e) {}
	@Override public void mouseEntered(MouseEvent e) {}
	@Override public void mouseExited(MouseEvent e) {}
	@Override public void mousePressed(MouseEvent e) {}
	@Override public void mouseReleased(MouseEvent e) {}
	@Override public void keyReleased(KeyEvent e) {}
	@Override public void keyTyped(KeyEvent e) {}
}

