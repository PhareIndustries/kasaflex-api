package com.kasaflex.api.Entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.hibernate.annotations.Generated;
import org.hibernate.generator.EventType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "station")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Station {

	@Id
	@Column(name = "idStation", length = 50, nullable = false, insertable = false, updatable = false)
	@Generated(event = EventType.INSERT)
	private String idStation;

	@Column(name = "nomStation", length = 50, nullable = false)
	private String nomStation;

	@Column(name = "adresse", length = 50, nullable = false)
	private String adresse;

	@Column(name = "latitude", precision = 15, scale = 2, nullable = false)
	private BigDecimal latitude;

	@Column(name = "longitude", precision = 15, scale = 2, nullable = false)
	private BigDecimal longitude;

	@Column(name = "dateInstallation", nullable = false)
	private LocalDate dateInstallation;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "idModele", nullable = false)
	private Modele modele;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idClients", nullable = false)
    private Client client;

}
