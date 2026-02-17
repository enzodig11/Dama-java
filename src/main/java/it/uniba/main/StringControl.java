package it.uniba.main;

import java.util.ArrayList;
import java.util.List;

/**
 * <<Control>>,
 * ha il compito di gestire i vari input degli utenti e assegnarli il metodo adeguato
*/
public class StringControl {
	private int turno;
	private int exitcode;
	private int esito = 0;
	private String mossa = null;
	private String input = null;
	private static final int MAX_PEDINA = 12;
	private static final int MAX_DIM = 8;
	private int[] presebianco = new int[MAX_PEDINA];
	private int[] presenero = new int[MAX_PEDINA];
	private int[][] matGioc = new int[MAX_DIM][MAX_DIM];
	private List<String> elencomosse = new ArrayList();
	private Output out = new Output();
	private Cronometro cronometrobianco = new Cronometro();
	private Cronometro cronometronero = new Cronometro();
	private Inizializza inizializza = new Inizializza();
	private BackendDama backend = new BackendDama();
	private BackendPezziControl backendpezzi = new BackendPezziControl();
	private Numerata numerata = new Numerata();
	private int[] pb = new int[presebianco.length];
	private int[] pn = new int[presebianco.length];

	/**
	 * Costruttore della classe
	 */
	public StringControl(final String inpt, final int exitcodein, final int turnoin) {
    	this.exitcode = exitcodein;
    	this.turno = turnoin;
    	this.input = inpt;
    	this.matGioc = inizializza.inizializzaPezzi();
    	this.cronometrobianco.azzera();
    	this.cronometronero.azzera();
    }

	/**
	 * Metodo che permette il ritorno della variabile turno
	 * @return turno
	 */
	public int getTurno() {
		return turno;
	}

	/**
	 * Metodo che invoca i veri metodi a seconda dell'input dell'utente
	 * deve ne modifica l'exitcode a seconda di quello che l'utete digita
	 * @return exitcode modificato
	 */
	public int inputControl(final String inpt, final int exitcodein) {
		this.input = inpt;
		this.exitcode = exitcodein;
		int turnowork = 0;
		System.arraycopy(presebianco, 0, pb, 0, presebianco.length);
		System.arraycopy(presenero, 0, pn, 0, presenero.length);
		if (input.contains("-")) {
            mossa = input;
            input = "-";
        }
        if (input.contains("x")) {
            mossa = input;
            input = "x";
        }

        switch (input) {
        	//help
    	    case("help"):
    	    	out.helpOut();
    	    break;

    	    case("-help"):
    	    	out.helpOut();
  		  	break;

    	    case("--help"):
    	    	out.helpOut();
    		break;

    	    case("-h"):
    	    	out.helpOut();
    		break;

    	    case("--h"):
    	    	out.helpOut();
    		break;

    		//gioca
    		case"gioca":
    			out.giocaOut(exitcode);
    			exitcode = backend.gioca(exitcode, cronometrobianco);
    		break;

    		//abbandona
    		case"abbandona":
    			input = out.abbandonaOut(exitcode, turno);
    			exitcode = backend.abbandona(input, cronometrobianco, cronometronero);
    			if (exitcode == 0) {
    				this.matGioc = inizializza.inizializzaPezzi();
    				elencomosse.clear();
    				presebianco = new int[MAX_PEDINA];
    				presenero = new int[MAX_PEDINA];
    			}
    			this.turno = exitcode;
    		break;

    		//numeri
    		case"numeri":
    			numerata.damieraNumerataOut();
    		break;

    		//tempo
    		case"tempo":
    			out.tempo(exitcode, cronometrobianco, cronometronero);
    		break;

    		//damiera
    		case"damiera":
    			out.damieraOut(exitcode, turno, this.matGioc);
    		break;

    		//comando esci
    		case"esci":
    			input = out.esciOut();
    			exitcode = backend.esci(input, exitcode);
    		break;

    		//comando prese
    		case"prese":
    			out.preseOut(pb, pn);
    		break;

    		//comando lista mosse
    		case"mosse":
    			out.elencoMossa(elencomosse);
    		break;

    		//comando mossa semplice
    		case"-":
    			esito = backendpezzi.controlloMossa(mossa);
    			if (esito == 0) {
    				turnowork = backendpezzi.backendMossa(mossa, cronometrobianco, cronometronero, turno,
    						matGioc, elencomosse);
    				if (this.turno == turnowork) {
    					out.errore();
    				} else {
    					this.turno = turnowork;
    				}
    			} else {
    				out.errore();
    			}
            break;

          //comando presa
			case"x":
    			this.presenero = pn;
    			this.presebianco = pb;
    			esito = backendpezzi.controlloPresa(mossa);
    			if (esito == 0) {
    				turnowork = backendpezzi.backendPresa(mossa, turno, matGioc, elencomosse,
    						presenero, presebianco);
    				if (this.turno == turnowork) {
    					out.errore();
    				}
    				if (this.turno != turnowork && turno % 2 == 0) {
    					this.turno++;
    					cronometrobianco.ferma();
    	            	cronometronero.avanza();
    	            	presebianco = backendpezzi.getPezziMangiatib();
    				} else {
    					if (this.turno != turnowork && turno % 2 != 0) {
    						this.turno++;
    						cronometronero.ferma();
    						cronometrobianco.avanza();
    						presenero = backendpezzi.getPezziMangiatin();
    					}
    				}
    			} else {
    				out.errore();
    			}
            break;

    		// comando di default
    		default: out.defOut();
    	}
    	return exitcode;
    }
}
