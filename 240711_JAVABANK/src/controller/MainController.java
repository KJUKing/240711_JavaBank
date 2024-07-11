package controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import service.MemberService;
import util.ScanUtil;
import view.View;
import vo.AccountVo;
import vo.BankVo;
import vo.MemberVo;

import static view.View.*;

public class MainController{
	static public Map<String, Object> sessionStorage = new HashMap<>();


	MemberService memberService = MemberService.getInstance();

	public static void main(String[] args) {
		new MainController().start();
	}

	private void start() {
		View view = View.MAIN;
		while (true) {
			switch (view) {
				case MAIN:
					view = main();
					break;
				case LOGIN:
					view = login();
					break;
				case ACCOUNT_CREATE:
					view = accountCreate();
					break;
				case ADMIN:
					view = admin();
					break;
				case MEMBER_SIGNUP:
					view = signUp();
					break;
				case ACCOUNT_LIST:
					view = accountList();
					break;
				case ACCOUNT_DETAIL:
					view = accountDetail();
					break;
				case DEPOSIT_INFO:
					view = depositInfo();
					break;
				case WITHDRAW_INFO:
					view = withDrawInfo();
					break;
				case DEPOWITH_INFO:
					view = depoWithInfo();
					break;
				case ACCOUNT_INFO:
					view = accountInfo();
					break;
				case DEPOWITH:
					view = depoWith();
					break;
				default:
					break;
			}
		}
	}

	private View depoWith() {
		System.out.println("1. 입금");
		System.out.println("2. 출금");
		System.out.println("3. 송금");
		int sel = ScanUtil.nextInt("숫자 입력 : ");
		switch (sel) {
			case 1: return DEPOSIT;

			case 2: return WITHDRAW;

			case 3: return TRANSFER;
		}
		return null;
	}

	private View depoWithInfo() {
		AccountVo account = (AccountVo) sessionStorage.get("account");
		int accountNo = account.getAccount_no();
		List<Object> param = new ArrayList<>();
		param.add(accountNo);

		List<BankVo> depoWithList = memberService.depoWithList(param);
		for (BankVo bankVo : depoWithList) {
			System.out.println(bankVo);
		}
		return ACCOUNT_INFO;
	}

	private View withDrawInfo() {
		AccountVo account = (AccountVo) sessionStorage.get("account");
		int accountNo = account.getAccount_no();
		List<Object> param = new ArrayList<>();
		param.add(accountNo);

		List<BankVo> withDrawList = memberService.withDrawList(param);
		for (BankVo bankVo : withDrawList) {
			System.out.println(bankVo);
		}
		return ACCOUNT_INFO;
	}

	private View depositInfo() {
		AccountVo account = (AccountVo) sessionStorage.get("account");
		int accountNo = account.getAccount_no();
		List<Object> param = new ArrayList<>();
		param.add(accountNo);

		List<BankVo> depositList = memberService.depositList(param);
		for (BankVo bankVo : depositList) {
			System.out.println(bankVo);
		}
		return ACCOUNT_INFO;
	}


	private View accountInfo() {
		//입금 1 출금 2
		System.out.println("1. 입금 내역");
		System.out.println("2. 출금 내역");
		System.out.println("3. 전체 내역");
		int sel = ScanUtil.nextInt("숫자 입력 : ");
		switch (sel) {
			case 1: return DEPOSIT_INFO;

			case 2: return WITHDRAW_INFO;

			case 3: return DEPOWITH_INFO;

		}
		return ACCOUNT_INFO;
	}

	private View accountDetail() {
		AccountVo account = (AccountVo) sessionStorage.get("account");
		System.out.println(account);

		System.out.println("1. 정보 조회");
		System.out.println("2. 입출금");

		int sel = ScanUtil.nextInt("숫자 입력 : ");

		if (sel == 1){
			return View.ACCOUNT_INFO;
		}
		if (sel == 2) {
			return View.DEPOWITH;
		} else {
			System.out.println("잘못된 접근입니다");
			return View.ACCOUNT_DETAIL;
		}
	}



	private View accountList() {
		if (sessionStorage.get("member") == null) {
			System.out.println("비로그인으로인한 잘못된 접근입니다.");
			System.out.println("로그인 창으로 이동합니다");
			sessionStorage.put("View", LOGIN);
			return View.LOGIN;
		}
		MemberVo member = (MemberVo) sessionStorage.get("member");
		int memNo = member.getMem_no();

		List<Object> param = new ArrayList<>();
		param.add(memNo);
		memberService.accountLogin(param);
		AccountVo account = (AccountVo) sessionStorage.get("account");
		int fkMemNo = account.getFk_mem_no();

		List<AccountVo> accountList = memberService.accountList();
		for (AccountVo accountVo : accountList) {
			System.out.println(accountVo);
		}

		System.out.println("1. 계좌 선택");
		System.out.println("2. 메인");

		int sel = ScanUtil.nextInt("입력 : ");
		switch (sel) {
			case 1: int account_no = ScanUtil.nextInt("계좌 MEM_NO : ");
				if (account_no == memNo && account_no == fkMemNo) {
					return ACCOUNT_DETAIL;
				} else {
					System.out.println("권한이 없습니다. 잘못된 접근입니다");
					return ACCOUNT_LIST;
				}
			case 2: return ADMIN;

			default: return ACCOUNT_LIST;
		}
	}

	private View admin() {
		MemberVo member = (MemberVo) sessionStorage.get("member");
		String memName = member.getMem_name();

		System.out.println("----------환영합니다! "+memName+"님----------");
		System.out.println();
		System.out.println("1. 계좌 생성");
		System.out.println("2. 계좌 리스트 조회");
		int sel = ScanUtil.menu();
		switch (sel) {
			case 1:
				return View.ACCOUNT_CREATE;
			case 2:
				return ACCOUNT_LIST;
			default:
				return View.ADMIN;
		}
	}

	private View signUp() {
		System.out.println("회원가입 화면입니다");
		System.out.println("개인 정보를 입력해주세요");

		String id = ScanUtil.nextLine("ID : ");
		String pw = ScanUtil.nextLine("PW : ");
		String name = ScanUtil.nextLine("이름 : ");

		List<Object> param = new ArrayList<>();
		param.add(id);
		param.add(pw);
		param.add(name);

		memberService.signUp(param);
		System.out.println("회원가입되었습니다.");
		System.out.println();
		return MAIN;
	}

	private View login() {
		System.out.println("로그인 하십시오");
		String id = ScanUtil.nextLine("ID : ");
		String pw = ScanUtil.nextLine("PW : ");
		List<Object> param = new ArrayList<>();
		param.add(id);
		sessionStorage.put("id", id);
		param.add(pw);
		boolean login = memberService.login(param);
		if (!login) {
			System.out.println("아이디나 비밀번호가 틀립니다");
			System.out.println("1. 재로그인");
			System.out.println("2. 회원가입");
			int sel = ScanUtil.nextInt("메뉴 선택: ");
			switch (sel) {
				case 1:
					return LOGIN;
				case 2:
					return MEMBER_SIGNUP;
				case 3:
					return LOGIN;
				default:
					return View.MAIN;
			}
		}
		View view =null;
		if (sessionStorage.containsKey("view")) {
				view = (View) sessionStorage.get("view");
				return view;
		}
		return View.ADMIN;
	}

	private View accountCreate() {
		if (sessionStorage.get("member") == null) {
			System.out.println("비로그인으로인한 잘못된 접근입니다.");
			System.out.println("로그인 창으로 이동합니다");
			sessionStorage.put("View", LOGIN);
			return View.LOGIN;
		}
		System.out.println("계좌 생성 화면입니다");
		System.out.println();
		System.out.println("계좌 생성중 . . .");
		MemberVo member = (MemberVo) sessionStorage.get("member");
		int memNo = member.getMem_no();

		List<Object> param = new ArrayList<>();
		param.add(memNo);

		memberService.accountCreate(param);
		System.out.println("계좌 생성 완료");
		return ACCOUNT_LIST;
	}

	public View main() {
		System.out.println("1. 계좌 생성");
		System.out.println("2. 계좌 리스트 조회");
		System.out.println("3. 로그인");
		System.out.println("4. 회원가입");

		int sel = ScanUtil.nextInt("메뉴 선택: ");
		switch (sel) {
			case 1:
				return ACCOUNT_CREATE;
			case 2:
				return ACCOUNT_LIST;
			case 3:
				return LOGIN;
			case 4:
				return MEMBER_SIGNUP;
			default:
				return View.MAIN;
			}
		}
	}
