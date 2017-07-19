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

@WebServlet("/Buscar")
public class BuscarProdutoServlet extends HttpServlet {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		PrintWriter out = response.getWriter();

		String id = request.getParameter("id");

		ProdutoDao dao = new ProdutoDao();

		Produto pVerificado = dao.consultaProdutoId(Long.valueOf(id));

		if (pVerificado != null) {

			out.println("<!DOCTYPE html>");
			out.println("<html lang=" + "pt" + ">");
			out.println("<head>");
			out.println("<meta charset=" + "UTF-8" + "/> ");
			out.println("<meta http-equiv=" + "X-UA-Compatible" + " content=" + "IE=edge" + "/> ");
			out.println("<meta name=" + "viewport" + " content=" + "width=device-width, initial-scale=1" + "/> ");
			out.println("<title>Loja Virtual</title> ");

			out.println("<link rel='stylesheet' type='text/css' href='" + request.getContextPath()
					+ "/stylesheets/vendors.min.css' />");
			out.println("<link rel='stylesheet' type='text/css' href='" + request.getContextPath()
					+ "/stylesheets/algaworks.min.css' />");
			out.println("<link rel='stylesheet' type='text/css' href='" + request.getContextPath()
					+ "/stylesheets/application.css' />");

			out.println("</head>");
			out.println("<body>");

			out.println("<div class=" + "aw-layout-page" + "> "); // div aberta
			out.println("<nav class=" + "navbar  navbar-fixed-top  navbar-default  js-sticky-reference" + " id="
					+ "main-navbar" + " > ");
			out.println("<div class=" + "container-fluid" + "> ");
			out.println("<div class=" + "navbar-header" + "> ");
			out.println("<ul class=" + "nav  navbar-nav" + ">");
			out.println("<li>");
			out.println(
					"<a href=" + "#" + " class=" + "js-sidebar-toggle" + "><i class=" + "fa  fa-bars" + "></i></a>");
			out.println("</li> ");
			out.println("</ul> ");
			out.println("</div> ");
			out.println("</div> ");
			out.println("</nav>");
			//
			out.println("<aside class=" + "aw-layout-sidebar  js-sidebar" + ">");
			out.println("<div class=" + "aw-layout-sidebar__content" + "> ");
			out.println("<nav class=" + "aw-menu  js-menu" + "> ");
			out.println("<ul class=" + "aw-menu__list" + "> ");
			out.println("<li class=" + "aw-menu__item  is-active" + "> ");
			out.println("<a href=" + "#" + "> ");
			out.println("<i class=" + "fa  fa-fw  fa-file-text" + "></i><span>Cadastros</span> ");
			out.println("<i class=" + "aw-menu__navigation-icon  fa" + "></i> ");
			out.println("</a>");
			out.println("<ul class=" + "aw-menu__list  aw-menu__list--sublist" + ">");
			out.println("<li class=" + "aw-menu__item  aw-menu__item--link" + "><a href=" + "manutencao-cliente.html"
					+ ">Cadastro de usuário</a></li>");
			out.println("</ul> ");
			out.println("</li> ");
			out.println("</ul> ");
			out.println("</nav>");
			out.println("</div>");
			out.println("</aside> ");

			//
			out.println("<section class=" + "aw-layout-content  js-content" + ">");
			out.println("<div class=" + "page-header" + "> ");
			out.println("<div class=" + "container-fluid" + "> ");

			out.println("</div> ");
			out.println("</div> ");
			out.println("<div class=" + "container-fluid" + "> ");

			out.println("<form action=" + "Incluir" + "class=" + "form-vertical  js-form-loading" + " method = "
					+ "post" + ">");

			out.println("<div class=" + "form-group" + ">");
			out.println("<label for=" + "input-produto-nome" + ">Id Produto</label> ");
			out.println("<input id=" + "input-produto-nome" + " type=" + "text" + "class=" + "form-control" + " name="
					+ "id" + "/> ");
			out.println("</div> ");
			out.println(" <div class=" + "row" + ">");
			out.println("<div class=" + "form-group col-sm-4" + "> ");
			out.println("<label for=" + "marca" + " >Marca</label ");
			out.println("<input type=" + "text" + " class=" + "form-control js-phone-number" + "id=" + "marca"
					+ " name=" + "marca" + "/> ");
			out.println("</div> ");
			out.println("<div class=" + "form-group col-sm-4" + "> ");
			out.println("<label for=" + "preco" + ">Preco Unitario</label> ");
			out.println("<input type=" + "text" + " class=" + "form-control js-phone-number" + "id=" + "preco"
					+ " name=" + "preco_unit" + "/> ");
			out.println("</div> ");
			out.println("<div class=" + "form-group  col-sm-4" + "> ");
			out.println("<label for=" + "fornecedor" + "class=" + "control-label" + ">Fornecedor</label> ");
			out.println("<input type=" + "text" + "class=" + "form-control" + " id=" + "fornecedor" + " name="
					+ "fornecedor" + "/> ");
			out.println("</div> ");
			out.println("</div> ");
			out.println(" <div class=" + "row" + ">");
			out.println("<div class=" + "form-group  col-sm-12" + "> ");
			out.println("<label for=" + "quantidade" + ">Quantidade Estoque</label> ");
			out.println("<input type=" + "text" + " class=" + "form-control" + "id=" + "logradouro" + "name="
					+ "qtd_estoque" + "/> ");
			out.println("</div> ");
			out.println("</div> ");
			out.println("<div class=" + "form-group" + ">");
			out.println("<button class=" + "btn  btn-primary" + " type=" + "submit" + ">Incluir</button> ");
			out.println("<a href=" + "http://localhost:8080/loja-virtual/Manutencao" + "class=" + "btn  btn-default"
					+ ">Cancelar</a> ");
			out.println("</div> ");
			out.println(" <button>Alterar</button>");
			out.println("</form> ");
			out.println("</div> ");

			out.println("</section> ");
			out.println("<footer class=" + "aw-layout-footer  js-content" + "> ");
			out.println("<div class=" + "container-fluid" + "> ");
			out.println("<span class=" + "aw-footer-disclaimer"
					+ ">&copy; 2017 IFG - Loja Virtual. Todos os direitos reservados.</span> ");
			out.println("</div> ");
			out.println("</footer> ");
			out.println("</div> ");
			out.println("</body>");
			out.println("</html>");
		} else {
			out.println("<html>");
			out.println("<body>");
			out.println("Produto n�o cadastrado!");

			out.println("<a href = 'http://localhost:8080/loja-virtual/Manutencao'>Voltar</a>");
			out.println("</form>");
			out.println("</body>");
			out.println("</head>");
			out.println("</html>");
		}
		/*
		 * 
		 * 
		 * out.println("<html>"); out.println("<body>"); out.println(
		 * "<h1>Lista de Cadastrados</h1>"); out.println("<table border = 1 >");
		 * out.println("<tr>"); out.println("<th>ID</th> ");
		 * out.println("<th>Marca</th>"); out.println("<th>Preco Unit�rio</th>"
		 * ); out.println("<th>ESTOQUE</th>");
		 * out.println("<th>FORNECEDOR</th>"); out.println("</tr>");
		 * out.println("<tr>"); out.println("<th>"+pVerificado.getId()+"</th>");
		 * out.println("<th>"+pVerificado.getMarca()+"</th>");
		 * out.println("<th>"+pVerificado.getPreco_unit()+"</th>");
		 * out.println("<th>"+pVerificado.getQtd_estoque()+"</th>");
		 * out.println("<th>"+pVerificado.getFornecedor()+"</th>");
		 * 
		 * out.println(
		 * "<a href = 'http://localhost:8080/SistemaDeCompras/Teste.html'>Alterar</a>"
		 * ); out.println("</body>"); out.println("</html>");
		 */

	}
}
