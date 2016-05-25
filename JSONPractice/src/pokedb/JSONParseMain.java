package pokedb;

import data.*;

public class JSONParseMain {
	
	public static void main(String[] args) {
		PokemonData[] pokemonData = new PokemonData[151];
		PokemonAPI api = new PokemonAPI();
			String s = api.getPokemonData("persian");
			PokemonData data = new PokemonData(s);
			data.printBasicData();
	
		//s = api.getNatureData("bold");
		//NatureData nd = new NatureData(s);
	}

}
