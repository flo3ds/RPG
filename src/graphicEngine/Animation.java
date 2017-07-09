package graphicEngine;

import java.util.ArrayList;

import org.lwjgl.Sys;

/**
 * A utility to hold and render animations
 *
 * @author kevin
 * @author DeX (speed updates)
 */
public class Animation {
	/** The list of frames to render in this animation */
	private ArrayList<TextureRegion> frames = new ArrayList<TextureRegion>();

	private int currentFrame = 0;
	private int delay = 0;
	private int currentDelay = 0;
	private int frame = 0;

	/**
	 * Create an empty animation
	 */
	public Animation(Texture frames, int size, int nb, int y, int delay) {

		this.delay = delay;
		currentDelay = delay;
		frame = nb;

		for (int i = 0; i < nb; i++)
			this.frames.add(new TextureRegion(frames, size * i, y * size, size, size));

	}

	public void update() {
		// System.out.println("delay " + currentDelay);
		if (currentDelay > 0) {
			currentDelay--;
		} else {
			currentDelay = delay;
			incAnim();
		}

	}

	public void incAnim() {
		// System.out.println("Inc ");
		currentFrame++;
		if (currentFrame >= frame)
			currentFrame = 0;
	}

	public TextureRegion getTexture() {
		// System.out.println("Frame " + currentFrame);
		if (delay != 0)
			update();
		return this.frames.get(currentFrame);
	}

}
