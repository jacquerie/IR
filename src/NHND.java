import it.acubelab.tagme.RelatednessMeasure;

public class NHND extends RelatednessMeasure {

    public NHND (String lang) {
        super(lang);
    }

    @Override
    public float rel(int a, int b) {
        return 0.0f;
    }

}
