package tactful.tokenizer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

//A document represents the input text. It holds a list of fragments generated
//from the text.
public class Doc {
	public ArrayList<Frag> frags;

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

		BufferedReader in;
		try {
			/* Replacing FileReader with StringReader as 
			 *  Filereader takes path of file as String, and we have text(String which needs sentence segmentation) */
			in = new BufferedReader(new StringReader(text));
			while (in.ready()) {
				String line = in.readLine();
				if (line != null) {
					String[] res = line
							.split("(.*?[.!?](?:[\"')\\]}]|(?:<.*>))*[\\s])");
					for (String string : res) {
						Frag frag = new Frag(string);
						if (frags != null || !frags.equals("")) {
							frags.get(frags.size() - 1).setNext(
									frag.getCleaned()[0]);
						}
						frags.add(frag);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// # Segments the text. More precisely, it reassembles the fragments into
	// sentences.
	// # We call something a sentence whenever it is more likely to be a
	// sentence than not.
	public String[] segment() {
		ArrayList<String> sent = null, sents = null;
		float thresh = (float) 0.5;
		for (Frag frag : frags) {
			sent.add(frag.getOrig());
			if (frag.getPred() > thresh) {
				if (frag.getOrig() == null)
					break;
				sents.add(sent.toString());
				sent = new ArrayList<String>();
			}
		}
		return (String[]) sents.toArray();
	}

}
