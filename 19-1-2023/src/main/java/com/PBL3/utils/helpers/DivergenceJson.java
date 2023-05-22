package com.PBL3.utils.helpers;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class DivergenceJson {
    public static ArrayList<List<String>> get(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, ArrayList<String>> map = mapper.readValue(json, Map.class);
            ArrayList<String> usersList = map.get("users");
            ArrayList<String> modsList = map.get("mods");
            return new ArrayList<>(Arrays.asList(usersList, modsList));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
