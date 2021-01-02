package br.telehand.model;

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
 * TbOs generated by hbm2java
 */
@Entity
@Table(name = "tb_os", catalog = "db_sge")
public class TbOs implements java.io.Serializable {

	private Integer idOs;
	private TbServico tbServico;
	private Date dtGeracao;
	private String nrCpf;
	private String nrCnpj;
	private String txDetalhe;
	private String cdKit;
	private Character cdStatus;
	private Date dtFim;
	private Set<TbAtendimento> tbAtendimentos = new HashSet(0);

	public TbOs() {
	}

	public TbOs(TbServico tbServico) {
		this.tbServico = tbServico;
	}

	public TbOs(TbServico tbServico, Date dtGeracao, String nrCpf,
			String nrCnpj, String txDetalhe, String cdKit, Character cdStatus,
			Date dtFim, Set tbAtendimentos) {
		this.tbServico = tbServico;
		this.dtGeracao = dtGeracao;
		this.nrCpf = nrCpf;
		this.nrCnpj = nrCnpj;
		this.txDetalhe = txDetalhe;
		this.cdKit = cdKit;
		this.cdStatus = cdStatus;
		this.dtFim = dtFim;
		this.tbAtendimentos = tbAtendimentos;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Id_OS", unique = true, nullable = false)
	public Integer getIdOs() {
		return this.idOs;
	}

	public void setIdOs(Integer idOs) {
		this.idOs = idOs;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Id_servico", nullable = false)
	public TbServico getTbServico() {
		return this.tbServico;
	}

	public void setTbServico(TbServico tbServico) {
		this.tbServico = tbServico;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Dt_geracao", length = 0)
	public Date getDtGeracao() {
		return this.dtGeracao;
	}

	public void setDtGeracao(Date dtGeracao) {
		this.dtGeracao = dtGeracao;
	}

	@Column(name = "Nr_cpf", length = 11)
	public String getNrCpf() {
		return this.nrCpf;
	}

	public void setNrCpf(String nrCpf) {
		this.nrCpf = nrCpf;
	}

	@Column(name = "Nr_cnpj", length = 14)
	public String getNrCnpj() {
		return this.nrCnpj;
	}

	public void setNrCnpj(String nrCnpj) {
		this.nrCnpj = nrCnpj;
	}

	@Column(name = "Tx_detalhe", length = 65535)
	public String getTxDetalhe() {
		return this.txDetalhe;
	}

	public void setTxDetalhe(String txDetalhe) {
		this.txDetalhe = txDetalhe;
	}

	@Column(name = "Cd_kit", length = 3)
	public String getCdKit() {
		return this.cdKit;
	}

	public void setCdKit(String cdKit) {
		this.cdKit = cdKit;
	}

	@Column(name = "Cd_status", length = 1)
	public Character getCdStatus() {
		return this.cdStatus;
	}

	public void setCdStatus(Character cdStatus) {
		this.cdStatus = cdStatus;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Dt_fim", length = 0)
	public Date getDtFim() {
		return this.dtFim;
	}

	public void setDtFim(Date dtFim) {
		this.dtFim = dtFim;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "tbOs")
	public Set<TbAtendimento> getTbAtendimentos() {
		return this.tbAtendimentos;
	}

	public void setTbAtendimentos(Set<TbAtendimento> tbAtendimentos) {
		this.tbAtendimentos = tbAtendimentos;
	}

}
