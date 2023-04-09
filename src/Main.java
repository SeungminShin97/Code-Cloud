import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());   // 사람 수
        int m = Integer.parseInt(st.nextToken());   // 파티 수
        int[] arr = new int[n + 1];
        for(int i = 0; i <= n; i++)
            arr[i] = i;
        st = new StringTokenizer(br.readLine());
        int personNum = Integer.parseInt(st.nextToken());
        if(personNum == 0) {
            for(int i = 0; i < m; i++)
                br.readLine();
            System.out.println(m);
        }
        else {
            int root = Integer.parseInt(st.nextToken());
            for(int i = 1; i < personNum; i++) 
                arr[Integer.parseInt(st.nextToken())] = root;
            int[][] party = new int[m][n + 1];
            for(int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                party[i][0] = Integer.parseInt(st.nextToken());
                for(int j = 1; j <= party[i][0]; j++) {
                    party[i][j] = Integer.parseInt(st.nextToken());
                    if(j == 1)
                        arr[party[i][j]] = rootNum(arr, party[i][j]);
                    else {
                        if(arr[party[i][1]] == root) {
                            arr[rootNum(arr, party[i][j])] = root;
                            arr[party[i][j]] = root;
                        }
                        else {
                            if(rootNum(arr, party[i][j]) == root) {
                                arr[rootNum(arr, party[i][1])] = root;
                                arr[party[i][1]] = root;
                                arr[party[i][j]] = root;
                            }
                            else 
                                arr[party[i][j]] = arr[party[i][1]];
                        }
                        
                    }   
                }
            }
            int cnt = 0;
            for(int i = 0; i < m; i++) {
                boolean yesman = true;
                for(int j = 1; j <= party[i][0]; j++) {
                    if(rootNum(arr, party[i][j]) == root) {
                        yesman = false;
                        j = party[i][0] + 1;
                    }
                }
                if(yesman)
                    cnt++;
            }
            System.out.println(cnt);
        }
    }

    public static int rootNum(int[] arr, int n) {
        if(arr[n] == n) return n;
        else
            return rootNum(arr, arr[n]);
    }
}