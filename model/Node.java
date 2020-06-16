// Carlos Santiago Bañón

// Preferential Deletion Model with Changes in Existing Connections (PDCModel)

// This program was developed for the 'Accounting for Changes in Existing Connections in the 
// Preferential Deletion Model for Web-Like Networks' research project made as part of the
// COT 5405: Design and Analysis of Algorithms graduate course at the University of Central Florida.

// Node.java
// Definition of the Node object.

public class Node
{
  private int code;

  public Node(int code)
  {
    this.code = code;
  }

  // Returns the node's code.
  public int getCode()
  {
    return code;
  }
}
