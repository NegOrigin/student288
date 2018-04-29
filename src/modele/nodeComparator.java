package modele;

import java.util.Comparator;

public class nodeComparator implements Comparator<Node> {
	@Override
	public int compare(Node x, Node y){
	    if(x.getPathScore()<y.getPathScore()){
	        return -1;
	    }
	    if(x.getPathScore()>y.getPathScore()){
	        return 1;
	    }
	    return 0;
	}
}
