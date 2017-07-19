package br.edu.ifg.proi.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ComprarProdutoServlet extends HttpServlet{
       
	 public void doPost(HttpServletRequest request, HttpServletResponse response)
	 throws IOException, ServletException{
		 
		 String codigo = request.getParameter("codigo");
		 String qtd = request.getParameter("qtd_estoque");
		 
		 List<String> listaProduto = new ArrayList<String>();
		 
		 listaProduto.add(request.getParameter(getServletName()));
		 
	 }
	
}
