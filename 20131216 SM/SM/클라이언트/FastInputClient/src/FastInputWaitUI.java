import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class FastInputWaitUI extends JFrame implements ActionListener {

	// *****************************************************************
	// ui선언시작
	Container BigCT = getContentPane();
	// 전체 창 크기설정 변수 선언시작
	private Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
	int x;
	int y;
	// 전체 창 크기설정 변수 선언끝

	// 패널선언시작
	private JPanel jpBig = new JPanel(new BorderLayout());
	private JPanel jpBC = new JPanel(new BorderLayout()), jpBR = new JPanel(
			new BorderLayout());
	private JPanel jpCT = new JPanel(new BorderLayout()), jpCB = new JPanel(
			new BorderLayout());
	private JPanel jpCTC = new JPanel(new BorderLayout()), jpCTB = new JPanel(
			new FlowLayout());
	private JPanel jpRC = new JPanel(new GridLayout(1, 2)), jpRB = new JPanel(
			new GridLayout(1, 2));
	private JPanel jpRCL = new JPanel(new BorderLayout()), jpRCR = new JPanel(
			new BorderLayout());
	private JPanel jpRBL = new JPanel(new GridLayout(2, 2)),
			jpRBR = new JPanel(new GridLayout(2, 2));
	// 패널선언끝

	// 텍스트필드 및 어레어 선언시작
	private JTextField jtfCTBChat = new JTextField(35);
	protected JTextArea jtaCTCChatview = new JTextArea(10, 10);
	// 텍스트필드 및 어레어 선언 끝

	// 스크롤판 및 리스트 선언 시작
	private JScrollPane jspCTCChatpane = new JScrollPane();
	private JScrollPane jspCBRoomlistpane = new JScrollPane();
	private JScrollPane jspRCLWaitlistpane = new JScrollPane();
	private JScrollPane jspRCRFriendlistpane = new JScrollPane();
	protected JList jlRCLwaitlist = new JList();
	protected JList jlRCRFriendlist = new JList();
	protected JList jlCBRoomlist = new JList();
	protected String selectionroom=""; // 현재 선택한 대기방
	protected String selectionfriend=""; // 현재 선택한 친구
	protected String selectionwait=""; // 현재 선택한 대기회원
	// 스크롤판 및 리스트 선언 끝

	// 버튼선언시작
	private JButton jbCTBSend = new JButton("전송");
	private JButton jbRBLMake = new JButton("방만들기");
	private JButton jbRBLJoin = new JButton("입장하기");
	private JButton jbRBLinform = new JButton("방 상태보기");
	private JButton jbRBRMail = new JButton("쪽지");
	private JButton jbRBROnechat = new JButton("1:1대화");
	private JButton jbRBRFriend = new JButton("친구관리");
	// 버튼선언끝

	// 라벨선언시작
	private JLabel jlaCTServinform = new JLabel();
	protected JLabel jlaCBRoominform = new JLabel();
	protected JLabel jlaRCLWaitinform = new JLabel();
	protected JLabel jlaRCRFriendinform = new JLabel();
	// 라벨선언끝

	// ui선언끝
	// ********************************************************************************

	// *********************************************************************************
	// 방만들기선언시작

	private JDialog jdMakeroom = new JDialog();
	private JPanel jpjdMakeRoom = new JPanel(new GridLayout(3, 1));
	private JPanel jpjdMRT = new JPanel(new GridLayout(1, 2));
	private JPanel jpjdMRC = new JPanel(new GridLayout(1, 2));
	private JPanel jpjdMRB = new JPanel(new GridLayout(1, 2));

	private JTextField jtfjdMRTRoomname = new JTextField(8);
	private JTextField jtfjdMRCRoompw = new JPasswordField(8);
	private JComboBox jcbjdMRBRoompeer = new JComboBox();

	private JLabel jljdMRTRoomname = new JLabel("방    제목 : ", JLabel.CENTER);
	private JLabel jljdMRCRoompw = new JLabel("비밀번호 : ", JLabel.CENTER);
	private JLabel jljdMRBRoompeer = new JLabel("인        원 : ", JLabel.CENTER);

	private JButton jbjdRoomMake = new JButton("만들기");

	private JCheckBox jcbjdRoompw = new JCheckBox("비밀번호사용");
	private boolean bjdRoompw = false;
	// 방만들기선언끝
	// *********************************************************************************

	// *********************************************************************************
	// 비번요구대화상자선언시작
	protected JDialog jdreqpw = new JDialog();
	private JLabel jljdreqpw = new JLabel("비밀번호 : ");
	private JTextField jtfjdreqpw = new JPasswordField(10);
	private JPanel jpjdreqpw = new JPanel(new FlowLayout());
	private JPanel jp2jdreqpw = new JPanel(new FlowLayout());
	private JButton jbjdreqpw = new JButton("방 입장");
	// 비번요구대화상자선언끝
	// *********************************************************************************

	// *********************************************************************************
	// 클라이언트설정선언시작
	private FastinputStart st;
	private FastInputUI ui;
	private FastInputThread th;
	// 클라이언트설정선언끝
	// *********************************************************************************
	public FastInputWaitUI() {
		
		jtaCTCChatview.setEditable(false);
		jtaCTCChatview.setLineWrap(true);

		jlCBRoomlist.setFixedCellHeight(30);
		jspCTCChatpane.setViewportView(jtaCTCChatview);
		jspCBRoomlistpane.setViewportView(jlCBRoomlist);
		jspRCRFriendlistpane.setViewportView(jlRCRFriendlist);
		jspRCLWaitlistpane.setViewportView(jlRCLwaitlist);

		jlaCTServinform.setText("서버공지 : 반갑습니다");
		jlaCBRoominform.setText("방 목록 대기방 : 0");
		jlaRCLWaitinform.setText("대기실 인원 : 0명");
		jlaRCRFriendinform.setText("친구목록 인원 : 0명");

		jlCBRoomlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jlRCLwaitlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jlRCRFriendlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		jpCTC.add(jspCTCChatpane, BorderLayout.CENTER);
		jpCTC.add(jlaCTServinform, BorderLayout.NORTH);
		jpCTB.add(jtfCTBChat);
		jpCTB.add(jbCTBSend);
		jpRCL.add(jspRCLWaitlistpane, BorderLayout.CENTER);
		jpRCL.add(jlaRCLWaitinform, BorderLayout.NORTH);
		jpRCR.add(jspRCRFriendlistpane, BorderLayout.CENTER);
		jpRCR.add(jlaRCRFriendinform, BorderLayout.NORTH);
		jpRBL.add(jbRBLMake);
		jpRBL.add(jbRBLJoin);
		jpRBL.add(jbRBLinform);
		jpRBR.add(jbRBRMail);
		jpRBR.add(jbRBROnechat);
		jpRBR.add(jbRBRFriend);

		jpCT.add(jpCTC, BorderLayout.CENTER);
		jpCT.add(jpCTB, BorderLayout.SOUTH);
		jpCB.add(jspCBRoomlistpane, BorderLayout.CENTER);
		jpCB.add(jlaCBRoominform, BorderLayout.NORTH);
		jpRC.add(jpRCL);
		jpRC.add(jpRCR);
		jpRB.add(jpRBL);
		jpRB.add(jpRBR);

		jpBC.add(jpCT, BorderLayout.CENTER);
		jpBC.add(jpCB, BorderLayout.SOUTH);
		jpBR.add(jpRC, BorderLayout.CENTER);
		jpBR.add(jpRB, BorderLayout.SOUTH);

		jpBig.add(jpBC, BorderLayout.CENTER);
		jpBig.add(jpBR, BorderLayout.EAST);

		BigCT.setLayout(new BorderLayout());
		BigCT.add(jpBig, BorderLayout.CENTER);

		setTitle("FastInputClient");
		setSize(1000, 600);
		x = (int) ((dm.getWidth() / 2) - (this.getWidth() / 2));
		y = (int) ((dm.getHeight() / 2) - (this.getHeight() / 2));
		setLocation(new Point(x, y));
		setVisible(true);
		this.setResizable(false);
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				st.send("bye");
				System.exit(0);
				super.windowClosing(e);
			}

		});

		jbjdRoomMake.addActionListener(this);
		jbCTBSend.addActionListener(this);
		jbRBLMake.addActionListener(this);
		jbRBLJoin.addActionListener(this);
		jcbjdRoompw.addActionListener(this);
		jbjdreqpw.addActionListener(this);
		jlCBRoomlist.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				// TODO 자동 생성된 메소드 스텁
				selectionroom = (String) jlCBRoomlist.getSelectedValue();
			}
		});
	}

	public void Makeroom() {
		bjdRoompw = false;
		jtfjdMRCRoompw.setEditable(false);
		jcbjdRoompw.setSelected(false);
		jcbjdMRBRoompeer.removeAllItems();

		jtfjdMRTRoomname.setDocument(new FastInputDocument(8));
		jtfjdMRCRoompw.setDocument(new FastInputDocument(8, null, 2));

		jcbjdMRBRoompeer.addItem("2명");
		jcbjdMRBRoompeer.addItem("3명");
		jcbjdMRBRoompeer.addItem("4명");

		jpjdMRT.add(jljdMRTRoomname);
		jpjdMRT.add(jtfjdMRTRoomname);

		jpjdMRC.add(jljdMRCRoompw);
		jpjdMRC.add(jtfjdMRCRoompw);
		jpjdMRB.add(jljdMRBRoompeer);
		jpjdMRB.add(jcbjdMRBRoompeer);

		jpjdMakeRoom.add(jpjdMRT, BorderLayout.NORTH);
		jpjdMakeRoom.add(jpjdMRC, BorderLayout.CENTER);
		jpjdMakeRoom.add(jpjdMRB, BorderLayout.SOUTH);

		jdMakeroom.setLayout(new BorderLayout());
		jdMakeroom.setSize(400, 150);
		x = (int) ((dm.getWidth() / 2) - (jdMakeroom.getWidth() / 2));
		y = (int) ((dm.getHeight() / 2) - (jdMakeroom.getHeight() / 2));
		jdMakeroom.setModal(true);
		jdMakeroom.setLocation(new Point(x, y));
		jdMakeroom.add(jpjdMakeRoom, BorderLayout.CENTER);
		jdMakeroom.add(jbjdRoomMake, BorderLayout.SOUTH);
		jdMakeroom.add(jcbjdRoompw, BorderLayout.EAST);

		jdMakeroom.setVisible(true);
		jdMakeroom.setResizable(false);
		jdMakeroom.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				jdMakeroom.dispose();
			}

		});

	}

	public void reqpw() {
		jtfjdreqpw.setText("");
		jdreqpw.setLayout(new BorderLayout());
		jtfjdreqpw.setDocument(new FastInputDocument(8, null, 2));
		jpjdreqpw.add(jljdreqpw);
		jpjdreqpw.add(jtfjdreqpw);
		jdreqpw.add(jpjdreqpw, BorderLayout.NORTH);
		jp2jdreqpw.add(jbjdreqpw);
		jdreqpw.add(jp2jdreqpw, BorderLayout.SOUTH);
		jdreqpw.setSize(220, 110);
		x = (int) ((dm.getWidth() / 2) - (jdreqpw.getWidth() / 2));
		y = (int) ((dm.getHeight() / 2) - (jdreqpw.getHeight() / 2));
		jdreqpw.setModal(true);
		jdreqpw.setLocation(new Point(x, y));
		jdreqpw.setVisible(true);
		jdreqpw.setResizable(false);
		jdreqpw.setTitle("비밀번호입력");
		jdreqpw.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				jdreqpw.dispose();
			}

		});

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String str = arg0.getActionCommand();

		if (str.equals("전송")) {
			if (!jtfCTBChat.getText().isEmpty()) {
				if (jtfCTBChat.getText().length() < 100) {
					st.send(st.Mynicname + " : "
							+ jtfCTBChat.getText().toString());
					jtfCTBChat.setText("");
				}
			}
		} else if (str.equals("방만들기")) {
			Makeroom();
		} else if (str.equals("입장하기")) {
			String str2 = selectionroom;
			if (!str2.equals("")) {
				char a = str2.charAt(0);
				switch (a) {
				case '공':
					st.Joinroom(str2, "");
					break;
				case '비':
					reqpw();
					break;
				}
				
			}
		} else if (str.equals("방 입장")) {
			String str2 = jtfjdreqpw.getText().toString();
			st.Joinroom(selectionroom, str2);
		} else if (str.equals("만들기")) {
			if (bjdRoompw) {
				if (!jtfjdMRCRoompw.getText().isEmpty()
						&& !jtfjdMRTRoomname.getText().isEmpty()) {
					int number = 0;
					switch (jcbjdMRBRoompeer.getSelectedIndex()) {
					case 0:
						number = 2;
						break;
					case 1:
						number = 3;
						break;
					case 2:
						number = 4;
						break;
					}
					if (st.Makeroom(jtfjdMRTRoomname.getText().toString(),
							jtfjdMRCRoompw.getText().toString(), number, true)) {
						this.setVisible(false);
						jdMakeroom.dispose();
						ui = new FastInputUI(this);
						ui.setinfo(st);
						ui.setroom(st.stroomname, st.stroompw, st.stroompeople,1, true);
						th.setinfo(ui);
					} else {
						JOptionPane.showConfirmDialog(jdMakeroom,
								"알 수 없는 오류로 접속할 수 없습니다.", "경고",
								JOptionPane.CLOSED_OPTION,
								JOptionPane.WARNING_MESSAGE);
					}
				} else {
					JOptionPane.showConfirmDialog(jdMakeroom,
							"빈칸 없이 모두 입력해주세요.", "경고",
							JOptionPane.CLOSED_OPTION,
							JOptionPane.WARNING_MESSAGE);
				}
			} else {
				if (!jtfjdMRTRoomname.getText().isEmpty()) {
					int number = 0;
					switch (jcbjdMRBRoompeer.getSelectedIndex()) {
					case 0:
						number = 2;
						break;
					case 1:
						number = 3;
						break;
					case 2:
						number = 4;
						break;
					}
					if (st.Makeroom(jtfjdMRTRoomname.getText().toString(),
							jtfjdMRCRoompw.getText().toString(), number, false)) {
						this.setVisible(false);
						jdMakeroom.dispose();
						ui = new FastInputUI(this);
						ui.setinfo(st);
						ui.setroom(st.stroomname, st.stroompw, st.stroompeople,1, false);
						th.setinfo(ui);
					} else {
						JOptionPane.showConfirmDialog(jdMakeroom,
								"빈칸 없이 모두 입력해주세요.", "경고",
								JOptionPane.CLOSED_OPTION,
								JOptionPane.WARNING_MESSAGE);
					}

				} else {
					JOptionPane.showConfirmDialog(jdMakeroom,
							"빈칸 없이 모두 입력해주세요.", "경고",
							JOptionPane.CLOSED_OPTION,
							JOptionPane.WARNING_MESSAGE);
				}

			}
		} else if (str.equals("비밀번호사용")) {
			if (bjdRoompw) {
				jtfjdMRCRoompw.setEditable(false);
				jtfjdMRCRoompw.setText("");
				bjdRoompw = false;
			} else {
				jtfjdMRCRoompw.setEditable(true);
				bjdRoompw = true;
			}
		}
	}
	public void setth(FastInputThread th)
	{
		this.th = th;
	}
	
	public void setinfo(FastinputStart st) {
		this.st = st;
	}

}
