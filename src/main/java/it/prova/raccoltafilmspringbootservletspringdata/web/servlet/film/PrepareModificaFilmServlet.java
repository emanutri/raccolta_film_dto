package it.prova.raccoltafilmspringbootservletspringdata.web.servlet.film;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.prova.raccoltafilmspringbootservletspringdata.dto.FilmDTO;
import it.prova.raccoltafilmspringbootservletspringdata.dto.RegistaDTO;
import it.prova.raccoltafilmspringbootservletspringdata.model.Film;
import it.prova.raccoltafilmspringbootservletspringdata.service.FilmService;
import it.prova.raccoltafilmspringbootservletspringdata.service.RegistaService;

@Component
public class PrepareModificaFilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private FilmService filmService;

	@Autowired
	private RegistaService registaService;

	public PrepareModificaFilmServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String filmIdParameter = request.getParameter("idFilm");

		if (!NumberUtils.isCreatable(filmIdParameter)) {

			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/film/list.jsp").forward(request, response);
			return;

		}
		RequestDispatcher rd = null;

		try {
			Film filmInstance = filmService.caricaSingoloElementoEager(Long.parseLong(filmIdParameter));

			request.setAttribute("film_attribute", FilmDTO.buildFilmDTOFromModel(filmInstance, true));
			request.setAttribute("list_reg_attr",
					RegistaDTO.createRegistaDTOListFromModelList(registaService.listAllElements()));

		} catch (Exception e) {

			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}

		rd = request.getRequestDispatcher("/film/edit.jsp");
		rd.forward(request, response);
	}

}
