package Metro;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

public class Enquiries {
	
	static final int max = 10000000; //不邻接时的权重,与加载文件中不邻接值相等
	private static int [][] matrix; //定义一个邻接矩阵
	public static double distce;//两点间的最短距离
	public static String path;
	public static int price;
	static int pstart; //存起点终点对应的下标
	static int pend;
	static Boolean fstart;//起点终点输入辨别标志位
	static Boolean fend;
	public static GUI g = new GUI();
	
	static List<String> START = new ArrayList<>();
	static List<String> END = new ArrayList<>();  //没用到
	static List<Integer> JULI = new ArrayList<>();
	
	public static void main(String[] args) {
		// TODO 自动生成的方法存根	
		String filePath = "src/data.txt";	
		readTxtFile(filePath);
		g.search.addActionListener(new ActionListener() {			
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO 自动生成的方法存根
					Map(START,JULI);
					run(g.jt1.getText(),g.jt2.getText());
				}
			});
			
	  }
	
	public static  void run(String startpoint,String endpoint) {

		for(int i=0;i<START.size();i++) {
			if(startpoint.equals(START.get(i))) {
				pstart=i;
				fstart=true;break;
			}else
				fstart=false;
		}
		for(int i=0;i<START.size();i++) {
			if(endpoint.equals(START.get(i))) {
			  pend=i;
			fend=true;break;
		    }else
			fend=false;
		}
		if(fstart&&fend) {
		  if(pstart==pend) {
			distce=0;
			g.jt5.setText("起点与终点相同，确定要花3元进去？");
		}
		else {
			distce=(double)Dijkstra.dijkstra(matrix, pstart,pend)[pend]/1000;
			g.jt5.setText(trpath(path));
		}
		g.setstring(distce, price(distce));
	  }else {
		  if(!fstart&&fend) {g.jt5.setText("起点有误，请重新输入！");g.jt3.setText(null);g.jt4.setText(null);}
		  if(fstart&&!fend) {g.jt5.setText("终点有误，请重新输入！");g.jt3.setText(null);g.jt4.setText(null);}
		  if(!fstart&&!fend) {g.jt5.setText("起点有误，请重新输入！"+'\n'+"终点有误，请重新输入！");g.jt3.setText(null);g.jt4.setText(null);}
	  }
		  

}
	
	public static String trpath(String path) {//路线站点优化
		
    	int f1=0,f2=0;
		for(int k=0;k<2;k++) {
		for(int i=0;i<path.length();i++) {
			if(path.charAt(i)>='0'&&path.charAt(i)<='9')
				path=path.replace(path.charAt(i),' ').replace(" ","").trim();
		  }		
		}
		
		return path;
	}
	
	public static int price(double distce) {//计算票价
		if(distce<=6)  price=3;
		else if(distce<=12) price=4;
		else if(distce<=22) price=5;
		else if(distce<=32) price=6;
		else price=(int)(7+(distce-32)/20);
		return price;
		
	}
	
    public static void Map(List<String> start,List<Integer> juli) {   //构建邻接矩阵
		matrix = new int[start.size()][start.size()];
		String str1,str2;
		for(int i=0;i<start.size();i++) {
			for(int j=0;j<start.size();j++) {
				if(i==j)
					matrix[i][j]=0;
				else
					if(j==i+1)
						matrix[i][j]=juli.get(i);
					else
						if(i==j+1)
							matrix[i][j]=juli.get(j);
						else
							matrix[i][j]=max;	
			}
		}	
		for(int i=0;i<start.size();i++) {
			for(int j=0;j<start.size();j++) {
		      str1=start.get(i);
		      str2=start.get(j);
		//两位
		   if(str1.charAt(0)>='0'&&str1.charAt(0)<='9'&&str1.charAt(1)>='0'&&str1.charAt(1)<='9') {
			if(str1.substring(2).trim().equals(str2)) {
//				System.out.println("两位的"+str1+" "+str2);
            	matrix[i][j]=0;
				matrix[j][i]=0;
			 }
		   }
		}
	}
	}

 	public static void readTxtFile(String filePath){  //读取文件，并存入LIST集合
	        try {
	                String encoding="GBK";
	                File file=new File(filePath);
	                if(file.isFile() && file.exists()){ 
	                    InputStreamReader read = new InputStreamReader(new FileInputStream(file),encoding);	                    
	                    BufferedReader bufferedReader = new BufferedReader(read);
	                    String lineTxt = null;
	                    while((lineTxt = bufferedReader.readLine()) != null) {
	                    	if(lineTxt.indexOf("―")>0)
	                    	{
	                    		START.add(lineTxt.substring(0, lineTxt.indexOf("―")).trim());
	                    		END.add(lineTxt.substring(lineTxt.indexOf("―")+2,lineTxt.indexOf(" ")).trim());
	                    		JULI.add(Integer.parseInt(lineTxt.substring(lineTxt.indexOf(" ")).trim()));
	                    	}
	                    }
	                    read.close();
	        }else{
	            System.out.println("找不到指定的文件");
	        }
	        } catch (Exception e) {
	            System.out.println("读取文件内容出错");
	            e.printStackTrace();
	        }

	    }
  
}
 