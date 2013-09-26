package tactful.tokenizer;

import static org.junit.Assert.assertArrayEquals;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

public class ModelTest {


@Test
public void testTokenize_text() throws IOException{

	Model model = new Model();
	 Map<String,String[]> mockMessages = new HashMap<String,String[]> () {
	 {
		 
		 put("I am reg. in doing Sci. home work after coming from Sch.", new String[]{"I am reg. in doing Sci. home work after coming from Sch."} );			
		 
		 put("Please look at the prep. for meeting.", new String[]{"Please look at the prep. for meeting."} );
		 
		 put("Look at this App. of Telugu song.", new String[]{"Look at this App. of Telugu song."} );
		 
		 put("reQall, inc. is the name of the company.",new String[]{"reQall, inc. is the name of the company."});
		 
		 put("Watch the cricket match between in. and W. Ind.",new String[]{"Watch the cricket match between in. and W. Ind."});

         put("this is Adv. to you not to disturb." ,new String[]{"this is Adv. to you not to disturb."} );

         put("Mr. S.T.S. is the president of Dr. Happly Club",new String[]{"Mr. S.T.S. is the president of Dr. Happly Club"});

         put("N.Y. is the biggest city of U.S.A. and Auckland is the biggest city of N.Z.",new String[]{"N.Y. is the biggest city of U.S.A. and Auckland is the biggest city of N.Z."});
         
         put("``She was a great kid. She was always smiling a beautiful girl./'/' The flowers were placed in front of three white candles lit for Kennedy, his wife and sister-in-law.",new String[]{"``She was a great kid.","She was always smiling a beautiful girl./'/' The flowers were placed in front of three white candles lit for Kennedy, his wife and sister-in-law."});

         put("China and Taiwan have been ruled separately since a civil war 50 years ago.Both say they are part of the same country.China's comments came hours after Taiwan dismissed reports that China was preparing military action.",new String[]{"China and Taiwan have been ruled separately since a civil war 50 years ago.Both say they are part of the same country.China's comments came hours after Taiwan dismissed reports that China was preparing military action."});

         put("After getting Orlando Cabrera to hit a popup for the final out, Cone dropped to his knees, grabbed his head in disbelief and was mobbed by his joyous teammates.It was replay of the scene from last year when David Wells pitched the only other regular-season perfect game in Yankees' history.The Seattle Mariners on Sunday announced they signed high school catcher Ryan Christianson, their first-round pick and the 11th overall selection in last month's baseball draft.",new String[]{"After getting Orlando Cabrera to hit a popup for the final out, Cone dropped to his knees, grabbed his head in disbelief and was mobbed by his joyous teammates.It was replay of the scene from last year when David Wells pitched the only other regular-season perfect game in Yankees' history.The Seattle Mariners on Sunday announced they signed high school catcher Ryan Christianson, their first-round pick and the 11th overall selection in last month's baseball draft."});
		 
         put("Добавим немного русского текста, чтобы проверить, верно ли все работает.Еще одно предложение.",new String[]{"Добавим немного русского текста, чтобы проверить, верно ли все работает.Еще одно предложение."});
         
	  }
	};   

	for (Entry<String, String[]> entry : mockMessages.entrySet()) {
		 String message = entry.getKey();
		 assertArrayEquals(entry.getValue(),model.tokenize_text(message));
	 } 
}
}