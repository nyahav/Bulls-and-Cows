/**
 * In this class the logic of this game is implemented-how to game should run.
 * method i used:
 * generate_hidden_num-Create the wanted number the user need to guess
 * input_check-Checks if the user input match the predetermined rules
 * check_guess-Compare the input to the hidden number and return a matching result
 * @coded by Yahav Nir-Advanced Programming with Java 
 *
 */

public class LogicOfGame {
	//length of the hidden number constant
	protected final int LENGTH_OF_HIDDEN_NUMBER = 4;
	protected String num_to_guess = "";
	protected int attemps_to_guess;

	//empty default constructor 
	public LogicOfGame() {
		this.num_to_guess = generate_hidden_num();
		this.attemps_to_guess = 0;
	}

	//getter of the hidden number
	public String getNum() {												
		return num_to_guess;}

	//getter of the attempted guessed
	public int getGuesses() {												
		return attemps_to_guess;}
	//no need for setters

	//private method to generate hidden number for the user to guess
	private String generate_hidden_num() {
		String generated_num = "";
		int rand;
		//get random digit, add it to the 'generated_num' string 
		while(generated_num.length() < LENGTH_OF_HIDDEN_NUMBER) {
			rand = (int)(Math.random()*10);
			//check if the digit dosen't already exist
			if (generated_num.contains(""+rand) != true)	
				generated_num += rand;
		}
		return generated_num;
	}

	//return corresponding message according to the input, return blank string if there's no errors
	private String input_check(String guess) {
		String message = "";
		if(guess.length() != LENGTH_OF_HIDDEN_NUMBER)						
			return message = "Input must be 4 digits";
		//using regex to check if all input is digits
		if(guess.matches("\\d+") != true)					
			return message = "The input can't countain nothing but numbers";
		//complexity isn't n^2 because the length is of hidden num 4
		for(int i=0; i<=guess.length()-1;i++) {				
			for(int j=i+1; j<=guess.length()-1;j++) {
				if(guess.charAt(i) == guess.charAt(j))
					return message = "Input can't contain repeated digits"; 
			}
		}
		return message;
	}	

	//checks if the current guess hits bulls or cows,and if the game ends or continue 
	public String check_guess(String guess) {
		int bulls = 0;										
		int cows = 0;										
		String message ="";	
		//return a error message using input_check method,or return empty string 
		if(input_check(guess).length() != 0)				
			return input_check(guess);						

		this.attemps_to_guess += 1;								 
		//check bulls and cow by checking if equal in the same index or the hidden contains this digit
		for (int i=0; i<LENGTH_OF_HIDDEN_NUMBER; i++) {						 
			if(this.getNum().charAt(i) == guess.charAt(i))
				bulls +=1;
			else if( this.getNum().contains(""+guess.charAt(i)) == true)
				cows +=1;
		}
		if(bulls == LENGTH_OF_HIDDEN_NUMBER) {
			//save the result of the guess if user won
			message = "Good job! you won the guessing game. took you "+ this.getGuesses() +" guesses to guess it's "+this.getNum();
		}
		else {
			//save the result of the guess if couln't guess right
			message = "Your guess "+ guess +" has "+ bulls +" Bulls, and "+ cows +" Cows"; 
		}
		//return the proper message,if won or the game continue
		return message;
	}
}