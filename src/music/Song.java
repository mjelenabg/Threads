package music;

import java.util.Iterator;
import java.util.List;

public class Song {
	 private String title;
	 private List<String> lyrics;
	 
	 // prazan konstruktor neophodan da bi klasa mogla da se klasifikuje u java bean
	 public Song() {
		 super();
		}
	public Song(String title, List<String> lyrics) {
		super();
		this.title = title;
		this.lyrics = lyrics;
	}
	public String pickLine(Voice voice, int noOfLine){
		//kad pozivas enum Ime enuma.sta ti treba
		if (voice == Voice.ALL) {
			return getAllLyrics();
		}
		if (voice == Voice.LEAD || voice==Voice.BACKING) {
			return this.lyrics.get(noOfLine);
		}
		return null;
		
	}
	
	//kada neko peva sve on peva sve stihove, i onda ih vracamo kao jedan string
	public String getAllLyrics(){
		StringBuffer lyrics=new StringBuffer();
		//for kroz kolekciju odaberes, on sam napravi iterator, posle ;;nema i++ jer iterator sam prelazi na sledeci kad vrati taj na kome si
		for (Iterator iterator = this.lyrics.iterator(); iterator.hasNext();) {
			//ovo nam ne treba za sad type type = (type) iterator.next();
			lyrics=lyrics.append((String) iterator.next()+'\n');
		}
		return lyrics.toString();
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<String> getLyrics() {
		return lyrics;
	}
	public void setLyrics(List<String> lyrics) {
		this.lyrics = lyrics;
	}
	 
}
