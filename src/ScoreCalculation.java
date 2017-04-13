import java.util.Arrays;

/**
 * Created by seongwonlee on 2017. 4. 5..
 */
public class ScoreCalculation {
    private double avgScore;

    public ScoreCalculation() {
    }

    public double avg(double[] sumScores) {
        avgScore = Arrays.stream(sumScores)
                .sorted()
                .limit(sumScores.length-1)
                .skip(1)
                .average()
                .getAsDouble();

        return avgScore;
    }

    public double[] sum(double[][] panelScores) {
        double[] sumScores = new double[Selector.SKILL];

        for (int i=0; i<Selector.DEFAULT_PANEL; i++) {
            for (int j=0; j<Selector.SKILL; j++) {
                sumScores[i] = Arrays.stream(panelScores[i])
                        .sum();
            }
        }
        return sumScores;
    }
}

