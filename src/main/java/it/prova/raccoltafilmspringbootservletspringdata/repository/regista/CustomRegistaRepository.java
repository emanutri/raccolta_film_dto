package it.prova.raccoltafilmspringbootservletspringdata.repository.regista;

import java.util.List;
import java.util.Optional;

import it.prova.raccoltafilmspringbootservletspringdata.model.Regista;

public interface CustomRegistaRepository {
	
	public List<Regista> findByExample(Regista example);

	public Optional<Regista> findByIdEager(Long idInput);
}
