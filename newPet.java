import java.util.Random;

/*
 * Created by Zachary Taylor
 * Date created: June 25, 2016
 * Description: The purpose of this class is to be an object of a tomagachi pet.
 * 	A pet should have the attributes of: Type of pet, Level modifier, HP, etc.
 */



public class newPet {
	private int petType, level, baseHP, HP, baseDMG, DMG, currentXP, requiredXP;
	private String species, name;
	private double XPMultiplier=2;

	public newPet(int petTypeInit, int levelInit, String nameInit){
		int minLevel = 0;//minimum level for a pet.
		int maxLevel = 46341;//Max level to initialize a pet to.
		Random rand = new Random();//Random object for random

		setName(nameInit);
		petType=petTypeInit;
		level= (levelInit>=minLevel) ? levelInit : minLevel;//Ternary operator that sets a minimum level of the pet.
		level= (levelInit<=maxLevel) ? levelInit : maxLevel;//Ternary operator to set a maximum level of the pet.
		currentXP=(int) (Math.pow(level,XPMultiplier));//Based on current level, set the current XP
		requiredXP=(int) (Math.pow(level+1,XPMultiplier));//Based on current level, get the required XP needed to level up.

		
		//Switch statement for determining initial values
		switch(petType){
		case 0: {
			species="Cat";
			baseHP=110+rand.nextInt(20);
			baseDMG=10+rand.nextInt(10);
		}
		break;

		case 1: {
			species="Dog";
			baseHP=85+rand.nextInt(20);
			baseDMG=12+rand.nextInt(10);
		}
		break;

		case 2: {
			species="Turtle";
			baseHP=145+rand.nextInt(20);
			baseDMG=7+rand.nextInt(10);
		}
		break;

		default: {
			species="Snail";
			int i;
			if((i = rand.nextInt(10000000))>9999900){//Give snails an incredibly low chance of being super snails
				System.out.println("THE MAGIC NUMBER: " + i);//let the world know
				baseHP=10+rand.nextInt(1000);//make the base HP much higher than normal
				baseDMG=10+rand.nextInt(1000);//make the base damage much higher than normal
				setName("SUPER " + getName());
			} else {
				baseHP=10+rand.nextInt(5);//normal low health and damage for a snail.
				baseDMG=10+rand.nextInt(5);
			}
		}
		}

		//Set health and damage based on initial level
		HP=(int)(baseHP+Math.round(baseHP*level*.1));
		DMG=(int)(baseDMG+Math.round(baseDMG*level*.1));

	}


	//----------------[Setters and getters]----------------------
	public String getSpecies() {
		return species;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHP() {
		return HP;
	}

	public int getDMG() {
		return DMG;
	}

	public int getLevel() {
		return level;
	}

	public int getCurrentXP() {
		return currentXP;
	}
	
	public int getRequiredXP() {
		return requiredXP;
	}

	//------------------[End of setters and getters]------------------

	public String addXP(int XP){
		String response = "";
		
		currentXP+=XP;//Adds XP to the current XP.
		if(checkPromotion()){
			response="Congradulations! " + name + " is now level " + level;//Let the user know that a pet has leveled up.
		}
		
		return response; //return the response.
	}

	private boolean checkPromotion(){
		boolean levelup = false;
		while (currentXP>=requiredXP) {//loop through leveling up until the pet can no longer level up.
			levelup = true;
			level++;//LEVEL UP!
			requiredXP=(int) (Math.pow(level+1,XPMultiplier));//update the new required XP, health, and damage of the pet.
			HP=(int)(baseHP+Math.round(baseHP*level*.1));
			DMG=(int)(baseDMG+Math.round(baseDMG*level*.1));
		}
		return levelup;
	}

}
