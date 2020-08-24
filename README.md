# JavaMesh Documentation
Polygon Mesh Library for generating basic meshes.

## 1. Point
Point is a **_immutable_** class which can be used to define _Vectors_,_Faces_ and many more. 
To define a _Point_, 
```
Point p = new Point(X,Y,Z);
```
If you need origin, you can directly call the static variable _ORIGIN_,
```
Point origin = new Point(0,0,0);
//But instead,
Point origin = Point.ORIGIN;
```
### 1.1 Methods
#### getX(),getY(),getZ()
_returns double_
``` 
double xCoordMyPoint = myPoint.getX(); 
```
#### distanceToPoint(Point p)
_returns double_
```
double distance = myPoint.distanceToPoint(otherPoint);
```
#### shift(Vector v)
_returns Point_
```
Point shiftedPoint = myPoint.shift(myVector);
```
! Do not forget that _Point_ is an immutable class. Methods like this does not affect the point itself, but outputs new instances !

## Vector

To define a vector 

```
Vector v = new Vector();
```
