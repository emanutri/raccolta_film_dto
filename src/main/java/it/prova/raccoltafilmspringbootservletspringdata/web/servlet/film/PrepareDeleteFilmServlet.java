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
import it.prova.raccoltafilmspringbootservletspringdata.model.Film;
import it.prova.raccoltafilmspringbootservletspringdata.service.FilmService;

@Component
public class PrepareDeleteFilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private FilmService filmService;

	public PrepareDeleteFilmServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String filmId = request.getParameter("idFilm");

		if (!NumberUtils.isCreatable(filmId)) {

			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/film/list.jsp").forward(request, response);
			return;
		}

		RequestDispatcher rd = null;

		try {
			Film filmInstance = filmService.caricaSingoloElemento(Long.parseLong(filmId));

			request.setAttribute("film_delete", FilmDTO.buildFilmDTOFromModel(filmInstance, false));

		} catch (Exception e) {

			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}

		rd = request.getRequestDispatcher("/film/delete.jsp");
		rd.forward(request, response);
	}

}
