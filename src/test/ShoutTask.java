package test;

import java.util.Timer;
import java.util.TimerTask;

public class ShoutTask extends TimerTask{
	private Timer timer;
	
	public ShoutTask(Timer timer) {
		super();
		this.timer = timer;
	}

	//TimerTask u utilu, esencijalan jer ima metodu run-apstraktna metoda, esencijalan za sve threadove, sta se desi kad istekne stoperica je run
	@Override
	public void run() {
		System.out.println();
		System.out.println("Arrrgh!!");
		System.out.println();
		//ovim se gasi timer
		this.timer.cancel();
		this.timer.purge();
		
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}
	

}
