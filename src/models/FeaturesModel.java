package models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FeaturesModel {
	
	public  double getProbability(String lowerWord) throws IOException{
		double prob = 0;
		String data = "";
		InputStream inputStream  =  getClass().getResourceAsStream("/data/features.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
		data = br.readLine();
		if(data != null){
			String[] lwords = data.split(",");
			for (int i = 0; i < lwords.length; i++)
			{
			  String[] wordArray = lwords[i].split("\\|\\|");
				if(lowerWord.equals(wordArray[0]))
				{
					prob = 	Double.parseDouble(wordArray[1]);
					return prob;
				}
			}
		}		
	  return prob;				
	}

}
