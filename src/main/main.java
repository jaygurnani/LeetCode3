package main;

import java.util.*;

public class main {

    public static void main(String[] args) {
        //int[] input = new int[]{3,2,4};
        //int[] output = twoSum(input, 6);
        //System.out.println(output[0]);
        //System.out.println(output[1]);

        //int[] input = new int[]{1,0,2,0,-1,3};
        // boolean output = increasingTriplet(input);

        //boolean output = isAnagram("anagram", "nagaram");
        //int[] input = new int[]{2,3,1,2,4,3};
        //int output = minSubArrayLen(7, input);

        //String s = "ADOBECODEBANC";
        //String t = "ABC";
        //String output = minWindow(s,t);

        //String s = "00110011";
        //int output = countBinarySubstrings(s);
        //int[] input = new int[]{-1,0,1,2,-1,-4};
        //List<List<Integer>> output = threeSum(input);

        //long[] input = new long[]{2L,6L};
        //long output = getMaxAdditionalDinersCount(10,1, 2, input);

        //boolean output = validWordAbbreviation("internationalization", "i12iz4n");

        // String input = "lee(t(c)o)de)"
        //String input = "a)b(c)d";
        //String output = minRemoveToMakeValid(input);

        String input = "]]][[[";
        int output = minSwaps(input);

        System.out.println(output);
    }

    public static int[] twoSum(int[] nums, int target) {
        for(int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{0,0};
    }


    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];

        int left = 1;
        for(int i = 0; i < n; i++) {
            if (i > 0) {
                left = left * nums[i-1];
            }
            res[i] = left;
        }

        int right = 1;
        for(int j = n-1; j >= 0; j--) {
            if (j < n-1) {
                right = right * nums[j + 1];
            }

            res[j] = right * res[j];
        }

        return res;
    }

    public static boolean increasingTriplet(int[] nums) {
        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;

        for(int num: nums) {
            if (num <= first) {
                // We need to update first to be the lowest one even though when we return true
                // first might not be set to the item which will be used in the triplet
                // Try [1,0,2,0,-1,3] to see this. The triplet is 0,2,3, but when true is returned
                // first is set to -1
                first = num;
            } else if (num < second) {
                second = num;
            } else if (num > second) {
                return true;
            }
        }

        return false;
    }

    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        HashMap<Character, Integer> sDict = new HashMap<>();
        HashMap<Character, Integer> tDict = new HashMap<>();

        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();
        for (int i = 0; i < sArray.length; i++) {
            char key1 = sArray[i];
            sDict.put(key1, sDict.getOrDefault(key1, 0) + 1);

            char key2 = tArray[i];
            tDict.put(key2, tDict.getOrDefault(key2, 0) + 1);
        }

        return sDict.equals(tDict);
    }

    public static int minSubArrayLen(int target, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int i = 0;
        int j = 0;
        int sum = 0;
        int min = Integer.MAX_VALUE;

        while(j < nums.length) {
            sum = sum + nums[j++];

            while (sum >= target) {
                min = Math.min(min, j-i);
                sum = sum - nums[i++];
            }
        }

        return min == Integer.MAX_VALUE ? 0 : min;
    }

    public static String minWindow(String s, String t) {
        HashMap<Character, Integer> tDictionary = new HashMap<>();
        char[] tChar = t.toCharArray();
        char[] sChar = s.toCharArray();
        for (int i = 0; i < tChar.length; i++) {
            char item = tChar[i];
            int value = tDictionary.computeIfAbsent(item, (k) -> 0);
            tDictionary.put(item, ++value);
        }

        int i = 0;
        int j = 0;
        StringBuffer sb = new StringBuffer();
        int min = Integer.MAX_VALUE;

        while (i < sChar.length) {
            char atI = sChar[i];
            if (t.indexOf(atI) > 0) {
                sb.append(atI);
                int val = tDictionary.get(atI);
                tDictionary.put(atI, --val);
            } else {
                sb.append(atI);
            }

            i++;
        }

        return sb.toString();
    }

    public static int countBinarySubstringsNotWorked(String s) {
        int result = 0;
        char[] sCharArray = s.toCharArray();
        HashMap<Character, Integer> dictionary = new HashMap<Character, Integer>();
        dictionary.put('0', 0);
        dictionary.put('1', 0);

        if (s.length() == 1) {
            return result;
        }

        for (int i = 0; i < sCharArray.length; i++) {
            char item = sCharArray[i];
            if (item == '0') {
                int count = dictionary.get('0');
                dictionary.put('0', ++count);
            }
            if (item == '1') {
                int count = dictionary.get('1');
                dictionary.put('1', ++count);
            }

            if (dictionary.get('0') == dictionary.get('1') && dictionary.get('0') != 0) {
                result++;
            }
        }
        return result;
    }

    public static int countBinarySubstrings(String s) {
        int[] groups = new int[s.length()];
        int t = 0;
        groups[0] = 1;

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i-1) != s.charAt(i)) {
                groups[++t] = 1;
            } else {
                groups[t]++;
            }
        }

        int ans = 0;
        for(int i = 1; i <= t; i++) {
            ans += Math.min(groups[i-1], groups[i]);
        }

        return ans;
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();

        for(int i = 0; i < nums.length - 2; i++) {
            // Duplicate values has been seen
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int lo = i + 1;
            int hi = nums.length - 1;

            while (lo < hi) {
                int sum = nums[i] + nums[lo] + nums[hi];

                if (sum == 0) {
                    // Found a triplet with zero sum
                    ans.add(Arrays.asList(nums[i], nums[lo], nums[hi]));

                    // Duplicate values to be skipped
                    while (lo < hi && nums[lo] == nums[lo + 1]) {
                        lo++;
                    }

                    // Duplicate values to be skipped
                    while (lo < hi && nums[hi] == nums[hi - 1]) {
                        hi--;
                    }

                    // Move both pointers
                    lo++;
                    hi--;
                } else if (sum < 0) {
                    // Sum is less than zero, increment lo to increase the sum
                    lo++;
                } else {
                    // Sum is greater than zero, decrement k to decrease the sum
                    hi--;
                }
            }

        }

        return ans;
    }


    public static long getMaxAdditionalDinersCount(long N, long K, int M, long[] S) {
        // Write your code here
        Arrays.sort(S);
        long extraSpace = 0L;
        long firstOpenSeat = 1L;

        for(int i = 0; i < S.length; i++) {
            long takenSeat = S[i];
            long openSeat = takenSeat - K - firstOpenSeat;
            if (openSeat > 0) {
                extraSpace += Math.ceil((double) openSeat / (K+1L));
            }

            firstOpenSeat = takenSeat + K + 1;
        }

        long openSeat = N + 1L - firstOpenSeat;
        if (openSeat > 0) {
            extraSpace += Math.ceil((double) openSeat / (K+1L));
        }

        return extraSpace;
    }

    public long getSecondsRequired(long N, int F, long[] P) {
        long min = N;
        for (int i = 0; i < F; i++) {
            if (P[i] < min) min = P[i];
        }
        return N - min;
    }

    public static boolean validWordAbbreviation(String word, String abbr) {
        if (word == null || abbr == null) {
            throw new IllegalArgumentException("Input is null");
        }

        int wLen = word.length();
        int aLen = abbr.length();

        // length of abbreviation cannot be greater than word's length
        if (aLen > wLen) {
            return false;
        }

        if (wLen == 0) {
            return true;
        }

        int i = 0;
        int j = 0;

        while (i < wLen && j < aLen) {
            // It current characters in both word and abbr is same continue checking.
            if (word.charAt(i) == abbr.charAt(j)) {
                i++;
                j++;
                continue;
            }

            // Now current characters in word and abbr do not match. Thus current character
            // in abbr should be a valid starting digit 0 < x <= 9.
            if (abbr.charAt(j) == '0' || !Character.isDigit(abbr.charAt(j))) {
                return false;
            }

            // The num value
            int num = 0;
            while (j < aLen && Character.isDigit(abbr.charAt(j))) {
                num = 10 * num + (abbr.charAt(j) - '0');
                j++;
            }

            // Increment word pinter by num.
            i += num;
        }

        // If both i and j pointers are at end, then we have a valid word abbreviation
        return i == wLen && j == aLen;
    }

     // Definition for a binary tree node.
     public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
         TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
    }

    public class Pair {
        public Pair(TreeNode treeNode, Integer integer) {
            this.treeNode = treeNode;
            this.integer = integer;
        }

        public TreeNode getTreeNode() {
            return treeNode;
        }

        public void setTreeNode(TreeNode treeNode) {
            this.treeNode = treeNode;
        }

        TreeNode treeNode;
        Integer integer;
    }

    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> output = new ArrayList<>();
        if (root == null) {
            return output;
        }

        HashMap<Integer, ArrayList> columnTable = new HashMap<>();
        Queue<Pair> queue = new ArrayDeque<>();
        int column = 0;
        queue.offer(new Pair(root, column));

        while (!queue.isEmpty()) {
            Pair p = queue.poll();
            root = p.treeNode;
            column = p.integer;

            if (root != null) {
                if (!columnTable.containsKey(column)) {
                    columnTable.put(column, new ArrayList<Integer>());
                }
                columnTable.get(column).add(root.val);

                queue.offer(new Pair(root.left, column - 1));
                queue.offer(new Pair(root.right, column + 1));
            }
        }

        List<Integer> sortedKeys = new ArrayList<>(columnTable.keySet());
        Collections.sort(sortedKeys);
        for(int i: sortedKeys) {
            output.add(columnTable.get(i));
        }

        return output;
    }

    public static String minRemoveToMakeValid(String s) {
        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        char[] charArray = s.toCharArray();
        List<Integer> itemsToRemove = new ArrayList<>();

        for(int i = 0; i < charArray.length; i++) {
            char item = charArray[i];

            // If we see an opening parenthesis, add it to the stack
            if (item == '(') {
                stack.push(i);
            } else if (item == ')'){
                // If we see a closing bracket, there needs to be an opening one to make it correct
                if (stack.size() == 0) {
                    // error condition
                    itemsToRemove.add(i);
                } else {
                    int openingIndex = stack.pop();
                }
            }
        }

        while(!stack.isEmpty()) {
            int errorItem = stack.pop();
            itemsToRemove.add(errorItem);
        }

        for(int i = 0; i < charArray.length; i++) {
            if (itemsToRemove.contains(i)) {
                continue;
            }
            sb.append(charArray[i]);
        }
        return sb.toString();
    }

    public static int minSwaps(String s) {
        Stack <Character> stack = new Stack ();
        int mismatch = 0;
        for (int i = 0; i < s.length (); i++) {
            char ch = s.charAt (i);
            if (ch == '[')
                stack.push (ch);
            else {
                if (!stack.isEmpty ()) {
                    stack.pop ();
                } else {
                    mismatch++;
                }
            }
        }
        return (mismatch + 1) / 2;
    }


}
