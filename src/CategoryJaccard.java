import irproject.IRProjectHelper;
import it.acubelab.tagme.RelatednessMeasure;

public class CategoryJaccard extends RelatednessMeasure {

    public CategoryJaccard (String lang) {
        super(lang);
    }

    @Override
    public float rel (int first, int second) {
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
