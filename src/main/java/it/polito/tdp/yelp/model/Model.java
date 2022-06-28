package it.polito.tdp.yelp.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.yelp.db.YelpDao;

public class Model {
	private YelpDao dao;
	private Graph<Review,DefaultWeightedEdge>grafo;
	private Map<String,Review>idMap;
	public Model() {
		dao=new YelpDao();
		idMap=new HashMap<>();
		this.dao.getAllReviews(idMap);
		
	}
	public List<String> allCities(){
		return this.dao.allCities();
	}
	public List<Business> getLocali(String citta){
		return this.dao.getLocali(citta);
	}
	public void creaGrafo(Business locale,String citta) {
		this.grafo=new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		Graphs.addAllVertices(this.grafo, this.dao.getVertici(citta, locale, idMap));
		for(Adiacenza a:dao.getArchi(citta, locale, idMap)) {
			if(grafo.containsVertex(a.getR1())&& grafo.containsVertex(a.getR2())) {
				if(a.getPeso()<0) {
					Graphs.addEdgeWithVertices(grafo, a.getR2(), a.getR1(),((double)-1)*a.getPeso());
					
				}
				else if(a.getPeso()>0) {
					Graphs.addEdgeWithVertices(grafo, a.getR1(), a.getR2(),a.getPeso());
				}
				
			}
		}
		
	}
	public int nVertici() {
		return grafo.vertexSet().size();
	}
	
	public int nArchi() {
		return grafo.edgeSet().size();
	}
	
	public Graph<Review , DefaultWeightedEdge> getGrafo(){
		return grafo;
	}
	public List<Adiacenza> LocaleMigliore() {
		List<Adiacenza>result=new ArrayList<>();
		Review a=null;
		double maxDelta=0;
		for(Review e:this.grafo.vertexSet()) {
			double pesoUscente=this.grafo.outgoingEdgesOf(e).size();
			
			double delta=pesoUscente;
			if(delta==maxDelta) {
				maxDelta=delta;
				a=idMap.get(e);
				
				result.add(new Adiacenza(a,maxDelta));
				
			}
			else if(delta>maxDelta) {
				maxDelta=delta;
				a=idMap.get(e.getReviewId());
				result.clear();
				result.add(new Adiacenza(a,maxDelta));
				
			}
			
		}
		
	
return result;	}
	
}
