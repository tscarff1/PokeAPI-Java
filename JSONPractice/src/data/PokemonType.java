package data;

public class PokemonType {
	private String type;
	private int slot;
	
	public PokemonType(String t, int s){
		type = t;
		slot = s;
	}
	
	public String type(){
		return type;
	}
	
	public int slot(){
		return slot;
	}
}
