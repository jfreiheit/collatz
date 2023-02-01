import java.math.BigInteger;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {

        System.out.printf("%n%n--------------------- simple int next ----------------------%n%n");
        BinaerTernaer bt = new BinaerTernaer(129);
        while(!bt.isOne()) {
            bt.printNumber();
            bt.next();
        }

        System.out.printf("%n%n------------------------ some tests ------------------------%n%n");
        /* die gleiche Liste erstellen, nur dass mit den binaeren und ternaeren Strings gearbeitet wird */
        System.out.println(bt.fromBinaeryStringToBigInteger("101111000"));
        System.out.println(bt.fromTernaeryStringToBigInteger("10221"));

        System.out.printf("%n%n-------------- string next binaer and ternaer --------------%n%n");
        BinaerTernaer bt1 = new BinaerTernaer(129);
        while(!bt1.isOne()) {
            bt1.printNumber();
            bt1.nextWithStrings();
        }

        System.out.printf("%n%n------- string next binaer and ternaer skip even -----------%n%n");
        BinaerTernaer bt2 = new BinaerTernaer(129);

        while(!bt2.binaerStringIsPowOf2() && !bt2.isOne()) {
            bt2.printNumberShort();
            bt2.nextWithStringsSkipEven();
        }

        System.out.printf("%n%n---------- print odd successors of odd ternaer -------------%n%n");
        BinaerTernaer bt3 = new BinaerTernaer(129);
        bt3.printManyTernaerSuccessors(1000);

        System.out.printf("%n%n---------------------- some tests --------------------------%n%n");
        /*
         * * ist Wiederholung inkl. 0-mal
         * + ist Wiederholung mind. 1-mal
         * u ist ungerade (1, 3, 5, 7, ...)
         * g ist gerade (2, 4, 6, 8, ...)
         */
        /* 10(2)* --> 12(2)* */
        bt3.printNextOddTernaerTest("102");			// unten weitere Regel Konflikt !!!
        bt3.printNextOddTernaerTest("1022");
        bt3.printNextOddTernaerTest("10222");
        bt3.printNextOddTernaerTest("102222");
        bt3.printNextOddTernaerTest("1022222");
        bt3.printNextOddTernaerTest("10222222");

        /* 21(2)* --> 102(2)* */
        bt3.printNextOddTernaerTest("21222");
        bt3.printNextOddTernaerTest("212222");
        bt3.printNextOddTernaerTest("2122222");

        /* 120(2)* --> 212(2)* */
        bt3.printNextOddTernaerTest("12022");
        bt3.printNextOddTernaerTest("120222");
        bt3.printNextOddTernaerTest("1202222");

        /* 201(2)* --> 1002(2)* */
        bt3.printNextOddTernaerTest("20122");
        bt3.printNextOddTernaerTest("201222");
        bt3.printNextOddTernaerTest("2012222");

        /* 1(00)*02 --> 1(11)*22 */
        bt3.printNextOddTernaerTest("102");			// Konflikt mit 102 --> 122 oben !!!
        bt3.printNextOddTernaerTest("10002");
        bt3.printNextOddTernaerTest("1000002");
        bt3.printNextOddTernaerTest("100000002");
        bt3.printNextOddTernaerTest("10000000002");

        /* (1111)*122 --> (1010)*111 */
        bt3.printNextOddTernaerTest("122");
        bt3.printNextOddTernaerTest("1111122");
        bt3.printNextOddTernaerTest("11111111122");
        bt3.printNextOddTernaerTest("111111111111122");
        bt3.printNextOddTernaerTest("1111111111111111122");

        /* 1010(10101010)*111 --> 1200(12001200)*202 */
        bt3.printNextOddTernaerTest("1010111");
        bt3.printNextOddTernaerTest("101010101010111");
        bt3.printNextOddTernaerTest("10101010101010101010111");
        bt3.printNextOddTernaerTest("1010101010101010101010101010111");
        bt3.printNextOddTernaerTest("101010101010101010101010101010101010111");
        bt3.printNextOddTernaerTest("10101010101010101010101010101010101010101010111");

        /* 1110(00)* --> 20(11)*12 */
        bt3.printNextOddTernaerTest("1110");
        bt3.printNextOddTernaerTest("111000");
        bt3.printNextOddTernaerTest("11100000");
        bt3.printNextOddTernaerTest("1110000000");
        bt3.printNextOddTernaerTest("111000000000");

        /* 2(0)*12 --> 10(0)*22 */
        bt3.printNextOddTernaerTest("212");
        bt3.printNextOddTernaerTest("2012");
        bt3.printNextOddTernaerTest("20012");
        bt3.printNextOddTernaerTest("200012");
        bt3.printNextOddTernaerTest("2000012");


        bt3.printNextOddTernaerTest(new BigInteger("66433"));
        bt3.printNextOddTernaerTest(new BigInteger("5380843"));

        bt3.printNextOddTernaerTest("1002");
        bt3.printNextOddTernaerTest("10022");
        bt3.printNextOddTernaerTest("100222");
        bt3.printNextOddTernaerTest("1002222");
        bt3.printNextOddTernaerTest("10022222");



        RegularExpression re1 = new RegularExpression("1010(10101010)*111");
        Pattern p1 = Pattern.compile("1010111");
        Pattern p2 = Pattern.compile("10101010101010101010111");
        Pattern p3 = Pattern.compile("101010101010101010101010111");
        System.out.println(re1.match(p1));
        System.out.println(re1.match(p2));
        System.out.println(re1.match(p3));
        System.out.println(re1.getN(p1.toString()));
        System.out.println(re1.getN(p2.toString()));
        System.out.println(re1.getN(p3.toString()));

        RegularExpression re2 = new RegularExpression("1200(12001200)*202");
        Transition t1 = new Transition(re1, re2);
        String fromValue1 = "1010111";
        String fromValue2 = "10101010101010101010111";
        System.out.printf("%25s --> %25s %n", fromValue1, t1.doTransition(fromValue1));	 // 1200202
        System.out.printf("%25s --> %25s %n", fromValue2, t1.doTransition(fromValue2));	 // 12001200120012001200202

        RegularExpression re3 = new RegularExpression("10(2)*");
        RegularExpression re4 = new RegularExpression("12(2)*");
        Transition t2 = new Transition(re3, re4);
        String fromValue3 = "102";
        String fromValue4 = "102222";
        System.out.printf("%25s --> %25s %n", fromValue3, t2.doTransition(fromValue3));	// 122
        System.out.printf("%25s --> %25s %n", fromValue4, t2.doTransition(fromValue4));	// 122222

        RegularExpression re5 = new RegularExpression("21(2)*");
        RegularExpression re6 = new RegularExpression("102(2)*");
        Transition t3 = new Transition(re5, re6);
        String fromValue5 = "21";
        String fromValue6 = "21222";
        System.out.printf("%25s --> %25s %n", fromValue5, t3.doTransition(fromValue5));	// 102
        System.out.printf("%25s --> %25s %n", fromValue6, t3.doTransition(fromValue6));	// 102222
    }
}
