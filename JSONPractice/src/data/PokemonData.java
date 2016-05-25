package data;

import org.json.JSONArray;
import org.json.JSONObject;

public class PokemonData {
	int id;
	String name;
	int baseExp;
	int height;
	boolean isDefault;
	int order;
	int weight;
	int[] stats;
	PokemonType[] types;
	PokemonAbility[] abilities;
	//PokemonForm[] forms;
	
	
	//These are NOT the same as the ids in the api
	public final static int HP = 0, 
			ATK =1,
			DEF=2,
			SPATK=3,
			SPDEF=4,
			SPD=5;
	
	public PokemonData(String data){
		JSONObject obj = new JSONObject(data);
		id = obj.getInt("id");
		name = obj.getString("name");
		baseExp = obj.getInt("base_experience");
		height = obj.getInt("height");
		isDefault = obj.getBoolean("is_default");
		order = obj.getInt("order");
		weight = obj.getInt("weight");
		
		System.out.println(obj);
		JSONArray statArray = obj.getJSONArray("stats");
		stats = new int[6];
		for(int i = 0; i < statArray.length(); i++){
			JSONObject stat = statArray.getJSONObject(i);
			assignStat(stat.getJSONObject("stat").getString("name"), 
					stat.getInt("base_stat"));	
		}
		
		JSONArray typeArray = obj.getJSONArray("types");
		types = new PokemonType[typeArray.length()];
		for(int i = 0; i < typeArray.length(); i++){
			JSONObject type = typeArray.getJSONObject(typeArray.length() - i - 1);
			types[i] = new PokemonType(type.getJSONObject("type").getString("name"), type.getInt("slot"));
		}
		
		JSONArray abilArray = obj.getJSONArray("abilities");
		int arrayLength = abilArray.length();
		abilities = new PokemonAbility[arrayLength];
		for(int i = 0; i < arrayLength; i++){
			String name = abilArray.getJSONObject(i).getJSONObject("ability").getString("name");
			boolean isHidden = abilArray.getJSONObject(i).getBoolean("is_hidden");
			int slot = abilArray.getJSONObject(i).getInt("slot");
			abilities[i] = new PokemonAbility(name, slot, isHidden);
		}
		
		
		
	}
	
	private void assignStat(String name, int val){
		switch(name.toLowerCase()){
			case "hp":
				stats[HP] = val;
				break;
			case "attack":
				stats[ATK] = val;
				break;
			case "defense":
				stats[DEF] = val;
				break;
			case "special-attack":
				stats[SPATK] = val;
				break;
			case "special-defense":
				stats[SPDEF] = val;
				break;
			case "speed":
				stats[SPD] = val;
				break;
			default:
				System.out.println("[PokemonData.assignStat]Unrecognized stat name.");
		}
	}
	
	public void printBasicData(){
		System.out.println("Pokemon #" + id + " - " + name);
		for(int i = 0; i < types.length; i++){
			System.out.print(types[i].type() + " ");
		}
		
		System.out.println();
		
		System.out.println("Abilties: ");
		for(int i = 0; i < abilities.length; i++){
			System.out.println(abilities[i].ability() + " - hidden: " + abilities[i].isHidden());
		}
		
		
		System.out.println("HP: " + stats[HP]);
		System.out.println("ATTACK: " + stats[ATK]);
		System.out.println("DEFENSE: " + stats[DEF]);
		System.out.println("SP. ATTACK: " + stats[SPATK]);
		System.out.println("SP. DEFENSE: " + stats[SPDEF]);
		System.out.println("SPEED: " + stats[SPD]);
		System.out.println("BST: " + getBST());
	}
	
	//GETTERS
	public int getBST(){
		int bst = 0;
		for(int i : stats){
			bst += i;
		}
		return bst;
	}
}
