package postsApiTest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import core.utils.FileManager;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

public class postFileManagerTest {


	@Test
	public void fileManagerTest() {
		MultipartFile image;
		String fileName = "asdlkfjasldkf.jpg";
		
		String last = fileName.substring(fileName.lastIndexOf("."),fileName.length()); 
		
		assertThat(last, is(".jpg"));
		
	}

}
