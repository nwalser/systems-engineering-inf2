import java.util.*;

public class StringUtil {
	private static String Delim = ".";


	public static int countCapitals(String s) {
		int uppercaseCount = 0;

		for(int i = 0; i < s.length(); i++){
			if(Character.isUpperCase(s.charAt(i))) uppercaseCount++;
		}

		return uppercaseCount;
	}
	
	public static String[] partsTokenizer(String url) {
		StringTokenizer st1 = new StringTokenizer(url, Delim);

		String[] tokens = new String[st1.countTokens()];

		for (int i = 0; st1.hasMoreTokens(); i++){
			tokens[i]=st1.nextToken();
		}

		return tokens;
	}
	
	public static String[] partsSplit(String url) {
		return url.split("\\.");
	}
	
	public static String[] partsSubstring(String url) {
		List<String> tokens = new ArrayList<>();
		String splitUrl = url;

		while(true){
			int index = splitUrl.indexOf(Delim);

			// no delimiter was found
			if(index == -1) {
				tokens.add(splitUrl);
				break;
			}

			String tok1 = splitUrl.substring(0, index);
			String tok2 = splitUrl.substring(index+1);
			tokens.add(tok1);
			splitUrl=tok2;
		}

		return tokens.toArray(new String[0]);
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