/*this class is an extension of the Frame class, this handles the special features of the final frame, 
 * as mentioned earlier, the final frame can give the user an extra move, so we have to handle that 
 * case separately. 
 */


public class FinalFrame extends Frame {
	
	public FinalFrame (final Scoreboard frames) {
		super(frames);
	}
	/*method for updating the maximum length of the pinsKnocked array to 3, since you can have an extra in this frame */
	@Override
	protected void initializePinsKnocked() {
		pinsKnocked = new int[3];
	}
	public int getMaximumPins() { // maximum pins allowed in this frame
		if (isStrike() || isSpare()) { // if spare or strike happens, 3 frames occurs, therefore, 30 maximum to hit
			return 30;
		}
		else return 10; // otherwise at most, 10 can be dropped 
	}
	@Override
	protected int allowedMoves() { // updates the method from earlier to adjust the allowed moves
		if(isStrike() || isSpare()) { // spare or strike provide additional so you can have 3 
			return 3;
		}
		else return 2; // otherwise just 2 as usual 
	}
	@Override
	public int getScore() { // updates the method for handling score when strike or spare occurs 
		if (isStrike() || isSpare()) {
			return pinsKnockedOnSpecifiedThrow(0) +pinsKnockedOnSpecifiedThrow(1)+pinsKnockedOnSpecifiedThrow(2); 
		} // returns the results from the score obtained on the specific throws (0 = first, 1 = second, 2 = third throw)
		else return super.getScore(); //else just use regular score
	}
	@Override
	public String toDisplay() { // updates the printing method to adjust to the additional throw  
		StringBuilder sb = new StringBuilder();
		
		if (pinsKnockedFromFirstMove() == 10) { // first throw is a strike, display X
			sb.append("X");
		}
		else {									// else display the number
			sb.append(pinsKnockedFromFirstMove());
		}
		
		sb.append(" ");							// space for separation 
		
		if (pinsKnockedFromSecondMove() == 10) { // second move, if strike  also display X
			sb.append("X");
		}
		else if (pinsKnockedFromFirstMove() + pinsKnockedFromSecondMove() == 10) { // else if spare occurred 
			sb.append("/");															// then display /
		}				
		else {										
			sb.append(pinsKnockedFromSecondMove());					// no spare, then just show the number 
		}
		
		if (isSpare() || isStrike()) {		  // third throw, only allowed if strike or spare occurred. 				
			if(pinsKnockedOnSpecifiedThrow(2) == 10) {
				sb.append(" X");
			}
			else {
				sb.append(" " + pinsKnockedOnSpecifiedThrow(2));
			}
		}
		return sb.toString();
	}
	@Override
	protected int secondBonus() {
		return pinsKnocked[1];
	}
	
}
