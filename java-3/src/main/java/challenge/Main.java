package challenge;
import org.apache.commons.lang3.StringUtils;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;


public class Main {
	
	public LerArquivo la = new LerArquivo();
	public List<String[]> colunas = la.getColumnsFromCsvData();
	public List<Jogador> jogadoresFifa = this.criarListaJogadores();
	
	// Quantas nacionalidades (coluna `nationality`) diferentes existem no arquivo?
	public int q1() {

		return ((int) jogadoresFifa.stream()
				.map(Jogador::getNationality)
				.distinct()
				.count());
	}

	// Quantos clubes (coluna `club`) diferentes existem no arquivo?
	// Obs: Existem jogadores sem clube.
	public int q2() {
		
		return ((int) jogadoresFifa.stream()
				.filter(j -> StringUtils.isNotEmpty(j.getClub()))
				.map(Jogador::getClub)
				.distinct()
				.count());
	}

	// Liste o nome completo (coluna `full_name`) dos 20 primeiros jogadores.
	public List<String> q3() {
		
		return jogadoresFifa.subList(0, 20).stream()
				.map(Jogador::getFullName)
				.collect(Collectors.toList());
	}

	// Quem são os top 10 jogadores que possuem as maiores cláusulas de rescisão?
	// (utilize as colunas `full_name` e `eur_release_clause`)
	public List<String> q4() {
		
		return jogadoresFifa.stream()
				.sorted(Comparator.comparing(Jogador::getReleaseClause).reversed())
				.map(Jogador::getFullName)	
				.limit(10)
				.collect(Collectors.toList());
		
		
	}

	// Quem são os 10 jogadores mais velhos (use como critério de desempate o campo `eur_wage`)?
	// (utilize as colunas `full_name` e `birth_date`)
	public List<String> q5() {
		
		return jogadoresFifa.stream()
				.sorted(Comparator.comparing(Jogador::getBirthday)
						.thenComparing(Jogador::getEurWage))
				.map(Jogador::getFullName)
				.limit(10)
				.collect(Collectors.toList());
	}

	// Conte quantos jogadores existem por idade. Para isso, construa um mapa onde as chaves são as idades e os valores a contagem.
	// (utilize a coluna `age`)
	public Map<Integer, Integer> q6() {
		Map<Integer, Long> mapAgrupado = jogadoresFifa.stream()
				.collect(Collectors.groupingBy(Jogador::getAge, Collectors.counting()));

		Map<Integer, Integer> retorno = mapAgrupado.entrySet()
				.stream()
				.collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().intValue()));

		return retorno;
	}
	
	public List<Jogador> criarListaJogadores(){
		List<Jogador> jogadores = new ArrayList<>();
		
		for(String[] array : colunas) {
			
			if(array[18].isEmpty()) {
				array[18] = "0";
			}
			
			if ( array[6].equals("age")) {
			
			}else {
				
				Jogador jogador = new Jogador(array[2], array[14], array[3], Float.parseFloat(array[18]), array[8], Integer.parseInt(array[6]), array[17]);
				
				jogadores.add(jogador);
			}
			
		};
		
		return jogadores;
	}
}
