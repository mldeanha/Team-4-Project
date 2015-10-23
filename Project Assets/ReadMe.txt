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

--------------------------------------------------



