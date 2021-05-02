package it.prova.raccoltafilmspringbootservletspringdata.web.servlet.regista;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
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
public class PrepareModificaRegistaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private RegistaService registaService;

	public PrepareModificaRegistaServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String parametroregistadaModificare = request.getParameter("idRegista");

//		RegistaService registaservice = MyServiceFactory.getRegistaServiceInstance();
//		Regista result = null;

		if (!NumberUtils.isCreatable(parametroregistadaModificare)) {

			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/regista/list.jsp").forward(request, response);
			return;

		}

		try {

			Regista registaDaModificare = registaService
					.caricaSingoloElementoConFilms(Long.parseLong(parametroregistadaModificare));
			request.setAttribute("registaDaModificare", RegistaDTO.buildRegistaDTOFromModel(registaDaModificare));
			RequestDispatcher rd = request.getRequestDispatcher("/regista/edit.jsp");
			rd.forward(request, response);

		} catch (Exception e) {

			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}

	}

}
