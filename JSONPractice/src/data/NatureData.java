package data;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class NatureData {
//Contains all data from API
	int id;
	String name;
	String decreased;
	String increased;
	String hateFlavor;
	String likeFlavor;
	NatureStatChange[] statChanges; //Pokeathlon stat changes
	NatureBattleStyle[] battleStyles;
	Map <String, String> names;
	
	
	public NatureData(String data){
		JSONObject obj = new JSONObject(data);
		System.out.println(data);
		name = obj.getString("name");
		id = obj.getInt("id");
		decreased = obj.getJSONObject("decreased_stat").getString("name");
		increased = obj.getJSONObject("increased_stat").getString("name");
		likeFlavor = obj.getJSONObject("likes_flavor").getString("name");
		hateFlavor = obj.getJSONObject("hates_flavor").getString("name");
		System.out.println(name + ": -" + hateFlavor + " +" + likeFlavor);
		
		JSONArray statArray = obj.getJSONArray("pokeathlon_stat_changes");
		statChanges = new NatureStatChange[statArray.length()];
		for(int i = 0; i < statArray.length(); i++){
			JSONObject currentObj = statArray.getJSONObject(i);
			statChanges[i] = new NatureStatChange(currentObj.getInt("max_change"),
							currentObj.getJSONObject("pokeathlon_stat").getString("name"));
			System.out.println(statChanges[i]);
		}
		
		JSONArray array = obj.getJSONArray("move_battle_style_preferences");
		battleStyles = new NatureBattleStyle[array.length()];
		for(int i = 0; i < array.length(); i++){
			JSONObject currentObj =array.getJSONObject(i);
			int low = currentObj.getInt("low_hp_preference");
			int high = currentObj.getInt("high_hp_preference");
			String style = currentObj.getJSONObject("move_battle_style").getString("name");
			battleStyles[i] = new NatureBattleStyle(low, high, style);
			System.out.println(battleStyles[i]);
		}
		array = obj.getJSONArray("names");
		names = new HashMap<String, String>();
		for(int i = 0; i < array.length(); i++){
			JSONObject currentObj = array.getJSONObject(i);
			String language = currentObj.getJSONObject("language").getString("name");
			String name = currentObj.getString("name");
			names.put(language, name);
			System.out.println(names.get(language) + ": " + language);
		}
		
	}
	
	public class NatureStatChange {
		int change;
		String stat;
		
		public NatureStatChange(int c, String s){
			change = c;
			stat = s;
		}
		
		public int change(){
			return change;
		}
		
		public String stat(){
			return stat;
		}
		
		public String toString(){
			return stat + ": " + change;
		}
	}
	
	public class NatureBattleStyle{
		int lowHp;
		int highHp;
		String style;
		
		public NatureBattleStyle(int low, int high, String s){
			lowHp = low;
			highHp = high;
			style = s;
		}

		public int lowHp() {
			return lowHp;
		}

		public int highHp() {
			return highHp;
		}

		public String style() {
			return style;
		}
		
		public String toString(){
			return style + "- high hp: " + highHp + ", low hp: " + lowHp; 
		}
	}
}
