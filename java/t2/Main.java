package t2;

import java.util.Scanner;

public class Main {
    static int check(String s)//检测是哪张牌
    {
        if (s.length() < 2)
            return -1;
        if (s.length() == 3)
            return 10;
        if (s.charAt(0) == 'J') {
            if (s.charAt(1) == '1')
                return 14;//大王
            else
                return 0;//小王
        } else { //正常的
            if (s.charAt(1) == 'A')
                return 1;
            else if (s.charAt(1) >= '2' && s.charAt(1) <= '9')
                return s.charAt(1) - '0';
            else {//花孩
                if (s.charAt(1) == 'J')
                    return 11;
                if (s.charAt(1) == 'Q')
                    return 12;
                if (s.charAt(1) == 'K')
                    return 13;
            }
        }
        return -1;
    }

    static int four(int[] joker) {
        int res = 0;
        for (int i = 1; i <= 13; i++) {
            if (joker[i] == 4) {
                res += 5;
                joker[i] = 0;
            }
        }
        return res;
    }

    static int three(int[] joker) {
        int res = 0;
        for (int i = 1; i <= 13; i++) {
            if (joker[i] == 3) {
                res += 4;
                joker[i] = 0;
            }
        }
        return res;
    }

    static int shunzi(int[] joker) {
        int res = 0;
        for (int i = 2; i <= 9; i++) {
            int find = 1;
            for (int j = 0; j < 5; j++) {
                if (joker[i + j] == 0) {
                    find = 0;
                    break;
                }
            }
            if (find == 1) {
                res += 3;
                for (int j = 0; j < 5; j++) {
                    joker[i + j]--;
                }
                //未考虑 33 44 55 66 77
                int findn0 = 0; //发现不为0的
                for (int j = 0; j < 5; j++) {
                    if (joker[i + j] > 0) {
                        i = i + j - 1;
                        findn0 = 1;
                        break;
                    }
                }
                if (findn0 == 0)
                    i = i + 4;

            }
        }
        int find4 = 1;
        for (int i = 0; i <= 13; i++) {
            if (joker[10 + i] == 0) {
                find4 = 0;
                break;
            }
        }
        if (find4 == 1) {
            if (joker[1] > 0) {
                res += 3;
                for (int i = 0; i <= 13; i++) {
                    joker[10 + i]--;
                }
                joker[1]--;
            }
        }
        return res;
    }

    static int two(int[] joker) {
        int res = 0;
        for (int i = 0; i <= 13; i++) {
            if (joker[i] == 2) {
                res += 2;
                joker[i] = 0;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] joker = new int[15];
        for (int i = 0; i < 15; i++) {
            joker[i] = 0;
        }
        for (int i = 0; i < n; i++) {
            String s = sc.next();
            int val = check(s);
            joker[val]++;
        }
        int ans = 0, res = 0;
        if (joker[0] == joker[14] && joker[0] == 1) {
            ans += 5;
            joker[0] = 0;
            joker[14] = 0;
        }
        // 4 3 顺子 2
        int[] j1 = new int[15];
        for (int i = 0; i < 15; i++) {
            j1[i] = joker[i];
        }
        ans = ans + four(j1);
        ans = ans + three(j1);
        ans = ans + shunzi(j1);
        ans = ans + two(j1);
        res = Math.max(ans, res);
        // 顺子 4 3 2
        // 3 4 5 6 7 7 7 7
        ans = 0;
        for (int i = 0; i < 15; i++) {
            j1[1] = joker[i];
        }
        ans = ans + shunzi(j1);
        ans = ans + four(j1);
        ans = ans + three(j1);
        ans = ans + two(j1);
        res = Math.max(ans, res);

        //4 3 2 shunzi    33 44 55 66 77
        ans = 0;
        for (int i = 0; i < 15; i++) {
            j1[1] = joker[i];
        }

        ans = ans + four(j1);
        ans = ans + three(j1);
        ans = ans + two(j1);
        ans = ans + shunzi(j1);
        res = Math.max(ans, res);

        //4 3 2 shunzi    33 44 55 66 77
        ans = 0;
        for (int i = 0; i < 15; i++) {
            j1[1] = joker[i];
        }
        ans = ans + two(j1);
        ans = ans + four(j1);
        ans = ans + three(j1);
        ans = ans + shunzi(j1);
        res = Math.max(ans, res);
        System.out.println(res);
    }
}
