package com.ele.utils.pdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfPCell;

/**
 * pdf 样式管理
 * @author yaoxj
 * @time 2017年10月24日上午10:55:52
 */
public class PdfFormate {
	
	/**
	 * 表格框线颜色
	 */
	private static BaseColor bc = new BaseColor(190, 190, 190);
	/**
	 * 明细数据单元格背景颜色
	 */
	private static BaseColor bc_ground = new BaseColor(240, 240, 240);
	
	/**
	 * 单元格填充5.0f,左对齐，垂直居中，边框灰色，背景浅灰色
	 * @param cell 单元格集合
	 */
	public static void set_cell_formate(PdfPCell[] cell) {
		if(cell != null && cell.length >0){
			for (int i=0;i<cell.length;i++){
				cell[i].setPadding(5.0f);
				// 左对齐
				cell[i].setHorizontalAlignment(Element.ALIGN_LEFT);
				// 垂直居中
				cell[i].setVerticalAlignment(Element.ALIGN_MIDDLE);
				//边框颜色：灰色
				cell[i].setBorderColor(bc);
				//明细单元格背景颜色：浅灰色
				cell[i].setBackgroundColor(bc_ground);
			}
		}
	}
	
	/**
	 * 单元格属性设置:<br/>
	 * ①、左对齐；<br/>
	 * ②、垂直居中;<br/>
	 * ③、单元格填充5.0f</br>
	 * @param cell
	 */
	public static void set_cell_common(PdfPCell[] cell) {
		if(cell != null && cell.length >0){
			for (int i=0;i<cell.length;i++){
				if(cell[i] != null){
					cell[i].setPadding(5.0f);
					// 左对齐
					cell[i].setHorizontalAlignment(Element.ALIGN_LEFT);
					// 垂直居中
					cell[i].setVerticalAlignment(Element.ALIGN_MIDDLE);
					//边框颜色：灰色
					cell[i].setBorderColor(bc);
				}
			}
		}
	}
	/**
	 * 单元格填充2.0f,左对齐，垂直居中，边框灰色，背景浅灰色
	 * @param cell
	 */
	public static void set_detail_formate(PdfPCell[] cell) {
		if(cell != null && cell.length >0){
			for (int i=0;i<cell.length;i++){
				if(cell[i] != null){
					cell[i].setPadding(1.0f);
					// 左对齐
					cell[i].setHorizontalAlignment(Element.ALIGN_LEFT);
					// 垂直居中
					cell[i].setVerticalAlignment(Element.ALIGN_MIDDLE);
					//边框颜色：灰色
					cell[i].setBorderColor(bc);
					//明细单元格背景颜色：浅灰色
					cell[i].setBackgroundColor(bc_ground);
				}
			}
		}
	}
	
	/**
	 * 通用单元格样式设定
	 * @param cell 
	 * @param padding
	 * @param horizontalAlignment
	 * @param verticalAlignment
	 * @param borderColor
	 * @param backgroundColor
	 */
	public static void common_cell_formate(PdfPCell[] cell,float padding,int horizontalAlignment,
			int verticalAlignment,BaseColor borderColor,BaseColor backgroundColor) {
		if(cell != null && cell.length >0){
			for (int i=0;i<cell.length;i++){
				
				if(cell[i] != null){
					cell[i].setPadding(padding);
					// 左对齐
					cell[i].setHorizontalAlignment(horizontalAlignment);
					// 垂直居中
					cell[i].setVerticalAlignment(verticalAlignment);
					//边框颜色：灰色
					cell[i].setBorderColor(borderColor);
					//明细单元格背景颜色：浅灰色
					cell[i].setBackgroundColor(backgroundColor);
				}
			}
		}
	}
}
