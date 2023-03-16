import java.io.*;
import java.util.*;
import java.text.*;

public class RanglisteView {
	static final int MAXT = 100;
	static String filename = "Rangliste.csv";
	static int maxT = 0;
	static Teilnehmer[] teilnehmer = new Teilnehmer[MAXT];

	static void teilnehmerAusgeben(int maxT, Teilnehmer[] teilnehmer) {
		System.out.printf("\nTeilnehmerliste\n");
		int i;
		for (i = 0; i < maxT; i++) {
			System.out.printf("%-20s %02d:%02d:%02d\n", teilnehmer[i].name,
					teilnehmer[i].h, teilnehmer[i].min, teilnehmer[i].sec);
		}
	}

	static Teilnehmer leseTeilnehmer() {
		Scanner scanner = new Scanner(System.in);
		scanner.useLocale(Locale.ENGLISH);
		Teilnehmer tn = new Teilnehmer();
		System.out.printf("Name: ");
		System.out.flush();
		tn.name = scanner.nextLine();
		System.out.printf("Zeit (h:min:sec): ");
		System.out.flush();
		String time = scanner.nextLine();
		tn.h = Integer.parseInt(time.split(":")[0]);
		tn.min = Integer.parseInt(time.split(":")[1]);
		tn.sec = Integer.parseInt(time.split(":")[2]);
		return tn;
	}

	static void teilnehmerErfassen(Teilnehmer[] teilnehmer) {
		teilnehmer[maxT] = leseTeilnehmer();
		maxT++;
	}
    
	static int zeigeAuswahlMenu() {
		int eingabe;
		System.out.printf("\nRANGLISTENVERWALTUNG \n");
		System.out.printf(" 1)	Teilnehmer erfassen	\n");
		System.out.printf(" 2)	Teilnehmer ausgeben\n");
		System.out.printf(" 3)	Teilnehmer nach	Rang ordnen\n");
		System.out.printf(" 4)	Teilnehmer laden \n");
		System.out.printf(" 5)	Teilnehmer speichern \n");
		System.out.printf(" 6)	Teilnehmer alle	loeschen \n");
		System.out.printf(" 9)	Programm verlassen\n\n");
		System.out.printf(">");
		System.out.flush();
		Scanner scanner = new Scanner(System.in);
		return scanner.nextInt();
	}

	static void zeigeResultat(String msg, int res) {
		if (res >= 0) {
			System.out.printf("\n%d %s\n", res, msg);
		} else {
			System.out.printf("\nErrorcode: %d\n", -res);
		}
	}
    
	public static void main(String[] args) {
		while (true) {
			int res;
			int eingabe = zeigeAuswahlMenu();       
			switch (eingabe) {
			case 1:
				teilnehmerErfassen(teilnehmer);
				break;
			case 2:
				teilnehmerAusgeben(maxT, teilnehmer);
				break;
			case 3:
				Rangliste.teilnehmerSortieren(maxT, teilnehmer);
				break;
			case 4:
				res = Rangliste.teilnehmerLaden(filename, teilnehmer);
				zeigeResultat("Teilnehmer geladen", res);
				if (res >= 0) {
					maxT = res;
				}
				break;
			case 5:
				res = Rangliste.teilnehmerSpeichern(filename, maxT, teilnehmer);  
				zeigeResultat("Teilnehmer gespeichert", res);
				if (res >= 0) {
					maxT = res;
				}
				break;
			case 6:
				maxT = 0;
				break;
			case 9:
				return;
			default:
				break;
			}
		}
	}

}
