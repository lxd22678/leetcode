package com.youtube.demo;

import java.io.*;
import java.util.*;

public class HelloWorld {
    public static void main(String args[]) {
        Scanner cin = new Scanner(System.in);
        HelloWorld main = new HelloWorld();
        boolean reb = false;
        while(cin.hasNextLine()){
            String line = cin.nextLine();
            reb = false;
            char[] linechar = line.toCharArray();
            int len = linechar.length;
            String ss;
            for (int i = 0; i < len; i++) {
                ss = line.substring(0,i) + line.substring(i+1,len);
                if (main.ifback(ss)){
                    reb = true;
                    break;
                }
            }
            if (reb) {
                System.out.println("YES");
            }else {
                System.out.println("NO");
            }
        }
    }
    public boolean ifback(String s){
        boolean flag = false;
        if (s.length() == 0 || s.length() == 1){
            return true;
        }
        int low=0,up=s.length()-1;
        while(low<up){
            if((s.charAt(low))!=s.charAt(up)) return false;
            else {low++;up--;}
        }
        return true;
    }
}
