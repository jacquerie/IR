import java.util.Map;
import java.util.HashMap;

import irproject.IRProjectHelper;
import it.acubelab.tagme.RelatednessMeasure;

/**
 * @author  Jacopo Notarstefano jacopo.notarstefano@gmail.com
 * @version 0.0.1
 */
public class WeightedInJaccard extends RelatednessMeasure {

    public WeightedInJaccard (String lang) {
        super(lang);
    }

    @Override
    public float rel (int first, int second) {
        return weightedInJaccard(first, second);
    }

    private float weightedInJaccard (int first, int second) {
        int[] inFirst = IRProjectHelper.getInlinks(first);
        int[] inSecond = IRProjectHelper.getInlinks(second);

        double weightedIntersectionSize = getWeightedIntersectionSize(inFirst, inSecond);
        double weightedFirstSize = getWeightedSetSize(inFirst);
        double weightedSecondSize = getWeightedSetSize(inSecond);
        /* The weight of the union can be computed using the inclusion-exclusion
         * principle instead of computing explicitly the union.
         * See http://en.wikipedia.org/wiki/Inclusion%E2%80%93exclusion_principle
         */
        double weightedUnionSize = weightedFirstSize + weightedSecondSize - weightedIntersectionSize;
        double result = weightedIntersectionSize / weightedUnionSize;

        return (float) result;
    }

    /**
     * Returns the total weight of the common elements in two sorted arrays.
     *
     * Computes the total weight of the common elements in two sorted arrays.
     * Uses the standard "merge-like" algorithm.
     *
     * @param  A The first sorted array.
     * @param  B The second sorted array.
     * @return   The total weight of the common elements.
     */
    private double getWeightedIntersectionSize (int[] A, int[] B) {
        int i = 0, j = 0;
        double result = 0;
        
        while (i < A.length && j < B.length) {
            if (A[i] == B[j]) {
                result += getIdf(A[i]);
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

    /**
     * Returns the total weight of the elements in an array.
     *
     * Returns the total weight of the elements in an array. We could
     * use getWeightedIntersectionSize(A, A) instead, but this method
     * is included for code clarity.
     *
     * @param  A The array.
     * @return   The total weight of its elements.
     */
    private double getWeightedSetSize (int[] A) {
        int i = 0;
        double result = 0;

        while (i < A.length) {
            result += getIdf(A[i]);
            i++;
        }

        return result;
    }

    /**
     * The size of Wikipedia.
     */
    private int wikipediaSize = IRProjectHelper.getWikipediaSize();

    /**
     * A cache storing already computed idf values.
     */
    private Map<Integer, Double> idfCache = new HashMap<Integer, Double>(wikipediaSize);

    /**
     * Retrieves the idf of pageId from the cache or computes and stores it.
     *
     * Queries the cache for the given pageId. If found it is returned, otherwise
     * it's computed and added to the cache.
     *
     * @param  pageId An int representing the id of a page.
     * @return The idf of pageId.
     */
    private double getIdf (int pageId) {
        double idf;

        if (idfCache.containsKey(pageId)) {
            return idfCache.get(pageId);
        } else {
            idf = computeIdf(pageId);
            idfCache.put(pageId, idf);
            return idf;
        }
    }

    /**
     * Computes the idf of pageId.
     *
     * Computes the idf of pageId interpreting a page as a document,
     * its inlinks as its terms and applying the formula
     * $$idf(t,D) = \log{\frac{|W|}{\{d\in D\mid t\in d\}}}\text{.}$$
     *
     * @param  pageId An int representing the id of a page.
     * @return The idf of pageId.
     */
    private double computeIdf (int pageId) {
        int[] outLinks = IRProjectHelper.getOutlinks(pageId);
        return Math.log(wikipediaSize / ((double) outLinks.length));
    }

}
