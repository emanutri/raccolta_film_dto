package it.prova.raccoltafilmspringbootservletspringdata.web.servlet.regista;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.prova.raccoltafilmspringbootservletspringdata.dto.RegistaDTO;
import it.prova.raccoltafilmspringbootservletspringdata.service.RegistaService;

@Component
public class ExecuteModificaRegistaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private RegistaService registaService;

	public ExecuteModificaRegistaServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idParameter = request.getParameter("idRegista");
		String nomeParameter = request.getParameter("nome");
		String cognomeParameter = request.getParameter("cognome");
		String nickParameter = request.getParameter("nickName");
		String dataParameter = request.getParameter("dataDiNascita");
		String sessoParameter = request.getParameter("sesso");

		RegistaDTO registaDTOInstance = RegistaDTO.createRegistaDTOFromParams(nomeParameter, cognomeParameter,
				nickParameter, dataParameter, sessoParameter);
		registaDTOInstance.validate();
		registaDTOInstance.setId(Long.parseLong(idParameter));

		if (registaDTOInstance.hasErrors()) {

			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.setAttribute("registaDaModificare", registaDTOInstance);
			request.getRequestDispatcher("/regista/edit.jsp").forward(request, response);
			return;
		}

		try {
			registaService.aggiorna(registaDTOInstance.buildRegistaModel());

		} catch (Exception e) {

			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}

		// andiamo ai risultati
		response.sendRedirect("ExecuteListRegistaServlet?operationResult=SUCCESS");
	}

}
