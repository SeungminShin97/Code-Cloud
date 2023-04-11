import java.io.*;
import java.util.*;

public class Main {	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		int[][] arr = new int[n + 1][3];
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3; j++) 
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
		int[][] cost = new int[n + 1][3];
		for(int i = 1; i <= n; i++) {
			cost[i][0] = Min(cost[i - 1][1], cost[i - 1][2]) + arr[i][0];
			cost[i][1] = Min(cost[i - 1][0], cost[i - 1][2]) + arr[i][1];
			cost[i][2] = Min(cost[i - 1][0], cost[i - 1][1]) + arr[i][2];
		}
		int output = Min(cost[n][0], cost[n][1]);
		output = Min(output, cost[n][2]);
		System.out.println(output);
	}

	static int Min(int a, int b) {
		return (a > b) ? b : a;
	}
}