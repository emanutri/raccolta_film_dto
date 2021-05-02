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
public class ExecuteDeleteRegistaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	RegistaService registaService;

	public ExecuteDeleteRegistaServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idRegistaParam = request.getParameter("idDeleteInput");

		if (!NumberUtils.isCreatable(idRegistaParam)) {
			request.setAttribute("errorMessage", "Attenzione, si è verificato un errore!");
			request.getRequestDispatcher("regista/search.jsp").forward(request, response);
			return;
		}

		try {

			Regista registaInstance = registaService.caricaSingoloElementoConFilms(Long.parseLong(idRegistaParam));
			registaService.rimuovi(registaInstance);

			request.setAttribute("listaRegistiAttribute",
					RegistaDTO.createRegistaDTOListFromModelList(registaService.listAllElements()));
			request.setAttribute("successMessage", "Operazione eseguita con successo!");
		
		} catch (RuntimeException e) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			e.printStackTrace();
			request.setAttribute("errorMessage", "Impossibile eliminare regista, perchè ha film associati.");
			request.setAttribute("registi_list_attribute",
					RegistaDTO.createRegistaDTOListFromModelList(registaService.listAllElements()));
			request.getRequestDispatcher("regista/list.jsp").forward(request, response);
			return;
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("regista/list.jsp").forward(request, response);
			return;
		}

		request.getRequestDispatcher("ExecuteListRegistaServlet").forward(request, response);
	}

}
