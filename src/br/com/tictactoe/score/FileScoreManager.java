package br.com.tictactoe.score;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import br.com.tictactoe.core.Player;

public class FileScoreManager implements ScoreManager{

	private static final Path SCORE_FILE = Path.of("score.txt");
	private Map<String, Integer> scoreMap = new HashMap<String, Integer>();
	
	public FileScoreManager() throws IOException{
		setup();
	}
	
	private void setup() throws IOException {
		if(!Files.exists(SCORE_FILE)) {
			Files.createFile(SCORE_FILE);
		}
		
		try(BufferedReader reader = Files.newBufferedReader(SCORE_FILE)){
			String line;
			while((line = reader.readLine()) != null) {
				String[] tokens = line.split("\\|");
				scoreMap.put(tokens[0], Integer.parseInt(tokens[1]));
			}
		}
		
		
	}

	@Override
	public Integer getScore(Player player) {
		return scoreMap.get(player.getNome().toUpperCase());
	}

	@Override
	public void saveScore(Player player) throws IOException{
		Integer score = getScore(player);
		if(score == null) {
			score = 0;
		}
		scoreMap.put(player.getNome().toUpperCase(), ++score);
		
		try(BufferedWriter writer = Files.newBufferedWriter(SCORE_FILE)){ //Sobreescreve o arquivo anterior 
			Set<Map.Entry<String, Integer>> entries = scoreMap.entrySet(); 
			for(Map.Entry<String, Integer> entry : entries) {
				String name = entry.getKey().toUpperCase();
				Integer scorePlayer = entry.getValue();
				writer.write(name + "|" + scorePlayer);
				writer.newLine(); 
			}
			writer.flush();
		}
		
	}

}
