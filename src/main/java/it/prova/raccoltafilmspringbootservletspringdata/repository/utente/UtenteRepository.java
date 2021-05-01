package it.prova.raccoltafilmspringbootservletspringdata.repository.utente;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import it.prova.raccoltafilmspringbootservletspringdata.model.StatoUtente;
import it.prova.raccoltafilmspringbootservletspringdata.model.Utente;

public interface UtenteRepository extends CrudRepository<Utente, Long> {
	
	Utente findByUsernameAndPassword(String username, String password);
	
	//caricamento eager, ovviamente si può fare anche con jpql
	@EntityGraph(attributePaths = { "ruoli" })
	Utente findByUsernameAndPasswordAndStato(String username,String password, StatoUtente stato);
}
