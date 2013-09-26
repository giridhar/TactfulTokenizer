package models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class NonAbbrsModel {
	
	public static Map<String, Integer> initialize() throws IOException{
		int prob = 0;
		String data = "";
		Map<String, Integer> non_abbrs = new HashMap<String, Integer>();
		InputStream inputStream  =  NonAbbrsModel.class.getResourceAsStream("/data/non_abbrs.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
		while((data=br.readLine()) != null ){
			if(data != null){
				String[] nawords = data.split(",");
				for (int i = 0; i < nawords.length; i++)
				{
				  String[] wordArray = nawords[i].split("\\|\\|");
				  if (wordArray.length > 1)
					non_abbrs.put(wordArray[0], Integer.parseInt(wordArray[1]));

				}
			}
		}
		br.close();
	  return non_abbrs;				
	}
}
