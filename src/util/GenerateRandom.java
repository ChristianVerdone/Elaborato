package util;

import java.util.Random;

public class GenerateRandom {
	public String GenerateRandom(){
		Random rand = new Random(); //instance of random class
		int upperbound =1000;

		String int_random = "" + rand.nextInt(upperbound); 

		return int_random;
	}
}