package it.prova.raccoltafilmspringbootservletspringdata.service;

import java.util.List;
import java.util.Set;

import it.prova.raccoltafilmspringbootservletspringdata.model.Ruolo;

public interface RuoloService {
	public List<Ruolo> listAll();

	public Ruolo caricaSingoloElemento(Long id);

	public void aggiorna(Ruolo ruoloInstance);

	public void inserisciNuovo(Ruolo ruoloInstance);

	public void rimuovi(Ruolo ruoloInstance);

	public Ruolo cercaPerDescrizioneECodice(String descrizione, String codice);

	public Set<Ruolo> findOnArrayByIdParam(String[] ids);
}
