package it.uniba.main;
/**
 * <<Entity>>, si occupa ddi inizializzare la damiera
*/
public class Inizializza {
	private int i = 0;
	private int j = 0;
	private int dim = 0;
	private int cont = 0;
	private static final int NERO = 1;
	private static final int BIANCO = 2;
	private static final int VUOTO = 3;
	private static final int CASELLEN = 6;
	private static final int CASELLEV = 17;
	private static final int CASELLEB = 26;
	private static final int DIM = 8;
	private static final int CONT = 10;
	/**
	 * Questo metodo serve ad inizializzare la matrice che farà da damiera
	 * @return matGioc
	 */
	public int[][] inizializzaPezzi() {
		dim = DIM;
		cont = CONT;
    	int[][]matGioc = new int[dim][dim];
    	i = 0;
    	while (i < dim) {
    		j = 0;
			while (j < dim) {
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
		while (i < dim) {
			j = 0;
			while (j < dim) {
				if (matGioc[j][i] != 0) {
					if (matGioc[j][i] >= CASELLEN && matGioc[j][i] <= CASELLEV) {
						matGioc[j][i] = NERO;
					} else {
						if (matGioc[j][i] >= CASELLEB) {
							matGioc[j][i] = BIANCO;
						} else {
							matGioc[j][i] = VUOTO;
						}
					}
				}
				j++;
			}
			i++;
		}
		return matGioc;
    }
}
