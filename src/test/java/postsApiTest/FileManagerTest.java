package postsApiTest;

import org.junit.Before;
import org.junit.Test;
import org.l3cache.support.FileManager;
import org.springframework.mock.web.MockMultipartFile;

public class FileManagerTest {

	FileManager fm;
	@Before
	public void setUp() {
		fm = new FileManager();
	}
	
	
	@Test
	public void fileUploadTest() {
		
		MockMultipartFile file = new MockMultipartFile("file", "testFile.jpg", "img", new byte[]{1,2,3});
		
		
		
	}

}
