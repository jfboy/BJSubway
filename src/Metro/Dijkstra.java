package Metro;

public class Dijkstra {
	
    public static int[] dijkstra(int[][] weight, int start,int end) {  
    	int n = weight.length; //顶点个数 
    	int[] shortPath = new int[n];//保存start到其他各点的最短路径 
    	String[] path = new String[n]; //保存start到其他各点最短路径的字符串表示
    	for(int i=0;i<n;i++)
    		path[i]=new String(Enquiries.START.get(start)+"-->"+Enquiries.START.get(i)); 
    	int[] visited = new int[n]; //标记当前该顶点的最短路径是否已经求出,1表示已求出 
    	//初始化，第一个顶点已经求出 
    	shortPath[start] = 0; 
    	visited[start] = 1; 
    	for(int count = 1; count < n; count++) { //要加入n-1个顶点 
    		int k = -1; //选出一个距离初始顶点start最近的未标记顶点 
    		int dmin = Integer.MAX_VALUE; 
    		for(int i = 0; i < n; i++) { 
    			if(visited[i] == 0 && weight[start][i] < dmin) { 
    				dmin = weight[start][i]; 
    				k = i; 
    				} 
    			} //将新选出的顶点标记为已求出最短路径，且到start的最短路径就是dmin 
    		shortPath[k] = dmin; 
    		visited[k] = 1;
    		for(int i = 0; i < n; i++) { 
    			if(visited[i] == 0 && weight[start][k] + weight[k][i] < weight[start][i]) { 
    				weight[start][i] = weight[start][k] + weight[k][i]; 
    			    path[i] = path[k] + "-->" + Enquiries.START.get(i); 
    				}
    			} 
    		} 
        Enquiries.path=path[end];
		return shortPath; 
    	} 
    }