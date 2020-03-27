package com;

import jxl.Cell;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.read.biff.BiffException;
import jxl.write.*;




import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import static jxl.format.VerticalAlignment.CENTRE;
/*

 *导入jxl.jar;

 *后续扩充功能，sheet2增加测试报告展现；------待实现；
 *   <dependency>
            <groupId>net.sourceforge.jexcelapi</groupId>
            <artifactId>jxl</artifactId>
            <version>2.6.10</version>
        </dependency>

 */

public class CWOutputFile {
    /*

     * wOutputFile方法写结果文件

     * wOutputFile(文件路径，案例编号，测试验证点，预期结果，实际结果，错误码，状态码，响应结果)

     */

    public  void wOutputFile(String filepath, String caseNo,

                             String testPoint, String preResult, String fresult, String errCode,

                             String status, String respond) throws IOException, BiffException, WriteException {

        //创建文件（说明：指向文件，而不是目录）
        File output = new File(filepath);
        String result = "";
        //接着创建输入流，
        InputStream instream = new FileInputStream(filepath);
        //获取Excel文件对象，打开工作表
        Workbook readwb=Workbook.getWorkbook(instream);

        WritableWorkbook wbook =Workbook.createWorkbook(output,readwb); // 根据文件创建一个操作对象 // 打开一个文件的副本，并且指定数据写回到原文件
        // 添加一个工作表
        WritableSheet readsheet=wbook.getSheet(0);

        // int rsColumns=readsheet.getColumns();//获取Sheet表中所包含的总列数
        int rsRows=readsheet.getRows(); // 获取Sheet表中所包含的总行数
        /********************************字体样式设置 ****************************/

        WritableFont font =new WritableFont(WritableFont.createFont("宋体"),10,WritableFont.NO_BOLD);//字体样式
        WritableCellFormat wcf=new WritableCellFormat(font);
        /***********************************************************************/
        Cell cell1=readsheet.getCell(0,rsRows);//读取单元格的内容
        if (cell1.getContents().equals("")){
            Label labetest1 =new Label(0,rsRows,caseNo);//第1列---案例编号
            Label labetest2 =new Label(1,rsRows,testPoint);//第2列---验证测试点；
            Label labetest3 = new Label(2, rsRows, preResult); // 第3列--预期结果；
            Label labetest4 = new Label(3, rsRows, fresult);  // 第4列--实际结果；
            Label labetest5 = new Label(4, rsRows, errCode);  // 第5列--错误码；
            if (preResult==fresult){
                result="通过";
                wcf.setBackground(Colour.BRIGHT_GREEN);// 通过案例标注绿色
            }else {
                result="不通过";
                wcf.setBackground(Colour.RED);// 不通过案例标注红色
            }
            Label labetest6 = new Label(5, rsRows, result, wcf); // 第6列--执行结果；

            Label labetest7 = new Label(6, rsRows, status);      // 第7列--状态码

            Label labetest8 = new Label(7, rsRows, respond);    // 第8列--响应结果
            readsheet.addCell(labetest1);  //把结果增加进去
            readsheet.addCell(labetest2);
            readsheet.addCell(labetest3);
            readsheet.addCell(labetest4);
            readsheet.addCell(labetest5);
            readsheet.addCell(labetest6);
            readsheet.addCell(labetest7);
            readsheet.addCell(labetest8);
        }
        wbook.write();
        wbook.close();
    }

    /*

     * cOutputFile方法创建输出文件，传入参数为交易类型，如开户等；

     * cOutputFile方法返回文件路径，作为wOutputFile的入参；

     */

    public String cOutputFile(String  tradeType) throws IOException, WriteException {
        String temp_str="";
        Date dt=  new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        temp_str=sdf.format(dt);// 获取时间戳

        // 相对路径默认为 apache-jmeter-5.1\bin
        String filepath="D:\\软件测试学习\\"+tradeType+"_output_" + "_" + temp_str + ".xls"; // 以时间戳命名结果文件，确保唯一

        File output=new File(filepath);
        if (!output.isFile()){
            output.createNewFile();// 如果指定文件不存在，则新建该文件
            WritableWorkbook writeBook=Workbook.createWorkbook(output);

            WritableSheet Sheet =writeBook.createSheet("输出结果",0);//createSheet(sheet名称,第几个sheet)
            WritableFont headfont=new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.BOLD); // 字体样式
            WritableCellFormat headwcf=new WritableCellFormat(headfont);
            headwcf.setBackground(Colour.GRAY_25);//灰色颜色
            Sheet.setColumnView(0,11);//设置列宽度setColumnView(列号，宽度)
            Sheet.setColumnView(1,30);
            Sheet.setColumnView(2, 35);

            Sheet.setColumnView(3, 35);

            Sheet.setColumnView(4, 18);

            Sheet.setColumnView(5, 11);

            Sheet.setColumnView(6, 11);

            Sheet.setColumnView(7, 50);

            headwcf.setAlignment(Alignment.CENTRE);// 设置文字居中对齐方式;
            headwcf.setVerticalAlignment(VerticalAlignment.CENTRE);// 设置垂直居中;
            Label labe00=new Label(0,0,"案例编号",headwcf);//Label(列号,行号, 内容)
            Label labe10 = new Label(1, 0, "验证测试点", headwcf);

            Label labe20 = new Label(2, 0, "预期结果", headwcf);

            Label labe30 = new Label(3, 0, "实际结果", headwcf);

            Label labe40 = new Label(4, 0, "错误码", headwcf);

            Label labe50 = new Label(5, 0, "执行结果", headwcf);

            Label labe60 = new Label(6, 0, "返回状态", headwcf);

            Label labe70 = new Label(7, 0, "响应结果", headwcf);
            Sheet.addCell(labe00);

            Sheet.addCell(labe10);

            Sheet.addCell(labe20);

            Sheet.addCell(labe30);

            Sheet.addCell(labe40);

            Sheet.addCell(labe50);

            Sheet.addCell(labe60);

            Sheet.addCell(labe70);

            writeBook.write();

            writeBook.close();

        }
        return filepath;
    }

}

