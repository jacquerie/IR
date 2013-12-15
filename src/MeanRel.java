import it.acubelab.tagme.RelatednessMeasure;

public class MeanRel extends RelatednessMeasure {

    public MeanRel (String lang) {
        super(lang);
    }

    @Override
    public float rel (int a, int b) {
        return 0.596429f;
    }

}
