import java.util.*;
import edu.princeton.cs.algs4.In;

public class Board {
  private int[][] blocks;
  private int emptyI, emptyJ;

  public Board(int[][] blocks) {
    int dim = blocks[0].length;
    //System.out.println("yay "+dim);
    this.blocks = new int[dim][dim];
    for (int i = 0; i < dim; i++)
      for (int j = 0; j < dim; j++) {
        if (blocks[i][j] == 0) {
          emptyI = i;
          emptyJ = j;
        }
        //System.out.println("for "+i+" "+j+" "+blocks[i][j]);
        this.blocks[i][j] = blocks[i][j];
      }
  }          // construct a board from an n-by-n array of blocks
                                           // (where blocks[i][j] = block in row i, column j)
  public int dimension() {
    return blocks[0].length;
  }                // board dimension n

  public int hamming() {
    int dim = dimension();
    int ham = 0;
    for (int i = 0; i < dim; i++)
      for (int j = 0; j < dim; j++)
        if (blocks[i][j] != dim*i + j + 1 && blocks[i][j] != 0) {
          //System.out.println(blocks[i][j]+" "+sum);
          ham = ham + 1;
        }
    return ham;
  }                  // number of blocks out of place

  private int abs(int a) {
    if (a >= 0)
      return a;
    else
      return -a;
  }

  private int distance(int number, int pos) {
    int dim = dimension();
    int vertical = abs((number-1)/dim - pos/dim);
    int horizontal = abs((number-1)%dim - pos%dim);
    //System.out.println("dist for "+number+" "+pos+" "+vertical+" "+horizontal);
    return vertical + horizontal;
  }

  public int manhattan() {
    int dim = dimension();
    int man = 0;
    for (int i = 0; i < dim; i++)
      for (int j = 0; j < dim; j++)
        if (blocks[i][j] != dim*i + j + 1 && blocks[i][j] != 0)
          man = man + distance(blocks[i][j],dim*i+j);
    return man;
  }                // sum of Manhattan distances between blocks and goal

  public boolean isGoal() {
    return manhattan() == 0;
  }               // is this board the goal board?

  private boolean inRange(int a) {
    if (a < 0 || a >= dimension())
      return false;
    else
      return true;
  }

  public Board twin() {
    if (blocks[0][0] != 0 && blocks[0][1] != 0)
      return swap(0,0,0,1);
    else
      return swap(1,0,1,1);
  }                   // a board that is obtained by exchanging any pair of blocks

  public boolean equals(Object y) {
    if (y == this)
      return true;
    if (y == null || y.getClass() != this.getClass())
      return false;
    Board test = (Board) y;
    if (Arrays.deepEquals(blocks, test.blocks))
      return true;
    else
      return false;
  }       // does this board equal y?

  private Board swap(int destI, int destJ, int sourceI, int sourceJ) {
    int dim = dimension();
    int[][] board = new int[dim][dim];
    for (int i = 0; i < dim; i++)
      for (int j = 0; j < dim; j++)
        board[i][j] = blocks[i][j];
    board[destI][destJ] = blocks[sourceI][sourceJ];
    board[sourceI][sourceJ] = this.blocks[destI][destJ];
    return new Board(board);
  }

  public Iterable<Board> neighbors() {
    List<Board> list = new ArrayList<Board>();
    if (inRange(emptyI - 1)) {
      Board up = swap(emptyI-1,emptyJ,emptyI,emptyJ);
      list.add(up);
    }
    if (inRange(emptyJ - 1)) {
      Board left = swap(emptyI,emptyJ-1,emptyI,emptyJ);
      list.add(left);
    }
    if (inRange(emptyI + 1)) {
      Board down = swap(emptyI+1,emptyJ,emptyI,emptyJ);
      list.add(down);
    }
    if (inRange(emptyJ + 1)) {
      Board right = swap(emptyI,emptyJ+1,emptyI,emptyJ);
      list.add(right);
    }
    return list;
  }    // all neighboring boards

  public String toString() {
    StringBuilder s = new StringBuilder();
    int dim = dimension();
    s.append(dimension() + "\n");
    for (int i = 0; i < dim; i++) {
        for (int j = 0; j < dim; j++) {
            s.append(String.format("%2d ", blocks[i][j]));
        }
        s.append("\n");
    }
    return s.toString();
  }            // string representation of this board (in the output format specified below)

/*  public static void main(String[] args) {
    In in = new In(args[0]);
    int n = in.readInt();
    int[][] blocks = new int[n][n];
    for (int i = 0; i < n; i++)
      for (int j = 0; j < n; j++) {
        blocks[i][j] = in.readInt();
        //System.out.println(blocks[i][j]);
      }
    Board test = new Board(blocks);
    System.out.println("done");
    System.out.println(test);
    System.out.println(test.manhattan());
    System.out.println(test.hamming());
    for (Board neighbor: test.neighbors())
      System.out.println(neighbor);
    System.out.println("hoho");
    System.out.println(test.twin());
    int[][] blcks = blocks.clone();
    System.out.println(test.equals(new Board(blcks)));
  }// unit tests (not graded)*/
}
