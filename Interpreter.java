import java.io.PrintStream;
// this is a basic class for printing commands to the user and asking for inputs to interpret to the driver program
public class Interpreter {
	private PrintStream out = System.out;
	private Game g;
	
	public Interpreter(final Game g) {
		this.g = g;
	}
	public void printGreeting() { // basic greeting for bowler 
		out.println("Welcome to the Bowling Scoreboard Interface!");
	}
	public void fetchPinsKnocked() { // asking user what they bowled
		out.print("How many pins hit in " + g.bowler.currentFrame().toString() + ":");
 	}

}
