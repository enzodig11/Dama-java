package it.uniba.main;

/**
 * <<Boundary>>,
 *  si occupa della stampa della damiera con pezzi
*/

public class DamieraPezzi {
	private int z;
	private int k;
	private int temp = 0;
	private static final String PEDINAB = "\u26C0 ";
	private static final String PEDINAN = "\u26C2 ";
	private static final String DAMAB = "\u26C1 ";
	private static final String DAMAN = "\u26C3 ";
	private static final int DIM = 8;
	private static final int VAL_PED_B = 2;
	private static final int VAL_PED_N = 1;
	private static final int VAL_DAM_B = 6;
	private static final int VAL_DAM_N = 5;
	private static final int VAL_CONTR = 3;
	private static final int VAL_CONTR_D = 4;

	/**
	 * Metodo che si occupa della stampa della damiera
	 * con pezzi
	*/

	public void visualizzaDamiera(final int[][]matGioc) {
		System.out.println("\n");
		System.out.println("---------------------------- DAMIERA--------------------------- \n");
		k = 0;
		while (k < DIM) {
			z = 0;
			if (k % 2 == 0) {
				System.out.println("  ───────────────────────────────────────────────────────── ");
			}
			if (k % 2 != 0) {
				System.out.println("  ───────────────────────────────────────────────────────── ");
			}
			System.out.print("  |  ");
			while (z < DIM) {
				if (matGioc[z][k] != 0) {
					if (matGioc[z][k] == VAL_PED_N) {
						System.out.print(PEDINAN);
					} else {
						if (matGioc[z][k] == VAL_PED_B) {
								 System.out.print(PEDINAB);
						} else  {
							if (matGioc[z][k] == VAL_DAM_N) {
								System.out.print(DAMAN);
							} else {
								if (matGioc[z][k] == VAL_DAM_B) {
									System.out.print(DAMAB);
								} else {
									System.out.print("  ");
								}
							}
						}
					}
				} else {
					System.out.print("  ");
				}
					System.out.print("  |  ");
					 z++;
			}
			k++;
			System.out.println("\n");
		}
		System.out.println("  ───────────────────────────────────────────────────────── ");
	}

	/**
	 * Metodo che si occupa della modifica della damiera
	 * con pezzi
	*/

	public int modificaDamiera(final int x, final int y, final int[][] matGioc) {
		return matGioc[x][y];
	}

	/**
	 * Metodo che si occupa della modifica della damiera
	 * con pezzi
	*/

	public int spostaPezzi(final int[][]matGioc, final int rig, final int col,
			final int rig2, final int col2) {
			temp = matGioc[col][rig];
			matGioc[col][rig] = matGioc[col2][rig2];
			matGioc[col2][rig2] = temp;
			return 0;
	}

	/**
	 * Metodo che si occupa della modifica della damiera
	 * con pezzi
	*/

	public int eliminaPezzi(final int[][]matGioc, final int rig, final int col,
			final int rig2, final int col2, final int rigm, final int colm) {
			this.temp = matGioc[col][rig];
			matGioc[col][rig] = matGioc[col2][rig2];
			matGioc[col2][rig2] = temp;
			matGioc[colm][rigm] = matGioc[col][rig];
			return 0;
	}

	/**
	 * Metodo che si occupa della modifica della damiera
	 * con pezzi
	*/

	public int damatura(final int[][]matGioc, final int rig, final int col,
			final int dama) {
			if (dama == VAL_DAM_N) {
				matGioc[col][rig] = dama;
			}
			if (dama == VAL_DAM_B) {
				matGioc[col][rig] = dama;
			}
		return 0;
	}
}
