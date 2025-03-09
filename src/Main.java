package src;

import Utils.BaseConvertor;
import java.math.*;
import java.util.*;

public class Main{
 
  public static void main(String args[]) {
    //Utils u= new Utils();
    BaseConvertor a=new BaseConvertor();
    Random rand=new Random();
    RSAKeyGenerator Rg=new RSAKeyGenerator();
    RSAEncryptor Re=new RSAEncryptor();
    RSADecryptor Rd=new RSADecryptor();

    BigInteger n=Rg.n;
    BigInteger e=Rg.Pub_key();
    BigInteger d=Rg.Priv_key();
    ArrayList<Integer> st=a.StringToAscii("Hello world my name is awais.");
    ArrayList<BigInteger> cipher=Re.Encrypt(st,e,n);
    ArrayList<BigInteger> decipher=Rd.Decrypt(cipher,d,n);

    System.out.println("Hello world:");
    for (Integer s : st) {
      System.out.println(s);
    }

    System.out.println("Cipher: Hello world:");
    for (BigInteger c : cipher) {
      System.out.println(a.encode(c));
    }
    System.out.println("Decipher: Hello world:");
    System.out.println(a.AsciiToString(decipher));
    
  }
}
