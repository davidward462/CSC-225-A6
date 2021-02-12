/*
	Name: David Ward
	Student ID: V00920409
*/ 

import java.awt.Color;
import java.util.*;

public class GraphAlgorithms{

	/* FloodFillDFS(v, writer, fillColour)
	   Traverse the component the vertex v using DFS and set the colour 
	   of the pixels corresponding to all vertices encountered during the 
	   traversal to fillColour.
	   
	   To change the colour of a pixel at position (x,y) in the image to a 
	   colour c, use
		writer.setPixel(x,y,c);
	*/
	
	
	public static String generateKey(PixelVertex v)
	{
		int x = v.getX();
		int y = v.getY();
		String s = String.valueOf(x)+","+String.valueOf(y);
		//s += ",";
		return s;
	}
		
	//Hashtables
	//static Hashtable<String, PixelVertex> visitedDFS = new Hashtable<String, PixelVertex>();
	//static Hashtable<String, PixelVertex> visitedBFS = new Hashtable<String, PixelVertex>();
	//static Queue<PixelVertex> Q = new LinkedList<PixelVertex>();
	
	public static void FloodFillDFS(PixelVertex v, PixelWriter writer, Color fillColour){
		Hashtable<String, PixelVertex> visitedDFS = new Hashtable<String, PixelVertex>();
		
		Deque<PixelVertex> S = new LinkedList<PixelVertex>();//stack
		
		//initialize
		PixelVertex cur;
		PixelVertex n;
		String curKey;
		int degree;
		
		S.push(v);
		//System.out.println("SIZE: "+S.size());
		
		while(S.size() != 0)
		{
			cur = S.pop();
			//get key
			curKey = generateKey(cur);
			if(visitedDFS.containsKey(curKey)==false)//if not visited
			{
				//visit and color?
				visitedDFS.put(curKey, cur);
				writer.setPixel(cur.getX(), cur.getY(), fillColour);
				
				//for each neighbour n of cur
				LinkedList<PixelVertex> L = cur.getNeighbours();
				degree = cur.getDegree();
				for(int i = 0; i < degree; i++)
				{
					n = L.get(i);
					S.push(n);
				}
			}
		}
		visitedDFS.clear();//clear visited nodes
	}
	
	/* FloodFillBFS(v, writer, fillColour)
	   Traverse the component the vertex v using BFS and set the colour 
	   of the pixels corresponding to all vertices encountered during the 
	   traversal to fillColour.
	   
	   To change the colour of a pixel at position (x,y) in the image to a 
	   colour c, use
			writer.setPixel(x,y,c);
	*/
	public static void FloodFillBFS(PixelVertex v, PixelWriter writer, Color fillColour){
		Hashtable<String, PixelVertex> visitedBFS = new Hashtable<String, PixelVertex>();
		
		Deque<PixelVertex> Q = new LinkedList<PixelVertex>();//queue
		
		//initialize
		String vKey;
		PixelVertex cur;
		PixelVertex n;
		String nKey;
		int degree;
		
		Q.addLast(v);//enqueue at end
		writer.setPixel(v.getX(), v.getY(), fillColour);//color
		vKey = generateKey(v);
		visitedBFS.put(vKey, v);//mark as visited
		
		while(Q.size()!=0)//not empty
		{
			cur = Q.removeFirst();//dequeue from front
			degree = cur.getDegree();
			LinkedList<PixelVertex> L = cur.getNeighbours();//list of cur's neighbours
			for(int i = 0; i < degree; i++)
			{
				n = L.get(i);
				nKey = generateKey(n);
				//if n isn't marked as visited
				if(visitedBFS.containsKey(nKey)==false)
				{
					visitedBFS.put(nKey, n);//visit
					writer.setPixel(n.getX(), n.getY(), fillColour);//color
					Q.addLast(n);//enqueue
				}
			}
		}
		
		visitedBFS.clear();//clear visited nodes
	}
	
}