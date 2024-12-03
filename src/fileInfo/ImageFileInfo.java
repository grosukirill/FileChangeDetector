package fileInfo;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageFileInfo extends FileInfo {
    private int width;
    private int height;

    public ImageFileInfo(String filePath) throws IOException {
        super(filePath);
        BufferedImage bimg = ImageIO.read(new File(getFilePath()));
        this.width = bimg.getWidth();
        this.height = bimg.getHeight();
    }

    @Override
    public String getFileInfo() {
        return "Image width is " + this.width + " and height is " + this.height;
    }
}
