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
public class ExecuteSearchFilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private FilmService filmService;

	@Autowired
	private RegistaService registaService;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String titoloParam = request.getParameter("titolo");
		String genereParam = request.getParameter("genere");
		String dataPubblicazioneParam = request.getParameter("dataPubblicazione");
		String minutiDurataParam = request.getParameter("minutiDurata");
		String idRegistaParam = request.getParameter("regista.id");

		FilmDTO filmDTOInstance = FilmDTO.createFilmDTOFromParams(titoloParam, genereParam, minutiDurataParam,
				dataPubblicazioneParam, idRegistaParam);

		try {

			if (!idRegistaParam.isEmpty()) {

				request.setAttribute("film_list_attribute", FilmDTO.createFilmDTOListFromModelList(
						filmService.findByExample(filmDTOInstance.buildFilmModel()), true));

			} else {
				request.setAttribute("film_list_attribute", FilmDTO.createFilmDTOListFromModelList(
						filmService.findByExample(filmDTOInstance.buildFilmModelWhitoutRegista()), false));
			}

		} catch (Exception e) {
			e.printStackTrace();
			
			request.setAttribute("registi_list_attribute",
					RegistaDTO.createRegistaDTOListFromModelList(registaService.listAllElements()));
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/film/search.jsp").forward(request, response);
			return;
		}
		request.getRequestDispatcher("/film/list.jsp").forward(request, response);
	}

}
