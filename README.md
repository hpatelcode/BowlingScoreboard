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

![uml](https://user-images.githubusercontent.com/25422131/108034331-5b113800-7003-11eb-9573-faf953249de1.png)

## Approach
Since all inputs are not entered at once, using an predetermined input array to parse information is out of the question. This also means that calculating the bonus scores for strikes and spares become less obvious since the data for the next 1 or 2 bowls attempted isn't readily available until the user inputs their desired score. As a result, there need to be multiple classes that keep track of the current Frame, nextFrame, as well as lastFrame for proper game progression and game termination. 

The UML diagram above shows the proper breakdown of the classes and how they are implemented, extended, and inherited. 

## Technology

* Eclipse IDE 
* Java

## Files

This project was written with the intent to highlight the power of object oriented design. When possible, my goal was to implement useful classes, methods, and functions to organize data parsing and data accessing while maintaining a proper scheme and comments. 

The goal was also to write for future iterations in mind (eg. implementing a UI/API, adding more features, etc...)This is why the files have their own categories. There are files that serve to drive the core business model/logic of the program. These are:
* Frame.java (implements basics of a frame)
* LastFrame.java (extension of frame with additional properties)
* Scoreboard.java (holds collection of frames and performs operations)
* User.java (bowler that holds info on assigned frames)
* Game.java (setting up metrics of game in progress, knocking pins, etc..)

The second set of files serve to visualize the game with a Command Line based interface. These files are:
* BoardViewer.java (contains methods to print the scoreboard)
* Interpreter.java (contains methods to print directions, ask for inputs, greetings, error message)
* PinsBowled.java (used to sanitize input and error checking)
* ScoreInterface.java (driver class aka "main")

With this practice, the overall business logic has its own implementation and a separate set of files can communicate to business logic to expand on the functionality similar to how the command line interface is doing. With this, a REST API can be created easily as well as advanced GUIs.

## Setup
To run this project, you can execute the given .jar file provided in this repo as such:
```
$ java -jar /path/to/BowlingScoreboard.jar
```
#### Another way:

* Load all source files to IDE such as Visual Studio or Eclipse
* Compile the project
* Run the "ScoreInterface.java" file

## Performance and Current Bugs

Program works for majority of the test cases (90+%). In the current version, there is one small bug on the __last__ frame when inputting either a Strike or Spare and will not accept 2 consecutive strikes even though it is a legal input. Rest of the cases work as intended. 

## Sample Output
