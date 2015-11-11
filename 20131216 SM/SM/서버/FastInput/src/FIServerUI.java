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
	// ui선언시작

	// 창 크기 및 뷰 선언시작
	private Container BigCT = getContentPane();
	private Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
	int x, y;
	// 창 크기 및 뷰 선언끝

	// 패널선언시작
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
	// 패널선언끝

	// 버튼선언시작
	private JButton jbCBBSend = new JButton("알림말");
	private JButton jbRTBSend = new JButton("전송");
	private JButton jbRBalloff = new JButton("강제오프");
	private JButton jbRBtest2 = new JButton("테스트2");
	private JButton jbRBtest3 = new JButton("테스트3");
	// 버튼선언끝

	// 라벨선언시작
	private JLabel jlaBLMember = new JLabel();
	private JLabel jlaCTroom = new JLabel();
	private JLabel jlaCBChat = new JLabel();
	private JLabel jlaRTChat = new JLabel();
	private JLabel jlaRBetc = new JLabel();
	// 라벨선언끝

	// 어레이어선언시작
	public JTextArea jtaCBCServerChatArea = new JTextArea(10, 10);
	public JTextArea jtaRTCWaitChatArea = new JTextArea(10, 10);
	// 어레이어선언끝

	// 스크롤판 및 리스트선언시작
	private JScrollPane jspLMemberlistpane = new JScrollPane();
	private JScrollPane jspCTRoomlistpane = new JScrollPane();
	private JScrollPane jspCBSerChatPane = new JScrollPane();
	private JScrollPane jspRTWaitChatPane = new JScrollPane();
	private static JList JlLMemberlist = new JList();
	private static JList jlCTRoomlist = new JList();
	// 스크롤판 및 리스트선언끝

	// 텍스트필드선언시작
	private JTextField jtfCBBServerChat = new JTextField(25);
	private JTextField jtfRTBWaitChat = new JTextField(25);
	// 텍스트필드선언끝

	// ui선언끝
	// **************************************************************************

	// **************************************************************************
	// 서버설정선언시작
	private FIServerInfo info;
	public static Hashtable<Integer, FIServerRoomInfo> hash;
	private FIServerRoomInfo Rinfo;
	private ByteBuffer bf;
	private FIServerData data;
	private Vector v;
	private Vector v2 = new Vector();
	private Vector v3 = new Vector();
	// 서버설정선언끝
	// **************************************************************************
	public FIServerUI() {
		jtaCBCServerChatArea.setEditable(false);
		jtaRTCWaitChatArea.setEditable(false);
		jtaRTCWaitChatArea.setLineWrap(true);

		JlLMemberlist.setFixedCellWidth(250);

		jlaBLMember.setText("FastInput 총 접속인원 : 0명");
		jlaCBChat.setText("서버알림말");
		jlaCTroom.setText("대기방 목록");
		jlaRBetc.setText("각종 서버 기능");
		jlaRTChat.setText("대기실 채팅");
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
		String str = "(대기중)";
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
				str = "(대기중)";
				this.v2.setElementAt(info.getnicname() + str, v.indexOf(info));
				break;
			case 1002:
				str = "(게임중)";
				this.v2.setElementAt(info.getnicname() + str, v.indexOf(info));
				break;
			default:
				str = "오류";
				break;
			}
			break;
		default:
			break;
		}
		JlLMemberlist.setListData(this.v2);
		jlaBLMember.setText("FastInput 총 접속인원 : " + v.size() + "명");
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
			// TODO 자동 생성된 catch 블록
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
		case "공개방":
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
							// TODO 자동 생성된 catch 블록
							e1.printStackTrace();
						}
						FIServerStart.roominfo[a][i] = true;
						info2.setNowRoomPeople(info2.getNowRoomPeople()+1);
						info2.setRoomperson(this.info.getnicname() + "(대기중)",true);
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
										// TODO 자동 생성된 catch 블록
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
									// TODO 자동 생성된 catch 블록
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
		case "비공개방":
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
							// TODO 자동 생성된 catch 블록
							e1.printStackTrace();
						}
						FIServerStart.roominfo[a][i] = true;
						info2.setNowRoomPeople(info2.getNowRoomPeople()+1);
						info2.setRoomperson(this.info.getnicname()+"(대기중)",true);
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
										// TODO 자동 생성된 catch 블록
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
									// TODO 자동 생성된 catch 블록
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
				Rinfo = new FIServerRoomInfo("공개방", roomname, 1, roompeople, info.getnicname(), index,roompw);
				this.v3.add(Rinfo.makeRoom());
			} else {
				Rinfo = new FIServerRoomInfo("비공개방", roomname, 1, roompeople, info.getnicname(), index,roompw);
				this.v3.add(Rinfo.makeRoom());
			}
			Rinfo.setRoomperson(this.info.getnicname() + "(방장)",true);
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
				// TODO 자동 생성된 catch 블록
				e.printStackTrace();
			}
			switch(this.info.getRoominNum())
			{
			case 0:
				if(info2.getNowRoomPeople() == 1)
				{
					//방삭제
					this.hash.remove(a);
					this.v3.remove(this.info.getRoom());
					jlCTRoomlist.setListData(this.v3);
					Rinfo = null;
					FIServerStart.roomcount--;
					return this.v3;
				}
				else
				{
					//방장넘기기
					info2.setNowRoomPeople(info2.getNowRoomPeople()-1);
					info2.setRoomperson(this.info.getnicname() +"(방장)",false);
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
					info2.setRoomperson(this.info.getnicname() + "(대기중)",false);
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

		if (str == "전송") {
			if (!jtfRTBWaitChat.getText().isEmpty()) {
				String str2 = "서버 : " + jtfRTBWaitChat.getText().toString();
				info.bf.put(str2.getBytes());
				for (int i = 0; i < v.size(); i++) {
					info.bf.flip();
					FIServerInfo info2 = (FIServerInfo) v.get(i);
					info2.write(info.bf);
				}
				FIServerStart.initbuffer(info.bf);
				jtaRTCWaitChatArea.append("나 : "
						+ jtfRTBWaitChat.getText().toString() + '\n');
				jtfRTBWaitChat.setText("");
			}
		} else if (str == "알림말") {
			if (!jtfCBBServerChat.getText().isEmpty()) {
				String str2 = "알림말 : " + jtfCBBServerChat.getText().toString();
				info.bf.put(str2.getBytes());
				for (int i = 0; i < v.size(); i++) {
					info.bf.flip();
					FIServerInfo info2 = (FIServerInfo) v.get(i);
					info2.write(info.bf);
				}
				FIServerStart.initbuffer(info.bf);
				jtaCBCServerChatArea.append("알림말 : "
						+ jtfCBBServerChat.getText().toString() + '\n');
				jtfCBBServerChat.setText("");
			}
		}
		else if(str =="강제오프")
		{
			data.setalloff();
		}
	}

}
