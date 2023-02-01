import java.math.BigInteger;
import java.util.regex.Pattern;

public class BinaerTernaer
{
	BigInteger number;
	int lengthRun;
	
	BinaerTernaer(long number) {
		if(number < 0) number = -number;
		if(number ==0 ) number = 1;
		this.number = new BigInteger(Long.toString(number));
		this.lengthRun = 0;
	}
	
	String binaer() {
		return this.number.toString(2);
	}
	
	String binaer(BigInteger number) {
		return number.toString(2);
	}
	
	String ternaer() {
		return this.number.toString(3);
	}
	
	String ternaer(BigInteger number) {
		return number.toString(3);
	}
	
	boolean even() {
		BigInteger[] results = this.number.divideAndRemainder(new BigInteger("2"));
		return (results[1].intValue() == 0);
	}
	
	boolean even(BigInteger number) {
		BigInteger[] results = number.divideAndRemainder(new BigInteger("2"));
		return (results[1].intValue() == 0);
	}
	
	boolean even(int number) {
		return (number % 2 == 0);
	}
	
	boolean evenBinaer(String binaer) {
		return (binaer.charAt(binaer.length() - 1) == '0');
	}
	
	boolean evenTernaer(String ternaer) {
		return (this.even(checkSumOfStringNumber(ternaer)));
	}
	
	void next() {
		if(even())
		{
			this.number = this.number.divide(new BigInteger("2"));
		}
		else
		{
			this.number = this.number.multiply(new BigInteger("3")).add(new BigInteger("1"));
		}
		this.lengthRun++;
	}
	
	String cutLastZeroFromBinaer(String binaer) {
		return binaer.substring(0, binaer.length()-1);
	}
	
	String moveToLeftAndAdd1ToTernaer(String ternaer) {
		return ternaer + "1";
	}
	
	void nextWithStrings() {
		String binaer = this.binaer();
		String ternaer = this.ternaer();
		boolean binaerEven = this.evenBinaer(binaer);
		boolean ternaerEven = this.evenTernaer(ternaer);
		if(binaerEven != ternaerEven)
		{
			System.out.println("Something is wrong!!!");
			return;
		}
		if(binaerEven) {
			String nextBinaer = cutLastZeroFromBinaer(binaer);
			this.number = fromBinaeryStringToBigInteger(nextBinaer);
		} else {
			String nextTernaer = moveToLeftAndAdd1ToTernaer(ternaer);
			this.number = fromTernaeryStringToBigInteger(nextTernaer);
		}
		this.lengthRun++;
	}
	
	void nextWithStringsSkipEven() {
		String binaer = this.binaer();
		String ternaer = this.ternaer();
		boolean binaerEven = this.evenBinaer(binaer);
		
		/* kann nur ganz zu Anfang sein - danach ist number immer ungrade */
		this.preProcessingToFirstOddNumber(); 
		
		/* number ist immer ungerade (ausser zu Beginn) */
		String nextTernaer = moveToLeftAndAdd1ToTernaer(ternaer);
		this.number = fromTernaeryStringToBigInteger(nextTernaer);
		binaer = this.binaer();
		binaerEven = this.evenBinaer(binaer);
		while(binaerEven) {
			String nextBinaer = cutLastZeroFromBinaer(binaer);
			this.number = fromBinaeryStringToBigInteger(nextBinaer);
			binaer = this.binaer();
			binaerEven = this.evenBinaer(binaer);
		}
		
		this.lengthRun++;
	}
	
	void preProcessingToFirstOddNumber()
	{
		String binaer = this.binaer();
		boolean binaerEven = this.evenBinaer(binaer);
		
		/* kann nur ganz zu Anfang sein - danach ist number immer ungrade */
		while(binaerEven) {
			String nextBinaer = cutLastZeroFromBinaer(binaer);
			this.number = fromBinaeryStringToBigInteger(nextBinaer);
			binaer = this.binaer();
			binaerEven = this.evenBinaer(binaer);
		} 
	}
	
	String nextOddTernaer(String ternaer) {
		String nextTernaer = moveToLeftAndAdd1ToTernaer(ternaer);
		BigInteger number = fromTernaeryStringToBigInteger(nextTernaer);
		String binaer = this.binaer(number);
		boolean binaerEven = this.evenBinaer(binaer);
		while(binaerEven) {
			String nextBinaer = cutLastZeroFromBinaer(binaer);
			number = fromBinaeryStringToBigInteger(nextBinaer);
			binaer = this.binaer(number);
			binaerEven = this.evenBinaer(binaer);
		}
		return ternaer(number);
	}
	
	void printNextOddTernaer(String ternaer) {
		BigInteger number = this.fromTernaeryStringToBigInteger(ternaer);
		String nextTernaer = nextOddTernaer(ternaer);
		System.out.printf("%4d %20s --> %20s%n", number, ternaer, nextTernaer);
	}
	
	void printNextOddTernaerTest(String ternaer) {
		BigInteger number = this.fromTernaeryStringToBigInteger(ternaer);
		String nextTernaer = nextOddTernaer(ternaer);
		BigInteger nextNumber = this.fromTernaeryStringToBigInteger(nextTernaer);
		System.out.printf("%25s %25s --> %25s %25s %n", number.toString(), ternaer, nextTernaer, nextNumber.toString());
	}
		
	void printNextOddTernaerTest(BigInteger number) {
		String ternaer = this.ternaer(number);
		String nextTernaer = nextOddTernaer(ternaer);
		BigInteger nextNumber = this.fromTernaeryStringToBigInteger(nextTernaer);
		System.out.printf("%25s %25s --> %25s %25s %n", number.toString(), ternaer, nextTernaer, nextNumber.toString());
	}
	
	String addTwoToTernaer(String ternaer) {
	    int num = 0, power = 1;
	    for (int i = ternaer.length() - 1; i >= 0; i--) {
	        num += (ternaer.charAt(i) - '0') * power;
	        power *= 3;
	    }
	    num += 2;
	    StringBuilder result = new StringBuilder();
	    while (num > 0) {
	        result.append(num % 3);
	        num /= 3;
	    }
	    return result.reverse().toString();
	}
	
	void printManyTernaerSuccessors(int norOfLines) {
		String ternaer = "10";
		for(int i = 0; i < norOfLines; i++)
		{
			this.printNextOddTernaer(ternaer);
			ternaer = this.addTwoToTernaer(ternaer);
		}
	}
	
	boolean isOne() {
		return this.number.compareTo(new BigInteger("1"))==0;
	}
	
	int valueOfCharDigit(char c) {
		return c-48;
	}
	
	int checkSumOfStringNumber(String number) {
		int sum = 0;
		for(int index = 0; index < number.length(); index++) {
			sum += this.valueOfCharDigit(number.charAt(index));
		}
		return sum;
	}
	
	void printNumber() {
		String binaer = this.binaer();
		int checkSumBinaer = this.checkSumOfStringNumber(binaer);
		String ternaer = this.ternaer();
		int checkSumTernaer = this.checkSumOfStringNumber(ternaer);
		System.out.printf("%3d %20d %40s(%3d) %30s (%3d)%n", this.lengthRun, this.number, binaer, checkSumBinaer, ternaer, checkSumTernaer);
	}
	
	void printNumberShort() {
		String ternaer = this.ternaer();
		int checkSumTernaer = this.checkSumOfStringNumber(ternaer);
		System.out.printf("%3d %20d %30s (%3d)%n", this.lengthRun, this.number, ternaer, checkSumTernaer);
	}
	
	BigInteger fromBinaeryStringToBigInteger(String binaer) {
		BigInteger number = new BigInteger("0");
		BigInteger pow = new BigInteger("1");
		for(int index = binaer.length()-1; index >= 0; index--)
		{
			BigInteger value = new BigInteger(binaer.charAt(index)+"");
			number = number.add(pow.multiply(value));
			pow = pow.multiply(new BigInteger("2"));		
		}
		return number;
	}
	
	BigInteger fromTernaeryStringToBigInteger(String ternaer) {
		BigInteger number = new BigInteger("0");
		BigInteger pow = new BigInteger("1");
		for(int index = ternaer.length()-1; index >= 0; index--)
		{
			BigInteger value = new BigInteger(ternaer.charAt(index)+"");
			number = number.add(pow.multiply(value));
			pow = pow.multiply(new BigInteger("3"));	
		}
		return number;
	}
	
	boolean binaerStringIsPowOf2() {
		String binaer = this.binaer();
		boolean isPowOf2 = true;
		for(int index = 1; index < binaer.length() && isPowOf2; index++) {
			if(valueOfCharDigit(binaer.charAt(index)) != 0) isPowOf2 = false;
		}
		return isPowOf2;
	}

}

