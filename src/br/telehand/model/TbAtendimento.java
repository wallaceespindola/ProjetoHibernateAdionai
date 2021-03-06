package br.telehand.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TbAtendimento generated by hbm2java
 */
@Entity
@Table(name = "tb_atendimento", catalog = "db_sge")
public class TbAtendimento implements java.io.Serializable {

	private Integer idAtendimento;
	private TbOs tbOs;
	private TbCategoria tbCategoria;
	private TbAgenda tbAgenda;
	private Date dtAgendamento;
	private int nrMatricula;
	private char cdStatus;

	public TbAtendimento() {
	}

	public TbAtendimento(TbOs tbOs, TbAgenda tbAgenda, Date dtAgendamento,
			int nrMatricula, char cdStatus) {
		this.tbOs = tbOs;
		this.tbAgenda = tbAgenda;
		this.dtAgendamento = dtAgendamento;
		this.nrMatricula = nrMatricula;
		this.cdStatus = cdStatus;
	}

	public TbAtendimento(TbOs tbOs, TbCategoria tbCategoria, TbAgenda tbAgenda,
			Date dtAgendamento, int nrMatricula, char cdStatus) {
		this.tbOs = tbOs;
		this.tbCategoria = tbCategoria;
		this.tbAgenda = tbAgenda;
		this.dtAgendamento = dtAgendamento;
		this.nrMatricula = nrMatricula;
		this.cdStatus = cdStatus;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Id_atendimento", unique = true, nullable = false)
	public Integer getIdAtendimento() {
		return this.idAtendimento;
	}

	public void setIdAtendimento(Integer idAtendimento) {
		this.idAtendimento = idAtendimento;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Id_OS", nullable = false)
	public TbOs getTbOs() {
		return this.tbOs;
	}

	public void setTbOs(TbOs tbOs) {
		this.tbOs = tbOs;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Id_categoria")
	public TbCategoria getTbCategoria() {
		return this.tbCategoria;
	}

	public void setTbCategoria(TbCategoria tbCategoria) {
		this.tbCategoria = tbCategoria;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns({
			@JoinColumn(name = "Id_janela", referencedColumnName = "Id_janela", nullable = false),
			@JoinColumn(name = "Id_equipe", referencedColumnName = "Id_equipe", nullable = false) })
	public TbAgenda getTbAgenda() {
		return this.tbAgenda;
	}

	public void setTbAgenda(TbAgenda tbAgenda) {
		this.tbAgenda = tbAgenda;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Dt_agendamento", nullable = false, length = 0)
	public Date getDtAgendamento() {
		return this.dtAgendamento;
	}

	public void setDtAgendamento(Date dtAgendamento) {
		this.dtAgendamento = dtAgendamento;
	}

	@Column(name = "Nr_matricula", nullable = false)
	public int getNrMatricula() {
		return this.nrMatricula;
	}

	public void setNrMatricula(int nrMatricula) {
		this.nrMatricula = nrMatricula;
	}

	@Column(name = "Cd_status", nullable = false, length = 1)
	public char getCdStatus() {
		return this.cdStatus;
	}

	public void setCdStatus(char cdStatus) {
		this.cdStatus = cdStatus;
	}

}
