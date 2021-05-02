package it.prova.raccoltafilmspringbootservletspringdata.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.raccoltafilmspringbootservletspringdata.model.Regista;
import it.prova.raccoltafilmspringbootservletspringdata.repository.regista.RegistaRepository;

@Service
public class RegistaServiceImpl implements RegistaService {

	@Autowired
	private RegistaRepository repository;


	@Transactional(readOnly = true)
	public List<Regista> listAllElements() {
		return (List<Regista>)repository.findAll();
	}

	@Transactional(readOnly = true)
	public Regista caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Transactional(readOnly = true)
	public Regista caricaSingoloElementoConFilms(Long id) {
		return repository.findByIdEager(id).orElse(null);
	}

	@Transactional
	public void aggiorna(Regista registaInstance) {
		repository.save(registaInstance);
	}

	@Transactional
	public void inserisciNuovo(Regista registaInstance) {
		repository.save(registaInstance);
	}

	@Transactional
	public void rimuovi(Regista registaInstance) {
		if (repository.findByIdEager(registaInstance.getId()).get().getFilms().size() > 0)
			throw new RuntimeException("Non posso rimuovere questo regista.");
		else
			repository.delete(registaInstance);
	}

	@Transactional(readOnly = true)
	public List<Regista> findByExample(Regista example) {
		return repository.findByExample(example);
	}

	@Transactional(readOnly = true)
	public List<Regista> cercaByCognomeENomeILike(String term) {
		return repository.findByCognomeIgnoreCaseContainingOrNomeIgnoreCaseContaining(term, term);
	}

}
