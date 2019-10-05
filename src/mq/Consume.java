package mq;

import org.zeromq.SocketType;
import org.zeromq.ZMQ;
import org.zeromq.ZContext;

public class Consume {

	public static void main(String[] args) throws InterruptedException {
		
		try (ZContext context = new ZContext()) {
			ZMQ.Socket socket = context.createSocket(SocketType.REP);
			  socket.bind("tcp://*:5555");
			
			while (!Thread.currentThread().isInterrupted()) {
				byte[] reply = socket.recv(0);
				System.out.println("Consume " + ": [" + new String(reply, ZMQ.CHARSET) + "]");
				String response = "Consume";
				socket.send(response.getBytes(ZMQ.CHARSET), 0);
				Thread.sleep(1000);
			}
		}
	}
}
