package Utils;

import java.util.*;
import java.math.*;
public class BaseConvertor {

  private static final String CHARSET="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";

  public String encode(BigInteger ciph){
    StringBuilder res=new StringBuilder();
    BigInteger base=BigInteger.valueOf(CHARSET.length());

    while(ciph.compareTo(BigInteger.ZERO)>0){
      int index=ciph.mod(base).intValueExact();
      res.insert(0,CHARSET.charAt(index));
      ciph=ciph.divide(base);
    }
    return res.toString();
  }

  public String AsciiToString(ArrayList<BigInteger> de) {
    StringBuilder res=new StringBuilder();
    for (BigInteger c : de) { 
      char val=(char) c.intValueExact();
      res.append(val);
    }
    return res.toString();
  }


  public ArrayList<Integer> StringToAscii(String strg) {
    ArrayList<Integer> ls=new ArrayList<>();
    for (char c : strg.toCharArray()) { 
      int val=(int)c;
      ls.add(val);
    }
    return ls;
  }
  
}
