package it.uniba.main;

import java.util.List;
/**
 * 
 * <<Entity>>
 * 
 * La classe VerificaStatus si occupa di verificare se la pedina che
 * richiama la mossa è una pedina semplice o una dama
 *
 */
public class VerificaStatus {
	private static final int NERO = 1;
	private static final int BIANCO = 2;
	private static final int DAMAN = 5;
	private static final int DAMAB = 6;
	private static final int COLONNE = 8;
	private static final int RIGHE = 8;
	private int turno;
	private int[][]matNum = new int[RIGHE][RIGHE];
	private int pos = 0;
	private Dama dama = new Dama();
	private Pedine pedina = new Pedine();
	/**
	 * 
	 * Questo metodo serve a verificare la correttezza della mossa del bianco
	 * dà come valore di ritorno l'intero turno
	 *
	 */
	public int verificaMossaBianco(final String mossa, final Cronometro cronometrobianco,
			final Cronometro cronometronero, final int turnotemp,
			final int[][] matGioc, final List<String> elencomosse) {
		this.turno = turnotemp;
		String[] valore;
		valore = mossa.split("-");
		pos = Integer.parseInt(valore[0]);
		Numerata num = new Numerata();
		matNum = num.generaNumerata();
		int i = 0;
		while (i < RIGHE) {
			int j = 0;
			while (j < COLONNE) {
				if (matNum[j][i] == pos) {
					if (matGioc[j][i] == BIANCO) {
						turno = pedina.mossaSempliceBianco(mossa,
								cronometrobianco, cronometronero,
								turno, matGioc, elencomosse);
						break;
					}
					if (matGioc[j][i] == DAMAB) {
						turno = dama.mossaSempliceBianco(mossa,
								cronometrobianco, cronometronero,
								turno, matGioc, elencomosse);
						break;
					}
				}
			j++;
			}
			i++;
		}
		return turno;
	}
	/**
	 * 
	 * Questo metodo serve a verificare la correttezza della mossa del nero
	 * dà come valore di ritorno l'intero turno
	 *
	 */
	public int verificaMossaNero(final String mossa, final Cronometro cronometrobianco,
			final Cronometro cronometronero, final int turnotemp,
			final int[][] matGioc, final List<String> elencomosse) {
		this.turno = turnotemp;
		String[] valore;
		valore = mossa.split("-");
		pos = Integer.parseInt(valore[0]);
		Numerata num = new Numerata();
		matNum = num.generaNumerata();
		int i = 0;
		while (i < RIGHE) {
			int j = 0;
			while (j < COLONNE) {
				if (matNum[j][i] == pos) {
					if (matGioc[j][i] == NERO) {
						turno = pedina.mossaSempliceNero(mossa,
								cronometrobianco, cronometronero,
								turno, matGioc, elencomosse);
						break;
					}
					if (matGioc[j][i] == DAMAN) {
						turno = dama.mossaSempliceNero(mossa,
								cronometrobianco, cronometronero,
								turno, matGioc, elencomosse);
						break;
					}
				}
			j++;
			}
			i++;
		}
		return turno;
	}
	/**
	 * 
	 * Questo metodo serve a verificare la correttezza della presa del bianco
	 * dà come valore di ritorno l'intero di controllo es
	 *
	 */
	public int verificaPresaBianco(final int des,
			final int posizione, final int[][] matGioc, final String mossa) {
		int i = 0;
		int es = 0;
		i = 0;
		Numerata num = new Numerata();
		matNum = num.generaNumerata();
		while (i < RIGHE) {
			int j = 0;
			while (j < COLONNE) {
				if (matNum[j][i] == posizione) {
					if (matGioc[j][i] == BIANCO) {
						es = pedina.presaBianco(des, posizione, matGioc, mossa);
						return es;
					}
					if (matGioc[j][i] == DAMAB) {
						es = dama.presaBianco(des, posizione, matGioc, mossa);
						return es;
					}
				}
				j++;
			}
			i++;
		}
		return es;
	}
	/**
	 * 
	 * Questo metodo serve a verificare la correttezza della presa del nero
	 * dà come valore di ritorno l'intero di controllo es
	 *
	 */
	public int verificaPresaNero(final int des,
			final int posizione, final int[][] matGioc, final String mossa) {
		int i = 0;
		int es = 0;
		i = 0;
		Numerata num = new Numerata();
		matNum = num.generaNumerata();
		while (i < RIGHE) {
			int j = 0;
			while (j < COLONNE) {
				if (matNum[j][i] == posizione) {
					if (matGioc[j][i] == NERO) {
						es = pedina.presaNero(des, posizione, matGioc, mossa);
						return es;
						}
					if (matGioc[j][i] == DAMAN) {
						es = dama.presaNero(des, posizione, matGioc, mossa);
						return es;
					}
				}
				j++;
			}
			i++;
		}
		return es;
	}
}
