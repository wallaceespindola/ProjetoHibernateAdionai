package br.telehand.model;

// Generated 27/03/2014 16:20:36 by Hibernate Tools 4.0.0

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * TbCalendarioId generated by hbm2java
 */
@Embeddable
public class TbCalendarioId implements java.io.Serializable {

	private Date dtInicial;
	private Date dtFinal;

	public TbCalendarioId() {
	}

	public TbCalendarioId(Date dtInicial, Date dtFinal) {
		this.dtInicial = dtInicial;
		this.dtFinal = dtFinal;
	}

	@Column(name = "Dt_inicial", nullable = false, length = 0)
	public Date getDtInicial() {
		return this.dtInicial;
	}

	public void setDtInicial(Date dtInicial) {
		this.dtInicial = dtInicial;
	}

	@Column(name = "Dt_final", nullable = false, length = 0)
	public Date getDtFinal() {
		return this.dtFinal;
	}

	public void setDtFinal(Date dtFinal) {
		this.dtFinal = dtFinal;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TbCalendarioId))
			return false;
		TbCalendarioId castOther = (TbCalendarioId) other;

		return ((this.getDtInicial() == castOther.getDtInicial()) || (this
				.getDtInicial() != null && castOther.getDtInicial() != null && this
				.getDtInicial().equals(castOther.getDtInicial())))
				&& ((this.getDtFinal() == castOther.getDtFinal()) || (this
						.getDtFinal() != null && castOther.getDtFinal() != null && this
						.getDtFinal().equals(castOther.getDtFinal())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getDtInicial() == null ? 0 : this.getDtInicial().hashCode());
		result = 37 * result
				+ (getDtFinal() == null ? 0 : this.getDtFinal().hashCode());
		return result;
	}

}
