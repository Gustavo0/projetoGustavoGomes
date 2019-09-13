package com.ciet.projetoGustavoGomes.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Size;

import com.ciet.projetoGustavoGomes.entity.Bebida;
import com.ciet.projetoGustavoGomes.entity.TipoBebida;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BebidaDto {
	
	@Enumerated(EnumType.STRING)
	private TipoBebida tipoBebida;
	
	private boolean isAlcoolica;
	
	@Size(max = 10)
	private String marca;
	
	public BebidaDto(Bebida bebida) {
		this.tipoBebida = bebida.getTipoBebida();
		this.isAlcoolica = bebida.isAlcoolica();
		this.marca = bebida.getMarca();
	}

	public BebidaDto(TipoBebida tipoBebida, boolean isAlcoolica, String marca) {
		super();
		this.tipoBebida = tipoBebida;
		this.isAlcoolica = isAlcoolica;
		this.marca = marca;
	}
	
}
