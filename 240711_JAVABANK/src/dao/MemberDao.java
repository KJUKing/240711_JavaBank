package dao;

import util.JDBCUtil;
import vo.AccountVo;
import vo.BankVo;
import vo.MemberVo;

import java.util.List;

public class MemberDao {

    private static MemberDao instance;

    private MemberDao() {

    }

    public static MemberDao getInstance() {
        if (instance == null) {
            instance = new MemberDao();
        }
        return instance;
    }
    JDBCUtil jdbc = JDBCUtil.getInstance();


    public MemberVo login(List<Object> param) {
        String sql = "SELECT *\n" +
                "FROM BANK_MEMBER\n" +
                "WHERE MEM_ID = ?\n" +
                "AND MEM_PASS = ?\n";

        return jdbc.selectOne(sql, param, MemberVo.class);
    }

    public void signUp(List<Object> param) {
        String sql = "INSERT INTO BANK_MEMBER VALUES ((SELECT NVL(MAX(MEM_NO),0)+1\n" +
                "                                    FROM BANK_MEMBER),\n" +
                "                                    ?, ?, ?, SYSDATE)";
        jdbc.update(sql, param);
    }

    public List<AccountVo> movieList() {
        String sql = "SELECT * FROM MOVIE";
        return jdbc.selectList(sql, AccountVo.class);
    }


    public void memberDelete(List<Object> param) {
        String sql = " UPDATE MEMBER " +
                "      SET DELYN = 'Y'" +
                "      WHERE MEM_NO = ?";
        jdbc.update(sql, param);
    }

    public void accountCreate(List<Object> param) {
        String sql = "INSERT INTO ACCOUNT VALUES ((SELECT NVL(MAX(ACCOUNT_NO),0)+1\n" +
                "                                    FROM ACCOUNT),\n" +
                "                                    ?, SYSDATE, 0)";
        jdbc.update(sql, param);
    }

    public AccountVo accountLogin(List<Object> param) {
            String sql = "SELECT *\n" +
                    "FROM ACCOUNT\n" +
                    "WHERE FK_MEM_NO = ?";
            return jdbc.selectOne(sql, param, AccountVo.class);
    }

    public List<AccountVo> accountList() {
        String sql = "SELECT * FROM ACCOUNT";

        return jdbc.selectList(sql, AccountVo.class);


    }

    public List<BankVo> depositList(List<Object> param) {
        String sql = "SELECT * FROM JAVA_BANK" +
                "     WHERE DEPWIT_CNT = 1" +
                "     AND FK_ACCOUNT_NO = ?";
        return jdbc.selectList(sql, param, BankVo.class);
    }

    public List<BankVo> withDrawList(List<Object> param) {
        String sql = "SELECT * FROM JAVA_BANK" +
                "     WHERE DEPWIT_CNT = 2" +
                "     AND FK_ACCOUNT_NO = ?";
        return jdbc.selectList(sql, param, BankVo.class);
    }

    public List<BankVo> depoWithList(List<Object> param) {
        String sql = "SELECT * FROM JAVA_BANK" +
                "     WHERE FK_ACCOUNT_NO = ?";
        return jdbc.selectList(sql, param, BankVo.class);
    }
}
