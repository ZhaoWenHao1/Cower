package fanchuan1;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Vector;


public class Main {
    public static void main(String[] args) {
        int row,col,main,tran,itor;
        Scanner sc = new Scanner(System.in);
        row = sc.nextInt();
        col = sc.nextInt();
        main = sc.nextInt();
        tran = sc.nextInt();
        itor = sc.nextInt();
        int[][] table = new int[row][col];
        int count = 0;//聚合后的行数
        //Vector<Integer> its = new Vector<Integer>(1000);//指标种类数，科目总数
        Set<Integer> its = new HashSet<Integer>();
        for(int i = 0;i < row;i++)
        {
            for(int j = 0;j < col;j++)
            {
                table[i][j] = sc.nextInt();
            }
            if(!its.contains(table[i][tran]))//O(1)
            {
                its.add(table[i][tran]);
            }
            //聚合
            int sum = 0;
            for(int j = 0;j < count;j++)
            {
                if(table[i][main] == table[j][main] && table[i][tran] == table[j][tran])
                {
                    table[j][itor] += table[i][itor];
                    sum = 1;
                    break;
                }
            }
            if(sum == 0)
                count++;
        }
        /*for(int i = 0;i < count;i++)
        {
            for(int j = 0;j < col;j++)
            {
                System.out.print(table[i][j] + " ");
            }
            System.out.println();
        }*/
        Vector<Integer> unless = new Vector<Integer>(col);//非特殊列所在的下标
        for(int i = 0;i < col;i++)
        {
            if(i != main && i != tran && i != itor)
            {
                unless.add(i);
            }
        }

        //Vector<Integer> mvec = new Vector<Integer>(row);//主键的统计
        Set<Integer> mvec = new HashSet<Integer>();
        int extra = 0;//主键加非特殊值
        for(int i = 0;i < count;i++)
        {
            if(!mvec.contains(table[i][main]))
            {
                mvec.add(table[i][main]);
                int sum = table[i][main];
                for(int j = 0;j < unless.size();j++)
                {
                    int idx = unless.get(j);
                    sum += table[i][idx];
                }
                extra += sum;
            }
        }
        int res = 0;
        for(int i = 0;i < count;i++)
        {
            res+=table[i][itor];
        }
        res = res - (its.size()*mvec.size() - count);
        System.out.println(res+extra);
    }
}
