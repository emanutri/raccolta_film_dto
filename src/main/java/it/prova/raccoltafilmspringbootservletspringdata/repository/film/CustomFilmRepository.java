package it.prova.raccoltafilmspringbootservletspringdata.repository.film;

import java.util.List;
import java.util.Optional;

import it.prova.raccoltafilmspringbootservletspringdata.model.Film;

public interface CustomFilmRepository {

	public List<Film> findByExample(Film example);

	public Optional<Film> findByIdEager(Long idInput);

}
