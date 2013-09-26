package models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class FeaturesModel {
	
	public static Map<String,Double> initialize() throws IOException {
		String data = "";
		Map<String, Double> non_abbrs =  new HashMap<String, Double>();
		InputStream inputStream  = FeaturesModel.class.getResourceAsStream("/data/features.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
		
		while ((data = br.readLine()) != null) 
		{
			if(data != null){
				String[] lwords = data.split(",");
				for (int i = 0; i < lwords.length; i++)
				{
				  String[] wordArray = lwords[i].split("\\|\\|");
				  if(wordArray.length > 1)
					non_abbrs.put(wordArray[0], Double.parseDouble(wordArray[1]));
				}
			}
		}
		br.close();
	  return non_abbrs;				
	}
}
