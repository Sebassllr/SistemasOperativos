package problema2;
import java.util.Random;

public class Productor extends Thread{
	
	private Random r = new Random();
	private Buffer b;
	private int num;
	
	public Productor(Buffer b, int num) {
		this.b = b;
		this.num = num;
	}
	
	@Override
	public void run() {
		for(int i = 0; i < num; i++) {
			int aux = r.nextInt(100);
			System.out.println(i + ": Productor produce: " + aux);
			try {
				b.put(aux);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}

}
