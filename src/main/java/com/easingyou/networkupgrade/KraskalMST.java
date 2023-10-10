package com.easingyou.networkupgrade;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class KraskalMST {
	private final List<Edge> edges;
	private final int nOfVertices;

	public KraskalMST(List<Edge> edges, int nOfVertices){
		this.edges = edges;
		this.nOfVertices = nOfVertices;
	}

	public List<Edge> kruskal() {
		edges.sort(Comparator.comparingInt(o -> o.weight));
		UnionFind uf = new UnionFind(nOfVertices);
		List<Edge> result = new ArrayList<>();

		for (Edge edge : edges) {
			if (uf.union(edge.src, edge.dest)) {
				result.add(edge);
				if (result.size() == nOfVertices - 1) {
					break;
				}
			}
		}

		return result;
	}

	public static void main(String[] args) {
//		GraphReader gr = getGraphReaderJavaMagazine();
//		GraphReader gr = getGraphReaderJavaMagazineBiggerNumberAndNames();
		GraphReader gr = new GraphReader(Path.of("/Users/josroseboom/EasingYou/puzzles/network/networkupgrade/src/main/resources/Puzzle_input.txt"));

		final List<String> vertices = gr.getVertices();
		List<Edge> mst = new KraskalMST(gr.getEdges(), vertices.size()).kruskal();
		int totalWeight = 0;
		for (Edge edge : mst) {
			System.out.println("From " + vertices.get(edge.src) + " to " + vertices.get(edge.dest) + " (cost: " + edge.weight + ")");
			totalWeight += edge.weight;
		}
		System.out.println("Total cost: " + totalWeight);
	}

	private static GraphReader getGraphReaderJavaMagazine() {
		List<String> input = List.of(
				"Switch A at (0, 0) is connected to B, E",
				"Switch B at (2, 0) is connected to A, C, E",
				"Switch C at (6, 0) is connected to B, D, E, F",
				"Switch D at (9, 1) is connected to C, F",
				"Switch E at (2, 5) is connected to A, B, C, F",
				"Switch F at (5, 5) is connected to C, D, E"
		);
		return new GraphReader(input);
	}
	private static GraphReader getGraphReaderJavaMagazineBiggerNumberAndNames() {
		List<String> input = List.of(
				"Switch ASW at (100, 100) is connected to BGT, EIU",
				"Switch BGT at (102, 100) is connected to ASW, CHT, EIU",
				"Switch CHT at (106, 100) is connected to BGT, DJI, EIU, FLO",
				"Switch DJI at (109, 101) is connected to CHT, FLO",
				"Switch EIU at (102, 105) is connected to ASW, BGT, CHT, FLO",
				"Switch FLO at (105, 105) is connected to CHT, DJI, EIU"
		);
		return new GraphReader(input);
	}

	public static void mainChatGPT(String[] args) {
		List<Edge> edges = new ArrayList<>();
		edges.add(new Edge(0, 1, 10));
		edges.add(new Edge(0, 2, 6));
		edges.add(new Edge(0, 3, 5));
		edges.add(new Edge(1, 3, 15));
		edges.add(new Edge(2, 3, 4));

		List<Edge> mst = new KraskalMST(edges, 4).kruskal();
		int totalWeight = 0;
		for (Edge edge : mst) {
			System.out.println("Src: " + edge.src + " Dest: " + edge.dest + " Weight: " + edge.weight);
			totalWeight += edge.weight;
		}
		System.out.println("Total Weight of MST: " + totalWeight);
	}
}
