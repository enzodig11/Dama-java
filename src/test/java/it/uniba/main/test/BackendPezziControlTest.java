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

import it.uniba.main.BackendPezziControl;
import it.uniba.main.Cronometro;
import it.uniba.main.Inizializza;

public class BackendPezziControlTest {
	private int[][] inizializzaTest2(int a, int b, int c, int d, int tipo, int val){
		int cont=10;
		int[][] matGioc = new int [8][8];
		int i = 0;
		while (i < 8) {
			int j = 0;
			while (j < 8) {
				cont++;
				if (i % 2 == 0) {
					if (cont % 2 == 0) {
						matGioc[j][i] = 0;
					} else {
						matGioc[j][i] = (cont / 2) + 1;
					}
				} else {
					if (cont % 2 != 0) {
						matGioc[j][i] = 0;
					} else {
						matGioc[j][i] = cont / 2;
					}
				}
				j = j + 1;
			}
			i = i + 1;
		}
		i = 0;
		while (i < 8) {
			int j = 0;
			while (j < 8) {
				if (matGioc[j][i] != 0) {
					if (matGioc[j][i] >= a && matGioc[j][i] <= b) {
						matGioc[j][i] = val;
					} else {
						if (matGioc[j][i] >= c && matGioc[j][i] <= d) {
							matGioc[j][i] = tipo;
						} else {
							matGioc[j][i] = 3;
						}
					}
				}
				j++;
			}
			i++;
		}
		return matGioc;
	}
	
	@Test
	@DisplayName("Testing per Controllo MOSSA ")
	public void controlloMossaTest() {
		BackendPezziControl pz = new BackendPezziControl();
		assertEquals(0, pz.controlloMossa("1-10"));
		assertEquals(1, pz.controlloMossa("38-12"));
	}
	
	@Test
	@DisplayName("Testing per Controllo PRESA ")
	public void controlloPresaTest() {
		BackendPezziControl pz = new BackendPezziControl();
		assertEquals(0, pz.controlloPresa("1x10"));
		assertEquals(1, pz.controlloPresa("38x12"));
	}
	
	@Test
	@DisplayName("Testing PRESA ")
	public void backendPresaTest() {
		int[][] matProva;		
		BackendPezziControl pz = new BackendPezziControl();
		List<String> a = new ArrayList<>();
		int[] m = new int[12];
		matProva=inizializzaTest2(6, 9, 10 ,13, 2, 1);
		assertEquals(2, pz.backendPresa("1x10", 1, matProva, a, m, m));
		
		matProva=inizializzaTest2(34, 37,30, 33, 1, 2);
		assertEquals(3, pz.backendPresa("32x23", 2, matProva, a, m, m));	
		}
	
	@Test
	@DisplayName("Testing MOSSA ")
	public void backendMossaTest() {
		int [][] matProva;
		Cronometro c = new Cronometro();
		ArrayList<String> p = new ArrayList<>();
		Inizializza inz = new Inizializza ();
		BackendPezziControl pz = new BackendPezziControl();
		matProva=inz.inizializzaPezzi();
		// PEDINA NERA
		assertEquals(2, pz.backendMossa("9-13", c, c, 1, matProva, p));
		// PEDINA BIANCA
		matProva=inz.inizializzaPezzi();	
		assertEquals(3, pz.backendMossa("21-18", c, c, 2, matProva, p));
	
	}
}
