package Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;


public class Listener implements ActionListener{
	boolean isexist = false;
	//搜索文件的名称
	static String filenames = null;
	String searchfield;
	MyFrame f;
	public Listener(MyFrame f){
		this.f = f;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//文件夹路径
		String folderpath;
		//获取文件夹路径
		folderpath = f.path.getText();
		//得到搜索字段
		searchfield = f.search.getText();
		
		File file = new File(folderpath);
		
		//处理“浏览”按钮
		if(e.getSource()==f.browse){
			String path = null; 
			// 创建文件选择器
			JFileChooser chooser = new JFileChooser();
             // 设置只选择文件夹
             chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            //设置对话框标题  
             chooser.setDialogTitle("请选择路径");  
          if (JFileChooser.APPROVE_OPTION == chooser.showOpenDialog(null)) {//用户点击了确定  
             path = chooser.getSelectedFile().getAbsolutePath();//取得路径选择  
           }  
           //将路径传输到输入框
            f.path.setText(path + "");
		} 
		
		//处理“查询”按钮
		if(e.getSource()==f.check){
			
			f.fList.removeAll();
			f.vj.removeAllElements();
			
			//开始搜索，为空直接搜索，不为空按搜索字段搜索文件
			if(searchfield.equals("")){
				filenames = null;
				isexist = true;
			}else{
				filenames = searchfield;
				isexist = false;
			}
			
			//遍历搜索文件，存在输出符合条件的所有文件，不存在则弹出反馈窗口
			//调用遍历文件方法
			fileFind(file);
			if(!isexist){
				f.fList.removeAll();
				f.vj.removeAllElements();
				f.fList.repaint();
				javax.swing.JOptionPane.showMessageDialog(f.fList, "未找到要搜索的文件");	
			}
		}
		
		//处理“重命名”按钮
		if(e.getSource()==f.Rname){
			
			int num = 1;
			
			//文件复选框被选中
			if(f.fNameN.isSelected()){
				String newname;
				
				//扩展名复选框没被选中
				if(!f.extNameN.isSelected()){
					
					//编号和文件复选框都被选中
					if(f.fNameN.isSelected()&&f.id.isSelected()){
						//获取编号与新文件名
						for(int count=0;count<f.vj.size();count++){	
							
							if(f.vj.get(count).isSelected()){
								
								newname = f.fName.getText();
								ReName(new File(f.vj.get(count).getText()),newname,""+num,"");
								
								num++;
							}
						}
						
					}
					
					//编号复选框没被选中，文件复选框被选中
					if(f.fNameN.isSelected()&&!f.id.isSelected()){
						//获取编号与新文件名
						for(int count=0;count<f.vj.size();count++){
							
							if(f.vj.get(count).isSelected()){
								
								newname = f.fName.getText();
								if(newname.equals("")){
									javax.swing.JOptionPane.showMessageDialog(f.fList, "请输入文件名称");
								}else{
								
									ReName(new File(f.vj.get(count).getText()),newname,"","");
								}
							}
						}

					}
				//扩展名复选框被选中
				}else{
					String extname;
					
					////如果编号和文件复选框都被选中
					if(f.fNameN.isSelected()&&f.id.isSelected()){
						//得到起始编号
						
						extname = f.extName.getText();
						if(extname.equals("")){
							javax.swing.JOptionPane.showMessageDialog(f.fList, "请输入文件扩展名");
						}else{
							for(int count=0;count<f.vj.size();count++){			
								if(f.vj.get(count).isSelected()){
									//filer得到要改名后的文件名
									newname = f.fName.getText();
									if(newname.equals("")){
										javax.swing.JOptionPane.showMessageDialog(f.fList, "请输入文件名称");
									}else{
										//调用重命名函数
										ReName(new File(f.vj.get(count).getText()),newname,""+num,extname);
										num++;
									}
								}
							}
						}
						

					}
					//如果编号复选框没被选中和文件复选框被选中
					if(f.fNameN.isSelected()&&!f.id.isSelected()){
						extname = f.extName.getText();
						if(extname.equals("")){
							javax.swing.JOptionPane.showMessageDialog(f.fList, "请输入文件扩展名");
						}else{
							//获取编号与新文件名
							for(int count=0;count<f.vj.size();count++){
								
								if(f.vj.get(count).isSelected()){
									newname = f.fName.getText();
									if(newname.equals("")){
										javax.swing.JOptionPane.showMessageDialog(f.fList, "请输入文件名称");
									}else{
										ReName(new File(f.vj.get(count).getText()),newname,"",extname);
									}
								}
							}
						}

					}
				}
			}
		}
		
		
		//处理复选框明灭
		if(e.getSource()==f.fNameN){
			
			if(f.fNameN.isSelected()){
				
				f.extNameN.setEnabled(true);
				f.id.setEnabled(true);
				
			}else{
				
				f.extNameN.setSelected(false);
				f.extNameN.setEnabled(false);
				f.id.setEnabled(false);
				f.id.setSelected(false);
				f.fName.setText("");
				f.extName.setText("");
				
			}
		}
		
		

	}
	
	//搜索文件夹所有文件
		public void fileFind(File file){
			
			int position;
			
			if(filenames==null){
				nocondition(file);
			}else{
				position = filenames.indexOf(".");
				conditons(file, position, filenames);
			}
		}
	
	
	//无条件遍历类
	public void nocondition(File file){
		
		File []nameList;
		JCheckBox check1 = new JCheckBox();
		//得到当前文件夹的所有文件
		nameList = file.listFiles();
		
		if(nameList!=null&&nameList.length>0) {
			for(int j=0;j<(nameList.length);j++){
					check1 = new JCheckBox(""+nameList[j].toString());
					f.addFils(check1);
			}
		}
		
		
	}
	
	
	//有条件遍历类
	public void conditons(File file,int position,String filegs){
		//创建一个过滤处理类
		DirFilter dirfilter = new DirFilter();
		File []nameList;
		//.的在字符串的位置
		int  position1;     
		String newfile;
		JCheckBox check1 = new JCheckBox();
		
		//处理文件名前缀与后缀的过滤，-1为前缀过滤，否之为后缀过滤
		if(position==-1){ 
			nameList = file.listFiles();
			if(nameList!=null&&nameList.length>0) {
				for(int j=0;j<(nameList.length);j++){
					if(!nameList[j].isDirectory()){
						
							position1 = nameList[j].toString().lastIndexOf("\\");
							newfile= nameList[j].toString().substring(position1+1);
							
							if(newfile.indexOf(filegs)!=-1){
								check1 = new JCheckBox(""+nameList[j].toString());
								f.addFils(check1);
								isexist = true;
							}
					}
				}
			}
		}else{
			int position2;
			position2 = filegs.indexOf(".");
			
			if(position2==0){
				dirfilter.setFilegs(filegs);
				nameList = file.listFiles(dirfilter);
				
				if(nameList!=null&&nameList.length>0) {
					for(int j=0;j<(nameList.length);j++){
						
							check1 = new JCheckBox(""+nameList[j].toString());
							f.addFils(check1);
							isexist = true;
						
					}
				}
			}else{
				nameList = file.listFiles();
				if(nameList!=null&&nameList.length>0) {
					for(int j=0;j<(nameList.length);j++){
						if(!nameList[j].isDirectory()){
							
								position1 = nameList[j].toString().lastIndexOf("\\");
								newfile= nameList[j].toString().substring(position1+1);
								
								if(newfile.equals(filegs)){
									check1 = new JCheckBox(""+nameList[j].toString());
									f.addFils(check1);
									isexist = true;
									
								}
						}
					}
				}
			}
		}
	}
	

	//文件过滤处理类
	class DirFilter implements FilenameFilter{
		String filegs;
		
		public void setFilegs(String filegs) {
			this.filegs = filegs;
		}

		@Override
		public boolean accept(File dir, String name) {
			
			return name.endsWith(filegs);
		}
		
	}
	
	
	//文件重命名类
	public static void ReName(File file,String filer,String number,String extname){
		
		File newFile;
		String oldfilename;
		oldfilename = file.toString();
		int positionx;
		int positiond;
		String fileadress;
		String extnames;
		
		//保存\\的位置和.的位置
		positionx = file.toString().lastIndexOf("\\");
		positiond =oldfilename.indexOf(".");
		
		//保存后缀名和文件地址
		if(extname.equals("")){
			if(file.toString().indexOf(".")==-1){
				fileadress = oldfilename.substring(0,positionx);
				extnames="";
			}else{
				extnames = oldfilename.substring(positiond);
				fileadress = oldfilename.substring(0,positionx);
			}
			
		
			if(number.equals("")){
				newFile = new File(fileadress+"\\"+filer+number+extnames);
			}else{
				newFile = new File(fileadress+"\\"+filer+"("+number+")"+extnames);
			}
			if(newFile.exists()){
				javax.swing.JOptionPane.showMessageDialog(null,"文件已存在，不能重命名为该名称");
			}else{
				file.renameTo(newFile);
				javax.swing.JOptionPane.showMessageDialog(null,"文件命修改成功！");
			}
		}else{
			fileadress = oldfilename.substring(0,positionx);
			if(number.equals("")){
				newFile = new File(fileadress+"\\"+filer+number+extname);
			}else{
				newFile = new File(fileadress+"\\"+filer+"("+number+")"+extname);
			}
			if(newFile.exists()){
				javax.swing.JOptionPane.showMessageDialog(null,"文件已存在，请重新命名");
			}else{
				file.renameTo(newFile);
				javax.swing.JOptionPane.showMessageDialog(null,"文件名修改成功！");
			}
		}
	}
		
}


	



