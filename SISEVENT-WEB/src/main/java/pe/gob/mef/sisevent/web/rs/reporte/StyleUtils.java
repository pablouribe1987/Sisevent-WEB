package pe.gob.mef.sisevent.web.rs.reporte;

import java.awt.Rectangle;
import java.io.Serializable;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StyleUtils implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -6275136278276839475L;

	static Logger logger = LoggerFactory.getLogger(StyleUtils.class);

    private CellStyle newCellStyleVerde = null;
    private CellStyle newCellStyleAmarillo = null;
    private CellStyle newCellStyleRojo = null;
    private CellStyle newCellStyleAlingCenter = null;
    private CellStyle newCellStyleBorder = null;
    
    private CellStyle newCellStyleTitulo = null;
    
    private Font headerFont  = null; 
    private Font contentFont = null; 

    public StyleUtils(Workbook workbook) {
    	headerFont  = createFont(workbook, IndexedColors.WHITE.index, (short)14, true);
    	contentFont = createFont(workbook, IndexedColors.BLACK.index, (short)8, false);
    }
    
    @SuppressWarnings("unused")
	private void highlightBonus(Workbook workbook, Cell cell) {
        CellStyle cellStyle = cell.getCellStyle();
        CellStyle newCellStyle = workbook.createCellStyle();
        newCellStyle.setDataFormat( cellStyle.getDataFormat() );
        newCellStyle.setFont( workbook.getFontAt( cellStyle.getFontIndex() ));
        newCellStyle.setFillBackgroundColor( cellStyle.getFillBackgroundColor());
        newCellStyle.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
        newCellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cell.setCellStyle(newCellStyle);
    }
    
    public void verde(Workbook workbook, Cell cell) {
        CellStyle cellStyle = cell.getCellStyle();
        
        if(newCellStyleVerde==null){
        	newCellStyleVerde = workbook.createCellStyle();
        	newCellStyleVerde.setDataFormat( cellStyle.getDataFormat() );
        	newCellStyleVerde.setFont( workbook.getFontAt( cellStyle.getFontIndex() ));
        	newCellStyleVerde.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
        	newCellStyleVerde.setFillForegroundColor(cellStyle.getFillForegroundColor());
        	newCellStyleVerde.setFillPattern(cellStyle.getFillPattern());
        }
        cell.setCellStyle(newCellStyleVerde);
    }
    
    public void rojo(Workbook workbook, Cell cell) {
        CellStyle cellStyle = cell.getCellStyle();
        CellStyle newCellStyle = workbook.createCellStyle();
        if(newCellStyleRojo==null){
        	newCellStyleRojo = workbook.createCellStyle();
        	newCellStyleRojo.setDataFormat( cellStyle.getDataFormat() );
        	newCellStyleRojo.setFont( workbook.getFontAt( cellStyle.getFontIndex() ));
        	newCellStyleRojo.setFillBackgroundColor(IndexedColors.RED.getIndex());
        	newCellStyleRojo.setFillForegroundColor(cellStyle.getFillForegroundColor());
        	newCellStyleRojo.setFillPattern(cellStyle.getFillPattern());
        }
        cell.setCellStyle(newCellStyle);
    }
    
    public void amarillo(Workbook workbook, Cell cell) {
        CellStyle cellStyle = cell.getCellStyle();        
        if(newCellStyleAmarillo==null){
        	newCellStyleAmarillo = workbook.createCellStyle();
        	newCellStyleAmarillo.setDataFormat( cellStyle.getDataFormat() );
        	newCellStyleAmarillo.setFont( workbook.getFontAt( cellStyle.getFontIndex() ));
        	newCellStyleAmarillo.setFillBackgroundColor(IndexedColors.YELLOW.getIndex());
        	newCellStyleAmarillo.setFillForegroundColor(cellStyle.getFillForegroundColor());
        	newCellStyleAmarillo.setFillPattern(cellStyle.getFillPattern());
        }
        cell.setCellStyle(newCellStyleAmarillo);
    }
    
    public void meargingCell(Sheet hoja, Rectangle r){
    	CellRangeAddress cellRangeAddress = new CellRangeAddress(
   			 r.y,        //first row (0-based)
   	         r.height,   //last row (0-based)
   	         r.x,        //first column (0-based)
   	         r.width     //last column (0-based)
   	      );
    	hoja.addMergedRegion(cellRangeAddress);     	      
    }
    
    public void rowHeight(Row row, short t){
    	row.setHeight(t);
    }
    
    public void colunmWidth(Sheet hoja, int colunm, int w){
    	hoja.setColumnWidth(colunm, w);
    }
    
    public void alingCenter(Workbook workbook, Cell cell) {
        CellStyle cellStyle = cell.getCellStyle();        
        if(newCellStyleAlingCenter==null){
        	newCellStyleAlingCenter = workbook.createCellStyle();
//        	newCellStyleAlingCenter.setDataFormat( cellStyle.getDataFormat() );
//        	newCellStyleAlingCenter.setFont( workbook.getFontAt( cellStyle.getFontIndex() ));        	
        	newCellStyleAlingCenter.setFillBackgroundColor(cellStyle.getFillBackgroundColor());
        	newCellStyleAlingCenter.setFillForegroundColor(cellStyle.getFillForegroundColor());
        	newCellStyleAlingCenter.setFillPattern(cellStyle.getFillPattern());
        	newCellStyleAlingCenter.setAlignment(CellStyle.ALIGN_CENTER);
        	newCellStyleAlingCenter.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        }
        cell.setCellStyle(newCellStyleAlingCenter);
    }
    
    public Row copyRow(Workbook workbook, Sheet worksheet, int sourceRowNum,
			int destinationRowNum, CellStyle newCellStyleComun) {
		// Get the source / new row
		Row newRow = worksheet.getRow(destinationRowNum);
		Row sourceRow = worksheet.getRow(sourceRowNum);

		// If the row exist in destination, push down all rows by 1 else create
		// a new row
		if (newRow != null) {
			worksheet.shiftRows(destinationRowNum, worksheet.getLastRowNum(), 1);
		} else {
			newRow = worksheet.createRow(destinationRowNum);
		}

		// Loop through source columns to add to new row
		for (int i = 0; i < sourceRow.getLastCellNum(); i++) {
			// Grab a copy of the old/new cell
			Cell oldCell = sourceRow.getCell(i);
			Cell newCell = newRow.createCell(i);

			// If the old cell is null jump to next cell
			if (oldCell == null) {
				newCell = null;
				continue;
			}

			// Copy style from old cell and apply to new cell
			CellStyle newCellStyle = null;
			if (newCellStyleComun == null) {
//				newCellStyle = workbook.createCellStyle();
//				newCellStyle.cloneStyleFrom(oldCell.getCellStyle());
				newCellStyle = oldCell.getCellStyle();
			} else {
				newCellStyle = newCellStyleComun;
			}

			newCell.setCellStyle(newCellStyle);

			// If there is a cell comment, copy
			if (oldCell.getCellComment() != null) {
				newCell.setCellComment(oldCell.getCellComment());
			}

			// If there is a cell hyperlink, copy
			if (oldCell.getHyperlink() != null) {
				newCell.setHyperlink(oldCell.getHyperlink());
			}

			// Set the cell data type
			newCell.setCellType(oldCell.getCellType());

			// Set the cell data value
			switch (oldCell.getCellType()) {
			case Cell.CELL_TYPE_BLANK:
				newCell.setCellValue(oldCell.getStringCellValue());
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				newCell.setCellValue(oldCell.getBooleanCellValue());
				break;
			case Cell.CELL_TYPE_ERROR:
				newCell.setCellErrorValue(oldCell.getErrorCellValue());
				break;
			case Cell.CELL_TYPE_FORMULA:
				newCell.setCellFormula(oldCell.getCellFormula());
				break;
			case Cell.CELL_TYPE_NUMERIC:
				newCell.setCellValue(oldCell.getNumericCellValue());
				break;
			case Cell.CELL_TYPE_STRING:
				newCell.setCellValue(oldCell.getRichStringCellValue());
				break;
			}
		}

		// If there are are any merged regions in the source row, copy to new
		// row
		for (int i = 0; i < worksheet.getNumMergedRegions(); i++) {
			CellRangeAddress cellRangeAddress = worksheet.getMergedRegion(i);
			if (cellRangeAddress.getFirstRow() == sourceRow.getRowNum()) {
				CellRangeAddress newCellRangeAddress = new CellRangeAddress(
						newRow.getRowNum(),
						(newRow.getRowNum() + (cellRangeAddress.getLastRow() - cellRangeAddress
								.getFirstRow())), cellRangeAddress
								.getFirstColumn(), cellRangeAddress
								.getLastColumn());
				worksheet.addMergedRegion(newCellRangeAddress);
			}
		}

		return newRow;
	}
    
    private Font createFont(Workbook workbook, short fontColor, short fontHeight, boolean fontBold) {
    	Font font = workbook.createFont();
    	if(fontBold)
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		font.setColor(fontColor);
		font.setFontName("Arial");
		font.setFontHeightInPoints(fontHeight);
 		return font;
	}
    
    public void formatAsDate(Cell cell) {
		Workbook wb = cell.getSheet().getWorkbook();
		CellStyle style = wb.createCellStyle();
		style.cloneStyleFrom(cell.getCellStyle());
		style.setDataFormat(wb.createDataFormat().getFormat("d/mmm/yyyy"));
		cell.setCellStyle(style);
	}
    
    public void setBorderStyle(Workbook workbook, Cell cell) {
        CellStyle cellStyle = cell.getCellStyle();        
        if(newCellStyleBorder==null){
        	newCellStyleBorder = workbook.createCellStyle();
        	newCellStyleBorder.setDataFormat( cellStyle.getDataFormat() );
        	newCellStyleBorder.setFont( workbook.getFontAt(cellStyle.getFontIndex() ));
        	newCellStyleBorder.setFillBackgroundColor(cellStyle.getFillBackgroundColor());
        	newCellStyleBorder.setFillForegroundColor(cellStyle.getFillForegroundColor());
        	newCellStyleBorder.setFillPattern(cellStyle.getFillPattern());
        	newCellStyleBorder.setFillPattern(CellStyle.SOLID_FOREGROUND);
        	newCellStyleBorder.setFillForegroundColor((short) 44);
        	newCellStyleBorder.setBorderBottom(CellStyle.BORDER_THIN);
        	newCellStyleBorder.setBorderLeft(CellStyle.BORDER_THIN);
        	newCellStyleBorder.setBorderRight(CellStyle.BORDER_THIN);
        	newCellStyleBorder.setBorderTop(CellStyle.BORDER_THIN);
        	newCellStyleBorder.setAlignment(CellStyle.ALIGN_LEFT);        	
        }
        cell.setCellStyle(newCellStyleBorder);
    }
    
    ///////////////////////////////////////////////////////////////
    public void tituloCenter(Cell cell) {
    	Workbook workbook=cell.getSheet().getWorkbook();
        CellStyle cellStyle = cell.getCellStyle();        
        if(newCellStyleTitulo==null){
        	newCellStyleTitulo = workbook.createCellStyle();
        	newCellStyleTitulo.setFont(headerFont);        	
//        	newCellStyleTitulo.setFillForegroundColor(IndexedColors.DARK_BLUE.index); 
//        	newCellStyleTitulo.setFillPattern(CellStyle.SOLID_FOREGROUND); 
        	newCellStyleTitulo.setFillBackgroundColor(IndexedColors.BLUE.getIndex());
//        	newCellStyleTitulo.setFillForegroundColor(IndexedColors.DARK_GREEN.getIndex());
        	newCellStyleTitulo.setFillPattern(CellStyle.SOLID_FOREGROUND);
//        	newCellStyleTitulo.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//        	newCellStyleTitulo.setAlignment(CellStyle.ALIGN_CENTER);
//        	newCellStyleTitulo.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        }
        cell.setCellStyle(newCellStyleTitulo);
    }
    
}