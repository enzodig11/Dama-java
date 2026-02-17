package it.uniba.main.test;

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

import it.uniba.main.Inizializza;
import it.uniba.main.Numerata;

public class MatriciTest {
	@Test
	@DisplayName("Testing per NUMERATA ")
	public void generaNumerata() {
		Numerata num = new Numerata();
		assumeNotNull(num.generaNumerata());		
	}
	
	@Test
	@DisplayName("Testing per INIZIALIZZA ")
	public void inizializzaPezzi() {
		Inizializza inz = new Inizializza();
		assumeNotNull(inz.inizializzaPezzi());		
	}
}
