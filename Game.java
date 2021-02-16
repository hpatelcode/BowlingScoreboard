/* class used to set up the metrics of the game, eg. the user, the scoreboard, and the progression of the game 
 */

public class Game {
	public User bowler = new User(); //initialize a new User of the game, aka a bowler
	
	public void performBowl(final int pinsKnocked) { // method to perform a bowl
		Scoreboard frames = bowler.getAllFrames(); // get the bowlers assigned frames
		Frame current = bowler.currentFrame(); // get current frame 
		current.performBowl(pinsKnocked); // perform the bowl (no recursion here...this method is the one from Frame class) 
		
		if(current.movesFinished() && !current.isFinalFrame()) // check if this isnt the last frame
			frames.advance(); //advance if all the moves for this frame are finished (eg. after 2 bowls)
		
	}
	public boolean gameFinished() { // game ends when the bowler goes through all their allotted frames
		if (bowler.gameTerminated()) return true;
		else return false;
	}
}
