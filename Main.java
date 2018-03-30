package DungeonDominator;

import java.util.*;
import java.io.*;

public class Main {
	
   public static void main (String[] args) throws FileNotFoundException{

       Scanner in = new Scanner (System.in); // Read the user input
	   Dungeon myDungeon = null;

	   // numbers used for input in the various cases
	   int height, width;
	   String officialName1, officialName2, name1, name2;

	   // print the welcome screen
	   System.out.println("Welcome to Dungeon Dominator\n"); // name ideas: Dungeon Dominator
	   
	   System.out.println("Would you like to see the guide first?\n"
	   		+ "If you do, type in 'Y' and press enter\n"
	   		+ "If you do not, type in 'N' and press enter");
	   String rulesChoice = in.next();
	   rulesChoice = rulesChoice.substring(0,  1).toUpperCase();
	   if(rulesChoice.equals("Y")){
		   System.out.println("\nDungeon Dominator guide:\n\n"
		   		+ "Dungeon Dominators is a two player board game where they have their monsters battle in a dungeon.\n"
		   		+ "Each player will enter their name and the first letter of their name will represent their monsters\n"
		   		+ "on the board. The players will then be able to choose the size of the board (recommended size is 4x4).\n"
		   		+ "Each player will get their own list of monsters with varying health placed throughout the board.");
		   //example dungeon for rules
		    Dungeon tempDungeon = new Dungeon(4, 4);
		    Monster guide1 = new Monster("A", 3);
		   	tempDungeon.setMonster(1, 2, guide1);
		   	Monster guide2 = new Monster("A", 1);
		   	tempDungeon.setMonster(3, 3, guide2);
		   	Monster guide3 = new Monster("F", 2);
		   	tempDungeon.setMonster(1, 3, guide3);
		   	System.out.println(tempDungeon.printInfo() + "\n");
		   	System.out.println("\nHere is an example dungeon. There are two monsters owned by the player 'A', one with 3 health and the other with 1.\n"
		   			+ "The other play is 'F' and the monster only has 2 health. To battle, player 'A' can use WASD to move\n"
		   			+ "all of his monsters. In this case, player 'A' will press 'S' to move his monsters down and attack the last monster.");
		   	tempDungeon.shift("s", 1, "A", "F");
		   	System.out.println(tempDungeon.printInfo() + "\n");
		   	System.out.println("\nAs we can see, 'F' got attacked and lost the battle, and 'A' only lost a bit of health. You also may have noticed\n"
		   			+ "that the other 'A' monster moved from the bottom to the top. This is because all of the 'A' monsters move together, and if it\n"
		   			+ "moves out of bounds, it will move to the opposite side.\n\n"
		   			+ "This is how the health and attacking works:\n"
		   			+ "If the attacking monster has more health than the defending monster, the attaking monster will win the fight but lose a bit of health.\n"
		   			+ "The amount of health the attacker will lose is equal to the defenders health divided by 2.\n"
		   			+ "If the attacking monster has less health, the attacker will die but remove the health of the defender.\n"
		   			+ "The amount of health the defender loses is equal to the attacker.\n"
		   			+ "If the attacker and defender have the same health, then they both die.\n\n"
		   			+ "These are the main rules you have to know, it is a bit hard to understand at first but practicing will help.\n"
		   			+ "Good luck and have fun!\n\n");
	   }
	   
	   //gets name information
	   System.out.println("What is player 1's name?");
	   officialName1 = in.next();
	   name1 = officialName1.toUpperCase().substring(0, 1);
	   System.out.println();
	   System.out.println("What is player 2's name? ");
	   officialName2 = in.next();
	   name2 = officialName2.toUpperCase().substring(0, 1);
	   System.out.println();

       //sets up board
       System.out.print("How big would you like the dungeon to be (note: it has to be bigger than 3x3 and smaller than 8x8)?");
       System.out.println();
       System.out.print("Height: ");
       height = in.nextInt();
       System.out.println();
       System.out.print("Width: ");
       width = in.nextInt();
       System.out.println();
	   myDungeon = new Dungeon(height, width);
       int numOfMonsters = ((height * width)/2);
       System.out.println("Your dungeon will be " + height + "x" + width + " long, and each player will have " + numOfMonsters + " monsters!\n");
       System.out.println("Here is the randomly shuffled dungeon\n");
       
       //sets up the monsters on the board
	   myDungeon.list(name1, name2); 
	   myDungeon.setBoard();
	   System.out.println(myDungeon.printInfo());
	   	
	   //randomly chooses who goes first
	   int turn = (int)(Math.random() * 2 + 1);
	   if(turn == 1) {
		   System.out.println(officialName1 + " has been randomly chosen to go first! Choose a direction to move your monsters by using WASD");
	   }
	   else if(turn == 2) {
		   System.out.println(officialName2 + " has been randomly chosen to go first! Choose a direction to move your monsters by using WASD");
	   }
	   
	   boolean hasPrinted = false;
	   
	   //main part of the game loop
	   do {
		   
		   //uses finished method to check if the game is over. Breaks loop and ends game if it is
		   if(myDungeon.finished(name1, name2) == 1 || myDungeon.finished(name1, name2) == 2 || myDungeon.finished(name1, name2) == 3) {
			   break;
		   }
		   
		   //shows whose turn it is
		   if(hasPrinted) {
			   if(turn == 1) {
				   System.out.println("It is " + officialName1 + "'s turn to go! Choose a direction to move your monsters by using WASD and then pressing enter");
			   }
			   else if(turn == 2) {
				   System.out.println("It is " + officialName2 + "'s turn to go! Choose a direction to move your monsters by using WASD and then pressing enter");
			   }
	   	   }
		   hasPrinted = true;
		   
		   //takes in the players WASD move
		   String cmd = in.next().substring(0, 1).toLowerCase();
		   myDungeon.shift(cmd, turn, name1, name2);
		   System.out.println(myDungeon.printInfo());
		   
		   //switches turn
		   if(turn == 1) {
			   turn = 2;
		   }
		   else if(turn == 2) {
			   turn = 1;
		   }
	   }while(true);
	   
	   //displays who the winner is
	   int winner = myDungeon.finished(name1, name2);
	   if(winner == 1) {
		   System.out.println("\n" + officialName2 + " is the winner! Congratulations!");
	   }
	   if(winner == 2) {
		   System.out.println("\n" + officialName1 + " is the winner! Congratulations!");
	   }
	   if(winner == 3) {
		   System.out.println("It was a tie!");
	   }
	   
	}  //end of the main method
}

