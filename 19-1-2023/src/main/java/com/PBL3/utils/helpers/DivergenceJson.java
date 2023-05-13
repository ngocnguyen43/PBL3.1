package com.PBL3.utils.helpers;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class DivergenceJson {
    public static ArrayList<String[]> get(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, ArrayList<String>> map = mapper.readValue(json, Map.class);
            ArrayList<String> usersList = map.get("users");
            ArrayList<String> modsList = map.get("mods");
            String[] users = usersList.toArray(new String[0]);
            String[] mods = modsList.toArray(new String[0]);
            return new ArrayList<>(Arrays.asList(users, mods));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
