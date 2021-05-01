package it.prova.raccoltafilmspringbootservletspringdata;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.prova.raccoltafilmspringbootservletspringdata.model.Ruolo;
import it.prova.raccoltafilmspringbootservletspringdata.model.StatoUtente;
import it.prova.raccoltafilmspringbootservletspringdata.model.Utente;
import it.prova.raccoltafilmspringbootservletspringdata.service.RuoloService;
import it.prova.raccoltafilmspringbootservletspringdata.service.UtenteService;

@SpringBootApplication
public class RaccoltafilmspringbootservletspringdataApplication implements CommandLineRunner {
	
	@Autowired
	private RuoloService ruoloServiceInstance;
	@Autowired
	private UtenteService utenteServiceInstance;

	public static void main(String[] args) {
		SpringApplication.run(RaccoltafilmspringbootservletspringdataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", "ROLE_ADMIN") == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Administrator", "ROLE_ADMIN"));
		}

		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Classic User", "ROLE_CLASSIC_USER") == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Classic User", "ROLE_CLASSIC_USER"));
		}

		if (utenteServiceInstance.findByUsernameAndPassword("admin", "admin") == null) {
			Utente admin = new Utente("admin", "admin", "Mario", "Rossi", new Date());
			admin.setStato(StatoUtente.ATTIVO);
			admin.getRuoli().add(ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", "ROLE_ADMIN"));
			utenteServiceInstance.inserisciNuovo(admin);
//			utenteServiceInstance.aggiungiRuolo(admin,
//					ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", "ROLE_ADMIN"));
		}
		
		if (utenteServiceInstance.findByUsernameAndPassword("user", "user") == null) {
			Utente admin = new Utente("user", "user", "Antonio", "Verdi", new Date());
			admin.setStato(StatoUtente.ATTIVO);
			admin.getRuoli().add(ruoloServiceInstance.cercaPerDescrizioneECodice("Classic User", "ROLE_CLASSIC_USER"));
			utenteServiceInstance.inserisciNuovo(admin);
//			utenteServiceInstance.aggiungiRuolo(admin,
//					ruoloServiceInstance.cercaPerDescrizioneECodice("Classic User", "ROLE_CLASSIC_USER"));
		}
		
	}

}
