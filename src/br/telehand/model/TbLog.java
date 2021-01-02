package br.telehand.model;

// Generated 27/03/2014 16:20:36 by Hibernate Tools 4.0.0

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * TbLog generated by hbm2java
 */
@Entity
@Table(name = "tb_log", catalog = "db_sge")
public class TbLog implements java.io.Serializable {

	private TbLogId id;
	private String txDescricao;

	public TbLog() {
	}

	public TbLog(TbLogId id) {
		this.id = id;
	}

	public TbLog(TbLogId id, String txDescricao) {
		this.id = id;
		this.txDescricao = txDescricao;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "nrMatricula", column = @Column(name = "Nr_matricula", nullable = false)),
			@AttributeOverride(name = "dtOperacao", column = @Column(name = "Dt_operacao", nullable = false, length = 0)) })
	public TbLogId getId() {
		return this.id;
	}

	public void setId(TbLogId id) {
		this.id = id;
	}

	@Column(name = "Tx_descricao", length = 100)
	public String getTxDescricao() {
		return this.txDescricao;
	}

	public void setTxDescricao(String txDescricao) {
		this.txDescricao = txDescricao;
	}

}
