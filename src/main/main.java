package main;

import java.util.*;
import java.util.stream.Collectors;

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

        //String input = "]]][[[";
        //int output = minSwaps(input);

        //int[] input = new int[]{2,2,2,1,2,2,1,2,2,2};
        //int output = numberOfSubarrays2(input, 2);

        //String[] words = new String[]{"cat","bt","hat","tree"};
        //String chars = "atach";
        //int output = countCharacters(words, chars);

        //String[] cpdomains = new String[] {"9001 discuss.leetcode.com"};
        //List<String> output = subdomainVisits(cpdomains);

        //char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        //String word = "ABCCED";
        //boolean output = exist(board, word);

        int[] nums1 = {3,9,9,2,1};
        int[] nums2 = {3,2,1,4,7};
        int output = findLength(nums1, nums2);

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
            sum = sum + nums[j];
            j++;

            while (sum >= target) {
                min = Math.min(min, j-i);

                sum = sum - nums[i];
                i++;
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

    // Using 2 pointers
    public static int numberOfSubarrays(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int subArrayCount = 0;
        int oddCount = 0;
        int i = 0;
        int j = 0;

        while (i < nums.length) {
            if (nums[i] % 2 == 1) {
                oddCount++;
            }


            while (oddCount == k) {
                subArrayCount++;
                if (nums[j] % 2 == 1) {
                    oddCount--;
                }
                j++;
            }
            i++;

        }

        return subArrayCount;

    }

    // Using prefix sum
    public static int numberOfSubarrays2(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0,1);
        int totalFound = 0;
        int prefixSum = 0;

        for(int i = 0; i < nums.length; i++) {
            int item = nums[i];
            int value = 0;
            if (item % 2 == 1) {
                value = 1;
            }

            prefixSum = prefixSum + value;
            int diff = prefixSum - k;
            if (map.containsKey(diff)) {
                int current = map.get(diff);
                totalFound = totalFound + current;
            }

            if (map.containsKey(prefixSum)) {
                int prefixSumCurrent = map.get(prefixSum);
                map.put(prefixSum, ++prefixSumCurrent);
            } else {
                map.put(prefixSum, 1);
            }

        }

        return totalFound;

    }

    // First attempt but too complex
    public static int countCharactersFirstAttempt(String[] words, String chars) {
        int totalCounter = 0;

        // First we build up a hashmap of the chars String
        HashMap<Character, Integer> charsDictionary = new HashMap();
        char[] charsArray = chars.toCharArray();
        for(int i = 0; i < chars.length(); i++) {
            char item = charsArray[i];
            int value = charsDictionary.computeIfAbsent(item, (s) -> 0);
            charsDictionary.put(item, ++value);
        }

        // Next we see if we can make the item from the chars array
        for(int j = 0; j < words.length; j++) {
            StringBuffer sb = new StringBuffer();
            String word = words[j];
            char[] wordArray = word.toCharArray();

            for(int k = 0; k < wordArray.length; k++) {
                char toCheck = wordArray[k];
                if (charsDictionary.containsKey(toCheck)) {
                    int toCheckValue = charsDictionary.get(toCheck);
                    if (toCheckValue == 0) {
                        sb = new StringBuffer();
                        break;
                    }
                    sb.append(toCheck);
                    charsDictionary.put(toCheck, --toCheckValue);
                } else {
                    sb = new StringBuffer();
                    break;
                }
            }

            // If we reach here, we have found a match, we need to add it back to the counter
            String sbString = sb.toString();
            totalCounter = totalCounter + sbString.length();

            // Now we need to add the item back
            char[] sbStringArray = sbString.toCharArray();
            for(int i = 0; i < sbStringArray.length; i++) {
                char item = sbStringArray[i];
                int value = charsDictionary.computeIfAbsent(item, (s) -> 0);
                charsDictionary.put(item, ++value);
            }
        }


        return totalCounter;

    }

    // Second attempt by creating 2 hashmaps instead of one
    public static int countCharacters(String[] words, String chars) {
       int totalCount = 0;

        Map<Character, Integer> charCount = new HashMap<Character, Integer>();
        for(Character c: chars.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }

        for(String word: words) {
            Map<Character, Integer> wordCount = new HashMap<Character, Integer>();

            for(Character c: word.toCharArray()) {
                wordCount.put(c, wordCount.getOrDefault(c, 0) + 1);
            }

            boolean goodValue = true;
            for(Character c: wordCount.keySet()) {
                if (charCount.getOrDefault(c, 0) < wordCount.get(c)) {
                    goodValue = false;
                    break;
                }
            }

            if (goodValue) {
                totalCount = totalCount + word.length();
            }

        }

        return totalCount;
    }

    public static List<String> subdomainVisits(String[] cpdomains) {
        // First we need to take each domain and the split it up by the space character
        // Then we add it to the hashmap of subdomains split by "." character

        Map<String, Integer> map = new HashMap<>();

        for(String s: cpdomains) {
            String[] cpDomain = s.split("\s");

            int count = Integer.parseInt(cpDomain[0]);
            String[] domain = cpDomain[1].split("\\.");

            StringBuilder sb = new StringBuilder();
            for(int i = domain.length - 1; i >= 0; i--) {
                String domainString = domain[i];

                sb.insert(0, domainString);
                map.put(sb.toString(), map.getOrDefault(sb.toString(), 0) + count);

                sb.insert(0, ".");
            }
        }

        List<String> output =  map.keySet().stream()
                .map(key -> map.get(key) + " " + key)
                .collect(Collectors.toList());
        return output;
    }

    // Nah fuck this one. You gotta backtrack instead of DFS
    public static boolean exist(char[][] board, String word) {
        // first we need to find our first character then perform dfs
        // also need to keep track of visited

        boolean found = false;
        int boardX = board.length;
        int boardY = board[0].length;
        char[] wordArray = word.toCharArray();
        Stack<int[]> stack = new Stack<>();
        boolean[][] visited = new boolean[boardX][boardY];
        int[][] directions = new int[][]{{0,1}, {0,-1}, {-1,0}, {1,0}};

        // Add the first character to visit
        stack.add(new int[]{0,0});
        int currentWordCount = 0;



        while(!stack.isEmpty()) {
            int[] item = stack.pop();
            int x = item[0];
            int y = item[1];
            Character toCheck = board[x][y];
            if (visited[x][y]) {
                continue;
            }
            visited[x][y] = true;

            if (wordArray[currentWordCount] == toCheck) {
                currentWordCount++;
                for(int[] direction: directions) {
                    int newX = x + direction[0];
                    int newY = y + direction[1];
                    if (newX >= boardX || newX < 0 || newY >= boardY || newY < 0) {
                        continue;
                    } else {
                        stack.add(new int[]{newX, newY});
                    }
                }
            }

            if (currentWordCount == wordArray.length) {
                return true;
            }

        }

        return found;

    }

    public static int findLength(int[] nums1, int[] nums2) {
        int nums1Length = nums1.length;
        int nums2Length = nums2.length;
        int[][] dp = new int[nums1Length + 1][nums2Length+1];
        int max = 0;

        for(int i = 1; i < dp.length; i++) {
            for(int j = 1; j < dp[0].length; j++) {
                if (nums1[i-1] == nums2[j-1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1 ;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }

        return max;
    }

    public static boolean isValidSudoku(char[][] board) {
        // First we need a hashset of all the rows and columns
        // This is the board size
        int N = 9;

        HashSet<Character>[] rows = new HashSet[N];
        HashSet<Character>[] columns = new HashSet[N];
        HashSet<Character>[] boxes = new HashSet[N];

        for(int r = 0; r < N; r++) {
            rows[r] = new HashSet<Character>();
            columns[r] = new HashSet<Character>();
            boxes[r] = new HashSet<Character>();
        }

        for(int r = 0; r < N; r++) {
            for(int c = 0; c < N; c++){
                char val = board[r][c];

                if (val == '.') {
                    continue;
                }
                if (rows[r].contains(val)) {
                    return false;
                }
                rows[r].add(val);

                if (columns[c].contains(val)) {
                    return false;
                }
                columns[c].add(val);

                int idx = (r / 3) * 3 + c / 3;
                if (boxes[idx].contains(val)) {
                    return false;
                }
                boxes[idx].add(val);
            }
        }

        return true;
    }

    public List<String> alertNames(String[] keyName, String[] keyTime) {
        Map<String, List<Integer>> map = new HashMap<>();
        List<String> res = new ArrayList<>();
        for(int i = 0; i < keyName.length; i++) {
            String k = keyName[i];
            int timeInMinutes = getTime(keyTime[i]);
            map.computeIfAbsent(k, g -> new ArrayList<>());
            map.get(k).add(timeInMinutes);
        }

        for(String k: map.keySet()) {
            List<Integer> l = map.get(k);
            Collections.sort(l);  // sort to find the connective checkins
            for (int i = 2; i < l.size(); i++)
                if (l.get(i) - l.get(i - 2) <= 60) {  // connective 3 within 60 mins.
                    res.add(k);
                    break;
                }
        }


        Collections.sort(res);
        return res;
    }

    private int getTime(String t) {
        String[] ss = t.split(":");
        return Integer.parseInt(ss[1]) + 60 * Integer.parseInt(ss[0]);
    }
}
