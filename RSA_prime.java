import java.util.*;
import java.math.*;

public class RSA_prime {
  public static void main(String args[]) {
    Scanner in = new Scanner(System.in);
    RSA_prime R = new RSA_prime();
    BigInteger a= R.generate_random();
    BigInteger b= R.generate_random();
    System.out.println("Totient "+R.Totient(a,b));
  }
}

