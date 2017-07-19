package br.edu.ifg.proi.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifg.proi.dao.ProdutoDao;
import br.edu.ifg.proi.model.Produto;

@WebServlet("/IncluirProduto")
public class IncluirProdutoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String descricao = request.getParameter("descricao");
		String marca = request.getParameter("marca");
		String preco_unitario = request.getParameter("preco_unit");
		String fornecedor = request.getParameter("fornecedor");
		String qtd_estoque = request.getParameter("qtd_estoque");

		Produto produto = new Produto();

		produto.setDescricao(descricao);
		produto.setMarca(marca);
		produto.setPreco_unit(Double.parseDouble(preco_unitario));
		produto.setFornecedor(fornecedor);
		produto.setQtd_estoque(Integer.parseInt(qtd_estoque));

		ProdutoDao dao = new ProdutoDao();
		dao.incluir(produto);

		response.sendRedirect("sucesso-inclusao-produto.html");

	}
}
