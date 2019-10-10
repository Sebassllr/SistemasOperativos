package pcmessaging;

public class ParamsUtil {
	
	public static final int SERVER_PORT = 4949;
	
    public static int generateRandomNumber(int min, int max) {
    	return (int) (Math.random() * ((max - min) + 1) + min);
    }
}
