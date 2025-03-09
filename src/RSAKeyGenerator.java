package src;
import Utils.Utils;
import java.math.BigInteger;
public class RSAKeyGenerator{
  Utils u=new Utils();
  BigInteger n,phi,e,d,p,q;
  RSAKeyGenerator(int range){
    this.p=u.generate_random(range);
    this.q=u.generate_random(range);
    this.n=p.multiply(q);
    this.phi = u.Totient(p,q);
    this.e=BigInteger.ZERO;
  }
  RSAKeyGenerator(){
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

}
