import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.In;
import java.util.*;

public class Solver {
  private MinPQ<Node> minPQ;
  private MinPQ<Node> minPQTwin;
  private Board board;
  private Board twin;
  private boolean notSolvable;
  private Node goalNode;

  public Solver(Board initial) {
    minPQ = new MinPQ<Node>();
    minPQTwin = new MinPQ<Node>();
    board = initial;
    twin = board.twin();
    minPQ.insert(new Node(board,0,null));
    minPQTwin.insert(new Node(twin,0,null));
    test();
  }

  private class Node implements Comparable<Node> {
    public final Board board;
    public final int moves;
    public final Node previous;

    public Node(Board b, int m, Node n) {
      board = b;
      moves = m;
      previous = n;
    }

    public int compareTo(Node that) {
      int aDist = board.manhattan() + moves;
      int bDist = that.board.manhattan() + that.moves;
      if (aDist > bDist)
        return 1;
      if (aDist == bDist)
        return 0;
      return -1;
    }
  }

  public int moves() {
    if (notSolvable)
      return -1;
    else
      return goalNode.moves;
  }

  public boolean isSolvable() {
    return notSolvable == false;
  }

  private void test() {
    while (!minPQ.min().board.isGoal()) {
      if (minPQTwin.min().board.isGoal()) {
        notSolvable = true;
        return;
      }
      Node minKey = minPQ.delMin();
      Node minKeyTwin = minPQTwin.delMin();
      Iterable<Board> neighbors = minKey.board.neighbors();
      Iterable<Board> neighborsTwin = minKeyTwin.board.neighbors();
      for(Board neighbor: neighbors) {
        if (minKey.previous == null || !(minKey.previous.board).equals(neighbor)) {
          minPQ.insert(new Node(neighbor, minKey.moves+1, minKey));
        }
      }

      for(Board neighbor: neighborsTwin) {
        if (minKeyTwin.previous == null || !(minKeyTwin.previous.board).equals(neighbor)) {
          minPQTwin.insert(new Node(neighbor, minKeyTwin.moves+1, minKeyTwin));
        }
      }
      if (minPQ.isEmpty() || minPQTwin.isEmpty()) {
        notSolvable = true;
        return;
      }
    }
    goalNode = minPQ.min();
    return;
  }

  public Iterable<Board> solution() {
    if (notSolvable)
      return null;
    List<Board> listMoves = new ArrayList<Board>();
    Node current = goalNode;
    while (!current.board.equals(board)) {
      listMoves.add(0,current.board);
      current = current.previous;
    }
    listMoves.add(0,current.board);
    return listMoves;
  }     // sequence of boards in a shortest solution; null if unsolvable

  public static void main(String[] args) {
    In in = new In(args[0]);
    int n = in.readInt();
    int[][] blocks = new int[n][n];
    for (int i = 0; i < n; i++)
      for (int j = 0; j < n; j++) {
        blocks[i][j] = in.readInt();
      }
    Board test = new Board(blocks);
    Solver tst = new Solver(test);
    System.out.println(test);
    System.out.println(tst.moves());
    //System.out.println(tst.goalNode.board);
    for (Board b: tst.solution())
      System.out.println(b);
  }
}
