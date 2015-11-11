import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class FastInputJoinUI extends JFrame implements ActionListener {

	// ***************************************************************
	// ui설정 선언시작
	Container BigCT = getContentPane();
	// 전체 창 크기설정 변수 선언시작
	private Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
	int x;
	int y;
	// 전체 창 크기설정 변수 선언끝

	// 패널선언시작
	private JPanel jpBig = new JPanel(new BorderLayout());
	private JPanel jpBC = new JPanel(new GridLayout(2, 1)), jpBB = new JPanel(
			new FlowLayout());
	private JPanel jpCT = new JPanel(new GridLayout(1, 2, -50, 0)),
			jpCB = new JPanel(new GridLayout(1, 2, -50, 0));
	// 패널선언끝

	// 텍스트필드 선언시작
	private JTextField jtfCTInputID = new JTextField(8);
	private JTextField jtfCBInputPW = new JPasswordField(8);
	// 텍스트필드 선언끝

	// 버튼선언시작
	private JButton jbBLogin = new JButton("로그인");
	private JButton jbBNew = new JButton("회원가입");
	private JButton jbBFind = new JButton("ID/PW찾기");
	// 버튼선언끝

	// 라벨선언시작
	private JLabel jlaCTID = new JLabel("I D : ", JLabel.CENTER);
	private JLabel jlaCBPW = new JLabel("P W : ", JLabel.CENTER);
	private JLabel jlactr = new JLabel();
	private JLabel jlacbr = new JLabel();
	// 라벨선언끝

	// ui설정 선언끝
	// ***************************************************************

	// ***************************************************************
	// 회원가입UI설정선언 시작

	private JDialog jdNewmember = new JDialog(this);
	private JPanel jpjdSmall = new JPanel(new GridLayout(1, 2));
	private JPanel jpjdSLeft = new JPanel(new GridLayout(3, 1, 0, 10)),
			jpjdSRight = new JPanel(new GridLayout(3, 1, 0, 10));
	private JPanel jpjdID = new JPanel(new GridLayout(1, 2)),
			jpjdPW = new JPanel(new GridLayout(1, 2)), jpjdPW2 = new JPanel(
					new GridLayout(1, 2)), jpjdName = new JPanel(
					new GridLayout(1, 2)), jpjdNicname = new JPanel(
					new GridLayout(1, 2)), jpjdBirth = new JPanel(
					new GridLayout(1, 2));
	private JPanel jpjdBottom = new JPanel(new BorderLayout());
	private JTextField jtfjdID = new JTextField(8),
			jtfjdPW = new JPasswordField(8), jtfjdPW2 = new JPasswordField(8),
			jtfjdName = new JTextField(8), jtfjdNicname = new JTextField(8),
			jtfjdBirth = new JTextField(8);
	private JButton jbjdIDCheck = new JButton("아이디중복검사"),
			jbjdMake = new JButton("만들기"),
			jbjdNicCheck = new JButton("닉네임중복검사");
	private JLabel jljdID = new JLabel("아이디 : ", JLabel.RIGHT),
			jljdPW = new JLabel("비밀번호 : ", JLabel.RIGHT), jljdPW2 = new JLabel(
					"비밀번호확인 : ", JLabel.RIGHT), jljdName = new JLabel("이름 : ",
					JLabel.RIGHT), jljdNicname = new JLabel("닉네임 : ",
					JLabel.RIGHT), jljdBirth = new JLabel("생년월일 : ",
					JLabel.RIGHT);
	private int jdx, jdy;

	// 회원가입UI설정선언 끝
	// ***************************************************************

	// ***************************************************************
	// 아이디비번찾기UI설정선언 시작
	private JDialog jdfindidpw = new JDialog();
	private JTabbedPane jtpjdfindipw = new JTabbedPane();
	private JPanel jpjdfindid = new JPanel(new BorderLayout());
	private JPanel jpjdfindpw = new JPanel(new BorderLayout());
	private JPanel jpjdfindidinfo = new JPanel(new GridLayout(2, 1));
	private JPanel jpjdfindpwinfo = new JPanel(new GridLayout(3, 1));
	private JPanel jpjdidname = new JPanel(new GridLayout(1, 2)),
			jpjdidpw = new JPanel(new GridLayout(1, 2)), jpjdpwid = new JPanel(
					new GridLayout(1, 2)), jpjdpwname = new JPanel(
					new GridLayout(1, 2)), jpjdpwbirth = new JPanel(
					new GridLayout(1, 2));

	private JLabel jlajdidname = new JLabel("닉네임 : ", JLabel.RIGHT),
			jlajdidpw = new JLabel("비밀번호 : ", JLabel.RIGHT),
			jlajdpwid = new JLabel("아이디 : ", JLabel.RIGHT),
			jlajdpwname = new JLabel("이름 : ", JLabel.RIGHT),
			jlajdpwbirth = new JLabel("생년월일 : ", JLabel.RIGHT);

	private JTextField jtfjdidnicname = new JTextField(8),
			jtfjdidpw = new JPasswordField(8), jtfjdpwid = new JTextField(8),
			jtfjdpwname = new JTextField(8), jtfjdpwbirth = new JPasswordField(
					8);

	private JButton jbjdidfind = new JButton("아이디 찾기");
	private JButton jbjdpwfind = new JButton("비밀번호 찾기");
	// 아이디비번찾기UI설정선언 끝
	// ***************************************************************

	// ***************************************************************
	// 클라이언트설정선언시작
	private FastinputStart st;
	private boolean idcheck = false; // 아이디중복검사
	private boolean niccheck = false; // 닉네임중복검사

	// 클라이언트설정선언끝
	// ***************************************************************
	public FastInputJoinUI() {

		jtfCTInputID.setDocument(new FastInputDocument(8, null, 2));
		jtfCBInputPW.setDocument(new FastInputDocument(8, null, 2));

		jpCT.add(jlaCTID);
		jpCT.add(jtfCTInputID);
		jpCT.add(jlactr);
		jpCB.add(jlaCBPW);
		jpCB.add(jtfCBInputPW);
		jpCB.add(jlacbr);

		jpBC.add(jpCT);
		jpBC.add(jpCB);
		jpBB.add(jbBLogin);
		jpBB.add(jbBNew);
		jpBB.add(jbBFind);

		jpBig.add(jpBC, BorderLayout.CENTER);
		jpBig.add(jpBB, BorderLayout.SOUTH);

		BigCT.setLayout(new BorderLayout());
		BigCT.add(jpBig);

		setTitle("FastInputLogin");
		setSize(300, 130);
		x = (int) ((dm.getWidth() / 2) - (this.getWidth() / 2));
		y = (int) ((dm.getHeight() / 2) - (this.getHeight() / 2));
		setLocation(new Point(x, y));
		setVisible(true);
		this.setResizable(false);
		jbBLogin.addActionListener(this);
		jbBNew.addActionListener(this);
		jbBFind.addActionListener(this);
		jbjdIDCheck.addActionListener(this);
		jbjdMake.addActionListener(this);
		jbjdNicCheck.addActionListener(this);
		jbjdidfind.addActionListener(this);
		jbjdpwfind.addActionListener(this);
		
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				st.send("loginbye");
				System.exit(0);
				super.windowClosing(e);
			}

		});
	}

	public void NewMember() {

		jbjdIDCheck.setEnabled(true);
		jbjdNicCheck.setEnabled(true);
		jtfjdID.setEditable(true);
		jtfjdNicname.setEditable(true);
		Dimension dm2 = Toolkit.getDefaultToolkit().getScreenSize();
		jtfjdID.setDocument(new FastInputDocument(8, null, 2));
		jtfjdPW.setDocument(new FastInputDocument(8, null, 2));
		jtfjdPW2.setDocument(new FastInputDocument(8, null, 2));
		jtfjdName.setDocument(new FastInputDocument(8));
		jtfjdNicname.setDocument(new FastInputDocument(8));
		jtfjdBirth.setDocument(new FastInputDocument(8, null, 1));
		jpjdID.add(jljdID);
		jpjdID.add(jtfjdID);
		jpjdID.add(jbjdIDCheck);

		jpjdPW.add(jljdPW);
		jpjdPW.add(jtfjdPW);

		jpjdPW2.add(jljdPW2);
		jpjdPW2.add(jtfjdPW2);

		jpjdName.add(jljdName);
		jpjdName.add(jtfjdName);

		jpjdNicname.add(jljdNicname);
		jpjdNicname.add(jtfjdNicname);
		jpjdNicname.add(jbjdNicCheck);
		jpjdBirth.add(jljdBirth);
		jpjdBirth.add(jtfjdBirth);

		jpjdSLeft.add(jpjdID);
		jpjdSLeft.add(jpjdPW);
		jpjdSLeft.add(jpjdPW2);

		jpjdSRight.add(jpjdNicname);
		jpjdSRight.add(jpjdName);
		jpjdSRight.add(jpjdBirth);

		jpjdSmall.add(jpjdSLeft);
		jpjdSmall.add(jpjdSRight);

		jpjdBottom.add(jbjdMake, BorderLayout.SOUTH);

		jdNewmember.setLayout(new BorderLayout());
		jdNewmember.add(jpjdSmall, BorderLayout.CENTER);
		jdNewmember.add(jpjdBottom, BorderLayout.SOUTH);
		jdNewmember.setSize(550, 170);
		jdNewmember.setResizable(false);
		jdNewmember.setModal(true);
		jdNewmember.setTitle("회원가입");
		jdx = (int) ((dm2.getWidth() / 2) - (jdNewmember.getWidth() / 2));
		jdy = (int) ((dm2.getHeight() / 2) - (jdNewmember.getHeight() / 2));
		jdNewmember.setLocation(new Point(jdx, jdy));
		jdNewmember.setVisible(true);
		
		
		
		jdNewmember.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				jdNewmember.dispose();
			}
		});
	}

	public void findidpw() {

		jtfjdidnicname.setDocument(new FastInputDocument(8));
		jtfjdidpw.setDocument(new FastInputDocument(8, null, 2));
		jtfjdpwbirth.setDocument(new FastInputDocument(8, null, 1));
		jtfjdpwname.setDocument(new FastInputDocument(8));
		jtfjdpwid.setDocument(new FastInputDocument(8, null, 2));

		Dimension dm3 = Toolkit.getDefaultToolkit().getScreenSize();
		jpjdidname.add(jlajdidname);
		jpjdidname.add(jtfjdidnicname);

		jpjdidpw.add(jlajdidpw);
		jpjdidpw.add(jtfjdidpw);

		jpjdpwid.add(jlajdpwid);
		jpjdpwid.add(jtfjdpwid);

		jpjdpwname.add(jlajdpwname);
		jpjdpwname.add(jtfjdpwname);

		jpjdpwbirth.add(jlajdpwbirth);
		jpjdpwbirth.add(jtfjdpwbirth);

		jpjdfindidinfo.add(jpjdidname);
		jpjdfindidinfo.add(jpjdidpw);
		jpjdfindpwinfo.add(jpjdpwid);
		jpjdfindpwinfo.add(jpjdpwname);
		jpjdfindpwinfo.add(jpjdpwbirth);

		jpjdfindid.add(jpjdfindidinfo, BorderLayout.CENTER);
		jpjdfindid.add(jbjdidfind, BorderLayout.SOUTH);
		jpjdfindpw.add(jpjdfindpwinfo, BorderLayout.CENTER);
		jpjdfindpw.add(jbjdpwfind, BorderLayout.SOUTH);

		jtpjdfindipw.addTab("아이디찾기", jpjdfindid);
		jtpjdfindipw.addTab("비밀번호찾기", jpjdfindpw);

		jdfindidpw.add(jtpjdfindipw);

		jdfindidpw.setModal(true);
		jdfindidpw.setSize(210, 170);
		jdfindidpw.setResizable(false);
		jdx = (int) ((dm3.getWidth() / 2) - (jdfindidpw.getWidth() / 2));
		jdy = (int) ((dm3.getHeight() / 2) - (jdfindidpw.getHeight() / 2));
		jdfindidpw.setLocation(new Point(jdx, jdy));
		jdfindidpw.setTitle("개인정보찾기");
		jdfindidpw.setVisible(true);
		
		jdfindidpw.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				jdfindidpw.dispose();
			}
		});
	}

	public boolean checkbirthday(String bir) {
		String birth = bir;
		switch (birth.charAt(0)) {
		case '1':
			switch (birth.charAt(1)) {
			case '9':
				break;
			default:
				return false;
			}
			break;
		case '2':
			switch (birth.charAt(1)) {
			case '0':
				break;
			default:
				return false;
			}
			break;
		default:
			return false;
		}
		switch (birth.charAt(4)) {
		case '0':
			switch (birth.charAt(5)) {
			case '1':
			case '3':
			case '5':
			case '7':
			case '8':
				switch (birth.charAt(6)) {
				case '0':
					switch (birth.charAt(7)) {
					case '0':
						return false;
					default:
						return true;
					}
				case '1':
				case '2':
					return true;
				case '3':
					switch (birth.charAt(7)) {
					case '0':
					case '1':
						return true;
					default:
						return false;
					}
				default:
					return false;
				}
			case '4':
			case '6':
			case '9':
				switch (birth.charAt(6)) {
				case '0':
					switch (birth.charAt(7)) {
					case '0':
						return false;
					default:
						return true;
					}
				case '1':
				case '2':
					return true;
				case '3':
					switch (birth.charAt(7)) {
					case '0':
						return true;
					default:
						return false;
					}
				default:
					return false;
				}
			case '2':
				switch (birth.charAt(6)) {
				case '0':
					switch (birth.charAt(7)) {
					case '0':
						return false;
					default:
						return true;
					}
				case '1':
				case '2':
					return true;
				default:
					return false;
				}
			default:
			}
			break;
		case '1':
			switch (birth.charAt(5)) {
			case '0':
			case '2':
				switch (birth.charAt(6)) {
				case '0':
					switch (birth.charAt(7)) {
					case '0':
						return false;
					default:
						return true;
					}
				case '1':
				case '2':
					return true;
				case '3':
					switch (birth.charAt(7)) {
					case '0':
					case '1':
						return true;
					default:
						return false;
					}
				default:
					return false;
				}
			case '1':
				switch (birth.charAt(6)) {
				case '0':
					switch (birth.charAt(7)) {
					case '0':
						return false;
					default:
						return true;
					}
				case '1':
				case '2':
					return true;
				case '3':
					switch (birth.charAt(7)) {
					case '0':
						return true;
					default:
						return false;
					}
				default:
					return false;
				}
			}
		default:
			return false;
		}
		return false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String str = e.getActionCommand();
		if (str.equals("로그인")) {
			if (!jtfCTInputID.getText().isEmpty()
					&& !jtfCBInputPW.getText().isEmpty()) {
				if (st.OX(jtfCTInputID.getText().toString(), jtfCBInputPW
						.getText().toString())) {
					this.setVisible(false);
					FastInputWaitUI wui = new FastInputWaitUI();
					wui.setinfo(st);
					FastInputThread th = new FastInputThread(st.channel,
							st.buffer, st, wui);
					wui.setth(th);
					th.start();
					
				}

				else {
					JOptionPane.showConfirmDialog(jdNewmember,
							"아이디가 존재하지 않거나\n비밀번호가 올바르지 않습니다.\n다시 확인해주세요!",
							"경고", JOptionPane.CLOSED_OPTION,
							JOptionPane.WARNING_MESSAGE);
				}
			} else {
				JOptionPane.showConfirmDialog(jdNewmember, "빈칸 없이 모두 입력해주세요.",
						"경고", JOptionPane.CLOSED_OPTION,
						JOptionPane.WARNING_MESSAGE);
			}
		} else if (str.equals("회원가입")) {
			NewMember();
			idcheck = false;
			niccheck = false;
		} else if (str.equals("ID/PW찾기")) {
			findidpw();

		} else if (str.equals("만들기")) {
			if (!jtfjdID.getText().isEmpty() && !jtfjdPW.getText().isEmpty()
					&& !jtfjdPW2.getText().isEmpty()
					&& !jtfjdName.getText().isEmpty()
					&& !jtfjdNicname.getText().isEmpty()
					&& !jtfjdBirth.getText().isEmpty()) {
				if (idcheck && niccheck) {
					if (jtfjdPW.getText().toString()
							.equals(jtfjdPW2.getText().toString())) {
						if (jtfjdBirth.getText().toString().length() == 8) {
							if (checkbirthday(jtfjdBirth.getText().toString())) {
								st.Makemember(jtfjdID.getText().toString(),
										jtfjdPW.getText().toString(), jtfjdName
												.getText().toString(),
										jtfjdNicname.getText().toString(),
										jtfjdBirth.getText().toString());
								jdNewmember.dispose();
								JOptionPane
								.showConfirmDialog(
										jdNewmember,
										"환영합니다. 로그인해주세요!",
										"환영",
										JOptionPane.CLOSED_OPTION,
										JOptionPane.INFORMATION_MESSAGE);
							} else {
								JOptionPane
										.showConfirmDialog(
												jdNewmember,
												"유효하지 않은 생년월일입니다.\n8자리로 입력해주세요 예) 19910805",
												"경고",
												JOptionPane.CLOSED_OPTION,
												JOptionPane.WARNING_MESSAGE);
							}
						} else {
							JOptionPane
									.showConfirmDialog(
											jdNewmember,
											"유효하지 않은 생년월일입니다.\n8자리로 입력해주세요 예) 19910805",
											"경고", JOptionPane.CLOSED_OPTION,
											JOptionPane.WARNING_MESSAGE);
						}
					} else {
						JOptionPane.showConfirmDialog(jdNewmember,
								"비밀번호가 일치하지 않습니다.", "경고",
								JOptionPane.CLOSED_OPTION,
								JOptionPane.WARNING_MESSAGE);
					}
				} else {
					JOptionPane.showConfirmDialog(jdNewmember,
							"아이디나 닉네임\n중복확인 버튼을 눌러주세요", "경고",
							JOptionPane.CLOSED_OPTION,
							JOptionPane.WARNING_MESSAGE);
				}
			} else {
				JOptionPane.showConfirmDialog(jdNewmember, "빈칸 없이 모두 입력해주세요.",
						"경고", JOptionPane.CLOSED_OPTION,
						JOptionPane.WARNING_MESSAGE);
			}
		} else if (str.equals("아이디중복검사")) {

			if (!jtfjdID.getText().isEmpty()) {
				if (st.IDCheck(jtfjdID.getText().toString())) {
					int check;
					check = JOptionPane.showConfirmDialog(jdNewmember,
							"사용할 수 있는 아이디 입니다. 사용하시겠습니까?", "ID사용 가능",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
					if (check == 0) {
						idcheck = true;
						jbjdIDCheck.setEnabled(false);
						jtfjdID.setEditable(false);
					}
				} else {
					JOptionPane.showConfirmDialog(jdNewmember,
							"이미 사용하고 있는 아이디 입니다.", "ID사용 불가능",
							JOptionPane.CLOSED_OPTION,
							JOptionPane.WARNING_MESSAGE);
					idcheck = false;
				}
			} else {
				JOptionPane.showConfirmDialog(jdNewmember, "아이디를 입력해주세요.",
						"아이디입력", JOptionPane.CLOSED_OPTION,
						JOptionPane.WARNING_MESSAGE);
			}

		} else if (str.equals("닉네임중복검사")) {
			if (!jtfjdNicname.getText().isEmpty()) {
				if (st.NicCheck(jtfjdNicname.getText().toString())) {
					int check;
					check = JOptionPane.showConfirmDialog(jdNewmember,
							"사용할 수 있는 닉네임 입니다. 사용하시겠습니까?", "닉네임사용 가능",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
					if (check == 0) {
						niccheck = true;
						jbjdNicCheck.setEnabled(false);
						jtfjdNicname.setEditable(false);
					}
				} else {
					JOptionPane.showConfirmDialog(jdNewmember,
							"이미 사용하고 있는 닉네임 입니다.", "닉네임사용 불가능",
							JOptionPane.CLOSED_OPTION,
							JOptionPane.WARNING_MESSAGE);
					niccheck = false;
				}
			} else {
				JOptionPane.showConfirmDialog(jdNewmember, "닉네임을 입력해주세요.",
						"닉네임입력", JOptionPane.CLOSED_OPTION,
						JOptionPane.WARNING_MESSAGE);
			}

		} else if (str.equals("아이디 찾기")) {
			if (!jtfjdidnicname.getText().isEmpty()
					&& !jtfjdidpw.getText().isEmpty()) {
				String findid = st.findid(jtfjdidnicname.getText().toString(),
						jtfjdidpw.getText().toString());
				if (findid != "false") {
					if (JOptionPane.showConfirmDialog(jdfindidpw, "찾으시는 아이디는 "
							+ findid + "입니다. ", "아이디찾기",
							JOptionPane.CLOSED_OPTION,
							JOptionPane.INFORMATION_MESSAGE) == 0) {
						jdfindidpw.dispose();
					}
				} else {
					JOptionPane.showConfirmDialog(jdfindidpw,
							"일치하는 정보가 없습니다.\n 다시 확인해주세요.", "경고",
							JOptionPane.CLOSED_OPTION,
							JOptionPane.WARNING_MESSAGE);
				}
			} else {
				JOptionPane.showConfirmDialog(jdfindidpw, "빈칸 없이 모두 입력해주세요.",
						"경고", JOptionPane.CLOSED_OPTION,
						JOptionPane.WARNING_MESSAGE);

			}
		} else if (str.equals("비밀번호 찾기")) {
			if (!jtfjdpwname.getText().isEmpty()
					&& !jtfjdpwid.getText().isEmpty()
					&& !jtfjdpwbirth.getText().isEmpty()) {
				String findpw = st.findpw(jtfjdpwid.getText().toString(),
						jtfjdpwname.getText().toString(), jtfjdpwbirth
								.getText().toString());
				if (findpw != "false") {
					if (JOptionPane.showConfirmDialog(jdfindidpw, "찾으시는 비밀번호는 "
							+ findpw + "입니다. ", "비밀번호찾기",
							JOptionPane.CLOSED_OPTION,
							JOptionPane.INFORMATION_MESSAGE) == 0) {
						jdfindidpw.dispose();
					}
				} else {
					JOptionPane.showConfirmDialog(jdfindidpw,
							"일치하는 정보가 없습니다.\n 다시 확인해주세요.", "경고",
							JOptionPane.CLOSED_OPTION,
							JOptionPane.WARNING_MESSAGE);
				}
			} else {
				JOptionPane.showConfirmDialog(jdfindidpw, "빈칸 없이 모두 입력해주세요.",
						"경고", JOptionPane.CLOSED_OPTION,
						JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	public void setinfo(FastinputStart st) {
		this.st = st;
	}

}
