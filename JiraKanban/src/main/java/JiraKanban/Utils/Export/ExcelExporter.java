package JiraKanban.Utils.Export;

import JiraKanban.JiraService.JiraIssue;
import JiraKanban.JiraService.JiraService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Класс для выгрузки записей в Excel
 */
public class ExcelExporter {
    private static Workbook workbook;
    private static ArrayList<JiraIssue> issueList;
    private static Sheet sheet;

    private static final int COLUMN_QUANTITY = 8;

    public static void exportAllRecords() throws IOException {
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("Issues");
        createHeader();

        issueList = JiraService.getIssueList();

        int i = 1;
        for(JiraIssue issue: issueList){
            Row row = sheet.createRow(i++);

            Cell label = row.createCell(0);
            Cell hyperLink = row.createCell(1);
            Cell createInfo = row.createCell(2);
            Cell noteSummary = row.createCell(3);
            Cell assigneeInfo = row.createCell(4);
            Cell priority = row.createCell(5);
            Cell status = row.createCell(6);
            Cell issueType = row.createCell(7);

            label.setCellValue(issue.getLabel());
            hyperLink.setCellValue(issue.getHyperlink());
            createInfo.setCellValue(issue.getCreateInfo());
            noteSummary.setCellValue(issue.getNoteSummary());
            assigneeInfo.setCellValue(issue.getAssigneeInfo());
            priority.setCellValue(issue.getPriority());
            status.setCellValue(issue.getStatus());
            issueType.setCellValue(issue.getIssueType());
        }

        setAutoSizeAllColumns();

        // Формируем исходящий excel файл и записываем в него данные
        workbook.write(new FileOutputStream("Jira_Kanban_Desk.xlsx"));
    }

    public static void createHeader(){
        Row row = sheet.createRow(0);

        Cell label = row.createCell(0);
        Cell hyperLink = row.createCell(1);
        Cell createInfo = row.createCell(2);
        Cell noteSummary = row.createCell(3);
        Cell assigneeInfo = row.createCell(4);
        Cell priority = row.createCell(5);
        Cell status = row.createCell(6);
        Cell issueType = row.createCell(7);

        label.setCellValue("Инцидент");
        hyperLink.setCellValue("Ссылка");
        createInfo.setCellValue("Информация о создании");
        noteSummary.setCellValue("Описание");
        assigneeInfo.setCellValue("Исполнитель");
        priority.setCellValue("Приоритет");
        status.setCellValue("Статус");
        issueType.setCellValue("Тип");
    }

    /**
     * Автоматическое выравнивание колнок
     */
    private static void setAutoSizeAllColumns(){
        for(int i = 0; i < COLUMN_QUANTITY; i++){
            sheet.autoSizeColumn(i, true);
        }
    }
}
