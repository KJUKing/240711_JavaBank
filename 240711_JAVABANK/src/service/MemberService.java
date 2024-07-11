package service;

import controller.MainController;
import dao.MemberDao;
import vo.AccountVo;
import vo.BankVo;
import vo.MemberVo;

import java.util.List;

public class MemberService {

    private static MemberService instance;

    private MemberService() {

    }

    public static MemberService getInstance() {
        if (instance == null) {
            instance = new MemberService();
        }
        return instance;
    }
    MemberDao dao = MemberDao.getInstance();


    public boolean login(List<Object> param) {
        MemberVo member = dao.login(param);
        if (member ==null) return false;
        MainController.sessionStorage.put("member", member);
        return true;
    }

    public void signUp(List<Object> param) {
        dao.signUp(param);
    }

    public List<AccountVo> movieList() {
        return dao.movieList();
    }

//    public MovieVo memberDetail(List<Object> param) { 굳이필요없음
//        return dao.memberDetail(param);
//    }




    public void accountCreate(List<Object> param) {
        dao.accountCreate(param);
    }

    public List<AccountVo> accountList() {
        return dao.accountList();
    }

    public boolean accountLogin(List<Object> param) {
        AccountVo account = dao.accountLogin(param);
        MainController.sessionStorage.put("account", account);
        return true;
    }


    public List<BankVo> depositList(List<Object> param) {
        return dao.depositList(param);
    }

    public List<BankVo> withDrawList(List<Object> param) {
        return dao.withDrawList(param);
    }

    public List<BankVo> depoWithList(List<Object> param) {
        return dao.depoWithList(param);
    }
}
