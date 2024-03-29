package shortestPath;

import java.io.*;
import java.util.*;

/**
 * BaekJoon_10159, 저울
 * @author kevin-Arpe
 * 
 * Sketch Idea
 * 	1. 입력을 받으면서 무거운 것 -> 가벼운 것 으로 이동하는 그래프 생성
 * 	2. 두 무게가 비교 가능하다는 것은 무게 비교가 연속적으로 이루어졌음을 의미
 * 		2.1 무거운 물건에서 가벼운 물건으로 이동하는 단방향 그래프에서는 무거운 물건에서 가벼운 물건으로 이동하는 경로가 있을 때 그 경로상의 임의의 두 물건은 무게 비교가 가능
 * 		2.1 만약 가벼운 물건에서 시작한다면 직접 무거운 물건으로 이동할 수는 없으므로 무거운 물건에서 자신으로 오는 경로가 있는지 확인
 *
 */

public class BaekJoon_10159 {
	static class Node implements Comparable<Node> {
		int v, w;
		Node next;
		
		Node(int v, int w) {
			this.v = v;
			this.w = w;
		}
		
		Node(int v, int w, Node next) {
			this.v = v;
			this.w = w;
			this.next = next;
		}

		@Override
		public int compareTo(Node o) {
			return this.w < o.w ? -1 : 1;
		}
	}
	
	static final int INF = Integer.MAX_VALUE;
	static int V, E;
	static Node[] adjList;
	static int[][] dist;
	static boolean[] isVisited;
	
	static void dijkstra(int s) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(s, 0));
		dist[s][s] = 0;
		
		int cnt = 0;
		while (pq.size() > 0) {
			Node curr = pq.poll();
			if (isVisited[curr.v]) continue;
			isVisited[curr.v] = true; 
			
			if (++cnt == V) break;
			
			for (Node next=adjList[curr.v]; next!=null; next=next.next) {
				if (isVisited[next.v] || dist[s][next.v] <= dist[s][curr.v] + next.w) continue;
				dist[s][next.v] = dist[s][curr.v] + next.w;
				pq.offer(new Node(next.v, dist[s][next.v]));
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		V = Integer.parseInt(br.readLine());
		E = Integer.parseInt(br.readLine());
		
		adjList = new Node[V];
		for (int i=0; i<E; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken()) - 1;
			int e = Integer.parseInt(st.nextToken()) - 1;
			adjList[s] = new Node(e, 0, adjList[s]);
		}
		br.close();

		dist = new int[V][V];
		for (int i=0; i<V; i++) {
			Arrays.fill(dist[i], INF);
			isVisited = new boolean[V];
			dijkstra(i);
		}
		
		int[] cnt = new int[V];
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<V; i++) {
			for (int j=0; j<V; j++) {
				if (i == j || dist[i][j] == 0 || dist[j][i] == 0) continue;
				cnt[i]++;
			}
			sb.append(cnt[i]).append("\n");
		}
		System.out.print(sb);
	}

}
