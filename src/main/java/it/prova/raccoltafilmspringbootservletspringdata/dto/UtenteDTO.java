package it.prova.raccoltafilmspringbootservletspringdata.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import it.prova.raccoltafilmspringbootservletspringdata.model.Ruolo;
import it.prova.raccoltafilmspringbootservletspringdata.model.StatoUtente;
import it.prova.raccoltafilmspringbootservletspringdata.model.Utente;
import it.prova.raccoltafilmspringbootservletspringdata.utility.Utility;

public class UtenteDTO {

	private Long id;
	private String username;
	private String password;
	private String nome;
	private String cognome;
	private Date dateCreated;
	private StatoUtente stato;
	private Set<Ruolo> ruoli = new HashSet<>();
	private List<String> errors = new ArrayList<String>();

	public UtenteDTO() {
		super();
	}

	public UtenteDTO(Long id, String username, String password, String nome, String cognome, Date dateCreated,
			StatoUtente stato) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		this.dateCreated = dateCreated;
		this.stato = stato;
	}

	public UtenteDTO(String username, String password, String nome, String cognome, StatoUtente stato) {
		this.username = username;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		this.stato = stato;
	}

	public UtenteDTO(Long id, String username, String password, String nome, String cognome, Date dateCreated,
			StatoUtente stato, Set<Ruolo> ruolo) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		this.dateCreated = dateCreated;
		this.stato = stato;
		this.ruoli = ruolo;
	}

	public UtenteDTO(String username, String password, String nome, String cognome) {
		this.username = username;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
	}

	public UtenteDTO(String username, String nome, String cognome) {
		this.username = username;
		this.nome = nome;
		this.cognome = cognome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public StatoUtente getStato() {
		return stato;
	}

	public void setStato(StatoUtente stato) {
		this.stato = stato;
	}

	public Set<Ruolo> getRuoli() {
		return ruoli;
	}

	public void setRuoli(Set<Ruolo> ruoli) {
		this.ruoli = ruoli;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public void validate() {
		List<String> validationResult = new ArrayList<String>();

		if (StringUtils.isBlank(this.nome))
			validationResult.add("Il campo 'Nome' non può essere vuoto");
		if (StringUtils.isBlank(this.cognome))
			validationResult.add("Il campo 'Cognome' non può essere vuoto");
		if (StringUtils.isBlank(this.username))
			validationResult.add("Il campo 'username' non può essere vuoto");
		if (this.stato == null)
			validationResult.add("Il campo 'stato' non può essere vuoto");
		if (this.dateCreated == null)
			validationResult.add("Il campo 'dateCreated' non può essere vuoto");
		if (this.ruoli.isEmpty()) {
			validationResult.add("Il campo 'ruoli' non può essere vuoto");
		}
		this.setErrors(validationResult);
	}

	public boolean hasErrors() {
		return this.errors != null && !this.errors.isEmpty();
	}

	public Utente buildUtenteModel() {
		return new Utente(this.id, this.username, this.password, this.nome, this.cognome, this.dateCreated, this.stato,
				this.ruoli);
	}

	public static UtenteDTO buildUtenteDTOFromModel(Utente utenteModel) {
		return new UtenteDTO(utenteModel.getId(), utenteModel.getUsername(), utenteModel.getPassword(),
				utenteModel.getNome(), utenteModel.getCognome(), utenteModel.getDateCreated(), utenteModel.getStato(),
				utenteModel.getRuoli());
	}

	public static UtenteDTO createUtenteDTOFromParams(String usernameParam, String nomeParam, String cognomeParam,
			String dateCreatedParam, String statoParam, Set<Ruolo> ruoliParam) {

		UtenteDTO result = new UtenteDTO(usernameParam, nomeParam, cognomeParam);
		result.setStato(StringUtils.isBlank(statoParam) ? null : StatoUtente.traduciStatoUtente(statoParam));
		result.setDateCreated(Utility.parseDateFromString(dateCreatedParam));
		result.setRuoli(ruoliParam);
		return result;
	}

	public static UtenteDTO createUtenteDTOFromInsertParams(String usernameParam, String password, String nomeParam,
			String cognomeParam, Set<Ruolo> ruoliParam) {

		UtenteDTO result = UtenteDTO.createUtenteDTOFromParams(usernameParam, "creato", nomeParam, cognomeParam,
				ruoliParam);
		result.setPassword(password);
		result.setDateCreated(new Date());
		result.setStato(StatoUtente.CREATO);

		return result;
	}

	public static UtenteDTO createUtenteDTOFromParams(String usernameParam, String passwordParam, String nomeParam,
			String cognomeParam, Set<Ruolo> ruoliParam) {

		UtenteDTO result = new UtenteDTO(usernameParam, passwordParam, nomeParam, cognomeParam);
		result.setRuoli(ruoliParam);

		return result;
	}

	public static List<UtenteDTO> createUtenteDTOListFromModelList(List<Utente> modelListInput) {
		return modelListInput.stream().map(utenteEntity -> {
			return UtenteDTO.buildUtenteDTOFromModel(utenteEntity);
		}).collect(Collectors.toList());
	}
}
