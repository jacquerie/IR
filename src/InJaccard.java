import irproject.IRProjectHelper;
import it.acubelab.tagme.RelatednessMeasure;

public class InJaccard extends RelatednessMeasure {

    public InJaccard (String lang) {
        super(lang);
    }

    @Override
    public float rel (int first, int second) {
        int[] inFirst = IRProjectHelper.getInlinks(first);
        int[] inSecond = IRProjectHelper.getInlinks(second);

        int intersectionSize = getIntersectionSize(inFirst, inSecond);
        int unionSize = inFirst.length + inSecond.length - intersectionSize;

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
