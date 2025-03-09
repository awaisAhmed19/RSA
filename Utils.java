import java.lang.Math.*;
import java.utils.*;
public class Utils{

  public BigInteger GCD(BigInteger a, BigInteger b) {
    if (b.equals(BigInteger.ZERO)) {
        return a;
    }return GCD(b,a.mod(b).abs());
  }
  public BigInteger generate_random(){
    BigInteger n = BigInteger.probablePrime(1024, new Random());
    return n;
  }
  public BigInteger power(BigInteger base,BigInteger expo, BigInteger m){
    BigInteger res=BigInteger.ONE;
    base=base.mod(m);
    while (expo.compareTo(BigInteger.ZERO)==1){
      if(expo.testBit(0)){
        res=res.multiply(base).mod(m);
      }
      base=base.multiply(base).mod(m);
      expo=expo.shiftRight(1);
    }
    return res;
  }
  public BigInteger modInverse(BigInteger e, BigInteger phi){
    BigInteger[] result=extendedGCD(e,phi);
    BigInteger x=result[0];

    if(x.compareTo(BigInteger.ZERO)<0){
      x=x.add(phi);
    }
    return x
  }

  public BigInteger[] extendedGCD(BigInteger a,BigInteger b){
    if(b.equals(BigInteger.ZERO)){
      return new BigInteger[]{BigInteger.ONE,BigInteger.ZERO,a};
    }
    BigInteger[] value= extendedGCD(b,a.mod(b));
    BigInteger x=value[1];
    BigInteger y=value[0].subtract(a.divide(b).multiply(value[1]));
    return new BigInteger[]{x,y,values[2]};
  }

}

