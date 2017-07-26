package br.edu.ifg.proi.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifg.proi.dao.ClienteDao;

@WebServlet("/checkLogin")
public class CheckLoginServlet extends HttpServlet {

	private Map<String, String> passwordMap = new HashMap<>();

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		// Preenche o mapa de senhas
		ClienteDao dao = new ClienteDao();
		passwordMap = dao.buscarEmailESenha();

		// pegando os parâmetros do request
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String error = "";

		// verifica se há algum erro
		if (login.isEmpty() || password.isEmpty()) {
			error = "Login e senha devem ser não-vazios";
		} else if (!passwordMap.containsKey(login)) {
			error = "Login inexistente";
		} else {
			String correctPass = passwordMap.get(login);
			if (!password.equals(correctPass)) {
				error = "Login e/ou senha inválidos";
			}
		}

		// se houve algum erro exibe o formulário novamente com o erro
		if (!error.isEmpty()) {
			response.sendRedirect("login-error.html");
		} else {
			// se não houve erro, redireciona para a página
			response.sendRedirect("home.html");
		}
	}
}