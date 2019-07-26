package Metro;

public class Dijkstra {
	
    public static int[] dijkstra(int[][] weight, int start,int end) {  
    	int n = weight.length; //������� 
    	int[] shortPath = new int[n];//����start��������������·�� 
    	String[] path = new String[n]; //����start�������������·�����ַ�����ʾ
    	for(int i=0;i<n;i++)
    		path[i]=new String(Enquiries.START.get(start)+"-->"+Enquiries.START.get(i)); 
    	int[] visited = new int[n]; //��ǵ�ǰ�ö�������·���Ƿ��Ѿ����,1��ʾ����� 
    	//��ʼ������һ�������Ѿ���� 
    	shortPath[start] = 0; 
    	visited[start] = 1; 
    	for(int count = 1; count < n; count++) { //Ҫ����n-1������ 
    		int k = -1; //ѡ��һ�������ʼ����start�����δ��Ƕ��� 
    		int dmin = Integer.MAX_VALUE; 
    		for(int i = 0; i < n; i++) { 
    			if(visited[i] == 0 && weight[start][i] < dmin) { 
    				dmin = weight[start][i]; 
    				k = i; 
    				} 
    			} //����ѡ���Ķ�����Ϊ��������·�����ҵ�start�����·������dmin 
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