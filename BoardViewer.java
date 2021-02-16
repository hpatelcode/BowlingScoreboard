import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/*this class is for formatting the display of the board
 * each row will have the frame and its respective 2 scores, and a final row for the total.
 */
public class BoardViewer {
    private static final String LABEL = "| %1$-15s | %2$-10s | %3$-10s | %4$-10s | %5$-10s | %6$-10s | %7$-10s | %8$-10s | %9$-10s | %10$-10s | %11$-10s |\n";

    private PrintStream out = System.out;
    
    public void showBoard(final Game g) {
    	printRows(); 
    	List<String> individualScores = new ArrayList<String>(10);
    	for (Frame frame : g.bowler.getAllFrames()) { // add the score for each frame onto the list of scores
    		individualScores.add(frame.toDisplay()); // <--- toDisplay method returns either an X, / , or a number
    	}
    	individualScores.add(String.valueOf(g.bowler.finalScore())); // push the final score to the last row 
        out.format(LABEL, individualScores.toArray()); // formatting for the display

    	
    }
    private void printRows() { // method for printing the frame labels in an orderly fashion
    	out.format(
    			LABEL,
    		     "Frame 1", "Frame 2", "Frame 3", "Frame 4", "Frame 5",
                 "Frame 6", "Frame 7", "Frame 8", "Frame 9", "Frame 10", "Total");
    }
}
