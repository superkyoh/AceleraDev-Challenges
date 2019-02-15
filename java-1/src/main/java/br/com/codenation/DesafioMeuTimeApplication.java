package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;
import br.com.codenation.desafio.exceptions.*;
import br.com.codenation.model.Jogador;
import br.com.codenation.model.Time;




public class DesafioMeuTimeApplication implements MeuTimeInterface {
	
	public List<Time> times = new ArrayList<Time>();

	@Desafio("incluirTime")
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
		
		for(Time time : times){
			if(id.equals(time.getId())){
				throw new IdentificadorUtilizadoException();
			}
		};
		
		Time time = new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario);
		
		times.add(time);
	}

	@Desafio("incluirJogador")
	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {	
		
		for(Time time : times){
			time.getJogadores().forEach(jogador->{
				if(jogador.getId().equals(id)) {
					throw new IdentificadorUtilizadoException();
				}
			});
			if(idTime.equals(time.getId())) {
				Jogador jogador = new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario);
				time.getJogadores().add(jogador);
				return;
			}
			
		};
		
		throw new TimeNaoEncontradoException();
	}

	@Desafio("definirCapitao")
	public void definirCapitao(Long idJogador) {
		for(Time time : times){
			for(Jogador jogador: time.getJogadores()){
				if(jogador.getId().equals(idJogador)) {
					time.setCapitao(jogador);
					return;
				}
			};
		};
		
		throw new JogadorNaoEncontradoException();
	}

	@Desafio("buscarCapitaoDoTime")
	public Long buscarCapitaoDoTime(Long idTime) {
		
		for(Time time : times){
			if((time.getId().equals(idTime))){
				if(time.getCapitao().getId() == null) {
					throw new CapitaoNaoInformadoException();
				} else {
					return time.getCapitao().getId();
				}
			}
		}
		
		throw new TimeNaoEncontradoException();
		
	}

	@Desafio("buscarNomeJogador")
	public String buscarNomeJogador(Long idJogador) {

		for(Time time : times){
			for(Jogador jogador: time.getJogadores()){
				if(jogador.getId().equals(idJogador)) {
					return jogador.getNome();
				}
			};
		};
		throw new JogadorNaoEncontradoException();
		
	}

	@Desafio("buscarNomeTime")
	public String buscarNomeTime(Long idTime) {
		
		for(Time time : times){
			if(time.getId().equals(idTime)) {
				return time.getNome();
			}
		}	
		throw new TimeNaoEncontradoException();
	}

	@Desafio("buscarJogadoresDoTime")
	public List<Long> buscarJogadoresDoTime(Long idTime) {
		List<Long> jogadoresDoTime = new ArrayList<Long>();
		
		for(Time time : times){
			if(time.getId().equals(idTime)) {
				for(Jogador jogador: time.getJogadores()){
					jogadoresDoTime.add(jogador.getId());
				};
				Collections.sort(jogadoresDoTime);
				return jogadoresDoTime;
			};
		};

		throw new TimeNaoEncontradoException();
		
	}

	@Desafio("buscarMelhorJogadorDoTime")
	public Long buscarMelhorJogadorDoTime(Long idTime) {
		for(Time time : times){
			if(time.getId().equals(idTime)) {
				time.getJogadores().sort(Comparator.comparingInt(Jogador::getNivelHabilidade).reversed().thenComparingLong(Jogador::getId));
				return time.getJogadores().get(0).getId();
			};
		};
		
		throw new TimeNaoEncontradoException();
		
	}

	@Desafio("buscarJogadorMaisVelho")
	public Long buscarJogadorMaisVelho(Long idTime) {
		for(Time time : times){
			if(time.getId().equals(idTime)) {
				time.getJogadores().sort(Comparator.comparing(Jogador::getDataNascimento).thenComparingLong(Jogador::getId));
				return time.getJogadores().get(0).getId();
			};
		};
		
		throw new TimeNaoEncontradoException();
	}

	@Desafio("buscarTimes")
	public List<Long> buscarTimes() {
		List<Long> timeIds = new ArrayList<Long>();
		
		for(Time time : times){
			timeIds.add(time.getId());
		}
		Collections.sort(timeIds);
		return timeIds;
	}

	@Desafio("buscarJogadorMaiorSalario")
	public Long buscarJogadorMaiorSalario(Long idTime) {
		for(Time time : times){
			if(time.getId().equals(idTime)) {
				time.getJogadores().sort(Comparator.comparing(Jogador::getSalario).reversed().thenComparingLong(Jogador::getId));
				return time.getJogadores().get(0).getId();
			};
		};
		
		throw new TimeNaoEncontradoException();
	}

	@Desafio("buscarSalarioDoJogador")
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		for(Time time : times){
			for(Jogador jogador: time.getJogadores()){
				if(jogador.getId().equals(idJogador)) {
					return jogador.getSalario();
				};
			};
		};
		throw new JogadorNaoEncontradoException();
		
	}

	@Desafio("buscarTopJogadores")
	public List<Long> buscarTopJogadores(Integer top) {
		List<Jogador> listaJogadores = new ArrayList<Jogador>();
		
		for(Time time : times){
			for(Jogador jogador: time.getJogadores()){
				listaJogadores.add(jogador);
			};
		};
		
		listaJogadores.sort(Comparator.comparingInt(Jogador::getNivelHabilidade).reversed());
		
		 List<Long> topJogadores = listaJogadores.stream()
	                .limit(top)
	                .map(Jogador::getId)
	                .collect(Collectors.toList());
		
		return topJogadores;
		
	}

	@Desafio("buscarCorCamisaTimeDeFora")
	public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
		Time timeCasa = new Time();
		Time timeFora = new Time();
		
		for(Time time : times){
			if(time.getId().equals(timeDaCasa)) {
				timeCasa = time;
			}else if (time.getId().equals(timeDeFora)) {
				timeFora = time;
			}	
		}
		
		if(timeFora.getId() == null) {
			throw new TimeNaoEncontradoException();
		}
		
		if(timeCasa.getId() == null) {
			throw new TimeNaoEncontradoException();
		}
		
		if(timeCasa.getCorUniformePrincipal().equals(timeFora.getCorUniformePrincipal())) {
			return timeFora.getCorUniformeSecundario();
		}else {
			return timeFora.getCorUniformePrincipal();
		}
		
		
	}
}
