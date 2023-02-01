import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

public class Node
{
	BigInteger smallestNumber;
	RegularExpression regularExpression;
	Set<String> numbers;
	
	public Node(BigInteger smallestNumber, RegularExpression regularExpression)
	{
		this.smallestNumber = smallestNumber;
		this.regularExpression = regularExpression;
		this.numbers = new HashSet<>();
	}

	public BigInteger getSmallestNumber()
	{
		return this.smallestNumber;
	}

	public RegularExpression getRegularExpression()
	{
		return this.regularExpression;
	}
	
	public void addNumber(String number) {
		this.numbers.add(number);
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null) return false;
		if(o == this) return true;
		if(this.getClass() != o.getClass()) return false;
		
		Node other = (Node)o;
		return this.regularExpression.toString().equals(other.regularExpression.toString());
	}
	
	@Override
	public int hashCode() {
		return this.regularExpression.toString().hashCode();
	}
	
	
}
