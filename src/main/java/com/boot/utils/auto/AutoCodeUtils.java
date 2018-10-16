package com.boot.utils.auto;

import com.boot.utils.StringUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.sql.*;
import java.util.*;

public class AutoCodeUtils {

    Logger logger = LoggerFactory.getLogger(getClass());

    public static final char UNDERLINE = '_';

    public static final Map<String,String> FIELD_TYPE_MAP = new HashMap<>();
    static {
        FIELD_TYPE_MAP.put("TINYINT","String");
        FIELD_TYPE_MAP.put("CHAR","String");
        FIELD_TYPE_MAP.put("VARCHAR","String");
        FIELD_TYPE_MAP.put("LONGVARCHAR","String");
        FIELD_TYPE_MAP.put("CLOB","String");
        FIELD_TYPE_MAP.put("NUMERIC","BigDecimal");
        FIELD_TYPE_MAP.put("DECIMAL","BigDecimal");
        FIELD_TYPE_MAP.put("BIT","boolean");
        FIELD_TYPE_MAP.put("TINYINT","byte");
        FIELD_TYPE_MAP.put("SMALLINT","short");
        FIELD_TYPE_MAP.put("INT","int");
        FIELD_TYPE_MAP.put("INTEGER","int");
        FIELD_TYPE_MAP.put("BIGINT","long");
        FIELD_TYPE_MAP.put("REAL","float");
        FIELD_TYPE_MAP.put("FLOAT","double");
        FIELD_TYPE_MAP.put("DOUBLE","double");
        FIELD_TYPE_MAP.put("BINARY","byte[]");
        FIELD_TYPE_MAP.put("VARBINARY","byte[]");
        FIELD_TYPE_MAP.put("LONGVARBINARY","byte[]");
        FIELD_TYPE_MAP.put("DATETIME","Date");
        FIELD_TYPE_MAP.put("TIME","Time");
        FIELD_TYPE_MAP.put("TIMESTAMP","Timestamp");
    }
 
    private ConnDB connDB;
    private String[] tableNames;
    private String packageName;
    private String templateDir;
    private String srcFile;

    public AutoCodeUtils(ConnDB connDB, String packageName, String[] tableNames,String srcFile, String templateDir) {
        this.connDB = connDB;
        this.packageName = packageName;
        this.tableNames = tableNames;
        this.templateDir = templateDir;
        this.srcFile = srcFile;
    }

  
    public void autoCreateCode(String[] fileNameArray,List<Method> methodList) throws Exception {
        Configuration cfg = new Configuration();
        cfg.setDefaultEncoding("utf-8");
        cfg.setDirectoryForTemplateLoading(new File(templateDir));

        File dir = new File(srcFile + "\\");
        //检查目录是否存在，不存在则创建
        if (!dir.exists()) {
            dir.mkdir();
        }

        List<Table> tableList = queryTable();
        if (tableList == null){
            System.out.println("======================没有需要处理的表===============");
        }

        for (Table table : tableList){
            String className = underlineToCamel(table.getTableName());
            List<Field> columnList = getColumns(table.getTableName());
            //设置模板文件路径
            Map<String, Object> rootMap = new HashMap<String, Object>();
            rootMap.put("packageName", packageName);
            rootMap.put("tableName", table);
            rootMap.put("className", className);
            rootMap.put("classDesc", table.getTableComment());
            rootMap.put("columnList", columnList);
            rootMap.put("methodList", methodList);

            for (int i = 0; i < fileNameArray.length; i++) {
                String pre = "";
                if (fileNameArray[i].equals("service")){
                    pre = "I";
                }

                String newFile = srcFile +"\\"+fileNameArray[i];
                File dir2 = new File(newFile + "\\");
                //检查目录是否存在，不存在则创建
                if (!dir2.exists()) {
                    dir2.mkdir();
                }

                String fileName = pre+ StringUtil.upperFirstCase(className) + StringUtil.upperFirstCase(fileNameArray[i]) + ".java";
                File docFile = new File(newFile+ "\\" +fileName);
                if(docFile.exists()){
                    System.out.println("==============文件已经存在请检查后重试===============");
                    continue;
                }
                Writer docout = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
                Template temp = cfg.getTemplate(fileNameArray[i]+"Class.ftl");
                temp.process(rootMap, docout);//输出文件
                docout.close();
            }
        }
        System.out.println("==============文件生产成功===============");
    }

    public void autoAddCode(String tableName,String[] methodArray,List<Method> methodList) throws Exception {
        Configuration cfg = new Configuration();
        cfg.setDefaultEncoding("utf-8");
        cfg.setDirectoryForTemplateLoading(new File(templateDir));

        File dir = new File(srcFile + "\\");
        //检查目录是否存在，不存在则创建
        if (!dir.exists()) {
            System.out.println("=================目标文件不存在================");
            return;
        }

        String className = underlineToCamel(tableName);
        //设置模板文件路径
        Map<String, Object> rootMap = new HashMap<String, Object>();
        rootMap.put("packageName", packageName);
        rootMap.put("className", className);
        rootMap.put("tableName", tableName);
        rootMap.put("methodList", methodList);

        for (int i = 0; i < methodArray.length; i++){
            String pre = "";
            if (methodArray[i].equals("service")){
                pre = "I";
            }
            String fileName = pre + StringUtil.upperFirstCase(className) + StringUtil.upperFirstCase(methodArray[i]) + ".java";

            File dir3 = new File(srcFile + "\\temp\\");
            //检查目录是否存在，不存在则创建
            if (!dir3.exists()) {
                dir3.mkdir();
            }

            File tmpFile = new File(srcFile +"\\temp\\" +pre+ fileName);
            Writer docout = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tmpFile)));

            Template temp = cfg.getTemplate(methodArray[i]+"Method.ftl");
            temp.process(rootMap, docout);//输出文件
            docout.close(); // 关闭文件流

            File toFile = new File(srcFile +"\\"+methodArray[i]+"\\"+fileName);
            copyFile(toFile,tmpFile,"}\r\n");// 追加内容
            tmpFile.delete(); // 删除临时文件
            dir3.delete();// 删除临时文件夹

            if("mapper".equals(methodArray[i])){
                String fileName2 = pre + StringUtil.upperFirstCase(className) + StringUtil.upperFirstCase(methodArray[i]) + ".xml";
                File dir32 = new File(srcFile + "\\temp\\");
                //检查目录是否存在，不存在则创建
                if (!dir32.exists()) {
                    dir32.mkdir();
                }
                File tmpFile2 = new File(srcFile +"\\temp\\" +pre+ fileName2);
                Writer docout2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tmpFile2)));
                Template temp2 = cfg.getTemplate(methodArray[i]+"Xml.ftl");
                temp2.process(rootMap, docout2);//输出文件
                docout2.close(); // 关闭文件流

                File toFile2 = new File(srcFile +"\\"+methodArray[i]+"\\"+fileName2);
                copyFile(toFile2,tmpFile2,"</mapper>\r\n");// 追加内容
                tmpFile2.delete(); // 删除临时文件
                dir32.delete();// 删除临时文件夹
            }
        }

        System.out.println("==============文件追加成功===============");
    }

    /**
     * 文件最后追加。
     */
    private static void copyFile(File toFile, File fromFile,String endStr) throws Exception{
        BufferedReader readTo = null;
        BufferedReader readFrom = null;
        FileWriter write = null;
        String tempTo;
        String tempFrom;
        try {
            readTo = new BufferedReader(new FileReader(toFile));
            List<String> xList = new LinkedList<>();

            while((tempTo = readTo.readLine())!=null){
                xList.add(tempTo);
            }

            write = new FileWriter(toFile, false);
            for (int i=0; i < xList.size()-1;i++){
                write.write(xList.get(i)+"\r\n");
            }
            write.write("\r\n");

            readFrom = new BufferedReader(new FileReader(fromFile));
            while((tempFrom = readFrom.readLine())!=null){
                write.write(tempFrom+"\r\n");
            }

            write.write(endStr);

            System.out.println("---- 插入完毕 ----");
            write.close();
            readTo.close();
            readFrom.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (write != null){
                write.close();
            }
            if (readTo != null){
                readTo.close();
            }
            if (readFrom != null){
                readFrom.close();
            }
        }
    }

    //其他数据库不需要这个方法 oracle和db2需要
    public String getSchema(Connection conn) throws Exception {
        String schema;
        schema = conn.getMetaData().getUserName();
        if ((schema == null) || (schema.length() == 0)) {
            throw new Exception("ORACLE数据库模式不允许为空");
        }
        return schema.toUpperCase().toString();
    }


    public List<Table> queryTable(){
        Connection conn = null;
        try {
            Class.forName(connDB.getDriver()).newInstance();
            conn = DriverManager.getConnection(connDB.getUrl()+"/"+connDB.getSchema(), connDB.getName(),connDB.getPassWord());
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT table_name,table_comment FROM information_schema.tables WHERE table_schema = '"+connDB.getSchema()+"'");

            List<String> tableNameArray = Arrays.asList(tableNames);
            List<Table> tableList = new ArrayList<>();
            while (rs.next()) {
                String tabName = rs.getString("table_name");
                if (tableNames == null || !tableNameArray.contains(tabName)){
                    continue;
                }

                tableList.add(new Table(tabName,rs.getString("table_comment")));
            }
            return tableList;
        } catch (Exception e) {
            logger.error("自动化-获取表字段失败：",e);
            return null;
        } finally {
            if (conn!=null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    logger.error("自动化-关闭连接失败：",e);
                }
            }
        }
    }

    /**
     * 获取列信息
     * @return
     */
    public List<Field> getColumns(String tableName){
        Connection conn = null;
        try {
            Class.forName(connDB.getDriver()).newInstance();
            conn = DriverManager.getConnection(connDB.getUrl()+"/"+connDB.getSchema(), connDB.getName(),connDB.getPassWord());
            List<Field> dataList = new ArrayList<>();
            DatabaseMetaData dbMetaData = conn.getMetaData();
            ResultSet rs = dbMetaData.getColumns(null, getSchema(conn), tableName.toUpperCase(), "%");

            while (rs.next()) {
                // 字段
                String colName = underlineToCamel(rs.getString("COLUMN_NAME").replaceAll("\\s", ""));
                // 字段类型
                String colType = FIELD_TYPE_MAP.get(rs.getString("TYPE_NAME"));
                if (colType == null || colType.equals("")){
                    System.out.println("异常：无法识别类型---" + rs.getString("TYPE_NAME"));
                }
                // 注释
                String comment = rs.getString("REMARKS");
                dataList.add(new Field(colName,colType,comment));
            }
            return dataList;
        } catch (Exception e) {
            logger.error("自动化-获取表字段失败：",e);
            return null;
        } finally {
            if (conn!=null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    logger.error("自动化-关闭连接失败：",e);
                }
            }
        }
    }

    /**
     * 驼峰格式字符串转换为下划线格式字符串
     *
     * @param param
     * @return
     */
    public static String camelToUnderline(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append(UNDERLINE);
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 下划线格式字符串转换为驼峰格式字符串
     *
     * @param param
     * @return
     */
    public static String underlineToCamel(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }

        param = param.toLowerCase();

        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (c == UNDERLINE) {
                if(i == 1){
                    continue;
                }
                if (++i < len) {
                    sb.append(Character.toUpperCase(param.charAt(i)));
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
