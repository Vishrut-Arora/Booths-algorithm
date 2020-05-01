import java.util.Scanner;
import java.lang.*;
import java.lang.Math;
import java.lang.String;
import java.util.Arrays;
import java.text.AttributedString;
class boothsimplement_algo
{
    static String findTwoscomplement(StringBuffer s) //find twos complement
    { 
        int n = s.length(); 
        int i=0; 
        int j=0;
        for (i = n-1 ; i >= 0 ; i--) 
            if ((s.charAt(i)+"").equals("1")) 
                break; 

        if (i == -1) 
            return "1" + s; 

        for (j = i-1 ; j >= 0; j--) 
        { 

            if ((s.charAt(j)+"").equals("1")) 
                s.replace(j, j+1, "0"); 
            else
                s.replace(j, j+1, "1"); 
        } 
       

        return s.toString(); 
    } 
      
static String addbinary(String a, String b) { //add two binary nos.
    StringBuilder s = new StringBuilder();
 
    int i=a.length()-1;
    int j=b.length()-1;
 
    int carry = 0;
    int sumbinary=0;
    while(i>=0 || j>=0){
        sumbinary=0;
 
        if(i>=0 && (a.charAt(i)+"").equals("1")){
            sumbinary++;    
        }
 
        if(j>=0 && (b.charAt(j)+"").equals("1")){
            sumbinary++;
        }
 
        sumbinary += carry;

        carry=(sumbinary<2)?0:1;
 
        s.insert(0,(char)((sumbinary%2) + '0'));
 
        i--;
        j--;
    }
 
    if(carry==1)
        s.insert(0, '1');
 
    return s.toString();
}
  public static void main(String[] args)
  {
    Scanner sc=new Scanner(System.in);
    System.out.print("No. of bits: ");
    int nof=sc.nextInt();
    System.out.print("First number: ");
    int n1=sc.nextInt();
    System.out.print("Second number: ");
    int n2=sc.nextInt();
    String s1="";
    String s2="";
    StringBuffer ac=new StringBuffer("");

      String temp=String.format("%"+nof+"s", Integer.toBinaryString(0)).replace(' ', '0');

      ac.append(String.format(temp));
    
    
    
    if(n1<0)                                                                                  //take two's complemnt if no. is neagtive else convert it into 
                                                                                              //binary string directly
    {
      String c=String.format("%"+nof+"s", Integer.toBinaryString(-1*n1)).replace(' ', '0');
      StringBuffer str = new StringBuffer(c); 
      s1=findTwoscomplement(str);
      //System.out.println(s1);
    }
    else{
      s1=String.format("%"+nof+"s", Integer.toBinaryString(n1)).replace(' ', '0');
    }
    if(n2<0)
    {
      String c=String.format("%"+nof+"s", Integer.toBinaryString(-1*n2)).replace(' ', '0');
      StringBuffer str = new StringBuffer(c);
      s2=findTwoscomplement(str);
      
    }
    else
    {
      s2=String.format("%"+nof+"s", Integer.toBinaryString(n2)).replace(' ', '0');      
    }

    int check=nof;                                
    int op[]=new int[2];
    op[1]=0;
    StringBuffer multiplier=new StringBuffer(s2);
    StringBuffer str = new StringBuffer(s1);
    String multiplicand=findTwoscomplement(str);    
    while(check!=0)
    {

      op[0]=Integer.parseInt(multiplier.toString().charAt(nof-1)+"");
      String operate=op[0]+""+op[1];
      if(operate.equals("10"))                                    //this case we update ac by adding two's complement of first no.
                                                                  //then arithmatic right shift
      {
       // System.out.println(ac);
        ac.replace(0,nof,addbinary(ac.toString(),multiplicand));
        if(ac.toString().length()>nof)
        {
          ac.deleteCharAt(0);
        }
       System.out.println(ac+" "+multiplier);
      
        String acnew=ac.toString();
        String acac1=(acnew).charAt(0)+"";
        String acac2=(acnew).substring(0,nof-1);
        String acac3=(acnew).charAt(nof-1)+"";
        ac.replace(0,nof,acac1+acac2);
        String mulnew=multiplier.toString();
        String mul1=(mulnew).substring(0,nof-1);
        String mul2=(mulnew).charAt(nof-1)+"";
        multiplier.replace(0,nof,acac3+mul1);
        op[1]=Integer.parseInt(mul2);
        --check;
        System.out.println(ac+" "+multiplier);
        
      }
      else if(operate.equals("01"))                                     //update ac with addition of first no.
                                                                        //then arithmatic right shift
      {
        //System.out.println(addbinary(ac.toString(),s1));
        ac.replace(0,nof,addbinary(ac.toString(),s1));
        if(ac.toString().length()>nof)
        {
          ac.deleteCharAt(0);
        }        
        System.out.println(ac+" "+multiplier);
        //System.out.println(ac.toString());
        String acnew=ac.toString();
        String acac1=(acnew).charAt(0)+"";
        String acac2=(acnew).substring(0,nof-1);
        String acac3=(acnew).charAt(nof-1)+"";
        ac.replace(0,nof,acac1+acac2);
        String mulnew=multiplier.toString();
        String mul1=(mulnew).substring(0,nof-1);
        String mul2=(mulnew).charAt(nof-1)+"";
        multiplier.replace(0,nof,acac3+mul1);
        op[1]=Integer.parseInt(mul2);
        --check;
        System.out.println(ac+" "+multiplier);

      }
      else{                                                                     //arithmatic right shift in both 00/11 case
         System.out.println(ac+" "+multiplier);
        String acnew=ac.toString();
        String acac1=(acnew).charAt(0)+"";
        String acac2=(acnew).substring(0,nof-1);
        String acac3=(acnew).charAt(nof-1)+"";
        ac.replace(0,nof,acac1+acac2);
        
        String mulnew=multiplier.toString();
        String mul1=(mulnew).substring(0,nof-1);
        String mul2=(mulnew).charAt(nof-1)+"";
        multiplier.replace(0,nof,acac3+mul1);
        op[1]=Integer.parseInt(mul2);
        --check;
        //System.out.println(ac+" "+multiplier);
      }

    } 

    StringBuffer sr=new StringBuffer(ac.toString()+multiplier.toString());
    String fin=findTwoscomplement(sr);
    //System.out.println(fin);
    System.out.println("BINARY= "+ac.toString()+" "+multiplier.toString());    //binary representation
    if(ac.toString().charAt(0)=='1')                                           //in case of decimal we check if the 1st char is 0 or 1
    {
      System.out.println("DECIMAL= "+-1*Integer.parseInt(fin,2));
    }
    else{
      System.out.println("DECIMAL= "+Integer.parseInt(ac.toString()+multiplier.toString(),2));      
    }




  }}