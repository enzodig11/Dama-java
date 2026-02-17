package it.uniba.main;
import java.util.List;
import java.util.Scanner;
/**
 * <<Boundary>>, gestisce interazione con l'utente
*/
public class Output {
	private int i = 0;
	private String workvar = "x";
	private DamieraPezzi damiera = new DamieraPezzi();
	private static Scanner scan = new Scanner(System.in, "UTF-8");
	private static final int MAXPRESE = 12;
	private static final int NERO = 1;
	private static final int BIANCO = 2;
	private static final int DAMAN = 5;
	private static final int DAMAB = 6;

	/**
	 * Questo metodo serve a stampare a video il comando help
	 */

	public void helpOut() {
		System.out.println("PUOI UTILIZZARE I COMANDI:");
		System.out.println("gioca");
		System.out.println("esci");
		System.out.println("abbandona");
		System.out.println("numeri");
		System.out.println("tempo");
		System.out.println("damiera");
		System.out.println("prese");
		System.out.println("Spostamento semplice di pedina in notazione algebrica. Es. 1-5");
		System.out.println("Spostamento di pedina con presa semplice in notazione algebrica. Es. 18x11");
		System.out.println("Spostamento di pedina con presa semplice in notazione algebrica. Es. 22x15x6 ");
		System.out.println("...");
	}

	/**
	 * Questo metodo serve a dare un messaggio di aiuto all'utente
	 * nel caso in cui inserisca un comando non riconosciuto
	 */

	public void defOut() {
		System.out.println("SE NON SAI COME UTILIZZARE L'APPLICAZIONE INSERISCI IL COMANDO 'help'!\n");
	}

	/**
	 * Questo metodo serve a stampare un messaggio che conferma
	 * l'inizio di una nuova partita
	 * @param exitcode
	 */

	public void giocaOut(final int exitcode) {
		if (exitcode == 0) {
			System.out.println("================== INIZIO NUOVA PARTITA =================\n");
		} else {
			System.out.println("==================== PARTITA IN CORSO ===================\n");
		}
	}

	/**
	 * Questo metodo ha il compito di chiedere la conferma per
	 * abbandonare la partita
	 * @param exitcode
	 * @param turno
	 * @return
	 */

	public String abbandonaOut(final int exitcode, final int turno) {
		if (exitcode == 0) {
			System.out.println("================ NESSUNA PARTITA IN CORSO ===============\n");
		} else {
			while (!workvar.equals("y") && !workvar.equals("n"))	{
				System.out.println("SEI SICURO DI VOLER ABBANDONARE LA PARITA? \n"
					+ "DIGITA (Y / N ) OPPURE (YES / NO)");
				workvar = scan.next();
				workvar = workvar.toLowerCase();
				if (workvar.equals("y") || workvar.equals("yes")) {
					if (turno % 2 == 0) {
						System.out.println("====== IL BIANCO ABBANDONA,"
								+ "IL NERO VINCE LA PARTITA ====\n");
						workvar = "y";
					} else {
						System.out.println("====== IL NERO ABBANDONA,"
								+ "IL BIANCO VINCE LA PARTITA ====\n");
						workvar = "y";
					}
				}
				if (workvar.equals("n") || workvar.equals("no")) {
					System.out.println("================= "
							+ "LA PARTITA CONTINUA ===================\n");
					workvar = "n";
				}
			}
		}
		return workvar;
	}

	/**
	 * Questo metodo ha il compito di chiedere la conferma per
	 * chiudere il programma
	 * @return workvar
	 */

	public String esciOut() {
		while (!workvar.equals("y") && !workvar.equals("n")) {
			System.out.println("SEI SICURO DI VOLER USCIRE DAL GIOCO? \n"
					+ "DIGITA (Y / N ) OPPURE (YES / NO)");
		    workvar = scan.next();
		    workvar = workvar.toLowerCase();
			if (workvar.equals("y") || workvar.equals("yes")) {
				System.out.println("================ "
						+ "IL GIOCO E' STATA CHIUSO ===============\n");
				workvar = "y";
			}
		    if (workvar.equals("n") || workvar.equals("no")) {
		    	System.out.println("================= "
		    			+ "LA PARTITA CONTINUA ===================\n");
		    	workvar = "n";
		    }
		}
		return workvar;
	}

	/**
	 * Questo metodo stampa a video il tempo per il bianco e per il nero
	 * @param exitcode
	 * @param cronometrob
	 * @param cronometron
	 */

	public void tempo(final int exitcode, final Cronometro cronometrob, final Cronometro cronometron) {
		if (exitcode == 0) {
			System.out.println("================ NESSUNA PARTITA IN CORSO! ===============\n");
			System.out.println("====== INSERISCI IL COMANDO 'gioca', "
					+ "PER INIZIARE UNA NUOVA PARTITA =====\n");
		} else {
			System.out.println("IL TEMPO DEL GIOCATORE BIANCO E': " + cronometrob.toString());
			System.out.println("IL TEMPO DEL GIOCATORE NERO E': " + cronometron.toString());
		}
	}

	/**
	 * Questo metodo stampa un messaggio d'errore
	 */

	public void errore() {
		System.out.println("Comando Errato!");
	}

	/**
	 * Questo metodo stampa la mossa errata inserita dall'utente
	 * @param pos
	 * @param des
	 */

	public void erroreMossa(final int pos, final int des) {
		System.out.println("Mossa errata da " + pos + " a " + des);
	}

	/**
	 * Questo metodo stampa l'elenco delle mosse
	 * @param elencomosse
	 */

	public void elencoMossa(final List<String> elencomosse) {
		System.out.println(elencomosse);
	}

	/**
	 * Questo metodo stampa la damiera
	 * @param exitcode
	 * @param turno
	 * @param matGioc
	 */

	public void damieraOut(final int exitcode, final int turno, final int[][] matGioc) {
		if (exitcode == 1) {
			damiera.visualizzaDamiera(matGioc);
		} else {
			System.out.println("================ NESSUNA PARTITA IN CORSO! ===============\n");
			System.out.println("====== INSERISCI IL COMANDO 'gioca', "
					+ "PER INIZIARE UNA NUOVA PARTITA =====\n");
		}
	}

	/**
	 * Questo metodo stampa le pedine prese da entrambi i giocatori
	 * @param pezb
	 * @param pezn
	 */

	public void preseOut(final int[] pezb, final int[] pezn) {
		System.out.println("====================== PRESE BIANCO ======================\n");
		i = 0;
		while (i < MAXPRESE) {
			if (pezb[i] == NERO) {
				System.out.print("\u26C2  ");
			}
			if (pezb[i] == DAMAN) {
				System.out.print("\u26C3  ");
			}
			i = i + 1;
		}
		i = 0;
		System.out.println("\n======================= PRESE NERO =======================\n");
		while (i < MAXPRESE) {
			if (pezn[i] == BIANCO) {
				System.out.print("\u26C0  ");
			}
			if (pezn[i] == DAMAB) {
				System.out.print("\u26C1  ");
			}
			i = i + 1;
		}
		System.out.println(" ");
	}
}
