
/*frame class for a scoreboard. this class contains all the properties associated with a single
 * frame in a scoreboard.
 */
public class Frame {
	
	protected int[] pinsKnocked; // array to keep track of the pins dropped in each move
	private Scoreboard frames; 
	
	private int movesMade; 
	
	public Frame(final Scoreboard frames) { // basic constructor 
		this.frames = frames;
		initializePinsKnocked();
	}
	
	public int getCurrentMove() { // method returns which move the bowler is on. 
		return movesMade + 1; // (in bowling there are just 2 moves per frame so you need to keep track. 
	}
	
	protected void initializePinsKnocked() { // allocating the array for the pins you knocked down 
		pinsKnocked = new int[2];
	}
	public boolean activeFrame() { // method for determining if the frame is active meaning if the bowler already started 
		return movesMade > 0? true : false; //playing for this move 
	}
	public boolean isCurrentFrame() { // method for determining if the frame in question if the current frame the bowler is on
		return frames.currentFrame().equals(this);
	}
	/* note the  method below is not a recursive call. the method below checks if the frame is the first frame, 
	 * the parameter returned is the method from the Scoreboard class which also implements the isIniitialFrame. Variables
	 * were assigned as such to keep a consistent flow. 
	 */
	public boolean isInitialFrame() {
		return frames.isInitialFrame(this);
	}
	/*method to check if the frame is the last frame. this is crucial because rules of bowling state that in the final
	 * frame, a strike awards an extra move to the bowler (3) instead of the usual (2). 
	 */
	public boolean isFinalFrame() {
		return frames.isFinalFrame(this);
	}
	public Frame nextFrame() { // method for getting the next frame
		return frames.nextFrame(this); // return the result from the method in the scoreboard class
	}
	public void performBowl(final int pinsKnocked) { // method for making moves and hitting down pins
		if (movesFinished()) {
            throw new IllegalStateException("Out of moves");
		}
		if (pinsKnocked < 0 || pinsKnocked > 10) { // input sanitzation -- cant be negative or more than 10
            throw new IllegalStateException("Invalid Entry");
		}
		if (pinsKnockedFromFirstMove() + pinsKnocked > 10) {// if the pins hit from first move and second move are more than 10
            throw new IllegalStateException("Invalid Entry"); // it is also an error
		}
		this.pinsKnocked[movesMade++] = pinsKnocked; //add the pins hit in the array we declared earlier 
	}
	public boolean movesFinished() { // method for determining if there are no more moves left, this is for advancing to next frame
		return movesMade == allowedMoves();
	}
	public boolean isStrike() { // method for checking valid strike. 
		return pinsKnockedFromFirstMove() == 10; // strike occurs when the bowler hits 10 on the first move
	}
	public boolean isSpare() { // method for checking valid spare. 
		if (!isStrike() && pinsKnockedFromFirstMove() +  //spare occurs when the bowler manager to hit all 10 pins within 
				pinsKnockedFromSecondMove() == 10) {     // 2 moves 
			return true;
		}
		else return false;
	}
	protected int allowedMoves() { // method for determining how many moves are allowed in the current frame
		if(isStrike()) return 1; // if the first bowl was a strike, you are only allowed 1 move in this frame
		else return 2;           // otherwise, you are allowed 2 moves 
	}
	public int pinsKnockedFromFirstMove() { // method for getting the score from the first bowl
		return pinsKnocked[0];
	}
	public int pinsKnockedFromSecondMove() {// method for getting the score from the second bowl 
		return pinsKnocked[1];
	}
	/* below is the method for getting the pins hit at a specific index (throw).  
	 * index 0 is the first throw, index 1 is the second throw, and in the case of last frame, 
	 * index 3 would be the third throw. boundary is not hard coded since in the last frame, the 
	 * length of the pinsKnocked array can be either 2 or 3 depending on whether there was strike or spare. 
	 */
	protected int pinsKnockedOnSpecifiedThrow(int index) { 
		if (index > pinsKnocked.length) {
            throw new IllegalArgumentException("Out of bounds");// input sanitization -- out of bounds exception
		}
		return pinsKnocked[index];
	}
	/* below is the method for getting the score for the CURRENT Frame only. based on the rules of the bowling, 
	 * here is the breakdown: 
	 * if it is a spare, score is 10 + the pins hit from the move afterwards.
	 * if it is a strike score is 10 + the pins hit in the next TWO moves.
	 * otherwise the score is just the pins bowler knocked in their 2 moves. 
	 */
	public int getScore() {
		int score = 0;
		if (isSpare()) {
			score = 10 + nextFrame().pinsKnockedFromFirstMove();
		}
		else if (isStrike()) {
			score = 10 + nextFrame().pinsKnockedFromFirstMove() + nextFrame().secondBonus(); // here secondBonus needs to be 
		}																					//implemented separate to handle
		else {																				// the case of 2 back to back strikes
			score = pinsKnockedFromFirstMove() + pinsKnockedFromSecondMove();
		}
		return score;
	}
	/*method below fetches the second bonus for when you hit a strike. Note that if the frame after the first strike
	 * was also a strike, you would not get a second throw, hence, I cant say .pinsKnockedFromSecondMove above ---------^^
	 * i have to instead, advance to the next frame. If there werent 2 consecutive strikes, i can simply get the second
	 * move's score by using the index of the array. 
	 *Ex: Frame 2 = 10, (bonus would be      vs.      Frame 2 = 10           (bonus would be from both 
	 *	  Frame 3 = 10;  from frames 3                Frame 3 move 1 = 2      moves from frame 3)
	 *    Frame 4 = 5    and 4)                       Frame 3 move 2 = 4
	 */
	protected int secondBonus() { 
		if (isStrike()) {
			return nextFrame().pinsKnockedFromFirstMove(); // case of strike --> advance to next frame 
		}
		else return pinsKnocked[1]; // no strike --> bonus is the score of second move.
	}
	/* basic string display method for displaying to the scoreboard. Strike is represented with X
	 * Spare is represented with / 
	 * and numbers otherwise. 
	 */
	public String toDisplay() {
		if (movesMade == 0)return "";
		if (isStrike()) return "X";
		if (isSpare()) return pinsKnockedFromFirstMove() + "  /";
		else return pinsKnockedFromFirstMove() + "  " + pinsKnockedFromSecondMove();
	}
	/*method for displaying the tracking of game progression in string format. user should know which 
	 * frame and move theyre currently on. 
	 */
	@Override
    public String toString() {
        return "Frame #" + (frames.getAllFrames().indexOf(this) + 1) + ", Move #" + getCurrentMove();
    }

	
	
}
