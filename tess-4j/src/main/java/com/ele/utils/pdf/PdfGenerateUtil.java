package com.ele.utils.pdf;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPRow;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


/**
 * pdf生成工具类
 * @author yaoxj
 * @time 2017年10月23日下午3:00:05
 */
public class PdfGenerateUtil {
	
	
	/**
	 * 两列数据(公用)
	 */
	private static float[] widths_common = {25.0f,75.0f};
	
	
	/**
	 * pdf 表格生成
	 * 
	 * @throws Exception
	 */
	public static boolean pdf_table() {
		
		
		
		try {
			// 创建文件
			Document document = new Document();
			// 建立一个书写器
			PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream("F:/generate_test/table/rs.pdf"));
			// 打开文件
			document.open();
			
			
			// 中文字体,解决中文不能显示问题
			BaseFont bfChinese = BaseFont.createFont("STSong-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
//			BaseFont bfChinese = BaseFont.createFont("font/SIMYOU.TTF",BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
			
			Font cell_font = new Font(bfChinese, 8.0f, Font.NORMAL);
			
			// 添加内容
			//抬头
			Paragraph title = new Paragraph("餐费和打车费报销审批", new Font(bfChinese, 15.0f, Font.BOLD));
			title.setAlignment(Element.ALIGN_CENTER);
			title.setLeading(20.0f);
			document.add(title);
			
			
			Paragraph first_line = new Paragraph("大象慧云信息技术有限公司         申请日期：2017-04-13", cell_font);
			first_line.setSpacingBefore(10.0f);
//			first_line.setLeading(15.0f);
			document.add(first_line);
			
			
			float[] widths = {100.0f};// 设置表格的列宽和列数  默认是1列
			PdfPTable table = new PdfPTable(widths);
			table.setSpacingBefore(10f); // 前间距
			table.setWidthPercentage(100); // 宽度100%填充
			table.setSpacingAfter(10f); // 后间距

			java.util.List<PdfPRow> listRow = table.getRows();
			PdfPCell cells0[] = new PdfPCell[1];
			PdfPRow row0 = new PdfPRow(cells0);
			
			//=================  子表格   1  -start    ======================== 
			
			PdfPTable row0_t = new PdfPTable(widths_common);
			ArrayList<PdfPRow> rows0_r = row0_t.getRows();
			
			// 行1
			PdfPCell cells1_1[] = new PdfPCell[2];
			PdfPRow row1_1 = new PdfPRow(cells1_1);
			cells1_1[0] = new PdfPCell(new Paragraph("审批编码\n换行\n试一试\n在缓一缓",cell_font));// 单元格内容
			cells1_1[1] = new PdfPCell(new Paragraph("201704131339000003893",cell_font));
			
			// 行2
			PdfPCell cells1_2[] = new PdfPCell[2];
			PdfPRow row1_2 = new PdfPRow(cells1_2);
			cells1_2[0] = new PdfPCell(new Paragraph("申请人",cell_font));
			cells1_2[1] = new PdfPCell(new Paragraph("姚小军", cell_font));
			
			PdfPCell cells1_3[] = new PdfPCell[2];
			PdfPRow row1_3 = new PdfPRow(cells1_3);
			cells1_3[0] = new PdfPCell(new Paragraph("申请人部门",cell_font));
			cells1_3[1] = new PdfPCell(new Paragraph("研究开发三部",cell_font));
			
			
			rows0_r.add(row1_1);
			rows0_r.add(row1_2);
			rows0_r.add(row1_3);
			
			for(PdfPRow row:rows0_r){
				PdfFormate.set_cell_common(row.getCells());
			}
			
			cells0[0] = new PdfPCell(row0_t);
			
			//============= end ================
			
			PdfPCell cells1[] = new PdfPCell[1];
			PdfPRow row1 = new PdfPRow(cells1);
			
			//=================  子表 2 -start  ======================
			
			float[] widths_row1 = {25f,15.0f,15.0f,15.0f,30.0f};//设置列宽
			PdfPTable row1_t = new PdfPTable(widths_row1);
			
			row1_t.addCell(" ");
			row1_t.addCell(new Paragraph("是否在预算内",cell_font));
			row1_t.addCell(new Paragraph("报销类别",cell_font));
			row1_t.addCell(new Paragraph("报销金额",cell_font));
			row1_t.addCell(new Paragraph("费用报销明细",cell_font));
			
			row1_t.addCell(new Paragraph("明细1",cell_font));
			row1_t.addCell(new Paragraph("是",cell_font));
			row1_t.addCell(new Paragraph("夜间打车报销",cell_font));
			row1_t.addCell(new Paragraph("112",cell_font));
			row1_t.addCell(new Paragraph("2017年3月24日夜间打车费报销",cell_font));
			
			cells1[0] = new PdfPCell(row1_t);
			
			ArrayList<PdfPRow> rows1_r = row1_t.getRows();
			for(PdfPRow row:rows1_r){
				PdfFormate.set_cell_formate(row.getCells());
			}
			
			// ====================  end  ==============================
			
			PdfPCell cells2[] = new PdfPCell[1];
			PdfPRow row2 = new PdfPRow(cells2);
			
			//=================  子表 3 -start  ======================
			
			float[] widths_row2 = {25f,15.0f,15.0f,15.0f,30.0f};//设置列宽
			PdfPTable row2_t = new PdfPTable(widths_row2);
			
			row2_t.addCell(" ");
			row2_t.addCell(" ");
			row2_t.addCell(" ");
			row2_t.addCell(new Paragraph("总报销金额:112",cell_font));
			row2_t.addCell(" ");
			
			
			cells2[0] = new PdfPCell(row2_t);
			
			ArrayList<PdfPRow> rows2_r = row2_t.getRows();
			for(PdfPRow row:rows2_r){
				PdfFormate.set_detail_formate(row.getCells());
			}
			
			//==================  end  ==============================
			PdfPCell cells3[] = new PdfPCell[1];
			PdfPRow row3 = new PdfPRow(cells3);
			
			// ===================  子表4  start =====================
			PdfPTable row3_t = new PdfPTable(widths_common);
			
			row3_t.addCell(new Paragraph("图片",cell_font));
			row3_t.addCell(new Paragraph("lADOvaZhB80FAM0C0A_720_1280.jpg\n/lADOvag6eM0DwM0FAA_1280_960.jpg",cell_font));
			row3_t.addCell(new Paragraph("附件",cell_font));
			row3_t.addCell(new Paragraph(" ",cell_font));
			
			
			for(PdfPRow row:row3_t.getRows()){
				PdfFormate.set_cell_common(row.getCells());
			}
			cells3[0] = new PdfPCell(row3_t);
			// =====================  end  =========================
			
			PdfPCell cells4[] = new PdfPCell[1];
			PdfPRow row4 = new PdfPRow(cells4);
			// ====================  字表 5 start =======================
			
			
			PdfPTable row3_t0 = new PdfPTable(widths_common);
			
			PdfPCell cell_s = new PdfPCell(new Paragraph("审批人",cell_font));
			cell_s.setRowspan(4);
			row3_t0.addCell(cell_s);
			
			row3_t0.addCell(new Paragraph("朱延超"+StringUtils.repeat(" ", 5)+ "已同意 "+StringUtils.repeat(" ", 5)+"2017-04-13 20:05:42 ",cell_font));
			row3_t0.addCell(new Paragraph("庄德元"+StringUtils.repeat(" ", 5)+ "已同意 "+StringUtils.repeat(" ", 5)+"2017-04-13 20:05:42 ",cell_font));
			row3_t0.addCell(new Paragraph("侯宇蕾"+StringUtils.repeat(" ", 5)+ "已同意 "+StringUtils.repeat(" ", 5)+"2017-04-13 20:05:42 ",cell_font));
			row3_t0.addCell(new Paragraph("陈懿（陈总）"+StringUtils.repeat(" ", 5)+ "已同意 "+StringUtils.repeat(" ", 5)+"2017-04-13 20:05:42 ",cell_font));
			
			for(PdfPRow row:row3_t0.getRows()){
				PdfFormate.set_cell_common(row.getCells());
			}
			
			cells4[0] = new PdfPCell(row3_t0);
			
			
			// 行添加到表格中
			listRow.add(row0);
			listRow.add(row1);
			listRow.add(row2);
			listRow.add(row3);
			listRow.add(row4);
			
			
			Paragraph last_line = new Paragraph("打印时间： 2017-04-25 10：19       打印人：姚小军", cell_font);
			last_line.setAlignment(Element.ALIGN_LEFT);
			
			Paragraph last_right = new Paragraph("请用大象扫码", cell_font);
			last_right.setAlignment(Element.ALIGN_RIGHT);
			
			
			// 图片1
			Image image1 = Image.getInstance("F:/generate_test/title.png");
			// 设置图片位置的x轴和y周
			image1.setAbsolutePosition(100f, 200f);
			// 设置图片的宽度和高度
			image1.scaleAbsolute(200, 200);
			image1.setAlignment(Element.ALIGN_LEFT);
			
			
			Image image2 = Image.getInstance("F:/generate_test/time.jpg");
			// 设置图片位置的x轴和y周
//			image2.setAbsolutePosition(300f, 300f);
			// 设置图片的宽度和高度
			image2.scaleAbsolute(30, 30);
			image2.setAlignment(Element.ALIGN_RIGHT);
			
			// 把表格添加到文件中
			document.add(table);
			document.add(last_line);
			document.add(last_right);
			document.add(image1);
			document.add(image2);
			// 关闭文档
			document.close();
			// 关闭书写器
			writer.close();
			
			
			
			return true;
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		

	}
	


	
	
	
	
/**
 * 解决中文输出问题
 * @throws Exception
 */
	private static void pdf_format() throws Exception {
		// 创建文件
		Document document = new Document();
		// 建立一个书写器
		PdfWriter writer = PdfWriter.getInstance(document,
				new FileOutputStream("F:/generate_test/formate_pdf.pdf"));
		// 打开文件
		document.open();

		// 中文字体,解决中文不能显示问题
		BaseFont bfChinese = BaseFont.createFont("STSong-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);

		// 蓝色字体
		Font blueFont = new Font(bfChinese);
		blueFont.setColor(BaseColor.BLUE);
		// 段落文本
		Paragraph paragraphBlue = new Paragraph("中文和 英文  paragraphOne blue front", blueFont);
		document.add(paragraphBlue);

		// 绿色字体
		Font greenFont = new Font(bfChinese);
		greenFont.setColor(BaseColor.GREEN);
		// 创建章节
		Paragraph chapterTitle = new Paragraph("段落标题xxxx", greenFont);
		Chapter chapter1 = new Chapter(chapterTitle, 1);
		chapter1.setNumberDepth(0);

		Paragraph sectionTitle = new Paragraph("部分标题", greenFont);
		Section section1 = chapter1.addSection(sectionTitle);

		Paragraph sectionContent = new Paragraph("部分内容", blueFont);
		section1.add(sectionContent);

		// 将章节添加到文章中
		document.add(chapter1);

		// 关闭文档
		document.close();
		// 关闭书写器
		writer.close();
	}

}
