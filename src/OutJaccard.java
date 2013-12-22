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

        int intersectionSize = getIntersectionSize(outFirst, outSecond);
        int unionSize = outFirst.length + outSecond.length - intersectionSize;

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
