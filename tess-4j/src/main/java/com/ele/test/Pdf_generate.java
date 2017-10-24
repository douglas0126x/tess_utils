package com.ele.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;

import com.ele.utils.pdf.PdfGenerateUtil;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPRow;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

public class Pdf_generate {

	public static void main(String[] args) throws Exception {

		long start_time = System.currentTimeMillis();
		// pdf_simple();
		// pdf_property();
		// pdf_pic();
		// pdf_table();
		// pdf_list();
		// pdf_format();
		// pdf_password();
		// pdf_limit();
		// pdf_update();

		boolean rs = PdfGenerateUtil.pdf_table();

		long end_time = System.currentTimeMillis();
		if (rs) {
			System.out.println("\nsuccess!\n\n" + (end_time - start_time)
					+ "  ss");
		} else {
			System.out.println("\nerror!\n\n" + (end_time - start_time)
					+ "  ss");
		}

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	/**
	 * 简单pdf生成
	 * 
	 * @throws DocumentException
	 * @throws FileNotFoundException
	 */
	private static void pdf_simple() throws DocumentException,
			FileNotFoundException {
		// 1.新建document对象
		Document document = new Document();
		// 2.建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中。
		// 创建 PdfWriter 对象 第一个参数是对文档对象的引用，第二个参数是文件的实际名称，在该名称中还会给出其输出路径。
		PdfWriter writer = PdfWriter.getInstance(document,
				new FileOutputStream("F:/generate_test/simple_pdf.pdf"));
		// 3.打开文档
		document.open();
		// 4.添加一个内容段落
		document.add(new Paragraph("Hello World!"));
		// 5.关闭文档
		document.close();

	}

	/**
	 * pdf 属性设置
	 * 
	 * @throws Exception
	 */
	private static void pdf_property() throws Exception {

		// 创建文件
		Document document = new Document();
		// 建立一个书写器
		PdfWriter writer = PdfWriter.getInstance(document,
				new FileOutputStream("F:/generate_test/property_pdf.pdf"));
		// 打开文件
		document.open();
		// 添加内容
		document.add(new Paragraph("Some content here this is belong to pdf"));

		// 设置属性
		// 标题
		document.addTitle("this is a title");
		// 作者
		document.addAuthor("yaoxj");
		// 主题
		document.addSubject("property test");
		// 关键字
		document.addKeywords("property");
		// 创建时间
		document.addCreationDate();
		// 应用程序
		document.addCreator("yaoxiaojun@ele-cloud.com");
		// 关闭文档
		document.close();
		// 关闭书写器
		writer.close();
	}

	/**
	 * pdf 图片添加
	 * 
	 * @throws Exception
	 */
	private static void pdf_pic() throws Exception {

		// 创建文件
		Document document = new Document();
		// 建立一个书写器
		PdfWriter writer = PdfWriter.getInstance(document,
				new FileOutputStream("F:/generate_test/pic_pdf.pdf"));
		// 打开文件
		document.open();
		// 添加内容
		document.add(new Paragraph("HD content here"));

		// 图片1
		Image image1 = Image.getInstance("F:/generate_test/title.png");
		// 设置图片位置的x轴和y周
		image1.setAbsolutePosition(100f, 550f);
		// 设置图片的宽度和高度
		image1.scaleAbsolute(100, 100);
		// 将图片1添加到pdf文件中
		document.add(image1);

		// 图片2
		Image image2 = Image.getInstance(new URL(
				"http://static.cnblogs.com/images/adminlogo.gif"));
		// 将图片2添加到pdf文件中
		document.add(image2);

		// 关闭文档
		document.close();
		// 关闭书写器
		writer.close();

	}

	/**
	 * pdf 表格生成
	 * 
	 * @throws Exception
	 */
	private static void pdf_table() throws Exception {
		// 创建文件
		Document document = new Document();
		// 建立一个书写器
		PdfWriter writer = PdfWriter.getInstance(document,
				new FileOutputStream("F:/generate_test/table/table_pdf.pdf"));
		// 打开文件
		document.open();
		// 添加内容
		document.add(new Paragraph("HD content here"));

		// 3列的表.
		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(100); // 宽度100%填充
		table.setSpacingBefore(10f); // 前间距
		table.setSpacingAfter(10f); // 后间距

		// List<PdfPRow> listRow = table.getRows();
		java.util.List<PdfPRow> listRow = table.getRows();
		// 设置列宽
		float[] columnWidths = { 1f, 2f, 3f };
		table.setWidths(columnWidths);

		// 行1
		PdfPCell cells1[] = new PdfPCell[3];
		PdfPRow row1 = new PdfPRow(cells1);

		// 单元格
		cells1[0] = new PdfPCell(new Paragraph("111"));// 单元格内容
		cells1[0].setBorderColor(BaseColor.BLUE);// 边框颜色
		cells1[0].setPaddingLeft(20);// 左填充20
		cells1[0].setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
		cells1[0].setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中

		cells1[1] = new PdfPCell(new Paragraph("222"));
		cells1[2] = new PdfPCell(new Paragraph("333"));

		// 行2
		PdfPCell cells2[] = new PdfPCell[3];
		PdfPRow row2 = new PdfPRow(cells2);
		cells2[0] = new PdfPCell(new Paragraph("444"));

		// 把第一行添加到集合
		listRow.add(row1);
		listRow.add(row2);
		// 把表格添加到文件中
		document.add(table);

		// 关闭文档
		document.close();
		// 关闭书写器
		writer.close();

	}

	/**
	 * pdf list 生成
	 * 
	 * @throws Exception
	 */
	private static void pdf_list() throws Exception {
		// 创建文件
		Document document = new Document();
		// 建立一个书写器
		PdfWriter writer = PdfWriter.getInstance(document,
				new FileOutputStream("F:/generate_test/list_pdf.pdf"));
		// 打开文件
		document.open();
		// 添加内容
		document.add(new Paragraph("HD content here"));

		// 添加有序列表
		List orderedList = new List(List.ORDERED);
		orderedList.add(new ListItem("Item one"));
		orderedList.add(new ListItem("Item two"));
		orderedList.add(new ListItem("Item three"));
		document.add(orderedList);

		// 关闭文档
		document.close();
		// 关闭书写器
		writer.close();
	}


	/**
	 * pdf 设置密码
	 * 
	 * @throws Exception
	 */
	private static void pdf_password() throws Exception {
		// 创建文件
		Document document = new Document();
		// 建立一个书写器
		PdfWriter writer = PdfWriter.getInstance(document,
				new FileOutputStream("F:/generate_test/password_pdf.pdf"));

		// 用户密码
		String userPassword = "123456";
		// 拥有者密码
		String ownerPassword = "hd";
		writer.setEncryption(userPassword.getBytes(), ownerPassword.getBytes(),
				PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_128);

		// 打开文件
		document.open();

		// 添加内容
		document.add(new Paragraph("password !!!!"));

		// 关闭文档
		document.close();
		// 关闭书写器
		writer.close();
	}

	/**
	 * pdf 权限
	 * 
	 * @throws Exception
	 */
	private static void pdf_limit() throws Exception {
		// 创建文件
		Document document = new Document();
		// 建立一个书写器
		PdfWriter writer = PdfWriter.getInstance(document,
				new FileOutputStream("F:/generate_test/limit_pdf.pdf"));

		// 只读权限
		writer.setEncryption("".getBytes(), "".getBytes(),
				PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_128);

		// 打开文件
		document.open();

		// 添加内容
		document.add(new Paragraph("limit, you can only read this pdf !!!!"));

		// 关闭文档
		document.close();
		// 关闭书写器
		writer.close();
	}

	/**
	 * 更新已有pdf
	 * 
	 * @throws Exception
	 */
	private static void pdf_update() throws Exception {

		// 读取pdf文件
		PdfReader pdfReader = new PdfReader("F:/generate_test/property_pdf.pdf");

		// 修改器
		PdfStamper pdfStamper = new PdfStamper(pdfReader, new FileOutputStream("F:/generate_test/update_pdf.pdf"));

		Image image = Image.getInstance("F:/generate_test/title.png");
		image.scaleAbsolute(50, 50);
		image.setAbsolutePosition(0, 700);

		for (int i = 1; i <= pdfReader.getNumberOfPages(); i++) {
			PdfContentByte content = pdfStamper.getUnderContent(i);
			content.addImage(image);
		}

		pdfStamper.close();
	}
	

	private static void  test() throws Exception{
		
		 // A4纸大小 左、右、上、下                /* 使用中文字体 */
		Document document = new Document(PageSize.A4, 80, 79, 20, 45);
		BaseFont bfChinese = BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED); // 中文处理  
//        Font FontChinese = new Font(bfChinese, 14, Font.COURIER); // 其他所有文字字体
		Font FontChinese = new Font(bfChinese, 14, Font.STRIKETHRU); // 其他所有文字字体 
        Font BoldChinese = new Font(bfChinese, 14, Font.BOLD); // 粗体     
        Font titleChinese = new Font(bfChinese, 20, Font.BOLD); // 模板抬头的字体     
//        Font subFontChinese = new Font(bfChinese, 12, Font.COURIER); // 币种和租金金额的小一号字体
        Font subFontChinese = new Font(bfChinese, 12, Font.STRIKETHRU); // 币种和租金金额的小一号字体   
//        Font moneyFontChinese = new Font(bfChinese, 8, Font.COURIER); // 币种和租金金额的小一号字体
        Font moneyFontChinese = new Font(bfChinese, 8, Font.BOLDITALIC); // 币种和租金金额的小一号字体
        Font subBoldFontChinese = new Font(bfChinese, 8, Font.BOLD); // 币种和租金金额的小一号字体  
         PdfWriter.getInstance(document,new FileOutputStream("F:/generate_test/table/test.pdf"));  
         document.open(); //打开文档  
         
         
          //------------开始写数据-------------------  
         Paragraph title = new Paragraph("起租通知书", titleChinese);// 抬头  
		 title.setAlignment(Element.ALIGN_CENTER); // 居中设置  
		 title.setLeading(1f);//设置行间距//设置上面空白宽度  
		 document.add(title);  
		   
		 title = new Paragraph("致：XXX公司", BoldChinese);// 抬头  
		 title.setSpacingBefore(25f);//设置上面空白宽度  
		 document.add(title);  
		   
		 title = new Paragraph("贵我双方签署的编号为 XXX有关起租条件已满足，现将租赁合同项下相关租赁要素明示如下：", FontChinese);  
		 title.setLeading(22f);//设置行间距  
		 document.add(title);  
   
		 float[] widths = { 10f,25f,30f,30f };// 设置表格的列宽和列数  默认是4列  
		 
		 
		 /*
		 if(depositBean.isExpress()==5){ //如果是明示就是6列  
			 	widths = new float[]{ 8f,15f,19f,19f,19f,20f };  
		 }else if(depositBean.isExpress()==6){   //如果是业发事业部就是7列  
			 widths = new float[]{ 8f,15f,15f,15f,15f,16f,16f };  
		 }  
		 */
   
		 PdfPTable table = new PdfPTable(widths);// 建立一个pdf表格  
		 table.setSpacingBefore(20f);// 设置表格上面空白宽度  
		 table.setTotalWidth(500);// 设置表格的宽度  
		 table.setWidthPercentage(100);//设置表格宽度为%100  
 // 	table.getDefaultCell().setBorder(0);//设置表格默认为无边框  
           
//		 String[] tempValue = new String[4]{"11.11","11.11","3000","9999"}();  //租金期次列表
		 String[] tempValue = new String[]{};
		 int rowCount=1; //行计数器  
		 PdfPCell cell = null;  
 //---表头  
		 cell = new PdfPCell(new Paragraph("期次", subBoldFontChinese));//描述  
		 cell.setFixedHeight(20);  
		 cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示  
		 cell.setVerticalAlignment(Element.ALIGN_MIDDLE);  // 设置垂直居中  
		 table.addCell(cell);  
		 cell = new PdfPCell(new Paragraph("租金日", subBoldFontChinese));//描述  
		 cell.setFixedHeight(20);  
		 cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示  
		 cell.setVerticalAlignment(Element.ALIGN_MIDDLE);  // 设置垂直居中  
		 table.addCell(cell);  
		 cell = new PdfPCell(new Paragraph("各期租金金额", subBoldFontChinese));//描述  
		 cell.setFixedHeight(20);  
		 cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示  
		 cell.setVerticalAlignment(Element.ALIGN_MIDDLE);  // 设置垂直居中  
		 table.addCell(cell);  
   
		 cell = new PdfPCell(new Paragraph("各期租金后\n剩余租金", subBoldFontChinese));//描述  
		 cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示  
		 cell.setVerticalAlignment(Element.ALIGN_MIDDLE);  // 设置垂直居中  
		 cell.setFixedHeight(20);  
		 table.addCell(cell);  
   
   
		 for (int j = 1 ; j< tempValue.length; j++){  
//                 if(j/argument==1){      //第一列 日期
			 	if(j/2==1){      //第一列 日期  
		             cell = new PdfPCell(new Paragraph(rowCount+"", moneyFontChinese));//描述  
		             cell.setFixedHeight(20);  
		             cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示  
		             cell.setVerticalAlignment(Element.ALIGN_MIDDLE);  // 设置垂直居中  
		             table.addCell(cell);  
		             rowCount++;  
                 }  
                 cell = new PdfPCell(new Paragraph(tempValue[j], moneyFontChinese));//描述  
                 cell.setFixedHeight(20);  
                 cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示  
                 cell.setVerticalAlignment(Element.ALIGN_MIDDLE);  // 设置垂直居中  
                 table.addCell(cell);  
		 }  
		 document.add(table);  
		 
		 title = new Paragraph("租金总额：XXX", FontChinese);  
		 title.setLeading(22f);//设置行间距  
		 document.add(title);  
		 title = new Paragraph("特此通知！", FontChinese);  
		 title.setLeading(22f);//设置行间距  
		 document.add(title);
		 
 //-------此处增加图片和日期，因为图片会遇到跨页的问题，图片跨页，图片下方的日期就会脱离图片下方会放到上一页。  
         //所以必须用表格加以固定的技巧来实现  
		 float[] widthes = { 50f };// 设置表格的列宽和列数  
		 PdfPTable hiddenTable = new PdfPTable(widthes);// 建立一个pdf表格  
		 hiddenTable.setSpacingBefore(11f);  //设置表格上空间  
		 hiddenTable.setTotalWidth(500);// 设置表格的宽度  
		 hiddenTable.setWidthPercentage(100);//设置表格宽度为%100  
		 hiddenTable.getDefaultCell().disableBorderSide(1);  
		 hiddenTable.getDefaultCell().disableBorderSide(2);  
		 hiddenTable.getDefaultCell().disableBorderSide(4);  
		 hiddenTable.getDefaultCell().disableBorderSide(8);  
   
		 Image upgif = Image.getInstance("D:/opt/yd_apps/rim/uploadfolder/stamp1.jpg");   
		 upgif.scalePercent(7.5f);//设置缩放的百分比%7.5  
		 upgif.setAlignment(Element.ALIGN_RIGHT);  
   
		 cell = new PdfPCell(new Paragraph("", FontChinese));//描述  
		 cell.setHorizontalAlignment(Element.ALIGN_RIGHT);// 设置内容水平居中显示  
		 cell.addElement(upgif);  
		 cell.setPaddingTop(0f);             //设置内容靠上位置  
		 cell.setPaddingBottom(0f);  
		 cell.setPaddingRight(20f);  
		 cell.setBorder(Rectangle.NO_BORDER);//设置单元格无边框  
		 hiddenTable.addCell(cell);  
   
		 cell = new PdfPCell(new Paragraph("XX 年 XX 月 XX 日                    ", FontChinese));//金额  
		 cell.setHorizontalAlignment(Element.ALIGN_RIGHT);// 设置内容水平居中显示  
		 cell.setPaddingTop(0f);  
		 cell.setPaddingRight(20f);  
		 cell.setBorder(Rectangle.NO_BORDER);  
		 hiddenTable.addCell(cell);  
		 document.add(hiddenTable);  
		 document.close();  
		
	}


}