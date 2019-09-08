package problema2;


public class Consumidor extends Thread{
	private Buffer b;
	private int num;
	
	public Consumidor(Buffer b, int num) {
		this.b = b;
		this.num = num; 
		
	}
	
	@Override
	public void run() {
		for(int i = 0; i < num; i ++) {
			int aux;
			try {
				aux = b.extract();
				System.out.println(i + ": Consumidor consume: " + aux);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		Buffer b = new Buffer(1);
		Productor p = new Productor(b, 10);
		Consumidor c = new Consumidor(b,10);
		p.start();
		c.start();
	}

}
