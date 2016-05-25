package data;

import org.json.JSONObject;

public class StatData {
	int id;
	String name;
	int gameIndex;
	boolean isOnlyBattle;
	public StatData(String data){
		JSONObject obj = new JSONObject(data);
		id = obj.getInt("id");
		name = obj.getString("name");
		gameIndex = obj.getInt("game_index");
		isOnlyBattle = obj.getBoolean("is_battle_only");
	}
}
