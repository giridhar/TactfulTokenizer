package models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LowerWordsModel {
	
	public static int getProbability(String lowerWord) throws IOException{
		int prob = 0;
		String data = "";
		InputStream inputStream  =  LowerWordsModel.class.getResourceAsStream("/data/lower_words.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
		data = br.readLine();
		if(data != null){	
			String[] lwords = data.split(",");
			for (int i = 0; i < lwords.length; i++)
			{
			  String[] wordArray = lwords[i].split("\\|\\|");
				if(lowerWord.equals(wordArray[0]))
				{
					prob = 	Integer.parseInt(wordArray[1]);
					return prob;
				}
			}
		}		
	  return prob;				
	}

}
