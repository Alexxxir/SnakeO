public class Wall extends ImpenetrableObject{
	
	@Override
	public String toString() {return "Wall";}
	
	Wall(Coordinate coordinate, Field field) {
		super(coordinate, field);
	}
}
