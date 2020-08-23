package io;

import lombok.extern.slf4j.Slf4j;
import model.ExcelModel;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author lmc
 * @date 2020/8/22 10:13
 */
@Slf4j
public class ExcelOutMagicBox {

    private static List<String> CELL_HEADS; //列头

    static {
        // 类装载时就载入指定好的列头信息，如有需要，可以考虑做成动态生成的列头
        CELL_HEADS = new ArrayList<>();
        CELL_HEADS.add("id");
        CELL_HEADS.add("姓名");
    }

    /**
     * 生成Excel并写入数据信息
     *
     * @param dataList 数据列表
     * @return 写入数据后的工作簿对象
     */
    public static void exportData(List<ExcelModel> dataList) throws Exception {
        // 生成xlsx的Excel
        Workbook workbook = new SXSSFWorkbook();

        // 如需生成xls的Excel，请使用下面的工作簿对象，注意后续输出时文件后缀名也需更改为xls
        //Workbook workbook = new HSSFWorkbook();

        // 生成Sheet表，写入第一行的列头
        Sheet sheet = buildDataSheet(workbook);
        //构建每行的数据内容
        int rowNum = 1;
        for (Iterator<ExcelModel> it = dataList.iterator(); it.hasNext(); ) {
            ExcelModel data = it.next();
            if (data == null) {
                continue;
            }
            //输出行数据
            Row row = sheet.createRow(rowNum++);
            convertDataToRow(data, row);
        }

        // 写入Excel文件到前端
        if (null != workbook) {
            String excelName = "data导出";
            String fileName = excelName  + ".xlsx";
            fileName = new String(fileName.getBytes("UTF-8"), "UTF-8");
            String project_path = System.getProperty("user.dir");
            fileName = project_path + "/file/out/" + fileName;
            FileOutputStream fileOutputStream = new FileOutputStream(new File(fileName));
            workbook.write(fileOutputStream);
        }
    }

    public void exportExcel(HttpServletRequest request, HttpServletResponse response) {
        Workbook workbook = null;
        OutputStream out = null;
        try {
            // todo 根据业务需求获取需要写入Excel的数据列表 dataList

            // 生成Excel工作簿对象并写入数据
            exportData(new ArrayList<>());

            // 写入Excel文件到前端
            if (null != workbook) {
                String excelName = "示例Excel导出";
                String fileName = excelName  + ".xlsx";
                fileName = new String(fileName.getBytes("UTF-8"), "iso8859-1");
                response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
                response.setContentType("application/x-download");
                response.setCharacterEncoding("UTF-8");
                response.addHeader("Pargam", "no-cache");
                response.addHeader("Cache-Control", "no-cache");
                response.flushBuffer();
                out = response.getOutputStream();
                workbook.write(out);
                out.flush();
            }
        } catch (Exception e) {
            log.info("写入Excel过程出错！错误原因：" + e.getMessage());
        } finally {
            try {
                if (null != workbook) {
                    workbook.close();
                }
                if (null != out) {
                    out.close();
                }
            } catch (IOException e) {
                log.info("关闭workbook或outputStream出错！");
            }
        }
    }

    /**
     * 将数据转换成行
     *
     * @param data 源数据
     * @param row  行对象
     * @return
     */
    private static void convertDataToRow(ExcelModel data, Row row) {
        int cellNum = 0;
        Cell cell;

        // id
        cell = row.createCell(cellNum++);
        cell.setCellValue(null == data.getId() ? 0 : data.getId());

        // 姓名
        cell = row.createCell(cellNum++);
        cell.setCellValue(null == data.getName() ? "" : data.getName());

//        // 年龄
//        cell = row.createCell(cellNum++);
//        if (null != data.getAge()) {
//            cell.setCellValue(data.getAge());
//        } else {
//            cell.setCellValue("");
//        }
//        // 所在城市
//        cell = row.createCell(cellNum++);
//        cell.setCellValue(null == data.getLocation() ? "" : data.getLocation());
//        // 职业
//        cell = row.createCell(cellNum++);
//        cell.setCellValue(null == data.getJob() ? "" : data.getJob());
    }

    /**
     * 生成sheet表，并写入第一行数据（列头）
     *
     * @param workbook 工作簿对象
     * @return 已经写入列头的Sheet
     */
    private static Sheet buildDataSheet(Workbook workbook) {
        Sheet sheet = workbook.createSheet();
        // 设置列头宽度
        for (int i = 0; i < CELL_HEADS.size(); i++) {
            sheet.setColumnWidth(i, 4000);
        }
        // 设置默认行高
        sheet.setDefaultRowHeight((short) 400);
        // 构建头单元格样式
        CellStyle cellStyle = buildHeadCellStyle(sheet.getWorkbook());
        // 写入第一行各列的数据
        Row head = sheet.createRow(0);
        for (int i = 0; i < CELL_HEADS.size(); i++) {
            Cell cell = head.createCell(i);
            cell.setCellValue(CELL_HEADS.get(i));
            cell.setCellStyle(cellStyle);
        }
        return sheet;
    }

    /**
     * 设置第一行列头的样式
     *
     * @param workbook 工作簿对象
     * @return 单元格样式对象
     */
    private static CellStyle buildHeadCellStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        //对齐方式设置
        style.setAlignment(HorizontalAlignment.CENTER);
        //边框颜色和宽度设置
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex()); // 下边框
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex()); // 左边框
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex()); // 右边框
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex()); // 上边框
        //设置背景颜色
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        //粗体字设置
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        return style;
    }


}
