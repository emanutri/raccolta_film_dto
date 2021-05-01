package it.prova.raccoltafilmspringbootservletspringdata.web.servlet.regista;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.prova.raccoltafilmspringbootservletspringdata.dto.RegistaDTO;
import it.prova.raccoltafilmspringbootservletspringdata.model.Regista;
import it.prova.raccoltafilmspringbootservletspringdata.service.RegistaService;

@Component
public class ExecuteVisualizzaRegistaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private RegistaService registaService;

	public ExecuteVisualizzaRegistaServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idRegistaParam = request.getParameter("idRegista");

		if (!NumberUtils.isCreatable(idRegistaParam)) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}

		try {
			Regista registaInstance = registaService.caricaSingoloElemento(Long.parseLong(idRegistaParam));

			if (registaInstance == null) {
				request.setAttribute("errorMessage", "Elemento non trovato.");
				request.getRequestDispatcher("ExecuteListFilmServlet?operationResult=NOT_FOUND").forward(request,
						response);
				return;
			}

			request.setAttribute("show_regista_attr", RegistaDTO.buildRegistaDTOFromModel(registaInstance));
		} catch (Exception e) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}

		request.getRequestDispatcher("/regista/show.jsp").forward(request, response);
	}

}
