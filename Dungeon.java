package DungeonDominator;

public class Dungeon{

	private Monster[][] monsters;
	public static int gridWidth;
	public static int gridHeight;
	private Monster[] monsterList1;
	private Monster[] monsterList2;

	Monster monster = new Monster();

	//Dungeon custom constructor
	public Dungeon(int width, int height){
		gridWidth = width;
		gridHeight = height;
		monsters = new Monster[gridHeight][gridWidth];
		for(int i = 0; i < gridHeight; i++){

			for(int j = 0; j < gridWidth; j++){

				monsters[i][j] = new Monster();
			}
		}
	}
	//creates list of monsters that will be used
	public void list(String play1, String play2){
		int max = (gridHeight * gridWidth)/2;

		monsterList1 = new Monster[max];
		monsterList2 = new Monster[max];

		for(int i = 0; i < max; i++){
			monsterList1[i] = new Monster(play1, i +1);
			monsterList2[i] = new Monster(play2, i +1);
		}
	}

	//sets the dungeon with randomly placed monsters for both players
	public void setBoard(){

		for(int i = 0; i < (gridHeight * gridWidth)/2; i++){

			int randHeight1 = (int)(Math.random() * gridHeight);
			int randWidth1 = (int)(Math.random() * gridWidth);

			if(monsters[randHeight1][randWidth1].getLevel() == -1){

				monsters[randHeight1][randWidth1] = monsterList1[i];
			}
			else{
				i--;
			}
		}

		for(int i = 0; i < (gridHeight * gridWidth)/2; i++){

			int randHeight2 = (int)(Math.random() * gridHeight);
			int randWidth2 = (int)(Math.random() * gridWidth);

			if(monsters[randHeight2][randWidth2].getLevel() == -1){

				monsters[randHeight2][randWidth2] = monsterList2[i];
			}
			else{
				i--;
			}
		}
	}

	//set monsters in any location given it is not taken up
	public void setMonster(int x, int y, Monster m){

		if(x < gridWidth && y < gridHeight){

			if(monsters[y][x].getLevel() == -1){

				monsters[y][x] = m;
			}
		}
	}
	
	//method that swaps monsters in the array list
	private void swap(int x1, int y1, int x2, int y2){

		Monster temp = monsters[x1][y1];
		monsters[x1][y1] = monsters[x2][y2];
		monsters[x2][y2] = temp;

	}
	
	//method that shuffles the board
	public void shuffle(){

		for(int i = 0; i < 100; i++){

			int num1 = (int)(Math.random() * gridHeight);
			int num2 = (int)(Math.random() * gridWidth);

			int num3 = (int)(Math.random() * gridHeight);
			int num4 = (int)(Math.random() * gridWidth);

			swap(num1, num2, num3, num4);
		}
	}
	
	//method that checks if the game is finished
	public int finished(String name1, String name2) {
		
		boolean player1 = true;
		boolean player2 = true;
		int result = 0;
		
		//scans though board and sees if there are still player pieces alive
		for(int i = 0; i < gridHeight; i++) {
			   
			   for(int j = 0; j < gridWidth; j++) {
				   
				   if(name1.equals(monsters[i][j].getName())) {
					   player1 = false;
				   }
				   if(name2.equals(monsters[i][j].getName())) {
					   player2 = false;
				   }
			   }
		   }
		if(player1 && player2) {
			result = 3;
		}
		else if(player2) {
			result = 2;
		}
		else if(player1) {
			result = 1;
		}
		
		return result;
	}
	
	//method that moves monsters around
	public void shift(String command, int turn, String name1, String name2){

		switch(command){

			case "w":
				
				Monster[] tempArrW = new Monster[monsters[0].length];

				for(int i = 0; i < gridWidth; i++){
					
					if(turn == 1 && name1.equals(monsters[0][i].getName())) {
						tempArrW[i] = monsters[0][i];
						monsters[0][i] = new Monster();
					}
					
					else if(turn == 2 && name2.equals(monsters[0][i].getName())) {
						tempArrW[i] = monsters[0][i];
						monsters[0][i] = new Monster();
					}
					
					else {
						tempArrW[i] = new Monster();
					}
				}

				for(int i = 0; i < gridHeight - 1; i++){

					for(int j = 0; j < monsters[i].length; j++){

						if(turn == 1 && name1.equals(monsters[i + 1][j].getName())) {
							
							if(monsters[i][j].getLevel() == -1) {
								
								monsters[i][j] = monsters[i + 1][j];
								monsters[i + 1][j] = new Monster();
							}
							
							else if(monsters[i][j].getName().equals(monsters[i + 1][j].getName())) {
								
								monsters[i][j] = monsters[i + 1][j];
								monsters[i + 1][j] = new Monster();
							}
							
							else if(monsters[i][j].getLevel() < monsters[i + 1][j].getLevel()) {
								
								monsters[i + 1][j].setLevel(monsters[i + 1][j].getLevel() - monsters[i][j].getLevel()/2);
								monsters[i][j] = monsters[i + 1][j];
								monsters[i + 1][j] = new Monster();
							}
							
							else if(monsters[i][j].getLevel() == monsters[i + 1][j].getLevel()) {
								
								monsters[i][j] = new Monster();
								monsters[i + 1][j] = new Monster();
							}
							
							else if(monsters[i][j].getLevel() > monsters[i + 1][j].getLevel()) {
								
								monsters[i][j].setLevel(monsters[i][j].getLevel() - monsters[i + 1][j].getLevel());
								monsters[i + 1][j] = new Monster();
							}
						}
						
						else if(turn == 2 && name2.equals(monsters[i + 1][j].getName())) {
							
							if(monsters[i][j].getLevel() == -1) {
								
								monsters[i][j] = monsters[i + 1][j];
								monsters[i + 1][j] = new Monster();
							}
							
							else if(monsters[i][j].getName().equals(monsters[i + 1][j].getName())) {
								
								monsters[i][j] = monsters[i + 1][j];
								monsters[i + 1][j] = new Monster();
							}
							
							else if(monsters[i][j].getLevel() < monsters[i + 1][j].getLevel()) {
								
								monsters[i + 1][j].setLevel(monsters[i + 1][j].getLevel() - monsters[i][j].getLevel()/2);
								monsters[i][j] = monsters[i + 1][j];
								monsters[i + 1][j] = new Monster();
							}
							
							else if(monsters[i][j].getLevel() == monsters[i + 1][j].getLevel()) {
								
								monsters[i][j] = new Monster();
								monsters[i + 1][j] = new Monster();
							}
							
							else if(monsters[i][j].getLevel() > monsters[i + 1][j].getLevel()) {
								
								monsters[i][j].setLevel(monsters[i][j].getLevel() - monsters[i + 1][j].getLevel());
								monsters[i + 1][j] = new Monster();
							}
						}
					}
				}

				for(int i = 0; i < gridWidth; i++){

					if(turn == 1 && name1.equals(tempArrW[i].getName())){
						
						if(monsters[gridHeight - 1][i].getLevel() == -1) {
							
							monsters[gridHeight - 1][i] = tempArrW[i];
							//monsters[gridHeight - 1][i] = new Monster();
						}
						
						else if(monsters[gridHeight - 1][i].getName().equals(tempArrW[i].getName())) {
							
							monsters[gridHeight - 1][i] = tempArrW[i];
							//monsters[gridHeight - 1][i] = new Monster();
						}
						
						else if(monsters[gridHeight - 1][i].getLevel() < tempArrW[i].getLevel()) {
							
							tempArrW[i].setLevel(tempArrW[i].getLevel() - monsters[gridHeight - 1][i].getLevel()/2);
							monsters[gridHeight - 1][i] = tempArrW[i];
							//monsters[gridHeight - 1][i] = new Monster();
						}
						
						else if(monsters[gridHeight - 1][i].getLevel() == tempArrW[i].getLevel()) {
							
							monsters[gridHeight - 1][i] = new Monster();
							//monsters[gridHeight - 1][i] = new Monster();
						}
						
						else if(monsters[gridHeight - 1][i].getLevel() > tempArrW[i].getLevel()) {
							
							monsters[gridHeight - 1][i].setLevel(monsters[gridHeight - 1][i].getLevel() - tempArrW[i].getLevel());
							//monsters[gridHeight - 1][i] = new Monster();
						}
					}
					
					else if(turn == 2 && name2.equals(tempArrW[i].getName())) {
						
						if(monsters[gridHeight - 1][i].getLevel() == -1) {
							
							monsters[gridHeight - 1][i] = tempArrW[i];
							//monsters[gridHeight - 1][i] = new Monster();
						}
						
						else if(monsters[gridHeight - 1][i].getName().equals(tempArrW[i].getName())) {
							
							monsters[gridHeight - 1][i] = tempArrW[i];
							//monsters[gridHeight - 1][i] = new Monster();
						}
						
						else if(monsters[gridHeight - 1][i].getLevel() < tempArrW[i].getLevel()) {
							
							tempArrW[i].setLevel(tempArrW[i].getLevel() - monsters[gridHeight - 1][i].getLevel()/2);
							monsters[gridHeight - 1][i] = tempArrW[i];
							//monsters[gridHeight - 1][i] = new Monster();
						}
						
						else if(monsters[gridHeight - 1][i].getLevel() == tempArrW[i].getLevel()) {
							
							monsters[gridHeight - 1][i] = new Monster();
							//monsters[gridHeight - 1][i] = new Monster();
						}
						
						else if(monsters[gridHeight - 1][i].getLevel() > tempArrW[i].getLevel()) {
							
							monsters[gridHeight - 1][i].setLevel(monsters[gridHeight - 1][i].getLevel() - tempArrW[i].getLevel());
							//monsters[gridHeight - 1][i] = new Monster();
						}
					}
				}

				break;

			case "a":

				Monster[] tempArrA = new Monster[gridHeight];
				
				for(int i = 0; i < gridHeight; i++){
					
					if(turn == 1 && name1.equals(monsters[i][0].getName())) {
						tempArrA[i] = monsters[i][0];
						monsters[i][0] = new Monster();
					}
					
					else if(turn == 2 && name2.equals(monsters[i][0].getName())) {
						tempArrA[i] = monsters[i][0];
						monsters[i][0] = new Monster();
					}
					
					else {
						tempArrA[i] = new Monster();
					}
				}

				for(int i = 0; i < gridWidth - 1; i++){

					for(int j = 0; j < gridHeight; j++){

						if(turn == 1 && name1.equals(monsters[j][i + 1].getName())) {
							
							if(monsters[j][i].getLevel() == -1) {
								
								monsters[j][i] = monsters[j][i + 1];
								monsters[j][i + 1] = new Monster();
							}
							
							else if(monsters[j][i].getName().equals(monsters[j][i + 1].getName())) {
								
								monsters[j][i] = monsters[j][i + 1];
								monsters[j][i + 1] = new Monster();
							}
							
							else if(monsters[j][i].getLevel() < monsters[j][i + 1].getLevel()) {
								
								monsters[j][i + 1].setLevel(monsters[j][i + 1].getLevel() - monsters[j][i].getLevel()/2);
								monsters[j][i] = monsters[j][i + 1];
								monsters[j][i + 1] = new Monster();
							}
							
							else if(monsters[j][i].getLevel() == monsters[j][i + 1].getLevel()) {
								
								monsters[j][i] = new Monster();
								monsters[j][i + 1] = new Monster();
							}
							
							else if(monsters[j][i].getLevel() > monsters[j][i + 1].getLevel()) {
								
								monsters[j][i].setLevel(monsters[j][i].getLevel() - monsters[j][i + 1].getLevel());
								monsters[j][i + 1] = new Monster();
							}
						}
						
						else if(turn == 2 && name2.equals(monsters[j][i + 1].getName())) {
							
							if(monsters[j][i].getLevel() == -1) {
								
								monsters[j][i] = monsters[j][i + 1];
								monsters[j][i + 1] = new Monster();
							}
							
							else if(monsters[j][i].getName().equals(monsters[j][i + 1].getName())) {
								
								monsters[j][i] = monsters[j][i + 1];
								monsters[j][i + 1] = new Monster();
							}
							
							else if(monsters[j][i].getLevel() < monsters[j][i + 1].getLevel()) {
								
								monsters[j][i + 1].setLevel(monsters[j][i + 1].getLevel() - monsters[j][i].getLevel()/2);
								monsters[j][i] = monsters[j][i + 1];
								monsters[j][i + 1] = new Monster();
							}
							
							else if(monsters[j][i].getLevel() == monsters[j][i + 1].getLevel()) {
								
								monsters[j][i] = new Monster();
								monsters[j][i + 1] = new Monster();
							}
							
							else if(monsters[j][i].getLevel() > monsters[j][i + 1].getLevel()) {
								
								monsters[j][i].setLevel(monsters[j][i].getLevel() - monsters[j][i + 1].getLevel());
								monsters[j][i + 1] = new Monster();
							}
						}
					}
				}

				for(int i = 0; i < gridHeight; i++){

					if(turn == 1 && name1.equals(tempArrA[i].getName())){
						
						if(monsters[i][gridWidth - 1].getLevel() == -1) {
							
							monsters[i][gridWidth - 1] = tempArrA[i];
							//monsters[gridHeight - 1][i] = new Monster();
						}
						
						else if(monsters[i][gridWidth - 1].getName().equals(tempArrA[i].getName())) {
							
							monsters[i][gridWidth - 1] = tempArrA[i];
							//monsters[gridHeight - 1][i] = new Monster();
						}
						
						else if(monsters[i][gridWidth - 1].getLevel() < tempArrA[i].getLevel()) {
							
							tempArrA[i].setLevel(tempArrA[i].getLevel() - monsters[i][gridWidth - 1].getLevel()/2);
							monsters[i][gridWidth - 1] = tempArrA[i];
							//monsters[gridHeight - 1][i] = new Monster();
						}
						
						else if(monsters[i][gridWidth - 1].getLevel() == tempArrA[i].getLevel()) {
							
							monsters[i][gridWidth - 1] = new Monster();
							//monsters[gridHeight - 1][i] = new Monster();
						}
						
						else if(monsters[i][gridWidth - 1].getLevel() > tempArrA[i].getLevel()) {
							
							monsters[i][gridWidth - 1].setLevel(monsters[i][gridWidth - 1].getLevel() - tempArrA[i].getLevel());
							//monsters[gridHeight - 1][i] = new Monster();
						}
					}
					
					else if(turn == 2 && name2.equals(tempArrA[i].getName())) {
						
						if(monsters[i][gridWidth - 1].getLevel() == -1) {
							
							monsters[i][gridWidth - 1] = tempArrA[i];
							//monsters[gridHeight - 1][i] = new Monster();
						}
						
						else if(monsters[i][gridWidth - 1].getName().equals(tempArrA[i].getName())) {
							
							monsters[i][gridWidth - 1] = tempArrA[i];
							//monsters[gridHeight - 1][i] = new Monster();
						}
						
						else if(monsters[i][gridWidth - 1].getLevel() < tempArrA[i].getLevel()) {
							
							tempArrA[i].setLevel(tempArrA[i].getLevel() - monsters[i][gridWidth - 1].getLevel()/2);
							monsters[i][gridWidth - 1] = tempArrA[i];
							//monsters[gridHeight - 1][i] = new Monster();
						}
						
						else if(monsters[i][gridWidth - 1].getLevel() == tempArrA[i].getLevel()) {
							
							monsters[i][gridWidth - 1] = new Monster();
							//monsters[gridHeight - 1][i] = new Monster();
						}
						
						else if(monsters[i][gridWidth - 1].getLevel() > tempArrA[i].getLevel()) {
							
							monsters[i][gridWidth - 1].setLevel(monsters[i][gridWidth - 1].getLevel() - tempArrA[i].getLevel());
							//monsters[gridHeight - 1][i] = new Monster();
						}
					}
				}

			break;

			case "s":
				
				Monster[] tempArrS = new Monster[monsters[gridHeight - 1].length];
				
				for(int i = 0; i < gridWidth; i++){
					
					if(turn == 1 && name1.equals(monsters[gridHeight - 1][i].getName())) {
						tempArrS[i] = monsters[gridHeight - 1][i];
						monsters[gridHeight - 1][i] = new Monster();
					}
					
					else if(turn == 2 && name2.equals(monsters[gridHeight - 1][i].getName())) {
						tempArrS[i] = monsters[gridHeight - 1][i];
						monsters[gridHeight - 1][i] = new Monster();
					}
					
					else {
						tempArrS[i] = new Monster();
					}
				}

				for(int i = gridHeight - 1; i > 0; i--){

					for(int j = monsters[i].length - 1; j >= 0; j--){

						if(turn == 1 && name1.equals(monsters[i - 1][j].getName())) {
							
							if(monsters[i][j].getLevel() == -1) {
								
								monsters[i][j] = monsters[i - 1][j];
								monsters[i - 1][j] = new Monster();
							}
							
							else if(monsters[i][j].getName().equals(monsters[i - 1][j].getName())) {
								
								monsters[i][j] = monsters[i - 1][j];
								monsters[i - 1][j] = new Monster();
							}
							
							else if(monsters[i][j].getLevel() < monsters[i - 1][j].getLevel()) {
								
								monsters[i - 1][j].setLevel(monsters[i - 1][j].getLevel() - monsters[i][j].getLevel()/2);
								monsters[i][j] = monsters[i - 1][j];
								monsters[i - 1][j] = new Monster();
							}
							
							else if(monsters[i][j].getLevel() == monsters[i - 1][j].getLevel()) {
								
								monsters[i][j] = new Monster();
								monsters[i - 1][j] = new Monster();
							}
							
							else if(monsters[i][j].getLevel() > monsters[i - 1][j].getLevel()) {
								
								monsters[i][j].setLevel(monsters[i][j].getLevel() - monsters[i - 1][j].getLevel());
								monsters[i - 1][j] = new Monster();
							}
						}
						
						else if(turn == 2 && name2.equals(monsters[i - 1][j].getName())) {
							
							if(monsters[i][j].getLevel() == -1) {
								
								monsters[i][j] = monsters[i - 1][j];
								monsters[i - 1][j] = new Monster();
							}
							
							else if(monsters[i][j].getName().equals(monsters[i - 1][j].getName())) {
								
								monsters[i][j] = monsters[i - 1][j];
								monsters[i - 1][j] = new Monster();
							}
							
							else if(monsters[i][j].getLevel() < monsters[i - 1][j].getLevel()) {
								
								monsters[i - 1][j].setLevel(monsters[i - 1][j].getLevel() - monsters[i][j].getLevel()/2);
								monsters[i][j] = monsters[i - 1][j];
								monsters[i - 1][j] = new Monster();
							}
							
							else if(monsters[i][j].getLevel() == monsters[i - 1][j].getLevel()) {
								
								monsters[i][j] = new Monster();
								monsters[i - 1][j] = new Monster();
							}
							
							else if(monsters[i][j].getLevel() > monsters[i - 1][j].getLevel()) {
								
								monsters[i][j].setLevel(monsters[i][j].getLevel() - monsters[i - 1][j].getLevel());
								monsters[i - 1][j] = new Monster();
							}
						}
					}
				}

				for(int i = 0; i < gridWidth; i++){

					if(turn == 1 && name1.equals(tempArrS[i].getName())){
						
						if(monsters[0][i].getLevel() == -1) {
							
							monsters[0][i] = tempArrS[i];
							//monsters[gridHeight - 1][i] = new Monster();
						}
						
						else if(monsters[0][i].getName().equals(tempArrS[i].getName())) {
							
							monsters[0][i] = tempArrS[i];
							//monsters[gridHeight - 1][i] = new Monster();
						}
						
						else if(monsters[0][i].getLevel() < tempArrS[i].getLevel()) {
							
							tempArrS[i].setLevel(tempArrS[i].getLevel() - monsters[0][i].getLevel()/2);
							monsters[0][i] = tempArrS[i];
							//monsters[gridHeight - 1][i] = new Monster();
						}
						
						else if(monsters[0][i].getLevel() == tempArrS[i].getLevel()) {
							
							monsters[0][i] = new Monster();
							//monsters[gridHeight - 1][i] = new Monster();
						}
						
						else if(monsters[0][i].getLevel() > tempArrS[i].getLevel()) {
							
							monsters[0][i].setLevel(monsters[0][i].getLevel() - tempArrS[i].getLevel());
							//monsters[gridHeight - 1][i] = new Monster();
						}
					}
					
					else if(turn == 2 && name2.equals(tempArrS[i].getName())) {
						
						if(monsters[0][i].getLevel() == -1) {
							
							monsters[0][i] = tempArrS[i];
							//monsters[gridHeight - 1][i] = new Monster();
						}
						
						else if(monsters[0][i].getName().equals(tempArrS[i].getName())) {
							
							monsters[0][i] = tempArrS[i];
							//monsters[gridHeight - 1][i] = new Monster();
						}
						
						else if(monsters[0][i].getLevel() < tempArrS[i].getLevel()) {
							
							tempArrS[i].setLevel(tempArrS[i].getLevel() - monsters[0][i].getLevel()/2);
							monsters[0][i] = tempArrS[i];
							//monsters[gridHeight - 1][i] = new Monster();
						}
						
						else if(monsters[0][i].getLevel() == tempArrS[i].getLevel()) {
							
							monsters[0][i] = new Monster();
							//monsters[gridHeight - 1][i] = new Monster();
						}
						
						else if(monsters[0][i].getLevel() > tempArrS[i].getLevel()) {
							
							monsters[0][i].setLevel(monsters[0][i].getLevel() - tempArrS[i].getLevel());
							//monsters[gridHeight - 1][i] = new Monster();
						}
					}
				}

			break;

			case "d":

				Monster[] tempArrD = new Monster[gridHeight];
				
				for(int i = 0; i < gridHeight; i++){
					
					if(turn == 1 && name1.equals(monsters[i][gridWidth - 1].getName())) {
						tempArrD[i] = monsters[i][gridWidth - 1];
						monsters[i][gridWidth - 1] = new Monster();
					}
					
					else if(turn == 2 && name2.equals(monsters[i][gridWidth - 1].getName())) {
						tempArrD[i] = monsters[i][gridWidth - 1];
						monsters[i][gridWidth - 1] = new Monster();
					}
					
					else {
						tempArrD[i] = new Monster();
					}
				}

				for(int i = gridWidth - 1; i > 0; i--){

					for(int j = gridHeight - 1; j >= 0; j--){

						if(turn == 1 && name1.equals(monsters[j][i - 1].getName())) {
							
							if(monsters[j][i].getLevel() == -1) {
								
								monsters[j][i] = monsters[j][i - 1];
								monsters[j][i - 1] = new Monster();
							}
							
							else if(monsters[j][i].getName().equals(monsters[j][i - 1].getName())) {
								
								monsters[j][i] = monsters[j][i - 1];
								monsters[j][i - 1] = new Monster();
							}
							
							else if(monsters[j][i].getLevel() < monsters[j][i - 1].getLevel()) {
								
								monsters[j][i - 1].setLevel(monsters[j][i - 1].getLevel() - monsters[j][i].getLevel()/2);
								monsters[j][i] = monsters[j][i - 1];
								monsters[j][i - 1] = new Monster();
							}
							
							else if(monsters[j][i].getLevel() == monsters[j][i - 1].getLevel()) {
								
								monsters[j][i] = new Monster();
								monsters[j][i - 1] = new Monster();
							}
							
							else if(monsters[j][i].getLevel() > monsters[j][i - 1].getLevel()) {
								
								monsters[j][i].setLevel(monsters[j][i].getLevel() - monsters[j][i - 1].getLevel());
								monsters[j][i - 1] = new Monster();
							}
						}
						
						else if(turn == 2 && name2.equals(monsters[j][i - 1].getName())) {
							
							if(monsters[j][i].getLevel() == -1) {
								
								monsters[j][i] = monsters[j][i - 1];
								monsters[j][i - 1] = new Monster();
							}
							
							else if(monsters[j][i].getName().equals(monsters[j][i - 1].getName())) {
								
								monsters[j][i] = monsters[j][i - 1];
								monsters[j][i - 1] = new Monster();
							}
							
							else if(monsters[j][i].getLevel() < monsters[j][i - 1].getLevel()) {
								
								monsters[j][i - 1].setLevel(monsters[j][i - 1].getLevel() - monsters[j][i].getLevel()/2);
								monsters[j][i] = monsters[j][i - 1];
								monsters[j][i - 1] = new Monster();
							}
							
							else if(monsters[j][i].getLevel() == monsters[j][i - 1].getLevel()) {
								
								monsters[j][i] = new Monster();
								monsters[j][i - 1] = new Monster();
							}
							
							else if(monsters[j][i].getLevel() > monsters[j][i - 1].getLevel()) {
								
								monsters[j][i].setLevel(monsters[j][i].getLevel() - monsters[j][i - 1].getLevel());
								monsters[j][i - 1] = new Monster();
							}
						}
					}
				}

				for(int i = 0; i < gridHeight; i++){

					if(turn == 1 && name1.equals(tempArrD[i].getName())){
						
						if(monsters[i][0].getLevel() == -1) {
							
							monsters[i][0] = tempArrD[i];
							//monsters[gridHeight - 1][i] = new Monster();
						}
						
						else if(monsters[i][0].getName().equals(tempArrD[i].getName())) {
							
							monsters[i][0] = tempArrD[i];
							//monsters[gridHeight - 1][i] = new Monster();
						}
						
						else if(monsters[i][0].getLevel() < tempArrD[i].getLevel()) {
							
							tempArrD[i].setLevel(tempArrD[i].getLevel() - monsters[i][0].getLevel()/2);
							monsters[i][0] = tempArrD[i];
							//monsters[gridHeight - 1][i] = new Monster();
						}
						
						else if(monsters[i][0].getLevel() == tempArrD[i].getLevel()) {
							
							monsters[i][0] = new Monster();
							//monsters[gridHeight - 1][i] = new Monster();
						}
						
						else if(monsters[i][0].getLevel() > tempArrD[i].getLevel()) {
							
							monsters[i][0].setLevel(monsters[i][0].getLevel() - tempArrD[i].getLevel());
							//monsters[gridHeight - 1][i] = new Monster();
						}
					}
					
					else if(turn == 2 && name2.equals(tempArrD[i].getName())) {
						
						if(monsters[i][0].getLevel() == -1) {
							
							monsters[i][0] = tempArrD[i];
							//monsters[gridHeight - 1][i] = new Monster();
						}
						
						else if(monsters[i][0].getName().equals(tempArrD[i].getName())) {
							
							monsters[i][0] = tempArrD[i];
							//monsters[gridHeight - 1][i] = new Monster();
						}
						
						else if(monsters[i][0].getLevel() < tempArrD[i].getLevel()) {
							
							tempArrD[i].setLevel(tempArrD[i].getLevel() - monsters[i][0].getLevel()/2);
							monsters[i][0] = tempArrD[i];
							//monsters[gridHeight - 1][i] = new Monster();
						}
						
						else if(monsters[i][0].getLevel() == tempArrD[i].getLevel()) {
							
							monsters[i][0] = new Monster();
							//monsters[gridHeight - 1][i] = new Monster();
						}
						
						else if(monsters[i][0].getLevel() > tempArrD[i].getLevel()) {
							
							monsters[i][0].setLevel(monsters[i][0].getLevel() - tempArrD[i].getLevel());
							//monsters[gridHeight - 1][i] = new Monster();
						}
					}
				}

			break;
			
			default: System.out.println("Remember, use WASD to move your character");
				
		}

	}

	//puts the board into a string to display it
	public String printInfo(){
		String str = "";

		if(monsters != null){

			for(int i = 0; i < monsters.length; i++){
				for(int j = 0; j < monsters.length; j++){

					if(j % gridWidth == 0){
						str += "\n\n";
					}
					str += "\t" + monsters[i][j].monsterInfo();
				}
			}
		}
		else System.out.println("The map is empty!");

		return str;
	}

}
