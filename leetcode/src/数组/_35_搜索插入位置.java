package 数组;

public class _35_搜索插入位置 {
    public int searchInsert(int[] nums, int target) {
        int l = 0;
        int h = nums.length;
        while(l <= h){
            int m = l + (h - l) / 2;
            if(nums[m] == target){
                return m;
            }else if(nums[m] < target){
                l = m + 1;
            }else {
                h = m - 1;
            }
        }
        return l;
        /*
        //此处l和h已经调换位置了。说明没有找到
        if(h < 0 || l > nums.length - 1) return h < 0 ? l + 1 : h;
        return l;
        */
    }
}