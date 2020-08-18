package phonebook;

import java.util.HashMap;

public class HashRealisation {
    private final HashMap<String, Integer> phoneBase;

    public HashRealisation(String[] str) {
        phoneBase = new HashMap<>();
        for (String item : str){
            String[] data = item.split(" ", 2);
            phoneBase.put(data[1], Integer.parseInt(data[0]));
        }
    }

    public int startSearching(String[] persons){
        int founds = 0;

        for (String pers : persons){
            if (phoneBase.containsKey(pers)){
                founds++;
            }
        }
        return founds;
    }

    public HashMap<String, Integer> getPhoneBase() {
        return phoneBase;
    }

    public int size() {
        return phoneBase.size();
    }
}
