import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println(pathFinder(
                ".W...\n" +
                        ".W...\n" +
                        ".W.W.\n" +
                        "...W.\n" +
                        "...W.")
        );
    }

    public static boolean pathFinder(String maze) {
       if (maze.length()>1000) {
           System.out.println(maze);
       }
        Reader reader = new Reader();
        int[][] arr = reader.arr(maze);
        if (arr.length == 1 && arr[0][0] == 1) {
            return true;
        }
        if (arr.length > 1 && arr[arr.length - 2][arr.length - 1] == 0 && arr[arr.length - 1][arr.length - 2] == 0) {
            return false;
        }
        System.out.println(Arrays.deepToString(arr));

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
            ArrayList<Node> list = new ArrayList<>();

            Node lastNode = new Node();


            while (!node.finishVisited) {

               // System.out.println(node);


                if (node.right != null && !node.right.finishVisited && node.rightVisited == false && node.right != lastNode) {
                    node.rightVisited = true;
                    lastNode = node;

                    node = node.right;

                } else if (node.down != null && !node.down.finishVisited && node.downVisited == false && node.down != lastNode) {
                    node.downVisited = true;
                    lastNode = node;

                    node = node.down;


                } else if (node.left != null && !node.left.finishVisited && node.leftVisited == false && node.left != lastNode) {
                    node.leftVisited = true;
                    lastNode = node;
                    node = node.left;

                } else if (node.up != null && !node.up.finishVisited && node.upVisited == false && node.up != lastNode) {
                    node.upVisited = true;
                    lastNode = node;

                    node = node.up;
                } else if (node == rout[rout.length - 1][rout.length - 1]) {
                    return true;
                } else {
                    node = lastNode;
                }

                if (node == rout[rout.length - 1][rout.length - 1]) {
                    System.out.println("кінець");
                    flag = 1;


                    return true;

                }

                if ((node.upVisited || node.up == null) && (node.leftVisited || node.left == null) &&
                        (node.downVisited || node.down == null) && (node.rightVisited || node.right == null)) {
                    node.finishVisited = true;
                    node = lastNode;
                }

                if (flag == -1) {
                searcher(node)    ;
                } else {
                    break;
                }
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

        public String getName() {
            return name;
        }

        public Node getUp() {
            return up;
        }

        public Node getLeft() {
            return left;
        }

        public Node getDown() {
            return down;
        }

        public Node getRight() {
            return right;
        }

        public Node getNext() {
            return next;
        }

        public Node getPrevious() {
            return previous;
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


//    public boolean searcher(MainOne.Node node) {
//
//        if (node.finishVisited) {
//
//            return false;
//        }
//        MainOne.Node XY = new MainOne.Node();
//        XY = node;
//        while (flag==-1||!rout[0][0].finishVisited) {
//
//            //System.out.println(XY.toString());
//            if (XY == rout[rout.length - 1][rout.length - 1]) {
//                System.out.println("кінець");
//                flag = 1;
//                break;
//
//            }
//            if (XY.right != null && !XY.right.finishVisited && XY.rightVisited == false &&XY.right != XY.previous) {
//                XY.rightVisited = true;
//                XY.right.previous = XY;
//                XY = XY.right;
//
//
//
//            }
//            else if (XY.down != null && !XY.down.finishVisited && XY.downVisited == false && XY.down != XY.previous) {
//                XY.downVisited = true;
//                XY.down.previous = XY;
//                XY = XY.down;
//
//
//            }
//            else if (XY.left != null && !XY.left.finishVisited && XY.leftVisited == false && XY.left != XY.previous) {
//                XY.leftVisited = true;
//                XY.left.previous = XY;
//                XY = XY.left;
//
//            }
//            else if (XY.up != null && !XY.up.finishVisited && XY.upVisited == false && XY.up != XY.previous) {
//                XY.upVisited = true;
//                XY.up.previous = XY;
//                XY = XY.up;
//
//            } else {
//                XY.finishVisited = true;
//                XY = XY.previous;
//                ;
//            }
//
//        }
//        return false;
//    }
//
//}