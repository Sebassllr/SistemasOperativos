package pcmessaging;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import static pcmessaging.ParamsUtil.*;

public class Producer {
	
    private ServerSocket serverSocket;
    
    private Integer inventory = 0;
    
    public Producer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        inventory = generateRandomNumber(1, 10);
    }
	
    public void serve() throws IOException {
        while (true) {
            // block until a client connects
            final Socket socket = serverSocket.accept();
            // create a new thread to handle that client
            Thread handler = new Thread(new Runnable() {
                public void run() {
                    try {
                        try {
                            handle(socket);
                        } finally {
                            socket.close();
                        }
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                }
            });
            handler.start();
        }
    }
    
    private void produce(int request) {

    	synchronized (inventory) {
    		System.out.printf("En inventario: %d \t", inventory);
        	while(inventory < request) {
        		int random = generateRandomNumber(1, 10);
        		inventory += random;
        		System.out.printf("Produjo: %d \t", random);
        	}
        	inventory -= request;
		}

    }
    
    private void handle(Socket socket) throws IOException {
        System.out.println("client connected");
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

        try {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                
                try {
                	String[] arr = line.split("-");
                	assert arr.length == 2;
                    int x = Integer.valueOf(arr[0]);
                    System.out.printf("%s: \t ", arr[1]);
                    System.out.printf("Petición: %d \t ", x);
                    this.produce(x);	
                    System.out.printf("Consumió: %d \t \n", x);
                    out.println(x);
                } catch (NumberFormatException e) {
                    System.out.println("reply: err");
                    out.print("err\n");
                }
            }
        } finally {
            out.close();
            in.close();
        }
    }
    
	public static void main(String[] args) {
		try {
			Producer producer = new Producer(SERVER_PORT);
			producer.serve();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
