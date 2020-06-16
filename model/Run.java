// Carlos Santiago Bañón

// Preferential Deletion Model with Changes in Existing Connections (PDCModel)

// This program was developed for the 'Accounting for Changes in Existing Connections in the 
// Preferential Deletion Model for Web-Like Networks' research project made as part of the
// COT 5405: Design and Analysis of Algorithms graduate course at the University of Central Florida.

// Run.java
// File used to run the model.

public class Run
{
  private static final int TRIALS = 20;
  private static final int TIME = 50000;
  private static final double P1 = 0.6;
  private static final double P2 = 0.75;
  private static final double P3 = 0.9;

  public static void main(String [] args)
  {
    PDCModel M1;
    PDCModel M2;
    PDCModel M3;

    for (int i = 1; i <= TRIALS; i++)
    {
      System.out.println("=============== TRIAL " + i + "/" + TRIALS + " ===============");

      System.out.println("RUNNING MODEL AT P = " + P1);
      M1 = new PDCModel(TIME, P1);
      M1.runModel();
      System.out.println();

      System.out.println("RUNNING MODEL AT P = " + P2);
      M2 = new PDCModel(TIME, P2);
      M2.runModel();
      System.out.println();

      System.out.println("RUNNING MODEL AT P = " + P3);
      M3 = new PDCModel(TIME, P3);
      M3.runModel();
      System.out.println();
    }
  }
}
