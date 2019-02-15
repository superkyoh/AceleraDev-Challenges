package challenge;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LerArquivo {
	
	LerArquivo(){}
	
	public List<String[]> getColumnsFromCsvData() {
		List<String[]> columns = new ArrayList<>();
		ClassLoader classLoader = getClass().getClassLoader();
		File fileJogadoresFifa = new File(classLoader.getResource("data.csv").getFile());
		
		String line = "";
		String splitBy = ",";
		
		try(BufferedReader br = new BufferedReader(new FileReader(fileJogadoresFifa))){
			while((line = br.readLine()) != null) {
				columns.add(line.split(splitBy));
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		return columns;
	} 
}
