package it.prova.raccoltafilmspringbootservletspringdata.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import it.prova.raccoltafilmspringbootservletspringdata.model.Regista;
import it.prova.raccoltafilmspringbootservletspringdata.model.Sesso;
import it.prova.raccoltafilmspringbootservletspringdata.utility.Utility;

public class RegistaDTO {
	private Long id;
	private String nome;
	private String cognome;
	private String nickName;
	private Date dataDiNascita;
	private Sesso sesso;
	
	//la proprieta uno a molti e cioe 'films' non serve al momento nelle view
	//e quindi non la mettiamo anche perche in genere risulta di difficile gestione

	// questo serve per la validazione quindi la stampa degli errori in pagina
	// non riguarda il binding a differenza dei campi sopra....ecco un buon motivo
	// per
	// disaccoppiare con un DTO...
	private List<String> errors = new ArrayList<String>();

	public RegistaDTO() {
	}

	public RegistaDTO(Long id) {
		this.id = id;
	}

	public RegistaDTO(Long id, String nome, String cognome, String nickName, Date dataDiNascita, Sesso sesso) {
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.nickName = nickName;
		this.dataDiNascita = dataDiNascita;
		this.sesso = sesso;
	}

	public RegistaDTO(String nome, String cognome, String nickName) {
		this.nome = nome;
		this.cognome = cognome;
		this.nickName = nickName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Date getDataDiNascita() {
		return dataDiNascita;
	}

	public void setDataDiNascita(Date dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	public Sesso getSesso() {
		return sesso;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public void setSesso(Sesso sesso) {
		this.sesso = sesso;
	}

	public void validate() {
		List<String> validationResult = new ArrayList<String>();

		if (StringUtils.isBlank(this.nome))
			validationResult.add("Il campo 'Nome' non può essere vuoto");
		if (StringUtils.isBlank(this.cognome))
			validationResult.add("Il campo 'Cognome' non può essere vuoto");
		if (StringUtils.isBlank(this.nickName))
			validationResult.add("Il campo 'NickName' non può essere vuoto");
		if (this.sesso == null)
			validationResult.add("Il campo 'Sesso' non può essere vuoto");
		if (this.dataDiNascita == null)
			validationResult.add("Il campo 'Data di Nascita' non può essere vuoto");

		this.setErrors(validationResult);
	}

	public boolean hasErrors() {
		return this.errors != null && !this.errors.isEmpty();
	}

	public Regista buildRegistaModel() {
		return new Regista(this.id, this.nome, this.cognome, this.nickName, this.dataDiNascita, this.sesso);
	}

	public static RegistaDTO buildRegistaDTOFromModel(Regista registaModel) {
		return new RegistaDTO(registaModel.getId(), registaModel.getNome(), registaModel.getCognome(),
				registaModel.getNickName(), registaModel.getDataDiNascita(), registaModel.getSesso());
	}

	public static RegistaDTO createRegistaDTOFromParams(String nomeInputParam, String cognomeInputParam,
			String nickNameInputParam, String dataDiNascitaStringParam, String sessoParam) {

		RegistaDTO result = new RegistaDTO(nomeInputParam, cognomeInputParam, nickNameInputParam);
		result.setSesso(StringUtils.isBlank(sessoParam) ? null : Sesso.valueOf(sessoParam));
		result.setDataDiNascita(Utility.parseDateFromString(dataDiNascitaStringParam));
		return result;
	}

	public static List<RegistaDTO> createRegistaDTOListFromModelList(List<Regista> modelListInput) {
		return modelListInput.stream().map(registaEntity -> {
			return RegistaDTO.buildRegistaDTOFromModel(registaEntity);
		}).collect(Collectors.toList());
	}

}
