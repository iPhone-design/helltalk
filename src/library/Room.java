package library;

import java.io.Serializable;

public class Room implements Serializable {
	private static final long serialVersionUID = 1L;
	private String title;
	private String roomMasterName;
	private int headCount;
	
	public Room(String title, String roomMasterName, int headCount) {
		super();
		this.title = title;
		this.roomMasterName = roomMasterName;
		this.headCount = headCount;
	}

	public String getTitle() {
		return title;
	}

	public String getRoomMasterName() {
		return roomMasterName;
	}

	public synchronized int getHeadCount() {
		return headCount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + headCount;
		result = prime * result + ((roomMasterName == null) ? 0 : roomMasterName.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Room other = (Room) obj;
		if (headCount != other.headCount)
			return false;
		if (roomMasterName == null) {
			if (other.roomMasterName != null)
				return false;
		} else if (!roomMasterName.equals(other.roomMasterName))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Room [title=" + title + ", roomMasterName=" + roomMasterName + ", headCount=" + headCount + "]";
	}
}
