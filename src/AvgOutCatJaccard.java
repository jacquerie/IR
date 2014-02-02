import irproject.IRProjectHelper;
import it.acubelab.tagme.RelatednessMeasure;

public class AvgOutCategoryJaccard extends RelatednessMeasure {

    public AvgOutCategoryJaccard (String lang) {
        super(lang);
    }

    @Override
    public float rel (int first, int second) {
        int i, j;
        float current, result = 0.0f;

        int[] outFirst = IRProjectHelper.getOutlinks(first);
        int[] outSecond = IRProjectHelper.getOutlinks(second);
        int count = outFirst.length * outSecond.length;

        for (i = 0; i < outFirst.length; i++) {
            for (j = 0; j < outSecond.length; j++) {
                current = categoryJaccard(outFirst[i], outSecond[j]);
                if (Float.isNaN(current)) {
                    count--;
                } else {
                    result += categoryJaccard(outFirst[i], outSecond[j]);
                }
            }
        }

        if (count > 0) {
            return 3000 * (result / count) / (3000 * (result / count) + 1);
        } else {
            return inJaccard(first, second);
        }
    }

    private float inJaccard (int first, int second) {
        int[] inFirst = IRProjectHelper.getInlinks(first);
        int[] inSecond = IRProjectHelper.getInlinks(second);

        int intersectionSize = getIntersectionSize(inFirst, inSecond);
        int unionSize = inFirst.length + inSecond.length - intersectionSize;

        return (float) intersectionSize / (float) unionSize;
    }

    private float categoryJaccard (int first, int second) {
        int[] categoryFirst = IRProjectHelper.getCategories(first);
        int[] categorySecond = IRProjectHelper.getCategories(second);

        int intersectionSize = getIntersectionSize(categoryFirst, categorySecond);
        int unionSize = categoryFirst.length + categorySecond.length - intersectionSize;

        return (float) intersectionSize / (float) unionSize;
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

        return result;
    }

}
