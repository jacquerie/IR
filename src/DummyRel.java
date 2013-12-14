import it.acubelab.tagme.RelatednessMeasure;

public class DummyRel extends RelatednessMeasure {

    public DummyRel (String lang) {
        super(lang);
    }

    @Override
    public float rel (int a, int b) {
        return 0.596429f;
    }

}
