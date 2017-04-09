import java.io.*;

/**
 * Created by seongwonlee on 2017. 4. 5..
 */
public class FileIO {
    private File path;
    private String[] fileList;

    public FileIO(String path) {
        this.path = new File(path);
        this.fileList = null;
        directorySearcher();
    }

    public void directorySearcher() {
        fileList = path.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".txt");
            }
        });
    }

    public boolean isEmpty() {
        if (fileList.length == 0) {
            return true;
        } else {
            return false;
        }
    }

    public void savePlayerInformation(String name, String information) {
        try {
            File file = new File(path + "//" +name + ".txt");
            FileWriter save = new FileWriter(file, false);
            save.write(information);
            save.flush();
            save.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//
//    public void savePlayerRank() {
//        try {
//            File file = new File(path + "Rank.txt");
//            FileWriter save = new FileWriter(file, true);
//            save.write("Ranking");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void readPlayerRank() {
//
//    }

    public String[] readPlayerInformation() {
        if (!isEmpty()) {
            String[] filePlayerInformation = new String[fileList.length];
            for (int i=0; i<fileList.length; i++) {
                try {
                    BufferedReader in = new BufferedReader(new FileReader(path + "//" + fileList[i]));
                    filePlayerInformation[i] = in.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return filePlayerInformation;
        } else {
            return null;
        }
    }
}
