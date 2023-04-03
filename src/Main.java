import java.io.*;
import java.util.*;

class node {
    int x;
    int y;
    int time;
    int timelocate;
    node(int x, int y, int time, int timelocate) {
        this.x = x;
        this.y = y;
        this.time = time;
        this.timelocate = timelocate;
    }
}

public class Main {
    public static int fishSize = 2;
    public static int fishEatCnt = 0;
    public static Queue<node> q;
    public static PriorityQueue<node> q2;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int[][][] arr = new int[n][n][2];
        int[] size = new int[6];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                int input = Integer.parseInt(br.readLine());
                arr[i][j][0] = input;
                if(input == 9) {
                    q.add(new node(j, i, 0, -1));
                    arr[i][j][0] = 0;
                }
                else if(input > 0) 
                    size[input - 1] += 1;
            }
        }
        int time = 0;
        q2 = new PriorityQueue<>((o1,o2) -> {
            node a = (node) o1;
            node b = (node) o2;
            if(a.time == b.time) {
                if(a.y == b.y) {
                    if(a.x < b.x)
                        return -1;
                    else 
                        return 1;
                }
                else if(a.y > b.y)
                    return -1;
                else
                    return 1;
            }
            else if(a.time < b.time)
                return -1;
            else 
                return 1;
        });
        while(caneat(size, fishSize) && !q.isEmpty()) {
            int x = q.peek().x;
            int y = q.peek().y;
            time = q.peek().time;
            int timelocate = q.peek().timelocate;
            if(y > 0 && arr[y - 1][x][0] <= fishSize && arr[y - 1][x][1] != timelocate) {// 위쪽
                if(arr[y - 1][x][0] > 0 && arr[y - 1][x][0] < fishSize) 
                    q2.add(new node(x, y - 1, ))
            }

        }
    }
    
    //     int time = 0;                               // 경과시간
    //     q2 = new LinkedList<>();
    //     while(caneat(size, fishSize) && q.size() > 0) {
            
    //         boolean pass = false;

    //             pass = move(x1, y1, -1, 0, time, timelocate, arr);
    //         //if(!pass) {     // 먹이를 먹었다면 통과
    //             if(x1 > 0 && arr[y1][x1 - 1][0] <= fishSize)       // 왼쪽
    //                 pass = move(x1, y1, 0, -1, time, timelocate, arr);
    //         //}
    //         //if(!pass) {
    //             if(x1 < n - 1 && arr[y1][x1 + 1][0] <= fishSize)   // 오른쪽
    //                 pass = move(x1, y1, 0, 1, time, timelocate, arr);
    //         //}
    //         //if(!pass) {
    //             if(y1 < n - 1 && arr[y1 + 1][x1][0] <= fishSize)   // 아래
    //                 pass = move(x1, y1, 1, 0, time, timelocate, arr);
    //         //}
    //         //if(pass) {    
    //             // while(q.size() > 1) {
    //             //     q.poll();
    //             // }
    //             // size[arr[q.peek().y][q.peek().x][0] - 1]--;
    //             // arr[q.peek().y][q.peek().x][0] = 0;
    //         // }
    //         // else 
    //         //     q.poll();
    //         q.poll();
    //         if(!pass) {

    //         }
    //     }
    //     System.out.println(time);
    // }

    // public static boolean move(int x, int y, int ynum, int xnum, int time, int timelocate, int[][][] arr) {
    //     boolean pass = false;
    //     if(arr[y + ynum][x + xnum][0] > 0 && arr[y + ynum][x + xnum][0] < fishSize) {   // 먹이를 먹을 수 있음
    //         eat();                                                                      // 먹음
    //         pass = true;                                                                // 먹었으면 현재위치로 위치 초기화
    //         q2.add(new node(x + xnum, y + ynum, ))
    //     } 
    //     if(arr[y + ynum][x + xnum][1] != timelocate) {
    //         if(pass)
    //             timelocate -= 1;
    //         q.add(new node(x + xnum, y + ynum, time + 1, timelocate));
    //         arr[y + ynum][x + xnum][1] = timelocate;
    //     }
    //     return pass;
    // }

    public static void eat() {
        fishEatCnt++;
        if(fishEatCnt == fishSize) {
            fishSize++;
            fishEatCnt = 0;
        }
    }

    public static boolean caneat(int[] size, int fishsize) {
        int cnt = 0;
        if(fishsize > 7)
            fishsize = 7;
        for(int i = 0; i < fishsize - 1; i++)
            cnt += size[i];
        if(cnt > 0)
            return true;
        else
            return false;
    }

}