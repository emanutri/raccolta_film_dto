package it.prova.raccoltafilmspringbootservletspringdata.web.servlet.regista;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.prova.raccoltafilmspringbootservletspringdata.dto.RegistaDTO;
import it.prova.raccoltafilmspringbootservletspringdata.model.Regista;
import it.prova.raccoltafilmspringbootservletspringdata.model.Sesso;
import it.prova.raccoltafilmspringbootservletspringdata.service.RegistaService;
import it.prova.raccoltafilmspringbootservletspringdata.utility.Utility;

@Component
public class ExecuteSearchRegistaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private RegistaService registaService;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nomeParam = request.getParameter("nome");
		String cognomeParam = request.getParameter("cognome");
		String nickNameParam = request.getParameter("nickName");
		String dataDiNascitaParam = request.getParameter("dataDiNascita");
		String sessoParam = request.getParameter("sesso");

		Sesso sessoParsed = StringUtils.isNotBlank(sessoParam) ? Sesso.valueOf(sessoParam) : null;
		Regista example = new Regista(nomeParam, cognomeParam, nickNameParam,
				Utility.parseDateFromString(dataDiNascitaParam), sessoParsed);

		try {
			// ovviamente trasformo da Model a DTO e lo faccio con gli stream
			request.setAttribute("registi_list_attribute",
					RegistaDTO.createRegistaDTOListFromModelList(registaService.findByExample(example)));
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/regista/search.jsp").forward(request, response);
			return;
		}
		request.getRequestDispatcher("regista/list.jsp").forward(request, response);
	}

}
