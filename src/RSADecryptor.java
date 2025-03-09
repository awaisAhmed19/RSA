package src;

import java.util.*;
import java.math.*;
import Utils.Utils;

public class RSADecryptor{
  Utils u=new Utils();

  RSAKeyGenerator Re=new RSAKeyGenerator(128);
  
  public ArrayList<BigInteger> Decrypt(ArrayList<BigInteger> cmessage, BigInteger d, BigInteger n){
    ArrayList<BigInteger> decipher = new ArrayList<>();
    for( BigInteger m: cmessage){
      System.out.println(m);
      decipher.add((u.power(m,d,n)));
    }
    return decipher;
  }
}
