import irproject.IRProjectHelper;
import it.acubelab.tagme.RelatednessMeasure;

/**
 * @author  Jacopo Notarstefano jacopo.notarstefano@gmail.com
 * @version 0.0.1
 */
public class Probability extends RelatednessMeasure {

    public Probability (String lang) {
        super(lang);
    }

    @Override
    public float rel (int first, int second) {
        int[] outFirst = IRProjectHelper.getOutlinks(first);
        int[] outSecond = IRProjectHelper.getOutlinks(second);

        double actualIntersectionSize = (double) getIntersectionSize(outFirst, outSecond);
        double expectedIntersectionSize = ((double) (outFirst.length * outSecond.length)) * P;
        double result = actualIntersectionSize - expectedIntersectionSize;

        // System.out.println("outFirst=" + outFirst.length + " outSecond=" + outSecond.length + " expectedSize=" + (int) Math.floor(expectedIntersectionSize) + " actualSize=" + actualIntersectionSize);

        if (result <= 0) {
            return (float) 0;
        } else {
            /* Estimates the cumulative distribution function of an unknown
             * distribution using a generalized logistic function whose
             * parameters were determined by trial and error.
             */
            return (float) (1 / (1 + Math.exp(B * (result - M))));
        }
    }
    
    /**
     * The growth rate of the generalised logistic function.
     * See http://en.wikipedia.org/wiki/Generalised_logistic_function
     */
    private static double B = -0.25;

    /**
     * The time of maximum growth of the generalised logistic function.
     * See http://en.wikipedia.org/wiki/Generalised_logistic_function
     */
    private static double M = 16;

    /**
     * The probability that two pages link the same page.
     */
    private static double P = getP();

    /**
     * Returns the probability that two pages link the same page.
     *
     * Computes the probabilty that two pages link the same page using
     * the argument in http://math.stackexchange.com/q/664121/4471
     *
     * @return The probability that two pages link the same page.
     */
    private static double getP () {
        int addend, i, wikipediaSize = IRProjectHelper.getWikipediaSize();
        long numerator = 0, denominator, inlinksSize = 0;
        double result;

        for (i = 0; i < wikipediaSize; i++) {
            addend = IRProjectHelper.getInlinks(i).length;
            inlinksSize += addend;
            numerator += Math.pow(addend, 2);
        }

        denominator = (long) Math.pow(inlinksSize, 2);
        result = (double) numerator / (double) denominator;

        return result;
    }

    /**
     * Returns the number of common elements in two sorted arrays.
     * 
     * Computes the number of common elements in two sorted arrays of ints.
     * Uses the standard "merge-like" algorithm. 
     * 
     * @param  A The first sorted array.
     * @param  B The second sorted array.
     * @return   The number of common elements.
     */   
    private int getIntersectionSize (int[] A, int[] B) {
        int i = 0, j = 0, result = 0;

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
