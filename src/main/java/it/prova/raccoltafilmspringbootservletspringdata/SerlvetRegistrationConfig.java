package it.prova.raccoltafilmspringbootservletspringdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import it.prova.raccoltafilmspringbootservletspringdata.web.servlet.auth.LoginServlet;
import it.prova.raccoltafilmspringbootservletspringdata.web.servlet.film.ExecuteDeleteFilmServlet;
import it.prova.raccoltafilmspringbootservletspringdata.web.servlet.film.ExecuteInsertFilmServlet;
import it.prova.raccoltafilmspringbootservletspringdata.web.servlet.film.ExecuteListFilmServlet;
import it.prova.raccoltafilmspringbootservletspringdata.web.servlet.film.ExecuteModificaFilmServlet;
import it.prova.raccoltafilmspringbootservletspringdata.web.servlet.film.ExecuteSearchFilmServlet;
import it.prova.raccoltafilmspringbootservletspringdata.web.servlet.film.ExecuteVisualizzaFilmServlet;
import it.prova.raccoltafilmspringbootservletspringdata.web.servlet.film.PrepareDeleteFilmServlet;
import it.prova.raccoltafilmspringbootservletspringdata.web.servlet.film.PrepareInsertFilmServlet;
import it.prova.raccoltafilmspringbootservletspringdata.web.servlet.film.PrepareModificaFilmServlet;
import it.prova.raccoltafilmspringbootservletspringdata.web.servlet.film.PrepareSearchFilmServlet;
import it.prova.raccoltafilmspringbootservletspringdata.web.servlet.regista.ExecuteDeleteRegistaServlet;
import it.prova.raccoltafilmspringbootservletspringdata.web.servlet.regista.ExecuteInsertRegistaServlet;
import it.prova.raccoltafilmspringbootservletspringdata.web.servlet.regista.ExecuteListRegistaServlet;
import it.prova.raccoltafilmspringbootservletspringdata.web.servlet.regista.ExecuteModificaRegistaServlet;
import it.prova.raccoltafilmspringbootservletspringdata.web.servlet.regista.ExecuteSearchRegistaServlet;
import it.prova.raccoltafilmspringbootservletspringdata.web.servlet.regista.ExecuteVisualizzaRegistaServlet;
import it.prova.raccoltafilmspringbootservletspringdata.web.servlet.regista.PrepareDeleteRegistaServlet;
import it.prova.raccoltafilmspringbootservletspringdata.web.servlet.regista.PrepareModificaRegistaServlet;
import it.prova.raccoltafilmspringbootservletspringdata.web.servlet.utente.ExecuteSearchUtenteServlet;
import it.prova.raccoltafilmspringbootservletspringdata.web.servlet.utente.PrepareSearchUtenteServlet;

@Configuration
public class SerlvetRegistrationConfig {
	// N.B. se le servlet usano bean al loro interno vanno affidate a spring
	// altrimenti va bene @WebServlet

	// -------------regista----------------------
	@Autowired
	private LoginServlet loginServlet;
	@Autowired
	private ExecuteSearchRegistaServlet executeSearchRegistaServlet;
	@Autowired
	private ExecuteInsertRegistaServlet executeInsertRegistaServlet;
	@Autowired
	private ExecuteListRegistaServlet executeListRegistaServlet;
	@Autowired
	private ExecuteVisualizzaRegistaServlet executeVisualizzaRegistaServlet;
	@Autowired
	private PrepareDeleteRegistaServlet prepareDeleteRegistaServlet;
	@Autowired
	private ExecuteDeleteRegistaServlet executeDeleteRegistaServlet;
	@Autowired
	private PrepareModificaRegistaServlet prepareModificaRegistaServlet;
	@Autowired
	private ExecuteModificaRegistaServlet executeModificaRegistaServlet;

	// ------------------film-------------
	@Autowired
	private PrepareSearchFilmServlet prepareSearchFilmServlet;
	@Autowired
	private PrepareInsertFilmServlet prepareInsertFilmServlet;
	@Autowired
	private PrepareDeleteFilmServlet prepareDeleteFilmServlet;
	@Autowired
	private PrepareModificaFilmServlet prepareModificaFilmServlet;
	@Autowired
	private ExecuteModificaFilmServlet executeModificaFilmServlet;

	@Autowired
	private ExecuteInsertFilmServlet executeInsertFilmServlet;
	@Autowired
	private ExecuteListFilmServlet executeListFilmServlet;
	@Autowired
	private ExecuteSearchFilmServlet executeSearchFilmServlet;
	@Autowired
	private ExecuteVisualizzaFilmServlet executeVisualizzaFilmServlet;
	@Autowired
	private ExecuteDeleteFilmServlet executeDeleteFilmServlet;

	// --------------------utente---------------------------------
	@Autowired
	private PrepareSearchUtenteServlet prepareSearchUtenteServlet;
	@Autowired
	private ExecuteSearchUtenteServlet executeSearchUtenteServlet;

	@Bean
	public ServletRegistrationBean<LoginServlet> createLoginServletBean() {
		ServletRegistrationBean<LoginServlet> bean = new ServletRegistrationBean<LoginServlet>(loginServlet,
				"/LoginServlet");
		return bean;
	}

	// ---------------------regista------------------------------------------------

	@Bean
	public ServletRegistrationBean<PrepareDeleteRegistaServlet> createPrepareDeleteRegistaServletBean() {
		ServletRegistrationBean<PrepareDeleteRegistaServlet> bean = new ServletRegistrationBean<PrepareDeleteRegistaServlet>(
				prepareDeleteRegistaServlet, "/PrepareDeleteRegistaServlet");
		return bean;
	}

	@Bean
	public ServletRegistrationBean<PrepareModificaRegistaServlet> createPrepareModificaRegistaServletBean() {
		ServletRegistrationBean<PrepareModificaRegistaServlet> bean = new ServletRegistrationBean<PrepareModificaRegistaServlet>(
				prepareModificaRegistaServlet, "/PrepareModificaRegistaServlet");
		return bean;
	}

	@Bean
	public ServletRegistrationBean<ExecuteSearchRegistaServlet> createExecuteSearchRegistaServletBean() {
		ServletRegistrationBean<ExecuteSearchRegistaServlet> bean = new ServletRegistrationBean<ExecuteSearchRegistaServlet>(
				executeSearchRegistaServlet, "/ExecuteSearchRegistaServlet");
		return bean;
	}

	@Bean
	public ServletRegistrationBean<ExecuteInsertRegistaServlet> createExecuteInsertRegistaServletBean() {
		ServletRegistrationBean<ExecuteInsertRegistaServlet> bean = new ServletRegistrationBean<ExecuteInsertRegistaServlet>(
				executeInsertRegistaServlet, "/ExecuteInsertRegistaServlet");
		return bean;
	}

	@Bean
	public ServletRegistrationBean<ExecuteListRegistaServlet> createExecuteListRegistaServletBean() {
		ServletRegistrationBean<ExecuteListRegistaServlet> bean = new ServletRegistrationBean<ExecuteListRegistaServlet>(
				executeListRegistaServlet, "/ExecuteListRegistaServlet");
		return bean;
	}

	@Bean
	public ServletRegistrationBean<ExecuteVisualizzaRegistaServlet> createExecuteVisualizzaRegistaServletBean() {
		ServletRegistrationBean<ExecuteVisualizzaRegistaServlet> bean = new ServletRegistrationBean<ExecuteVisualizzaRegistaServlet>(
				executeVisualizzaRegistaServlet, "/ExecuteVisualizzaRegistaServlet");
		return bean;
	}

	@Bean
	public ServletRegistrationBean<ExecuteDeleteRegistaServlet> createExecuteDeleteRegistaServletBean() {
		ServletRegistrationBean<ExecuteDeleteRegistaServlet> bean = new ServletRegistrationBean<ExecuteDeleteRegistaServlet>(
				executeDeleteRegistaServlet, "/ExecuteDeleteRegistaServlet");
		return bean;
	}

	@Bean
	public ServletRegistrationBean<ExecuteModificaRegistaServlet> createExecuteModificaRegistaServletBean() {
		ServletRegistrationBean<ExecuteModificaRegistaServlet> bean = new ServletRegistrationBean<ExecuteModificaRegistaServlet>(
				executeModificaRegistaServlet, "/ExecuteModificaRegistaServlet");
		return bean;
	}

	// --------------------------film--------------------------------
	@Bean
	public ServletRegistrationBean<PrepareSearchFilmServlet> createPrepareSearchFilmServletBean() {
		ServletRegistrationBean<PrepareSearchFilmServlet> bean = new ServletRegistrationBean<PrepareSearchFilmServlet>(
				prepareSearchFilmServlet, "/PrepareSearchFilmServlet");
		return bean;
	}

	@Bean
	public ServletRegistrationBean<PrepareInsertFilmServlet> createPrepareInsertFilmServletBean() {
		ServletRegistrationBean<PrepareInsertFilmServlet> bean = new ServletRegistrationBean<PrepareInsertFilmServlet>(
				prepareInsertFilmServlet, "/PrepareInsertFilmServlet");
		return bean;
	}

	@Bean
	public ServletRegistrationBean<PrepareModificaFilmServlet> createPrepareModificaFilmServletBean() {
		ServletRegistrationBean<PrepareModificaFilmServlet> bean = new ServletRegistrationBean<PrepareModificaFilmServlet>(
				prepareModificaFilmServlet, "/PrepareModificaFilmServlet");
		return bean;
	}

	@Bean
	public ServletRegistrationBean<PrepareDeleteFilmServlet> createPrepareDeleteFilmServletBean() {
		ServletRegistrationBean<PrepareDeleteFilmServlet> bean = new ServletRegistrationBean<PrepareDeleteFilmServlet>(
				prepareDeleteFilmServlet, "/PrepareDeleteFilmServlet");
		return bean;
	}

	@Bean
	public ServletRegistrationBean<ExecuteInsertFilmServlet> createExecuteInsertFilmServletBean() {
		ServletRegistrationBean<ExecuteInsertFilmServlet> bean = new ServletRegistrationBean<ExecuteInsertFilmServlet>(
				executeInsertFilmServlet, "/ExecuteInsertFilmServlet");
		return bean;
	}

	@Bean
	public ServletRegistrationBean<ExecuteListFilmServlet> createExecuteListFilmServletBean() {
		ServletRegistrationBean<ExecuteListFilmServlet> bean = new ServletRegistrationBean<ExecuteListFilmServlet>(
				executeListFilmServlet, "/ExecuteListFilmServlet");
		return bean;
	}

	@Bean
	public ServletRegistrationBean<ExecuteSearchFilmServlet> createExecuteSearchFilmServletBean() {
		ServletRegistrationBean<ExecuteSearchFilmServlet> bean = new ServletRegistrationBean<ExecuteSearchFilmServlet>(
				executeSearchFilmServlet, "/ExecuteSearchFilmServlet");
		return bean;
	}

	@Bean
	public ServletRegistrationBean<ExecuteVisualizzaFilmServlet> createExecuteVisualizzaFilmServletBean() {
		ServletRegistrationBean<ExecuteVisualizzaFilmServlet> bean = new ServletRegistrationBean<ExecuteVisualizzaFilmServlet>(
				executeVisualizzaFilmServlet, "/ExecuteVisualizzaFilmServlet");
		return bean;
	}

	@Bean
	public ServletRegistrationBean<ExecuteDeleteFilmServlet> createExecuteDeleteFilmServletBean() {
		ServletRegistrationBean<ExecuteDeleteFilmServlet> bean = new ServletRegistrationBean<ExecuteDeleteFilmServlet>(
				executeDeleteFilmServlet, "/ExecuteDeleteFilmServlet");
		return bean;
	}

	@Bean
	public ServletRegistrationBean<ExecuteModificaFilmServlet> createExecuteModificaFilmServletBean() {
		ServletRegistrationBean<ExecuteModificaFilmServlet> bean = new ServletRegistrationBean<ExecuteModificaFilmServlet>(
				executeModificaFilmServlet, "/ExecuteModificaFilmServlet");
		return bean;
	}

	// ---------------------------utente--------------------------------------

	@Bean
	public ServletRegistrationBean<PrepareSearchUtenteServlet> createPrepareSearchUtenteServletBean() {
		ServletRegistrationBean<PrepareSearchUtenteServlet> bean = new ServletRegistrationBean<PrepareSearchUtenteServlet>(
				prepareSearchUtenteServlet, "/PrepareSearchUtenteServlet");
		return bean;
	}

	@Bean
	public ServletRegistrationBean<ExecuteSearchUtenteServlet> createExecuteSearchUtenteServletBean() {
		ServletRegistrationBean<ExecuteSearchUtenteServlet> bean = new ServletRegistrationBean<ExecuteSearchUtenteServlet>(
				executeSearchUtenteServlet, "/ExecuteSearchUtenteServlet");
		return bean;
	}

}
