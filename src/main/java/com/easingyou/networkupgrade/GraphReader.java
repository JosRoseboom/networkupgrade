package com.easingyou.networkupgrade;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GraphReader {

	private final List<String> unparsedLines;
	private List<Edge> edges;
	private List<String> vertices;


	public GraphReader(Path path){
		try {
			this.unparsedLines = Files.readAllLines(path);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	public GraphReader(List<String> unparsedLines){
		this.unparsedLines = unparsedLines;
	}

	private void init(){

		if (edges == null){
			edges = new ArrayList<>();
			vertices = new ArrayList<>();
			Map<String, Point> coordinates = new HashMap<>();
			Map<String, List<String>> connections = new HashMap<>();
			Pattern pattern = Pattern.compile("Switch ([A-Z]+) at \\(([0-9]+), ([0-9]+)\\) is connected to (.+)");
			unparsedLines.stream()
					.map(pattern::matcher)
					.filter(Matcher::matches)
					.forEach(matcher -> {
						final String vertexName = matcher.group(1);
						coordinates.put(vertexName, new Point(Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3))));
						List<String> connectedToOnlyKnown = Arrays.stream(matcher.group(4).split(","))
								.map(String::trim)
								.filter(vertices::contains)
								.toList();
						connections.put(vertexName, connectedToOnlyKnown);
						vertices.add(vertexName);
					});

			connections.forEach((v1, neighbours) -> {
				final int srcIndex = vertices.indexOf(v1);
				final Point srcPoint = coordinates.get(v1);

				neighbours.forEach(neighbour -> edges.add(new Edge(srcIndex, vertices.indexOf(neighbour), srcPoint.getCost(coordinates.get(neighbour)))));
			});
		}
	}

	public List<Edge> getEdges() {
		init();
		return edges;
	}

	public List<String> getVertices() {
		init();
		return vertices;
	}

}
