import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Edge {
	int src, dest, weight;

	public Edge(int src, int dest, int weight) {
		this.src = src;
		this.dest = dest;
		this.weight = weight;
	}
}

class UnionFind {
	private int[] parent;
	private int[] rank;

	public UnionFind(int n) {
		parent = new int[n];
		rank = new int[n];
		for (int i = 0; i < n; i++) {
			parent[i] = i;
			rank[i] = 0;
		}
	}

	public int find(int x) {
		if (parent[x] != x) {
			parent[x] = find(parent[x]);
		}
		return parent[x];
	}

	public boolean union(int x, int y) {
		int rootX = find(x);
		int rootY = find(y);

		if (rootX == rootY) {
			return false;
		}

		if (rank[rootX] > rank[rootY]) {
			parent[rootY] = rootX;
		} else if (rank[rootX] < rank[rootY]) {
			parent[rootX] = rootY;
		} else {
			parent[rootY] = rootX;
			rank[rootX]++;
		}
		return true;
	}
}

public class KruskalMST {

	public static List<Edge> kruskal(List<Edge> edges, int V) {
		Collections.sort(edges, Comparator.comparingInt(o -> o.weight));
		UnionFind uf = new UnionFind(V);
		List<Edge> result = new ArrayList<>();

		for (Edge edge : edges) {
			if (uf.union(edge.src, edge.dest)) {
				result.add(edge);
				if (result.size() == V - 1) {
					break;
				}
			}
		}

		return result;
	}

	public static void main(String[] args) {
		List<Edge> edges = new ArrayList<>();
		edges.add(new Edge(0, 1, 10));
		edges.add(new Edge(0, 2, 6));
		edges.add(new Edge(0, 3, 5));
		edges.add(new Edge(1, 3, 15));
		edges.add(new Edge(2, 3, 4));

		List<Edge> mst = kruskal(edges, 4);
		int totalWeight = 0;
		for (Edge edge : mst) {
			System.out.println("Src: " + edge.src + " Dest: " + edge.dest + " Weight: " + edge.weight);
			totalWeight += edge.weight;
		}
		System.out.println("Total Weight of MST: " + totalWeight);
	}
}
