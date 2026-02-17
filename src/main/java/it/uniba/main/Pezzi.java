package it.uniba.main;

import java.util.List;

/**
 * <<Entity>>,
 * classe astratta che contiene i metodi che andranno poi implementati ad hoc
 * per un pezzo semplice o una dama
 */
public abstract class Pezzi {
	/**
	 * Metodo, per effettuare la mossa semplice della pedina / dama nera
	 * @return turno aggiornato
	 */

	public abstract int mossaSempliceNero(String mossa, Cronometro cronometrobianco, Cronometro cronometronero,
			int turno, int[][]matGioc, List<String> elencomosse);

	/**
	 * Metodo, per effettuare la mossa semplice della pedina / dama bianco
	 * @return turno aggiornato
	 */

	public abstract int mossaSempliceBianco(String mossa, Cronometro cronometrobianco, Cronometro cronometronero,
			int turno, int[][]matGioc, List<String> elencomosse);

	/**
	 * Metodo, per effettuare la presa della pedina / dama nera
	 * @return valore pezzo mangiato
	 */

	public abstract int presaNero(int desin, int posin, int[][]matGioc, String mossa);

	/**
	 * Metodo, per effettuare la presa della pedina / dama bianca
	 * @return valore pezzo mangiato
	 */

	public abstract int presaBianco(int desin, int posin, int[][]matGioc, String mossa);


}
