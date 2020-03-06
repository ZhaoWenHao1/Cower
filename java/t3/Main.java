package t3;

import java.util.Scanner;
import java.util.Vector;

/**
 * @author happyzhao
 * @data 2020/2/19 15:24
 * @type 华为机试在线训练
 * @question
 */
public class Main {
    static float ans = 0.0f;
    static int sum = 0;//总人数
    static int m, w, c;

    static float count(Vector<Integer> circle, int men, int women, int child)//算多少分
    {
        float res = 0.0f;
        res = m * 2.0f + w * 1.0f + c * 0.5f;
        //System.out.println("res+ :" + res);
        int len = circle.size();
        int cwm = 0, cw = 0, cm = 0, mw = 0;
        for (int i = 0; i < len; i++) {
            int i1 = (i + 1) % len;
            int i2 = (i + 2) % len;
            if (circle.get(i) == 1 && circle.get(i1) == 2 && circle.get(i2) == 3)
                cwm++;
            if (circle.get(i) == 1 && circle.get(i1) == 2)
                cw++;
            if (circle.get(i) == 1 && circle.get(i1) == 3)
                cm++;
            if (circle.get(i) == 3 && circle.get(i1) == 2)
                mw++;
        }
        if (cwm > 0) {
            res += 3.0f;
        } else {
            if (cw > 0) {
                res += 2.0f;
            }
        }
        if (cm > 0)
            res += 2.5f;
        res = res - (float) mw;
        /*if(ans < res)
            System.out.println(circle);*/
        ans = Math.max(ans, res);
        return res;
    }

    // men - 3 women - 2 child - 1
    static int play(Vector<Integer> circle, int men, int women, int child) {
        int len = circle.size();
        if (sum == len) {
            if (circle.get(len - 1) == 1 && circle.get(0) == 3) {
                return 0;
            } else if (circle.get(len - 1) == 3 && circle.get(0) == 1)
                return 0;
            else {
                float re = count(circle, men, women, child);
                ans = Math.max(ans, re);
                return 0;
            }
        }
        if (len == 0 || circle.get(len - 1) == 2 || circle.get(len - 1) == 1)//3个都可以放
        {
            if (men > 0) {
                circle.add(3);
                play(circle, men - 1, women, child);
                circle.remove(len);
            }
            if (women > 0) {
                circle.add(2);
                play(circle, men, women - 1, child);
                circle.remove(len);
            }
            if (child > 0) {
                circle.add(1);
                play(circle, men, women, child - 1);
                circle.remove(len);
            }
        } else {
            if (men > 0) {
                circle.add(3);
                play(circle, men - 1, women, child);
                circle.remove(len);
            }
            if (women > 0) {
                circle.add(2);
                play(circle, men, women - 1, child);
                circle.remove(len);
            }
        }
        return 1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        m = sc.nextInt();
        w = sc.nextInt();
        c = sc.nextInt();
        sum = m + w + c;
        Vector<Integer> circle = new Vector<Integer>();
        if (m > 0 && w == 0 && c > 0) {
            System.out.println("0.0");
        } else {
            play(circle, m, w, c);
            System.out.println(ans);
        }

    }
}
