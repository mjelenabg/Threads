package music;

public class Singer extends Thread{
	//dodato extends naknadno,Thread klasa u principu obrati paznju na run najvise!
	//main osnovni thread aplikacije
	private String singerName;
	private Voice voice;
	private Performance performance;
	
	//dodatno
	private boolean stop;
	private Synchronizer synch;
	
	public Singer(String singerName, Voice voice, Performance performance,
			boolean stop, Synchronizer synch) {
		super();
		this.singerName = singerName;
		this.voice = voice;
		this.performance = performance;
		this.stop = stop;
		this.synch = synch;
	}
	public String getSingerName() {
		return singerName;
	}
	public void setSingerName(String singerName) {
		this.singerName = singerName;
	}
	public Voice getVoice() {
		return voice;
	}
	public void setVoice(Voice voice) {
		this.voice = voice;
	}
	public Performance getPerformance() {
		return performance;
	}
	public void setPerformance(Performance performance) {
		this.performance = performance;
	}
	public boolean isStop() {
		return stop;
	}
	public void setStop(boolean stop) {
		this.stop = stop;
	}
	public Synchronizer getSynch() {
		return synch;
	}
	public void setSynch(Synchronizer synch) {
		this.synch = synch;
	}
	public Singer(String name, Voice voice, Performance performance) {
		super();
		this.singerName = name;
		this.voice = voice;
		this.performance = performance;
	}
	public Singer(){
		super();
	}
	//int broj ponavljanja refrena, peva svako svoje
	public void sing(Song song, int noOfRepetitions){
		for (int i = 0; i < noOfRepetitions; i+=2) {
			if (this.voice==Voice.LEAD){
				//i%broj stihova jer strofe mogu da se ponavljaju
				System.out.println(song.getLyrics().get(i%song.getLyrics().size()));
			}
			if (this.voice==Voice.BACKING){
				System.out.println(song.getLyrics().get(i%song.getLyrics().size()+1));
			}
		}
	}
	//pocetak threadova, akcija se radi sa zadrskom, npr posle 1s, kljucna rec synchronized bez koje nece da radi dodata posle
	//
	public synchronized void singWithDelay(Song song, int noOfRepetitions){
		for (int i = 0; i < noOfRepetitions; i+=2) {
			if (this.voice==Voice.LEAD){
				//cekaj u miliekundama
				try {
					//isto u miliekundama
					wait(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(song.getLyrics().get(i%song.getLyrics().size()));
			}
			if (this.voice==Voice.BACKING){
				try {
					
					wait(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(song.getLyrics().get(i%song.getLyrics().size()+1));
			}
		}
	}
	//privatna jer je zove samo run
	private void sing(){
		//izvlacimo iz performansa ko sta i koliko kasni
		Song song=this.performance.getSong();
		long delay=this.performance.getDelay();
		//brojac za petlju
		int i=0;
		String line=null;
		//dok god nije kraj pesme neka pevaci pevaju sta smo im rekli
		while(!stop){
			if (this.voice==Voice.LEAD) {
				line=song.getLyrics().get(i%song.getLyrics().size());
				//pozivamo sinhronizator
				synch.singLeadLine(line, delay);
			}
			if (this.voice==Voice.BACKING) {
				line='\t'+song.getLyrics().get(i%song.getLyrics().size()+1);
				synch.singBackingLine(line, delay);
			}
			
			i+=2;
		}
	}
	//da bi moglo da radi kao thread moras da napravis run, u nju ubacimo sta thread radi kad se pokrene, nju moramo da imamo i u njoj samo zovemo metode koje imamo negde drugde
	@Override
	public void run() {
		sing();
	}
	

}
