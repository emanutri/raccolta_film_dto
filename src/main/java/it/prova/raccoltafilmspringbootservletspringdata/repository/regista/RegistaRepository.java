package it.prova.raccoltafilmspringbootservletspringdata.repository.regista;

import java.util.List;
//import java.util.Optional;

//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.raccoltafilmspringbootservletspringdata.model.Regista;

public interface RegistaRepository extends CrudRepository<Regista, Long>, CustomRegistaRepository{
	List<Regista> findByCognomeIgnoreCaseContainingOrNomeIgnoreCaseContaining(String cognome, String nome);
	
	//by lorenzo, query senza passare dal custom
//	@Query("Select r from Regista r left join fetch r.films where r.id = ?1")
//	Optional<Regista> findOneEager(Long id);
}
