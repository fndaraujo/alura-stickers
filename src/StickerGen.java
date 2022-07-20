import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class StickerGen {
    public void create(InputStream inputStream, String fileName) throws Exception {
        BufferedImage imgSource = ImageIO.read(inputStream);

        int width = imgSource.getWidth();
        int height = imgSource.getHeight();

        int newHeight = height + 200;

        BufferedImage imgNew = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

        Graphics2D img = (Graphics2D) imgNew.getGraphics();
        img.drawImage(imgSource, 0, 0, null);

        img.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 64));
        img.setColor(Color.YELLOW);
        img.drawString("TOPZERA", 0, newHeight - 100);

        ImageIO.write(imgNew, "png", new File(fileName));
    }
}
