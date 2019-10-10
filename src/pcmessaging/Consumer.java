package pcmessaging;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Consumer {
	
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
	
    public Consumer(String hostname, int port) throws IOException {
        socket = new Socket(hostname, port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
    }
    
    public void sendRequest(int x, String name) throws IOException {
        out.print(x + "-" + name + "\n");
        out.flush();
    }
    
    public int getReply() throws IOException {
        String reply = in.readLine();
        if (reply == null) {
            throw new IOException("La conexión terminó de forma inesperada");
        }
        
        try {
            return Integer.valueOf(reply);
        } catch (NumberFormatException nfe) {
            throw new IOException("Respuesta con formato incorrecto: " + reply);
        }
    }
    
    public void close() throws IOException {
        in.close();
        out.close();
        socket.close();
    }
    

	public static void main(String[] args) {

		  Thread t1 = new Thread(new MyRunnable("Consumidor 1"));
		  Thread t2 = new Thread(new MyRunnable("Consumidor 2"));
		  t1.start();
		  t2.start();
		  try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		  
	}
}
