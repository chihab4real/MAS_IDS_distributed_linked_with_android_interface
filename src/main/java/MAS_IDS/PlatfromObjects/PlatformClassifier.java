package MAS_IDS.PlatfromObjects;

import weka.classifiers.Classifier;
import weka.classifiers.evaluation.Evaluation;
import weka.core.Instances;

public class PlatformClassifier {
    private Classifier classifier;
    private String Name;

    private Evaluation evaluation;

    public PlatformClassifier(){

    }

    public PlatformClassifier(String name , Classifier classifier, Instances trainData ) throws Exception {
        this.Name=name;
        this.classifier = classifier;

        Evaluation evalnn = new Evaluation(trainData);
        evalnn.evaluateModel(this.classifier,trainData);
        this.evaluation = evalnn;



    }




    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Classifier getClassifier() {
        return classifier;
    }

    public void setClassifier(Classifier classifier) {
        this.classifier = classifier;
    }

    public Evaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }
}
