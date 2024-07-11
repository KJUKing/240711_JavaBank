package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JDBCMain {
    public static void main(String[] args) {
        JDBCUtil jdbc = JDBCUtil.getInstance();

//        String sql = "SELECT * FROM MEMBER WHERE MEM_ID = ? AND MEM_PASS = ?";
//        List<Object> param = new ArrayList<>();
//        param.add("a001");
//        param.add("asdfasdf");
//        Map<String, Object> map = jdbc.selectOne(sql, param);
//
//        System.out.println(map);

//        String sql = " SELECT * FROM MEMBER WHERE MEM_LIKE = ?";
//        List<Object> param = new ArrayList<>();
//        param.add("볼링");
//        List<Map<String, Object>> list = jdbc.selectList(sql, param);
//        for (Map<String, Object> map : list) {
//            System.out.println(map);
//        }

//          String sql = "UPDATE JAVA_MEMBER\n" + "SET MEM_NAME = ? \n" + "WHERE MEM_NO = ?";
//
//        List<Object> param = new ArrayList<>();
//        param.add("경주");
//        param.add(3);
//
//        int num = jdbc.update(sql, param);
//        System.out.println(num + "행 변경되었습니다");

        String sql = "DELETE \n" +
                "FROM JAVA_MEMBER\n" +
                "WHERE MEM_NO =3";
        jdbc.update(sql);
    }
}
