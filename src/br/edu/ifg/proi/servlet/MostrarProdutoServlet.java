package br.edu.ifg.proi.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifg.proi.dao.ProdutoDao;
import br.edu.ifg.proi.model.Produto;
import br.edu.ifg.proi.util.LayoutServletUtil;

@WebServlet(name = "ProdutosServlet", urlPatterns = { "/Produtos" })
public class MostrarProdutoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		List<Produto> listaProduto = new ArrayList<Produto>();

		ProdutoDao dao = new ProdutoDao();
		listaProduto = dao.consultarProdutos();

		// Codigo que insere o cabeçalho da pagina HTML
		LayoutServletUtil.getCabecalhoLayout(out);

		out.println("<section class=\"aw-layout-content  js-content\">");
		out.println("<div class=\"page-header\"> ");
		out.println("<div class=\"container-fluid\"> ");
		out.println("<h1>Produtos</h1>");
		out.println("</div> ");
		out.println("</div> ");
		out.println("<div class=\"container-fluid\"> ");
		out.println("<form action=\"class=\"form-vertical  js-form-loading\">");

		out.println("<table class = \"table table-hover\">");
		out.println("<caption>Lista de Produtos</caption>");
		out.println("<thead>");
		out.println("<tr>");
		out.println("<th scope= \"col\" >ID</th>");
		out.println("<th scope= \"col\" >Descricao</th>");
		out.println("<th scope= \"col\" >Marca</th>");
		out.println("<th scope= \"col\" >Preco Unitaio</th>");
		out.println("<th scope= \"col\" >Estoque</th>");
		out.println("<th scope= \"col\" >Fornecedor</th> ");
		out.println("</tr>");
		out.println("</thead>");

		for (Produto p : listaProduto) {
			out.println("<tbody>");
			out.println("<tr>");
			out.println("<td>" + p.getId() + "</td>");
			out.println("<td>" + p.getDescricao() + "</td>");
			out.println("<td>" + p.getMarca() + "</td>");
			out.println("<td>" + p.getPreco_unit() + "</td>");
			out.println("<td>" + p.getQtd_estoque() + "</td>");
			out.println("<td>" + p.getFornecedor() + "</td>");

		}

		out.println("</tr>");
		out.println("</tbody>");
		out.println("</table>");
		out.println("<a href = \"http://localhost:8080/loja-virtual/IncluirProduto.html\">Incluir</a>");
		out.println("<a href = \"http://localhost:8080/loja-virtual/AlterarProduto.html\">Alterar</a>");
		out.println("<a href = \"http://localhost:8080/loja-virtual/ExcluirProduto.html\">Excluir</a>");

		out.println("</div>");
		out.println("</form>");
		out.println("</section> ");

		// Codigo que insere o rodapé da pagina HTML
		LayoutServletUtil.getRodapeLayout(out);

	}

}
