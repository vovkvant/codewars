package algoexpert.apartmenthunt;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class ApartmentHuntTest {

    @Test
    public void TestCase1() {
        List<Map<String, Boolean>> blocks = new ArrayList<Map<String, Boolean>>();

        blocks.add(0, new HashMap<String, Boolean>());
        blocks.get(0).put("gym", false);
        blocks.get(0).put("school", true);
        blocks.get(0).put("store", false);

        blocks.add(1, new HashMap<String, Boolean>());
        blocks.get(1).put("gym", true);
        blocks.get(1).put("school", false);
        blocks.get(1).put("store", false);

        blocks.add(2, new HashMap<String, Boolean>());
        blocks.get(2).put("gym", true);
        blocks.get(2).put("school", true);
        blocks.get(2).put("store", false);

        blocks.add(3, new HashMap<String, Boolean>());
        blocks.get(3).put("gym", false);
        blocks.get(3).put("school", true);
        blocks.get(3).put("store", false);

        blocks.add(4, new HashMap<String, Boolean>());
        blocks.get(4).put("gym", false);
        blocks.get(4).put("school", true);
        blocks.get(4).put("store", true);

        String[] reqs = new String[] {"gym", "school", "store"};
        assertTrue(ApartmentHunt.apartmentHunting(blocks, reqs) == 3);
    }

    @Test
    public void TestCase2() {
        List<Map<String, Boolean>> blocks = new ArrayList<Map<String, Boolean>>();

        //{"gym": false, "office": true, "school": true, "store": false},
        blocks.add(0, new HashMap<String, Boolean>());
        blocks.get(0).put("gym", false);
        blocks.get(0).put("office", true);
        blocks.get(0).put("school", true);
        blocks.get(0).put("store", false);

        // {"gym": true, "office": false, "school": false, "store": false},
        blocks.add(1, new HashMap<String, Boolean>());
        blocks.get(1).put("gym", true);
        blocks.get(1).put("office", false);
        blocks.get(1).put("school", false);
        blocks.get(1).put("store", false);

        // {"gym": true, "office": false, "school": true, "store": false},
        blocks.add(2, new HashMap<String, Boolean>());
        blocks.get(2).put("gym", true);
        blocks.get(2).put("office", false);
        blocks.get(2).put("school", true);
        blocks.get(2).put("store", false);

        // {"gym": false, "office": false, "school": true, "store": false},
        blocks.add(3, new HashMap<String, Boolean>());
        blocks.get(3).put("gym", false);
        blocks.get(3).put("office", false);
        blocks.get(3).put("school", true);
        blocks.get(3).put("store", false);

        // {"gym": false, "office": false, "school": true, "store": true}
        blocks.add(4, new HashMap<String, Boolean>());
        blocks.get(4).put("gym", false);
        blocks.get(4).put("office", false);
        blocks.get(4).put("school", true);
        blocks.get(4).put("store", true);

        //"gym", "office", "school", "store"
        String[] reqs = new String[] {"gym","office", "school", "store"};
        System.out.println(ApartmentHunt.apartmentHunting(blocks, reqs));
        assertTrue(ApartmentHunt.apartmentHunting(blocks, reqs) == 2);
    }
}
