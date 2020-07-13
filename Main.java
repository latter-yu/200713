import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main1(String[] args) {

        // 木棒拼图
        // 有一个由很多木棒构成的集合，每个木棒有对应的长度
        // 请问能否用集合中的这些木棒以某个顺序首尾相连构成一个面积大于 0 的简单多边形且所有木棒都要用上
        // 简单多边形即不会自交的多边形。
        // 初始集合是空的，有两种操作：
        // 要么给集合添加一个长度为 L 的木棒，要么删去集合中已经有的某个木棒。
        // 每次操作结束后你都需要告知是否能用集合中的这些木棒构成一个简单多边形。

        // 输入描述:
        // 每组测试用例仅包含一组数据，每组数据第一行为一个正整数 n 表示操作的数量(1 ≤ n ≤ 50000) ，
        // 接下来有 n 行，每行第一个整数为操作类型 i (i ∈ {1,2})，第二个整数为一个长度 L(1 ≤ L ≤ 1,000,000,000)。
        // 如果 i = 1 代表在集合内插入一个长度为 L 的木棒，如果 i = 2 代表删去在集合内的一根长度为 L 的木棒。
        // 输入数据保证删除时集合中必定存在长度为 L 的木棒，且任意操作后集合都是非空的。
        // 输出描述:
        // 对于每一次操作结束有一次输出，如果集合内的木棒可以构成简单多边形，输出 "Yes" ，否则输出 "No"。

        // 示例：
        // 输入
        // 5
        // 1 1
        // 1 1
        // 1 1
        // 2 1
        // 1 2
        // 输出
        // No
        // No
        // Yes
        // No
        // No

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Integer> list = new ArrayList<>();
        // 存放 length
        for (int i = 0; i < n; i++) {
            int type = sc.nextInt();
            int length = sc.nextInt();
            if (type == 1) {
                // 第一种：增加
                list.add(length);
            }
            if (type == 2) {
                // 第二种：删除
                if (list != null) {
                    list.remove(list.indexOf(length));
                    // indexOf(Object obj)方法的实现机制是从序列(List)的第0个元素开始依次循环
                    // 并且调用每个元素的 equals() 方法和参数对象进行比较
                    // 如果某一个元素的 equals() 方法返回值为 true，那么就把当前元素的索引位置作为结果返回
                    // 假如序列中有多个重复的元素，只返回这个重复的元素第一次出现时所在的索引位置的值
                    // 参数对象如果在序列中没有出现，那么返回-1
                }
            }
            // 构建多边形的条件：
            // 其他边之和 > 最长的边
            // 此时多边形面积 > 0

            int max = 0;
            int sum = 0;
            for (int l : list) {
                // 找出最长的长度
                if (l > max) {
                    max = l;
                }
            }
            for (int l : list) {
                // 计算所以边的长度之和
                sum += l;
            }
            if (sum > (max * 2)) {
                // 所有边的长度之和 - 最长边 = 其它边之和
                // 然后两者比较大小
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }
    }

    public static void main(String[] args) {
        // 地下迷宫
        // 小青蛙有一天不小心落入了一个地下迷宫, 小青蛙希望用自己仅剩的体力值 P 跳出这个地下迷宫。
        // 为了让问题简单,假设这是一个 n * m 的格子迷宫,迷宫每个位置为 0 或者 1
        // 0 代表这个位置有障碍物, 小青蛙达到不了这个位置; 1 代表小青蛙可以达到的位置。
        // 小青蛙初始在 (0, 0) 位置, 地下迷宫的出口在 (0, m - 1)
        // (保证这两个位置都是 1, 并且保证一定有起点到终点可达的路径)
        // 小青蛙在迷宫中水平移动一个单位距离需要消耗 1 点体力值, 向上爬一个单位距离需要消耗 3 个单位的体力值, 向下移动不消耗体力值
        // 当小青蛙的体力值等于 0 的时候还没有到达出口, 小青蛙将无法逃离迷宫。
        // 现在需要你帮助小青蛙计算出能否用仅剩的体力值跳出迷宫(即达到 (0, m - 1)位置)。

        // 输入描述:
        // 输入包括 n + 1 行:
        // 第一行为三个整数n, m(3 <= m, n <= 10), P(1 <= P <= 100)
        // 接下来的 n 行:
        // 每行 m 个 0 或者 1, 以空格分隔
        // 输出描述:
        // 如果能逃离迷宫, 则输出一行体力消耗最小的路径, 输出格式见样例所示;
        // 如果不能逃离迷宫,则输出 "Can not escape!"。 测试数据保证答案唯一

        // 示例：
        // 输入
        // 4 4 10 1 0 0 1 1 1 0 1 0 1 1 1 0 0 1 1
        // 输出
        // [0, 0], [1, 0], [1, 1], [2, 1], [2, 2], [2, 3], [1, 3], [0, 3]

        /*
         * 1 0 0 1
         * 1 1 0 1
         * 0 1 1 1
         * 0 0 1 1
         *
         * [0,0],[1,0],[1,1],[2,1],[2,2],[2,3],[1,3],[0,3]
         */

        // 回溯思想 + 贪心算法
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int p = sc.nextInt();
        int [][] arr = new int[n][m];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
        // x、y设为起点
        int x = 0, y = 0;
        // 用两个 list，放走过的坐标
        ArrayList<Integer> xlist = new ArrayList<>();
        ArrayList<Integer> ylist = new ArrayList<>();
        int i = 0;
        if(path(x, y, n, m, p, xlist, ylist, arr)) {
            while(true) {
                if(i < xlist.size()) {
                    System.out.print("[" + xlist.get(i) + "," + ylist.get(i) + "]");
                } else {
                    break;
                }
                i++;
                if(i < xlist.size()) {
                    System.out.print(",");
                }
            }
        } else {
            System.out.println("Can not escape!");
        }
    }

    private static boolean path(int x, int y, int n, int m, int p, ArrayList<Integer> xlist, ArrayList<Integer> ylist, int[][] arr) {
        if(x < 0 || x >= n || y < 0 || y >= m || arr[x][y] != 1 || p < 0) {
            return false;
        }
        // 走过的坐标，为 -1
        arr[x][y] = -1;
        xlist.add(x);
        ylist.add(y);

        if(x ==0 && y == m - 1) {
            return true;
        }
        //贪心策略
        if(!path(x - 1, y, n, m, p, xlist, ylist, arr)) {
            if(!path(x, y + 1, n, m, p - 1, xlist, ylist, arr)) {
                if(!path(x + 1, y, n, m, p - 3, xlist, ylist, arr)) {
                    if(!path(x, y - 1, n, m, p - 1, xlist, ylist, arr)) {
                        // 回溯回退，对应坐标赋为 0，并且从 list 中移除
                        arr[x][y] = 0;
                        xlist.remove(xlist.size() - 1);
                        ylist.remove(ylist.size() - 1);
                        return false;
                    } else {
                        return true;
                    }
                } else {
                    return true;
                }
            } else {
                return true;
            }
        }else {
            return true;
        }
    }
}