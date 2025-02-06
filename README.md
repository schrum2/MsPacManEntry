# MsPacManEntry
MsPacManEntry was the first place entry (Squillyprice01) in the 2018 IEEE Computational Intellegence and Gamce conference Ms.Pac-Man vs. Ghosts competition, Pacman track. It features an agent that plays Ms. Pac-man under partial observability conditions. The agent's decision making process is an artifical neural network generated using MM-NEAT (https://github.com/schrum2/MM-NEAT) and is written in Java.

http://www.pacmanvghosts.co.uk/results.html

# Features
The agent has a model for pills and a model for ghosts. The pill model allows the agent to sense pills that it cannot see. The ghost model keeps track of where ghosts might possibbly be, as well as if they are edible or are threats. The agent also uses a number of sensors of the game state as input to it's network. This process will be described in an upcoming paper.

# Usage
Networks can be generated using the MM-NEAT library. XML files representing the network are placed in the main directory. The code will use a network named "bestPacMan.xml". To run the submission for testing purposes, execute the file ...src\main\java\pacman\main\Main.java. After making any changes to the source, run "mvn -U install" in the home directory to rebuild the library. This requires having Maven on your system. This class file can also be run from inside an IDE like Eclipse.

# Paper

```
@INPROCEEDINGS{price:cec19, 
    author={William Price, and Jacob Schrum}, 
    booktitle={Proceedings of the Congress on Evolutionary Computation (CEC 2019)}, 
    publisher={IEEE},
    title="{Neuroevolution of Multimodal Ms. Pac-Man Controllers Under Partially Observable Conditions}", 
    year={2019},
    url={https://collections.southwestern.edu/s/suscholar/item/213}
}
```

# Screenshots
![screenshot 46](https://user-images.githubusercontent.com/15305479/46963246-b8c79b00-d06a-11e8-8b1a-43888be2249d.png)

# Videos and other content

https://people.southwestern.edu/~schrum2/SCOPE/popacman.php
