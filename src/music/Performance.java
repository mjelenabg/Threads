package music;

public class Performance {
	private Song song;
	//stavljamo long int umesto inta
	private long delay;
	
	public Performance(Song song, long delay) {
		super();
		this.song = song;
		this.delay = delay;
	}
	public Performance() {
		super();
	}
	
	
	
	public Song getSong() {
		return song;
	}
	public void setSong(Song song) {
		this.song = song;
	}
	public long getDelay() {
		return delay;
	}
	public void setDelay(long delay) {
		this.delay = delay;
	}
	
	
}
