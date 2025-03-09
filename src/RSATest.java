package src;

import Utils.Utils;
import java.math.*;
import java.util.*;

public class RSATest{
  public static void main(String args[]){
    Random rand=new Random();
    Utils u=new Utils();
    int CongruencyValid=0;
    int validd=0;
    ArrayList<RSAKeyGenerator> RList=new ArrayList<>();
    for(int i=0;i<10;i++){
       RList.add(new RSAKeyGenerator(1000+rand.nextInt(24)));
    }
    for(RSAKeyGenerator R:RList){
      BigInteger[] val=u.extendedGCD(R.Pub_key(),R.phi);
      if((val[2].equals(BigInteger.ONE))){
        CongruencyValid++;  
      }
      if(((R.e.multiply(R.Priv_key())).mod(R.phi)).equals(BigInteger.ONE)){
        validd++;
      }
    }
    System.out.println("valid d pass ratio: "+validd/10*100);
    System.out.println("congruency pass ratio: "+CongruencyValid/10*100);
  }
}
