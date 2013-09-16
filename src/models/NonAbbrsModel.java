package models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class NonAbbrsModel {
	
	public static int getProbability(String nonAbbrWord) throws IOException{
		int prob = 0;
		String data = "";
		InputStream inputStream  =  NonAbbrsModel.class.getResourceAsStream("/data/non_abbrs.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
		data = br.readLine();
		if(data != null){
			String[] nawords = data.split(",");
			for (int i = 0; i < nawords.length; i++)
			{
			  String[] wordArray = nawords[i].split("\\|\\|");
				if(nonAbbrWord.equals(wordArray[0]))
				{
					prob = 	Integer.parseInt(wordArray[1]);
					return prob;
				}
			}
		}		
	  return prob;				
	}

}
