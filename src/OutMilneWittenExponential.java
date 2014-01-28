import java.lang.Math;

import irproject.IRProjectHelper;
import it.acubelab.tagme.RelatednessMeasure;

public class OutMilneWittenExponential extends RelatednessMeasure {

    public OutMilneWittenExponential (String lang) {
        super(lang);
    }

    @Override
    public float rel (int first, int second) {
        if (first == second) {
            return 1.0f;
        }

        int[] outFirst = IRProjectHelper.getOutlinks(first);
        int[] outSecond = IRProjectHelper.getOutlinks(second);
        int wikipediaSize = IRProjectHelper.getWikipediaSize();
        int intersectionSize = getIntersectionSize(outFirst, outSecond);

        float numerator = (float) Math.log(Math.max(outFirst.length, outSecond.length)) - (float) Math.log(intersectionSize);
        float denominator = (float) Math.log(wikipediaSize) - (float) Math.log(Math.min(outFirst.length, outSecond.length));
        float result = numerator / denominator;

        if (Math.abs(result) < EPS) {
            return result;
        } else {
            return ((float) Math.exp(-1/result));
        }
    }

    private static final float EPS = 0.000001f;

    private int getIntersectionSize (int[] A, int[] B) {
        int i = 0, j = 0;
        int result = 0;
        
        while (i < A.length && j < B.length) {
            if (A[i] == B[j]) {
                result++;
                i++;
                j++;
            } else if (A[i] > B[j]) {
                j++;
            } else {
                i++;
            }
        }

        return result == 0 ? 1 : result;
    }

}
