package Metro;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

public class Enquiries {
	
	static final int max = 10000000; //���ڽ�ʱ��Ȩ��,������ļ��в��ڽ�ֵ���
	private static int [][] matrix; //����һ���ڽӾ���
	public static double distce;//��������̾���
	public static String path;
	public static int price;
	static int pstart; //������յ��Ӧ���±�
	static int pend;
	static Boolean fstart;//����յ��������־λ
	static Boolean fend;
	public static GUI g = new GUI();
	
	static List<String> START = new ArrayList<>();
	static List<String> END = new ArrayList<>();  //û�õ�
	static List<Integer> JULI = new ArrayList<>();
	
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������	
		String filePath = "src/data.txt";	
		readTxtFile(filePath);
		g.search.addActionListener(new ActionListener() {			
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO �Զ����ɵķ������
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
			g.jt5.setText("������յ���ͬ��ȷ��Ҫ��3Ԫ��ȥ��");
		}
		else {
			distce=(double)Dijkstra.dijkstra(matrix, pstart,pend)[pend]/1000;
			g.jt5.setText(trpath(path));
		}
		g.setstring(distce, price(distce));
	  }else {
		  if(!fstart&&fend) {g.jt5.setText("����������������룡");g.jt3.setText(null);g.jt4.setText(null);}
		  if(fstart&&!fend) {g.jt5.setText("�յ��������������룡");g.jt3.setText(null);g.jt4.setText(null);}
		  if(!fstart&&!fend) {g.jt5.setText("����������������룡"+'\n'+"�յ��������������룡");g.jt3.setText(null);g.jt4.setText(null);}
	  }
		  

}
	
	public static String trpath(String path) {//·��վ���Ż�
		
    	int f1=0,f2=0;
		for(int k=0;k<2;k++) {
		for(int i=0;i<path.length();i++) {
			if(path.charAt(i)>='0'&&path.charAt(i)<='9')
				path=path.replace(path.charAt(i),' ').replace(" ","").trim();
		  }		
		}
		
		return path;
	}
	
	public static int price(double distce) {//����Ʊ��
		if(distce<=6)  price=3;
		else if(distce<=12) price=4;
		else if(distce<=22) price=5;
		else if(distce<=32) price=6;
		else price=(int)(7+(distce-32)/20);
		return price;
		
	}
	
    public static void Map(List<String> start,List<Integer> juli) {   //�����ڽӾ���
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
		//��λ
		   if(str1.charAt(0)>='0'&&str1.charAt(0)<='9'&&str1.charAt(1)>='0'&&str1.charAt(1)<='9') {
			if(str1.substring(2).trim().equals(str2)) {
//				System.out.println("��λ��"+str1+" "+str2);
            	matrix[i][j]=0;
				matrix[j][i]=0;
			 }
		   }
		}
	}
	}

 	public static void readTxtFile(String filePath){  //��ȡ�ļ���������LIST����
	        try {
	                String encoding="GBK";
	                File file=new File(filePath);
	                if(file.isFile() && file.exists()){ 
	                    InputStreamReader read = new InputStreamReader(new FileInputStream(file),encoding);	                    
	                    BufferedReader bufferedReader = new BufferedReader(read);
	                    String lineTxt = null;
	                    while((lineTxt = bufferedReader.readLine()) != null) {
	                    	if(lineTxt.indexOf("�D")>0)
	                    	{
	                    		START.add(lineTxt.substring(0, lineTxt.indexOf("�D")).trim());
	                    		END.add(lineTxt.substring(lineTxt.indexOf("�D")+2,lineTxt.indexOf(" ")).trim());
	                    		JULI.add(Integer.parseInt(lineTxt.substring(lineTxt.indexOf(" ")).trim()));
	                    	}
	                    }
	                    read.close();
	        }else{
	            System.out.println("�Ҳ���ָ�����ļ�");
	        }
	        } catch (Exception e) {
	            System.out.println("��ȡ�ļ����ݳ���");
	            e.printStackTrace();
	        }

	    }
  
}
 