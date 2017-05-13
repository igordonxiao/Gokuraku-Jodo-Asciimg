package io.korhner.asciimg

import io.korhner.asciimg.image.AsciiImgCache
import io.korhner.asciimg.image.character_fit_strategy.ColorSquareErrorFitStrategy
import io.korhner.asciimg.image.converter.AsciiToImageConverter
import java.awt.Font
import java.awt.Font.BOLD
import java.io.File
import javax.imageio.ImageIO


fun main(args: Array<String>) {
    // 1. 安装ffmpeg, 切割视频为图片
    // ffmpeg -i "jljt.mp4" -q:v 2 -y -f image2  -an   img/%d.jpeg

    // 2. 将图片转为AsciiImg
    val cache = AsciiImgCache.create(Font("Courier", BOLD, 6))
    val imageConverter = AsciiToImageConverter(cache, ColorSquareErrorFitStrategy())
    val fileList = File("/Users/gordon/Documents/javaproject/asciimg/src/webapp/img").listFiles()
    fileList.forEach {
        val portraitImage = ImageIO.read(it)
        ImageIO.write(imageConverter.convertImage(portraitImage), "png", File("/Users/gordon/Documents/javaproject/asciimg/src/webapp/img2/${it.nameWithoutExtension}.png"))

    }

    // 3. 请参见webapp下的index.html
}

