public class Apple extends PenetrableObject {
	
	@Override
	public String toString() {return "Apple";}
	
    Apple(Coordinate coordinate, Field field) {
        super(coordinate, field);
    }
}