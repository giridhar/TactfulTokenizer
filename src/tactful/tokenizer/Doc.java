package tactful.tokenizer;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

//A document represents the input text. It holds a list of fragments generated
//from the text.
public class Doc {

	public List<Frag> frags = new ArrayList<Frag>();

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

	public Doc(String text) {
		String line = null;
		BufferedReader br;
		try {
			// Creating Stream with BufferedReader to given Text..
			br = new BufferedReader(new StringReader(text));
			while ((line = br.readLine()) != null) {			
				//line.split("(.*?[.!?](?:[\"')\\]}]|(?:<.*>))*[\\s])");
				String[] res = line.split("(?<=[.!?])\\s+");
				if(res.length > 0 ){
					for (String string : res) {
						string = string.replace("\\n","");
						string = string.replace("\\r","");
						string = string.replaceAll("\\s+"," ");
						
						if (string.trim().equals("") || string.trim().equals(" "))
							continue;

						Frag frag = new Frag(string);
						if (!frags.isEmpty() && frag.getCleaned().length > 0) {
							frags.get(frags.size() - 1).setNext(
									frag.getCleaned()[0]);
						}
						this.frags.add(frag);
					}
				}
			}
		}catch (Exception e) {
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

		for (Frag frag : frags) {
			sent.add(frag.getOrig());

			if (frag.getPred() > thresh) {
				if (frag.getOrig() == null)
					break;
				String addall = "";
				for (String add : sent) {
					addall = addall + add + " ";
				}
				addall = addall.trim();
				sents.add(addall);
				sent = new ArrayList<String>();
			}
		}

		// (String[])sents.toArray(); this is not working 
		//so here creating string array from arraylist...
		String[] sentences;
		if (sents.size() > 0) {
			sentences = new String[sents.size()];
			for (int i = 0; i < sents.size(); i++) {
				sentences[i] = sents.get(i);
			}
		}

		else {
			sentences = new String[sent.size()];
			for (int i = 0; i < sent.size(); i++) {
				sentences[i] = sent.get(i);
			}
		}
		return sentences;
	}

}
