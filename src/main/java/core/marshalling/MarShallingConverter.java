package core.marshalling;

import org.springframework.oxm.Unmarshaller;

public class MarShallingConverter {
	private Unmarshaller unmarshaller;

	public MarShallingConverter(Unmarshaller unmarshaller) {
		this.unmarshaller = unmarshaller;
	}

	public Unmarshaller getUnmarshaller() {
		return unmarshaller;
	}

	public void setUnmarshaller(Unmarshaller unmarshaller) {
		this.unmarshaller = unmarshaller;
	}

	
}
