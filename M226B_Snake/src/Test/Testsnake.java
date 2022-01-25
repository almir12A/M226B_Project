package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Rectangle;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Modell.Snake;

class Testsnake {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void length() {
		//fail("Not yet implemented");
		// überprüft die lange
		Snake snake = new Snake();
		ArrayList<Rectangle> length = snake.getBody();
		assertEquals(3, length);
			
		
		
	}

}
