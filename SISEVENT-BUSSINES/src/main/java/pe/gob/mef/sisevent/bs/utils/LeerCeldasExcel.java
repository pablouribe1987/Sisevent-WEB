package pe.gob.mef.sisevent.bs.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

public class LeerCeldasExcel {

	private static final Logger log = Logger.getLogger(LeerCeldasExcel.class.getName());
	
	public LeerCeldasExcel() {
		// TODO Auto-generated constructor stub
	}
	
	public static List<Celdas> readExcel(File archivo, int iprimerafila, int tolerancia, String charset) throws IOException, BiffException {
		if (archivo.exists()) {
//			File archivo = new File(sFilename);
			if (archivo.exists() && archivo.isFile()) {
				int ihoja = 0;
//				int iprimerafila = 0;
				int iprimercolumna = 0;
				
				Workbook workbook = Workbook.getWorkbook(archivo);
				if(charset==null){
					workbook = Workbook.getWorkbook(archivo);
				}else{
					WorkbookSettings ws = new WorkbookSettings();
					ws.setEncoding(charset);
					ws.setPrintHelpEncode(false);
					workbook = Workbook.getWorkbook(archivo,ws);
				}
				// Gets the sheet
				Sheet sheet = workbook.getSheet(ihoja);

				int inumerofilas = -1;
				int iultimacolumna = -1;
				int contarfilas = iprimerafila;
				
				int contarTolerancia = 0;
				
				while (true) {
					int contarcolumna = iprimercolumna;
					try {
						Cell cell = sheet.getCell(contarcolumna, contarfilas);
						if (cell == null) {
							contarTolerancia++;
//							log.info("NULO en: Columna= " + (contarcolumna) + " Fila= " + (contarfilas)+" VAN "+contarcolumna+" DE "+tolerancia);
							if(contarTolerancia>=tolerancia){
								break;
							}else{
								contarfilas++;
								continue;
							}							
						} else {
							while (true) {
								try {
									contarcolumna++;
									cell = sheet.getCell(contarcolumna, contarfilas);
									if (cell == null) {
//										log.info("Parar Columna en: Columna= " + (contarcolumna) + " Fila= "+ (contarfilas));
										break;
									} else {
										String celda = cell.getContents();
//										log.info("Parar Columna en: Columna= " + (contarcolumna) + " Fila= "
//												+ (contarfilas) + " VALOR " + celda);
									}
								} catch (java.lang.ArrayIndexOutOfBoundsException e) {
//									System.out.println("Parar Columna en: Columna= " + (contarcolumna) + " Fila= "
//											+ (contarfilas));
									break;
								}
							}
							iultimacolumna = Math.max(contarcolumna, iultimacolumna);
						}
					} catch (java.lang.ArrayIndexOutOfBoundsException e) {
//						System.out.println("Parar en: Columna= " + (contarcolumna) + " Fila= " + (contarfilas));
						break;
					}
					contarfilas++;
				}

				List<Celdas> excelreadingr = new ArrayList<Celdas>();
				inumerofilas = contarfilas;
				// String sColumns[][] = new String[(iultimacolumna - iprimercolumna)][(inumerofilas - iprimerafila)];
				/* Reads cell data */

				for (int ifila = 0; ifila < (inumerofilas - iprimerafila); ifila++) {
					int contarcolumna = 1;
					Celdas celda = new Celdas();
					for (int icolumna = 0; icolumna < (iultimacolumna - iprimercolumna); icolumna++) {
						Cell cell = null;
						try {
							cell = sheet.getCell(icolumna + iprimercolumna, ifila + iprimerafila);
						} catch (java.lang.ArrayIndexOutOfBoundsException e) {
//							System.out.println("No data: " + e);
						}

						if (cell == null) {
							FuncionesStaticas.setValorInItem(celda, "celda" + icolumna, new String(""));
						} else {
							String celdavalor = cell.getContents();							
//							if(celdavalor.indexOf("TELEF")>-1){
//								UTF8Util.imprimirConTodosLosEncodes(cell.getContents());
//								UTF8Util.imprimirConTodosLosEncodes(cell.getContents().getBytes());
//								UTF8Util.imprimirConTodosYTodosLosEncodes(cell.getContents(), "TELEFÓNICO");
//							}
							FuncionesStaticas.setValorInItem(celda, "celda" + icolumna, celdavalor);
						} 
						contarcolumna++;
						if (contarcolumna >= 30)
							break;
					}
					excelreadingr.add(celda);
				}
				workbook.close();				
				return excelreadingr;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

}
