
# Wallpaper calculator

# Task

A renovation company needs to hang new wallpaper in a bunch of rooms. They need to cover even the floors and ceilings with it.

They run out of wallpaper so they need to submit an order for more. Knowing the dimensions (length l, width w, and height h) of each room, they want to order only as much as they need.

Every room is a rectangular prism, which makes calculations easier: the surface area of room is 2\*l\*w + 2\*w\*h + 2\*h\*l.

The company also need a little extra wallpaper for each room: the area of the smallest side.

**All numbers in the rooms list are in feet. Write application that will:**
- take the list of room dimensions as input (use provided input file as an example)
- calculate number of total square feet of wallpaper the company should order for all rooms from input
- list all rooms from input that have a cubic shape (order by total needed wallpaper descending)
- list all rooms from input that are appearing more than once (order is irrelevant)

# Run and build
- Build: mvn clean install
- Run: java -jar .\target\renovationProject-1.0-SNAPSHOT.jar
- Run and build: mvn compile exec:java
