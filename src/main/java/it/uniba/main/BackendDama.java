package it.uniba.main;

/**
 * <<Entity>>
 * si occupa del funzionamento delle varie funzioalità messe a disposizione
*/

public class BackendDama {
	private VerificaStatus verifica = new VerificaStatus();
	private String mossa;
	private static final int MAX_PEDINA = 12;
	private int[] pezzimangiati = new int[MAX_PEDINA];
	private int[] pezzimangiatib = new int[MAX_PEDINA];
	private int[] pezzimangiatin = new int[MAX_PEDINA];

	/**
	 * Metodo che si occupa di avviare una nuova partita.
	 * @return in valore aggiornato di exitcode
	 */
	public final int gioca(final int exitcode, final Cronometro cronometrobianco) {
		if (exitcode == 0) {
			cronometrobianco.avanza();
			return 1;
		}
		return exitcode;
	}

	/**
	 * Metodo che si occupa di abbandonare la partita elimando.
	 * @return in valore aggiornato di exitcode
	 */
	public int abbandona(final String input, final Cronometro cronometrobianco,
			final Cronometro cronometronero) {
		int retvalue = 1;
		if (input.equals("y") || input.equals("yes")) {
			retvalue = 0;
			cronometrobianco.azzera();
			cronometronero.azzera();
		}
		return retvalue;
	}

	/**
	 * Metodo che si occupa di uscire dal gioco.
	 * @return in valore aggiornato di exitcode
	 */
	public int esci(final String input, final int exitcode) {
		if (input.equals("y")) {
			return -1;
		}
		return exitcode;
	}

	/**
	 * Metodo che controlla l'arry pezzi per inserire il pezzo mangiato al suo interno.
	 * @return array aggiornato
	 */
	public int[] contaPez(final int es, final int[]pezzi) {
		int j = 0;
		int[] pez;
		System.arraycopy(pezzi, 0, pezzimangiati, 0, pezzi.length);
		while (j < MAX_PEDINA) {
			if (pezzimangiati[j] == 0) {
				pezzimangiati[j] = es;
				pez = pezzimangiati;
				return pez;
			 }
			 j++;
		}
		return pezzi;
	}

	/**
	 * Metodo che di verificare se la presa indicata dal giocatore nero sia fattibile.
	 * @return l'esito del controllo
	 */
	public int verificaNero(final int des, final int pos, final int[][]matGioc, final String mossain) {
		int esito;
		this.mossa = mossain;
		esito = verifica.verificaPresaNero(des, pos, matGioc, mossa);
		return esito;
	}

	/**
	 * Metodo che di verificare se la presa indicata dal giocatore bianco sia fattibile.
	 * @retun l'esito del controllo
	 */
	public int verificaBianco(final int des, final int pos, final int[][]matGioc, final String mossain) {
		int esito;
		this.mossa = mossain;
		esito = verifica.verificaPresaBianco(des, pos, matGioc, mossa);
		return esito;
	}

	/**
	 * Metodo usato se e solo se la mossa nera è fattibile dove effettua la
	 * presa vera e propria inserendo il pezzo mangiato all'interno
	 * dell' array corrispondente.
	 * @retun array agiornato
	 */
	public int[] chiamapresaNero(final int[] es, final String[] valore, final int[] pezzinero,
			final int[][] matGioc) {
		int i = 0;
		int j = 1;
		int poss;
		int des;
		System.arraycopy(pezzinero, 0, pezzimangiatin, 0, pezzimangiatin.length);
		while (es[i] != 0) {
			poss = Integer.parseInt(valore[i]);
			des = Integer.parseInt(valore[j]);
			es[i] = verifica.verificaPresaNero(des, poss, matGioc, mossa);
			pezzimangiatin = contaPez(es[i], pezzimangiatin);
			i++;
			j++;
		}
		int[] pezzi = new int[pezzinero.length];
		System.arraycopy(pezzimangiatin, 0, pezzi, 0, pezzimangiatin.length);
		return pezzi;
	}

	/**
	 * Metodo usato se e solo se la mossa bianca è fattibile dove effettua la
	 * presa vera e propria inserendo il pezzo mangiato all'interno
	 * dell' array corrispondente.
	 * @retun array agiornato
	 */
	public int[] chiamapresaBianco(final int[] es, final String[] valore,
			final int[] pezzibianco, final int[][] matGioc) {
		int i = 0;
		int j = 1;
		int poss;
		int des;
		System.arraycopy(pezzibianco, 0, pezzimangiatin, 0, pezzimangiatin.length);
		while (es[i] != 0) {
			poss = Integer.parseInt(valore[i]);
			des = Integer.parseInt(valore[j]);
			es[i] = verifica.verificaPresaBianco(des, poss, matGioc, mossa);
			pezzimangiatib = contaPez(es[i], pezzimangiatib);
			i = i + 1;
			j = j + 1;
		}
		int[] pezzi = new int[pezzibianco.length];
		System.arraycopy(pezzimangiatib, 0, pezzi, 0, pezzimangiatib.length);
		return pezzi;
	}
}
