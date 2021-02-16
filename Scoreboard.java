import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* this class wraps the Frame class in the Scoreboard. The Scoreboard contains a collection 
 * of 10 different frames for the overall game. the Iterable type is used to navigate and
 * loop through the individual frames of the scoreboard. 
 */

public class Scoreboard implements Iterable<Frame>{
	
	
	private Frame current;		// tracking the current frame
	private List<Frame> frames = new ArrayList<Frame>();   // list to contain the frames. 
	
	public Scoreboard() { // constructor to initialize 
		for (int i = 1; i < 10; i++) { // adds the first 9 frames, hence the start from i = 1.
			frames.add(new Frame(this));
		}
		frames.add(new FinalFrame(this)); // 10th frame is added separately with its own properties. 
		
		current = frames.get(0); // current is the first added frame at 0th index. 
	}
	public int finalScore() { // final score after playing all 10 frames to completion 
		int total = 0;
		for (Frame frame : frames) {
			total += frame.getScore();  // running total of each frames score added 
		}
		return total;
	}
	public List<Frame> getAllFrames(){ // method to access the frames useful for printing 
		return Collections.unmodifiableList(frames);
	}
	public Frame currentFrame() { // returning the current frame for this method 
		return current;
	}
	public Frame nextFrame(final Frame frame) { // gets the next frame
		if (isFinalFrame(frame))throw new IllegalStateException("Invalid Entry"); // input santization -- out of bounds when at last frame
		else return frames.get(frames.indexOf(frame) + 1);     // else return the frame at next index. 
	}
	public Frame prevFrame(final Frame frame) { // gets the previous frame
		if (isInitialFrame(frame))throw new IllegalStateException("Invalid Entry");// input sanitization -- out of bounds when at first frame
		else return frames.get(frames.indexOf(frame) - 1);
	}
	public boolean isInitialFrame(final Frame frame) { // checks if its the first frame 
		return frames.indexOf(frame) == 0? true : false;
	}
	public boolean isFinalFrame(final Frame frame) { // check if its the last frame 
		return frames.indexOf(frame) == frames.size() - 1? true : false;
	}
	public void advance() { // method for advancing to the next frame as game progresses 
		if(isFinalFrame(current)) {
			throw new IllegalStateException("Invalid Entry"); // input sanitization if last frame (cant advance further )
			
		}
		if (!current.movesFinished()) { // input sanitization -- cant advance if the user hasnt exercised all the frames moves
			throw new IllegalStateException("Invalid Entry");
		}
		else current = frames.get(frames.indexOf(current) + 1); // else updates the current frame to frame at next index. 
	}

	@Override
	public Iterator<Frame> iterator(){
		return frames.iterator();
	}
	@Override
	public String toString() {
		return "currentFrame=" + current + ", frames=" + frames; // helper for displaying to board 
	}

}
