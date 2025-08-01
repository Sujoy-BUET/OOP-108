@Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Point)) {
            return false;
        }
        Point p = (Point) o;
        if (p.x == this.x && p.y == this.y)
            return true;
        return false;
    }