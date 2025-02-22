import java.util.*;
import java.math.*;

public class RSA_prime {
  public BigInteger GCD(BigInteger a, BigInteger b) {
    if (b.equals(BigInteger.ZERO)) {
        return a;
    }return GCD(b,a.mod(b).abs());
  }
  public BigInteger generate_random(){
    BigInteger n = BigInteger.probablePrime(1024, new Random());
    return n;
  }
  public BigInteger Totient(BigInteger a,BigInteger b){
    return a.subtract(BigInteger.ONE).multiply(b.subtract(BigInteger.ONE));
  }
  public static void main(String args[]) {
    Scanner in = new Scanner(System.in);
    RSA_prime R = new RSA_prime();
    BigInteger a = R.generate_random();
    BigInteger b = R.generate_random();
    System.out.println("Totient "+R.Totient(a,b));
  }
}

