package br.edu.ifg.proi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import br.edu.ifg.proi.model.Cliente;
import br.edu.ifg.proi.model.Endereco;

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
					+ "nome VARCHAR(255)," + "cpf VARCHAR(14)," + "email VARCHAR(255)," + "senha VARCHAR(100),"
					+ "telefone VARCHAR(255)," + "endereco MEDIUMINT(9)," + "primary key (id),"
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
		String sql = "insert into cliente " + "(nome,cpf,email,senha,telefone,endereco)" + " values (?,?,?,?,?,?)";
		try {
			// prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sql);

			// seta os valores
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getCpf());
			stmt.setString(3, cliente.getEmail());
			stmt.setString(4, cliente.getSenha());
			stmt.setString(5, cliente.getTelefone());
			stmt.setLong(6, idEndereco);

			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void alterar(Cliente cliente) {
		try {
			String sql = "UPDATE CLIENTE SET nome = ?, cpf = ?, email = ?, telefone = ?, senha = ? WHERE id = ?;";

			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getCpf());
			stmt.setString(3, cliente.getEmail());
			stmt.setString(4, cliente.getTelefone());
			stmt.setString(5, cliente.getSenha());
			stmt.setLong(6, cliente.getId());

			stmt.execute();
			stmt.close();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void exclui(Cliente cliente) {
		try {
			String sql = "DELETE FROM CLIENTE WHERE cpf = ? ;";

			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, cliente.getCpf());

			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public Map<String, String> buscarEmailESenha() {
		Map<String, String> passwordMap = new HashMap<>();
		try {
			String sql = "SELECT email, senha FROM CLIENTE ;";

			PreparedStatement stmt = connection.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				passwordMap.put(rs.getString("email"), rs.getString("senha"));
			}
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return passwordMap;

	}

	public Cliente buscaCliente(String cpf) {
		Cliente cli = new Cliente();
		try {
			String sql = "SELECT * FROM CLIENTE WHERE cpf = ?;";

			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, cpf);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				cli.setId(rs.getLong("id"));
				cli.setCpf(rs.getString("cpf"));
				cli.setEmail(rs.getString("email"));
				cli.setSenha(rs.getString("senha"));
				cli.setNome(rs.getString("nome"));
				cli.setTelefone(rs.getString("telefone"));

				Endereco end = new Endereco();
				end.setId(rs.getLong("endereco"));
				cli.setEndereco(end);

			}
			stmt.execute();
			stmt.close();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return cli;
	}
}
