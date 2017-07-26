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

@WebServlet("/comprarProduto")

public class ComprarProdutoServlet extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		List<Produto> listaCompra = new ArrayList<Produto>();
		PrintWriter out = response.getWriter();
		Produto produto = null;
		// Codigo que insere o cabeçalho da pagina HTML
		LayoutServletUtil.getCabecalhoLayout(out);
		
		out.println("<section class=\"aw-layout-content  js-content\">");

		out.println("<div class=\"page-header\">");
		out.println("<div class=\"container-fluid\">");
		out.println("<h1>Lista de Compras</h1>");
		out.println("</div>");
		out.println("</div>");

		out.println("<div class=\"container-fluid\">");
		ProdutoDao dao = new ProdutoDao();
		String compra = request.getParameter("compra");

		String[] itens = compra.split("\n");
		String[][] pedidos = new String[itens.length][2];

		for (int i = 0; i < itens.length; i++) {
			produto = new Produto();

			pedidos[i] = itens[i].split(" ");
			for (int j = 0; j < 2; j++) {
				String a = pedidos[i][j];
				if (j == 0) {
					produto.setId(Long.parseLong(a));
					produto = dao.consultaProdutoId(Long.parseLong(a));
				} else {
					produto.setQtd_estoque(Double.parseDouble(a));
				}

			}
			listaCompra.add(produto);
		}

		
		dao.BaixarEstoque(listaCompra);

		out.println("<form action=\"class=\"form-vertical  js-form-loading\">");

		out.println("<table class = \"table table-hover\">");
		out.println("<caption>Lista de Produtos</caption>");
		out.println("<thead>");
		out.println("<tr>");
		out.println("<th scope= \"col\" >ID</th>");
		out.println("<th scope= \"col\" >Descricao</th>");
		out.println("<th scope= \"col\" >Marca</th>");
		out.println("<th scope= \"col\" >Preco Unitaio</th>");
		out.println("<th scope= \"col\" >Quantidade</th>");
		out.println("<th scope= \"col\" >Fornecedor</th> ");
		out.println("</tr>");
		out.println("</thead>");

		for (Produto p : listaCompra) {
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
		
		
		
		out.println("<a href=\"comprar\" class=\"btn  btn-default\">Voltar</a>");
		
		out.println("</section>");

		out.println("<input type=\"text\" id=\"compra\" name=\"compra\">");

		LayoutServletUtil.getRodapeLayout(out);

	}

}
