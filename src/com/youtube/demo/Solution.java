package com.youtube.demo;

import com.sun.tools.javac.util.*;

import java.util.*;
import java.util.List;

/**
 * Created by baidu on 15/9/19.
 */
public class Solution{
    public void moveZeroes(int[] nums){
        int len = nums.length;
        int index = 0;
        for (int i = 0; i < len; ) {
            if (nums[i] == 0){
                while (i<len&&nums[i] == 0){
                    i++;
                }
            }else {
                i++;
                index++;
            }
            if(i<len)
                nums[index] = nums[i];
        }
        while (index < len){
            nums[index] = 0;
            index++;
        }
    }
    public boolean isUgly(int num) {
        if (num == 0)
            return false;
        if (num == 1)
            return true;
        while (num%2 == 0)
            num = num/2;
        while (num%3 == 0)
            num = num/3;
        while (num%5 == 0)
            num = num/5;
        return num == 1;
    }
    public boolean isAnagram(String s, String t) {
        int[] stable = new int[26];
        int[] ttable = new int[26];
        if (s.length() != t.length())
            return false;
        for (int i = 0; i < s.length(); i++) {
            stable[s.charAt(i)-'a'] += 1;
        }
        for (int i = 0; i < t.length(); i++) {
            ttable[t.charAt(i)-'a'] += 1;
        }
        for (int i = 0; i < 26; i++) {
            if (stable[i] != ttable[i]){
                return false;
            }
        }
        return true;
    }

    public int hammingWeight(int n) {
        int count = 0,x;
        for (int i = 0; i < 32; i++) {
            x = n&1;
            if ((n&1) == 1) {
                count++;
            }
            n = n>>1;
        }
        return count;
    }

    public boolean canWinNim(int n) {
        return n%4 != 0;
    }

    public boolean wordPattern(String pattern, String str) {
        HashSet<Character> patset = new HashSet<>();
        HashSet<String> strset = new HashSet<>();
        String[] strarr = str.split(" ");
        if (strarr.length != pattern.length()) {
            return false;
        }
        for (int i = 0; i < strarr.length; i++) {
            if (strset.contains(strarr[i])) {
                if (!patset.contains(pattern.charAt(i))){
                    return false;
                }
                strset.remove(strarr[i]);
                patset.remove(pattern.charAt(i));
            }else {
                if (patset.contains(pattern.charAt(i))){
                    return false;
                }
                strset.add(strarr[i]);
                patset.add(pattern.charAt(i));

            }
        }
        return true;
    }

    public void gameOfLife(int[][] board) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
            return;
        }
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i <m; i++) {
            for (int j = 0; j < n; j++) {
                int x = getLiveNum(board,i,j);
                if (board[i][j] == 0){
                    if (x == 3) board[i][j] += 10;
                }else {
                    if (x == 2||x == 3) board[i][j] += 10;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] /= 10;
            }
        }
    }

    private int getLiveNum(int[][] board,int x,int y){
        int count = 0;
        for (int i = x-1; i <= x+1; i++) {
            for (int j = y-1; j <= y+1; j++) {
                if (i<0||j<0||i>board.length-1||j>board[0].length-1||(i==x&&j==y)) continue;
                if (board[i][j]%10==1){
                    count++;
                }
            }
        }
        return count;
    }

    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[] row = new int[m];
        int[] col = new int[n];


        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0){
                    row[i] = 1;
                    col[j] = 1;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (row[i] == 1 || col[j] == 1){
                    matrix[i][j]=0;
                }
            }
        }
    }

    private boolean ifZeroe(int[][] matrix, int x,int y){
        boolean flag = false;
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i <m; i++) {
            if (matrix[i][y] == 0){
                return true;
            }
        }
        for (int i = 0; i < n; i++) {
            if (matrix[x][i] == 0) {
                return true;
            }
        }
        return flag;
    }

    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length-1;
        int mid = left + (right-left)/2;
        while (left < right){
            if (nums[left]<nums[right]){
                return nums[left];
            }
            if (nums[left]>nums[mid]){
                right = mid;
                mid = left + (right-left)/2;
            }else {
                left = mid + 1;
                mid = left + (right-left)/2;
            }
        }
        return nums[left];
    }

    public int countPrimes(int n) {
        boolean[] isprime = new boolean[n];
        for (int i = 0; i < n; i++) {
            isprime[i] = true;
        }

        for (int i = 2; i*i < n; i++) {
            if (isprime[i] == false) continue;
            for (int j = i*i; j < n; j+=i) {
                isprime[j]=false;
            }
        }
        
        int count = 0;
        for (int i = 1; i < n; i++) {
            if (isprime[i]){
                count ++;
            }
        }
        return count;
    }

    public int nthUglyNumber(int n) {
        PriorityQueue<Long> pqueue = new PriorityQueue<>();
        long tem = 1;
        pqueue.offer(1l);
        for (int i = 1; i < n ; i++) {
            tem = pqueue.poll();
            while (!pqueue.isEmpty() && pqueue.peek() == tem) pqueue.poll();
            pqueue.add(tem*2);
            pqueue.add(tem*3);
            pqueue.add(tem*5);
        }
        return pqueue.poll().intValue();
    }

    public boolean isHappy(int n) {
        HashSet<Integer> hset = new HashSet<>();
        int squere,remain;
        while (hset.add(n)){
            squere = 0;
            while (n!=0){
                remain = n%10;
                squere += remain*remain;
                n = n/10;
            }
            if (squere==1) return true;
            n = squere;
        }
        return false;
    }

    public int addDigits(int num) {
        int temp;
        temp = (int)((num-1)/9);
        return num - 9*temp;
    }

    public int mySqrt(int x) {
        int left = 1;
        int right = x;
        long mid = left + (right-left)/2;
        while (mid*mid > x){
            mid=(mid+x/mid)/2;
        }
        return (int)mid;
    }

    public double myPow(double x, int n) {
        int count = -1;
        int m = n;
        double result = x;
        while (m!=0){
            m = m/2;
            count++;
        }
        for (int i = 0; i < count; i++) {
            result *= result;
        }
        if (Math.abs(n%2)==1){
            result *= x;
        }
        if (n==0){
            result = 1;
        }
        System.out.println(result);
        if (n<0){
            result = 1/result;
        }

        return result;
    }

    public int findDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        int size = 0;
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
            size++;
            if (size!=set.size()){
                return nums[i];
            }
        }
        return -1;
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> order = new ArrayList<>();
        if (matrix.length == 0){
            return order;
        }
        int[][] flag = new int[matrix.length][matrix[0].length];
        int row = 0,col = 0;
        int rowflag = 1,colflag = 1;
        while (true){
            if (row>=matrix.length || row<0 || col<0 || col>=matrix[0].length || flag[row][col] == 1) {
                break;
            }
            while (true){
                if (row>=matrix.length || row<0 || col<0 || col>=matrix[0].length || flag[row][col] == 1) {
                    col -= colflag;
                    colflag *= -1;
                    row += rowflag;
                    break;
                }
                if (flag[row][col] == 0){
                    order.add(matrix[row][col]);
                    flag[row][col] = 1;
                }else {
                    col -= colflag;
                    row += rowflag;
                    break;
                }
                col += colflag;
            }
            while (true){
                if (row>=matrix.length || row<0 || col<0 || col>=matrix[0].length || flag[row][col] == 1) {
                    row -= rowflag;
                    rowflag *= -1;
                    col += colflag;
                    break;
                }
                if (flag[row][col] == 0){
                    order.add(matrix[row][col]);
                    flag[row][col] = 1;
                }
                else {
                    row -= rowflag;
                    col += colflag;
                    break;
                }
                row += rowflag;
            }
        }
        return order;
    }

    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int count = 1;
        if (n == 0){
            return matrix;
        }
        int[][] flag = new int[n][n];
        int row = 0,col = 0;
        int rowflag = 1,colflag = 1;
        while (true){
            if (row>=n || row<0 || col<0 || col>=n || flag[row][col] == 1) {
                break;
            }
            while (true){
                if (row>=n || row<0 || col<0 || col>=n || flag[row][col] == 1) {
                    col -= colflag;
                    colflag *= -1;
                    row += rowflag;
                    break;
                }
                if (flag[row][col] == 0){
                    matrix[row][col] = count++;
                    flag[row][col] = 1;
                }else {
                    col -= colflag;
                    row += rowflag;
                    break;
                }
                col += colflag;
            }
            while (true){
                if (row>=n || row<0 || col<0 || col>=n || flag[row][col] == 1) {
                    row -= rowflag;
                    rowflag *= -1;
                    col += colflag;
                    break;
                }
                if (flag[row][col] == 0){
                    matrix[row][col] = count++;
                    flag[row][col] = 1;
                }
                else {
                    row -= rowflag;
                    col += colflag;
                    break;
                }
                row += rowflag;
            }
        }
        return matrix;
    }

    public int numSquares(int n) {
        int[] nums = new int[n+1];
        int min;
        int j;
        for (int i = 1; i <= n; i++) {
            j = 1;
            min = Integer.MAX_VALUE;
            while ((i - j*j)>=0){
                if ((nums[i - j*j]+1)<min){
                    min = nums[i - j*j]+1;
                }
                j++;
            }
            nums[i]=min;
        }
        return nums[n];
    }

    public int[] searchRange(int[] nums, int target) {
        int start = 0,end = nums.length-1,mid = start + (end - start)/2;
        int[] station = new int[2];
        while (start < end){
            if (nums[mid] == target){
                break;
            }else if (nums[mid] > target){
                end = mid;
            }else {
                start = mid + 1;
            }
            mid = start + (end - start)/2;
        }
        if (nums[mid] == target){
            station[0] = station[1] = mid;
            while (station[0]>=0 && nums[station[0]] == target){
                station[0]--;
            }
            station[0]++;
            while (station[1]<nums.length && nums[station[1]] == target){
                station[1]++;
            }
            station[1]--;
        }else {
            station[0] = station[1] = -1;
        }
        return station;
    }

    public int removeElement(int[] nums, int val) {
        int count = 0;
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            nums[index] = nums[i];
            if (nums[i] == val){
                count++;
            }else {
                index++;
            }
        }
        return nums.length - count;
    }

    public ListNode removeElements(ListNode head, int val) {
        ListNode pre = null,p = head;
        while (p!=null){
            if (p.val == val){
                if (pre == null){
                    head = head.next;
                    p = head;
                }else {
                    p = p.next;
                    pre.next = p;
                }
            }else {
                pre = p;
                p = p.next;
            }
        }
        return head;
    }

    public ListNode insertionSortList(ListNode head) {
        ListNode curr = head, next = null;
        ListNode l = new ListNode(0);
        while (curr != null){
            next = curr.next;
            ListNode p = l;
            while (p.next != null && p.next.val < curr.val){
                p = p.next;
            }
            curr.next = p.next;
            p.next = curr;
            curr = next;
        }
        return l.next;
    }

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode tail = head.next.next;
        ListNode p = head;
        while (tail != null && tail.next != null){
            p = p.next;
            tail = tail.next.next;
        }
        ListNode l1 = sortList(p.next);
        p.next = null;
        ListNode l2 = sortList(head);
        return sortListMerge(l1,l2);
    }

    public ListNode sortListMerge(ListNode l1,ListNode l2){
        ListNode result = new ListNode(Integer.MIN_VALUE);
        ListNode p = result;
        while (l1!=null&&l2!=null){
            if (l1.val<l2.val){
                p.next = l1;
                l1 = l1.next;
            }else {
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }
        if (l1 == null){
            p.next = l2;
        }
        if (l2 == null){
            p.next = l1;
        }
        return result.next;
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int index = m+n-1;
        int index1 = m-1;
        int index2 = n-1;
        while (index1 >= 0 && index2 >= 0){
            if (nums1[index1]<nums2[index2]){
                nums1[index--] = nums2[index2--];
            }else {
                nums1[index--] = nums1[index1--];
            }
        }
        while (index1>=0){
            nums1[index--] = nums1[index1--];
        }
        while (index2>=0){
            nums1[index--] = nums2[index2--];
        }
    }

    public int largestRectangleArea(int[] height) {
        if (height == null || height.length == 0){
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int max = Integer.MIN_VALUE,top;
        for (int i = 0; i < height.length; i++) {
            while (stack.peek() != -1){
                if (height[stack.peek()] > height[i]){
                    top = stack.pop();
                    max = Math.max(max,height[top]*(i - 1 - stack.peek()));
                }else break;
            }
            stack.push(i);
        }

        while (stack.peek() != -1){
            top = stack.pop();
            max = Math.max(max, height[top] * (height.length - 1 - stack.peek()));
        }
        return max;
    }

    public static void main(String[] args){
        Solution solution = new Solution();
        int[] nums = new int[2];
        nums[0] = 1;
        nums[1] = 1;

        int[] nums2 = new int[7];
        nums2[0] = 6;
        nums2[1] = 2;
        nums2[2] = 5;
        nums2[3] = 4;
        nums2[4] = 5;
        nums2[5] = 1;
        nums2[6] = 6;
        System.out.println(solution.largestRectangleArea(nums));
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
