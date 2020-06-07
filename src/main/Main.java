/**
 * 
 */
package main;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import gettable.Getrule;





/**
 * @author 柴
 *
 */

class guanli{
	String re="";
	String cunzai;
	String table="";
}

class rule{
	List<Getrule> rule=new ArrayList();
	String rulestr;
}
class Tcase{
	String tcase="";
}

public class Main extends JFrame {
	
	private JButton start; 
	private JButton exce;
	private JTextArea Text1;
	private JLabel label1;
	private JLabel label2;
	private JTextArea Text2;
	private JLabel label3;
	private List<Getrule> listrule;
	private Map<Integer, Integer> map; 
	private String rulestr;
	private Boolean over;
	private String getfile;
	
	public void MainPage() {              //进行页面格式设置
		
		this.setTitle("正交表测试用例生成");
		this.setBounds(700, 100, 1000, 900); 
		
		JPanel panel = new JPanel();
		this.add(panel);
		panel.setLayout(null);
		
		label1 = new JLabel("输入因子跟水平：(因子数小于等于10)使用冒号分开因子和水平，使用逗号分开水平与水平");
		label1.setFont(new Font("宋体", Font.ITALIC, 16));
		label1.setBounds(10,10,800,20);
		panel.add(label1);
		
		start = new JButton("测试用例生成");
		start.setBounds(750, 100, 150, 30);
		panel.add(start);
		
		exce = new JButton("导出测试用例");
		exce.setBounds(750, 500, 150, 30);
		panel.add(exce);
		
		Text1 = new JTextArea();
		Text1.setLineWrap(true);
		Text1.setFont(new Font("宋体", 1, 16));
		JScrollPane scroll = new JScrollPane(Text1);
		scroll.setBounds(10, 40, 700, 300);
		panel.add(scroll);
		
		label2 = new JLabel("生成的测试用例：");
		label2.setFont(new Font("宋体", Font.ITALIC, 16));
		label2.setBounds(10,380,500,20);
		panel.add(label2);
		
		label3 = new JLabel("正交表对应的测试用例");
		label3.setFont(new Font("宋体", Font.ITALIC, 16));
		label3.setBounds(250,380,500,20);
		panel.add(label3);
		
		Text2 = new JTextArea();
		Text2.setLineWrap(true);
		Text2.setFont(new Font("宋体", 1, 16));
		JScrollPane scroll1 = new JScrollPane(Text2);
		scroll1.setBounds(10, 410, 700, 300);
		panel.add(scroll1);
		
		click();
		Wexcel();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Main w= new Main();
      w.MainPage();
	}

	public void click() {           //生成测试用例按钮设置
	start.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			String input = Text1.getText();
			if(!"".equals(input)) { 
				
				try {
					
					if(input.contains(":")&&input.contains(",")||input.contains("：")&&input.contains("，")) {
						
						    guanli re1=new guanli();
							re1.cunzai = find(analy(input).rulestr).cunzai;
							re1.re = find(analy(input).rulestr).re;
							re1.table=find(analy(input).rulestr).table;
						if(re1.cunzai.equals("Y")) {
							label3.setText(re1.re);	
							Tcase T1=new Tcase();
							T1.tcase=create(analy(input).rule,re1.table ).tcase;
							getfile=T1.tcase;
							Text2.setText(T1.tcase);
						}
						else {
							label3.setText("正交表中没有这个规则");
						}
					}
					else {
						label3.setText("没有按照这个格式输入");
					}
					   
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			
				}
			}
		
		});
	}
	
	public void Wexcel() {        //导出按钮
		exce.addActionListener(new ActionListener() {
			
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 自动生成的方法存根
			try {
				FileWriter file1=new FileWriter("D:\\TestFile.txt");
				Tcase T2=new Tcase();
				
					file1.write(getfile);
					
				file1.flush();
				file1.close();
				JOptionPane.showMessageDialog(null, "导出成功,文件在你的D盘","提示" ,JOptionPane.PLAIN_MESSAGE);
			} catch (IOException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
			
			
		}
	
	
	});
	}
	
	public guanli find(String str) throws IOException {      //用于找到正交表中测试用例的方法
		guanli RE=new guanli();
		RE.cunzai="N";
		BufferedReader file = new BufferedReader(new FileReader("textline.txt"));
		String sstr=str.toString()+"    n=";
		String aa=file.readLine();
		List<String> list=new ArrayList<String>();
		while(aa!=null) {
			list.add(aa);
			aa=file.readLine();
		}
		int i;
//		for(String o:list) {
//			if(o.contains(sstr)) {
//				RE.re=o;
//				RE.cunzai="Y";
//				String []temp=RE.re.split("=");
//				int b=Integer.valueOf(temp[1]); 
//			    for(i=0;i<b;i++) {
//			    	RE.table=RE.table+list.get(i+1);
//			    	if(i<b) {
//			    		RE.table=RE.table+"\n";
//			    	}
//			    }
//				break;
//			}	
//			
//		}
		String temp1;
		for(int j=0;j<list.size();j++) {
			temp1=list.get(j);
			if(temp1.contains(sstr)) {
				RE.re=temp1;
				RE.cunzai="Y";
				String []temp2=RE.re.split("=");
				int b1=Integer.valueOf(temp2[1]);
				
				for(int k=j,count=0;count<b1;k++,count++) {
					RE.table=RE.table+list.get(k+1);
					if(count<b1-1) {
						RE.table=RE.table+"\n";
					}
				}
				break;
			}
		}
	
     //label3.setText(RE.re);
		file.close();
		return RE;
	}	
	
	
public rule analy(String str) {        //用于将输入的字符串进行规则化的方法
	rule r1=new rule();	
	String[] a = str.split("\n");       
		r1.rule = new ArrayList<Getrule>();
		for (String string1 : a) {
			String[] b = string1.split(":|,|：|，");     //基于：或者，进行分割
			int i = 0;
			Getrule rule = new Getrule();
			List<String> list1 = new ArrayList<String>();
			for (String string2 : b) {
				if (i == 0) {
					rule.setName(string2);
				} else {
					list1.add(string2);
				}
				i++;
			}
			rule.setList(list1);
			r1.rule.add(rule);
		}
		Collections.sort(r1.rule);
		map = new HashMap<>();
		for (Getrule r : r1.rule) {
			int key = r.getN();
			if (map.containsKey(key)) {
				map.put(key, map.get(key) + 1);
			} else {
				map.put(key, 1);
			}
		}
		r1.rulestr = "";
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			String string3 = entry.getKey() + "^" + entry.getValue();
			r1.rulestr += string3 + " ";
			if (entry.getKey() > 10) {    //限制因子数，10因子左右时就会报错数组下标越界
				over = true;
			}
		}
		return r1;
	}
	
	
	
public Tcase create(List<Getrule> list1,String table) {     //创建测试用例
		Tcase T=new Tcase();
		String [] arr=table.split("\n");
		for(String str:arr) {
			char []brr=str.toCharArray();
			for(int i=0;i<list1.size();i++) {
				Getrule r=list1.get(i);
				T.tcase+=r.getList().get(brr[i]-'0')+" ";
				
			}
			T.tcase+="\n";
		}
		return T;
	}
	
	
	
}
