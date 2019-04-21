package lexical_analyzer;
import java.io.*;
public class Lexical_analyzer 
{
 
    static int scan,value=0,no_line=0,i=0;
    static  char []Token = new char [10];
    static int [] numbers= new int[100];
    static String [] Keyword = {"main"," if"," for"};
    static String [] Datatype={"int"," float"," char"};
    public static void Scaning ()throws IOException
    {
        PushbackInputStream input = new PushbackInputStream(
               new FileInputStream("input.txt"));
       FileWriter  W_Tokens = new FileWriter("Tokens.txt");
       FileWriter  W_SpecialSymbols = new FileWriter("SpecialSymbols.txt");
       while((scan=input.read())!=-1)
        {
         if(Character.isDigit(scan))
         {
            value= scan-'0';
            scan=input.read();
            while(Character.isDigit(scan))
             {
               value= value*10+(scan-'0');
               scan=input.read();
             }
            numbers[i++]=value;
             input.unread(scan);
            }
         else if(Character.isAlphabetic(scan))
         {
             W_Tokens.write(scan);
             scan=input.read();
            while(Character.isDigit(scan)||Character.isAlphabetic(scan))
            {
                 W_Tokens.write(scan);
                 scan=input.read();
            }
            W_Tokens.write(" ");
            input.unread(scan);
           }
         else if(scan==' '||scan=='\t')
             System.out.print(" ");
        else if(scan=='\n')
            no_line++;
         else
           W_SpecialSymbols.write(scan);
    }
   input.close();
   W_Tokens.close();
   W_SpecialSymbols.close();
    }
    public static void Search()
    {      
     String lexeme= new String (Token).trim();
        
        for( String K : Keyword) 
        {
            if (K.contentEquals(lexeme)) 
            {
                System.out.println(lexeme+ " is a keyword");
                return;   
            }
           
        }
        for (String D: Datatype)
        {
          if (D.contentEquals(lexeme))
            {
                System.out.println(lexeme + " is a primitive data types");
                return;
            }
        }
         System.out.println(lexeme + " is an identifier");
      
    }
    public static void Token_output()throws IOException
    {
        FileReader R_Tokens = new FileReader("Tokens.txt");
        int j=0;
        System.out.println("The keywords and identifiers are: ");
          while((scan=R_Tokens.read())!=-1)
          {
              if(scan!=' ')
                Token[j++]=(char)scan;
            else
            {
               Token[j]='\0'; 
                Search( );
                j=0;
             }
          }
       R_Tokens.close();
    }
    public static void SpecialSymbols_output()throws IOException
    {
        System.out.println("\nSpecial characters are:- ");
        FileReader R_SpecialSymbols = new FileReader("SpecialSymbols.txt");
        while((scan=R_SpecialSymbols.read())!=-1)
           {    
            System.out.println((char)scan);
           }
            
            R_SpecialSymbols.close();
    }
    public static void main(String[] args) throws IOException
    {
  
    Scaning();
    System.out.print("\nThe numbers in the program are:");
    for(int j=0;j<i;j++)
         System.out.print(numbers[j]+" ");
    System.out.println();
    Token_output();
    SpecialSymbols_output();
    System.out.println("Total no.of lines are:"+no_line);
    }
}
