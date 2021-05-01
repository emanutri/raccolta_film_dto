package it.prova.raccoltafilmspringbootservletspringdata.repository.regista;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.prova.raccoltafilmspringbootservletspringdata.model.Regista;

public interface RegistaRepository extends CrudRepository<Regista, Long>, CustomRegistaRepository{
	List<Regista> findByCognomeIgnoreCaseContainingOrNomeIgnoreCaseContaining(String cognome, String nome);
}
