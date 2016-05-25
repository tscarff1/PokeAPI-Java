package pokedb;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class PokemonAPI {
	public final static String APIURL = "http://pokeapi.co/api/v2/";
	public final static String POKEMONURL = "pokemon/";
	URL url;
	
	HttpURLConnection httpcon; 
	InputStream iStream;
	Scanner scan;
	String data;
	public PokemonAPI(){
		
	}
	
	private String getData(String sUrl){
		data="";
		try {
			url = new URL(sUrl.toLowerCase());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		try {
			HttpURLConnection httpcon = (HttpURLConnection) url.openConnection();
			httpcon.setRequestProperty("User-Agent", "Mozilla/5.0");
			if(httpcon.getResponseCode() == 504 || httpcon.getResponseCode() == -1){
				System.err.println("[PokemonAPI.getData] Bad Response code");
				return data;
			}
			InputStream iStream = httpcon.getInputStream();
			Scanner scan = new Scanner(iStream);
			while(scan.hasNext()){
				data += scan.nextLine();
			}
			scan.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return data;
	}
	
	public String getPokemonData(String id){
		return getData(APIURL + "pokemon/" + id);
	}
	
	public String getPokemonData(int id){
		return getData(APIURL + "pokemon/" + id);
	}
	
	public String getNatureData(String id){
		return getData(APIURL + "nature/" + id);
	}
	
	public String getNatureData(int id){
		return getData(APIURL + "nature/" + id);
	}
	
	public String getStatData(String id){
		return getData(APIURL + "stat/" + id);
	}
	
	public String getStatData(int id){
		return getData(APIURL + "stat/" + id);
	}
	
	
	
}
