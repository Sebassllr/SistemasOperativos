package pcmessaging;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static pcmessaging.ParamsUtil.*;

public class MyRunnable implements Runnable {
	
	private String name;
	
	public MyRunnable(String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
  		try {
			List<Integer> request = new ArrayList<>();
			Consumer client = new Consumer("localhost", SERVER_PORT);
			final int N = generateRandomNumber(50, 100);
			
            for (int x = 1; x <= N; ++x) {
            	int req = generateRandomNumber(1, 10);
            	request.add(req);
                client.sendRequest(req, this.name);
                System.out.printf("%s: Se pide %d\n",this.name, req);
            }

            for(Integer x : request) {
            	int y = client.getReply();
            	System.out.printf("%s: Se pide %d y se recibe %d\n", this.name, x, y);
            }
            client.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
	}

}
