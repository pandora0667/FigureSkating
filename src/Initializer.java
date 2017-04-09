import java.util.*;
import java.util.stream.Collectors;


/**
 * Created by seongwonlee on 2017. 4. 5..
 */
public class Initializer {
    private FileIO fileIO;
    private ScoreCalculation scoreCalculation;
    private LinkedList<PlayerInformation> playerInformation;

    public Initializer() {
        this.fileIO = new FileIO("/Users/seongwonlee/Workspace/Project /FigureSkating/Data/");
        this.scoreCalculation = new ScoreCalculation();
        this.playerInformation = new LinkedList<>();
    }

    public void setPlayerInformation(String name, String sex, String nationality) {
        playerInformation.add(new PlayerInformation(name, sex, nationality));
    }

    public void setPlayerInformation(String name, String sex, String nationality, String avg) {
        double avgScore = Double.valueOf(avg);
        playerInformation.add(new PlayerInformation(name, sex, nationality, avgScore));
    }

    public void getPlayerInformation() {
        playerInformation.stream()
                .forEach(s -> System.out.println("\t" + s.getName() + " " + s.getSex() + " " + s.getNationality() + " " + s.getAvgScore()));
    }

    public void setScore(double[][] panelScores, String name) {

        double avg = scoreCalculation.avg(scoreCalculation.sum(panelScores));

        playerInformation.stream()
                .filter(s -> s.getName().equals(name))
                .filter(s -> s.getSex().equals("FEMALE"))
                .forEach(s -> s.setAvgScore(avg * Selector.FEMALE_WEIGHT));

        playerInformation.stream()
                .filter(s -> s.getName().equals(name))
                .filter(s -> s.getSex().equals("MALE"))
                .forEach(s -> s.setAvgScore(avg * Selector.MALE_WEIGHT));

        setFileSave(name);
    }

    public boolean isExist(String name) {
        boolean exist = playerInformation.stream()
                .anyMatch(s -> s.getName().equals(name));

        return exist;
    }

    public double setPlayerRank(String name) {
        Map<String, Double> map = playerInformation.stream() // 이름과 점수만 뽑음
                .collect(Collectors.toMap(s -> s.getName(), s -> s.getAvgScore()));

        // rank 순위를 위한 sort
        Map<String, Double> rank = new HashMap<>();
        map.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .forEachOrdered(s -> rank.put(s.getKey(), s.getValue()));

        System.out.println(rank);
        System.out.println();
        return rank.get(name);

    }

    public void setFileSave(String name) {
        String information = null;
        for (int i=0; i<playerInformation.size(); i++) {
            if (playerInformation.get(i).getName().equals(name)) {
                information = playerInformation.get(i).getInformation();
                break;
            }
        }

        fileIO.savePlayerInformation(name, information);
    }

    public boolean setFileRead() {
        String[] information =  fileIO.readPlayerInformation();

        if (information != null) {
            for (String info : information) {
                String[] player = info.split("\\s");
                setPlayerInformation(player[0], player[1], player[2], player[3]);
            }
            return true;

        } else {
            return false;
        }
    }
}

