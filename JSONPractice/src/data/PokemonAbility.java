package data;

public class PokemonAbility {
	private boolean isHidden;
	private int slot;
	private String ability;
	
	public PokemonAbility(String a, int s, boolean h){
		ability = a;
		slot = s;
		isHidden = h;
	}
	
	public boolean isHidden(){
		return isHidden;
	}
	
	public int slot(){
		return slot;
	}
	
	public String ability(){
		return ability;
	}
}
