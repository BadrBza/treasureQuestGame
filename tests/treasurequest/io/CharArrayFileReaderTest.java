package treasurequest.io;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CharArrayFileReaderTest {
	@Test
	public void mapsFileContentToCharArray() {
		char[][] expected = new char[][] {
			{'X','X','X','X','X','X','X','S','S','S','X','S','S','S','S','S','S','S'},
			{'X','S','S','S','S','S','X','X','X','X','X','X','S','S','S','X','S','S'},
			{'X','S','S','P','P','S','S','S','S','S','X','X','X','X','X','X','X','S'},
			{'X','X','S','P','F','F','F','F','P','S','X','X','X','X','X','X','X','P'},
			{'X','X','X','S','P','P','F','F','F','F','F','P','S','S','S','S','S','F'},
			{'X','X','S','S','P','F','F','R','R','R','F','F','R','R','R','R','R','R'},
			{'X','X','S','P','P','S','X','X','S','S','S','S','S','P','P','R','R','R'},
			{'X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X'}
		};
		
		char[][] actual = CharArrayFileReader.parseFile("resources/maps/map-sample-4.txt");
		
		
		assertEquals(expected.length, actual.length, "Expecting same length");
		for(int pos = 0; pos < expected.length; ++pos) {
			assertArrayEquals(expected[pos], actual[pos], String.format("at line %d", pos));
		}
	}
	
	@Test
	public void throwsExceptionWhenPathDoesNotMatchAnExistingFile() {
		assertThrows(RuntimeException.class, () -> CharArrayFileReader.parseFile("resources/maps/unexisting-file.txt"));
	}
}
