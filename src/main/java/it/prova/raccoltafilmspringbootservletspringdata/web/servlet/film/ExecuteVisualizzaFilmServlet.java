package it.prova.raccoltafilmspringbootservletspringdata.web.servlet.film;

import java.io.IOException;
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
public class ExecuteVisualizzaFilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private FilmService filmService;

	public ExecuteVisualizzaFilmServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idFilmParam = request.getParameter("idFilm");

		if (!NumberUtils.isCreatable(idFilmParam)) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}

		try {
			Film filmInstance = filmService.caricaSingoloElementoEager(Long.parseLong(idFilmParam));
			System.out.println(filmInstance);
			if (filmInstance == null) {
				request.setAttribute("errorMessage", "Elemento non trovato.");
				request.getRequestDispatcher("ExecuteListFilmServlet?operationResult=NOT_FOUND").forward(request,
						response);
				return;
			}
			request.setAttribute("show_film_attr", FilmDTO.buildFilmDTOFromModel(filmInstance, true));

		} catch (Exception e) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}

		request.getRequestDispatcher("/film/show.jsp").forward(request, response);
	}

}
