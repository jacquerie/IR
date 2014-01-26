import java.lang.Math;

import irproject.IRProjectHelper;
import it.acubelab.tagme.RelatednessMeasure;

public class CategoryMilneWittenExponential extends RelatednessMeasure {

    public CategoryMilneWittenExponential (String lang) {
        super(lang);
    }

    @Override
    public float rel (int first, int second) {
        if (first == second) {
            return 1.0f;
        }

        int[] categoryFirst = IRProjectHelper.getCategories(first);
        int[] categorySecond = IRProjectHelper.getCategories(second);
        int categorySize = 58586;
        int intersectionSize = getIntersectionSize(categoryFirst, categorySecond);

        float numerator = (float) Math.log(Math.max(categoryFirst.length, categorySecond.length)) - (float) Math.log(intersectionSize);
        float denominator = (float) Math.log(categorySize) - (float) Math.log(Math.min(categoryFirst.length, categorySecond.length));
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