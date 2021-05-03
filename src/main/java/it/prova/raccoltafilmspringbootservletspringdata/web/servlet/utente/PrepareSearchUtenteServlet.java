package it.prova.raccoltafilmspringbootservletspringdata.web.servlet.utente;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.prova.raccoltafilmspringbootservletspringdata.model.Ruolo;
import it.prova.raccoltafilmspringbootservletspringdata.model.StatoUtente;
import it.prova.raccoltafilmspringbootservletspringdata.service.RuoloService;

@Component
public class PrepareSearchUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private RuoloService ruoloService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Ruolo> ruoli = ruoloService.listAll();
		List<String> stati = Stream.of(StatoUtente.values()).map(stato -> stato.name()).collect(Collectors.toList());
		request.setAttribute("ruoli_list_attribute", ruoli);
		request.setAttribute("stato_list_attribute", stati);

		request.getRequestDispatcher("/utente/search.jsp").forward(request, response);
	}

}
