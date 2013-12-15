import java.lang.Math;
import java.util.Set;
import java.util.HashSet;

import irproject.IRProjectHelper;
import it.acubelab.tagme.RelatednessMeasure;

public class MilneWitten extends RelatednessMeasure {

    public MilneWitten (String lang) {
        super(lang);
    }

    @Override
    public float rel (int first, int second) {
        double result;
        int[] inFirst = IRProjectHelper.getInlinks(first);
        int[] inSecond = IRProjectHelper.getInlinks(second);
        int wikipediaSize = IRProjectHelper.getWikipediaSize();

        Set<Integer> intersection = getIntersection(inFirst, inSecond);
        result = (Math.log(Math.max(inFirst.length, inSecond.length)) - Math.log(intersection.size())) / (Math.log(wikipediaSize) - Math.log(Math.min(inFirst.length, inSecond.length)));
        if (result > 1.0f) {
            return 1.0f;
        } else if (result < 0.0f) {
            return 0.0f;
        } else {
            return (float) result;
        }
    }

    private Set<Integer> toSet (int[] arr) {
        Set<Integer> result = new HashSet<Integer>();

        for (int i = 0; i < arr.length; i++) {
            result.add(arr[i]);
        }

        return result;
    }

    private Set<Integer> getIntersection (int[] A, int[] B) {
        Set<Integer> result = toSet(A);
        Set<Integer> difference = toSet(B);
        result.retainAll(difference);

        return result;
    }

}
