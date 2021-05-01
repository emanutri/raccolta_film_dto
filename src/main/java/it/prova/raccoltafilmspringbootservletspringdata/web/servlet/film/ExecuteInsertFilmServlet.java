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
public class ExecuteInsertFilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private RegistaService registaService;
	@Autowired
	private FilmService filmService;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// estraggo input
		String titoloParam = request.getParameter("titolo");
		String genereParam = request.getParameter("genere");
		String dataPubblicazioneParam = request.getParameter("dataPubblicazione");
		String minutiDurataParam = request.getParameter("minutiDurata");
		String registaIdParam = request.getParameter("regista.id");

		FilmDTO filmDTOInstance = FilmDTO.createFilmDTOFromParams(titoloParam, genereParam, minutiDurataParam,
				dataPubblicazioneParam, registaIdParam);
		filmDTOInstance.validate();

		try {
			// se la validazione non risulta ok
			if (filmDTOInstance.hasErrors()) {
				request.setAttribute("insert_film_attr", filmDTOInstance);
				request.setAttribute("registi_list_attribute",
						RegistaDTO.createRegistaDTOListFromModelList(registaService.listAllElements()));
				request.getRequestDispatcher("/film/insert.jsp").forward(request, response);
				return;
			}

			// se sono qui i valori sono ok quindi posso creare l'oggetto da inserire
			// occupiamoci delle operazioni di business
			filmService.inserisciNuovo(filmDTOInstance.buildFilmModel());
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/film/insert.jsp").forward(request, response);
			return;
		}

		response.sendRedirect("ExecuteListFilmServlet?operationResult=SUCCESS");
	}

}
