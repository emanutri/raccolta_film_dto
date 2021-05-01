package it.prova.raccoltafilmspringbootservletspringdata.repository.film;

import org.springframework.data.repository.CrudRepository;

import it.prova.raccoltafilmspringbootservletspringdata.model.Film;

public interface FilmRepository extends CrudRepository<Film, Long>, CustomFilmRepository{

}
