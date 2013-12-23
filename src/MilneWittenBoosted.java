import irproject.IRProjectHelper;
import it.acubelab.tagme.RelatednessMeasure;

public class MilneWittenBoosted extends RelatednessMeasure {

    public MilneWittenBoosted (String lang) {
        super(lang);
    }

    @Override
    public float rel (int first, int second) {
        if (first == second) {
            return 1.0f;
        } else if (mutalLink(first, second)) {
            return 0.75f;
        }

        int[] inFirst = IRProjectHelper.getInlinks(first);
        int[] inSecond = IRProjectHelper.getInlinks(second);

        int wikipediaSize = IRProjectHelper.getWikipediaSize();
        int realIntersectionSize = getIntersectionSize(inFirst, inSecond);
        int intersectionSize = (realIntersectionSize == 0) ? 1 : realIntersectionSize;

        float numerator = (float) Math.log(Math.max(inFirst.length, inSecond.length)) - (float) Math.log(intersectionSize);
        float denominator = (float) Math.log(wikipediaSize) - (float) Math.log(Math.min(inFirst.length, inSecond.length));
        float result = numerator / denominator;

        return Math.min(result, 1.0f);
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

    private boolean mutualLink (int first, int second) {
        int[] outFirst = IRProjectHelper.getOutlinks(first);
        int[] outSecond = IRProjectHelper.getOutlinks(second);

        return (binarySearch(outFirst, second) >= 0) &&
            (binarySearch(outSecond, first) >= 0);
    }

}
