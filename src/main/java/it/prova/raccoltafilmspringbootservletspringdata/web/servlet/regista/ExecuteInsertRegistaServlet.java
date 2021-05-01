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
public class ExecuteInsertRegistaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private RegistaService registaService;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// estraggo input
		String nomeParam = request.getParameter("nome");
		String cognomeParam = request.getParameter("cognome");
		String nickNameParam = request.getParameter("nickName");
		String dataDiNascitaParam = request.getParameter("dataDiNascita");
		String sessoParam = request.getParameter("sesso");

		// preparo il dto e lo valido
		RegistaDTO registaDTOInstance = RegistaDTO.createRegistaDTOFromParams(nomeParam, cognomeParam, nickNameParam,
				dataDiNascitaParam, sessoParam);
		registaDTOInstance.validate();

		// se la validazione non risulta ok
		if (registaDTOInstance.hasErrors()) {
			request.setAttribute("insert_regista_attr", registaDTOInstance);
			request.getRequestDispatcher("/regista/insert.jsp").forward(request, response);
			return;
		}

		// se sono qui i valori sono ok quindi posso creare l'oggetto da inserire
		// occupiamoci delle operazioni di business
		try {
			registaService.inserisciNuovo(registaDTOInstance.buildRegistaModel());
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/regista/insert.jsp").forward(request, response);
			return;
		}

		response.sendRedirect("ExecuteListRegistaServlet?operationResult=SUCCESS");

	}

}
