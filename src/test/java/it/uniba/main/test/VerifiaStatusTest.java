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

import it.uniba.main.Cronometro;
import it.uniba.main.VerificaStatus;

public class VerifiaStatusTest {
	private int[][] inizializzaTest(int a, int b, int val){
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
					}else {
							matGioc[j][i] = 3;
						}
					
					
				}
				j++;
			}
			i++;
		}
		return matGioc;
	}
	
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
	@DisplayName("Testing per VERIFCA MOSSA PEDINA E DAMA NERA")
	public void verficaMossaNeroTest() {
		VerificaStatus ver = new VerificaStatus ();
		int [][] matProva;
		Cronometro c = new Cronometro();
		ArrayList<String> p = new ArrayList<>();
		// PEDINA NERA
		matProva=inizializzaTest(6, 9, 1);
		assertEquals(2, ver.verificaMossaNero("1-5", c, c, 1, matProva, p));
		// DAMA NERA
		matProva=inizializzaTest(6, 9, 5);
		assertEquals(2, ver.verificaMossaNero("1-5", c, c, 1, matProva, p));		
	}
	
	@Test
	@DisplayName("Testing per VERIFCA MOSSA PEDINA E DAMA BIANCA")
	public void verficaMossaBiancoTest() {
		VerificaStatus ver = new VerificaStatus ();
		int [][] matProva;
		Cronometro c = new Cronometro();
		ArrayList<String> p = new ArrayList<>();
		// PEDINA BIANCA
		matProva=inizializzaTest(26, 29, 2);	
		assertEquals(3, ver.verificaMossaBianco("21-17", c, c, 2, matProva, p));
		// DAMA BIANCA
		matProva=inizializzaTest(34, 37, 6);
		assertEquals(3, ver.verificaMossaBianco("29-25", c, c, 2, matProva, p));		
	}
	
	@Test
	@DisplayName("Testing per VERIFCA PRESA PEDINA E DAMA NERA")
	public void verficaPresaNeroTest() {
		VerificaStatus ver = new VerificaStatus ();
		int [][] matProva;
		// PEDINA NERA
		matProva=inizializzaTest2(6, 9, 10 ,13, 2, 1);
		assertEquals(2, ver.verificaPresaNero(10, 1, matProva, "1x10"));
		// DAMA NERA
		matProva=inizializzaTest2(6, 9, 10 ,13, 2, 5);
		assertEquals(2, ver.verificaPresaNero(10, 1, matProva, "1x10"));;		
	}
	
	@Test
	@DisplayName("Testing per VERIFCA PRESA PEDINA E DAMA BIANCA")
	public void verficaPresaBiancoTest() {
		VerificaStatus ver = new VerificaStatus ();
		int [][] matProva;
		// PEDINA BIANCA
		matProva=inizializzaTest2(30, 33, 26, 29, 1, 2);
		assertEquals(1, ver.verificaPresaBianco(19, 26, matProva, "26x19"));
		// DAMA BIANCA
		matProva=inizializzaTest2(30, 33, 26, 29, 1, 6);
		assertEquals(1, ver.verificaPresaBianco(19, 26, matProva, "26x19"));		
	}
}
