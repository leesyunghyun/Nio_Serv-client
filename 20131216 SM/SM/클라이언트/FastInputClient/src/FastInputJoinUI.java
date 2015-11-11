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
	// ui���� �������
	Container BigCT = getContentPane();
	// ��ü â ũ�⼳�� ���� �������
	private Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
	int x;
	int y;
	// ��ü â ũ�⼳�� ���� ����

	// �гμ������
	private JPanel jpBig = new JPanel(new BorderLayout());
	private JPanel jpBC = new JPanel(new GridLayout(2, 1)), jpBB = new JPanel(
			new FlowLayout());
	private JPanel jpCT = new JPanel(new GridLayout(1, 2, -50, 0)),
			jpCB = new JPanel(new GridLayout(1, 2, -50, 0));
	// �гμ���

	// �ؽ�Ʈ�ʵ� �������
	private JTextField jtfCTInputID = new JTextField(8);
	private JTextField jtfCBInputPW = new JPasswordField(8);
	// �ؽ�Ʈ�ʵ� ����

	// ��ư�������
	private JButton jbBLogin = new JButton("�α���");
	private JButton jbBNew = new JButton("ȸ������");
	private JButton jbBFind = new JButton("ID/PWã��");
	// ��ư����

	// �󺧼������
	private JLabel jlaCTID = new JLabel("I D : ", JLabel.CENTER);
	private JLabel jlaCBPW = new JLabel("P W : ", JLabel.CENTER);
	private JLabel jlactr = new JLabel();
	private JLabel jlacbr = new JLabel();
	// �󺧼���

	// ui���� ����
	// ***************************************************************

	// ***************************************************************
	// ȸ������UI�������� ����

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
	private JButton jbjdIDCheck = new JButton("���̵��ߺ��˻�"),
			jbjdMake = new JButton("�����"),
			jbjdNicCheck = new JButton("�г����ߺ��˻�");
	private JLabel jljdID = new JLabel("���̵� : ", JLabel.RIGHT),
			jljdPW = new JLabel("��й�ȣ : ", JLabel.RIGHT), jljdPW2 = new JLabel(
					"��й�ȣȮ�� : ", JLabel.RIGHT), jljdName = new JLabel("�̸� : ",
					JLabel.RIGHT), jljdNicname = new JLabel("�г��� : ",
					JLabel.RIGHT), jljdBirth = new JLabel("������� : ",
					JLabel.RIGHT);
	private int jdx, jdy;

	// ȸ������UI�������� ��
	// ***************************************************************

	// ***************************************************************
	// ���̵���ã��UI�������� ����
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

	private JLabel jlajdidname = new JLabel("�г��� : ", JLabel.RIGHT),
			jlajdidpw = new JLabel("��й�ȣ : ", JLabel.RIGHT),
			jlajdpwid = new JLabel("���̵� : ", JLabel.RIGHT),
			jlajdpwname = new JLabel("�̸� : ", JLabel.RIGHT),
			jlajdpwbirth = new JLabel("������� : ", JLabel.RIGHT);

	private JTextField jtfjdidnicname = new JTextField(8),
			jtfjdidpw = new JPasswordField(8), jtfjdpwid = new JTextField(8),
			jtfjdpwname = new JTextField(8), jtfjdpwbirth = new JPasswordField(
					8);

	private JButton jbjdidfind = new JButton("���̵� ã��");
	private JButton jbjdpwfind = new JButton("��й�ȣ ã��");
	// ���̵���ã��UI�������� ��
	// ***************************************************************

	// ***************************************************************
	// Ŭ���̾�Ʈ�����������
	private FastinputStart st;
	private boolean idcheck = false; // ���̵��ߺ��˻�
	private boolean niccheck = false; // �г����ߺ��˻�

	// Ŭ���̾�Ʈ��������
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
		jdNewmember.setTitle("ȸ������");
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

		jtpjdfindipw.addTab("���̵�ã��", jpjdfindid);
		jtpjdfindipw.addTab("��й�ȣã��", jpjdfindpw);

		jdfindidpw.add(jtpjdfindipw);

		jdfindidpw.setModal(true);
		jdfindidpw.setSize(210, 170);
		jdfindidpw.setResizable(false);
		jdx = (int) ((dm3.getWidth() / 2) - (jdfindidpw.getWidth() / 2));
		jdy = (int) ((dm3.getHeight() / 2) - (jdfindidpw.getHeight() / 2));
		jdfindidpw.setLocation(new Point(jdx, jdy));
		jdfindidpw.setTitle("��������ã��");
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
		if (str.equals("�α���")) {
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
							"���̵� �������� �ʰų�\n��й�ȣ�� �ùٸ��� �ʽ��ϴ�.\n�ٽ� Ȯ�����ּ���!",
							"���", JOptionPane.CLOSED_OPTION,
							JOptionPane.WARNING_MESSAGE);
				}
			} else {
				JOptionPane.showConfirmDialog(jdNewmember, "��ĭ ���� ��� �Է����ּ���.",
						"���", JOptionPane.CLOSED_OPTION,
						JOptionPane.WARNING_MESSAGE);
			}
		} else if (str.equals("ȸ������")) {
			NewMember();
			idcheck = false;
			niccheck = false;
		} else if (str.equals("ID/PWã��")) {
			findidpw();

		} else if (str.equals("�����")) {
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
										"ȯ���մϴ�. �α������ּ���!",
										"ȯ��",
										JOptionPane.CLOSED_OPTION,
										JOptionPane.INFORMATION_MESSAGE);
							} else {
								JOptionPane
										.showConfirmDialog(
												jdNewmember,
												"��ȿ���� ���� ��������Դϴ�.\n8�ڸ��� �Է����ּ��� ��) 19910805",
												"���",
												JOptionPane.CLOSED_OPTION,
												JOptionPane.WARNING_MESSAGE);
							}
						} else {
							JOptionPane
									.showConfirmDialog(
											jdNewmember,
											"��ȿ���� ���� ��������Դϴ�.\n8�ڸ��� �Է����ּ��� ��) 19910805",
											"���", JOptionPane.CLOSED_OPTION,
											JOptionPane.WARNING_MESSAGE);
						}
					} else {
						JOptionPane.showConfirmDialog(jdNewmember,
								"��й�ȣ�� ��ġ���� �ʽ��ϴ�.", "���",
								JOptionPane.CLOSED_OPTION,
								JOptionPane.WARNING_MESSAGE);
					}
				} else {
					JOptionPane.showConfirmDialog(jdNewmember,
							"���̵� �г���\n�ߺ�Ȯ�� ��ư�� �����ּ���", "���",
							JOptionPane.CLOSED_OPTION,
							JOptionPane.WARNING_MESSAGE);
				}
			} else {
				JOptionPane.showConfirmDialog(jdNewmember, "��ĭ ���� ��� �Է����ּ���.",
						"���", JOptionPane.CLOSED_OPTION,
						JOptionPane.WARNING_MESSAGE);
			}
		} else if (str.equals("���̵��ߺ��˻�")) {

			if (!jtfjdID.getText().isEmpty()) {
				if (st.IDCheck(jtfjdID.getText().toString())) {
					int check;
					check = JOptionPane.showConfirmDialog(jdNewmember,
							"����� �� �ִ� ���̵� �Դϴ�. ����Ͻðڽ��ϱ�?", "ID��� ����",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
					if (check == 0) {
						idcheck = true;
						jbjdIDCheck.setEnabled(false);
						jtfjdID.setEditable(false);
					}
				} else {
					JOptionPane.showConfirmDialog(jdNewmember,
							"�̹� ����ϰ� �ִ� ���̵� �Դϴ�.", "ID��� �Ұ���",
							JOptionPane.CLOSED_OPTION,
							JOptionPane.WARNING_MESSAGE);
					idcheck = false;
				}
			} else {
				JOptionPane.showConfirmDialog(jdNewmember, "���̵� �Է����ּ���.",
						"���̵��Է�", JOptionPane.CLOSED_OPTION,
						JOptionPane.WARNING_MESSAGE);
			}

		} else if (str.equals("�г����ߺ��˻�")) {
			if (!jtfjdNicname.getText().isEmpty()) {
				if (st.NicCheck(jtfjdNicname.getText().toString())) {
					int check;
					check = JOptionPane.showConfirmDialog(jdNewmember,
							"����� �� �ִ� �г��� �Դϴ�. ����Ͻðڽ��ϱ�?", "�г��ӻ�� ����",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
					if (check == 0) {
						niccheck = true;
						jbjdNicCheck.setEnabled(false);
						jtfjdNicname.setEditable(false);
					}
				} else {
					JOptionPane.showConfirmDialog(jdNewmember,
							"�̹� ����ϰ� �ִ� �г��� �Դϴ�.", "�г��ӻ�� �Ұ���",
							JOptionPane.CLOSED_OPTION,
							JOptionPane.WARNING_MESSAGE);
					niccheck = false;
				}
			} else {
				JOptionPane.showConfirmDialog(jdNewmember, "�г����� �Է����ּ���.",
						"�г����Է�", JOptionPane.CLOSED_OPTION,
						JOptionPane.WARNING_MESSAGE);
			}

		} else if (str.equals("���̵� ã��")) {
			if (!jtfjdidnicname.getText().isEmpty()
					&& !jtfjdidpw.getText().isEmpty()) {
				String findid = st.findid(jtfjdidnicname.getText().toString(),
						jtfjdidpw.getText().toString());
				if (findid != "false") {
					if (JOptionPane.showConfirmDialog(jdfindidpw, "ã���ô� ���̵�� "
							+ findid + "�Դϴ�. ", "���̵�ã��",
							JOptionPane.CLOSED_OPTION,
							JOptionPane.INFORMATION_MESSAGE) == 0) {
						jdfindidpw.dispose();
					}
				} else {
					JOptionPane.showConfirmDialog(jdfindidpw,
							"��ġ�ϴ� ������ �����ϴ�.\n �ٽ� Ȯ�����ּ���.", "���",
							JOptionPane.CLOSED_OPTION,
							JOptionPane.WARNING_MESSAGE);
				}
			} else {
				JOptionPane.showConfirmDialog(jdfindidpw, "��ĭ ���� ��� �Է����ּ���.",
						"���", JOptionPane.CLOSED_OPTION,
						JOptionPane.WARNING_MESSAGE);

			}
		} else if (str.equals("��й�ȣ ã��")) {
			if (!jtfjdpwname.getText().isEmpty()
					&& !jtfjdpwid.getText().isEmpty()
					&& !jtfjdpwbirth.getText().isEmpty()) {
				String findpw = st.findpw(jtfjdpwid.getText().toString(),
						jtfjdpwname.getText().toString(), jtfjdpwbirth
								.getText().toString());
				if (findpw != "false") {
					if (JOptionPane.showConfirmDialog(jdfindidpw, "ã���ô� ��й�ȣ�� "
							+ findpw + "�Դϴ�. ", "��й�ȣã��",
							JOptionPane.CLOSED_OPTION,
							JOptionPane.INFORMATION_MESSAGE) == 0) {
						jdfindidpw.dispose();
					}
				} else {
					JOptionPane.showConfirmDialog(jdfindidpw,
							"��ġ�ϴ� ������ �����ϴ�.\n �ٽ� Ȯ�����ּ���.", "���",
							JOptionPane.CLOSED_OPTION,
							JOptionPane.WARNING_MESSAGE);
				}
			} else {
				JOptionPane.showConfirmDialog(jdfindidpw, "��ĭ ���� ��� �Է����ּ���.",
						"���", JOptionPane.CLOSED_OPTION,
						JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	public void setinfo(FastinputStart st) {
		this.st = st;
	}

}
