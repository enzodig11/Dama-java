package it.uniba.main.test;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assume.assumeFalse;
import static org.junit.Assume.assumeNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import it.uniba.main.StringControl;

public class StringControlTest {
	StringControl st = new StringControl(null, 0, 0);
	@Test
	@DisplayName("MOSSE ")
	public void verficaMosseTest() {
		StringControl st = new StringControl("9-13", 1, 1);
		assertEquals(1, st.inputControl("9-13", 1));
		assertEquals(1, st.inputControl("9-10", 1));
		StringControl st2 = new StringControl("9-13", 1, 2);
		assertEquals(1, st2.inputControl("21-18", 1));
		assertEquals(1, st2.inputControl("21-12", 1));
	}
	
	@Test
	@DisplayName("PRESE ")
	public void verficaPreseTest() {
		StringControl st = new StringControl("1x10", 1, 1);
		assertEquals(1, st.inputControl("1x10", 1));
		StringControl st2 = new StringControl("18x9", 1, 2);
		assertEquals(1, st2.inputControl("18x9", 1));
	}
	
	@Test
	@DisplayName("PRESE VISUALIZZA")
	public void preseTest() {
		StringControl st = new StringControl("prese", 1, 1);
		assertEquals(1, st.inputControl("prese", 1));
	}
	
	@Test
	@DisplayName("MOSSE VISUALIZZA")
	public void mosseTest() {
		StringControl st = new StringControl("mosse", 1, 1);
		assertEquals(1, st.inputControl("mosse", 1));
	}
	
	@Test
	@DisplayName("NUMERATA")
	public void numerataTest() {
		StringControl st = new StringControl("numeri", 1, 1);
		assertEquals(1, st.inputControl("numeri", 1));
	}
	
	@Test
	@DisplayName("DAMIERA")
	public void damieraTest() {
		StringControl st = new StringControl("damiera", 1, 1);
		assertEquals(1, st.inputControl("damiera", 1));
		
		StringControl st1 = new StringControl("damiera", 0, 1);
		assertEquals(0, st1.inputControl("damiera", 0));
	}
	
	@Test
	@DisplayName("HELP")
	public void helpTest() {
		StringControl st = new StringControl("help", 1, 1);
		assertEquals(1, st.inputControl("help", 1));
	}
	@Test
	@DisplayName("GIOCA")
	public void giocaTest() {
		StringControl st = new StringControl("gioca", 1, 1);
		assertEquals(1, st.inputControl("gioca", 0));
		assertEquals(1, st.inputControl("gioca", 1));
	}
	
	@Test
	@DisplayName("TEMPO")
	public void tempoTest() {
		StringControl st = new StringControl("tempo", 1, 1);
		assertEquals(0, st.inputControl("tempo", 0));
		assertEquals(1, st.inputControl("tempo", 1));
	}
	

	@Test
	@DisplayName("ABBANDONA")
	public void abbandonaTest() {
		StringControl st = new StringControl("abbandona", 0, 1);
		int[] value = {1,0};
		assertEquals(1, st.inputControl("abbandona", 0));
	
	}
	
}
