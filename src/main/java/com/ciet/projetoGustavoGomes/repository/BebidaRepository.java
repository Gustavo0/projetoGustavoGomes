package com.ciet.projetoGustavoGomes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ciet.projetoGustavoGomes.entity.Bebida;
import com.ciet.projetoGustavoGomes.entity.TipoBebida;

@Repository
public interface BebidaRepository extends JpaRepository<Bebida, Long> {

	Bebida findByTipoBebida(TipoBebida tipoBebida);

}
