Zombie Apocalypse

Rules and Introduction:

- A simple grid-based, turn-based game with a hero character that the player controls denoted with "@". 
- The game starts in a 10x10 grid with the hero character in the upper left corner.
- Your goal is to reach the exit at the lower left denoted by "E".
- A Zombie will be present and moving to stop you which will happen if you collide with it. 
- Zombie denoted by "Z". 
- Player movement through "WASD" keys.
- "W" - Move up.
- "A" - Move left.
- "S" - Move down.
- "D" - Move right.


Added Features:

- Besides the base code, there are a couple of added features in the game:

1. Added rules to the algorithm to constrain the player to the level, where they can't move outside of the level.
- By ensuring that the user command is ignored if the player is at the edge of the grid, this is applied.
- Relational operators helps us check and only accept the command if they're within range. 

2. Added a second zombie.
- The first zombie spawns at (5,5) in the grid position, the second one spawns at (7,7).
- The second zombie is also denoted with a "Z".

3. Multiple levels.
- Added 2 additional levels to make a total of 3. 
- Created a new class called loadLevel in order to do that. 
- Inside the loadLevel class; initialized grid, player, exit, and zombie values for each level.
- Any additional characteristics that the levels might have was also included in it. 
- The class was closed with a victory statement for whoever makes it to the end.
How each level works:
(Level 1): 
- Level 1 is the most standard level. 
- It starts with a 10x10 grid with 2 zombies spawning at (5,5) and (7,7).
- One zombie moves in a random fashion.
- The other has a 30% chance to move randomly, the other 70% of the time, it follows the movement of the player and closes it out.
(Level 2):
- Level 2 has a bigger 15x15 grid with 2 zombies spawning at (6,6) and (10,10).
- The zombie movements are the same as the first level.
- This level contains a key spawning at the middle of the grid that is required to unlock the exit.
- It also contains obstacles that the player has to maneuver during playing.
- Players can't move into obstacles and the obstacles are denoted through a red square. 
(Level 3): 
- It has the same grid size as level 2. The zombies spawn at (5,5) and (12,12).
- This level also has a key spawning at the middle of the grid that is required to unlock the exit.
- Contains obstacles and the zombies are smarter as well.
- Instead of one moving completely randomly and the other having a 70% chance to move smarter, both zombies in this level are smart.
- They have a 20% chance to stay still and not move but a 80% chance to close out the player. 

4. Smarter Zombies.
- While the first zombie moves randomly, the second zombie is coded to move like its chasing the player. 
- It compares the position of the player and itself and closes the player out. 
- Using absolute value, it first determines if the zombie is farther away from the player vertically or horizontally.
- Depending on that, it closes out the furthest direction first and then the next. (In level 3, both zombies do that)
- Level 1 and 2 has 1 random zombie, 1 smart zombie with a 70% chance to act smart.
- Level 3 has 2 smart zombies, both with an 80% chance to act smart. 

5. Obstacles.
- Obstacles were added into the second and third level of the game.
- It is denoted by a red square.
- Used HashSet in order to create and implement obstacles.
- HashSet stores obstacles as (x,y) coordinates which helps with easy lookup. 
- It is a collection that stores unique values and is faster than an array or a list in checking if an obstacle exists. 
- Stores obstacles as "x,y" strings. For example: "3,3" represents an obstacle at (3,3).

6. Random placed keys that are needed to unlock level exit.
- In levels 2 and 3, there is a key that spawns at the middle of the grid that is required to unlock the exit for those two levels. 
- Denoted with a "K".

7. Improved graphics.
- Used ANSI escape codes to implement colors for colored ASCII characters.
- Tried to implement Unicode elements but since everything wasn't supported, stuck to colored symbols and Unicode blocks. 
- Used ANSI escape code for console clears as well, with a System.out.flush() line to immediately implement changes. 
- While it may not support every terminal, console clears helps with making the output less cluttered and messy. 
- The player symbol is a blue "@".
- The zombie symbol is a red "Z".
- The key symbol is a yellow "K".
- The exit symbol is a green "E".
- The floor tile is a yellow colored solid Unicode block.
- The obstacle tile is a red colored solid Unicode block.
- Colored output for win or loss. Green letters for a win and red for a loss.


Additional Code Details and Added Elements: 

- In order to make sure that the player doesn't accidentally move into the obstacles, newX and newY is first used to check. (For the 1st zombie)
- For the second zombie, tempX and tempY is used.
- It checks if the move is legal and only then it updates the actual position of playerX and playerY. 
- Created separate classes for zombie movement for easier readability and less repetition.
- This makes the code more streamlined and less cluttered. Also follows DRY principles.
- This makes the code more modular and if we ever wanted to edit zombie movements, only editing the separate functions would work.
- Instead of having to edit in main and fix a lot of code, just editing the separate functions would be easier to do and to implement. 
- A seperate clearScreen class also created to help with console clears so that it's ready to be called anytime. 