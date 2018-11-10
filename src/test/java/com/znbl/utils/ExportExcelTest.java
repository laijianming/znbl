package com.znbl.utils;

import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.junit.Test;

import com.znbl.common.utils.excel.ExportExcel;
import com.google.common.collect.Lists;

public class ExportExcelTest {

	@Test
	public void testExportExcelStringClassOfQ() {
		//fail("Not yet implemented");
		System.out.println("OK!!!");
	}

	@Test
	public void testExportExcelStringClassOfQIntIntArray() {
		//fail("Not yet implemented");
	}

	@Test
	public void testExportExcelStringStringArray() {
		//fail("Not yet implemented");
	}

	@Test
	public void testExportExcelStringListOfString() {
		///fail("Not yet implemented");
	}

	@Test
	public void testAddRow() {
 
		List<String> headerList = Lists.newArrayList();
		for (int i = 1; i <= 10; i++) {
			headerList.add("表头"+i);
		}
		List<String> dataRowList = Lists.newArrayList();
		for (int i = 1; i <= headerList.size(); i++) {
			dataRowList.add("数据"+i);
		}
		ExportExcel ee = new ExportExcel("表格标题", headerList);
		
		List<List<String>> dataList = Lists.newArrayList();
		for (int i = 1; i <=10000; i++) {
 		   dataList.add(dataRowList);
 		}
		for (int i = 0; i < dataList.size(); i++) {
			Row row = ee.addRow();
			for (int j = 0; j < dataList.get(i).size(); j++) {
				ee.addCell(row, j, dataList.get(i).get(j));
			}
		}
	}

	@Test
	public void testAddCellRowIntObject() {
		//fail("Not yet implemented");
	}

	@Test
	public void testAddCellRowIntObjectIntClassOfQ() {
		//fail("Not yet implemented");
	}

	@Test
	public void testSetDataList() {
		//fail("Not yet implemented");
	}

	@Test
	public void testWriteOutputStream() {
		//fail("Not yet implemented");
	}

	@Test
	public void testWriteHttpServletResponseString() {
		//fail("Not yet implemented");
	}

	@Test
	public void testWriteFile() {
		//fail("Not yet implemented");
	}

}
