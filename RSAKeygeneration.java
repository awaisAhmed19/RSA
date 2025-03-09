import java.util.*;
import java.math.BigInteger;

public class RSAKeygeneration{
  Utils u=new Utils();
  BigInteger n,phi,e,d,p,q;
  RSAKeygeneration(int range){
    this.p=u.generate_random(range);
    this.q=u.generate_random(range);
    this.n=p.multiply(q);
    this.phi = u.Totient(p,q);
    this.e=BigInteger.ZERO;
  }
  RSAKeygeneration(){
    this.p=u.generate_random();
    this.q=u.generate_random();
    this.n=p.multiply(q);
    this.phi = u.Totient(p,q);
    this.e=BigInteger.ZERO;
    this.d=BigInteger.ZERO;
  }

  public BigInteger Pub_key(){
    for(this.e=BigInteger.TWO;(this.e.compareTo(this.phi))==-1;this.e=this.e.add(BigInteger.ONE)){
      BigInteger[] val=u.extendedGCD(this.e,this.phi);
      if(val[2].equals(BigInteger.ONE)){
        break;
     }
    }
    return this.e;
  }

  public BigInteger Priv_key(){
    return u.modInverse(this.e,this.phi);
  }
  public static void main(String args []){
    RSAKeygeneration R=new RSAKeygeneration();
    System.out.println(R.Pub_key());
    System.out.println(R.Priv_key());
    System.out.println(R.phi);
  }
}
