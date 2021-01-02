package br.telehand.model;

// Generated 27/03/2014 16:20:36 by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TbAlmKit generated by hbm2java
 */
@Entity
@Table(name = "tb_alm_kit", catalog = "db_sge")
public class TbAlmKit implements java.io.Serializable {

	private int idServico;
	private int cdKit;

	public TbAlmKit() {
	}

	public TbAlmKit(int idServico, int cdKit) {
		this.idServico = idServico;
		this.cdKit = cdKit;
	}

	@Id
	@Column(name = "id_servico", unique = true, nullable = false)
	public int getIdServico() {
		return this.idServico;
	}

	public void setIdServico(int idServico) {
		this.idServico = idServico;
	}

	@Column(name = "cd_kit", nullable = false)
	public int getCdKit() {
		return this.cdKit;
	}

	public void setCdKit(int cdKit) {
		this.cdKit = cdKit;
	}

}
