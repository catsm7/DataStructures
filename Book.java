/**
* Book.java
*  @author Catalina Sanchez-Maes
* Janurary 29th, 2019
* CS 272
*/

import java.lang.*;
public class Book{//class open
   private String bookTitle;
   private int numAuthors;
   String[] bookAuthors;
   private String ISBN;
      
   public Book(){//open constructor
      bookTitle = null;
      ISBN = null;
      numAuthors = 0;
      bookAuthors = new String[3];
   }//end constructor
      
   public Book (String _title){//open constructor
      bookTitle = _title;
      ISBN = null;
      numAuthors = 0;
      bookAuthors = new String[3];
   }//end constructor
      
   public Book(Object obj){//open Book object
      if (obj instanceof Book){
          Book candidate = (Book) obj;
          bookTitle = candidate.getTitle();
          numAuthors = candidate.getAuthorNumber();
          bookAuthors = new String[3];
          for (int i = 0; i < numAuthors; i++){
            bookAuthors[i] = candidate.bookAuthors[i];
          }//end for
          ISBN = candidate.getISBN();
      }
   }//end Book object
         
   public String getTitle(){//open getTitle
      return bookTitle;
   }//end getTitle
      
   public int getAuthorNumber(){//open getAuthorNumber
      return numAuthors;  
   }//end getAuthorNumber
      
   public String getISBN(){//open getISBN
      return ISBN;
   }//end getISBN
      
   public void setTitle(String _title){//open setTitle
      bookTitle = _title;
   }//end setTitle
      
   public void setISBN(String _isbn){//open ISBN
      ISBN = _isbn;
   }//end setISBN
      
   public boolean addAuthor(String _author){//open addAuthor
      if(numAuthors < 3){
         for(int i = 0; i < numAuthors; i++){
            if(_author == bookAuthors[i]){
               return false;
            }//end if
         }//end for
         bookAuthors[numAuthors] = _author;
         numAuthors = numAuthors + 1;
         return true;
      }else{
         return false;
      }//end else
   }//end addAuthor      

   public boolean equals(Object obj){//open equals
      if (obj instanceof Book){
         Book candidate = (Book) obj;
         if (candidate.getISBN() == ISBN){
            return true;
         }else{
            return false;
         }//end inner else
      }//end outer if
      return false;
   }//end equals
   
   public static String[] getAllAuthors(Book b1, Book b2){//getAllAuthors open
      int offset = -1;
      if(b1.numAuthors >= 1 && b2.numAuthors >= 1){
         String[] bookAuthor = new String[6];
         int num = 0;
         for (int i = 0; i < b1.numAuthors; i++){
            bookAuthor[i] = b1.bookAuthors[i];
            num = i;
         }//end for
         for (int i = 0; i < b2.numAuthors; i++){
            boolean duplicate = false;
            for(int j = 0; j < 6; j++){
               if(bookAuthor[j] == b2.bookAuthors[i]){
                  duplicate = true;
                  offset++;
               }//end if  
            }//end for
            if( !duplicate ){
               bookAuthor[i + num - offset] = b2.bookAuthors[i];
            }//end if
         }//end for
         return bookAuthor;
      }else{
         return new String[6];
      }//end else
   }//end getAllAuthors
   
   public String toString(){//toString open
      String bookRep = "Title: " + getTitle() + ", ISBN: " + getISBN() + ", # of Authors: " + Integer.toString(getAuthorNumber());
      bookRep = bookRep.concat(", Book Authors: ");
      for( int i = 0; i < getAuthorNumber(); i++){
         bookRep = bookRep.concat(bookAuthors[i]);
         bookRep = bookRep.concat(", ");
      }//end for loop
      return bookRep;   
   }//end toString
     
   public static void main(String[] args) {//main open
      //Test toString method & default constructor
      Book b1 = new Book();
      System.out.println(b1.toString());
      
      //Test toString method & secondary constructor
      Book b2 = new Book("Hello World");
      System.out.println(b2.toString());
      
      //Test the accessors/mutators
      b1.setTitle("Winnie the Pooh");
      b1.setISBN("1234567890");
      b1.addAuthor("Christopher Robin");
      System.out.print("Book 1: " + b1.getTitle());
      System.out.print(", " + b1.getAuthorNumber());
      System.out.println(", " + b1.getISBN());
      
      String [] testAuthors = getAllAuthors(b1, b2);
      String allTestAuthors = "";
      for ( int i = 0; i < 6; i++){
         allTestAuthors = allTestAuthors + testAuthors[i] + ", ";
      }//end for loop
      //test output of All Authors; output: all null
      System.out.println(allTestAuthors);
      
      
      b2.addAuthor("Christopher Robin");
      testAuthors = getAllAuthors(b1, b2);
      allTestAuthors = "";
      for ( int i = 0; i < 6; i++){
         allTestAuthors = allTestAuthors + testAuthors[i] + ", ";
      }//end for loop
      //test output of All Authors; output: with the same new author added
      System.out.println(allTestAuthors);
      
      
      b2.addAuthor("Jason Mario");
      testAuthors = getAllAuthors(b1, b2);
      allTestAuthors = "";
      for ( int i = 0; i < 6; i++){
         allTestAuthors = allTestAuthors + testAuthors[i] + ", ";
      }//end for loop
      //test output of All Authors; output: with the new diff author added
      System.out.println(allTestAuthors);
      
      //Test the equals methods
      System.out.println(b1.equals(b1)); //true because same
      System.out.println(b1.equals(b2)); //false because isbn is different
      
      //Test the toString with diff authors added
      System.out.println(b1.toString());
      System.out.println(b2.toString());
      
   }//end main
   //*************************************************************
}//end class