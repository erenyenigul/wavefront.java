# JavaMesh Documentation
Polygon Mesh Library for generating basic meshes. 
Here's the list of classes:

[Point](#class-point)

[Vector](#class-vector)

[Face]

[Surface]

[Creation Operations]

<h2 id="class-point">Point</h2>

Point is a **_immutable_** class which can be used to define _Vectors_, _Faces_ and many more. 
To define a _Point_, 
```Java
Point p = new Point(double X, double Y, double Z);
```
If you need origin, you can directly call the static variable _ORIGIN_,
```Java
Point origin = new Point(0,0,0);
//But instead,
Point origin = Point.ORIGIN;
```

### Methods

#### getX(),getY(),getZ() 
##### _returns **double**_
```Java
double xCoordMyPoint = myPoint.getX(); 
```
---
#### distanceToPoint(Point p) 
##### _returns **double**_
```Java
double distance = myPoint.distanceToPoint(otherPoint);
```
---
#### shift(Vector v) 
##### _returns **Point**_
```Java
Point shiftedPoint = myPoint.shift(myVector);
```
> Do not forget that _Point_ is an immutable class. Methods like this does not affect the point itself, but outputs new instances!

___


<h2 id="class-vector">Vector</h2>

Vectors allow you to do many different operations. _Vector_ is an immutable class. 
To define a _Vector_, you can use any of the ways below:
```
Vector v = new Vector(Point head);
//or
Vector v = new Vector(Point tail, Point head);
//or 
Vector v = new Vector(double headX, double headY, double headZ);
```
> _Vector_ s in JavaMesh do not have the instance **tail**. Constructor with **tail** (which is the second example above), forms a new **head**, and creates a vector which is a shifted-to-origin version of the vector with user-given **tail** and **head**

### Methods

#### getHead() 
##### _returns **Point**_
---
#### length()
##### _returns **double**_
---
#### getUnitVector()
##### _returns **Vector**_
Returns a unit vector which has the same direction as the vector itself.

---
#### cross(Vector v)
##### _returns **Vector**_
Get the cross product of the vector with **v** .

---
#### dot(Vector v)
##### _returns **double**_
Get the dot product of the vector with **v** .

---
#### degreeBetween(Vector v)
##### _returns **double**_
>Return value is radians.
---
#### add(Vector v), substract(Vector v)
##### _returns **Vector**_
---
#### scale(double scaleFactor)
##### _returns **Vector**_
Returns a new vector which is a scaled version of the original.

---
#### rotate(double angle, Vector axisOfRotation)
##### _returns **Vector**_
>Angle must be in radians.
___
#### isZeroVector()
##### _returns **boolean**_

