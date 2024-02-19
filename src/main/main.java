package main;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

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

        String s = "00110011";
        int output = countBinarySubstrings(s);
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
}
