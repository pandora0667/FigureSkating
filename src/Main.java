import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by seongwonlee on 2017. 4. 5..
 */
public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Initializer initializer = new Initializer();

    public static void main(String[] args) {
        System.out.println("\t ---------- 2018 평창동계올림픽 피겨스케이팅 점수 프로그램 V2.0 ----------");
        System.out.println();

        System.out.println("저장된 선수 정보가 있는지 확인합니다.");
        if (initializer.setFileRead()) {
            System.out.println("저장된 선수가 있습니다.");
            System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
            initializer.getPlayerInformation();
            System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
            System.out.println();
        } else {
            System.out.println("저장된 선수가 없습니다.");
            System.out.println();
        }

        System.out.println("\t -*-*- Starting Program -*-*- ");

        try {
            boolean run = true;
            while (run) {
                System.out.println("| 1. Player Section | 2. Panel Section | 0. EXIT |");
                int menu = scanner.nextInt();
                switch (menu) {
                    case Selector.PLAYER_SECTION:
                        PlayerSection();
                        break;

                    case Selector.PANEL_SECTION:
                        PanelSection();
                        break;

                    case Selector.EXIT:
                        run = false;
                        System.out.println("2018.02.09~25 Hello Pyeongchang");
                        break;

                    default:
                        System.out.println("원하는 메뉴를 찾을 수 없습니다.");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("허용되지 않은 입력입니다. 다시 시도하세요.");
        }
    }

    private static void PlayerSection() {
        System.out.println("\t ---------- Player Section ---------");
        System.out.println();

        System.out.println("| 1. Player List | 2. Player Rank | 0. Return Main |");
        int menu = scanner.nextInt();

        try {
            switch (menu) {
                case Selector.PLAYER_LIST:
                    initializer.getPlayerInformation();
                    break;

                case  Selector.PLAYER_RANK:
                    System.out.print("선수이름을 입력 -> ");
                    String name = scanner.next();

                    if (initializer.isExist(name)) {
                        System.out.println(name + "선수의 점수 : " + initializer.setPlayerRank(name));
                    } else {
                        System.out.println("선수가 존재하지 않습니다.");
                        break;
                    }
                    break;

                case Selector.EXIT:
                    break;

                default:
                    System.out.println("원하는 메뉴를 찾을 수 없습니다.");
                    System.out.println();
            }
        } catch (InputMismatchException e) {
            System.out.println("허용되지 않은 입력입니다. 다시 시도하세요.");
            System.out.println();
        }
    }

    private static void PanelSection() {
        System.out.println("\t --------- Panel Section ---------");
        System.out.println();

        System.out.println("| 1. Player Input | 2. Player Score Input | 0. Return Main |");
        int menu = scanner.nextInt();

        try {
            switch (menu) {
                case Selector.PLAYER_INPUT:
                    System.out.print("| 선수이름 | -> ");
                    String name = scanner.next();

                    System.out.print("| 1. 여자 | 2. 남자 | -> ");
                    String sex = scanner.next();
                    if (sex.equals(Selector.FEMALE)) {
                        sex = "FEMALE";
                    } else {
                        sex = "MALE";
                    }

                    System.out.print("| 국적 | -> ");
                    String nationality = scanner.next();

                    System.out.println("| " + name + " | "+ sex + " | " + nationality + " | " + "입력합니다." );
                    initializer.setPlayerInformation(name, sex, nationality);
                    break;

                case Selector.SCORE_INPUT:
                    System.out.println("\t *** 각 기술점수는 10점이 최대입니다. 이를 어기면 점수는 0으로 초기화 됩니다 *** ");
                    System.out.print("선수단 이름 -> ");
                    name = scanner.next();

                    if (!initializer.isExist(name)) {
                        System.out.println("선수가 존재하지 않습니다. ");
                        break;
                    }

                    double[][] panelScore = new double[Selector.DEFAULT_PANEL][Selector.SKILL];

                    for (int i=0; i<Selector.DEFAULT_PANEL; i++) {
                        System.out.println("심사위원" + (i+1) + " 점수입력");
                        System.out.println("| 기술점수 | 동작의 연결 | 연기 | 안무 | 곡해석 |");

                        for (int j=0; j<Selector.SKILL; j++) {
                            panelScore[i][j] = scanner.nextDouble();

                            if (panelScore[i][j] > Selector.MAXIMUM_SCORE) {
                                panelScore[i][j] = 0;
                            }
                        }
                    }
                    initializer.setScore(panelScore, name);
                    break;

                case Selector.EXIT:
                    break;

                default:
                    System.out.println("원하는 메뉴를 찾을 수 없습니다.");
                    System.out.println();
            }
        } catch (InputMismatchException e) {
            System.out.println("허용되지 않은 입력입니다. 다시 시도하세요.");
            System.out.println();
        }
    }
}