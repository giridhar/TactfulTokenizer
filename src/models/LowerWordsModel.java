package models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class LowerWordsModel {
	
	
	public static  Map<String, Integer> initialize() throws IOException{
		String data = "";
		InputStream inputStream  =  LowerWordsModel.class.getResourceAsStream("/data/lower_words.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
		Map<String, Integer> lower_words =  new HashMap<String, Integer>();
		
		while((data=br.readLine()) != null){
			if(data != null){	
				String[] lwords = data.split(",");				
				for (int i = 0; i < lwords.length; i++)
				{
				  String[] wordArray = lwords[i].split("\\|\\|");
				   if(wordArray.length > 1)
					lower_words.put(wordArray[0], Integer.parseInt(wordArray[1]));
				}			
			}
		}
		br.close();
	  return lower_words;				
	}
}
