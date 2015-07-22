package player;

public class Died implements Runnable{

	private Player p;
	private Thread t;
	
	public Died(Player p){
		this.p=p;
		t=new Thread(this, "Respawn");
		t.start();
	}
	
	@Override
	public void run() {
		p.stop(true);
		try{Thread.sleep(700);}catch(InterruptedException e){}
		p.stop(false);
	}
}
