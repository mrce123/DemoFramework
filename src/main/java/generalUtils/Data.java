package generalUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class Data {
	
	//This class access data from JSON
	private final String resourcePath;
	protected String id;
	
	protected Data(String resourcePath,String id) {
		this.resourcePath = resourcePath;
		populate(this,id);
	}

	private static void populate(Data data, String id) {
		HashMap<String,Object> map = Data.getJsonData(data.resourcePath, id);
		assert map!=null;
		for(Map.Entry<String, Object> entry:map.entrySet()) {
				try {				 
					BeanUtils.getProperty(data, entry.getKey());
			}catch(NoSuchMethodException e) {
				throw new IllegalStateException(String.format("class %s does not have property. Check json %s",data.getClass(),data.resourcePath), e.getCause());
			}catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		try {
			BeanUtils.populate(data, Data.getJsonData(data.resourcePath, id));
		}catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
	}
	
	@SuppressWarnings("rawtypes")
	private static HashMap<String,Object> getJsonData(String resourcePath,String id){
		
		InputStream json = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourcePath);
		ObjectMapper objectMapper = new ObjectMapper();
		List<HashMap<String,Object>> hashMaps = null;
		try {
			hashMaps = objectMapper.readValue(json, new TypeReference<List<HashMap>>() {
			});
		}catch(IOException e) {
			e.printStackTrace();
		}
		assert hashMaps!=null;
		for(HashMap<String,Object> hashMap:hashMaps) {
			if(hashMap.get("id").equals(id)) {
				return hashMap;
			}
		}
		return null;
		
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
