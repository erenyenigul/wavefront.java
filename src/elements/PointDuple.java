package elements;

public class PointDuple extends GeoElement{
	
	public Point first;
	public Point second;
	
	public PointDuple(Point first, Point second) {
		this.first = first;
		this.second = second;
	}
	
	
	@Override
	public boolean equals(Object o) {
		if( !(o instanceof PointDuple)) return false;
		PointDuple d = (PointDuple) o;
		return (d.first.equals(this.first) && d.second.equals(this.second))
				|| (d.first.equals(this.second) && d.second.equals(this.first));
	}
	@Override
	public int hashCode() {
		int result = 0;
		
		result = (int) ( first.getX() + second.getX() + 9);
		result = (int) ( 31*result + first.getY() + second.getY() + 9);
		result = (int) ( 31*result + first.getZ() + second.getZ() + 9);
		
		return result;
	}
	
	public String toString() {
		return "[" + first +"," + second+"]";
	}

}
