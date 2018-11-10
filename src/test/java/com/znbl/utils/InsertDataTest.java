package com.znbl.utils;

import java.io.File;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.junit.Test;

import com.znbl.common.utils.excel.ExportExcel;
import com.google.common.collect.Lists;

public class InsertDataTest {

	@Test
	public void repl() {
		String aa="aa"+File.separator+"aa.jpg";
		System.err.println(aa);
		String bb=aa.replaceAll("\\\\", "/");
		System.err.println(bb);
	}

}
