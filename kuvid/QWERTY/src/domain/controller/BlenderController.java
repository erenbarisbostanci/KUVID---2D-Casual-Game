package domain.controller;

import domain.utilities.Blender;

public class BlenderController {

	public BlenderController() {}

	public void blendAction(int consuming, int producing) {
		Blender.getBlender().blend(consuming, producing);
	}
}
