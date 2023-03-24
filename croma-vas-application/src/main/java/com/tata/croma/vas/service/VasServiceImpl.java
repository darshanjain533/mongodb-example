package com.tata.croma.vas.service;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tata.croma.vas.model.Vas;
import com.tata.croma.vas.repository.VasRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class VasServiceImpl implements IVasService{
	
	@Autowired
	VasRepository repo;
	
	private SequenceGeneratorService sequenceGeneratorService;
	
	@Override
	public void savedata() throws Exception{
		log.info("save data service class called...");
		Workbook workbook = new XSSFWorkbook(new FileInputStream("D:\\vas\\ServiceMaster.xlsx"));
		Sheet sheet = workbook.getSheetAt(0);
		Iterator<Row> itr = sheet.iterator(); // iterating over excel file
		itr.next();
		while (itr.hasNext()) {
			Row row = itr.next();
			if (row.getCell(0) != null) {
				Integer cellValueServiceProdL1 = (int) row.getCell(0).getNumericCellValue();
				Integer cellValueServiceProdL2 = (int) row.getCell(1).getNumericCellValue();
				Integer cellValueServiceProdCode = (int) row.getCell(2).getNumericCellValue();
				String cellValueServiceDescOrig = row.getCell(3).getStringCellValue();
				String cellValueServiceDescShort = row.getCell(4).getStringCellValue();
				Double cellValueMrp = row.getCell(5).getNumericCellValue();
				Double cellValueSp = row.getCell(6).getNumericCellValue();
				String cellValueBrandCode = Integer.toString((int) row.getCell(7).getNumericCellValue());
				String cellValueBrandName = row.getCell(8).getStringCellValue();
				String cellValueMaterialType = row.getCell(9).getStringCellValue();
				String cellValueOfferedBy = row.getCell(10).getStringCellValue();
				Double cellValueMinCoverage = row.getCell(11).getNumericCellValue();
				Double cellValueMaxCoverage = row.getCell(12).getNumericCellValue();
				Integer cellValueCoveragePeriod = (int) row.getCell(13).getNumericCellValue();
				Integer cellValueL0CategoryCode = (int) row.getCell(14).getNumericCellValue();
				Integer cellValueL1CategoryCode = (int) row.getCell(15).getNumericCellValue();
				String cellValueL2CategoryCode = row.getCell(16).getStringCellValue();
				List<Integer> cellValueL2CategoryCodeIntList = Arrays.stream(cellValueL2CategoryCode.split(",")).map(Integer::parseInt).collect(Collectors.toList());
				Vas vas = Vas.builder()
						.id("croma_"+cellValueServiceProdCode.toString())
						.vas_master_id(cellValueServiceProdCode)
						.sku_id(cellValueServiceProdCode)
						.service_product_l1(cellValueServiceProdL1)
						.service_product_l2(cellValueServiceProdL2)
						.coverage_period(cellValueCoveragePeriod)
						.l0_category_code(cellValueL0CategoryCode)
						.l1_category_code(cellValueL1CategoryCode)
						.l2_category_code(cellValueL2CategoryCodeIntList)
						.approval_status(0)
						.channel_type(0)
						.selling_price(cellValueSp)
						.mrp(cellValueMrp)
						.min_coverage_price(cellValueMinCoverage)
						.max_coverage_price(cellValueMaxCoverage)
						.service_description_original(cellValueServiceDescOrig)
						.service_description_short(cellValueServiceDescShort)
						.brand_code(cellValueBrandCode)
						.brand_name(cellValueBrandName)
						.material_type(cellValueMaterialType)
						.offered_by(cellValueOfferedBy)
						.created_on(new Date())
						.last_modified_at(new Date())
						.build();
				
				log.info("vas is:::"+vas);
				repo.save(vas);
			}else {
				log.info("empty row reached...");
			} //empty cell condition close
		} //while loop close
	}
	
	@Override
	public void getdata() throws Exception {
		log.info("get data service class called...");
		Workbook workbook = new XSSFWorkbook(new FileInputStream("D:\\vas\\ServiceMaster.xlsx"));
		Sheet sheet = workbook.getSheetAt(0);
		Iterator<Row> itr = sheet.iterator(); // iterating over excel file
		while (itr.hasNext()) {
			Row row = itr.next();
			Iterator<Cell> cellIterator = row.cellIterator(); // iterating over each column
			while (cellIterator.hasNext()) {
				Cell cell1 = cellIterator.next();
				switch (cell1.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					log.info("string value is:::" + cell1.getStringCellValue());
					break;
				case Cell.CELL_TYPE_NUMERIC:
					log.info("numeric value is:::" + cell1.getNumericCellValue());
					break;
				default:
					log.info("default is:::" + cell1);
					break;
				}
			}
		}
	}
	
}


