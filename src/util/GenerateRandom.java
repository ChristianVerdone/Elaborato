package util;

import java.util.Random;

public class GenerateRandom {
	public String GenerateRandom(){
		Random rand = new Random(); //instance of random class
		int upperbound =1000;

		String int_random = "" + rand.nextInt(upperbound); 

		return int_random;
	}

	public String generateRandomWithInitials(String initials) {
		Random rand = new Random(); //instance of random class
		
		int n = rand.nextInt(1000);
		if(n < 10) {
			initials += "00"; 
		}
		else if (n < 100) {
			initials += "0";
		}
		
		return initials + n;		
	}
}