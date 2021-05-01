package it.prova.raccoltafilmspringbootservletspringdata.repository.regista;

import java.util.List;

import it.prova.raccoltafilmspringbootservletspringdata.model.Regista;

public interface CustomRegistaRepository {
	List<Regista> findByExample(Regista example);
}
