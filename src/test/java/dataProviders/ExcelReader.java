package dataProviders;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.FileInputStream;
import java.util.HashMap;

public class ExcelReader {

    public static HashMap<String, HashMap<String, String>> excelSheetHashMap = new HashMap();

    private static String columnHashMap;

    public void ReadExcel(String xlpath, String SheetName) throws Exception {

       FileInputStream file = new FileInputStream(xlpath);
        HSSFWorkbook workbook = new HSSFWorkbook(file);
        HSSFSheet sheet = workbook.getSheet(SheetName);

        Row HeaderRow = sheet.getRow(0);
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            Row currentRow = sheet.getRow(i);
            HashMap<String, String> currentHash = new HashMap<String, String>();
            for (int j = 0; j < currentRow.getPhysicalNumberOfCells(); j++) {

                Cell currentCell1 = currentRow.getCell(0);
                switch (currentCell1.getCellType()) {
                    case Cell.CELL_TYPE_STRING:
                        columnHashMap = currentCell1.getStringCellValue();
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        columnHashMap = String.valueOf(currentCell1.getNumericCellValue());
                        break;
                }

                Cell currentCell = currentRow.getCell(j);

                switch (currentCell.getCellType()) {
                    case Cell.CELL_TYPE_STRING:
                        currentHash.put(HeaderRow.getCell(j).getStringCellValue(), currentCell.getStringCellValue());
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        currentHash.put(HeaderRow.getCell(j).getStringCellValue(),
                                String.valueOf(currentCell.getNumericCellValue()));
                        break;
                }

            }


            excelSheetHashMap.put(columnHashMap, currentHash);

        }
    }

}
