package org.usfirst.team4183.robot.constants;

public enum PS4Constants {
	SQUARE   (1), 
	TRIANGLE (2), 
	CIRCLE   (3),
	CROSS    (4), 
	L1       (5), 
	L2       (6), 
	R1       (7), 
	R2       (8),
	SHARE    (9), 
	OPTIONS  (10), 
	L_STICK  (11), 
	R_STICK  (12), 
	PS4      (13), 
	TRACKPAD (14);
	
	private int value;
	private PS4Constants(int value) {
		this.value = value;
	}

}


