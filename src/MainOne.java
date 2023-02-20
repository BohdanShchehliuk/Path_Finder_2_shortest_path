import java.util.ArrayList;
import java.util.Arrays;

public class MainOne {
    public static void main(String[] args) {
        System.out.println(pathFinder(
                "  ........W..\n" +
                        "WWWW.W.W.W.\n" +
                        "....W......\n" +
                        ".W.....W...\n" +
                        ".W.W.....W.\n" +
                        ".....WW...W\n" +
                        "W......WW..\n" +
                        "W...W..W.W.\n" +
                        ".W......W.W\n" +
                        ".W.W..W...W\n" +
                        ".WW..WW.WW.\n" +
                        ".WW......W..\n" +
                        ".W..W....W..\n" +
                        ".W.....WW.WW\n" +
                        "..W.........\n" +
                        "W...W.W....W\n" +
                        ".WWW...WW...\n" +
                        "W...WWW..W.W\n" +
                        "W.W.WW.W.W..\n" +
                        ".W.W.......W\n" +
                        "..W..W..WW.W\n" +
                        "..W.......W.\n" +
                        "W.W..W.W..W.")
        );
    }

    public static boolean pathFinder(String maze) {
        Reader reader = new Reader();
        int[][] arr = reader.arr(maze);
        if (arr.length == 1 && arr[0][0] == 1) {
            return true;
        }
        if (arr.length > 1 && arr[arr.length - 2][arr.length - 1] == 0 && arr[arr.length - 1][arr.length - 2] == 0) {
            return false;
        }
        //  System.out.println(Arrays.deepToString(arr));

        Logic logic = new Logic();
        logic.initializationNode(arr);
        logic.declarationNode(logic.rout, arr);
        Node node = logic.rout[0][0];
        logic.searcher(node);
        if (logic.flag == 1) {
            return true;
        }
        return false;
    }

    static class Logic {
        int count = 0;
        int flag = -1;

        static Node[][] rout;

        public void initializationNode(int arr[][]) {
            rout = new Node[arr.length][arr[0].length];
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr.length; j++) {
                    Node node = new Node();
                    node.name = "" + i + ";" + j;
                    node.x = i;
                    node.y = j;
                    rout[i][j] = node;
                }
            }
        }

        public void declarationNode(Node rout[][], int arr[][]) {

            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr.length; j++) {

                    if (arr[i][j] == 0) {
                        rout[i][j] = null;
                    } else {
                        if (j < arr.length - 1 && arr[i][j + 1] == 1) {
                            rout[i][j].right = rout[i][j + 1];
                        }
                        if (j >= 1 && arr[i][j - 1] == 1) {
                            rout[i][j].left = rout[i][j - 1];
                        }
                        if (i < rout.length - 1 && arr[i + 1][j] == 1) {
                            rout[i][j].down = rout[i + 1][j];
                        }
                        if (i >= 1 && arr[i - 1][j] == 1) {
                            rout[i][j].up = rout[i - 1][j];
                        }
                    }
                }
            }
        }

        public boolean searcher(Node node) {
            count++;
            System.out.println(node.toString() + "   COUNT= " + count);


            if (node == rout[rout.length - 1][rout.length - 1]) {
                System.out.println("кінець");
                flag = 1;
                return true;
            }
            if (node.finishVisited) {
                searcher(node.previous);
            }

            if (node.right != null && !node.right.finishVisited && node.rightVisited == false && node.previous != node.right) {
                node.rightVisited = true;
                node.right.previous = node;
                searcher(node.right);

            } else if (node.down != null && !node.down.finishVisited && node.downVisited == false && node.previous != node.down) {
                node.downVisited = true;
                node.down.previous = node;
                searcher(node.down);

            } else if (node.left != null && !node.left.finishVisited && node.leftVisited == false && node.previous != node.left) {
                node.leftVisited = true;
                node.left.previous = node;
                searcher(node.left);

            } else if (node.up != null && !node.up.finishVisited && node.upVisited == false && node.previous != node.up) {
                node.upVisited = true;
                node.up.previous = node;
                searcher(node.up);
            } else {
                node.finishVisited = true;
                searcher(node.previous);
            }
            return false;
        }
    }


    static class Node {
        boolean upVisited = false;
        boolean downVisited = false;
        boolean rightVisited = false;
        boolean leftVisited = false;
        boolean finishVisited = false;

        Node up = null;
        Node left = null;
        Node down = null;
        Node right = null;
        String name;
        Node next;
        Node previous;
        int x;
        int y;


        public Node() {
            this.up = up;
            this.left = left;
            this.down = down;
            this.right = right;
            this.name = name;
            this.next = next;
            this.previous = previous;
        }


        @Override
        public String toString() {
            return "Main.Node{" +
                    "NAME='" + name + '\'' +
                    '}';
        }


    }

    static class Reader {
        public int[][] arr(String maze) {
            String c = "";
            for (int i = 0; i < maze.length(); i++) {
                if (maze.charAt(i) == '.' || maze.charAt(i) == 'W') {
                    c = c + maze.charAt(i);
                }
            }
            System.out.println(c);
            int size = (int) Math.sqrt(c.length());
            int[][] arr = new int[size][size];
            int index = 0;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (c.charAt(index) == '.') {
                        arr[i][j] = 1;
                    } else {
                        arr[i][j] = 0;
                    }
                    index++;
                }
                ;
            }
            return arr;
        }
    }
}
