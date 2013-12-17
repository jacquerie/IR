import it.acubelab.tagme.RelatednessMeasure;

public class Dummy extends RelatednessMeasure {

    public Dummy (String lang) {
        super(lang);
    }

    @Override
    public float rel (int a, int b) {
        return 0.0f;
    }

}
