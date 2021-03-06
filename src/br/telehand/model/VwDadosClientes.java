package br.telehand.model;

// Generated 27/03/2014 16:20:36 by Hibernate Tools 4.0.0

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * VwDadosClientes generated by hbm2java
 */
@Entity
@Table(name = "vw_dados_clientes", catalog = "db_sge")
public class VwDadosClientes implements java.io.Serializable {

	private VwDadosClientesId id;

	public VwDadosClientes() {
	}

	public VwDadosClientes(VwDadosClientesId id) {
		this.id = id;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "idCliente", column = @Column(name = "id_cliente", nullable = false)),
			@AttributeOverride(name = "cpf", column = @Column(name = "CPF", precision = 11, scale = 0)),
			@AttributeOverride(name = "cnpj", column = @Column(name = "CNPJ", precision = 14, scale = 0)),
			@AttributeOverride(name = "nmCliente", column = @Column(name = "NM_CLIENTE", nullable = false, length = 20)),
			@AttributeOverride(name = "cdEmail", column = @Column(name = "CD_EMAIL")),
			@AttributeOverride(name = "endereco", column = @Column(name = "ENDERECO", length = 100)),
			@AttributeOverride(name = "numero", column = @Column(name = "NUMERO")),
			@AttributeOverride(name = "complemento", column = @Column(name = "COMPLEMENTO", length = 30)),
			@AttributeOverride(name = "bairro", column = @Column(name = "BAIRRO", length = 30)),
			@AttributeOverride(name = "cidade", column = @Column(name = "CIDADE", length = 40)),
			@AttributeOverride(name = "uf", column = @Column(name = "UF", length = 2)),
			@AttributeOverride(name = "cep", column = @Column(name = "CEP", length = 10)) })
	public VwDadosClientesId getId() {
		return this.id;
	}

	public void setId(VwDadosClientesId id) {
		this.id = id;
	}

}
