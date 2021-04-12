package library;

public class Room {
	private String title;
	private String roomMasterName;
	private int amount;
	
	public Room(String title, String roomMasterName, int amount) {
		super();
		this.title = title;
		this.roomMasterName = roomMasterName;
		this.amount = amount;
	}

	public synchronized String getTitle() {
		return title;
	}

	public synchronized void setTitle(String title) {
		this.title = title;
	}

	public synchronized String getRoomMasterName() {
		return roomMasterName;
	}

	public synchronized void setRoomMasterName(String roomMasterName) {
		this.roomMasterName = roomMasterName;
	}

	public synchronized int getAmount() {
		return amount;
	}

	public synchronized void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		result = prime * result + ((roomMasterName == null) ? 0 : roomMasterName.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Room))
			return false;
		Room other = (Room) obj;
		if (amount != other.amount)
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
		return "Room [title=" + title + ", roomMasterName=" + roomMasterName + ", amount=" + amount + "]";
	}
}
