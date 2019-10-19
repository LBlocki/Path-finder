# PathFinder

This is my simple app to present few of the most popular graph traversal algorithms. Tested on JDK 13. Uses maven as building tool.
Window has a fixed size 1200x800 so it may be overflowing some screens ( 14 inch laptops for example ).

## Running application
  You can run the app using following command:
   ```bash
   mvn clean package javafx:run 
   ```
   Maven will package application into .jar file and run it. If you wish to only create .jar than just run:
   ```bash
   mvn clean package
   ```  
## Generating API
  You can create JavaDoc by excecuting:
   ```bash
   mvn javadoc:javadoc -Dshow=private
   ```
   Maven will generate documentation that you can find under target/site/apidocs/index.html

## Controls:

### Before you can click 'start' you must place starting node and ending node first:

- Starting node: Hold 's' key and left click on any position on the board.
- Ending node: Hold 'e' key and left click on any position on the board.

#### If you wish to place these nodes somewhere else after placing them already just hold the proper button and click on desired space. Program will handle switching node's positions.

### Blocking nodes

You can place these nodes with left mouse click. Holding and clicking is both supported. They serve as obsticles during path searching to the ending node.

### Deleting nodes

You can delete any node on the board by clicking or holding right mouse button. Please note that if you delete starting or ending node you have to place them again before running the algorithm.

## Picking algorithm

Just press left mouse button on the algorithm you wish to use. Only one algorithm can be chosen at the same time.

## CheckBoxes

- Diagonal Search: Enables algorithm to move in all 8 directions instead of basic 4 (South, East, North, West).
- Instant Search: For fastest possible performance. Program will skip updating interface and attempt to find the path as fast as possible. Only after finding the path ( if there is one ) it will update interface.
- Don't cut corners: Example: If there are blocking nodes on the south and west program will not consider south-west corner even with diagonal search turned on.

## Buttons

- Play: To run the algorithm. First, starting node and ending node must be placed on board. Also note that if algorithm has already finished work you have to press stop for it to reset.
- Pause/Stop: You can pause the algorithm's work in any time. It will change to 'Stop' If you hit 'Start' again it will simply continue it's job. If u press 'Stop' than it will stop running and clear the board ( it will leave blocking and start/end blocks alone tho),
- Clear: Clears the entire board. Please note that ifyou clicked 'Start' and the algorithm is runnning you have to stop it first by clicking stop button before cleaning will be avaliable.

## Slider

Simple slider to control speed of the algorithm. 100% is max and 1% will be approximatey 1-3 moves per second.

### Have fun :)
