/**
 * Created by seongwonlee on 2017. 4. 5..
 */
public class PlayerInformation {
    private String name;
    private String sex;
    private String nationality;
    private double avgScore;

    public PlayerInformation() {
    }

    public PlayerInformation(String name, String sex, String nationality) {
        this.name = name;
        this.sex = sex;
        this.nationality = nationality;
        this.avgScore = 0.0;
    }

    public PlayerInformation(String name, String sex, String nationality, double avgScore) {
        this.name = name;
        this.sex = sex;
        this.nationality = nationality;
        this.avgScore = avgScore;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public String getNationality() {
        return nationality;
    }

    public void setAvgScore(double avgScore) {
        this.avgScore = avgScore;
    }

    public double getAvgScore() {
        return avgScore;
    }

    public String getInformation() {
        return toString();
    }

    @Override
    public String toString() {
        return name + " " + sex + " " + nationality + " " + Double.toString(avgScore);
    }
}
