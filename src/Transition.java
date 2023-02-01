public class Transition
{
	RegularExpression from;
	RegularExpression to;
	
	public Transition(RegularExpression from, RegularExpression to)
	{
		this.from = from;
		this.to = to;
	}
	
	String doTransition(String valueFrom) {
		int fromN = this.from.getN(valueFrom);
		String[] pre = this.to.getRegularExpression().toString().split("\\(");
		String[] post = pre[1].split("\\)");
		String first = pre[0];
		String second = post[0];
		String third = post[1].substring(1);	// remove *
		String repeat = "";
		for(int i = 0; i < fromN; i++) {
			repeat += second;
		}
		return first + repeat + third;
	}
	
	
}
