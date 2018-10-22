# MsPacManEntry
MsPacManEntry was the first place entry in the 2018 IEEE Computational Intellegence and Gamce conference Ms.Pac-Man vs. Ghosts competition, Pacman track. It features an agent that plays Ms. Pac-man under partial observability conditions. The agent's decision making process is an artifical neural network generated using MM-NEAT (https://github.com/schrum2/MM-NEAT) and is written in Java.

http://www.pacmanvghosts.co.uk/results.html

# Features
The agent has a model for pills and a model for ghosts. The pill model allows the agent to path to pills that is cannot see. The ghost model keeps track of where ghosts might possibbly be, as well as if they are edible or are threats. The agent also uses a number of sensors of the game state as input to it's network. This process is described in the paper.

# Usage
Networks can be generated using the MM-NEAT library. XML files representing the network are placed in the main directory. The code will use a network named "bestPacMan.xml". To run the submission for testing purposes, execute the file ...src\main\java\pacman\main.Main. After making any changes to the source, run "mvn -u install" in the home directory to rebuild the library. This requires having Maven on your system.

# Paper
The paper describing the agent is a WIP. More to come soon!

# Screenshots
![screenshot 46](https://user-images.githubusercontent.com/15305479/46963246-b8c79b00-d06a-11e8-8b1a-43888be2249d.png)
