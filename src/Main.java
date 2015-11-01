import java.io.*;
import java.util.*;

public class Main {

    public static boolean nOutOfMTrue(int n, boolean... booleans) {
        int count = 0;
        for (int i = 0; i < booleans.length; i++) {
            if (booleans[i]){
                count++;
            }
        }
        return count==n;
    }
    public static void main(String args[]) {
        Scanner cin = new Scanner(System.in);
        ArrayList<Boolean> a = new ArrayList<>();
        boolean[] b;
        int n;

        while(cin.hasNextInt()){
            n = cin.nextInt();
            while (cin.hasNextBoolean()){
                a.add(cin.nextBoolean());
            }
            b=new boolean[a.size()];
            for (int i = 0; i < a.size(); i++) {
                b[i] = a.get(i);
            }
            System.out.println(Main.nOutOfMTrue(n,b));
        }
    }
}
