import java.util.Set;
import java.util.HashSet;

import irproject.IRProjectHelper;
import it.acubelab.tagme.RelatednessMeasure;

public class OutJaccard extends RelatednessMeasure {

    public OutJaccard (String lang) {
        super(lang);
    }

    @Override
    public float rel (int first, int second) {
        int[] outFirst = IRProjectHelper.getOutlinks(first);
        int[] outSecond = IRProjectHelper.getOutlinks(second);

        Set<Integer> intersection = getIntersection(outFirst, outSecond);
        Set<Integer> union = getUnion(outFirst, outSecond);
        return ((float) intersection.size()) / ((float) union.size());
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

    private Set<Integer> getUnion (int[] A, int[] B) {
        Set<Integer> result = toSet(A);
        Set<Integer> missing = toSet(B);
        result.addAll(missing);
    
        return result;
    }

}