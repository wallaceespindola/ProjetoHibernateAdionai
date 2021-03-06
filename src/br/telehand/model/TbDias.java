package br.telehand.model;

// Generated 09/04/2014 16:06:53 by Hibernate Tools 4.0.0

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * TbDias generated by hbm2java
 */
@Entity
@Table(name = "tb_dias", catalog = "db_sge")
public class TbDias implements java.io.Serializable {

	private TbDiasId id;
	private TbJanela tbJanela;

	public TbDias() {
	}

	public TbDias(TbDiasId id, TbJanela tbJanela) {
		this.id = id;
		this.tbJanela = tbJanela;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "idJanela", column = @Column(name = "Id_janela", nullable = false)),
			@AttributeOverride(name = "cdDia", column = @Column(name = "Cd_dia", nullable = false)),
			@AttributeOverride(name = "cdSemana", column = @Column(name = "cd_semana", nullable = false)) })
	public TbDiasId getId() {
		return this.id;
	}

	public void setId(TbDiasId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Id_janela", nullable = false, insertable = false, updatable = false)
	public TbJanela getTbJanela() {
		return this.tbJanela;
	}

	public void setTbJanela(TbJanela tbJanela) {
		this.tbJanela = tbJanela;
	}

}
