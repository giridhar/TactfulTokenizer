package tactful.tokenizer;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

//A document represents the input text. It holds a list of fragments generated
//from the text.
public class Doc {

	public List<Frag> frags = new ArrayList<Frag>() ;
	
	
	// # Receives a text, which is then broken into fragments.
	// # A fragment ends with a period, quesetion mark, or exclamation mark
	// followed
	// # possibly by right handed punctuation like quotation marks or closing
	// braces
	// # and trailing whitespace. Failing that, it'll accept something like
	// "I hate cheese\n"
	// # No, it doesn't have a period, but that's the end of paragraph.
	// #
	// # Input assumption: Paragraphs delimited by line breaks.
	//public Doc(){}
		
	public Doc(String text) {
		//can't initialize frags here with null it causes nullpointerexception...
		//frags = null;
		///BufferedReader in;
		try {
			/* Replacing FileReader with StringReader as 
			 *  Filereader takes path of file as String, and we have text(String which needs sentnce segmentation) */
		// BufferedReader is not working comment it for now, needs to  find a alternate of Bufferedreader
		//	in = new BufferedReader(new StringReader(text));
			//while (in.ready()) {
				String line = text;

				if (line != null) {
					
					//String[] res = line.split("(.*?[.!?](?:[\"')\\]}]|(?:<.*>))*[\\s])");
					String[] res = line.split("(?<=[.!?])\\s+");

					for (String string : res) {
						
					   if (string.trim() == "" || string.trim() == " ")
						   continue;
					   
					   Frag frag = new Frag(string);
					   
					   if ( ! frags.isEmpty() ) {
						   frags.get(frags.size()-1).setNext(frag.getCleaned()[0]);
					   }  					   
					   this.frags.add(frag);
		
					}
				}
			//} 	
		}catch(Exception e) {
			e.printStackTrace();
		} 
	}

	// # Segments the text. More precisely, it reassembles the fragments into
	// sentences.
	// # We call something a sentence whenever it is more likely to be a
	// sentence than not.
	public String[] segment() {
		ArrayList<String> sent = new ArrayList<String>();
		ArrayList<String> sents = new ArrayList<String>();
		float thresh = (float) 0.5;
		int i=0;
		String sentString;
		for (Frag frag : frags) {
			//sent.add(frag.getOrig());
			//if (frag.getPred() > thresh) {
			sentString = frag.getOrig();
			//Will Replace ture with condition once database is ready.. 
			if(true){
				if (frag.getOrig() == null)
					break;
				//sents.add(sent.toString());
				sents.add(sentString);
				sent = new ArrayList<String>();
			}
		}
		// (String[])sents.toArray(); this is not working so creating string array from arraylist...
		String[] sentences = new String[sents.size()];
		for (int i1 = 0; i1 < sents.size(); i1++) {
		    sentences[i1] = sents.get(i1);
		}
		return sentences;
	}

}
