package mq;

import org.zeromq.SocketType;
import org.zeromq.ZMQ;
import org.zeromq.ZContext;

public class Produce {
	
    public static void main(String[] args){
    	
        try (ZContext context = new ZContext()) {
            System.out.println("Se conecta al servidor");

            ZMQ.Socket socket = context.createSocket(SocketType.REQ);
            socket.connect("tcp://localhost:5555");

            for (int requestNbr = 0; requestNbr != 10; requestNbr++) {
                String request = "";
                System.out.println("Produce " + requestNbr);
                socket.send(request.getBytes(ZMQ.CHARSET), 0);

                byte[] reply = socket.recv(0);
                System.out.println(new String(reply, ZMQ.CHARSET) + " " + requestNbr);
            }
        }
    }
}
