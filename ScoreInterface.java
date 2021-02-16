import java.io.BufferedReader;
import java.io.InputStreamReader;
/*This is the main class to run and execute the program. It realizes the business logic of the task and
 * puts all the pieces together. 
 */
public class ScoreInterface {
	private Game g; // new game 
	private BufferedReader in; // for input
	private Interpreter interpret; // for printing messages and directions to the interface
	private BoardViewer board; // for printing the scoreboard
	private PinsBowled readPins; // for input checking and sanitizing
	
	public ScoreInterface() { // constructor 
		g = new Game(); 
		in = new BufferedReader(new InputStreamReader(System.in));
		interpret = new Interpreter(g);
		board = new BoardViewer();
		readPins = new PinsBowled(in,interpret);
	}
	
	public static void main(String[] args) { 
		new ScoreInterface().inSession(); // game is in session 
	}
	public void inSession() {
		interpret.printGreeting(); //print greeting
		commence(); // starts the game mechanics and will go on until all 10 frames are played 
		System.out.println("\n\n~~Hooray for finishing the game! Here is the final score: \n\n");
		board.showBoard(g); // final display of the board 
		
	}
	public void commence() {
		while(!g.gameFinished()) { // keep playing until 10 frames 
			while(true) { // keep executing until valid input 
				int pinsKnocked = readPins.pinsDown();  //method serves to sanitize input. returned only if int is valid
				try {
					g.performBowl(pinsKnocked);
					board.showBoard(g);
					break;
				}catch(Exception e) {// error message 
				      System.out.println("Oops invalid entry...Try again\n\n");
				}
			}
		}
	}
}
