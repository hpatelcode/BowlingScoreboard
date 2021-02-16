/*this is a user class for the user to hold the information of the frames being played.
 * 
 */
public class User {
	private Scoreboard frames = new Scoreboard(); // user has a scoreboard
	
	public boolean doneWithCurrentFrame() { // method for determining if current frame is finished 
		return frames.currentFrame().movesFinished();
	}
	public boolean gameTerminated() { // method for determining end of the game 
		Frame current = frames.currentFrame();
		if (current.isFinalFrame() && current.movesFinished()) return true; // ends when final frame's moves are done
		else return false;
	}
	public Scoreboard getAllFrames() { // returns all frames 
		return frames;
	}
	public int finalScore() { // returns total score 
		return frames.finalScore();
	}
	public Frame currentFrame() { // access to current frame
		return frames.currentFrame();
	}
	
}
