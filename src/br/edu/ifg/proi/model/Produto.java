package br.edu.ifg.proi.model;

public class Produto {

	private Long id;
	private String descricao;
	private String marca;
	private double preco_unit;
	private String fornecedor;
	private double qtd_estoque;

	public Produto() {

	}

	public Produto(String descricao, String marca, double preco_unit, String fornecedor, double qtd_estoque) {
		this.descricao = descricao;
		this.marca = marca;
		this.preco_unit = preco_unit;
		this.fornecedor = fornecedor;
		this.qtd_estoque = qtd_estoque;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public double getPreco_unit() {
		return preco_unit;
	}

	public void setPreco_unit(double preco_unit) {
		this.preco_unit = preco_unit;
	}

	public String getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}

	public double getQtd_estoque() {
		return qtd_estoque;
	}

	public void setQtd_estoque(double qtd_estoque) {
		this.qtd_estoque = qtd_estoque;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.id + " - " + this.descricao;
	}

}
