package layout;

import java.io.IOException;

import graphicEngine.SpriteBatch;
import graphicEngine.Texture;
import graphicEngine.Util;

public class Layout_sac extends Layout {

	public Layout_sac() throws IOException {
		super(new Texture(Util.getResource("res/layout/sac.png")));
	}

	public void draw(SpriteBatch batch, int x, int y) {
		x -= 320;
		y -= 240;
		draw_extends(batch, x, y);
	}

}
