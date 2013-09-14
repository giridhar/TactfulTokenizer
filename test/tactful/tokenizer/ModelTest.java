package tactful.tokenizer;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class ModelTest {

	@Test
	public void testTokenize_text() throws IOException {
		Model model = new Model();
		String[] re = model.tokenize_text("come someday on mon.da.aa. please yrr. its urgent");
		for(String print:re){
			System.out.println(print);
		}
	}
}
