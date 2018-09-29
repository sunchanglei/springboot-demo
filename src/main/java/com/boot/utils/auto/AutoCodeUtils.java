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
    private static String[][] fileNameArray = new String[7][3];
    static {
        fileNameArray[0][0] = "dtfClass.ftl";
        fileNameArray[0][1] = "Dtf.java";
        fileNameArray[0][2] = "dtf";

        fileNameArray[1][0] = "serviceClass.ftl";
        fileNameArray[1][1] = "Service.java";
        fileNameArray[1][2] = "service";

        fileNameArray[2][0] = "serviceImplClass.ftl";
        fileNameArray[2][1] = "ServiceImpl.java";
        fileNameArray[2][2] = "serviceImpl";

        fileNameArray[3][0] = "daoClass.ftl";
        fileNameArray[3][1] = "Dao.java";
        fileNameArray[3][2] = "dao";

        fileNameArray[4][0] = "voClass.ftl";
        fileNameArray[4][1] = "Vo.java";
        fileNameArray[4][2] = "vo";

        fileNameArray[5][0] = "boClass.ftl";
        fileNameArray[5][1] = "Bo.java";
        fileNameArray[5][2] = "bo";

        fileNameArray[6][0] = "mapperClass.ftl";
        fileNameArray[6][1] = "Mapper.java";
        fileNameArray[6][2] = "mapper";
    }

    private static String[][] methodArray = new String[1][2];
    static {
//        methodArray[0][0] = "controllerMethod.ftl";
//        methodArray[0][1] = "Controller.java";
//
//        methodArray[1][0] = "dtfMethod.ftl";
//        methodArray[1][1] = "Dtf.java";
//
//        methodArray[2][0] = "serviceMethod.ftl";
//        methodArray[2][1] = "Service.java";
//
//        methodArray[3][0] = "serviceImplMethod.ftl";
//        methodArray[3][1] = "ServiceImpl.java";
//
//        methodArray[4][0] = "daoMethod.ftl";
//        methodArray[4][1] = "Dao.java";

        methodArray[0][0] = "mapperMethod.ftl";
        methodArray[0][1] = "Mapper.java";
    }

    public AutoCodeUtils(ConnDB connDB, String packageName, String[] tableNames,String srcFile, String templateDir) {
        this.connDB = connDB;
        this.packageName = packageName;
        this.tableNames = tableNames;
        this.templateDir = templateDir;
        this.srcFile = srcFile;
    }

  
    public void autoCreateCode() throws Exception {
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
            String fileName = StringUtil.upperFirstCase(className);
            List<Field> columnList = getColumns(table.getTableName());
            //设置模板文件路径
            Map<String, Object> rootMap = new HashMap<String, Object>();
            rootMap.put("packageName", packageName);
            rootMap.put("className", className);
            rootMap.put("classDesc", table.getTableComment());
            rootMap.put("columnList", columnList);

            for (int i = 0; i < fileNameArray.length; i++) {
                String pre = "";
                if (fileNameArray[i][1].equals("Service.java")){
                    pre = "I";
                }

                String newFile = srcFile +"\\"+fileNameArray[i][2];
                File dir2 = new File(newFile + "\\");
                //检查目录是否存在，不存在则创建
                if (!dir2.exists()) {
                    dir2.mkdir();
                }

                File docFile = new File(newFile+ "\\" +pre+ fileName + fileNameArray[i][1]);
                Writer docout = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
                Template temp = cfg.getTemplate(fileNameArray[i][0]);
                temp.process(rootMap, docout);//输出文件
                docout.close();
            }
        }
        System.out.println("==============文件生产成功===============");
    }

    public void autoAddCode(String tableName,Map<String, Object> rootMap) throws Exception {

        Configuration cfg = new Configuration();
        cfg.setDefaultEncoding("utf-8");
        cfg.setDirectoryForTemplateLoading(new File(templateDir));

        File dir = new File(srcFile + "\\");
        //检查目录是否存在，不存在则创建
        if (!dir.exists()) {
            System.out.println("=================目标文件不存在================");
            return;
        }

        for (int i = 0; i < methodArray.length; i++){

            String pre = "";
            if (methodArray[i][1].equals("Service.java")){
                pre = "I";
            }

            File dir3 = new File(srcFile + "\\temp\\");
            //检查目录是否存在，不存在则创建
            if (!dir3.exists()) {
                dir3.mkdir();
            }

            File tmpFile = new File(srcFile +"\\temp\\" +pre+ tableName + methodArray[i][1]);
            Writer docout = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tmpFile)));

            Template temp = cfg.getTemplate(methodArray[i][0]);
            temp.process(rootMap, docout);//输出文件
            docout.close(); // 关闭文件流

            File toFile = new File(srcFile +"\\mapper"+"\\"+pre+ tableName + methodArray[i][1]);
            copyFile(toFile,tmpFile);// 追加内容
            tmpFile.delete(); // 删除临时文件
            dir3.delete();// 删除临时文件夹
        }

        System.out.println("==============文件追加成功===============");
    }

    /**
     * 文件最后追加。
     */
    private static void copyFile(File toFile, File fromFile) throws Exception{
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

            write.write("}\r\n");

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
