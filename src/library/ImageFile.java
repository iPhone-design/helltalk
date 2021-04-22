package library;

import java.io.Serializable;

public class ImageFile implements Serializable {
	private static final long serialVersionUID = 1L;
	private String imageName;
	private byte[] imageByte;
	public ImageFile(String imageName, byte[] imageByte) {
		super();
		this.imageName = imageName;
		this.imageByte = imageByte;
	}
	public String getImageName() {
		return imageName;
	}
	
	public byte[] getImageByte() {
		return imageByte;
	}	
}
