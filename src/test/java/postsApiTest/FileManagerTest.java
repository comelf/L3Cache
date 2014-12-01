package postsApiTest;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;

import core.utils.FileManager;
import static org.junit.Assert.*;

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
