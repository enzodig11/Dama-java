package it.uniba.main;

/**
 * 
 *<<Entity>>
 * La classe Cronometro svolge il ruolo di gesitre il tempo del i due giocatori
 */

public class Cronometro {
	private long contatoreminuti;
	private long contatoresecondi;
	private long avviatominuti;
	private long avviatosecondi;
	private long min;
	private long sec;
	private static final int NUM_TO_DIVIDE = 1000;
	private static final int NUM_TO_CONVERT = 60;
	private boolean avanzando;

	 /**
	   * Costruttore: resetta il cronometro invocando il metodo azzera
	   *
	   * */
	public Cronometro() {
		azzera();
	}

	/**
	 * Metodo per azzerare del cronometro.
	 */
	public void azzera() {
		synchronized (this) {
			contatoreminuti = 0;
			contatoresecondi = 0;
			avanzando = false;
	    }
	  }

	/**
	 * Metodo per avviare e reavviare il cronometro.
	 */
	public void avanza() {
		synchronized (this) {
			avviatominuti = System.currentTimeMillis();
			avviatosecondi = System.currentTimeMillis();
			avanzando = true;
		}
	}

	/**
	 * Metodo per fermare il cronometro.
	 */
	public void ferma() {
		synchronized (this) {
			contatoreminuti += System.currentTimeMillis() - avviatominuti;
			contatoresecondi += System.currentTimeMillis() - avviatosecondi;
			avanzando = false;
		}
	}

	/**
	 * Metodo che si occupa di leggere i minuti delcronometro.
	 * @return il numero dei millisecondi contati da quando il cronometro è stato avviato
	 */
	public long leggiminuti() {
		synchronized (this) {
			if (avanzando) {
				return contatoreminuti + System.currentTimeMillis() - avviatominuti;
			} else {
				return contatoreminuti;
			}
		}
	}

	/**
	 * Metodo che si occupa di leggere i secondi delcronometro.
	 * @return il numero dei millisecondi contati da quando il cronometro è stato avviato
	 */
	public long leggisecondi() {
		synchronized (this) {
			if (avanzando) {
				return contatoresecondi + System.currentTimeMillis() - avviatosecondi;
			} else {
				return contatoresecondi;
			}
		}
	}

	/**
	 * Metodo che si occupa di convertitre i millisecondo i minuti e secondi.
	 * @return stringa contenente il risultato
	 */
	public String toString() {
		min = leggiminuti();
		sec = leggisecondi();
		min = min / (NUM_TO_DIVIDE * NUM_TO_CONVERT);
		sec = sec / (NUM_TO_DIVIDE) % NUM_TO_CONVERT;
	    return "" + min + " MIN" + " " + sec + " SEC";
	}
}
