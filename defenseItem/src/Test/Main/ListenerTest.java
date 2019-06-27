package Main;

import static org.junit.Assert.*;

import java.io.File;



import org.junit.Before;
import org.junit.Test;

public class ListenerTest {
	 Listener lis=new Listener(null);
	 String path="C:\\Users\\谢月月\\Documents\\质量保证批处理改名";
	 File file=new File(path,"test.txt");
	 String filenames="test.txt";
	 int position = filenames.indexOf(".");
	 String filegs="test";
	 String filer="tests";
	 String extname="txt";
	 String number="(1)";
		
	@Before
	public void setUp() throws Exception {
	}

	
	@Test
	public void testFileFind() {
		
			lis.fileFind(file);
		
	}
	
	@Test
	public void testNocondition(){
		   
		   lis.nocondition(file);
	
	}
	
	@Test
	public void testConditons(){
		   lis.conditons(file,position,filegs);
	}
	
	
	
	
	
	
	}

