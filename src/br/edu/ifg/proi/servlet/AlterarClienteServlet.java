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
import br.edu.ifg.proi.util.LayoutServletUtil;

@WebServlet(name = "AlterarClienteServlet", urlPatterns = { "/alterar-cliente" })
public class AlterarClienteServlet extends HttpServlet {

	Long idCliente;
	Long idEndereco;

	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		String cpfBusca = request.getParameter("cpfBusca");

		if (cpfBusca != null) {
			ClienteDao dao = new ClienteDao();
			Cliente cliente = dao.buscaCliente(cpfBusca);
			idCliente = cliente.getId();
			idEndereco = cliente.getEndereco().getId();
			EnderecoDao endDao = new EnderecoDao();
			Endereco end = endDao.buscaEndereco(cliente.getEndereco().getId());

			// Codigo que insere o cabeçalho da pagina HTML
			LayoutServletUtil.getCabecalhoLayout(out);

			out.println("<section class=\"aw-layout-content  js-content\">");

			out.println("<div class=\"page-header\">");
			out.println("<div class=\"container-fluid\">");
			out.println("<h1>Cadastro de cliente</h1>");
			out.println("</div>");
			out.println("</div>");

			out.println("<div class=\"container-fluid\">");

			out.println("<form action=\"alterar-cliente\" class=\"form-vertical  js-form-loading\">");

			out.println("<div class=\"form-group\">");
			out.println("<label for=\"input-produto-nome\">Nome</label>");
			out.println("<input id=\"input-produto-nome\" type=\"text\" class=\"form-control\" name=\"nome\" value=\""+cliente.getNome() + "\"/>");
			out.println("</div>");

			out.println("<div class=\"row\">");
			out.println("<div class=\"form-group col-sm-4\">");
			out.println("<label for=\"cpf\" class=\"control-label\">CPF</label>");
			out.println("<input type=\"text\" class=\"form-control\"  id=\"cpf\" name=\"cpf\" value=\""
					+ cliente.getCpf() + "\"/>");
			out.println("</div>");
			out.println("<div class=\"form-group  col-sm-4\">");
			out.println("<label for=\"telefone\">Telefone</label>");
			out.println(
					"<input type=\"text\" class=\"form-control  js-phone-number\" id=\"telefone\" name=\"telefone\" value=\""
							+ cliente.getTelefone() + "\"/>");
			out.println("</div>");

			out.println("</div>");

			out.println("<div class=\"row\">");
			out.println("<div class=\"form-group  col-sm-4\" >");
			out.println("<label for=\"email\" class=\"control-label\">E-mail</label>");
			out.println("<input type=\"text\" class=\"form-control\" id=\"email\" name=\"email\" value=\""
					+ cliente.getEmail() + "\"/>");
			out.println("</div>");
			out.println("<div class=\"form-group  col-sm-4\">");
			out.println("<label for=\"complemento\">Senha</label>");
			out.println("<input type=\"password\" class=\"form-control\" id=\"senha\" name=\"senha\" value=\""
					+ cliente.getSenha() + "\"/>");
			out.println("</div>");
			out.println("</div>");

			out.println("<div class=\"row\">");
			out.println("<div class=\"form-group col-sm-12\">");
			out.println("<label for=\"logradouro\">Logradouro</label>");
			out.println("<input type=\"text\" class=\"form-control\" id=\"logradouro\" name=\"logradouro\"  value=\""
					+ end.getLogradouro() + "\"/>");
			out.println("</div>");

			out.println("<div class=\"form-group  col-sm-4\">");
			out.println("<label for=\"numero\">Número</label>");
			out.println("<input type=\"text\" class=\"form-control\" id=\"numero\" name=\"numero\" value=\""
					+ end.getNumero() + "\"/>");
			out.println("</div>");

			out.println("<div class=\"form-group  col-sm-4\">");
			out.println("<label for=\"complemento\">Complemento</label>");
			out.println("<input type=\"text\" class=\"form-control\" id=\"complemento\" name=\"complemento\" value=\""
					+ end.getComplemento() + "\"/>");
			out.println("</div>");
			out.println("</div>");

			out.println("<div class=\"row\">");
			out.println("<div class=\"form-group  col-sm-4\">");
			out.println("<label for=\"cep\">CEP</label>");
			out.println("<input type=\"text\" class=\"form-control  js-cep\" id=\"cep\" name=\"cep\"  value=\""
					+ end.getCep() + "\"/>");
			out.println("</div>");

			out.println("<div class=\"form-group  col-sm-4\">");
			out.println("<label for=\"estado\">Estado</label>");
			out.println("<input type=\"text\" class=\"form-control\" id=\"estado\" name=\"estado\" value=\""
					+ end.getEstado() + "\"/>");
			out.println("</div>");

			out.println("<div class=\"form-group  col-sm-4\">");
			out.println("<label  for=\"cidade\">Cidade</label>");
			out.println("<input type=\"text\" class=\"form-control\" id=\"cidade\" name=\"cidade\" value=\""
					+ end.getCidade() + "\"/>");
			out.println("</div>");
			out.println("</div>");

			out.println("<div class=\"form-group\">");
			out.println("<button class=\"btn  btn-primary\" type=\"submit\" >Salvar</button>");
			out.println("<a href=\"home.html\" class=\"btn  btn-default\">Cancelar</a>");
			out.println("</div>");

			out.println("</form>");
			out.println("</div>");

			out.println("</section>");

			// Codigo que insere o rodapé da pagina HTML
			LayoutServletUtil.getRodapeLayout(out);

			// METODO QUE SERÁ CHAMADO QUANDO SALVAR O CLIENTE ALTERADO
		} else {
			// pegando os parametros do request
			String nome = request.getParameter("nome");
			String cpf = request.getParameter("cpf");
			String email = request.getParameter("email");
			String senha = request.getParameter("senha");
			String telefone = request.getParameter("telefone");
			// endereco
			String logradouro = request.getParameter("logradouro");
			int numero =  Integer.valueOf(request.getParameter("numero"));
			String complemento = request.getParameter("complemento");
			String cep = request.getParameter("cep");
			String estado = request.getParameter("estado");
			String cidade = request.getParameter("cidade");

			// monta um objeto Cliente
			Cliente cliente = new Cliente();
			cliente.setId(idCliente);
			cliente.setNome(nome);
			cliente.setEmail(email);
			cliente.setSenha(senha);
			cliente.setCpf(cpf);
			cliente.setTelefone(telefone);

			Endereco endereco = new Endereco();
			endereco.setId(idEndereco);
			endereco.setLogradouro(logradouro);
			endereco.setNumero(numero);
			endereco.setComplemento(complemento);
			endereco.setCep(cep);
			endereco.setEstado(estado);
			endereco.setCidade(cidade);

			// salva o Endereco
			EnderecoDao enderecodao = new EnderecoDao();
			enderecodao.alterarEndereco(endereco);

			// salva o Cliente
			ClienteDao dao = new ClienteDao();
			dao.alterar(cliente);

			// direciona pra pagina de sucesso
			response.sendRedirect("sucesso-alteracao-cliente.html");
		}

	}
}
