import java.util.*;

public class StringUtil {

	public static int countCapitals(String s) {
        /* TO BE DONE */
	}
	
	public static String[] partsTokenizer(String url) {
        /* TO BE DONE */
	}
	
	public static String[] partsSplit(String url) {
        /* TO BE DONE */
	}
	
	public static String[] partsSubstring(String url) {
        /* TO BE DONE */
	}
	
    private static void println(String[] s) {
        for (int i = 0;i < s.length; i++) {
            System.out.print(s[i]+" ");
        }
        System.out.println();
    }

	public static void main(String[] args) {
		String s = "Hallo Welt";
		System.out.println("\""+s+"\" hat "+countCapitals(s)+" Grossbuchstaben");
		println(partsTokenizer("www.zhaw.ch"));
		println(partsSplit("radar.zhaw.ch"));
		println(partsSubstring("waikiki.zhaw.ch"));
	}
}