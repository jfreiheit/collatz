import java.util.HashSet;
import java.util.Set;

/*
 * Symbolic Reachable Graph
 */
public class SRG
{
	Set<Node> nodes;
	Set<Edge> edges;
	
	public SRG() {
		this.nodes = new HashSet<>();
		this.edges = new HashSet<>();
	}
	
	public void addEdge(Edge e) {
		Node from = e.getFrom();
		Node to = e.getTo();
		this.nodes.add(from);
		this.nodes.add(to);
		this.edges.add(e);
	}
}
