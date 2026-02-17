package it.uniba.main;

import java.util.List;

/**
 * <<Entity>>
 * si occupa del funzionamento delle varie funzioalità dei pezzi (dama/pedina)
*/

public class BackendPezziControl {
	private VerificaStatus verifica = new VerificaStatus();
	private BackendDama backend = new BackendDama();
	private int turno;
	private static final int POS_FIRST = 1;
	private static final int POS_FINAL = 32;
	private static final int MAX_PEDINA = 12;
	private static final int NUM_ITER = 10;
	private static final int MATX_DIM = 8;
	private int[] pezzimangiatib = new int[MAX_PEDINA];
	private int[] pezzimangiatin = new int[MAX_PEDINA];

	/**
	 * Metodo che di verificare che la mossa semplice inserita sia corretta o meno.
	 * @retun controllo che indica se la mossa è corretta o meno
	 */
	public int controlloMossa(final String mossa) {
		String[] valore;
		int pos;
		int des;
		int controllo = 1;
		valore = mossa.split("-");
		pos = Integer.parseInt(valore[0]);
		des = Integer.parseInt(valore[1]);
		if (pos >= POS_FIRST && pos <= POS_FINAL) {
			if (des >= POS_FIRST && des <= POS_FINAL) {
				controllo = 0;
			} else {
				controllo = 1;
			}
		}
		return controllo;
	}

	/**
	 * Medodo che verifica in quale turno ci troviamo e invoca il metodo corrispondente.
	 * @retun turno aggiornato
	 */
	public int backendMossa(final String mossa, final Cronometro cronometrobianco, final Cronometro cronometronero,
			final int turnoin, final int[][]matGioc, final List<String> elencomosse) {
		this.turno = turnoin;
		if (turno % 2 == 0) {
			turno = verifica.verificaMossaBianco(mossa, cronometrobianco, cronometronero,
					turno, matGioc, elencomosse);
		} else {
			turno = verifica.verificaMossaNero(mossa, cronometrobianco, cronometronero,
					turno, matGioc, elencomosse);
		}
		return turno;
	}

	/**
	 * Metodo che di verificare che la presa inserita sia corretta o meno.
	 * @retun controllo che indica se la presa è corretta o meno
	 */
	public int controlloPresa(final String mossa) {
		String[] valore;
		int pos;
		int des;
		int controllo = 1;
		valore = mossa.split("x");
		pos = Integer.parseInt(valore[0]);
		des = Integer.parseInt(valore[1]);
		if (pos >= POS_FIRST && pos <= POS_FINAL) {
			if (des >= POS_FIRST && des <= POS_FINAL) {
				controllo = 0;
			} else {
				controllo = 1;
			}
		}
		return controllo;
	}
	/**
	 * Medodo che verifica in quale turno ci troviamo e invoca i metodi corrispondente.
	 * Inzializzando tutte le informazioni tutte le variabili che ci serviranno per i
	 * vari metodi.
	 * @retun turno aggiornato
	 */
	public int backendPresa(final String mossa, final int turnoin, final int[][]matGioc,
			final List<String> elencomosse, final int[] presenero, final int[] presebianco) {
		this.turno = turnoin;
		if (turno % 2 == 0) {
			System.arraycopy(presebianco, 0, this.pezzimangiatib, 0, presebianco.length);
			String[] valore = mossa.split("x");
            int i = 0;
            int j = 1;
            int des;
            String s = null;
            int[][] matGiocapp = inizializzaMat(matGioc);
			StringBuffer buf = new StringBuffer();
			buf.append("B. " + valore[0]);
            int poss;
            int[] es = new int[NUM_ITER];
            while (valore.length > j) {
                poss = Integer.parseInt(valore[i]);
                des = Integer.parseInt(valore[j]);
                es[i] = verifica.verificaPresaBianco(des, poss, matGiocapp, mossa);
                if (es[i] != 0) {
					buf.append("x" + valore[j]);
                }
                i = i + 1;
                j = j + 1;
                s = buf.toString();
            }
            if (es[i - 1] != 0) {
            	pezzimangiatib = backend.chiamapresaBianco(es, valore, pezzimangiatib, matGioc);
            	turno = turno + 1;
				s = (s + "\n");
				elencomosse.add(s);
            }
		} else {
			System.arraycopy(presenero, 0, this.pezzimangiatin, 0, presenero.length);
			String[] valore = mossa.split("x");
            int i = 0;
            int j = 1;
            int des;
            int poss;
            int[][] matGiocapp = inizializzaMat(matGioc);
			StringBuffer buf = new StringBuffer();
			buf.append("N. " + valore[0]);
			String s = null;
            int[] es = new int[NUM_ITER];
            while (valore.length > j) {
                poss = Integer.parseInt(valore[i]);
                des = Integer.parseInt(valore[j]);
                es[i] = verifica.verificaPresaNero(des, poss, matGiocapp, mossa);
                if (es[i] != 0) {
                	buf.append("x" + valore[j]);
                }
                i = i + 1;
                j = j + 1;
                s = buf.toString();
            }
            if (es[i - 1] != 0) {
            	pezzimangiatin = backend.chiamapresaNero(es, valore, pezzimangiatin, matGioc);
                turno = turno + 1;
				s = (s + "\n");
				elencomosse.add(s);
            }
		}
		return turno;
	}

	/**
	 * Medodo che permette il ritorno dei pezzi mangiati dal bianco.
	 * @retun pezzi mangiati aggiornati
	 */
	public int[] getPezziMangiatib() {
		int[] pezzimang = new int[MAX_PEDINA];
		System.arraycopy(this.pezzimangiatib, 0, pezzimang, 0, pezzimangiatib.length);
		return pezzimang;
	}
	/**
	 * Medodo che permette il ritorno dei pezzi mangiati dal nero.
	 * @retun pezzi mangiati aggiornati
	 */
	public int[] getPezziMangiatin() {
		int[] pezzimang = new int[MAX_PEDINA];
		System.arraycopy(this.pezzimangiatin, 0, pezzimang, 0, pezzimangiatin.length);
		return pezzimang;
	}

	/**
	 * Medodo che si occupa di inizializzare una matrice fittizzia
	 * utilizzata per verificare le prese
	 * @retun matrice fittizza
	 */
	public int[][] inizializzaMat(final int[][] matGioc) {
		int[][] matGiocapp = new int[MATX_DIM][MATX_DIM];
			for (int i = 0; i < MATX_DIM; i++) {
				for (int j = 0; j < MATX_DIM; j++) {
					matGiocapp[j][i] = matGioc[j][i];
				}
			}
		return matGiocapp;
	}
}
