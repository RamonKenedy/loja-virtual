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

@WebServlet("/Alterar")
public class AlterarProdutoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		PrintWriter out = response.getWriter();

		String marca = request.getParameter("marca");
		String descricao = request.getParameter("descricao");
		String preco_unitario = request.getParameter("preco_unit");
		String fornecedor = request.getParameter("fornecedor");
		String qtd_estoque = request.getParameter("qtd_estoque");

		Produto produto = new Produto();

		produto.setDescricao(descricao);
		produto.setMarca(marca);
		produto.setPreco_unit(Double.parseDouble(preco_unitario));
		produto.setFornecedor(fornecedor);
		produto.setQtd_estoque(Double.valueOf(qtd_estoque));

		ProdutoDao dao = new ProdutoDao();

		dao.alterar(produto);

		request.getRequestDispatcher("Manutencao").forward(request, response);

	}

}
