
import java.io.*;
import java.util.*;
import java.text.*;

public class Rangliste {

    static class TimeCompare implements Comparator<Teilnehmer> {
        public int compare(Teilnehmer t1, Teilnehmer t2) {
            return (t1.h * 3600 + t1.min * 60 + t1.sec)
                    - (t2.h * 3600 + t2.min * 60 + t2.sec);
        }
    }

    public static void teilnehmerSortieren(int maxT, Teilnehmer[] zeiten) {
        Arrays.sort(zeiten, 0, maxT, new TimeCompare());
    }

    public static int teilnehmerLaden(String fileName, Teilnehmer[] zeiten) {
        int maxT = 0;
        try {
            String line;
            BufferedReader inFile = new BufferedReader(new FileReader(fileName));
            while ((line = inFile.readLine()) != null) {
                StringTokenizer tok = new StringTokenizer(line, ";:");
                zeiten[maxT] = new Teilnehmer();
                zeiten[maxT].name = tok.nextToken();
                zeiten[maxT].h = Integer.parseInt(tok.nextToken());
                zeiten[maxT].min = Integer.parseInt(tok.nextToken());
                zeiten[maxT].sec = Integer.parseInt(tok.nextToken());
                maxT++;
            }
            inFile.close();
            return maxT;
        } catch (Exception e) {
            return -2;
        }
    }

    public static int teilnehmerSpeichern(String fileName, int maxT, Teilnehmer[] zeiten) {
        try {
            PrintWriter outFile = new PrintWriter(new FileWriter(fileName), true);
            for (int i = 0; i < maxT; i++) {
                outFile.printf("%s;%d:%d:%d\n", zeiten[i].name, zeiten[i].h, zeiten[i].min, zeiten[i].sec);
            }
            outFile.close();
            return maxT;
        } catch (Exception e) {
            return -2;
        }

    }
}
