package br.edu.ifg.proi.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifg.proi.dao.ClienteDao;
import br.edu.ifg.proi.dao.EnderecoDao;
import br.edu.ifg.proi.model.Cliente;
import br.edu.ifg.proi.model.Endereco;

@WebServlet("/manutencaoCliente2")
public class AtualizarDadosCadastraisServlet extends HttpServlet {

	public void init() throws ServletException {
		
		     getServletContext().setAttribute("nome", "Ramon");
		
		    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		PrintWriter out = response.getWriter();

		// pegando os par�metros do request
		String nome = request.getParameter("nome");
		String cpf = request.getParameter("cpf");
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		String telefone = request.getParameter("telefone");
		// endereco
		String logradouro = request.getParameter("logradouro");
		int numero = Integer.valueOf(request.getParameter("numero"));
		String complemento = request.getParameter("complemento");
		String cep = request.getParameter("cep");
		String estado = request.getParameter("estado");
		String cidade = request.getParameter("cidade");

		// monta um objeto Cliente
		Cliente cliente = new Cliente();
		cliente.setNome(nome);
		cliente.setEmail(email);
		cliente.setSenha(senha);
		cliente.setCpf(cpf);
		cliente.setTelefone(telefone);

		Endereco endereco = new Endereco();
		endereco.setLogradouro(logradouro);
		endereco.setNumero(numero);
		endereco.setComplemento(complemento);
		endereco.setCep(cep);
		endereco.setEstado(estado);
		endereco.setCidade(cidade);

		// salva o Endereco
		EnderecoDao enderecodao = new EnderecoDao();
		int idEndereco = enderecodao.adiciona(endereco);

		// salva o Cliente
		ClienteDao dao = new ClienteDao();
		dao.adiciona(cliente, idEndereco);

		// imprime o nome do Cliente que foi adicionado
		out.println("<html>");
		out.println("<body>");
		out.println("Cliente " + cliente.getNome() + " adicionado com sucesso");
		out.println("</body>");
		out.println("</html>");
	}
}
