package br.edu.ifg.proi.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifg.proi.dao.ClienteDao;
import br.edu.ifg.proi.dao.EnderecoDao;
import br.edu.ifg.proi.model.Cliente;

@WebServlet("/BuscarCliente")
public class BuscarClienteServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		// PrintWriter out = response.getWriter();

		String cpf = request.getParameter("cpf");

		String buttonAlterar = request.getParameter("alterar");
		String buttonExcluir = request.getParameter("excluir");

		Cliente cliente = new Cliente();
		cliente.setCpf(cpf);

		ClienteDao dao = new ClienteDao();

		Cliente cVerificado = dao.buscaCliente(cpf);

		if (cVerificado != null && buttonAlterar != null) {

			request.getRequestDispatcher("AlterarCliente.html").forward(request, response);
		} else if (cVerificado != null && buttonExcluir != null) {
			Long idEndereco = cVerificado.getId();

			EnderecoDao enderecodao = new EnderecoDao();

			dao.exclui(cVerificado);
			enderecodao.excluiEndereco(idEndereco);

			request.getRequestDispatcher("Teste.html").forward(request, response);
		}

		else {
			request.getRequestDispatcher("403.html").forward(request, response);
		}

	}
}
