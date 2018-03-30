package DungeonDominator;

public class Monster{

	private String name; 
	private int level;   

	//Default constructor
	public Monster(){
		name = "";		
		level = -1;		
	}
	
	//Custom constructor
	public Monster (String _name, int _level){
		name = _name;
		level = _level;
	}

	//Setter and Getter methods
	public String getName(){
		return name;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setName(String _name) {
		name=_name;
	}
	
	public void setLevel(int _level) {
		level =_level;
	}

	// Checks whether the monster is equal to the other monster
	public boolean equals(Monster other){
		if(name.equals(other.getName()) && level == other.getLevel())
			return true;
		else
			return false;
	}
	// Returns the initial and level as a string data
	public String monsterInfo(){
		if(getLevel() == -1)
			return "*";
		else
			return getName().charAt(0) + "("+ getLevel() + ")";
	}
}
