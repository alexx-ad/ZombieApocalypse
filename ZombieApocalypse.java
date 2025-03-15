import java.util.Scanner;
import java.util.HashSet;                  

public class ZombieApocalypse {
    
    //ANSI escape codes for colors
    public static final String RESET = "\u001B[0m";   //\u001B[ is the escape character in unicode/ANSI
    public static final String RED = "\u001B[31m";    //0m resets the color to default, 31m makes text red
    public static final String GREEN = "\u001B[32m";  //32m makes it green, 34m makes it blue, 33m makes it yellow
    public static final String BLUE = "\u001B[34m";   //final makes it so that their values cant be changed later on
    public static final String YELLOW = "\u001B[33m";

    public static int currentLevel = 1;                                                                       //keeps track of the level
    public static int colSize, rowSize;                                                                       //defines the grid size
    public static boolean hasKey;                                                                             //checks if the player has the key or no
    public static int playerX, playerY, exitX, exitY, zombieX, zombieY, zombie2X, zombie2Y, keyX, keyY;       //stores the variables for player, zombies, exit and key
    public static HashSet<String> obstacles = new HashSet<>();                                                //stores obstacles as (x,y) coordinates for easy lookup
    
    public static void clearScreen() {
        System.out.print("\033[H\033[J");  //\033[H moves the cursor to the top left of the terminal, \033[J clears everything below the cursor 
        System.out.flush();                  //the flush line ensures that the changes are immediately applied            
    }                                        //ANSI escape codes might not always be supported by all terminals 
    
    public static void loadLevel(int level){
        clearScreen();
        hasKey = false;             
        obstacles.clear();                             //resets everything when a new level loads 

        if (level == 1){                               //initialization of variables when user is at the 1st level 
            colSize = 10; rowSize = 10;                //basic Grid (10x10)
            playerX = 0; playerY = 0;                  //player starts at (0,0) and exit is at (9,9)
            exitX = colSize - 1; exitY = rowSize - 1;
            zombieX = 5; zombieY = 5;                  //zombies spawn at (5,5) and (7,7) 
            zombie2X = 7; zombie2Y = 7;
        } else if (level == 2){                        //initialization of variables when user is at the 2nd level, this time there's a bigger grid
            colSize = 15; rowSize = 15;                //larger grid (15x15)
            playerX = 0; playerY = 0;                  //same starting position, exit at (14,14)
            exitX = colSize - 1; exitY = rowSize - 1;
            keyX = colSize / 2; keyY = rowSize / 2;    //a key is introduced and it spawns at (7,7)
            zombieX = 6; zombieY = 6;                  //zombies spawn at (6,6) and (10,10)
            zombie2X = 10; zombie2Y = 10;

            //Define Obstacles 
            obstacles.add("3,3"); obstacles.add("3,4"); obstacles.add("4,3");      //adds obstacles at specified positions
            obstacles.add("7,8"); obstacles.add("8,8"); obstacles.add("8,7");
        } else if (level == 3){                        //initialization of variables when user is at the 3rd level
            colSize = 15; rowSize = 15;                //same as level 2 except for zombie positions and zombie movements
            playerX = 0; playerY = 0;
            exitX = colSize - 1; exitY = rowSize - 1;
            zombieX = 5; zombieY = 5;
            zombie2X = 10; zombie2Y = 10;

            obstacles.add("3,3"); obstacles.add("3,4"); obstacles.add("4,3");      //adds obstacles at specified positions
            obstacles.add("7,8"); obstacles.add("8,8"); obstacles.add("8,7");
            obstacles.add("12,13"); obstacles.add("12,12"); obstacles.add("13,12");
            
        } else {
            System.out.println(GREEN + "Congratulations! You survived all the levels. You Win!" + RESET);
            System.exit(0);        //when the player wins, the game stops running completely instead of looping endlessly
        }
        clearScreen();
    }
    public static void main(String[] args) {

        //Setup Game Data
        Scanner input = new Scanner(System.in);
        boolean gameOver = false;
        loadLevel(currentLevel);
        String floorTile = YELLOW + "▓▓" + RESET;
        String playerTile = BLUE + "@ " + RESET;
        String exitTile = GREEN + "E " + RESET;
        String zombieTile = RED + "Z " + RESET;
        String zombie2Tile = RED + "Z " + RESET;
        String keyTile = YELLOW + "K " + RESET;
        String obstacleTile = RED + "▓▓" + RESET; 

        //Main Game Loop:
        while (gameOver == false){                //start game loop
            clearScreen();                        //clear screen before drawing so that the output isnt messy

           //1. Draw Game Scene
           for (int y = 0; y < rowSize; y ++){                  //start outer for-loop (rows)
            for (int x = 0; x < colSize; x ++){                 //start inner for-loop (columns)
                if (x == playerX && y == playerY){              //if this grid coord is player's position..
                    System.out.print(playerTile);               //..print player tile
                } else if (x == exitX && y == exitY){           //if this grid coord is exit's position..
                    System.out.print(exitTile);                 //..print exit tile
                } else if (x == keyX && y == keyY && !hasKey){  //if this grid coord is key's position..
                    System.out.print(keyTile);                  //..print key tile
                } else if (x == zombieX && y == zombieY){       //if this grid is zombie's position..
                    System.out.print(zombieTile);               //..print zombie tile
                } else if (x == zombie2X && y == zombie2Y){
                    System.out.print(zombie2Tile);
                } else if (obstacles.contains(x + "," + y)){
                    System.out.print(obstacleTile);
                } else {                                      //else...
                System.out.print(floorTile);                  //draw floor tile ...print floor tile
                }
            }                                                 //end inner for-loop (x-axis)
            System.out.print("\n");                         //new line for next row 
           }                                                  //end outer for-loop (y-axis)

           //2. Get Player Input
           String choice = input.nextLine();
           int newX = playerX, newY = playerY;

           //3. Execute Player Action
           if (choice.equals("w") && playerY > 0){                    //ensuring that the player doesn't move out of the bounds..
            newY--;
           } else if (choice.equals("s") && playerY < rowSize - 1){   //..if the player is at the edge, the command is ignored 
            newY++;
           } else if (choice.equals("d") && playerX < colSize - 1){   //the idea being that while moving up or down, the player isnt' at the edge of the y-axis..
            newX++;
           } else if (choice.equals("a") && playerX > 0){             //..and while moving right or left, the player isn't at the edge of the x-axis
            newX--;                                                            //the relational operator helps us check and only take the command if they're within range 
           }

           //Prevent moving into obstacles 
           if (!obstacles.contains(newX + "," + newY) && newX >= 0 && newX < colSize && newY >= 0 && newY < rowSize){  //check if move is valid
            playerX = newX;                                                                                            //Update position only if its valid
            playerY = newY;
           }

           //Check if player picked up the key
           if (playerX == keyX && playerY == keyY) hasKey = true;

           //4. Check Win Condition
           if (playerX == exitX && playerY == exitY && (currentLevel < 2 || hasKey)){
            currentLevel++;
            loadLevel(currentLevel);
            continue;            //immediately skips the rest of the loop iteration and moves on to the next step
           }

           //Execute monster actions by calling their classes
           moveZombie();
           moveZombie2();

           //6. Check Loss Condition
           if ((zombieX == playerX && zombieY == playerY) || (zombie2X == playerX && zombie2Y == playerY)){
            gameOver = true;
            System.out.println(RED + "Your brains were eaten by the zombie!" + RESET);
           }   
        }                                         //end game loop
        input.close();
    }
    public static void moveZombie(){
        int zombieChoice = (int) (Math.random()*4);
        int newX = zombieX, newY = zombieY;

        if (zombieChoice ==0){
            newX = (zombieX + 1) % colSize;
        } else if (zombieChoice == 1){
            newX = (zombieX - 1 + colSize) % colSize;
        } else if (zombieChoice == 2){
            newY = (zombieY - 1 + rowSize) % rowSize;
        } else if (zombieChoice == 3){
            newY = (zombieY + 1) % rowSize;
        }

        if (!isObstacle(newX, newY)){
            zombieX = newX;
            zombieY = newY;
        } else {
            moveZombie();
        }
    }
    public static void moveRandom(){
        int randomMove = (int) (Math.random()*4);
        int tempX = zombie2X, tempY = zombie2Y;

        if (randomMove ==0){
            tempX = (zombie2X + 1) % colSize;
        } else if (randomMove == 1){
            tempX = (zombie2X - 1 + colSize) % colSize;
        } else if (randomMove == 2){
            tempY = (zombie2Y - 1 + rowSize) % rowSize;
        } else if (randomMove == 3){
            tempY = (zombie2Y + 1) % rowSize;
        }

        if (!isObstacle(tempX, tempY)){
            zombie2X = tempX;
            zombie2Y = tempY;
        } else {
            moveRandom();
        }
    }
    public static void moveZombie2(){
        int chance = (currentLevel == 3) ? 80 : 70;
        int zombie2Choice = (int) (Math.random()*101);

        if (zombie2Choice <= chance){
            int newX = zombie2X, newY = zombie2Y;
            boolean moved = false;

            if (Math.abs(zombie2X - playerX) > Math.abs(zombie2Y - playerY)){
                if (zombie2X < playerX){
                    newX = zombie2X + 1;
                } else {
                    newX = zombie2X - 1;
                }
            } else {
                if (zombie2Y < playerY){
                    newY = zombie2Y + 1;
                } else {
                    newY = zombie2Y - 1;
                }
            }

            if (!isObstacle(newX, newY)){
                zombie2X = newX;
                zombie2Y = newY;
                moved = true;
            }

            if (!moved){
                if (zombie2X < playerX && !isObstacle(zombie2X + 1, zombie2Y)){
                    zombie2X++;
                } else if (zombie2X > playerX && !isObstacle(zombie2X - 1, zombie2Y)){
                    zombie2X--;
                } else if (zombie2Y < playerY && !isObstacle(zombie2X, zombie2Y + 1)){
                    zombie2Y++;
                } else if (zombie2Y > playerY && !isObstacle(zombie2X, zombie2Y + 1)){
                    zombie2Y--;
                } else {
                    moveRandom();
                }
            }
        } else {
            moveRandom();
        }
    }
    public static boolean isObstacle(int x, int y){
        return obstacles.contains(x + "," + y) || (x == zombieX && y == zombieY);
    }
}