package br.telehand.model;

// Generated 27/03/2014 16:20:36 by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TbUsuario generated by hbm2java
 */
@Entity
@Table(name = "tb_usuario", catalog = "db_sge")
public class TbUsuario implements java.io.Serializable {

	private int nrMatricula;
	private char cdUsuario;

	public TbUsuario() {
	}

	public TbUsuario(int nrMatricula, char cdUsuario) {
		this.nrMatricula = nrMatricula;
		this.cdUsuario = cdUsuario;
	}

	@Id
	@Column(name = "nr_matricula", unique = true, nullable = false)
	public int getNrMatricula() {
		return this.nrMatricula;
	}

	public void setNrMatricula(int nrMatricula) {
		this.nrMatricula = nrMatricula;
	}

	@Column(name = "cd_usuario", nullable = false, length = 1)
	public char getCdUsuario() {
		return this.cdUsuario;
	}

	public void setCdUsuario(char cdUsuario) {
		this.cdUsuario = cdUsuario;
	}

}