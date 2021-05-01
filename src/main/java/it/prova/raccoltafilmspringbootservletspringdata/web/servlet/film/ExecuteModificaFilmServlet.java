package it.prova.raccoltafilmspringbootservletspringdata.web.servlet.film;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.prova.raccoltafilmspringbootservletspringdata.dto.FilmDTO;
import it.prova.raccoltafilmspringbootservletspringdata.dto.RegistaDTO;
import it.prova.raccoltafilmspringbootservletspringdata.service.FilmService;
import it.prova.raccoltafilmspringbootservletspringdata.service.RegistaService;

@Component
public class ExecuteModificaFilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private FilmService filmService;
	@Autowired
	private RegistaService registaService;

	public ExecuteModificaFilmServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idParameter = request.getParameter("idFilm");
		String titoloParameter = request.getParameter("titolo");
		String genereParameter = request.getParameter("genere");
		String dataParameter = request.getParameter("dataPubblicazione");
		String durataParameter = request.getParameter("durata");
		String idRegistaParameter = request.getParameter("regista.id");

		FilmDTO filmDTOInstance = FilmDTO.createFilmDTOFromParams(titoloParameter, genereParameter, durataParameter,
				dataParameter, idRegistaParameter);
		filmDTOInstance.setId(Long.parseLong(idParameter));

		filmDTOInstance.validate();

		try {

			if (filmDTOInstance.hasErrors()) {

				request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");

				request.setAttribute("film_attribute", filmDTOInstance);
				request.setAttribute("list_reg_attr",
						RegistaDTO.createRegistaDTOListFromModelList(registaService.listAllElements()));

				request.getRequestDispatcher("/film/edit.jsp").forward(request, response);
				return;
			}

			filmService.aggiorna(filmDTOInstance.buildFilmModel());
			request.setAttribute("film_list_attribute", filmService.listAllElements());
			request.setAttribute("successMessage", "Operazione effettuata con successo");

		} catch (Exception e) {

			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}

		// andiamo ai risultati
		response.sendRedirect("ExecuteListFilmServlet?operationResult=SUCCESS");
	}

}
