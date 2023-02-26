import java.awt.image.BufferedImage
import java.io.{File, FileWriter, PrintWriter}
import java.util.Scanner
import javax.imageio.ImageIO
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Random

object Image {
  private var time = 0L
  def main(args: Array[String]): Unit = {
    val sc = new Scanner(System.in)
    print("HOW MANY FILES ? : ")
    val max = 50
    val st = System.nanoTime()
    Future {
      for (i <- 0 until max) {
        Thread.sleep(1000)
        mkpic(i)
      }
    }

    println("SUCCESS")
    val en = System.nanoTime()
    print("delete? y/n : ")
    val str = sc.nextLine()

    val dir = new File("./rsc/pic/")
    val fd = dir.listFiles
    if(str == "y"){
      try {
        for (i <- 0 until  fd.length) {
          fd(i).delete()
        }
      }catch {
        case exception: Exception => exception.printStackTrace()
      }

      println("ALL DELETE!")
    }
    println("TIME TAKEN IS : " + time/1000000 + "ms")
    println("TIME TAKEN IS : " + (en - st)/1000000 + "ms")


  }
  def mkpic(z :Int): Unit = {
    val random = new Random()
    val r1 = random.nextInt(255) + 0.0 ; val g1 = random.nextInt(255) + 0.0 ; val b1 = random.nextInt(255) + 0.0
    val r2 = random.nextInt(255) + 0.0 ; val g2 = random.nextInt(255) + 0.0 ; val b2 = random.nextInt(255) + 0.0
    val width = 500 ; val height = 500
    //println("FILE NAME : ")
    //val name = sc.nextLine()
    val name: String = "SamplePicture" + z
    val f = new File("./rsc/pic/" + name + ".png")
    val start = System.nanoTime
    val img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
    var x = 0
    for(y <- 0 until height){
      x = 0
      while (x < width) {
        val d = x.asInstanceOf[Double] / (width - 1).asInstanceOf[Double]
        val r = ((r2 - r1) * d + r1).asInstanceOf[Int]
        val g = ((g2 - g1) * d + g1).asInstanceOf[Int]
        val b = ((b2 - b1) * d + b1).asInstanceOf[Int]
        val color = (r << 16) + (g << 8) + b
        img.setRGB(x,y,color)
        x += 1
      }
    }
    try {
      ImageIO.write(img, "png", f)
    }catch {
      case exception: Exception => exception.printStackTrace()
    }
    val end = System.nanoTime
    val interval = end - start
    //println(z + ":" + interval + " ns")
    try {
      val resultData = new File("./rsc/data.txt")
      val fw = new FileWriter(resultData, true)
      val pw = new PrintWriter(fw)
      pw.print(z +":")
      pw.println(interval + "ns")
      pw.close()
      //println("SUCCESS")
      //time += interval
    }catch {
      case exception: Exception => exception.printStackTrace()
    }
    //f.delete()
  }
}

