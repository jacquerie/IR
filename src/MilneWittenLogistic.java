import java.lang.Math;

import irproject.IRProjectHelper;
import it.acubelab.tagme.RelatednessMeasure;

public class MilneWittenLogistic extends RelatednessMeasure {

    public MilneWittenLogistic (String lang) {
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

        return ((float) 2 / ( 1 + (float) Math.exp(-result)) - (float) 1);
    }

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
