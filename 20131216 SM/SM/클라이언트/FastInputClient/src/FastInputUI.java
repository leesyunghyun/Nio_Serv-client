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
	// ui�������
	Container BigCT = getContentPane();
	// ��ü â ũ�⼳�� ���� �������
	private Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
	int x;
	int y;
	// ��ü â ũ�⼳�� ���� ����

	// �гμ������
	private JPanel jpBig = new JPanel(new BorderLayout()); // ��ü�г�
	private JPanel jpBigC = new JPanel(new BorderLayout()),
			jpBigR = new JPanel(new BorderLayout()); // ��ü�г��߾�,��ü�гο�����
	private JPanel jpBigCC = new JPanel(new GridLayout(1, 2));
	private JPanel jpCT = new JPanel(new FlowLayout()), jpCL = new JPanel(
			new FlowLayout()), jpCR = new JPanel(new FlowLayout()); // ��ü�г��߾���
																	// ���� ���ʰ�
																	// ������
	private JPanel jpRT = new JPanel(new BorderLayout()), jpRTC = new JPanel(
			new FlowLayout()), jpRC = new JPanel(new BorderLayout()),
			jpRB = new JPanel(new GridLayout(1, 2)); // ��ü�гο������� ��,�߾�,�Ʒ�
	private JPanel jpRCC = new JPanel(new GridLayout(1, 1)),
			jpRCB = new JPanel(new FlowLayout()); // ��ü�гο����ʾƷ��� �߾�,�Ʒ�
	// �гμ���

	// ��ư�������
	private JButton jbRTCBan = new JButton("����");
	private JButton jbRTCInvite = new JButton("�ʴ�");
	private JButton jbRTCView = new JButton("����");
	private JButton jbRTCHope = new JButton("����");
	private JButton jbRCSend = new JButton("����");
	public JButton jbReady = new JButton("�غ��ϱ�");
	private JButton jbOut = new JButton("������");
	// ��ư����

	// �󺧼������
	private JLabel jlCTRoominfo = new JLabel();
	private JLabel jlRTlistinfo = new JLabel();
	// �󺧼���

	// �ؽ�Ʈ�������
	protected JTextArea jaRBChatView = new JTextArea(10, 25);
	// �ؽ�Ʈ����

	// ��ũ���� �� ����Ʈ �������
	private JScrollPane jspRTlistpane = new JScrollPane();
	private JScrollPane jspRBChatpane = new JScrollPane();
	protected JList jspRTlist = new JList();
	// ��ũ���� �� ����Ʈ ����

	// �ؽ�Ʈ�ʵ弱�����
	private JTextField jtRBChat = new JTextField(18);
	// �ؽ�Ʈ�ʵ弱��

	// ui����
	// ********************************************************************************

	// *********************************************************************************
	// Ŭ���̾�Ʈ�����������

	private FastinputStart st;
	private FastInputWaitUI wui;
	protected Vector v = new Vector();

	// Ŭ���̾�Ʈ��������
	// *********************************************************************************

	public FastInputUI(final FastInputWaitUI wui) {
		this.wui = wui;
		jlCTRoominfo
				.setText("001����������������������������������������������ְ� ��ƿ�(1234)������������������������������������������");
		jlRTlistinfo.setText("ȸ����ϡ������������������������ο�:0��");
		jaRBChatView.setEditable(false);
		jaRBChatView.setLineWrap(true);

		jspRTlist.setFixedCellHeight(25);
		jspRTlistpane.setViewportView(jspRTlist);
		jspRBChatpane.setViewportView(jaRBChatView);

		jpCT.add(jlCTRoominfo);
		jpCL.setBackground(Color.white);
		jpCR.setBackground(Color.lightGray);
		jpCL.add(new JLabel("��"), BorderLayout.CENTER);
		jpCR.add(new JLabel("����"), BorderLayout.CENTER);

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
				if (jbReady.getText().toString().equals("����ϱ�")) {
					// ����ϱ��϶��� �׼� �ֱ�;
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
				st.Mynicname = str2 + "(����)";
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
								str4 += "(����)";
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

		if (str.equals("����")) {
			if (!jtRBChat.getText().isEmpty()) {
				if (jtRBChat.getText().length() < 100) {
					st.send(st.Mynicname + " : "
							+ jtRBChat.getText().toString());
					jtRBChat.setText("");
				}
			}
		} else if (str.equals("�غ��ϱ�")) {
			st.send("memberready");
			jbReady.setText("����ϱ�");
		} else if (str.equals("�����ϱ�")) {
			st.send("memberstart");
		} else if (str.equals("������")) {
			if (jbReady.getText().toString().equals("����ϱ�")) {
				st.send("memberunready");
				jbReady.setText("�غ��ϱ�");
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
			st.Mynicname = str2 + "(����)";
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
							str4 += "(����)";
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
		} else if (str.equals("����ϱ�")) {
			st.send("memberunready");
			jbReady.setText("�غ��ϱ�");
		}
	}

	public void setroom(String roomname, String roompw, int roommaxpeople,
			int roomnowpeople, boolean bpw) {
		if (bpw) {
			jlCTRoominfo.setText("001������������������������������������������" + roomname + "("
					+ roompw + ")������������������������������������������");
			jlRTlistinfo.setText("ȸ����ϡ������������������������ο�:" + roomnowpeople + "/"
					+ roommaxpeople + "��");
		} else {
			jlCTRoominfo.setText("001������������������������������������������" + roomname
					+ "������������������������������������������");
			jlRTlistinfo.setText("ȸ����ϡ������������������������ο�:" + roomnowpeople + "/"
					+ roommaxpeople + "��");
		}
	}

	public void setinfo(FastinputStart st) {
		this.st = st;
	}
}
