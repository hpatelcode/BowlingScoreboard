import java.io.BufferedReader;
import java.io.IOException;
/* this class handles the "input sanitization" as required by the task. This ensures the proper integer value is
 * inputted, and the BufferedReader class provided by Java allows for efficient and cleaner input accessing 
 * 
 */
public class PinsBowled {
	private BufferedReader input; // input 
	private Interpreter sender; // what we're using as a means to "send" so that the Interpreter class prints
	
	public PinsBowled(final BufferedReader input, final Interpreter sender){
		this.input = input;
		this.sender = sender;
	}
	public int pinsDown() {
		int pins = 0;
		sender.fetchPinsKnocked(); //prints and asks user to input what they just bowled  
		while(true) {
			try {
				pins = Integer.valueOf(input.readLine()).intValue(); //input handling
				break;												// exit while loop when proper input is given
				
			}catch (NumberFormatException e) {
				System.out.println("Expected a number, try again..."); //keep trying until number inputted 
			}
			catch (IOException ioe) {
	         ioe.printStackTrace();
			}
		}
		return pins;
	}
}
