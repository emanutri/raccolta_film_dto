package it.prova.raccoltafilmspringbootservletspringdata.repository.ruolo;

import org.springframework.data.repository.CrudRepository;

import it.prova.raccoltafilmspringbootservletspringdata.model.Ruolo;

public interface RuoloRepository extends CrudRepository<Ruolo, Long> {
	Ruolo findByDescrizioneAndCodice(String descrizione, String codice);
}
