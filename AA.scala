import java.io.{File, PrintWriter}
import javax.imageio.ImageIO
object AA {
  def main(args: Array[String]): Unit = {
    val txt_path = "./rsc/ASCII-art.txt"
    if(new File(txt_path) != null) new File(txt_path).delete()
    val art = new File(txt_path)
    val f = new File("./rsc/revolver.jpg")
    val img = ImageIO.read(f)
    val width = img.getWidth
    val height = img.getHeight
    println("横ピクセル:" + width)
    println("縦ピクセル:" + height)
    val txt = new PrintWriter(art) //txtファイルを呼び出す
    val d = 2
    val t = height / d
    print("|")
    for(i <- 0 to t-3)print("-")
    println("|")
    var y = 0
    while (y < height) {
      var x = 0
      while (x < width) {
        val p = img.getRGB(x, y) // x,y座標のRGB要素を抽出する
        val r = (p >> 16) & 0xff
        val g = (p >> 8) & 0xff
        val b = p & 0xff
        val ave = (r + g + b) / 3
        val l = {
          if (ave < 32) "@"
          else if (ave >= 32 && ave < 64) {
            "$"
          } else if (ave >= 64 && ave < 96) {
            "#"
          } else if (ave >= 96 && ave < 128) {
            "*"
          } else if (ave >= 128 && ave < 160) {
            "="
          } else if (ave >= 160 && ave < 192) {
            "~"
          } else if (ave >= 192 && ave < 224) {
            ","
          } else {
            "."
          }
        }
        txt.write(s"$l")
        x += d
      }
      txt.write("\n")
      y += d
      print("|")
      Thread.sleep(5)
    }
    txt.close()
    println(" SUCCESS")
  }
}
