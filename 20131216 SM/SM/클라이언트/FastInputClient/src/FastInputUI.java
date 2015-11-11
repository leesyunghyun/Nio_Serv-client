import java.awt.BorderLayout;
import java.awt.Color;
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
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class FastInputUI extends JFrame implements ActionListener {

	// ***************************************************************
	// ui선언시작
	Container BigCT = getContentPane();
	// 전체 창 크기설정 변수 선언시작
	private Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
	int x;
	int y;
	// 전체 창 크기설정 변수 선언끝

	// 패널선언시작
	private JPanel jpBig = new JPanel(new BorderLayout()); // 전체패널
	private JPanel jpBigC = new JPanel(new BorderLayout()),
			jpBigR = new JPanel(new BorderLayout()); // 전체패널중앙,전체패널오른쪽
	private JPanel jpBigCC = new JPanel(new GridLayout(1, 2));
	private JPanel jpCT = new JPanel(new FlowLayout()), jpCL = new JPanel(
			new FlowLayout()), jpCR = new JPanel(new FlowLayout()); // 전체패널중앙의
																	// 위와 왼쪽과
																	// 오른쪽
	private JPanel jpRT = new JPanel(new BorderLayout()), jpRTC = new JPanel(
			new FlowLayout()), jpRC = new JPanel(new BorderLayout()),
			jpRB = new JPanel(new GridLayout(1, 2)); // 전체패널오른쪽의 위,중앙,아래
	private JPanel jpRCC = new JPanel(new GridLayout(1, 1)),
			jpRCB = new JPanel(new FlowLayout()); // 전체패널오른쪽아래의 중앙,아래
	// 패널선언끝

	// 버튼선언시작
	private JButton jbRTCBan = new JButton("강퇴");
	private JButton jbRTCInvite = new JButton("초대");
	private JButton jbRTCView = new JButton("관전");
	private JButton jbRTCHope = new JButton("참여");
	private JButton jbRCSend = new JButton("전송");
	public JButton jbReady = new JButton("준비하기");
	private JButton jbOut = new JButton("나가기");
	// 버튼선언끝

	// 라벨선언시작
	private JLabel jlCTRoominfo = new JLabel();
	private JLabel jlRTlistinfo = new JLabel();
	// 라벨선언끝

	// 텍스트어레어선언시작
	protected JTextArea jaRBChatView = new JTextArea(10, 25);
	// 텍스트어레어선언끝

	// 스크롤판 및 리스트 선언시작
	private JScrollPane jspRTlistpane = new JScrollPane();
	private JScrollPane jspRBChatpane = new JScrollPane();
	protected JList jspRTlist = new JList();
	// 스크롤판 및 리스트 선언끝

	// 텍스트필드선언시작
	private JTextField jtRBChat = new JTextField(18);
	// 텍스트필드선언끝

	// ui선언끝
	// ********************************************************************************

	// *********************************************************************************
	// 클라이언트설정선언시작

	private FastinputStart st;
	private FastInputWaitUI wui;
	protected Vector v = new Vector();

	// 클라이언트설정선언끝
	// *********************************************************************************

	public FastInputUI(final FastInputWaitUI wui) {
		this.wui = wui;
		jlCTRoominfo
				.setText("001　　　　　　　　　　　　　　　　　　　　　재미있게 놀아요(1234)　　　　　　　　　　　　　　　　　　　　　");
		jlRTlistinfo.setText("회원목록　　　　　　　　　　참여인원:0명");
		jaRBChatView.setEditable(false);
		jaRBChatView.setLineWrap(true);

		jspRTlist.setFixedCellHeight(25);
		jspRTlistpane.setViewportView(jspRTlist);
		jspRBChatpane.setViewportView(jaRBChatView);

		jpCT.add(jlCTRoominfo);
		jpCL.setBackground(Color.white);
		jpCR.setBackground(Color.lightGray);
		jpCL.add(new JLabel("나"), BorderLayout.CENTER);
		jpCR.add(new JLabel("상대방"), BorderLayout.CENTER);

		jpBigCC.add(jpCL);
		jpBigCC.add(jpCR);
		jpRT.add(jlRTlistinfo, BorderLayout.NORTH);
		jpRT.add(jspRTlistpane, BorderLayout.CENTER);
		jpRTC.add(jbRTCBan);
		jpRTC.add(jbRTCInvite);
		jpRTC.add(jbRTCView);
		jpRTC.add(jbRTCHope);
		jpRT.add(jpRTC, BorderLayout.SOUTH);
		jpRCC.add(jspRBChatpane);
		jpRCB.add(jtRBChat);
		jpRCB.add(jbRCSend);
		jpRC.add(jpRCC, BorderLayout.CENTER);
		jpRC.add(jpRCB, BorderLayout.SOUTH);
		jpRB.add(jbReady);
		jpRB.add(jbOut);

		jpBigC.add(jpCT, BorderLayout.NORTH);
		jpBigC.add(jpBigCC, BorderLayout.CENTER);

		jpBigR.add(jpRT, BorderLayout.NORTH);
		jpBigR.add(jpRC, BorderLayout.CENTER);
		jpBigR.add(jpRB, BorderLayout.SOUTH);

		jpBig.add(jpBigC, BorderLayout.CENTER);
		jpBig.add(jpBigR, BorderLayout.EAST);

		BigCT.setLayout(new BorderLayout());
		BigCT.add(jpBig, BorderLayout.CENTER);

		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				if (jbReady.getText().toString().equals("대기하기")) {
					// 대기하기일때의 액션 넣기;
				}

				String str2 = "";
				FastInputThread.v3.remove(st.Mynicname);
				for (int i = 0; i < st.Mynicname.length(); i++) {
					if (st.Mynicname.charAt(i) == '(') {
						for (int j = 0; j < i; j++) {
							str2 += st.Mynicname.charAt(j);
						}
					}
				}
				st.Mynicname = str2 + "(방장)";
				str2 = "";
				FastInputThread.v3.remove(st.Mynicname);
				for (int i = 0; i < st.Mynicname.length(); i++) {
					if (st.Mynicname.charAt(i) == '(') {
						for (int j = 0; j < i; j++) {
							str2 += st.Mynicname.charAt(j);
						}
					}
				}
				st.Mynicname = str2;
				str2 = "";
				for (int i = 0; i < FastInputThread.v3.size(); i++) {
					if (i == 0) {
						String str3 = "";
						str3 = (String) FastInputThread.v3.get(i);
						for (int j = 0; j < str3.length(); j++) {
							if (str3.charAt(j) == '(') {
								String str4 = "";
								for (int k = 0; k < j; k++) {
									str4 += str3.charAt(k);
								}
								str4 += "(방장)";
								FastInputThread.v3.setElementAt(str4, i);
								break;
							}
						}

					}
					str2 += FastInputThread.v3.get(i) + "/";
				}
				FastInputThread.v3.removeAllElements();
				System.out.println(str2);
				st.roombye(str2);
				wui.setVisible(true);
				wui.jtaCTCChatview.setText("");
				FastInputThread.selectUI = false;
				// super.windowClosing(e);
				dispose();
			}
		});
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		setTitle("FastInputClient");
		setSize(1000, 600);
		x = (int) ((dm.getWidth() / 2) - (this.getWidth() / 2));
		y = (int) ((dm.getHeight() / 2) - (this.getHeight() / 2));
		setLocation(new Point(x, y));
		setVisible(true);

		jbRCSend.addActionListener(this);
		jbReady.addActionListener(this);
		jbOut.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String str = arg0.getActionCommand();

		if (str.equals("전송")) {
			if (!jtRBChat.getText().isEmpty()) {
				if (jtRBChat.getText().length() < 100) {
					st.send(st.Mynicname + " : "
							+ jtRBChat.getText().toString());
					jtRBChat.setText("");
				}
			}
		} else if (str.equals("준비하기")) {
			st.send("memberready");
			jbReady.setText("대기하기");
		} else if (str.equals("시작하기")) {
			st.send("memberstart");
		} else if (str.equals("나가기")) {
			if (jbReady.getText().toString().equals("대기하기")) {
				st.send("memberunready");
				jbReady.setText("준비하기");
			}

			String str2 = "";
			FastInputThread.v3.remove(st.Mynicname);
			for (int i = 0; i < st.Mynicname.length(); i++) {
				if (st.Mynicname.charAt(i) == '(') {
					for (int j = 0; j < i; j++) {
						str2 += st.Mynicname.charAt(j);
					}
				}
			}
			st.Mynicname = str2 + "(방장)";
			str2 = "";
			FastInputThread.v3.remove(st.Mynicname);
			for (int i = 0; i < st.Mynicname.length(); i++) {
				if (st.Mynicname.charAt(i) == '(') {
					for (int j = 0; j < i; j++) {
						str2 += st.Mynicname.charAt(j);
					}
				}
			}
			st.Mynicname = str2;
			str2 = "";
			for (int i = 0; i < FastInputThread.v3.size(); i++) {
				if (i == 0) {
					String str3 = "";
					str3 = (String) FastInputThread.v3.get(i);
					for (int j = 0; j < str3.length(); j++) {
						if (str3.charAt(j) == '(') {
							String str4 = "";
							for (int k = 0; k < j; k++) {
								str4 += str3.charAt(k);
							}
							str4 += "(방장)";
							FastInputThread.v3.setElementAt(str4, i);
							break;
						}
					}

				}
				str2 += FastInputThread.v3.get(i) + "/";
			}
			FastInputThread.v3.removeAllElements();
			System.out.println(str2);
			st.roombye(str2);
			wui.setVisible(true);
			wui.jtaCTCChatview.setText("");
			FastInputThread.selectUI = false;
			// super.windowClosing(e);
			dispose();
		} else if (str.equals("대기하기")) {
			st.send("memberunready");
			jbReady.setText("준비하기");
		}
	}

	public void setroom(String roomname, String roompw, int roommaxpeople,
			int roomnowpeople, boolean bpw) {
		if (bpw) {
			jlCTRoominfo.setText("001　　　　　　　　　　　　　　　　　　　　　" + roomname + "("
					+ roompw + ")　　　　　　　　　　　　　　　　　　　　　");
			jlRTlistinfo.setText("회원목록　　　　　　　　　　참여인원:" + roomnowpeople + "/"
					+ roommaxpeople + "명");
		} else {
			jlCTRoominfo.setText("001　　　　　　　　　　　　　　　　　　　　　" + roomname
					+ "　　　　　　　　　　　　　　　　　　　　　");
			jlRTlistinfo.setText("회원목록　　　　　　　　　　참여인원:" + roomnowpeople + "/"
					+ roommaxpeople + "명");
		}
	}

	public void setinfo(FastinputStart st) {
		this.st = st;
	}
}
