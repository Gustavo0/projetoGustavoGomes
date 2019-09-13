package com.ciet.projetoGustavoGomes.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
public class Bebida {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private TipoBebida tipoBebida;
	
	private boolean isAlcoolica;
	
	private String marca;
	
	public Bebida(TipoBebida tipoBebida, boolean isAlcoolica, String marca) {
		this.tipoBebida = tipoBebida;
		this.isAlcoolica = isAlcoolica;
		this.marca = marca;
	}

	public Bebida(Long id, TipoBebida tipoBebida, boolean isAlcoolica, String marca) {
		super();
		this.id = id;
		this.tipoBebida = tipoBebida;
		this.isAlcoolica = isAlcoolica;
		this.marca = marca;
	}
	
	public Bebida() {
		
	}
	
}
