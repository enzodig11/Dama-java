package it.uniba.main;
/**
 * <<Boundary>>, si occupa della stampa della damiera numerata
*/
public class Numerata {
	private int i;
	private int j;
	private int dim;
	private int cont;
	private int[][]matGioc = new int[DIM][DIM];
	private static final int DIM = 8;
	private static final int CONT = 10;
	/**
	 * Questo metodo genera la damiera numerata
	 * @return matGioc
	*/
	public int[][] generaNumerata() {
		cont = 0;
		i = 0;
		dim = DIM;
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
				j++;
			}
			i++;
		}
		int[][]matGioctemp = matGioc;
		return matGioctemp;
	}
	/**
	 * Questo metodo stampa a video la damiera numerata
	 * @return matGioc[x][y]
	*/
	public void damieraNumerataOut() {
		matGioc = generaNumerata();
		System.out.println("\n");
		System.out.println("---------------------------- DAMIERA--------------------------- \n");
		i = 0;
		while (i < dim) {
			j = 0;
			System.out.print("  +-----+-----+-----+-----+-----+-----+-----+-----+ \n");
			System.out.print("  |  ");
			while (j < dim) {
				if (matGioc[j][i] != 0) {
					System.out.print(matGioc[j][i]);
				} else {
					System.out.print(" ");
				}
				if (matGioc[j][i] < CONT) {
					System.out.print("  |  ");
				} else {
					System.out.print(" |  ");
				}
				j++;
			}
			i++;
			System.out.println("\n");
		}
		System.out.println("  +-----+-----+-----+-----+-----+-----+-----+-----+ \n");
	}
}
