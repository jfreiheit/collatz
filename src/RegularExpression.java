import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpression
{
	Pattern regularExpression;

	final static boolean DEBUG = false;

	public RegularExpression(String regularExpression)
	{
		this.regularExpression = Pattern.compile(regularExpression);
	}
	
	public boolean match(Pattern otherPattern) {
		Matcher m = this.regularExpression.matcher(otherPattern.toString());
		return m.matches();
	}
	
	public Pattern getRegularExpression()
	{
		return regularExpression;
	}
	
	/*
	 * assume something like my = ""1010(10101010)*111"
	 * it is assumed that there is ONE pair of parenthesis
	 * it is assumed that there is a * or + after )
	 * aim ist to find the N how often * applies
	 * e.g. other = "1010111"  --> N is 0
	 * e.g. other = "1010 10101010 10101010 111"	--> N is 2
	 * if other doesn't match N is -1
	 */
	public int getN(String other) {
		String my = this.regularExpression.toString();
		Matcher m = this.regularExpression.matcher(other);
		if(m.matches()) {
			String[] pre = my.split("\\(");
			String[] post = pre[1].split("\\)");
			String first = pre[0];
			String second = post[0];
			String third = post[1].substring(1);	// remove *
			if(DEBUG) System.out.println("--> 1 : " + first + " " + second + " " + third);
			String otherCopy = other;
			otherCopy = otherCopy.substring(first.length());	// remove first from other -> e.g. 1010
			if(DEBUG) System.out.println("--> 2 : " + otherCopy);
			otherCopy = otherCopy.substring(0, otherCopy.length() - third.length()); // remove third from other --> e.g. 111
			if(DEBUG) System.out.println("--> 3 : " + otherCopy);
			int n = 0;
			while(otherCopy.length() > 0) {
				n++;
				otherCopy = otherCopy.substring(second.length());
				if(DEBUG) System.out.println("--> 4 : " + otherCopy);
			}
			return n;
		}
		else return -1;
	}
}
