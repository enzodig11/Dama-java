package it.uniba.main;

import java.util.Scanner;


/**
 * <<Boundary>>
 * La classe AppMain ha il compito di interfacciarsi con l'utente e avviare l'applicazione
*/

public final class AppMain {
	private static Scanner scanner = new Scanner(System.in, "UTF-8");
    private StringControl stringcontrol;
    private int exitcode;
    private int turno;
    private String input = null;
    private static Output out = new Output();

    private AppMain() {
        exitcode = 0;
        turno = 0;
        stringcontrol = new StringControl(input, exitcode, turno);
    }

    public void run() {
        System.out.println("===================== DAMA ITALIANA =====================");
        System.out.println("INSERISCI UN COMANDO:");
			while (scanner.hasNextLine()) {
			   input = scanner.nextLine();
			   input = input.toLowerCase();
			   exitcode = stringcontrol.inputControl(input, exitcode);
				if (exitcode == -1) {
					break;
				}
			   turno = stringcontrol.getTurno();
			   if (turno % 2 == 0 && exitcode == 1) {
			       System.out.println("================= TURNO GIOCATORE BIANCO ================");
			   }
			   if (turno % 2 != 0 && exitcode == 1) {
			       System.out.println("================= TURNO GIOCATORE NERO ==================");

			   }
			   System.out.println("INSERISCI UN COMANDO:");
			}
		}


    public static void main(final String[] args) {
    	if (args.length > 0) {
    		switch (args[0]) {
    			case ("help"):
    				out.helpOut();
    			break;
    			case ("-help"):
    				out.helpOut();
    			break;
    			case ("--help"):
    				out.helpOut();
    			break;
    			case ("-h"):
    				out.helpOut();
    			break;
    			case ("--h"):
    				out.helpOut();
    			default: out.defOut();
    		}
    	}
    	AppMain appMain = new AppMain();
        appMain.run();
    }
}

