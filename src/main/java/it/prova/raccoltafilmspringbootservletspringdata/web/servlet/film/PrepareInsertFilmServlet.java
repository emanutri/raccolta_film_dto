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
import it.prova.raccoltafilmspringbootservletspringdata.service.RegistaService;

@Component
public class PrepareInsertFilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private RegistaService registaService;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setAttribute("insert_film_attr", new FilmDTO());
			request.setAttribute("registi_list_attribute",
					RegistaDTO.createRegistaDTOListFromModelList(registaService.listAllElements()));
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}

		request.getRequestDispatcher("/film/insert.jsp").forward(request, response);
	}

}
