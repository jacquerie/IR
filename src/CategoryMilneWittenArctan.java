import java.lang.Math;

import irproject.IRProjectHelper;
import it.acubelab.tagme.RelatednessMeasure;

public class CategoryMilneWittenArctan extends RelatednessMeasure {

    public CategoryMilneWittenArctan (String lang) {
        super(lang);
    }

    @Override
    public float rel (int first, int second) {
        if (first == second) {
            return 1.0f;
        }

        int[] categoryFirst = IRProjectHelper.getCategories(first);
        int[] categorySecond = IRProjectHelper.getCategories(second);
        int wikipediaSize = 58586;
        int intersectionSize = getIntersectionSize(categoryFirst, categorySecond);

        float numerator = (float) Math.log(Math.max(categoryFirst.length, categorySecond.length)) - (float) Math.log(intersectionSize);
        float denominator = (float) Math.log(wikipediaSize) - (float) Math.log(Math.min(categoryFirst.length, categorySecond.length));
        float result = numerator / denominator;

        return ((float) (2 / Math.PI)) * ((float) Math.atan(result));
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
