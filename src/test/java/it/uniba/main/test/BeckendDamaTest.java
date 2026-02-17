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

import it.uniba.main.BackendDama;
import it.uniba.main.Cronometro;
import it.uniba.main.Numerata;

public class BeckendDamaTest {
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
	@DisplayName("Testing per comando GIOCA ")
	public void gioca() {
		BackendDama bd = new BackendDama();
		Cronometro c = new Cronometro ();
		assertEquals(1, bd.gioca(0, c));
		assertEquals(1, bd.gioca(1, c));
	}
	
	@Test
	@DisplayName("Testing per comando ABBANDONA ")
	public void abbandona() {
		BackendDama bd = new BackendDama();
		Cronometro c = new Cronometro ();	
		assertEquals(0, bd.abbandona("y", c, c));
		assertEquals(1, bd.abbandona("n", c, c));
	}
	
	@Test
	@DisplayName("Testing per comando ESCI ")
	public void esci() {
		BackendDama bd = new BackendDama();
		Cronometro c = new Cronometro ();	
		assertEquals(-1, bd.esci("y", 1));
		assertEquals(1, bd.esci("n", 1));
	}
	
	@Test
	@DisplayName("Testing per CONTA PEZZI ")
	public void contaPez() {
		BackendDama bd = new BackendDama();
		int[] prova = new int[12];	
		assumeNotNull(bd.contaPez(2, prova));		
	}
	
	@Test
	@DisplayName("Verifica NERO ")
	public void verficaNero() {
		int[][] matProva; 
		matProva=inizializzaTest2(6, 9, 10 ,13, 2, 1);
		BackendDama bd = new BackendDama();
		assertEquals(2, bd.verificaNero(9, 2, matProva, "2x9"));
	}
	@Test
	@DisplayName("Verifica Bianco ")
	public void verficaBianco() {
		int[][] matProva; 
		BackendDama bd = new BackendDama();
		matProva=inizializzaTest2(34, 37,30, 33, 1, 2);
		assertEquals(1, bd.verificaBianco(23, 30, matProva, "30x23"));
	}
}
