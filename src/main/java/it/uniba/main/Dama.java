package it.uniba.main;

import java.util.List;
/**
 * <<Entity>>,
 * classe che gestisce dama
 *
 */
public class Dama extends Pezzi {
	private static final int DIM = 8;
	private static final int VUOTO = 3;
	private static final int ESTREMODESTRO = 7;
	private static final int DESTRO = 6;
	private static final int ESTREMOSINISTRO = 0;
	private static final int SINISTRO = 1;
	private static final int NERO = 1;
	private static final int BIANCO = 2;
	private static final int DAMAN = 5;
	private static final int DAMAB = 6;
	private int trovato = 0;
	private Numerata numerata = new Numerata();
	private DamieraPezzi damiera = new DamieraPezzi();
	private String mossa;
	private int i;
	private int j;
	private int controllo;
	private int col;
	private int rig;
	private int col2;
	private int rig2;
	private int rigt;
	private int colt;
	private int colt1;
	private int rigt1;
	private int rigm;
	private int colm;
	private int controllo2;
	private int pos = 0;
	private int des = 0;
	private int turno;
	private int esito;
	private String[] valore;
	private final int[][] matNum = numerata.generaNumerata();

	/**
	 * Setta variabili
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
		rigt1 = 0;
		colm = 0;
		rigm = 0;
	}

	/**
	 * Setta variabili ricerca
	 */
	private void setVariabiliSearch() {
		rigt = 0;
	    colt = 0;
	    colt1 = 0;
	    rigt1 = 0;
		trovato = 0;
	}

	/**
	 * Metodo modifica damiera dopo mossa
	 * @return turno aggiornato
	 */
	private int modificaMossa(final Cronometro cronometrobianco, final Cronometro cronometronero,
			 final int[][] matGioc, final List<String> elencomosse) {
		if (controllo == 0 || controllo2 == 0) {
			return turno;
		} else {
			if (turno % 2 == 0) {
				mossa = ("B. " + mossa + "\n");
				elencomosse.add(mossa);
				cronometrobianco.ferma();
				cronometronero.avanza();
				turno = turno + 1;
			} else {
				mossa = ("N. " + mossa + "\n");
				elencomosse.add(mossa);
				cronometronero.ferma();
				cronometrobianco.avanza();
				turno = turno + 1;
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
	 * Metodo modifica damiera dopo presa
	 * @return pezzo mangiato
	 */
	private int modificaPresa(final int[][] matGioc) {
		if (controllo == 0 || controllo2 == 0) {
			return 0;
		}
		i = 0;
		controllo = 0;
		while (i < DIM) {
			j = 0;
			while (j < DIM) {
				if (col == j && rig == i) {
					esito = matGioc[colm][rigm];
					damiera.eliminaPezzi(matGioc, rig, col, rig2, col2, rigm, colm);
				}
				j++;
			}
			i++;
		}
		return esito;
	}

	/**
	 * Metodo Sposta Dama Nera
	 * @return matrice aggiornata
	 */
	private int[][]spostaSemplice(final int[][] matGioc, final int tipo) {
		if (matNum[j][i] == pos) {
			if (matGioc[j][i] == tipo) {
				if (j == ESTREMOSINISTRO) {
					if (i == ESTREMOSINISTRO) {
						setVariabiliSearch();
						rigt = i;
						colt1 = j;
						try {
						  while (matNum[colt1][rigt] != des && trovato == 0
								  && controllo2 == 0) {
							  rigt = rigt + 1;
							  colt1 = colt1 + 1;
							  if (matGioc[colt1][rigt] == VUOTO) {
								  trovato = 0;
							  } else {
								  trovato = 1;
							  }
							}
						} catch (ArrayIndexOutOfBoundsException e) {
							trovato = 1;
						}
						if (trovato == 0) {
							col2 = colt1;
							rig2 = rigt;
							controllo2 = 1;
						}
					} else {
						if (i == ESTREMODESTRO) {
							setVariabiliSearch();
							colt1 = j;
							rigt1 = i;
							try {
							  while (matNum[colt1][rigt1] != des
								&& trovato == 0 && controllo2 == 0) {
								rigt1 = rigt1 - 1;
								colt1 = colt1 + 1;
								if (matGioc[colt1][rigt1] == VUOTO) {
									trovato = 0;
								} else {
									trovato = 1;
								}
							  }
							} catch (ArrayIndexOutOfBoundsException e) {
								trovato = 1;
							}
							if (trovato == 0) {
								col2 = colt1;
								rig2 = rigt1;
								controllo2 = 1;
							}
						} else {
							setVariabiliSearch();
							rigt = i;
							colt1 = j;
							try {
							  while (matNum[colt1][rigt] != des
								&& trovato == 0 && controllo2 == 0) {
								rigt = rigt + 1;
								colt1 = colt1 + 1;
								if (matGioc[colt1][rigt] == VUOTO) {
									trovato = 0;
								} else {
									trovato = 1;
								}
							  }
							} catch (ArrayIndexOutOfBoundsException e) {
								trovato = 1;
							}
							if (trovato == 0) {
								col2 = colt1;
								rig2 = rigt;
								controllo2 = 1;
							} else {
								setVariabiliSearch();
								colt1 = j;
								rigt1 = i;
								try {
								  while (matNum[colt1][rigt1] != des
									&& trovato == 0 && controllo2 == 0) {
									rigt1 = rigt1 - 1;
									colt1 = colt1 + 1;
									if (matGioc[colt1][rigt1] == VUOTO) {
										trovato = 0;
									} else {
										trovato = 1;
									}
								  }
								} catch (ArrayIndexOutOfBoundsException e) {
									trovato = 1;
								}
								if (trovato == 0) {
									col2 = colt1;
									rig2 = rigt1;
									controllo2 = 1;
								}
							}
						}
					}
				} else {
					if (j == ESTREMODESTRO) {
						if (i == ESTREMOSINISTRO) {
							setVariabiliSearch();
							rigt = i;
							colt1 = j;
							try {
							  while (matNum[colt1][rigt] != des
								&& trovato == 0 && controllo2 == 0) {
								rigt = rigt + 1;
								colt1 = colt1 - 1;
								if (matGioc[colt1][rigt] == VUOTO) {
									trovato = 0;
								} else {
									trovato = 1;
								}
							  }
							} catch (ArrayIndexOutOfBoundsException e) {
								trovato = 1;
							}
							if (trovato == 0) {
								col2 = colt1;
								rig2 = rigt;
								controllo2 = 1;
							}
						} else {
							if (i == ESTREMODESTRO) {
								setVariabiliSearch();
								colt1 = j;
								rigt1 = i;
								try {
								  while (matNum[colt1][rigt1] != des
								    && trovato == 0 && controllo2 == 0) {
									  rigt1 = rigt1 - 1;
									  colt1 = colt1 - 1;
									  if (matGioc[colt1][rigt1] == VUOTO) {
										  trovato = 0;
									  } else {
										  trovato = 1;
									  }
								  }
								} catch (ArrayIndexOutOfBoundsException e) {
									trovato = 1;
								}
								if (trovato == 0) {
									col2 = colt1;
									rig2 = rigt1;
									controllo2 = 1;
								}
							} else {
								setVariabiliSearch();
								rigt = i; //+1
								colt1 = j; //-1
								try {
								  while (matNum[colt1][rigt] != des && trovato == 0
										  && controllo2 == 0) {
									  rigt = rigt + 1;
									  colt1 = colt1 - 1;
									  if (matGioc[colt1][rigt] == VUOTO) {
										  trovato = 0;
									  } else {
										  trovato = 1;
									  }
								   }
								} catch (ArrayIndexOutOfBoundsException e) {
									trovato = 1;
								}
								if (trovato == 0) {
									col2 = colt1;
									rig2 = rigt;
									controllo2 = 1;
								} else {
									setVariabiliSearch();
									colt1 = j;
									rigt1 = i;
									try {
									  while (matNum[colt1][rigt1] != des
									&& trovato == 0 && controllo2 == 0) {
										  rigt1 = rigt1 - 1;
										  colt1 = colt1 - 1;
										  if (matGioc[colt1][rigt1] == VUOTO) {
											  trovato = 0;
										  } else {
											trovato = 1;
										  }
									  }
									} catch (ArrayIndexOutOfBoundsException e) {
										trovato = 1;
									}
									if (trovato == 0) {
										col2 = colt1;
										rig2 = rigt1;
										controllo2 = 1;
									}
								}
							}
						}
					} else {
						if (i == ESTREMOSINISTRO) { // qui
							setVariabiliSearch();
							rigt = i;
							colt1 = j;
							try {
							  while (matNum[colt1][rigt] != des && trovato == 0) {
								  rigt = rigt + 1;
								  colt1 = colt1 + 1;
								  if (matGioc[colt1][rigt] == VUOTO
									&& matGioc[colt1 + 1][rigt + 1] == VUOTO) {
									  trovato = 0;
								  } else {
									  trovato = 1;
								}
							  }
							} catch (ArrayIndexOutOfBoundsException e) {
								trovato = 1;
							}
								if (trovato == 0) {
									col2 = colt1;
									rig2 = rigt;
									controllo2 = 1;
								} else {
									setVariabiliSearch();
									colt = j;
									rigt = i;
									try {
									  while (matNum[colt][rigt] != des
									  && trovato == 0 && controllo2 == 0) {
										rigt = rigt + 1;
										colt = colt - 1;
										if (matGioc[colt][rigt] == VUOTO) {
											trovato = 0;
										} else {
											trovato = 1;
										}
									  }
									} catch (ArrayIndexOutOfBoundsException e) {
										trovato = 1;
									}
									if (trovato == 0) {
										col2 = colt;
										rig2 = rigt;
										controllo2 = 1;
									}
								}
						} else {
							if (i == ESTREMODESTRO) {
								setVariabiliSearch();
								colt1 = j;
								rigt1 = i;
								try {
								  while (matNum[colt1][rigt1] != des
									&& trovato == 0 && controllo2 == 0) {
									rigt1 = rigt1 - 1;
									colt1 = colt1 + 1;
									if (matGioc[colt1][rigt1] == VUOTO) {
										trovato = 0;
									} else {
										trovato = 1;
									}
								  }
								} catch (ArrayIndexOutOfBoundsException e) {
									trovato = 1;
								}
								if (trovato == 0) {
									col2 = colt1;
									rig2 = rigt1;
									controllo2 = 1;
								} else {
									setVariabiliSearch();
									colt = j;
									rigt1 = i;
									try {
									  while (matNum[colt][rigt1] != des
									  && trovato == 0 && controllo2 == 0) {
										  rigt1 = rigt1 - 1;
										  colt = colt - 1;
										  if (matGioc[colt][rigt1] == VUOTO) {
											  trovato = 0;
										  } else {
												trovato = 1;
										  }
									   }
									} catch (ArrayIndexOutOfBoundsException e) {
										trovato = 1;
									}
									if (trovato == 0) {
										col2 = colt;
										rig2 = rigt1;
										controllo2 = 1;
									}
								}
							} else {
								setVariabiliSearch();
								rigt = i;
								colt1 = j;
								try {
								  while (matNum[colt1][rigt] != des && trovato == 0) {
									  rigt = rigt + 1;
									  colt1 = colt1 + 1;
									  if (matGioc[colt1][rigt] == VUOTO) {
										  trovato = 0;
									  } else {
										  trovato = 1;
									  }
								  }
								} catch (ArrayIndexOutOfBoundsException e) {
											trovato = 1;
								}
								if (trovato == 0) {
									col2 = colt1;
									rig2 = rigt;
									controllo2 = 1;
								} else {
									setVariabiliSearch();
									colt = j;
									rigt = i;
									try {
									  while (matNum[colt][rigt] != des
									  && trovato == 0 && controllo2 == 0) {
									    rigt = rigt + 1;
										colt = colt - 1;
										if (matGioc[colt][rigt] == VUOTO) {
											trovato = 0;
										} else {
										  trovato = 1;
										}
									}
									} catch (ArrayIndexOutOfBoundsException e) {
										trovato = 1;
									}
									if (trovato == 0) {
										col2 = colt;
										rig2 = rigt;
										controllo2 = 1;
									} else {
									  setVariabiliSearch();
									  colt1 = j;
									  rigt1 = i;
									  try {
									   while (matNum[colt1][rigt1] != des
									 && trovato == 0 && controllo2 == 0) {
									     rigt1 = rigt1 - 1;
										 colt1 = colt1 + 1;
										 if (matGioc[colt1][rigt1] == VUOTO) {
											 trovato = 0;
										 } else {
											 trovato = 1;
										 }
									   }
									  } catch (ArrayIndexOutOfBoundsException e) {
										  trovato = 1;
									  }
									  if (trovato == 0) {
										  col2 = colt1;
										  rig2 = rigt1;
										  controllo2 = 1;
									  } else {
										setVariabiliSearch();
										colt = j;
										rigt1 = i;
										try {
										  while (matNum[colt][rigt1] != des
										 && trovato == 0 && controllo2 == 0) {
										    rigt1 = rigt1 - 1;
											colt = colt - 1;
										   if (matGioc[colt][rigt1] == VUOTO) {
												trovato = 0;
											} else {
												trovato = 1;
											}
											}
									    } catch (ArrayIndexOutOfBoundsException e) {
											trovato = 1;
										}
										if (trovato == 0) {
											col2 = colt;
											rig2 = rigt1;
											controllo2 = 1;
										}
									  }
									}
								}
							}
						}
					}
				}
			}
		}
		return matGioc;
	}

	/**
	 * Metodo per la mossa semolplice della dama nera
	 * @retrun tempo aggiornato
	 */
	@Override
	public int mossaSempliceNero(final String mossain, final Cronometro cronobianco,
		final Cronometro crononero, final int turnoin, final int[][] matGioc,
		final List<String> elencomosse) {
		this.mossa = mossain;
		this.turno = turnoin;
		valore = mossa.split("-");
		pos = Integer.parseInt(valore[0]);
		des = Integer.parseInt(valore[1]);
		setVariabili();
		i = 0;
		while (i < DIM) {
			j = 0;
			while (j < DIM) {
				if (matNum[j][i] == pos) {
					if (matGioc[j][i] == DAMAN) {
							if (j == ESTREMOSINISTRO) {
								if (i == ESTREMOSINISTRO) {
									rigt = i + 1;
									colt1 = j + 1;
									if (matGioc[colt1][rigt] == VUOTO) {
										col = j;
										rig = i;
										controllo = 1;
									}
								} else {
									if (i == ESTREMODESTRO) {
										colt1 = j + 1;
										rigt1 = i - 1;
										if (matGioc[colt1][rigt1] == VUOTO) {
											col = j;
											rig = i;
											controllo = 1;
										}
									} else {
										rigt = i + 1;
										colt1 = j + 1;
										rigt1 = i - 1;
										if (matGioc[colt1][rigt] == VUOTO
										|| matGioc[colt1][rigt1] == VUOTO) {
											col = j;
											rig = i;
											controllo = 1;
										}
									}
								}
							} else {
								if (j == ESTREMODESTRO) {
									if (i == ESTREMOSINISTRO) {
										rigt = i + 1;
										colt1 = j - 1;
										if (matGioc[colt1][rigt] == VUOTO) {
											col = j;
											rig = i;
											controllo = 1;
										}
									} else {
										if (i == ESTREMODESTRO) {
											colt1 = j - 1;
											rigt1 = i - 1;
											if (matGioc[colt1][rigt1]
													== VUOTO) {
												col = j;
												rig = i;
												controllo = 1;
											}
										} else {
											rigt = i + 1;
											colt1 = j - 1;
											rigt1 = i - 1;
										  if (matGioc[colt1][rigt] == VUOTO
										  || matGioc[colt1][rigt1] == VUOTO) {
												col = j;
												rig = i;
												controllo = 1;
											}
										}
									}
								} else {
									if (i == ESTREMOSINISTRO) {
										rigt = i + 1;
										colt = j - 1;
										colt1 = j + 1;
									  if (matGioc[colt][rigt] == VUOTO
										|| matGioc[colt1][rigt] == VUOTO) {
											col = j;
											rig = i;
											controllo = 1;
										}
									} else {
										if (i == ESTREMODESTRO) {
											colt = j - 1;
											colt1 = j + 1;
											rigt1 = i - 1;
										  if (matGioc[colt][rigt1] == VUOTO
										  || matGioc[colt1][rigt1] == VUOTO) {
												col = j;
												rig = i;
												controllo = 1;
											}
										} else {
											rigt = i + 1;
											colt = j - 1;
											colt1 = j + 1;
											rigt1 = i - 1;
										  if (matGioc[colt][rigt] == VUOTO
											|| matGioc[colt][rigt1] == VUOTO
											|| matGioc[colt1][rigt] == VUOTO
										    || matGioc[colt1][rigt1] == VUOTO) {
												col = j;
												rig = i;
												controllo = 1;
											}
										}
									}
								}
							}
						}
					}
					j++;
				}
				i++;
			}
		int[][]matMod = matGioc;
		rigt = 0;
		colt = 0;
		colt1 = 0;
		i = 0;
		while (i < DIM) {
			j = 0;
			while (j < DIM) {
				matMod = spostaSemplice(matGioc, DAMAN);
				j++;
			}
			i++;
		}
		return modificaMossa(cronobianco, crononero, matMod, elencomosse);
	}

	/**
	 * Metodo per la mossa semolplice della dama binaca
	 * @retrun tempo aggiornato
	 */
	@Override
	public int mossaSempliceBianco(final String mossain, final Cronometro cronobianco, final Cronometro crononero,
			final int turnoin, final int[][] matGioc, final List<String> elencomosse) {
		this.mossa = mossain;
		this.turno = turnoin;
		valore = mossa.split("-");
		pos = Integer.parseInt(valore[0]);
		des = Integer.parseInt(valore[1]);
		setVariabili();
		i = 0;
		while (i < DIM) {
			j = 0;
			while (j < DIM) {
				if (matNum[j][i] == pos) {
					if (matGioc[j][i] == DAMAB) {
						if (j == ESTREMOSINISTRO) {
							if (i == ESTREMOSINISTRO) {
								rigt = i + 1;
								colt1 = j + 1;
								if (matGioc[colt1][rigt] == VUOTO) {
									col = j;
									rig = i;
									controllo = 1;
								}
							} else {
								if (i == ESTREMODESTRO) {
									colt1 = j + 1;
									rigt1 = i - 1;
									if (matGioc[colt1][rigt1] == VUOTO) {
										col = j;
										rig = i;
										controllo = 1;
									}
								} else {
									rigt = i + 1;
									colt1 = j + 1;
									rigt1 = i - 1;
									if (matGioc[colt1][rigt] == VUOTO
									|| matGioc[colt1][rigt1] == VUOTO) {
										col = j;
										rig = i;
										controllo = 1;
									}
								}
							}
						} else {
							if (j == ESTREMODESTRO) {
								if (i == ESTREMOSINISTRO) {
									rigt = i + 1;
									colt1 = j - 1;
									if (matGioc[colt1][rigt] == VUOTO) {
										col = j;
										rig = i;
										controllo = 1;
									}
								} else {
									if (i == ESTREMODESTRO) {
										colt1 = j - 1;
										rigt1 = i - 1;
										if (matGioc[colt1][rigt1] == VUOTO) {
											col = j;
											rig = i;
											controllo = 1;
										}
									} else {
										rigt = i + 1;
										colt1 = j - 1;
										rigt1 = i - 1;
										if (matGioc[colt1][rigt] == VUOTO
										|| matGioc[colt1][rigt1] == VUOTO) {
											col = j;
											rig = i;
											controllo = 1;
										}
									}
								}
							} else {
								if (i == ESTREMOSINISTRO) {
									rigt = i + 1;
									colt = j - 1;
									colt1 = j + 1;
									if (matGioc[colt][rigt] == VUOTO
									|| matGioc[colt1][rigt] == VUOTO) {
										col = j;
										rig = i;
										controllo = 1;
									}
								} else {
									if (i == ESTREMODESTRO) {
										colt = j - 1;
										colt1 = j + 1;
										rigt1 = i - 1;
										if (matGioc[colt][rigt1] == VUOTO
										|| matGioc[colt1][rigt1] == VUOTO) {
											col = j;
											rig = i;
											controllo = 1;
										}
									} else {
										rigt = i + 1;
										colt = j - 1;
										colt1 = j + 1;
										rigt1 = i - 1;
										if (matGioc[colt][rigt] == VUOTO
										|| matGioc[colt][rigt1] == VUOTO
										|| matGioc[colt1][rigt] == VUOTO
										|| matGioc[colt1][rigt1] == VUOTO) {
											col = j;
											rig = i;
											controllo = 1;
										}
									}
								}
							}
						}
					}
				}
				j++;
			}
			i++;
		}
		int[][]matMod = matGioc;
		rigt = 0;
		colt = 0;
		colt1 = 0;
		i = 0;
		while (i < DIM) {
			j = 0;
			while (j < DIM) {
				matMod = spostaSemplice(matGioc,  DAMAB);
				j++;
			}
			i++;
		}
		return modificaMossa(cronobianco, crononero, matMod, elencomosse);
	}
	/**
	 * Metodo per la prese della dama nera
	 * @return valore pezzo mangiato
	 */
	@Override
	public int presaNero(final int desin, final int posin, final int[][] matGioc,
			final String mossain) {
		this.des = desin;
		this.pos = posin;
		this.mossa = mossain;
		setVariabili();
		i = 0;
		while (i < DIM) {
			j = 0;
			while (j < DIM) {
				if (matNum[j][i] == pos) {
					if (matGioc[j][i] == DAMAN) {
						if (j == ESTREMOSINISTRO || j == SINISTRO) {
							if (i == ESTREMOSINISTRO || i == SINISTRO) {
								rigt = i + 1;
								colt1 = j + 1;
								if ((matGioc[colt1][rigt] == BIANCO
									|| matGioc[colt1][rigt] == DAMAB)
									&& controllo2 == 0) {
									col = j;
									rig = i;
									rigm = rigt;
									colm = colt1;
									controllo = 1;
									rigt++;
									colt1++;
									if (matNum[colt1][rigt] == des
									&& matGioc[colt1][rigt] == VUOTO) {
										col2 = colt1;
										rig2 = rigt;
										controllo2 = 1;
									} else {
										rigt--;
										colt1--;
									}
								}
							} else {
								if (i == DESTRO || i == ESTREMODESTRO) {
									colt1 = j + 1;
									rigt1 = i - 1;
									if ((matGioc[colt1][rigt1] == BIANCO
										|| matGioc[colt1][rigt1] == DAMAB)
										&& controllo2 == 0) {
										col = j;
										rig = i;
										rigm = rigt1;
										colm = colt1;
										controllo = 1;
										colt1++;
										rigt1--;
										if (matNum[colt1][rigt1] == des
										&& matGioc[colt1][rigt1] == VUOTO) {
											col2 = colt1;
											rig2 = rigt1;
											controllo2 = 1;
										} else {
											colt1--;
											rigt1++;
										}
									}
								} else {
									rigt = i + 1;
									colt1 = j + 1;
									rigt1 = i - 1;
									if ((matGioc[colt1][rigt] == BIANCO
										|| matGioc[colt1][rigt] == DAMAB)
											&& controllo2 == 0) {
										col = j;
										rig = i;
										rigm = rigt;
										colm = colt1;
										controllo = 1;
										rigt++;
										colt1++;
										if (matNum[colt1][rigt] == des
										&& matGioc[colt1][rigt] == VUOTO) {
											col2 = colt1;
											rig2 = rigt;
											controllo2 = 1;
										} else {
											rigt--;
											colt1--;
										}
									}
									if ((matGioc[colt1][rigt1] == BIANCO
										|| matGioc[colt1][rigt1] == DAMAB)
										&& controllo2 == 0) {
										col = j;
										rig = i;
										rigm = rigt1;
										colm = colt1;
										controllo = 1;
										colt1++;
										rigt1--;
										if (matNum[colt1][rigt1] == des
										&& matGioc[colt1][rigt1] == VUOTO) {
											col2 = colt1;
											rig2 = rigt1;
											controllo2 = 1;
										} else {
											colt1--;
											rigt1++;
										}
									}
								}
							}
						} else {
							if (j == DESTRO || j == ESTREMODESTRO) {
								if (i == ESTREMOSINISTRO || i == 1) {
									rigt = i + 1;
									colt = j - 1;
									if ((matGioc[colt][rigt] == BIANCO
									|| matGioc[colt][rigt] == DAMAB)
									&& controllo2 == 0) {
										col = j;
										rig = i;
										rigm = rigt;
										colm = colt;
										controllo = 1;
										rigt++;
										colt--;
										if (matNum[colt][rigt] == des
										&& matGioc[colt][rigt] == VUOTO) {
											col2 = colt;
											rig2 = rigt;
											controllo2 = 1;
										} else {
											rigt--;
											colt++;
										}
									}
								} else {
									if (i == DESTRO || i == ESTREMODESTRO) {
										colt = j - 1;
										rigt1 = i - 1;
										if ((matGioc[colt][rigt1] == BIANCO
										|| matGioc[colt][rigt1] == DAMAB)
											&& controllo2 == 0) {
											col = j;
											rig = i;
											rigm = rigt1;
											colm = colt;
											controllo = 1;
											colt--;
											rigt1--;
											if (matNum[colt][rigt1] == des
										  && matGioc[colt][rigt1] == VUOTO) {
												col2 = colt;
												rig2 = rigt1;
												controllo2 = 1;
											} else {
												colt++;
												rigt1++;
											}
										}
									} else {
										rigt = i + 1;
										colt = j - 1;
										rigt1 = i - 1;
										if ((matGioc[colt][rigt] == BIANCO
											|| matGioc[colt][rigt] == DAMAB)
											&& controllo2 == 0) {
											col = j;
											rig = i;
											rigm = rigt;
											colm = colt;
											controllo = 1;
											rigt++;
											colt--;
											if (matNum[colt][rigt] == des
										  && matGioc[colt][rigt] == VUOTO) {
												col2 = colt;
												rig2 = rigt;
												controllo2 = 1;
											} else {
												rigt--;
												colt++;
											}
										}
										if ((matGioc[colt][rigt1] == BIANCO
										|| matGioc[colt][rigt1] == DAMAB)
											&& controllo2 == 0) {
											col = j;
											rig = i;
											rigm = rigt1;
											colm = colt;
											controllo = 1;
											colt--;
											rigt1--;
											if (matNum[colt][rigt1] == des
										&& matGioc[colt][rigt1] == VUOTO) {
												col2 = colt;
												rig2 = rigt1;
												controllo2 = 1;
												} else {
												colt++;
												rigt1++;
											}
										}
									}
								}
							} else
								if (i == ESTREMOSINISTRO || i == SINISTRO) {
									rigt = i + 1;
									colt = j - 1;
									colt1 = j + 1;
									if ((matGioc[colt][rigt] == BIANCO
										|| matGioc[colt][rigt] == DAMAB)
										&& controllo2 == 0) {
										col = j;
										rig = i;
										rigm = rigt;
										colm = colt;
										controllo = 1;
										rigt++;
										colt--;
										if (matNum[colt][rigt] == des
										&& matGioc[colt][rigt] == VUOTO) {
											col2 = colt;
											rig2 = rigt;
											controllo2 = 1;
										} else {
											rigt--;
											colt++;
										}
									}
									if ((matGioc[colt1][rigt] == BIANCO
										|| matGioc[colt1][rigt] == DAMAB)
										&& controllo2 == 0) {
										col = j;
										rig = i;
										rigm = rigt;
										colm = colt1;
										controllo = 1;
										colt1++;
										rigt++;
										if (matNum[colt1][rigt] == des
										&& matGioc[colt1][rigt] == VUOTO) {
											col2 = colt1;
											rig2 = rigt;
											controllo2 = 1;
										} else {
											colt1--;
											rigt--;
										}
									}
								} else {
									if (i == DESTRO || i == ESTREMODESTRO) {
										colt = j - 1;
										colt1 = j + 1;
										rigt1 = i - 1;
										if ((matGioc[colt][rigt1] == BIANCO
										|| matGioc[colt][rigt1] == DAMAB)
										&& controllo2 == 0) {
											col = j;
											rig = i;
											rigm = rigt1;
											colm = colt;
											controllo = 1;
											rigt1--;
											colt--;
											if (matNum[colt][rigt1] == des
										 && matGioc[colt][rigt1] == VUOTO) {
												col2 = colt;
												rig2 = rigt1;
												controllo2 = 1;
											} else {
												rigt1++;
												colt++;
											}
										}
										if ((matGioc[colt1][rigt1] == BIANCO
										|| matGioc[colt1][rigt1] == DAMAB)
										&& controllo2 == 0) {
											col = j;
											rig = i;
											rigm = rigt1;
											colm = colt1;
											controllo = 1;
											colt1++;
											rigt1--;
											if (matNum[colt1][rigt1] == des
										&& matGioc[colt1][rigt1] == VUOTO) {
												col2 = colt1;
												rig2 = rigt1;
												controllo2 = 1;
											} else {
												colt1--;
												rigt1++;
											}
										}
									} else {
										rigt = i + 1;
										colt = j - 1;
										colt1 = j + 1;
										rigt1 = i - 1;
										if ((matGioc[colt][rigt] == BIANCO
											|| matGioc[colt][rigt] == DAMAB)
											&& controllo2 == 0) {
											col = j;
											rig = i;
											rigm = rigt;
											colm = colt;
											controllo = 1;
											rigt++;
											colt--;
											if (matNum[colt][rigt] == des
										  && matGioc[colt][rigt] == VUOTO) {
												col2 = colt;
												rig2 = rigt;
												controllo2 = 1;
											} else {
												rigt--;
												colt++;
											}
										}
										if ((matGioc[colt][rigt1] == BIANCO
										|| matGioc[colt][rigt1] == DAMAB)
										&& controllo2 == 0) {
											col = j;
											rig = i;
											rigm = rigt1;
											colm = colt;
											controllo = 1;
											rigt1--;
											colt--;
											if (matNum[colt][rigt1] == des
										  && matGioc[colt][rigt1] == VUOTO) {
												col2 = colt;
												rig2 = rigt1;
												controllo2 = 1;
											} else {
												rigt1++;
												colt++;
											}
										}
										if ((matGioc[colt1][rigt] == BIANCO
										|| matGioc[colt1][rigt] == DAMAB)
										&& controllo2 == 0) {
											col = j;
											rig = i;
											rigm = rigt;
											colm = colt1;
											controllo = 1;
											colt1++;
											rigt++;
											if (matNum[colt1][rigt] == des
										  && matGioc[colt1][rigt] == VUOTO) {
												col2 = colt1;
												rig2 = rigt;
												controllo2 = 1;
											} else {
												colt1--;
												rigt--;
											}
										}
										if ((matGioc[colt1][rigt1] == BIANCO
										|| matGioc[colt1][rigt1] == DAMAB)
										&& controllo2 == 0) {
											col = j;
											rig = i;
											rigm = rigt1;
											colm = colt1;
											controllo = 1;
											colt1++;
											rigt1--;
											if (matNum[colt1][rigt1] == des
										&& matGioc[colt1][rigt1] == VUOTO) {
												col2 = colt1;
												rig2 = rigt1;
												controllo2 = 1;
											} else {
												colt1--;
												rigt1++;
											}
										}
									}
								}
							}
						}
					}
					j++;
				}
				i++;
			}
		return modificaPresa(matGioc);
	}
	/**
	 * Metodo per la prese della dama bianca
	 * @return valore pezzo mangiato
	 */
	@Override
	public int presaBianco(final int desin, final int posin, final int[][] matGioc,
			final String mossain) {
		this.mossa = mossain;
		this.pos = posin;
		this.des = desin;
		setVariabili();
		i = 0;
		while (i < DIM) {
			j = 0;
			while (j < DIM) {
				if (matNum[j][i] == pos) {
					if (matGioc[j][i] == DAMAB) {
						if (j == ESTREMOSINISTRO || j == SINISTRO) {
							if (i == ESTREMOSINISTRO || i == SINISTRO) {
								rigt = i + 1;
								colt1 = j + 1;
								if ((matGioc[colt1][rigt] == NERO
								|| matGioc[colt1][rigt] == DAMAN) && controllo2 == 0) {
									col = j;
									rig = i;
									rigm = rigt;
									colm = colt1;
									controllo = 1;
									rigt++;
									colt1++;
									if (matNum[colt1][rigt] == des
									&& matGioc[colt1][rigt] == VUOTO) {
										col2 = colt1;
										rig2 = rigt;
										controllo2 = 1;
									} else {
										rigt--;
										colt1--;
									}
								}
							} else {
								if (i == DESTRO || i == ESTREMODESTRO) {
									colt1 = j + 1;
									rigt1 = i - 1;
									if ((matGioc[colt1][rigt1] == NERO
									|| matGioc[colt1][rigt1] == DAMAN)
									&& controllo2 == 0) {
										col = j;
										rig = i;
										rigm = rigt1;
										colm = colt1;
										controllo = 1;
										colt1++;
										rigt1--;
										if (matNum[colt1][rigt1] == des
									&& matGioc[colt1][rigt1] == VUOTO) {
											col2 = colt1;
											rig2 = rigt1;
											controllo2 = 1;
										} else {
											colt1--;
											rigt1++;
										}
									}
								} else {
									rigt = i + 1;
									colt1 = j + 1;
									rigt1 = i - 1;
									if ((matGioc[colt1][rigt] == NERO
									|| matGioc[colt1][rigt] == DAMAN)
									&& controllo2 == 0) {
										col = j;
										rig = i;
										rigm = rigt;
										colm = colt1;
										controllo = 1;
										rigt++;
										colt1++;
										if (matNum[colt1][rigt] == des
										&& matGioc[colt1][rigt] == VUOTO) {
											col2 = colt1;
											rig2 = rigt;
											controllo2 = 1;
										} else {
											rigt--;
											colt1--;
										}
									}
									if ((matGioc[colt1][rigt1] == 1
									|| matGioc[colt1][rigt1] == DAMAN)
									&& controllo2 == 0) {
										col = j;
										rig = i;
										rigm = rigt1;
										colm = colt1;
										controllo = 1;
										colt1++;
										rigt1--;
										if (matNum[colt1][rigt1] == des
										&& matGioc[colt1][rigt1] == VUOTO) {
											col2 = colt1;
											rig2 = rigt1;
											controllo2 = 1;
										} else {
											colt1--;
											rigt1++;
										}
									}
								}
							}
						} else {
							if (j == DESTRO || j == ESTREMODESTRO) {
								if (i == ESTREMOSINISTRO || i == 1) {
									rigt = i + 1;
									colt = j - 1;
									if ((matGioc[colt][rigt] == NERO
									|| matGioc[colt][rigt] == DAMAN)
									&& controllo2 == 0) {
										col = j;
										rig = i;
										rigm = rigt;
										colm = colt;
										controllo = 1;
										rigt++;
										colt--;
										if (matNum[colt][rigt] == des
										&& matGioc[colt][rigt] == VUOTO) {
											col2 = colt;
											rig2 = rigt;
											controllo2 = 1;
										} else {
											rigt--;
											colt++;
										}
									}
								} else {
									if (i == DESTRO || i == ESTREMODESTRO) {
										colt = j - 1;
										rigt1 = i - 1;
										if ((matGioc[colt][rigt1] == NERO
										|| matGioc[colt][rigt1] == DAMAN)
										&& controllo2 == 0) {
											col = j;
											rig = i;
											rigm = rigt1;
											colm = colt;
											controllo = 1;
											colt--;
											rigt1--;
											if (matNum[colt][rigt1] == des
										&& matGioc[colt][rigt1] == VUOTO) {
												col2 = colt;
												rig2 = rigt1;
												controllo2 = 1;
											} else {
												colt++;
												rigt1++;
											}
										}
									} else {
										rigt = i + 1;
										colt = j - 1;
										rigt1 = i - 1;
										if ((matGioc[colt][rigt] == NERO
										|| matGioc[colt][rigt] == DAMAN)
										&& controllo2 == 0) {
											col = j;
											rig = i;
											rigm = rigt;
											colm = colt;
											controllo = 1;
											rigt++;
											colt--;
											if (matNum[colt][rigt] == des
										&& matGioc[colt][rigt] == VUOTO) {
												col2 = colt;
												rig2 = rigt;
												controllo2 = 1;
											} else {
												rigt--;
												colt++;
											}
										}
										if ((matGioc[colt][rigt1] == NERO
										|| matGioc[colt][rigt1] == DAMAN)
										&& controllo2 == 0) {
											col = j;
											rig = i;
											rigm = rigt1;
											colm = colt;
											controllo = 1;
											colt--;
											rigt1--;
											if (matNum[colt][rigt1] == des
										&& matGioc[colt][rigt1] == VUOTO) {
												col2 = colt;
												rig2 = rigt1;
												controllo2 = 1;
											} else {
												colt++;
												rigt1++;
											}
										}
									}
								}
							} else {
								if (i == ESTREMOSINISTRO || i == SINISTRO) {
									rigt = i + 1;
									colt = j - 1;
									colt1 = j + 1;
									if ((matGioc[colt][rigt] == NERO
										|| matGioc[colt][rigt] == DAMAN)
										&& controllo2 == 0) {
										col = j;
										rig = i;
										rigm = rigt;
										colm = colt;
										controllo = 1;
										rigt++;
										colt--;
										if (matNum[colt][rigt] == des
										&& matGioc[colt][rigt] == VUOTO) {
											col2 = colt;
											rig2 = rigt;
											controllo2 = 1;
										} else {
											rigt--;
											colt++;
										}
									}
									if ((matGioc[colt1][rigt] == NERO
									|| matGioc[colt1][rigt] == DAMAN)
									&& controllo2 == 0) {
										col = j;
										rig = i;
										rigm = rigt;
										colm = colt1;
										controllo = 1;
										colt1++;
										rigt++;
										if (matNum[colt1][rigt] == des
										&& matGioc[colt1][rigt] == VUOTO) {
											col2 = colt1;
											rig2 = rigt;
											controllo2 = 1;
										} else {
											colt1--;
											rigt--;
										}
									}
								} else {
									if (i == DESTRO || i == ESTREMODESTRO) {
										colt = j - 1;
										colt1 = j + 1;
										rigt1 = i  - 1;
										if ((matGioc[colt][rigt1] == NERO
										|| matGioc[colt][rigt1] == DAMAN)
										&& controllo2 == 0) {
											col = j;
											rig = i;
											rigm = rigt1;
											colm = colt;
											controllo = 1;
											rigt1--;
											colt--;
											if (matNum[colt][rigt1] == des
										&& matGioc[colt][rigt1] == VUOTO) {
												col2 = colt;
												rig2 = rigt1;
												controllo2 = 1;
											} else {
												rigt1++;
												colt++;
											}
										}
										if ((matGioc[colt1][rigt1] == NERO
										|| matGioc[colt1][rigt1] == DAMAN)
										&& controllo2 == 0) {
											col = j;
											rig = i;
											rigm = rigt1;
											colm = colt1;
											controllo = 1;
											colt1++;
											rigt1--;
											if (matNum[colt1][rigt1] == des
										&& matGioc[colt1][rigt1] == VUOTO) {
												col2 = colt1;
												rig2 = rigt1;
												controllo2 = 1;
											} else {
												colt1--;
												rigt1++;
											}
										}
									} else {
										rigt = i + 1;
										colt = j - 1;
										colt1 = j + 1;
										rigt1 = i - 1;
										if ((matGioc[colt][rigt] == NERO
										|| matGioc[colt][rigt] == DAMAN)
										&& controllo2 == 0) {
											col = j;
											rig = i;
											rigm = rigt;
											colm = colt;
											controllo = 1;
											rigt++;
											colt--;
											if (matNum[colt][rigt] == des
										&& matGioc[colt][rigt] == VUOTO) {
												col2 = colt;
												rig2 = rigt;
												controllo2 = 1;
											} else {
												rigt--;
												colt++;
											}
										}
										if ((matGioc[colt][rigt1] == 1
										|| matGioc[colt][rigt1] == DAMAN)
										&& controllo2 == 0) {
											col = j;
											rig = i;
											rigm = rigt1;
											colm = colt;
											controllo = 1;
											rigt1--;
											colt--;
											if (matNum[colt][rigt1] == des
										&& matGioc[colt][rigt1] == VUOTO) {
												col2 = colt;
												rig2 = rigt1;
												controllo2 = 1;
											} else {
												rigt1++;
												colt++;
											}
										}
										if ((matGioc[colt1][rigt] == NERO
										|| matGioc[colt1][rigt] == DAMAN)
										&& controllo2 == 0) {
											col = j;
											rig = i;
											rigm = rigt;
											colm = colt1;
											controllo = 1;
											colt1++;
											rigt++;
											if (matNum[colt1][rigt] == des
										&& matGioc[colt1][rigt] == VUOTO) {
												col2 = colt1;
												rig2 = rigt;
												controllo2 = 1;
											} else {
												colt1--;
												rigt--;
											}
										}
										if ((matGioc[colt1][rigt1] == NERO
										|| matGioc[colt1][rigt1] == DAMAN)
										&& controllo2 == 0) {
											col = j;
											rig = i;
											rigm = rigt1;
											colm = colt1;
											controllo = 1;
											colt1++;
											rigt1--;
											if (matNum[colt1][rigt1] == des
										&& matGioc[colt1][rigt1] == VUOTO) {
											 col2 = colt1;
											 rig2 = rigt1;
											 controllo2 = 1;
											} else {
												colt1--;
												rigt1++;
											}
										}
									}
								}
							}
						}
					}
				}
				j++;
			}
			i++;
		}
		return modificaPresa(matGioc);
	}
}
