package it.prova.raccoltafilmspringbootservletspringdata.web.servlet.regista;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.raccoltafilmspringbootservletspringdata.dto.RegistaDTO;

@WebServlet("/PrepareInsertRegistaServlet")
public class PrepareInsertRegistaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("insert_regista_attr", new RegistaDTO());
		request.getRequestDispatcher("/regista/insert.jsp").forward(request, response);
	}

}
