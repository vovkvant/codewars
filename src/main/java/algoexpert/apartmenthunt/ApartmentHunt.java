package algoexpert.apartmenthunt;

import java.util.*;

public class ApartmentHunt {

    public static int apartmentHunting(List<Map<String, Boolean>> blocks, String[] reqs) {
        // Write your code here.
        List<Integer> result = new ArrayList<>(blocks.size());
        int blockIndex = 0;
        for (Map<String, Boolean> block : blocks) {
            int farthest = 0;
            for (String requirement : reqs) {
                if (!block.get(requirement)) {
                    int distance = findNearest(requirement, blockIndex, blocks);
                    if(distance > farthest) {
                        farthest = distance;
                    }
                }
            }
            result.add(farthest);
            blockIndex++;
        }

        return result.indexOf(Collections.min(result));
    }

    public static int findNearest(String requirement, int blockIndex, List<Map<String, Boolean>> blocks) {
        int i = blockIndex + 1, j = blockIndex - 1;
        while(i < blocks.size() || j >= 0) {
            if(i < blocks.size() && blocks.get(i).get(requirement)) {
                return i - blockIndex;
            }
            if(j >= 0 && blocks.get(j).get(requirement)) {
                return blockIndex - j;
            }
            i++;
            j--;
        }
        return 0;
    }
}
