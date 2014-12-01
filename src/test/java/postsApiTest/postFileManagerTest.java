package postsApiTest;

import org.junit.Test;
import org.springframework.web.multipart.MultipartFile;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PostFileManagerTest {


	@Test
	public void fileManagerTest() {
		MultipartFile image;
		String fileName = "asdlkfjasldkf.jpg";
		
		String last = fileName.substring(fileName.lastIndexOf("."),fileName.length()); 
		
		assertThat(last, is(".jpg"));
		
	}

}
