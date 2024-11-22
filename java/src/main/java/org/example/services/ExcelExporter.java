package org.example.services;


import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.models.Evenement;

import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelExporter {

    public static void exportToExcel(TableView<Evenement> tableView, String filePath) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Events");

        // Create header row
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("NOMEVENT");
        headerRow.createCell(2).setCellValue("NBR_MAX");
        headerRow.createCell(3).setCellValue("Category");
        headerRow.createCell(4).setCellValue("DATE");
        headerRow.createCell(5).setCellValue("LIEU");

        // Populate data rows
        ObservableList<Evenement> data = tableView.getItems();
        for (int i = 0; i < data.size(); i++) {
            Row row = sheet.createRow(i + 1);
            Evenement event = data.get(i);
            row.createCell(0).setCellValue(event.getId());
            row.createCell(1).setCellValue(event.getNom_event());
            row.createCell(2).setCellValue(event.getMax_places_event());
            row.createCell(3).setCellValue(event.getCate().getNom());
            row.createCell(4).setCellValue(event.getDate_event().toString());
            row.createCell(5).setCellValue(event.getLieu_event());

        }

        // Write workbook to file
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
            workbook.close();
            System.out.println("Excel file exported successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
