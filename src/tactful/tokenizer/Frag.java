package tactful.tokenizer;

import java.util.ArrayList;
import java.util.List;

//  # A fragment is a potential sentence, but is based only on the existence of a period.
//    # The text "Here in the U.S. Senate we prefer to devour our friends." will be split
//    # into "Here in the U.S." and "Senate we prefer to devour our friends."
public class Frag extends WordTokenizer {

	// # orig = The original text of the fragment.
	// # next = The next word following the fragment.
	// # cleaned = Array of the fragment's words after cleaning.
	// # pred = Probability that the fragment is a sentence.
	// # features = Array of the fragment's features.
	// attr_accessor :orig, :next, :cleaned, :pred, :features
	//

	private String orig;
	public String[] cleaned;
	private String next;
	public float pred;
//	public String[] features ;
	List<String> features = new ArrayList<String>();
	public String getNext() {
		return next;
	}

	public void setNext(String next) {
		this.next = next;
	}

	public float getPred() {
		return pred;
	}

	public void setPred(float pred) {
		this.pred = pred;
	}

	public List<String> getFeatures() {
		return features;
	}

	public void setFeatures(List<String> features) {
		this.features = features;
	}

	public String getOrig() {
		return orig;
	}

	public String[] getCleaned() {
		return cleaned;
	}

	public Frag(String orig) {
		this.orig = orig;
		clean(orig);
	}

	// # Normalizes numbers and discards ambiguous punctuation. And then splits
	// into an
	// # array, because realistically only the last and first words are ever
	// accessed.
	public void clean(String string) {
		string = tokenize(string);
		string = string.replaceAll("[0-9]+/*\\.*[0-9]*", "<NUM>");
		string = string.replaceAll("[!\\*=]", "");
		string=string.replace("--", " ");
		cleaned= string.split(" ");
	}
}
