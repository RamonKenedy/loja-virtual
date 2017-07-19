package br.edu.ifg.proi.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifg.proi.dao.ProdutoDao;

@WebServlet("/Excluir")
public class ExcluirProdutoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		PrintWriter out = response.getWriter();

		String id = request.getParameter("id");

		ProdutoDao dao = new ProdutoDao();

		dao.deletar(Long.valueOf(id));

		out.println("<html>");
		out.println("<body>");
		out.println("Produto Excluido!");
		out.println("<form action=" + "Manutencao" + "method = " + "post>");
		out.println("<a href = 'http://localhost:8080/SistemaDeCompras/Manutencao'>Voltar</a>");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");

	}
}
