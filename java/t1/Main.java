package t1;
// 本题为考试单行多行输入输出规范示例，无需提交，不计分。

        import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        int[] nums = {0,1,2,3,4,5,6,7};
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        //大根堆
        int len = nums.length;
        //建立大根堆
        for(int i = 3;i > 0;i-- )
        {
            int pos = i;
            while (pos*2 < len)
            {
                int maxIdx = pos * 2;
                if(pos*2+1 < len)
                {
                    maxIdx = nums[pos*2] > nums[pos*2+1] ? pos*2 : pos*2+1;
                }
                int t = nums[maxIdx];
                nums[maxIdx] = nums[pos];
                nums[pos] = t;
                pos = maxIdx;
            }
            /*System.out.println(i);
            for(int j = 1;j < len;j++) System.out.print(nums[j]+" ");
            System.out.println();*/
        }
        /*for(int i = 1;i < len;i++) System.out.print(nums[i]+" ");
        System.out.println();*/
        for(int i = 0;i < k;i++)
        {
            int tmp = nums[len-1];
            nums[len-1] = nums[1];
            nums[1] = tmp;
            len--;
            int pos = 1;
            while (pos*2 < len)
            {
                int maxIdx = pos * 2;
                if(pos*2+1 < len)
                {
                    maxIdx = nums[pos*2] > nums[pos*2+1] ? pos*2 : pos*2+1;
                }
                int t = nums[maxIdx];
                nums[maxIdx] = nums[pos];
                nums[pos] = t;
                pos = maxIdx;
            }
        }
        /*for(int i = 1;i < 8;i++) System.out.print(nums[i]+" ");
        System.out.println();*/
        System.out.println(nums[8-k]);
    }
}