package tactful.tokenizer;

import java.util.Scanner;

public class WordTokenizer {
	private String [][] tokenize_regexps = {
			{"''|``", "\"" },   //Verified Working fine...
			
//	        # Separate punctuation (except for periods) from words.
	        {"(^|\\s)(')", "$1$2"},  //Verified Working fine ..
	        {"(?=[\\(\"`{\\[:;&#*@])(.)", "$1 "},  // Not sure what is supposed to do...
			
	        {"(?=[?!\\)\";}\\]*:@'])|(?=[\\)}\\]])(.)|(.)(?=[({\\[])|((^|\\s)-)(?=[^-])", "$1 "}, //Not sure what is supposed to do

//	        # Treat double-hyphen as a single token.
	        {"([^-])(--+)([^-])", "$1 $2 $3"}, //Verified Working...
	        {"(\\s|^)(,)(?=(\\S))", "$1$2 "}, //Verified Working.
			
//	        # Only separate a comma if a space follows.
	        {"(.)(,)(\\s|$)","$1 $2$3"}, //Verified working...

//	       # Combine dots separated by whitespace to be a single token.
	       {"\\.\\s\\.\\s\\.", "..."}, //Verified working now...

//	        # Separate "No.6"
	        {"([a-zA-Z].)(\\d+)", "$1 $2"},   //Verified Working ....

//	        # Separate words from ellipses
	        {"([^\\.]|^)(\\.{2,})(.?)" , "$1 $2 $3"}, //Verified Working ..
	        {"(^|\\s)(\\.{2,})([^\\.\\s])", "$1$2 $3"}, //Verified Working...
	        {"(^|\\s)(\\.{2,})([^\\.\\s])", "$1 $2$3"}, //Verified Working.

//	        ##### Some additional fixes.

//	        # Fix %, $, &
	        {"(\\d)%", "$1 %"}, //Fixed Working
	        {"\\$(\\.?[0-9])", "\\$ $1"},  //Fixed Working ....
	        {"(\\w)& (\\w)", "$1&$2"},  //Verified Working....
	        {"(\\w\\w+)&(\\w\\w+)", "$1 & $2"}, //Verified Working..

//	        # Fix (n 't) -> ( n't)
	        {"n 't( |$)", " n't$1"}, //Verified Working fine...
	        {"N 'T( |$)", " N'T$1"}, //Verified Working fine...

//	        # Treebank tokenizer special words
	        {"([Cc])annot", "$1an not"} // Verified working now...

	    };
	
	public String tokenize(String s){
		for(String[] tokenize_regexp:tokenize_regexps){
			//System.out.println("string that causes error  "+s);
			//System.out.println("regex 1   "+tokenize_regexp[0]+"regex 2   "+tokenize_regexp[1]);
			System.out.println("s before "+s);
			s=s.replaceAll(tokenize_regexp[0],tokenize_regexp[1]);
			System.out.println("s after "+s);
		}
		return s;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("please enter line to check replacement ");
		String input = sc.nextLine();
		input = input.replaceAll("(\\s|^)(,)(?=(\\S))", "$1$2 ");
		System.out.println("After Replacement = "+input);
	}
}
