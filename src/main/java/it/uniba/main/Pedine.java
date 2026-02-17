package it.uniba.main;
import java.util.List;
/**
 * <<Entity>>
 * La classe Pedine estende la classe astratta Pezzi
 * ha il compito di gestire le mosse e le prese delle pedine semplici
 */
public class Pedine extends Pezzi  {
	private static final int DIM = 8;
	private static final int VUOTO = 3;
	private static final int ESTREMO = 7;
	private static final int NERO = 1;
	private static final int BIANCO = 2;
	private static final int DAMAN = 5;
	private static final int DAMAB = 6;
	private Numerata numerata = new Numerata();
	private DamieraPezzi damiera = new DamieraPezzi();
	private String mossa = null;
	private int i = 0;
	private int j = 0;
	private int controllo;
	private int col;
	private int rig;
	private int col2;
	private int rig2;
	private int rigt;
	private int colt;
	private int colt1;
	private int controllo2;
	private int pos = 0;
	private int des = 0;
	private int turno;
	private int esito = 0;
	private int rigm;
	private int colm;
	private String[] valore;
	private int[][] matNum = numerata.generaNumerata();

	/**
	 * Metodo per settaggio delle varibili
	 */
	private void setVariabili() {
		controllo = 0;
		controllo2 = 0;
		col = 0;
		rig = 0;
		col2 = 0;
		rig2 = 0;
		rigt = 0;
		colt = 0;
		colt1 = 0;
	}

	/**
	 * Modifica damiera con presa
	 * @return esito
	 */
	private int modificaPresa(final int[][] matGioc, final int colmin, final int rigmin,
			final int esitoin) {
		this.esito = esitoin;
		if (controllo == 0 || controllo2 == 0) {
			return 0;
		}
		i = 0;
		while (i < DIM) {
			j = 0;
			while (j < DIM) {
				if (col == j && rig == i) {
					esito = matGioc[colmin][rigmin];
					damiera.eliminaPezzi(matGioc, rig, col, rig2, col2, rigm, colm);
				}
				j++;
			}
			i++;
		}
		return esito;
	}

	private int modificaMossa(final Cronometro cronometrobianco, final Cronometro cronometronero,
			 final int[][] matGioc, final List<String> elencomosse) {
		if (controllo == 0 || controllo2 == 0) {
			return turno;
		} else {
			if (turno % 2 == 0) {
				mossa = ("B. " + mossa + "\n");
				elencomosse.add(mossa);
				turno++;
				cronometrobianco.ferma();
				cronometronero.avanza();
			} else {
				mossa = ("N. " + mossa + "\n");
				elencomosse.add(mossa);
				turno++;
				cronometronero.ferma();
				cronometrobianco.avanza();
			}
		}
		i = 0;
		controllo = 0;
		while (i < DIM) {
			j = 0;
			while (j < DIM) {
				if (col == j && rig == i) {
					damiera.spostaPezzi(matGioc, rig, col, rig2, col2);
			}
				j++;
			}
			i++;
		}
		return turno;
	}
	/**
	 * Metodo, per effettuare la mossa semplice della pedina nera
	 * @return turno aggiornato
	 */
	@Override
	public int mossaSempliceNero(final String mossatemp, final Cronometro cronometrobianco,
			final Cronometro cronometronero, final int incrturno,
			final int[][] matGioc, final List<String> elencomosse) {
		this.turno = incrturno;
		this.mossa = mossatemp;
		valore = mossa.split("-");
		pos = Integer.parseInt(valore[0]);
		des = Integer.parseInt(valore[1]);
		setVariabili();
		i = 0;
		while (i < DIM) {
			j = 0;
			while (j < DIM) {
				if (matNum[j][i] == pos) {
					if (matGioc[j][i] == NERO) {
						if (j == 0) {
							rigt = i + 1;
							colt1 = j + 1;
							if (matGioc[colt1][rigt] == VUOTO) {
								col = j;
								rig = i;
								controllo = 1;
							}
						} else {
							if (j == ESTREMO) {
								rigt = i + 1;
								colt = j - 1;
								try {
								if (matGioc[colt][rigt] == VUOTO) {
									col = j;
									rig = i;
									controllo = 1;
								}
							} catch (ArrayIndexOutOfBoundsException e) {
								controllo = 0;
							}
							} else {
								rigt = i + 1;
								colt = j - 1;
								colt1 = j + 1;
								try {
									if (matGioc[colt][rigt] == VUOTO
									|| matGioc[colt1][rigt] == VUOTO) {
										col = j;
										rig = i;
										controllo = 1;
									}
								} catch (ArrayIndexOutOfBoundsException e) {
									controllo = 0;
								}
							}
						}
					}
				}
				j++;
			}
			i++;
		}
		rigt = 0;
		colt = 0;
		colt1 = 0;
		i = 0;
		while (i < DIM) {
			j = 0;
			while (j < DIM) {
				if (matNum[j][i] == pos) {
					if (matGioc[j][i] == NERO) {
						if (j == 0) {
							rigt = i + 1;
							colt1 = j + 1;
							if (matNum[colt1][rigt] == des
									&& matGioc[colt1][rigt] == VUOTO) {
								col2 = colt1;
								rig2 = rigt;
								controllo2 = 1;
							}
						} else {
							if (j == ESTREMO) {
								rigt = i + 1;
								colt = j - 1;
								try {
								if (matNum[colt][rigt] == des
									&& matGioc[colt][rigt] == VUOTO) {
									col2 = colt;
									rig2 = rigt;
									controllo2 = 1;
								}
								} catch (ArrayIndexOutOfBoundsException e) {
									controllo2 = 0;
								}
							} else {
								rigt = i + 1;
								colt = j - 1;
								colt1 = j + 1;
								try {
								if (matNum[colt][rigt] == des
										&& matGioc[colt][rigt] == VUOTO) {
									col2 = colt;
									rig2 = rigt;
									controllo2 = 1;
								} else {
									if (matNum[colt1][rigt] == des
											&&
											matGioc[colt1][rigt] == VUOTO) {
										col2 = colt1;
										rig2 = rigt;
										controllo2 = 1;
									}
								}
							} catch (ArrayIndexOutOfBoundsException e) {
								controllo = 0;
							}
							}
						}
						if (rigt == ESTREMO) {
							damiera.damatura(matGioc, rig, col, DAMAN);

						}
					}
				}
				j++;
			}
			i++;
		}
		return  modificaMossa(cronometrobianco, cronometronero, matGioc, elencomosse);
	}

	/**
	 * Questo metodo si occupa della mossa della pedina semplice bianca
	 * e dà di ritorno la variabile turno
	 * @return turno
	 */
	@Override
	public int mossaSempliceBianco(final String mossatemp, final Cronometro cronometrobianco,
			final Cronometro cronometronero, final int incrturno,
			final int[][] matGioc, final List<String> elencomosse) {
		this.turno = incrturno;
		this.mossa = mossatemp;
		valore = mossa.split("-");
		pos = Integer.parseInt(valore[0]);
		des = Integer.parseInt(valore[1]);
		setVariabili();
		i = 0;
		while (i < DIM) {
			j = 0;
			while (j < DIM) {
				if (matNum[j][i] == pos) {
					if (matGioc[j][i] == BIANCO) {
						if (j == 0) {
							rigt = i - 1;
							colt1 = j + 1;
							try {
								if (matGioc[colt1][rigt] == VUOTO) {
									col = j; rig = i;
									controllo = 1;
								}
							} catch (ArrayIndexOutOfBoundsException e) {
								controllo = 0;
							}
						} else {
							if (j == ESTREMO) {
								rigt = i - 1; colt = j - 1;
								if (matGioc[colt][rigt] == VUOTO) {
									col = j;
									rig = i;
									controllo = 1;
								}
							} else {
							rigt = i - 1; colt = j - 1; colt1 = j + 1;
								try {
									if (matGioc[colt1][rigt] == VUOTO) {
										col = j;
										rig = i;
										controllo = 1;
									} else {
										if (matGioc[colt][rigt] == VUOTO) {
											col = j;
											rig = i;
											controllo = 1;
										}
									}
								} catch (ArrayIndexOutOfBoundsException e) {
									controllo = 0;
								}
							}
						}
					}
				}
				j++;
			}
			i++;
		}
		rigt = 0; colt = 0; colt1 = 0;
		i = 0;
		while (i < DIM) {
			j = 0;
			while (j < DIM) {
				if (matNum[j][i] == pos) {
					if (matGioc[j][i] == BIANCO) {
						if (j == 0) {
							rigt = i - 1;
							colt1 = j + 1;
							try {
								if (matNum[colt1][rigt] == des
								&& matGioc[colt1][rigt] == VUOTO) {
									col2 = colt1;
									rig2 = rigt;
									controllo2 = 1;
								}
							} catch (ArrayIndexOutOfBoundsException e) {
								controllo2 = 0;
							}
						} else {
							if (j == ESTREMO) {
								rigt = i - 1;
								colt = j - 1;
								try {
									if (matNum[colt][rigt] == des
									&& matGioc[colt][rigt] == VUOTO) {
										col2 = colt;
										rig2 = rigt;
										controllo2 = 1;
									} else {
										rigt = i - 1;
										colt = j - 1;
										colt1 = j + 1;
										if (matNum[colt][rigt] == des
										&& matGioc[colt][rigt] == VUOTO) {
											col2 = colt;
											rig2 = rigt;
											controllo2 = 1;
										} else {
										  if (matNum[colt1][rigt] == des
										  && matGioc[colt1][rigt] == VUOTO) {
												col2 = colt1;
												rig2 = rigt;
												controllo2 = 1;
											}
										}
									}
								} catch (ArrayIndexOutOfBoundsException e) {
									controllo2 = 0;
								}
							} else {
								rigt = i - 1;
								colt = j - 1;
								colt1 = j + 1;
								try {
									if (matNum[colt][rigt] == des
									&& matGioc[colt][rigt] == VUOTO) {
										col2 = colt;
										rig2 = rigt;
										controllo2 = 1;
									} else {
										if (matNum[colt1][rigt] == des
										&& matGioc[colt1][rigt] == VUOTO) {
											col2 = colt1;
											rig2 = rigt;
											controllo2 = 1;
										} else {
										  rigt = i - 1;
										  colt = j - 1;
										  if (matNum[colt][rigt] == des
										  && matGioc[colt][rigt] == VUOTO) {
												col2 = colt;
												rig2 = rigt;
												controllo2 = 1;
											}
										}
									}
								} catch (ArrayIndexOutOfBoundsException e) {
									controllo2 = 0;
								}
							}
						}
					}
					if (rigt == 0 && controllo2 == 1) {
						damiera.damatura(matGioc, rig, col, DAMAB);
					}
				}
				j++;
			}
			i++;
		}
		return  modificaMossa(cronometrobianco, cronometronero, matGioc, elencomosse);
	}
	/**
	 * Questo metodo si occupa della presa della pedina semplice nera
	 * e dà di ritorno la variabile turno
	 * @return esito
	 */
	@Override
	public int presaNero(final int desin, final int posin,
			final int[][] matGioc, final String mossatemp) {
		this.des = desin;
		this.pos = posin;
		this.mossa = mossatemp;
		esito = 0;
		setVariabili();
		i = 0;
		rigm = 0;
		colm = 0;
		while (i < DIM) {
			j = 0;
			while (j < DIM) {
				if (matNum[j][i] == pos) {
					if (matGioc[j][i] == NERO) {
						if (j == 0) {
							rigt = i + 1; colt1 = j + 1;
							if (matGioc[colt1][rigt] == BIANCO
								&& controllo2 == 0) {
								col = j;
								rig = i;
								rigm = rigt;
								colm = colt1;
								controllo = 1;
								rigt = rigt + 1;
								colt1 = colt1 + 1;
								try {
								if (matNum[colt1][rigt] == des
								&& matGioc[colt1][rigt] == VUOTO) {
									col2 = colt1;
									rig2 = rigt;
									controllo2 = 1;
									if (rigt == ESTREMO) {
									  damiera.damatura(matGioc, rig, col, DAMAN);
									 }
								} else {
									rigt = rigt - 1;
									colt1 = colt1 - 1;
									if (rigt == ESTREMO) {
									  damiera.damatura(matGioc, rig, col, DAMAN);
									}
								}
								} catch (ArrayIndexOutOfBoundsException e) {
									controllo2 = 0;
								}
							}
						}
						if (j == ESTREMO) {
							rigt = i + 1; colt = j - 1;
							if (matGioc[colt][rigt] == BIANCO
								&& controllo2 == 0) {
								col = j; rig = i;
								rigm = rigt;
								colm = colt;
								controllo = 1;
								rigt = rigt + 1;
								colt = colt - 1;
								if (matNum[colt][rigt] == des
										&& matGioc[colt][rigt] == VUOTO) {
									col2 = colt;
									rig2 = rigt;
									controllo2 = 1;
									if (rigt == ESTREMO) {
									  damiera.damatura(matGioc, rig, col, DAMAN);
									}
								} else {
									rigt = rigt - 1;
									colt = colt + 1;
									if (rigt == ESTREMO) {
									 damiera.damatura(matGioc, rig, col, DAMAN);
									}
								}
							}
						} else {
							rigt = i + 1;
							if (j == 0) {
								colt = j;
							} else {
								colt = j - 1;
								}
							colt1 = j + 1;
						try {
							if (matGioc[colt][rigt] == BIANCO
									&& matNum[colt - 1][rigt + 1] == des
									&& controllo2 == 0) {
								col = j;
								rig = i;
								rigm = rigt;
								colm = colt;
								controllo = 1;
								rigt = rigt + 1;
								colt = colt - 1;
								if (matNum[colt][rigt] == des
									&& matGioc[colt][rigt] == VUOTO) {
									col2 = colt;
									rig2 = rigt;
									controllo2 = 1;
									if (rigt == ESTREMO) {
									 damiera.damatura(matGioc, rig, col, DAMAN);
									}
								} else {
									rigt = rigt - 1;
									colt = colt + 1;
									if (rigt == ESTREMO) {
									  damiera.damatura(matGioc, rig, col, DAMAN);
									}
								}
							} else {
							  try {
								if (matGioc[colt1][rigt] == BIANCO
								  && matNum[colt1 + 1][rigt + 1] == des
								  && controllo2 == 0) {
									col = j; rig = i;
									rigm = rigt;
									colm = colt1;
									controllo = 1;
									rigt = rigt + 1;
									colt1 = colt1 + 1;
									if (matNum[colt1][rigt] == des
										&& matGioc[colt1][rigt] == VUOTO) {
										col2 = colt1;
										rig2 = rigt;
										controllo2 = 1;
										if (rigt == ESTREMO) {
										 damiera.damatura(matGioc, rig,
												 col, DAMAN);
										}
									} else {
										rigt = rigt - 1; colt1 = colt1 - 1;
										if (rigt == ESTREMO) {
										 damiera.damatura(matGioc, rig,
												 col, DAMAN);
										}
									}
								}
							  } catch (ArrayIndexOutOfBoundsException e) {
									controllo = 0;
							  }
							}
						}  catch (ArrayIndexOutOfBoundsException e) {
							controllo = 0;
						}
						}
					}
				}
				j++;
			}
			i++;
		}
		return modificaPresa(matGioc, colm, rigm, esito);
	}

	/**
	 * Questo metodo si occupa della presa della pedina semplice bianco
	 * e dà di ritorno la variabile turno
	 * @return esito
	 */
	@Override
	public int presaBianco(final int desin, final int posin,
			final int[][] matGioc, final String mossatemp) {
		this.mossa = mossatemp;
		this.des = desin;
		this.pos = posin;
		setVariabili();
	    i = 0;
	    rigm = 0;
	    colm = 0;
		while (i < DIM) {
			j = 0;
			while (j < DIM) {
				if (matNum[j][i] == pos) {
					if (matGioc[j][i] == BIANCO) {
						if (j == 0) {
							rigt = i - 1;
							colt1 = j + 1;
							if (matGioc[colt1][rigt] == NERO) {
								col = j;
								rig = i;
								rigm = rigt;
								colm = colt1;
								controllo = 1;
								rigt = rigt - 1; colt1 = colt1 + 1;
								if (matNum[colt1][rigt] == des
										&& matGioc[colt1][rigt] == VUOTO) {
								col2 = colt1;
								rig2 = rigt;
								controllo2 = 1;
								if (rigt == 0) {
								  damiera.damatura(matGioc, rig, col, DAMAB);
								}
							} else {
								rigt = rigt + 1;
								colt1 = colt1 - 1;
								if (rigt == 0) {
								  damiera.damatura(matGioc, rig, col, DAMAB);
								}
							}
						}
					} else {
						if (j == ESTREMO) {
							rigt = i - 1;
							colt = j - 1;
							if (matGioc[colt][rigt] == NERO) {
								col = j;
								rig = i;
								rigm = rigt;
								colm = colt;
								controllo = 1;
								rigt = rigt - 1;
								colt = colt - 1;
								try {
								if (matNum[colt][rigt] == des
									&& matGioc[colt][rigt] == VUOTO) {
									col2 = colt;
									rig2 = rigt;
									controllo2 = 1;
									if (rigt == 0) {
									  damiera.damatura(matGioc, rig, col, DAMAB);
									}
								} else {
									rigt = rigt + 1;
									colt = colt + 1;
									if (rigt == 0) {
									  damiera.damatura(matGioc, rig, col, DAMAB);
									}
								}
								} catch (ArrayIndexOutOfBoundsException e) {
									controllo2 = 0;
								}
							}
						} else {
							rigt = i - 1;
							colt = j - 1;
							colt1 = j + 1;
							try {
							if (matGioc[colt][rigt] == NERO
								&& matNum[colt - 1][rigt - 1] == des
								&& controllo2 == 0) {
								col = j;
								rig = i;
								rigm = rigt;
								colm = colt;
								controllo = 1;
								rigt = rigt - 1;
								colt = colt - 1;
								if (matNum[colt][rigt] == des
									&& matGioc[colt][rigt] == VUOTO) {
									col2 = colt;
									rig2 = rigt;
									controllo2 = 1;
									if (rigt == 0) {
									  damiera.damatura(matGioc, rig, col, DAMAB);
									}
								} else {
									rigt = rigt + 1;
									colt = colt + 1;
									if (rigt == 0) {
									  damiera.damatura(matGioc, rig, col, DAMAB);
									}
								}
							} else {
								if (matGioc[colt1][rigt] == NERO
									&& matNum[colt1 + 1][rigt - 1] == des
									&& controllo2 == 0) {
									col = j;
									rig = i;
									rigm = rigt;
									colm = colt1;
									controllo = 1;
									rigt = rigt - 1;
									colt1 = colt1 + 1;
									if (matNum[colt1][rigt] == des
										&& matGioc[colt1][rigt] == VUOTO) {
										col2 = colt1;
										rig2 = rigt;
										controllo2 = 1;
										if (rigt == 0) {
										 damiera.damatura(matGioc, rig,
												 col, DAMAB);
										}
									} else {
										rigt = rigt + 1;
										colt1 = colt1 - 1;
										if (rigt == 0) {
										 damiera.damatura(matGioc, rig,
												 col, DAMAB);
											}
										}
									}
								}
							} catch (ArrayIndexOutOfBoundsException e) {
								controllo = 0;
							}
							}
						}
					}
				}
				j++;
			}
			i++;
		}
		return modificaPresa(matGioc, colm, rigm, esito);
	}
}
