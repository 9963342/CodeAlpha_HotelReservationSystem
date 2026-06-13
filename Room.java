public class Room {
    int roomNo;
    String category;
    double price;
    boolean available;

    public Room(int roomNo, String category, double price) {
        this.roomNo = roomNo;
        this.category = category;
        this.price = price;
        this.available = true;
    }
}