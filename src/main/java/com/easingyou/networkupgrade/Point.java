package com.easingyou.networkupgrade;

public record Point(int x, int y) {

	int getCost(Point otherPoint){
		int diffX = otherPoint.x() - x();
		int diffY = otherPoint.y() - y();
		return (int) (Math.sqrt(diffX * diffX + diffY * diffY) * 100);
	}
}
