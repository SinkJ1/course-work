package test2;

import java.awt.MouseInfo;
import java.awt.Robot;
import java.io.Serializable;

public class CoursourInfo implements Serializable {
		
	public int mouzeX() {
		return MouseInfo.getPointerInfo().getLocation().x;
	}
	
	public int mouzeY() {
		return MouseInfo.getPointerInfo().getLocation().y;
	}
	
	
	
}