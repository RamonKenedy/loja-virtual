package br.edu.ifg.proi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.edu.ifg.proi.model.Cliente;
import br.edu.ifg.proi.model.Endereco;

public class EnderecoDao {

	// a conexão com o banco de dados
	private Connection connection;

	public EnderecoDao() {
		this.connection = new ConnectionFactory().getConnection();

		criarTabela();
	}

	public void criarTabela() {
		try {
			String expression = "CREATE TABLE IF NOT EXISTS endereco (" + "id MEDIUMINT NOT NULL AUTO_INCREMENT,"
					+ "logradouro VARCHAR(255)," + "numero INT(11)," + "complemento VARCHAR(100)," + "cep VARCHAR(8),"
					+ "estado VARCHAR(100)," + "cidade VARCHAR(255)," + "primary key (id)" + ");";

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

	public int adiciona(Endereco endereco) {
		String sql = "insert into endereco " + "(logradouro,numero,complemento,cep,estado,cidade)"
				+ " values (?,?,?,?,?,?)";
		int last_inserted_id = 0;
		try {
			// prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			// seta os valores
			stmt.setString(1, endereco.getLogradouro());
			stmt.setInt(2, endereco.getNumero());
			stmt.setString(3, endereco.getComplemento());
			stmt.setString(4, endereco.getCep());
			stmt.setString(5, endereco.getEstado());
			stmt.setString(6, endereco.getCidade());

			// executa
			stmt.executeUpdate();
			// Logica para obter o id do endereço salvo no banco
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				last_inserted_id = rs.getInt(1);
			}
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return last_inserted_id;
	}

	public void excluiEndereco(Long idEndereco) {

		try {
			String sql = "DELETE FROM ENDERECO WHERE ID = ?;";

			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setLong(1, idEndereco);

			stmt.execute();
			stmt.close();

		} catch (Exception e) {
			System.out.println("Erro sql" + e.getMessage());
		}

	}

	public int alterarEndereco(Endereco endereco) {
		int last_inserted_id = 0;
		try {
			String sql = "UPDATE ENDERECO SET logradouro = ?, numero = ?, complemento = ?,"
					+ "cep = ?, estado = ?,cidade = ? WHERE complemento = ? ;";

			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, endereco.getLogradouro());
			stmt.setInt(2, endereco.getNumero());
			stmt.setString(3, endereco.getComplemento());
			stmt.setString(4, endereco.getCep());
			stmt.setString(5, endereco.getEstado());
			stmt.setString(6, endereco.getCidade());
			stmt.setString(7, endereco.getComplemento());

			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return last_inserted_id;

	}

	public Endereco buscaEndereco(Long id) {
		Endereco end = new Endereco();
		try {
			String sql = "SELECT * FROM ENDERECO WHERE ID = ?;";

			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setLong(1, id);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				end.setId(rs.getLong("id"));
				end.setLogradouro(rs.getString("logradouro"));
				end.setCep(rs.getString("cep"));
				end.setCidade(rs.getString("cidade"));
				end.setComplemento(rs.getString("complemento"));
				end.setEstado(rs.getString("estado"));
				end.setNumero(rs.getInt("numero"));

			}
			stmt.execute();
			stmt.close();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return end;
	}
}
