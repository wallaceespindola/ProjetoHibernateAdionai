package br.telehand.model;

// Generated 27/03/2014 16:20:36 by Hibernate Tools 4.0.0

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TbJanela generated by hbm2java
 */
@Entity
@Table(name = "tb_janela", catalog = "db_sge")
public class TbJanela implements java.io.Serializable {

	private Integer idJanela;
	private TbServico tbServico;
	private Date hrInicial;
	private Date hrFinal;
	private Date dtVigFim;
	private Date dtVigIni;
	private Set tbAgendas = new HashSet(0);
	private Set tbDiases = new HashSet(0);

	public TbJanela() {
	}

	public TbJanela(TbServico tbServico, Date hrInicial, Date hrFinal,
			Date dtVigIni) {
		this.tbServico = tbServico;
		this.hrInicial = hrInicial;
		this.hrFinal = hrFinal;
		this.dtVigIni = dtVigIni;
	}

	public TbJanela(TbServico tbServico, Date hrInicial, Date hrFinal,
			Date dtVigFim, Date dtVigIni, Set tbAgendas, Set tbDiases) {
		this.tbServico = tbServico;
		this.hrInicial = hrInicial;
		this.hrFinal = hrFinal;
		this.dtVigFim = dtVigFim;
		this.dtVigIni = dtVigIni;
		this.tbAgendas = tbAgendas;
		this.tbDiases = tbDiases;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Id_janela", unique = true, nullable = false)
	public Integer getIdJanela() {
		return this.idJanela;
	}

	public void setIdJanela(Integer idJanela) {
		this.idJanela = idJanela;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Id_servico", nullable = false)
	public TbServico getTbServico() {
		return this.tbServico;
	}

	public void setTbServico(TbServico tbServico) {
		this.tbServico = tbServico;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "Hr_inicial", nullable = false, length = 0)
	public Date getHrInicial() {
		return this.hrInicial;
	}

	public void setHrInicial(Date hrInicial) {
		this.hrInicial = hrInicial;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "Hr_final", nullable = false, length = 0)
	public Date getHrFinal() {
		return this.hrFinal;
	}

	public void setHrFinal(Date hrFinal) {
		this.hrFinal = hrFinal;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Dt_vig_fim", length = 0)
	public Date getDtVigFim() {
		return this.dtVigFim;
	}

	public void setDtVigFim(Date dtVigFim) {
		this.dtVigFim = dtVigFim;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Dt_vig_ini", nullable = false, length = 0)
	public Date getDtVigIni() {
		return this.dtVigIni;
	}

	public void setDtVigIni(Date dtVigIni) {
		this.dtVigIni = dtVigIni;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbJanela")
	public Set<TbAgenda> getTbAgendas() {
		return this.tbAgendas;
	}

	public void setTbAgendas(Set<TbAgenda> tbAgendas) {
		this.tbAgendas = tbAgendas;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbJanela")
	public Set<TbDias> getTbDiases() {
		return this.tbDiases;
	}

	public void setTbDiases(Set<TbDias> tbDiases) {
		this.tbDiases = tbDiases;
	}

}
