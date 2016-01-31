package com.innovista.db.util;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

public class ConvertObject {
	
	public static JSONArray convertListToJSONArray(List records){
		JSONArray jsonRecord = new JSONArray();
		for(Object obj:records){
			JSONObject reco = convertMapToJSONObject((Map)obj);
			jsonRecord.put(reco);
		}
		return jsonRecord;
	}
	
	public static JSONObject convertMapToJSONObject(Map record){
		JSONObject jsonRecord = new JSONObject();
		Set keys= record.keySet();
		for(Object key:keys){
			Object value = record.get(key);
			jsonRecord.put(key.toString(), value);
		}
		return jsonRecord;
	}

}
