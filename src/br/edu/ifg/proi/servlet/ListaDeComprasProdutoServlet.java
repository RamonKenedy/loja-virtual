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

@WebServlet("/comprar")
public class ListaDeComprasProdutoServlet extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response)
			 throws IOException, ServletException{
				
				 //String compra = request.getParameter("comprar");
				PrintWriter out = response.getWriter();
				
				LayoutServletUtil.getCabecalhoLayout(out);
				
				List<Produto> listaProduto = new ArrayList<Produto>();
				ProdutoDao dao = new ProdutoDao();
				listaProduto = dao.consultarProdutos();
				
				out.println("<section class=\"aw-layout-content  js-content\">");

				out.println("<div class=\"page-header\">");
				out.println("<div class=\"container-fluid\">");
				out.println("<h1>Lista de Compras</h1>");
				out.println("</div>");
				out.println("</div>");
				
				out.println("<div class=\"container-fluid\">");
				
				out.println("<form class=\"form-vertical  js-form-loading\">");

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

				out.println("</div>");
				out.println("</form>");
				
				
				out.println("<form action = \"comprarProduto\" method =\"post\">");
				
				 out.println("<div class= \"form-group\">");
				 out.println("<label for= \"comment\">Informe o codigo e a quantidade do produto:</label>");
				 out.println("<textarea class=\"form-control\" rows=\"5\" id=\"comment\""
				 		+ "<input type=\"text\" id=\"compra\" name=\"compra\">"+"</textarea>");
				 out.println("<button class=\"btn  btn-default\">Comprar</button>");
				 out.println("</div>");
				
				out.println("</form>");
				out.println("</section>");
				
				LayoutServletUtil.getRodapeLayout(out);
				

				 
			 }
			
		}

