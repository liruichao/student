package Main;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class MyFrame{
	Vector<JCheckBox> vj = new Vector<JCheckBox>();
	Listener l;
	JFrame f ;//f
	JScrollPane scrollP;
	JTextField path;//文件路径
	JTextField search;//文件搜索
	JTextField fName;//文件名
	JTextField extName;//扩展名
	JPanel p;//jp1
	JPanel fList;//文件列表
	JLabel pathN;//文件路径
	JLabel searchN;//文件搜索
	JCheckBox fNameN;//文件名
	JCheckBox extNameN;//扩展名
	JCheckBox id;//编号
	JButton check;//查询
	JButton Rname;//改名
	JButton browse;//浏览
	
	public void addFils(JCheckBox file){
		vj.addElement(file);
		fList.add(file);
		fList.doLayout();
		fList.setSize(300, 800);
		
	}
	public MyFrame() {
		l = new Listener(this);
		f = new JFrame();
		path = new JTextField();
		search = new JTextField(null);
		fName = new JTextField();
		extName = new JTextField();
		p = new JPanel();
		fList = new JPanel();
		pathN = new JLabel("文件路径");
		searchN = new JLabel("文件搜索");
		check = new JButton("查询   ");
		Rname = new JButton("重命名");
		browse = new JButton("浏览");
		fNameN = new JCheckBox("文件名");
		extNameN = new JCheckBox("扩展名");
		id = new JCheckBox("编号");
		scrollP = new JScrollPane();
		//窗口关闭结束程序
		f.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		f.setResizable(false);//窗口不可改变大小
		
		
		f.setLayout(new GridLayout(1,1));
		f.setSize(400, 500);
		
		f.setLocation(400, 100);
		f.setTitle("文件批量改名");
		f.add(p);
		p.setLayout(null);
		p.add(pathN);
		pathN.setBounds(10,5, 80, 40);
		p.add(path);
		path.setBounds(100,10, 160, 30);
		p.add(browse);
		browse.setBounds(280,10, 80, 30);
		p.add(searchN);
		searchN.setBounds(10,40, 80, 50);
		p.add(check);
		check.setBounds(280,50,80, 30);
		p.add(search);
		search.setBounds(100,50,160, 30);
		
		p.add(fNameN);
		fNameN.setBounds(10, 100, 80, 30);
		p.add(extNameN);
		extNameN.setBounds(10, 140, 80, 30);
		extNameN.setEnabled(false);
		p.add(id);
		id.setBounds(10, 180, 80, 30);
		id.setEnabled(false);
		
		p.add(Rname);
		Rname.setBounds(280, 100,80, 30);
		p.add(fName);
		fName.setBounds(100, 100,130, 30);
		p.add(extName);
		extName.setBounds(100, 140,130, 30);
		
		check.addActionListener(l);
		Rname.addActionListener(l);
		browse.addActionListener(l);
		fNameN.addActionListener(l);
		
		p.add(scrollP);
		//设置panel的外观和布局
		fList.setBorder(BorderFactory.createLineBorder(Color.black));  
        fList.setLayout(new BoxLayout(fList,BoxLayout.Y_AXIS));

		scrollP.setBounds(10, 240, 360, 200);
		scrollP.setViewportView(fList);
		
		pathN.setVisible(true);
		f.setVisible(true);
	}
	public static void main(String []args){
		MyFrame f = new MyFrame();
	}
	
}
