//
//
// Name: Abdirahman Abdirahman
// Project: Polynomial Arithmetic
// Course: CSCI 1913: Introduction to Algorithms,Data Structures, and Program Development
// Date: November 9, 2017
//
//
// Polynomial arithmetic is a classic application of linked data structures. In this project,
// you will write a Java class that represents polynomials using singly linked linear lists.
// Its methods will add, subtract, and print these polynomials.



class Poly
{
  private class Term
  {
    private int coef;
    private int expo;
    private Term next;

    public Term(int coef, int expo, Term next)
    {
      this.coef = coef;
      this.expo = expo;
      this.next = next;
    }
  }

  private Term head;



  public Poly()
  {
    head = new Term(0,4,null);
  }


//The method term adds a new Term to this. The new Term’s coefficient is coef and its exponent is expo.

  public Poly term(int coef, int expo)
  {
     Term left = head;
     Term right = head.next;

    if(expo<0)
    {
      throw new IllegalArgumentException("No negative exponents");
    }
    else
    {
      while(right != null)
      {
        if(right.expo < expo)
        {
          left.next = new Term (coef, expo, right);
          left = left.next;
          return this;
        }
        if(right.expo > expo)
        {
          left = right;
          right = right.next;
          continue;
        }
        if(right.expo == expo)
        {
          throw new IllegalArgumentException();
        }
      }
      left.next = new Term(coef, expo, null);
      right = left.next;
      return this;
    }

  }



//The method add may add a Term to this, it may change a Term in this, or it may delete a Term from this.

  private void add(int coef, int expo)
  {
    Term left = head;
    Term right = head.next;

    if(expo<0)
    {
      throw new IllegalArgumentException("No negative exponents");
    }
    else
    {
          while(right != null)
          {
            if(right.expo == expo)
            {
              right.coef += coef;
              if (right.coef == 0)
              {
                left.next = right.next;
                right = right.next;
                return ;
              }
              return;
            }

            if(right.expo < expo)
            {
              left.next = new Term (coef, expo, right);
              left = left.next;
              return ;
            }
            if(right.expo > expo)
            {
              left = right;
              right = right.next;
              continue;
            }

      }
    }

  }



//The method plus returns a new polynomial that is the sum of this and that.

  public Poly plus(Poly that)
  {
    Poly p0 = new Poly();

      if(head.next == null)
        return that;

      Term right = head.next;
      Term thatRight = that.head.next;

      while(right != null)
      {
        p0.term(right.coef,right.expo);
        right = right.next;
      }

      while(thatRight != null)
      {
        p0.add(thatRight.coef,thatRight.expo);
        thatRight = thatRight.next;
      }
      return p0;
    }



//The method minus returns a new polynomial like this, but with Term’s whose coef slots have the opposite signs.

    public Poly minus()
    {
      Poly p0 = new Poly();
      Term right = head.next;

      while(right != null)
      {
        p0.term(-right.coef,right.expo);
        right = right.next;
      }

      return p0;
    }





  //  APPEND EXPONENT. Append Unicode subscript digits for EXPO to BUILDER.

  private void appendExponent(StringBuilder builder, int expo)
  {
    if (expo < 0)
    {
      throw new IllegalStateException("Bad exponent " + expo + ".");
    }
    else if (expo == 0)
    {
      builder.append('⁰');
    }
    else
    {
      appendingExponent(builder, expo);
    }
  }

//  APPENDING EXPONENT. Do all the work for APPEND EXPONENT when EXPO is not 0.

  private void appendingExponent(StringBuilder builder, int expo)
  {
    if (expo > 0)
    {
      appendingExponent(builder, expo / 10);
      builder.append("⁰¹²³⁴⁵⁶⁷⁸⁹".charAt(expo % 10));
    }
  }



//The method toString returns a String for printing this.


  public String toString()
  {
    Term first_term = head.next;

    if(first_term == null)
    {
      return "0";
    }
    else
    {
      Term right = first_term.next;
      StringBuilder builder1 = new  StringBuilder();

      builder1.append(first_term.coef);
      builder1.append("x");
      appendExponent(builder1,first_term.expo);

      while(right != null)
      {
        if(right.coef < 0)
        {
          builder1.append(" - ");
        }
        else
          builder1.append(" + ");

          builder1.append(Math.abs(right.coef));
          builder1.append("x");
          appendExponent(builder1,right.expo);

          right = right.next;
      }
          return builder1.toString();
    }
  }
}



class PollyEsther
{
  public static void main(String[] args)
  {
    Poly p0 = new Poly();
    Poly p1 = new Poly().term(1, 3).term(1, 1).term(1, 2);
    Poly p2 = new Poly().term(2, 1).term(3, 2);
    Poly p3 = p2.minus();

    System.out.println(p0);           //  0
    System.out.println(p1);           //  1x3 + 1x2 + 1x1
    System.out.println(p2);           //  3x2 + 2x1
    System.out.println(p3);           //  −3x2 − 2x1

    System.out.println(p1.plus(p2));  //  1x3 + 4x2 + 3x1
    System.out.println(p1.plus(p3));  //  1x3 − 2x2 − 1x1
  }
}
