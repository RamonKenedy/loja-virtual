package br.edu.ifg.proi.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifg.proi.dao.ProdutoDao;
import br.edu.ifg.proi.model.Produto;
import br.edu.ifg.proi.util.LayoutServletUtil;

@WebServlet(name = "AlterarProdutoServlet", urlPatterns = { "/alterar-produto" })
public class AlterarProdutoServlet extends HttpServlet {
	Long idProduto;

	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		String idBusca = request.getParameter("idBusca");

		if (idBusca != null) {
			ProdutoDao dao = new ProdutoDao();
			Produto produto = dao.consultaProdutoId(Long.valueOf(idBusca));
			if (produto == null) {
				response.sendRedirect("erro-produto-nao-encontrado.html");
			} else {
				idProduto = produto.getId();

				// Codigo que insere o cabeçalho da pagina HTML
				LayoutServletUtil.getCabecalhoLayout(out);

				out.println("<section class=\"aw-layout-content  js-content\">");

				out.println("<div class=\"page-header\">");
				out.println("<div class=\"container-fluid\">");
				out.println("<h1>Cadastro de Produto</h1>");
				out.println("</div>");
				out.println("</div>");

				out.println("<div class=\"container-fluid\">");

				out.println(
						"<form action=\"alterar-produto\" class=\"form-vertical  js-form-loading\" method = \"post\">");

				out.println("<div class=\"form-group\">");
				out.println("<label for=\"input-produto-nome\">Descrição</label>");
				out.println(
						"<input id=\"input-produto-nome\" type=\"text\" class=\"form-control\" name=\"descricao\" value=\""
								+ produto.getDescricao() + "\"/>");
				out.println("</div>");

				out.println("<div class=\"row\">");
				out.println("<div class=\"form-group col-sm-4\">");
				out.println("<label for=\"marca\" class=\"control-label\">Marca</label>");
				out.println("<input type=\"text\" class=\"form-control\"  id=\"marca\" name=\"marca\" value=\""
						+ produto.getMarca() + "\"/>");
				out.println("</div>");
				out.println("<div class=\"form-group  col-sm-4\">");
				out.println("<label for=\"preco\">Preco Unitario</label>");
				out.println(
						"<input type=\"text\" class=\"form-control  js-phone-number\" id=\"preco\" name=\"preco_unit\"value=\""
								+ produto.getPreco_unit() + "\"/>");
				out.println("</div>");
				out.println("<div class=\"form-group col-sm-4\">");
				out.println("<label for=\"qtd_estoque\">Quantidade Estoque</label>");
				out.println(
						"<input type=\"text\" class=\"form-control\" id=\"qtd_estoque\" name=\"qtd_estoque\"value=\""
								+ produto.getQtd_estoque() + "\"/>");
				out.println("</div>");
				out.println("</div>");

				out.println("<div class=\"row\">");
				out.println("<div class=\"form-group  col-sm-12\" >");
				out.println("<label for=\"fornecedor\" class=\"control-label\">Fornecedor</label>");
				out.println("<input type=\"text\" class=\"form-control\" id=\"fornecedor\" name=\"fornecedor\"value=\""
						+ produto.getFornecedor() + "\"/>");
				out.println("</div>");
				out.println("</div>");

				out.println("<div class=\"form-group\">");
				out.println("<button class=\"btn  btn-primary\" type=\"submit\" >Salvar</button>");
				out.println("<a href=\"Produtos\" class=\"btn  btn-default\">Cancelar</a>");

				out.println("</div>");

				out.println("</form>");
				out.println("</div>");

				out.println("</section>");

				// Codigo que insere o rodapé da pagina HTML
				LayoutServletUtil.getRodapeLayout(out);
			}

		} else {

			String marca = request.getParameter("marca");
			String descricao = request.getParameter("descricao");
			String preco_unitario = request.getParameter("preco_unit");
			String fornecedor = request.getParameter("fornecedor");
			String qtd_estoque = request.getParameter("qtd_estoque");

			Produto produto = new Produto();

			produto.setId(idProduto);
			produto.setDescricao(descricao);
			produto.setMarca(marca);
			produto.setPreco_unit(Double.parseDouble(preco_unitario));
			produto.setFornecedor(fornecedor);
			produto.setQtd_estoque(Double.valueOf(qtd_estoque));

			ProdutoDao dao = new ProdutoDao();

			dao.alterar(produto);
			// direciona pra pagina de sucesso
			response.sendRedirect("sucesso-alteracao-produto.html");

		}

	}

}
