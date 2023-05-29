package commun;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RegleYams {
    public List<String> determinerCategories(List<Integer> des) {
        List<String> categories = new ArrayList<>();
        Collections.sort(des);

        if (isYams(des)) {
            categories.add("yams");
        }

        if (isGrandeSuite(des)) {
            categories.add("grandeSuite");
        }

        if (isPetiteSuite(des)) {
            categories.add("petiteSuite");
        }

        if (isFullHouse(des)) {
            categories.add("fullHouse");
        }

        if (isCarre(des)) {
            categories.add("carre");
        }

        if (isBrelan(des)) {
            categories.add("brelan");
        }

        for (int i = 1; i <= 6; i++) {
            if (Collections.frequency(des, i) >= 1) {
                categories.add("total" + i);
            }
        }

        categories.add("chance");

        return categories;
    }
    private boolean isYams(List<Integer> des) {
        return des.get(0).equals(des.get(4));
    }

    private boolean isGrandeSuite(List<Integer> des) {
        return des.equals(Arrays.asList(1, 2, 3, 4, 5)) || des.equals(Arrays.asList(2, 3, 4, 5, 6));
    }

    private boolean isPetiteSuite(List<Integer> des) {
        for (int i = 0; i <= 1; i++) {
            if (des.subList(i, i + 4).equals(Arrays.asList(1, 2, 3, 4)) || des.subList(i, i + 4).equals(Arrays.asList(2, 3, 4, 5)) || des.subList(i, i + 4).equals(Arrays.asList(3, 4, 5, 6))) {
                return true;
            }
        }
        return false;
    }

    private boolean isFullHouse(List<Integer> des) {
        return Collections.frequency(des, des.get(0)) == 3 && Collections.frequency(des, des.get(4)) == 2 ||
                Collections.frequency(des, des.get(0)) == 2 && Collections.frequency(des, des.get(4)) == 3;
    }

    private boolean isCarre(List<Integer> des) {
        for (int i = 0; i <= 1; i++) {
            if (des.subList(i, i + 4).stream().distinct().count() == 1) {
                return true;
            }
        }
        return false;
    }

    private boolean isBrelan(List<Integer> des) {
        for (int i = 0; i <= 2; i++) {
            if (des.subList(i, i + 3).stream().distinct().count() == 1) {
                return true;
            }
        }
        return false;
    }


}
