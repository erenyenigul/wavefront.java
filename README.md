# JavaMesh Documentation
Polygon Mesh Library for generating basic meshes. 
Here's the list of classes:

[Point](#class-point)
[Vector](#class-vector)

<h2 id="class-point">Point</h2>
Point is a **_immutable_** class which can be used to define _Vectors_, _Faces_ and many more. 
To define a _Point_, 
```Java
Point p = new Point(X,Y,Z);
```
If you need origin, you can directly call the static variable _ORIGIN_,
```Java
Point origin = new Point(0,0,0);
//But instead,
Point origin = Point.ORIGIN;
```
### Methods
#### getX(),getY(),getZ() 
_returns *double*_
```Java
double xCoordMyPoint = myPoint.getX(); 
```
#### distanceToPoint(Point p) 
_returns *double*_
```Java
double distance = myPoint.distanceToPoint(otherPoint);
```
#### shift(Vector v) 
_returns *Point*_
```Java
Point shiftedPoint = myPoint.shift(myVector);
```
! Do not forget that _Point_ is an immutable class. Methods like this does not affect the point itself, but outputs new instances !

## Vector {#class-vector}

To define a vector 

```
Vector v = new Vector();
```
