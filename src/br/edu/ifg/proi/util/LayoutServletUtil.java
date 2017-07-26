package br.edu.ifg.proi.util;

import java.io.PrintWriter;

public class LayoutServletUtil {

	public static void getCabecalhoLayout(PrintWriter out) {
		out.println("<!DOCTYPE html>");
		out.println("<html lang= \"pt\">");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\"/>");
		out.println("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" /> ");
		out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"/> ");
		out.println("<title>Loja Virtual</title> ");

		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"stylesheets/vendors.min.css\"/>");
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"stylesheets/algaworks.min.css\"/>");
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"stylesheets/application.css\"/>");
		out.println("</head>");

		out.println("<body>");
		out.println("<div class=\"aw-layout-page\"> ");
		out.println(
				"<nav class=\"navbar  navbar-fixed-top  navbar-default  js-sticky-reference\" id=\"main-navbar\"> ");
		out.println("<div class=\"container-fluid\"> ");
		out.println("<div class=\"navbar-header\"> ");
		out.println("<ul class=\"nav  navbar-nav\">");
		out.println("<li>");
		out.println("<a href=\"#\" class=\"js-sidebar-toggle\"><i class=\"fa  fa-bars\"></i></a>");
		out.println("</li> ");
		out.println("</ul> ");
		out.println("</div> ");
		out.println("</div> ");
		out.println("</nav>");

		out.println("<aside class=\"aw-layout-sidebar  js-sidebar\">");
		out.println("<div class=\"aw-layout-sidebar__content\">");

		out.println("<nav class=\"aw-menu  js-menu\">");
		out.println("<ul class=\"aw-menu__list\">");
		out.println("<li class=\"aw-menu__item  is-active\">");
		out.println("<a href=\"#\">");
		out.println("<i class=\"fa  fa-fw  fa-file-text\"></i><span>Cadastros</span>");
		out.println("<i class=\"aw-menu__navigation-icon  fa\"></i>");
		out.println("</a>");

		out.println("<ul class=\"aw-menu__list  aw-menu__list--sublist\">");
		out.println(
				"<li class=\"aw-menu__item  aw-menu__item--link\"><a href=\"manutencao-cliente.html\">Cadastro de cliente</a></li>");
		out.println("</ul>");
		out.println("<ul class=\"aw-menu__list  aw-menu__list--sublist\">");
		out.println(
				"<li class=\"aw-menu__item  aw-menu__item--link\"><a href=\"Produtos\">Cadastro de produtos</a></li>");
		out.println("</ul>");
		out.println("</li>");

		out.println("</ul>");
		out.println("</nav>");

		out.println("</div>");
		out.println("</aside>");

	}

	public static void getRodapeLayout(PrintWriter out) {

		out.println("<footer class=\"aw-layout-footer  js-content\"> ");
		out.println("<div class=\"container-fluid\"> ");
		out.println(
				"<span class=\"aw-footer-disclaimer\">&copy; 2017 IFG - Loja Virtual. Todos os direitos reservados.</span> ");
		out.println("</div> ");
		out.println("</footer> ");
		out.println("</div> ");

		out.println("<script src=\"javascripts/vendors.min.js\"></script>");
		out.println("<script src=\"javascripts/algaworks.min.js\"></script>");

		out.println("</body>");
		out.println("</html>");

}

}
