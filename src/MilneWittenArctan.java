import java.lang.Math;
import java.util.Set;
import java.util.HashSet;

import irproject.IRProjectHelper;
import it.acubelab.tagme.RelatednessMeasure;

public class MilneWittenArctan extends RelatednessMeasure {

    public MilneWittenArctan (String lang) {
        super(lang);
    }

    @Override
    public float rel (int first, int second) {
        int[] inFirst = IRProjectHelper.getInlinks(first);
        int[] inSecond = IRProjectHelper.getInlinks(second);
        int wikipediaSize = IRProjectHelper.getWikipediaSize();

        Set<Integer> intersection = getIntersection(inFirst, inSecond);
        int intersectionSize = intersection.size() == 0 ? 1 : intersection.size();

        float numerator = (float) Math.log(Math.max(inFirst.length, inSecond.length) - (float) Math.log(intersectionSize));
        float denominator = (float) Math.log(wikipediaSize) - (float) Math.log(Math.min(inFirst.length, inSecond.length));
        float result = numerator / denominator;

        return ((float) (2 / Math.PI)) * ((float) Math.atan(result));
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
