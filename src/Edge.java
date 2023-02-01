public class Edge
{
	Node from;
	Node to;
	Transition transition;
	
	public Edge(Node from, Node to)
	{
		this.from = from;
		this.to = to;
		this.transition = new Transition(from.getRegularExpression(), to.getRegularExpression());
	}

	public Node getFrom()
	{
		return this.from;
	}

	public Node getTo()
	{
		return this.to;
	}
	
	public String fires(String fromValue) {
		String toValue = this.transition.doTransition(fromValue);
		this.from.addNumber(fromValue);
		this.to.addNumber(toValue);
		return toValue;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null) return false;
		if(o == this) return true;
		if(this.getClass() != o.getClass()) return false;
		
		Edge other = (Edge)o;
		return (this.from.equals(other.from) && this.to.equals(other.to));
	}
	
	@Override
	public int hashCode() {
		return this.from.hashCode() * 7 + this.to.hashCode() * 3;
	}
	
}
