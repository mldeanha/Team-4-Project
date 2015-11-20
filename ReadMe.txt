--------------------SudokoPlus--------------------

Developers:

-Mitchell Baer
-Eric Celerin
-Matt Dean-Hall

Welcome!

This project is the brain-child of all 3 of us and it's being used as the final project for CS3141 - Team Software Project (TSP).

For instructions on running the game (program), read the most recent update.

--------------------------------------------------

UPDATE 1 - 10/23/15:

As of Sprint 1, the project is incomplete still (expected). There's not much functionality
in terms of the GUI, but if you do in fact want to run each .java file, they will all at 
least do something. 

If you run GameLogic.java, you'll notice that there are some things that are printed to the terminal, and you can go into the file itself and adjust different things in the main method in order to spit out different results. We've written an algorithm that randomly selects 1 of 3 different puzzles per difficulty. If you wish to adjust the difficulty, select 1 (easy), 2 (medium), or 3 (hard), and it will spit out a different puzzles of the selected difficulty.

If you run GameGUI.java, the only thing that will pop up is a window with number buttons (1-81) in a grid.

If you run Client.java, it will prompt you for a server IP.

If you run SudokuPlusServer.java, it will run a server in the background that will accept different user connections.

If you run UserThread.java, it will run in the background, but it will assign a thread to a user and that's the link between the server and the GUI.

-------------------------------------------------

UPDATE 2 - 11/6/15

As of Sprint 2, the project is incomplete still (getting closer to the finished product, but still expected).
We've added more functionality, and it's possible for the user to actually make inputs now and to know whether the number
they're trying to insert is correct or not, as well as many other features which you will discover later.

Running this program is still very ugly, and the finished product won't be as ugly.

To run on your computer, you need to open up SudokuPlus.zip in Eclipse and run:

1.) SudokuPlusServer.java
2.) Client.java (click cancel on the server window if you're running the server and client on the same PC)
3.) Play away

There are 2 text boxes on the right that don't server a purpose as of yet, so ignore those.

If you select a button from the list of buttons that are orange/red (1-9), that is the number you're going to input.
If you click in an empty box, and it turns red, that means that you've incorrectly guessed the value there.
If the color changes to look like all the other buttons, then you've correctly guessed the value that goes there.
If you select a number that was put in the puzzle originally, you'll notice it turns blue along with all of the other numbers that match that value.
This is so that you can see where all of the other values that are the same are in the puzzle so that helps with finishing the puzzle.

If you want to try multiple users working on the same puzzle:

1.) Run the SudokuPlusServer.java on a PC
2.) Run Client.java (On same PC as server)
3.) Run Client.java from another computer and when it prompts for the IP, type the IP of the computer running the server.

If either of the people playing make a move, it will update their correct guess onto the screens of both of them, so you can use teamwork to finish a puzzle.

-------------------------------------------------

UPDATE 3 - 11/19/15

As of Sprint 3, this project is nearing completion and is currently at a spot where the only issues are small bugs. When you boot up the game, you'll notice a timer. This isn't the
time you've been playing the game, but rather, the time the server as been running for, so don't get that confused. Up in that corner too, you'll see a score. If you guess a number and it's incorrect, 100 will be deducted from your total, but if you correctly guess a number, 100 will be added.

As of 11/20/15, the only way to run our application is still ugly, but in the next week, we're planning on making it into an executable, but until then the way  to run it
is as follows:

1.) Run the SudokuPlusServer.java on the PC you want to run the server on.
2.) Run OptionGUI.java (this is the new file to run in place of Client.java)
3.) Select difficulty, type in a username, and type in the server ip. (IMPORTANT: The IP you type in is the IP of the computer that is running the server.)
3.5) If you're planning on playing the game and hosting the server on the same computer, you need to leave the IP field blank and just hit submit. Everything else still applies (username/difficulty).
4.) Win!

The instructions of the game are the same as usual, but now that the application is in a finished state, here are the rules for those who don't know:

1.) Numbers can only appear once (and only once) in each row.
2.) Numbers can only appear once (and only once) in each column.
3.) Numbers can only appear once (and only once) in each 3x3 square.

If you make a correct guess, the board will update and your number will appear like the others.
If you make an incorrect guess, the square will turn red, indicating that the guess is incorrect.
Score will be updated accordingly.

And that's it!

Some other things to note:

-When you select a number on the bottom, all of the numbers in the puzzle should highlight blue indicating their locations.
-You can also select the number in the puzzle itself, and it will do the same thing.
-If you look in the top corner and select the dropdown menu, there is an about page that has all of our names as well as some other information.
-The text box isn't working, so no messages can be sent between users.


