package 数组;

public class _34_在排序数组中查找元素的第一个和最后一个位置 {
    //二分查找：O(log n)级别，找到target不能直接返回，要用一个res来记录当前位置，以便继续寻找边界
    public int[] searchRange1(int[] nums, int target) {
        int left = findLeft(nums, target);
        int right = findRight(nums, target);
        return new int[]{left, right};
    }

    private int findLeft(int[] nums, int target) {
        int res = -1;
        int l = 0;
        int h = nums.length - 1;
        while (l <= h) {
            int m = l + (h - l) / 2;
            if (nums[m] > target){
                h = m - 1;
            }else if(nums[m] == target){
                res = m;
                h = m - 1;
            }else {
                l = m + 1;
            }
        }
        return res;
    }

    private int findRight(int[] nums, int target){
        int res = -1;
        int l = 0;
        int h = nums.length - 1;
        while (l <= h) {
            int m = l + (h - l) / 2;
            if (nums[m] < target){
                l = m + 1;
            }else if(nums[m] == target){
                res = m;
                l = m + 1;
            }else {
                h = m - 1;
            }
        }
        return res;
    }

    //线性扫描法
    public int[] searchRange2(int[] nums, int target) {
        int left = -1, right = -1;
        int p = 0; //计数器
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == target){
                right = i;
                p++;
            }
        }
        if(p != 0){ //讨论空数组情况
            left = right - p + 1;
        }
        return new int[]{left, right};
    }
}