package Metro;

import java.awt.Container;

import javax.swing.*;


public class GUI extends JFrame {
	
	JLabel start = new JLabel("起点");
	JLabel end = new JLabel("终点");
    JLabel dist = new JLabel("距离");
    JLabel price = new JLabel("票价");
    JLabel path = new JLabel("路线");
	JButton search = new JButton("查询");
	JTextField jt1 = new JTextField(20);
	JTextField jt2 = new JTextField(20);
	JTextField jt3 = new JTextField(20);
	JTextField jt4 = new JTextField(20);
	JTextArea  jt5 = new JTextArea();
	JScrollPane jsp = new JScrollPane(jt5);
	
	public GUI() {
		JFrame jf = new JFrame("北京地铁票价查询");
		Container c = jf.getContentPane();
		jf.setLayout(null);
		jf.setBounds(300, 300, 300, 450);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		start.setBounds(10, 10, 50, 50);
		end.setBounds(10, 50, 50, 50);
		dist.setBounds(10, 120, 50, 50);
		price.setBounds(130, 120, 50, 50);
		path.setBounds(10, 160, 50, 50);
		jt1.setBounds(40, 25, 200, 20);
		jt2.setBounds(40, 65, 200, 20);
		jt3.setBounds(40, 135, 80, 20);
		jt4.setBounds(160, 135, 80, 20);
		jt5.setBounds(40, 175, 200, 200);
		search.setBounds(100, 100, 80, 20);
		jt5.setLineWrap(true);
		jsp.setBounds(40, 175, 200, 200);
		
		c.add(jsp);
		c.add(start);
		c.add(end);
		c.add(dist);
		c.add(price);
		c.add(path);
		c.add(jt1);
		c.add(jt2);
		c.add(jt3);
		c.add(jt4);
		c.add(search);
		
	}
	
	public void setstring(double distce,int prices) {
		jt3.setText(distce+"Km");
		jt4.setText(prices+"元");
	}
	
}
