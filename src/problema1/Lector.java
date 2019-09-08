package problema1;
import java.util.*;

public class Lector extends Thread{
	
	private static Random r = new Random();
	private Gestor gestor;
	private int id;
	
	public Lector(Gestor gestor, int id) {
		this.gestor = gestor;
		this.id = id;
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				gestor.close(id);
				Thread.sleep(r.nextInt(200));
				gestor.open(id);
				Thread.sleep(r.nextInt(500));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
