package library;

import java.io.InputStream;
import java.io.Serializable;

public class ImageFile implements Serializable {
	private static final long serialVersionUID = 1L;
	private String imageName;
	private InputStream inputImage;
	public ImageFile(String imageName, InputStream inputImage) {
		super();
		this.imageName = imageName;
		this.inputImage = inputImage;
	}
	public String getImageName() {
		return imageName;
	}
	public InputStream getInputImage() {
		return inputImage;
	}
}
