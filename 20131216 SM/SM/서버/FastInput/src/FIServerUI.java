import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class FIServerUI extends JFrame implements ActionListener {

	// **************************************************************************
	// ui�������

	// â ũ�� �� �� �������
	private Container BigCT = getContentPane();
	private Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
	int x, y;
	// â ũ�� �� �� ����

	// �гμ������
	private JPanel jpBig = new JPanel(new BorderLayout());
	private JPanel jpBL = new JPanel(new BorderLayout()), jpBC = new JPanel(
			new GridLayout(2, 1)), jpBR = new JPanel(new BorderLayout());
	private JPanel jpCT = new JPanel(new BorderLayout()), jpCB = new JPanel(
			new BorderLayout());
	private JPanel jpRT = new JPanel(new BorderLayout()), jpRB = new JPanel(
			new BorderLayout());
	private JPanel jpCBC = new JPanel(new GridLayout(1, 1)),
			jpCBB = new JPanel(new FlowLayout());
	private JPanel jpRTC = new JPanel(new GridLayout(1, 1)),
			jpRTB = new JPanel(new FlowLayout());
	private JPanel jpRBB = new JPanel(new GridLayout(1, 5));
	// �гμ���

	// ��ư�������
	private JButton jbCBBSend = new JButton("�˸���");
	private JButton jbRTBSend = new JButton("����");
	private JButton jbRBalloff = new JButton("��������");
	private JButton jbRBtest2 = new JButton("�׽�Ʈ2");
	private JButton jbRBtest3 = new JButton("�׽�Ʈ3");
	// ��ư����

	// �󺧼������
	private JLabel jlaBLMember = new JLabel();
	private JLabel jlaCTroom = new JLabel();
	private JLabel jlaCBChat = new JLabel();
	private JLabel jlaRTChat = new JLabel();
	private JLabel jlaRBetc = new JLabel();
	// �󺧼���

	// ��̾�����
	public JTextArea jtaCBCServerChatArea = new JTextArea(10, 10);
	public JTextArea jtaRTCWaitChatArea = new JTextArea(10, 10);
	// ��̾��

	// ��ũ���� �� ����Ʈ�������
	private JScrollPane jspLMemberlistpane = new JScrollPane();
	private JScrollPane jspCTRoomlistpane = new JScrollPane();
	private JScrollPane jspCBSerChatPane = new JScrollPane();
	private JScrollPane jspRTWaitChatPane = new JScrollPane();
	private static JList JlLMemberlist = new JList();
	private static JList jlCTRoomlist = new JList();
	// ��ũ���� �� ����Ʈ����

	// �ؽ�Ʈ�ʵ弱�����
	private JTextField jtfCBBServerChat = new JTextField(25);
	private JTextField jtfRTBWaitChat = new JTextField(25);
	// �ؽ�Ʈ�ʵ弱��

	// ui����
	// **************************************************************************

	// **************************************************************************
	// ���������������
	private FIServerInfo info;
	public static Hashtable<Integer, FIServerRoomInfo> hash;
	private FIServerRoomInfo Rinfo;
	private ByteBuffer bf;
	private FIServerData data;
	private Vector v;
	private Vector v2 = new Vector();
	private Vector v3 = new Vector();
	// ������������
	// **************************************************************************
	public FIServerUI() {
		jtaCBCServerChatArea.setEditable(false);
		jtaRTCWaitChatArea.setEditable(false);
		jtaRTCWaitChatArea.setLineWrap(true);

		JlLMemberlist.setFixedCellWidth(250);

		jlaBLMember.setText("FastInput �� �����ο� : 0��");
		jlaCBChat.setText("�����˸���");
		jlaCTroom.setText("���� ���");
		jlaRBetc.setText("���� ���� ���");
		jlaRTChat.setText("���� ä��");
		jlaBLMember.setHorizontalAlignment(SwingConstants.CENTER);
		jlaCBChat.setHorizontalAlignment(SwingConstants.CENTER);
		jlaCTroom.setHorizontalAlignment(SwingConstants.CENTER);
		jlaRBetc.setHorizontalAlignment(SwingConstants.CENTER);
		jlaRTChat.setHorizontalAlignment(SwingConstants.CENTER);

		jspLMemberlistpane.setViewportView(JlLMemberlist);
		jspCTRoomlistpane.setViewportView(jlCTRoomlist);
		jspCBSerChatPane.setViewportView(jtaCBCServerChatArea);
		jspRTWaitChatPane.setViewportView(jtaRTCWaitChatArea);

		jpCBC.add(jspCBSerChatPane);
		jpCBB.add(jtfCBBServerChat);
		jpCBB.add(jbCBBSend);

		jpRTC.add(jspRTWaitChatPane);
		jpRTB.add(jtfRTBWaitChat);
		jpRTB.add(jbRTBSend);

		jpRBB.add(jbRBalloff);
		jpRBB.add(jbRBtest2);
		jpRBB.add(jbRBtest3);

		jpCT.add(jspCTRoomlistpane, BorderLayout.CENTER);
		jpCT.add(jlaCTroom, BorderLayout.NORTH);
		jpCB.add(jlaCBChat, BorderLayout.NORTH);
		jpCB.add(jpCBC, BorderLayout.CENTER);
		jpCB.add(jpCBB, BorderLayout.SOUTH);
		jpRT.add(jlaRTChat, BorderLayout.NORTH);
		jpRT.add(jpRTC, BorderLayout.CENTER);
		jpRT.add(jpRTB, BorderLayout.SOUTH);
		jpRB.add(jlaRBetc, BorderLayout.NORTH);
		jpRB.add(jpRBB, BorderLayout.CENTER);

		jpBL.add(jlaBLMember, BorderLayout.NORTH);
		jpBL.add(jspLMemberlistpane, BorderLayout.CENTER);
		jpBC.add(jpCT);
		jpBC.add(jpCB);
		jpBR.add(jpRT, BorderLayout.CENTER);
		jpBR.add(jpRB, BorderLayout.SOUTH);

		jpBig.add(jpBL, BorderLayout.WEST);
		jpBig.add(jpBC, BorderLayout.CENTER);
		jpBig.add(jpBR, BorderLayout.EAST);

		BigCT.setLayout(new BorderLayout());
		BigCT.add(jpBig, BorderLayout.CENTER);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 600);
		setTitle("FastInputServer");
		x = (int) ((dm.getWidth() / 2) - (this.getWidth() / 2));
		y = (int) ((dm.getHeight() / 2) - (this.getHeight() / 2));
		setLocation(new Point(x, y));
		setVisible(true);

		jbCBBSend.addActionListener(this);
		jbRTBSend.addActionListener(this);
		jbRBalloff.addActionListener(this);
	}

	public void Setoption(FIServerInfo info, Vector v, int updown, int channel) {
		this.info = info;
		this.v = v;
		String str = "(�����)";
		switch (updown) {
		case 0:
			this.v2.add(info.getnicname() + str);
			break;
		case 1:
			this.v2.remove(info.getnicname() + str);
			break;
		case 2:
			switch (channel) {
			case 1001:
				str = "(�����)";
				this.v2.setElementAt(info.getnicname() + str, v.indexOf(info));
				break;
			case 1002:
				str = "(������)";
				this.v2.setElementAt(info.getnicname() + str, v.indexOf(info));
				break;
			default:
				str = "����";
				break;
			}
			break;
		default:
			break;
		}
		JlLMemberlist.setListData(this.v2);
		jlaBLMember.setText("FastInput �� �����ο� : " + v.size() + "��");
	}

	public void Roominset(String Roomname,ByteBuffer bf)
	{
		this.bf = bf;
		String str = "settingroom";
		try {
			FIServerThread.sleep(300);
			bf.put(FIServerThread.toByteBuffer(str));
			bf.position(20);
			str = Roomname;
			bf.put(FIServerThread.toByteBuffer(str));
			for(int i=0;i<this.v.size();i++)
			{
				bf.flip();
				FIServerInfo info3 = (FIServerInfo) this.v.get(i);
				if(info3.getchannel() == FIServerThread.PLAY_ROOM)
				{
					info3.write(bf);
				}
			}
		} catch (Exception e) {
			// TODO �ڵ� ������ catch ���
			e.printStackTrace();
		}
		bf.clear();
	}
	public boolean Gamesetting(String roomstate, FIServerInfo info,Vector v)
	{
		this.info = info;
		this.v = v;
		switch(roomstate)
		{
		case "memberready":
			this.info.setRoomState(roomstate);
			return true;
		case "memberunready":
			this.info.setRoomState(roomstate);
			return true;
		case "memberstart":
			for(int i=0;i<this.v.size();i++)
			{
				FIServerInfo info2 = (FIServerInfo)this.v.get(i);
				if(info2.getchannel() == FIServerThread.PLAY_ROOM)
				{
					if(this.info.equals(info2))
					{
						continue;
					}
					if(info2.getRoomState().equals("memberready"))
					{
					}
					else
					{
						
						return false;
					}
				}
			}
			return true;
		}
		return true;
	}
	public boolean JoinRoom(FIServerInfo info, Vector v,String roomname,String roompw, ByteBuffer bf)
	{
		this.bf = bf;
		this.info = info;
		this.v = v;
		int a = this.v3.indexOf(roomname);
		FIServerRoomInfo info2 = hash.get(a);
		switch(info2.getRoomType())
		{
		case "������":
			if(info2.getNowRoomPeople() == info2.getMaxRoomPeople())
			{
				return false;
			}
			else
			{
				this.info.setRoom(roomname);
				
				for(int i=1;i<info2.getMaxRoomPeople();i++)
				{
					if(FIServerStart.roominfo[a][i] == false)
					{
						try {
							FIServerThread.sleep(300);
						} catch (InterruptedException e1) {
							// TODO �ڵ� ������ catch ���
							e1.printStackTrace();
						}
						FIServerStart.roominfo[a][i] = true;
						info2.setNowRoomPeople(info2.getNowRoomPeople()+1);
						info2.setRoomperson(this.info.getnicname() + "(�����)",true);
						hash.remove(a);
						hash.put(a, info2);
						this.v3.setElementAt(info2.makeRoom(), a);
						for(int j=0;j<this.v.size();j++)
						{
							FIServerInfo info3 = (FIServerInfo) this.v.get(j);
							if(this.info.getRoom().equals(info3.getRoom()))
							{
								String str2 ="serroomset2";
								if(this.info.equals(info3))
								{
									str2 ="serroomset";
									try {
										bf.put(FIServerThread.toByteBuffer(str2));
										bf.position(20);
										str2 = info2.getRoomName();
										bf.put(FIServerThread.toByteBuffer(str2));
										bf.position(40);
										str2 = info2.getRoompw();
										bf.put(FIServerThread.toByteBuffer(str2));
										bf.position(60);
										str2 = Integer.toString(info2.getNowRoomPeople());
										bf.put(FIServerThread.toByteBuffer(str2));
										bf.position(80);
										str2 = Integer.toString(info2.getMaxRoomPeople());
										bf.put(FIServerThread.toByteBuffer(str2));
										bf.position(100);
										str2 = "false";
										bf.put(FIServerThread.toByteBuffer(str2));
										bf.position(120);
										str2 = info2.getRoomperson();
										bf.put(FIServerThread.toByteBuffer(str2));
										bf.flip();
										info3.write(bf);
										FIServerStart.initbuffer(bf);
									} catch (UnsupportedEncodingException e) {
										// TODO �ڵ� ������ catch ���
										e.printStackTrace();
									}
									
									continue;
								}
								
								try {
									bf.put(FIServerThread.toByteBuffer(str2));
									bf.position(20);
									str2 = info2.getRoomName();
									bf.put(FIServerThread.toByteBuffer(str2));
									bf.position(40);
									str2 = info2.getRoompw();
									bf.put(FIServerThread.toByteBuffer(str2));
									bf.position(60);
									str2 = Integer.toString(info2.getNowRoomPeople());
									bf.put(FIServerThread.toByteBuffer(str2));
									bf.position(80);
									str2 = Integer.toString(info2.getMaxRoomPeople());
									bf.put(FIServerThread.toByteBuffer(str2));
									bf.position(100);
									str2 = "false";
									bf.put(FIServerThread.toByteBuffer(str2));
									bf.position(120);
									str2 = info2.getRoomperson();
									bf.put(FIServerThread.toByteBuffer(str2));
									bf.flip();
									info3.write(bf);
									FIServerStart.initbuffer(bf);
								} catch (UnsupportedEncodingException e) {
									// TODO �ڵ� ������ catch ���
									e.printStackTrace();
								}
								
								
								info3.setRoom(info2.makeRoom());
							}
						}
						this.info.setRoom(info2.makeRoom());
						this.info.setRoominNum(info2.getNowRoomPeople()-1);
						break;
					}
				}
				jlCTRoomlist.setListData(this.v3);
				return true;
			}
		case "�������":
			if(!info2.getRoompw().equals(roompw))
			{
				return false;
			}
			if(info2.getNowRoomPeople() == info2.getMaxRoomPeople())
			{
				return false;
			}
			else
			{
				this.info.setRoom(roomname);
				
				for(int i=1;i<info2.getMaxRoomPeople();i++)
				{
					if(FIServerStart.roominfo[a][i] == false)
					{
						try {
							FIServerThread.sleep(300);
						} catch (InterruptedException e1) {
							// TODO �ڵ� ������ catch ���
							e1.printStackTrace();
						}
						FIServerStart.roominfo[a][i] = true;
						info2.setNowRoomPeople(info2.getNowRoomPeople()+1);
						info2.setRoomperson(this.info.getnicname()+"(�����)",true);
						hash.remove(a);
						hash.put(a, info2);
						this.v3.setElementAt(info2.makeRoom(), a);
						for(int j=0;j<this.v.size();j++)
						{
							FIServerInfo info3 = (FIServerInfo) this.v.get(j);
							if(this.info.getRoom().equals(info3.getRoom()))
							{
								String str2 ="serroomset2";
								if(this.info.equals(info3))
								{
									str2 ="serroomset";
									try {
										bf.put(FIServerThread.toByteBuffer(str2));
										bf.position(20);
										str2 = info2.getRoomName();
										bf.put(FIServerThread.toByteBuffer(str2));
										bf.position(40);
										str2 = info2.getRoompw();
										bf.put(FIServerThread.toByteBuffer(str2));
										bf.position(60);
										str2 = Integer.toString(info2.getNowRoomPeople());
										bf.put(FIServerThread.toByteBuffer(str2));
										bf.position(80);
										str2 = Integer.toString(info2.getMaxRoomPeople());
										bf.put(FIServerThread.toByteBuffer(str2));
										bf.position(100);
										str2 = "true";
										bf.put(FIServerThread.toByteBuffer(str2));
										bf.position(120);
										str2 = info2.getRoomperson();
										bf.put(FIServerThread.toByteBuffer(str2));
										bf.flip();
										info3.write(bf);
										FIServerStart.initbuffer(bf);
									} catch (UnsupportedEncodingException e) {
										// TODO �ڵ� ������ catch ���
										e.printStackTrace();
									}
									
									continue;
								}
								
								try {
									bf.put(FIServerThread.toByteBuffer(str2));
									bf.position(20);
									str2 = info2.getRoomName();
									bf.put(FIServerThread.toByteBuffer(str2));
									bf.position(40);
									str2 = info2.getRoompw();
									bf.put(FIServerThread.toByteBuffer(str2));
									bf.position(60);
									str2 = Integer.toString(info2.getNowRoomPeople());
									bf.put(FIServerThread.toByteBuffer(str2));
									bf.position(80);
									str2 = Integer.toString(info2.getMaxRoomPeople());
									bf.put(FIServerThread.toByteBuffer(str2));
									bf.position(100);
									str2 = "true";
									bf.put(FIServerThread.toByteBuffer(str2));
									bf.position(120);
									str2 = info2.getRoomperson();
									bf.put(FIServerThread.toByteBuffer(str2));
									bf.flip();
									info3.write(bf);
									FIServerStart.initbuffer(bf);
								} catch (UnsupportedEncodingException e) {
									// TODO �ڵ� ������ catch ���
									e.printStackTrace();
								}
								
								
								info3.setRoom(info2.makeRoom());
							}
						}
						this.info.setRoom(info2.makeRoom());
						this.info.setRoominNum(info2.getNowRoomPeople()-1);
						break;
					}
				}
				jlCTRoomlist.setListData(this.v3);
				return true;
			}
		}
		return false;
	}
	public Vector getV3()
	{
		return this.v3;
	}
	public Vector SetRoom(FIServerInfo info, Vector v,
			Hashtable<Integer, FIServerRoomInfo> hash, boolean updown, String roomname,
			String roompw, int roompeople) {
		this.info = info;
		this.v = v;
		if (updown) {
			this.hash = hash;
			int index = FIServerStart.roomcount;
				FIServerStart.roominfo[index][0] = true;
				for(int i=1;i<4;i++)
				{
					FIServerStart.roominfo[index][i] = false;
				}
				
			if (roompw.isEmpty()) {
				Rinfo = new FIServerRoomInfo("������", roomname, 1, roompeople, info.getnicname(), index,roompw);
				this.v3.add(Rinfo.makeRoom());
			} else {
				Rinfo = new FIServerRoomInfo("�������", roomname, 1, roompeople, info.getnicname(), index,roompw);
				this.v3.add(Rinfo.makeRoom());
			}
			Rinfo.setRoomperson(this.info.getnicname() + "(����)",true);
			this.hash.put(index, Rinfo);
			this.info.setRoom(Rinfo.makeRoom());
			this.info.setRoominNum(0);
			jlCTRoomlist.setListData(this.v3);
			FIServerStart.roomcount++;
		} else {
			int a = this.v3.indexOf(this.info.getRoom());
			FIServerRoomInfo info2 = this.hash.get(a);
			try {
				FIServerThread.sleep(300);
			} catch (InterruptedException e) {
				// TODO �ڵ� ������ catch ���
				e.printStackTrace();
			}
			switch(this.info.getRoominNum())
			{
			case 0:
				if(info2.getNowRoomPeople() == 1)
				{
					//�����
					this.hash.remove(a);
					this.v3.remove(this.info.getRoom());
					jlCTRoomlist.setListData(this.v3);
					Rinfo = null;
					FIServerStart.roomcount--;
					return this.v3;
				}
				else
				{
					//����ѱ��
					info2.setNowRoomPeople(info2.getNowRoomPeople()-1);
					info2.setRoomperson(this.info.getnicname() +"(����)",false);
					for(int i=0;i<this.v.size();i++)
					{
						FIServerInfo info3 = (FIServerInfo)this.v.get(i);
						if(this.info.getRoom().equals(info3.getRoom()))
						{
							if(this.info.equals(info3))
							{
								continue;
							}
							info3.setRoominNum(info3.getRoominNum()-1);
							info3.setRoom(info2.makeRoom());
						}
					}
					this.info.setRoom(info2.makeRoom());
					this.hash.remove(a);
					this.hash.put(a, info2);
					this.v3.setElementAt(info2.makeRoom(),a);
					jlCTRoomlist.setListData(this.v3);
					
					for(int i=1;i<info2.getMaxRoomPeople();i++)
					{
						if(FIServerStart.roominfo[a][i] == true)
						{
							FIServerStart.roominfo[a][i-1] = true;
							FIServerStart.roominfo[a][i] = false;
						}
					}
					
					return this.v3;
				}
			case 1:
			case 2:
			case 3:
					info2.setNowRoomPeople(info2.getNowRoomPeople()-1);
					info2.setRoomperson(this.info.getnicname() + "(�����)",false);
					for(int i=0;i<this.v.size();i++)
					{
						FIServerInfo info3 = (FIServerInfo)this.v.get(i);
						if(this.info.getRoom().equals(info3.getRoom()))
						{
							if(this.info.getRoominNum() > info3.getRoominNum())
							{
								info3.setRoom(info2.makeRoom());
							}
							else{
								if(this.info.equals(info3))
								{
									continue;
								}
								info3.setRoominNum(info3.getRoominNum()-1);
								info3.setRoom(info2.makeRoom());
							}	
						}
					}
					this.info.setRoom(info2.makeRoom());
					this.hash.remove(a);
					this.hash.put(a, info2);
					this.v3.setElementAt(info2.makeRoom(), a);
					jlCTRoomlist.setListData(this.v3);
					
					for(int i=1;i<info2.getMaxRoomPeople();i++)
					{
						if(FIServerStart.roominfo[a][i] == true)
						{
							FIServerStart.roominfo[a][i-1] = true;
							FIServerStart.roominfo[a][i] = false;
						}
					}
					
					return this.v3;
				default:
					break;
			}
			/*
			for (int i = 0; i < info2.getMaxRoomPeople(); i++) {
				if (FIServerStart.roominfo[a][i] == true) {
					FIServerStart.roominfo[a][i-1] = true;
					FIServerStart.roominfo[a][i] = false;
				} else {
					if (i == 0) {
						hash.remove(a);
						this.v3.remove(this.info.getRoom());
						jlCTRoomlist.setListData(this.v3);
						return this.v3;
					} else {
						jlCTRoomlist.setListData(this.v3);
						return this.v3;
					}
				}
			}
			*/
		}
		return this.v3;
	}
	public void setdata(FIServerData data)
	{
		this.data = data;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String str = arg0.getActionCommand();

		if (str == "����") {
			if (!jtfRTBWaitChat.getText().isEmpty()) {
				String str2 = "���� : " + jtfRTBWaitChat.getText().toString();
				info.bf.put(str2.getBytes());
				for (int i = 0; i < v.size(); i++) {
					info.bf.flip();
					FIServerInfo info2 = (FIServerInfo) v.get(i);
					info2.write(info.bf);
				}
				FIServerStart.initbuffer(info.bf);
				jtaRTCWaitChatArea.append("�� : "
						+ jtfRTBWaitChat.getText().toString() + '\n');
				jtfRTBWaitChat.setText("");
			}
		} else if (str == "�˸���") {
			if (!jtfCBBServerChat.getText().isEmpty()) {
				String str2 = "�˸��� : " + jtfCBBServerChat.getText().toString();
				info.bf.put(str2.getBytes());
				for (int i = 0; i < v.size(); i++) {
					info.bf.flip();
					FIServerInfo info2 = (FIServerInfo) v.get(i);
					info2.write(info.bf);
				}
				FIServerStart.initbuffer(info.bf);
				jtaCBCServerChatArea.append("�˸��� : "
						+ jtfCBBServerChat.getText().toString() + '\n');
				jtfCBBServerChat.setText("");
			}
		}
		else if(str =="��������")
		{
			data.setalloff();
		}
	}

}
