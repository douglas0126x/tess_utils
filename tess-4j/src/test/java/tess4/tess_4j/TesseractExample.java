package tess4.tess_4j;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import com.ele.utils.baidu.TestPrepare;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

/**
 * 官方样例
 * 
 * @author thinkpad
 *
 */
public class TesseractExample {

	public static void main(String[] args) {

		TestPrepare prepare = new TestPrepare();
		List<String> list = new ArrayList<String>();
		String imgPath = "F:/定额票/";
		File imageFile = new File(imgPath);
		prepare.ergodic(imageFile, list);
		
		ITesseract instance = new Tesseract(); // JNA Interface Mapping
		// instance.setLanguage("eng");
		 instance.setLanguage("ding+eng+chi_sim");
		// instance.setLanguage("invoice_all");
//		instance.setLanguage("eng+chi_sim");
		
		System.out.println("\n-------application is running --------------");
		for(String picPath:list){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date now = new Date();
			long startTime = System.currentTimeMillis();
			System.out.println("--------------current time --------------\n"+sdf.format(now)+"   current file path  :"+picPath);
			try {
				String result = instance.doOCR(new File(picPath));
				System.out.println("\nresult= " + result);
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
			long endTime = System.currentTimeMillis();
			System.out.println("\n-------end------------" + "\n lasted time =  "+ (endTime - startTime) / 1000 + "s," + (endTime - startTime)% 1000 + " mm\n\n");
			
		}


		// Rectangle rect_code = new Rectangle(3607, 366, 373, 154);

	}
}
