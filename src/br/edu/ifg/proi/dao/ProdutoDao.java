package br.edu.ifg.proi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifg.proi.model.Produto;

public class ProdutoDao {

	private Connection connection;

	public ProdutoDao() {

		this.connection = new ConnectionFactory().getConnection();

		criarTabela();
	}

	public void criarTabela() {
		try {
			String expression = "CREATE TABLE IF NOT EXISTS Produto (" + "id MEDIUMINT NOT NULL AUTO_INCREMENT,"
					+ "descricao VARCHAR(255)," + "marca VARCHAR(100)," + "preco_unit DOUBLE,"
					+ "fornecedor VARCHAR(255)," + "qtd_estoque DOUBLE," + "primary key (id)" + ");";

			Statement st = connection.createStatement();

			int i = st.executeUpdate(expression);
			if (i == -1) {
				throw new RuntimeException("db error : " + expression);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void incluir(Produto produto) {
		try {
			String sql = "insert into Produto(descricao, marca, preco_unit,fornecedor,qtd_estoque) "
					+ "values(?,?,?,?,?)";

			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, produto.getDescricao());
			stmt.setString(2, produto.getMarca());
			stmt.setDouble(3, produto.getPreco_unit());
			stmt.setString(4, produto.getFornecedor());
			stmt.setDouble(5, produto.getQtd_estoque());

			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Produto> consultarProdutos() {

		List<Produto> listaProduto = new ArrayList<Produto>();
		try {

			String sql = "select * FROM PRODUTO";

			Statement st = connection.createStatement();

			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				Produto produto = consultaProdutoId(rs.getLong("id"));
				listaProduto.add(produto);
			}
			st.close();
			rs.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return listaProduto;
	}

	public Produto consultaProdutoId(Long id) {
		Produto produto = null;
		try {

			String sql = "Select * FROM Produto WHERE id = ? ;";

			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setLong(1, id);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				produto = new Produto();
				produto.setId(rs.getLong("id"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setMarca(rs.getString("marca"));
				produto.setPreco_unit(rs.getDouble("preco_unit"));
				produto.setFornecedor(rs.getString("fornecedor"));
				produto.setQtd_estoque(rs.getInt("qtd_estoque"));
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return produto;

	}

	public void alterar(Produto produto) {
		try {
			String sql = "Update Produto SET descricao = ?, marca= ?, preco_unit = ?, fornecedor = ?, "
					+ "qtd_estoque = ? WHERE id = ? ;";

			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, produto.getDescricao());
			stmt.setString(2, produto.getMarca());
			stmt.setDouble(3, produto.getPreco_unit());
			stmt.setString(4, produto.getFornecedor());
			stmt.setDouble(5, produto.getQtd_estoque());
			stmt.setLong(6, produto.getId());

			stmt.execute();
			stmt.close();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void deletar(Long id) {

		try {
			String sql = "DELETE FROM Produto WHERE id = ? ;";

			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setLong(1, id);
			stmt.execute();
			stmt.close();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
