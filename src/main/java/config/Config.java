package config;

import java.util.List;

public interface Config {

	public void load();
	
	public List<String> getSuspects();

	public List<String> getRooms();

	public List<String> getWeapons();
}
