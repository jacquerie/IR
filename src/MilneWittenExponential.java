import java.lang.Math;

import irproject.IRProjectHelper;
import it.acubelab.tagme.RelatednessMeasure;

public class MilneWittenExponential extends RelatednessMeasure {

    public MilneWittenExponential (String lang) {
        super(lang);
    }

    @Override
    public float rel (int first, int second) {
        if (first == second) {
            return 1.0f;
        }

        int[] inFirst = IRProjectHelper.getInlinks(first);
        int[] inSecond = IRProjectHelper.getInlinks(second);
        int wikipediaSize = IRProjectHelper.getWikipediaSize();
        int intersectionSize = getIntersectionSize(inFirst, inSecond);

        float numerator = (float) Math.log(Math.max(inFirst.length, inSecond.length)) - (float) Math.log(intersectionSize);
        float denominator = (float) Math.log(wikipediaSize) - (float) Math.log(Math.min(inFirst.length, inSecond.length));
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
