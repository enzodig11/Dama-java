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

import it.uniba.main.DamieraPezzi;
import it.uniba.main.Inizializza;

public class DamieraPezziTest {
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
	@DisplayName("Testing per metodo che SPOSTA I PEZZI")
	public void spostaPezziTest() {
		int [][] matProva;
		DamieraPezzi dp = new DamieraPezzi();
		Inizializza inz = new Inizializza();
		matProva=inz.inizializzaPezzi();	
		assertEquals(0, dp.spostaPezzi(matProva, 0, 2, 1, 3));		
	}
	
	@Test
	@DisplayName("Testing per metodo che ELIMINA I PEZZI")
	public void eliminaPezziTest() {
		int [][] matProva;
		DamieraPezzi dp = new DamieraPezzi();	
		matProva=inizializzaTest2(6, 9, 10 ,13, 2, 1);
		assertEquals(0, dp.eliminaPezzi(matProva, 0, 0, 2, 2, 1, 1));		
	}
	
	@Test
	@DisplayName("Testing per metodo che ELIMINA I PEZZI")
	public void damaturaTest() {
		int [][] matProva;
		DamieraPezzi dp = new DamieraPezzi();	
		matProva=inizializzaTest2(6, 9, 10 ,13, 1, 2);
		assertEquals(0, dp.damatura(matProva, 0, 0, 6));		
	}

}
