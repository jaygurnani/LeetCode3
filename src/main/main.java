package main;

public class main {

    public static void main(String[] args) {
        //int[] input = new int[]{3,2,4};
        //int[] output = twoSum(input, 6);
        //System.out.println(output[0]);
        //System.out.println(output[1]);

        int[] input = new int[]{1,0,2,0,-1,3};
        boolean output = increasingTriplet(input);
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

}
