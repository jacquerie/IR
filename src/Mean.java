import it.acubelab.tagme.RelatednessMeasure;

public class Mean extends RelatednessMeasure {

    public Mean (String lang) {
        super(lang);
    }

    @Override
    public float rel (int a, int b) {
        return 0.591f;
    }

}
