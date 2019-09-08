package problema1;

import java.util.Random;

public class Escritor extends Thread{

	private static Random r = new Random();
	private Gestor gestor;
	private int id;
	
	public Escritor(Gestor gestor, int id) {
		this.gestor = gestor;
		this.id = id;
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				gestor.closeE(id);
				Thread.sleep(r.nextInt(200));
				gestor.openE(id);
				Thread.sleep(r.nextInt(500));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public static void main(String[] args) {
		Gestor gestor = new Gestor();
		Escritor[] e = new Escritor[2];
		Lector[] l = new Lector[2];
		
		for(int i = 0; i < e.length; i ++) {
			e[i] = new Escritor(gestor, i);
		}
		
		for(int i = 0; i < l.length; i ++) {
			l[i] = new Lector(gestor, i);
		}	
		
		for(int i = 0; i < e.length; i ++) {
			e[i].start();
		}
		
		for(int i = 0; i < l.length; i ++) {
			l[i].start();
		}	
		
	}
}
