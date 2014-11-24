package core.utils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class FileManager {
	
		public String saveFile(MultipartFile image, String uploadPath) throws IllegalStateException, IOException {
			String originName = image.getOriginalFilename();
			String lastName = originName.substring(originName.lastIndexOf("."),originName.length()); 
			UUID uuid = UUID.randomUUID();
        	String fileName = uuid.toString().replace("-", "");
        	File file = new File(uploadPath + fileName + lastName);
            image.transferTo(file);
                    
            return fileName;
        	
        }

		public boolean isValidatedFile(MultipartFile image) {
			String fileName = image.getOriginalFilename();
			
			if(image.isEmpty())
				return false;
			if(!(fileName.endsWith(".jpg")||fileName.endsWith(".jpeg")||fileName.endsWith(".png")))
				return false;
				 
			return true;
		
	}

}
