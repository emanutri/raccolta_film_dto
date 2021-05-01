package it.prova.raccoltafilmspringbootservletspringdata.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import it.prova.raccoltafilmspringbootservletspringdata.model.Film;
import it.prova.raccoltafilmspringbootservletspringdata.utility.Utility;

public class FilmDTO {
	private Long id;
	private String titolo;
	private String genere;
	private Date dataPubblicazione;
	private Integer minutiDurata;

	private RegistaDTO regista;

	// questo serve per la validazione quindi la stampa degli errori in pagina
	// non riguarda il binding a differenza dei campi sopra....ecco un buon motivo
	// per
	// disaccoppiare con un DTO...
	private List<String> errors = new ArrayList<String>();

	public FilmDTO() {
	}

	public FilmDTO(Long id, String titolo, String genere, Date dataPubblicazione, Integer minutiDurata,
			RegistaDTO regista) {
		this.id = id;
		this.titolo = titolo;
		this.genere = genere;
		this.dataPubblicazione = dataPubblicazione;
		this.minutiDurata = minutiDurata;
		this.regista = regista;
	}

	public FilmDTO(Long id, String titolo, String genere, Date dataPubblicazione, Integer minutiDurata) {
		this.id = id;
		this.titolo = titolo;
		this.genere = genere;
		this.dataPubblicazione = dataPubblicazione;
		this.minutiDurata = minutiDurata;
	}

	public FilmDTO(String titolo, String genere) {
		this.titolo = titolo;
		this.genere = genere;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getGenere() {
		return genere;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}

	public Date getDataPubblicazione() {
		return dataPubblicazione;
	}

	public void setDataPubblicazione(Date dataPubblicazione) {
		this.dataPubblicazione = dataPubblicazione;
	}

	public Integer getMinutiDurata() {
		return minutiDurata;
	}

	public void setMinutiDurata(Integer minutiDurata) {
		this.minutiDurata = minutiDurata;
	}

	public RegistaDTO getRegista() {
		return regista;
	}

	public void setRegista(RegistaDTO regista) {
		this.regista = regista;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public void validate() {
		List<String> validationResult = new ArrayList<String>();

		if (StringUtils.isBlank(this.titolo))
			validationResult.add("Il campo 'Titolo' non può essere vuoto");
		if (StringUtils.isBlank(this.genere))
			validationResult.add("Il campo 'Genere' non può essere vuoto");
		if (this.dataPubblicazione == null)
			validationResult.add("Il campo 'Data di Pubblicazione' non può essere vuoto");
		if (this.minutiDurata == null || this.minutiDurata < 1)
			validationResult.add("Il campo 'Durata' deve contenere valori positivi");
		if (this.regista == null)
			validationResult.add("Il campo 'Regista' non può essere vuoto");

		this.setErrors(validationResult);
	}

	public boolean hasErrors() {
		return this.errors != null && !this.errors.isEmpty();
	}

	public Film buildFilmModel() {
		return new Film(this.id, this.titolo, this.genere, this.dataPubblicazione, this.minutiDurata,
				this.regista.buildRegistaModel());
	}

	public static FilmDTO buildFilmDTOFromModel(Film filmModel, boolean includeRegisti) {
		FilmDTO result = new FilmDTO(filmModel.getId(), filmModel.getTitolo(), filmModel.getGenere(),
				filmModel.getDataPubblicazione(), filmModel.getMinutiDurata());

		if (includeRegisti)
			result.setRegista(RegistaDTO.buildRegistaDTOFromModel(filmModel.getRegista()));

		return result;
	}

	public static FilmDTO createFilmDTOFromParams(String titoloInputParam, String genereInputParam,
			String minutiDurataInputParam, String dataPubblicazioneStringParam, String registaIdStringParam) {

		FilmDTO result = new FilmDTO(titoloInputParam, genereInputParam);
		if (NumberUtils.isCreatable(minutiDurataInputParam)) {
			result.setMinutiDurata(Integer.parseInt(minutiDurataInputParam));
		}
		result.setDataPubblicazione(Utility.parseDateFromString(dataPubblicazioneStringParam));
		if (NumberUtils.isCreatable(registaIdStringParam)) {
			result.setRegista(new RegistaDTO(Long.parseLong(registaIdStringParam)));
		}
		return result;
	}

	public static List<FilmDTO> createFilmDTOListFromModelList(List<Film> modelListInput, boolean includeRegisti) {
		return modelListInput.stream().map(filmEntity -> {
			return FilmDTO.buildFilmDTOFromModel(filmEntity, includeRegisti);
		}).collect(Collectors.toList());
	}

	public Film buildFilmModelWhitoutRegista() {
		return new Film(this.id, this.titolo, this.genere, this.dataPubblicazione, this.minutiDurata);
	}
}
