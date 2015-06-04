package music;

public class Synchronizer {
	private boolean leadVoiceFlag;
	
	public Synchronizer(boolean leadVoiceFlag) {
		super();
		this.leadVoiceFlag = leadVoiceFlag;
	}
	public boolean isLeadVoiceFlag() {
		return leadVoiceFlag;
	}
	public void setLeadVoiceFlag(boolean leadVoiceFlag) {
		this.leadVoiceFlag = leadVoiceFlag;
	}
	
	//dve metode, jedna za pevanje prvog glasa, druga drugog
	public synchronized void singLeadLine(String line, long delay){
		//prvo pita da li metoda ima dozvolu da "peva"
		while(!leadVoiceFlag){
			//nemoj da radis nista dok ti ne kazu
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		singOneLine(line,delay);
		
	}
	public synchronized void singBackingLine(String line, long delay){
		//dok prvi glas peva ja cekam, dok je to tacno, preko flaga se dogovaraju, potrebno da budu sinhronizovane (!leadVoiceFlag,leadVoiceFlag)
		while(leadVoiceFlag){
			
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		singOneLine(line,delay);
		
	}
	private void singOneLine(String line, long delay) {
		//kazemo mu da ceka malo, da se ne zalece
		try {
			wait(delay);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(line);
		//VAZNO, moramo da kazemo da je drugi na redu, BEZ OVOGA NISTA NE RADI
		leadVoiceFlag=!leadVoiceFlag;
		//iz klase object, budi sve druge uspavane threadove, notify samo jedan budi
		notifyAll();
	}

}
