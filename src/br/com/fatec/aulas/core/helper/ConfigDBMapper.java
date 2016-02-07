package br.com.fatec.aulas.core.helper;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ConfigDBMapper {
	
	private static ConfigDBMapper instance;
	
	private static JSONParser parser = new JSONParser();
	
	private static final ClassLoader loader = ConfigDBMapper.class.getClassLoader();
	
	private static Map<String, Connection> connections = new HashMap<String, Connection>();
	
	private String defaultConnectionName;
	
	private List<String> possibleConfigs;
	
	private ConfigDBMapper() {
		
	}
	
	
	public static ConfigDBMapper getInstance() {
		if (instance == null) {
			instance = new ConfigDBMapper();
			instance.loadConnnection();
		}
		return instance;
	}



	public void loadConnnection () {
		
		try {
			String path = loader.getResource("br/com/fatec/aulas/core/config/databases.json").getPath();
			JSONArray configs = (JSONArray) ConfigDBMapper.parser.parse(new FileReader(path));
			if(configs.size() < 1){
				throw new RuntimeException("É necessário ao menos uma configuração de  Banco de Dados");
			}
			for (Object config : configs) {
				JSONObject configJSON = (JSONObject) config;
				String configNameJSON = (String) configJSON.get("name");
				String url = (String) configJSON.get("url");
				String login = (String) configJSON.get("login");
				String password = (String) configJSON.get("password");
				String driverClassName = (String) configJSON.get("driverClassName");
				Class.forName(driverClassName);
				connections.put(configNameJSON, DriverManager.getConnection(url,login,password));	
			}
			this.possibleConfigs = new ArrayList<String>(connections.keySet());
		
		} catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}
	
	public void setDefaultConnectionName(String config){
		if (this.defaultConnectionName == null && (config != null && !StringUtils.isEmpty(config))) {
			if (this.possibleConfigs.contains(config)) {
				this.defaultConnectionName = config;
			} else {
				throw new RuntimeException("Não existe configuração com nome '"+ config + "'.");
			}
			
		}
		
	}
	
	public Connection getDefaultConnection() {
		if (this.defaultConnectionName == null) {
			return null;
		}
		return this.getConnectionByConfig(this.defaultConnectionName);
	}
	
	public List<String> getPossibleConfigs() {
		return this.possibleConfigs;
	}
	

	private Connection getConnectionByConfig(String configName) {
		if (connections.containsKey(configName)) {
			return connections.get(configName);
		}
		throw new RuntimeException("Não existe configuração com nome '" + configName + "'.");
	}

}
