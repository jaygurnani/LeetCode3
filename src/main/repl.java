package main;

public class repl {
    public static void main(String[] args) {
        int r = 2;
        int c = 0;
        int indx = (4 / 3) * 3 + c / 3;

        int output = getTime("11:20");
        System.out.println(output);

    }

    private static int getTime(String t) {  // transfer stirng to relative mins.
        String[] ss = t.split(":");
        return Integer.parseInt(ss[1]) + 60 * Integer.parseInt(ss[0]);
    }

}
