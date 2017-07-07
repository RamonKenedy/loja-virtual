package br.edu.ifg.proi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import br.edu.ifg.proi.model.Cliente;

public class ClienteDao {

	// a conexão com o banco de dados
	private Connection connection;

	public ClienteDao() {
		this.connection = new ConnectionFactory().getConnection();

		criarTabela();
	}

	public void criarTabela() {
		try {
			String expression = "CREATE TABLE IF NOT EXISTS cliente (" + "id MEDIUMINT NOT NULL AUTO_INCREMENT,"
					+ "nome VARCHAR(255)," + "cpf VARCHAR(14)," + "email VARCHAR(255)," + "telefone VARCHAR(255),"
					+ "endereco MEDIUMINT(9)," + "primary key (id),"
					+ " CONSTRAINT `endereco_fk` FOREIGN KEY (`endereco`) REFERENCES `endereco` (`id`)" + ");";

			// Criando o statement
			Statement st = connection.createStatement();

			// Executando a consulta
			int i = st.executeUpdate(expression);
			if (i == -1) {
				throw new RuntimeException("db error : " + expression);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void adiciona(Cliente cliente, int idEndereco) {
		String sql = "insert into cliente " + "(nome,cpf,email,telefone,endereco)" + " values (?,?,?,?,?)";
		try {
			// prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sql);

			// seta os valores
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getCpf());
			stmt.setString(3, cliente.getEmail());
			stmt.setString(4, cliente.getTelefone());
			stmt.setLong(5, idEndereco);

			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
