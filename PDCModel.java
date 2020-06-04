// Carlos Santiago Bañón

// Preferential Deletion Model with Changes in Existing Connections (PDCModel)

// This program was developed for the 'Accounting for Changes in Existing Connections in the 
// Preferential Deletion Model for Web-Like Networks' research project made as part of the
// COT 5405: Design and Analysis of Algorithms graduate course at the University of Central Florida.

// PDCModel.java
// Implementation of a new dynamic random graph model with preferential deletion that accounts for
// changes in existing nodes.

import java.lang.Math;
import java.util.*;

public class PDCModel
{
  private double probability;
  private int time;
  private Graph G;

  private static final boolean DEBUG = false;

  public PDCModel(int time, double probability)
  {
    this.time = time;
    this.probability = probability;
    G = new Graph();
  }

  public PDCModel(Graph G, double probability)
  {
    this.time = 0;
    this.probability = probability;
    this.G = G;
  }

  // Changes the existing connections in the graph at random.
  public int changeExistingConnections(int t)
  {
    int numMeetings = 0;

    if (DEBUG)
      System.out.println("Checking for new connections!");

    HashMap<Node, Double> probabilities = new HashMap<>();
    HashMap<Node, Node> newConnections = new HashMap<>();
    double sum = 0.0;

    for (Node nodeA : G.getAdjacencyList().keySet())
    {

      boolean found = false;
      int random = (int) (Math.random() * t);
      double p = Math.random();

      Node newNode = G.getNode(random);

      while (newNode == null)
      {
        random = (int) (Math.random() * t);;
        newNode = G.getNode(random);
      }

      if (DEBUG)
        System.out.println("Meeting Prob: " + getMeetingProbability(nodeA, newNode));

      if (p < getMeetingProbability(nodeA, newNode))
      {
        if (DEBUG)
          System.out.println("ADDED A NEW CONNECTION");

        G.addEdge(nodeA, newNode);
        found = true;
        numMeetings++;
      }
    }

    return numMeetings;
  }

  // Creates a new node and attaches it to an existing node based on the linear preferential
  // attachment rule.
  public void createNode(int t)
  {
    if (DEBUG)
    {
      System.out.println("Creating Node " + t);
    }

    if (G.getNumNodes() == 0)
    {
      G.initializeGraph();
      return;
    }

    HashMap<Node, Double> probabilities = new HashMap<>();
    double sum = 0.0;

    for (Node node : G.getAdjacencyList().keySet())
    {
      sum += linearPreferentialAttachment(node);
      probabilities.put(node, sum);
    }

    if (DEBUG)
    {
      System.out.println("Size of Probabilities: " + probabilities.size());

      for (Node node : probabilities.keySet())
        System.out.println("Probability of " + node.getCode() + ": " + probabilities.get(node));
    }

    Node newNeighbor = null;
    boolean found = false;
    double p = Math.random() * sum;

    for (Node node : probabilities.keySet())
    {
      if (probabilities.get(node) >= p)
      {
        newNeighbor = node;
        found = true;
        break;
      }
    }

    if (DEBUG)
    {
      System.out.println("New Neighbor: " + newNeighbor.getCode());
      System.out.println();
    }

    G.createNode(newNeighbor, t);
  }

  // Deletes a random node chosen by preferential deletion.
  public void deleteRandomNode()
  {
    if (DEBUG)
    {
      System.out.println("Deleting Random Node");
    }

    HashMap<Node, Double> probabilities = new HashMap<>();
    double sum = 0.0;

    for (Node node : G.getAdjacencyList().keySet())
    {
      double p = preferentialDeletion(node);
      probabilities.put(node, (p + sum));
      sum += p;
    }

    if (DEBUG)
    {
      System.out.println("Size of Probabilities: " + probabilities.size());

      for (Node node : probabilities.keySet())
        System.out.println("Probability of " + node.getCode() + ": " + probabilities.get(node));
    }

    int code = 0;
    Node nodeToDelete = null;
    double p = Math.random() * sum;

    for (Node node : probabilities.keySet())
    {
      if (probabilities.get(node) >= p)
      {
        nodeToDelete = node;
        break;
      }
    }

    if (DEBUG)
    {
      System.out.println("Num Probabilities = " + probabilities.size());

      if (nodeToDelete == null)
        System.out.println("ERROR! Null node.");
    }

    if (DEBUG)
    {
      System.out.println("Node to Delete: " + nodeToDelete.getCode());
      System.out.println();
    }

    G.deleteNode(nodeToDelete.getCode());
  }

  // Models the fermi function.
  public double fermi(int degree)
  {
    double beta = 5.0;
    double sharpness = 5.0;

    double A = 1;
    double B = Math.exp(beta * (degree - sharpness)) + 1;

    if (B == 0)
      return 0.0;

    double result = A / B;

    return result;
  }

  // Returns the graph used in the model.
  public Graph getGraph()
  {
    return G;
  }

  // Returns the probability of nodes A and B meeting.
  public double getMeetingProbability(Node A, Node B)
  {
    int numMutualEdges = G.getNumMutualEdges(A, B);
    int degreeA = G.getDegree(A);
    int degreeB = G.getDegree(B);

    return (fermi(degreeA) * fermi(degreeB) * likelihoodIncrease(numMutualEdges));
  }

  // Returns the probability parameter for the model.
  public double getProbability()
  {
    return probability;
  }

  // Returns the time parameter for the model.
  public int getTime()
  {
    return time;
  }

  // Returns the increase in the likelyhood of two nodes meeting.
  public double likelihoodIncrease(int numMutualEdges)
  {
    double prob = 0.0001;
    double alpha = 0.5;

    double result = (1 - (1 - prob) * Math.exp(-(alpha * numMutualEdges)));

    return result;
  }

  // Returns the probability that a new node is connected to node u, according
  // to the Linear Preferencial Attachment rule.
  public double linearPreferentialAttachment(Node u)
  {
    int degree = G.getDegree(u);
    int numEdges = G.getNumEdges();

    double A = (double) degree;
    double B = (double) (2 * numEdges);

    if (B == 0)
      return 0.0;

    return (A / B);
  }

  // Returns the probability that node u will be deleted from the graph.
  public double preferentialDeletion(Node u)
  {
    int numNodes = G.getNumNodes();
    int numEdges = G.getNumEdges();
    int degree = G.getDegree(u);

    double A = (double) (numNodes - degree);
    double B = (double) ((numNodes * numNodes) - (2 * numEdges));

    if (B == 0)
      return 0.0;

    return Math.abs(A / B);
  }

  // Prints the linear preferential attachment probability values for each node
  // in the graph.
  public void printLPAValues()
  {
    System.out.println("LPA Values in G:");
    System.out.println("================");

    for (Node node : G.getAdjacencyList().keySet())
    {
      System.out.println("LPA(" + node.getCode() + "): " + linearPreferentialAttachment(node));
    }

    System.out.println();
  }

  // Prints the preferential deletion probability values for each node in the graph.
  public void printPDValues()
  {
    System.out.println("PD Values in G:");
    System.out.println("===============");

    for (Node node : G.getAdjacencyList().keySet())
    {
      System.out.println("PD(" + node.getCode() + "): " + preferentialDeletion(node));
    }

    System.out.println();
  }

  // Runs the model.
  public void runModel()
  {
    G.initializeGraph();

    double p = 0;
    int numMeetings = 0;

    System.out.println("1 " + G.getNumNodes() + " " + G.getNumEdges() + " " + G.getAverageDegree());

    for (int t = 2; t <= time; t++)
    {
      if (DEBUG)
      {
        System.out.println(t + "/" + time);
      }

      p = Math.random();

      if (p < probability)
      {
        createNode(t);
      }
      else
      {
        deleteRandomNode();
      }

      numMeetings += changeExistingConnections(t);

      // If the graph becomes empty, re-initialize the graph.
      if (G.getNumNodes() == 0)
      {
        G.initializeGraph();
      }

      if (t % 1000 == 0)
      {
        System.out.println(t + " " + G.getNumNodes() + " " + G.getNumEdges() + " " + G.getAverageDegree());
      }

      if (DEBUG)
      {
        G.printAdjacencyList();
        G.printStatistics(t);
      }
    }

    if (DEBUG)
    {
      G.printAdjacencyList();
      G.printDegreeDistribution();
    }

    G.printStatistics(time);
    G.printDegreeDistribution();

    System.out.println("Total Number of Meetings: " + numMeetings);
  }
}
