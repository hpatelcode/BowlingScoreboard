# BowlingScoreboard
This project allows the user to enter the score for a single game of bowling frame by frame and displays the final scoreboard.


## General Information 
A game of bowling consists of 10 frames, where each frame has 2 attempts to knock down 10 pins. For brevity:

Strike = 10 pins hit all at once.

Spare = 10 pins hit in 2 attempts.

Otherwise score of frame is the combined total of the 2 attempts. 

Strike bonus: 10 + score of next 2 attempts.

Spare bonus: 10 + score of next bonus.

## Project Constraints
* Assume all inputs are not entered at once
* Incorporate error checking
* Implement logic of last frame where an extra attempt is awarded for a strike or spare (3 attempts total instead of 2)
* Business logic -- keeping the running total of the score as well as proper strike bonus and spare bonus

## Design Diagram

![uml](https://user-images.githubusercontent.com/25422131/108031488-efc56700-6ffe-11eb-9a12-5337baca7809.png)

## Approach
Since all inputs are not entered at once, using an predetermined input array to parse information is out of the question. This also means that calculating the bonus scores for strikes and spares become less obvious since the data for the next 1 or 2 bowls attempted isn't readily available until the user inputs their desired score. As a result, there need to be multiple classes that keep track of the current Frame, nextFrame, as well as lastFrame for proper game progression and game termination. 
