package src;

import Utils.Utils;
import java.math.*;
import java.util.*;

public class RSAEncryptor{
  Utils u=new Utils();

  RSAKeyGenerator Re=new RSAKeyGenerator(128);
  
  public ArrayList<BigInteger > Encrypt(ArrayList<Integer> message, BigInteger e,BigInteger n){
    ArrayList<BigInteger > cipher = new ArrayList<>();
    for( Integer  m: message){
      cipher.add((u.power(BigInteger.valueOf(m),e,n)));
    }
    return cipher;
  }
}
