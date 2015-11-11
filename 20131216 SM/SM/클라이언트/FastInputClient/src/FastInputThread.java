import java.awt.Graphics;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Vector;

import javax.swing.JOptionPane;



public class FastInputThread extends Thread{
	private SocketChannel sc;
	private ByteBuffer bf;
	private FastinputStart st;
	private FastInputUI ui;
	private FastInputWaitUI wui;
	public static boolean selectUI = false;
	private Selector selector;
	private Vector v;
	private Vector v2;
	public static Vector v3 = new Vector();
	private String thRoomname="";
	private String thRoompw="";
	private String thRoomNowp="";
	private String thRoomMaxp="";
	private String thRoomtype="";
	public FastInputThread(SocketChannel sc, ByteBuffer bf, FastinputStart st,FastInputUI ui)
	{
		this.sc = sc;
		this.bf = bf;
		this.st = st;
		this.ui = ui;
		selectUI = true;
	}
	
	public FastInputThread(SocketChannel sc, ByteBuffer bf, FastinputStart st,FastInputWaitUI wui)
	{
		this.sc = sc;
		this.bf = bf;
		this.st = st;
		this.wui = wui;
		selectUI = false;
		try {
			selector = Selector.open();
			sc.register(selector, SelectionKey.OP_READ);
		} catch (IOException e) {
			// TODO 자동 생성된 catch 블록
			e.printStackTrace();
		}
		
	}
	public void setinfo(FastInputUI ui)
	{
		this.ui = ui;
		ui.jbReady.setText("시작하기");
		v = new Vector();
		st.Mynicname = st.Mynicname + "(방장)";
		v.add(st.Mynicname);
		this.ui.jspRTlist.setListData(v);
	}
	@Override
	public void run() {
		// TODO 자동 생성된 메소드 스텁
		
		while(true)
		{
			try {
				String str[];
				selector.select();
				sc.read(bf);
				bf.rewind();
				bf.limit(20);
				switch(st.toString(bf).trim())
				{
				case "memlistup":
					v = new Vector();
					bf.position(20).limit(60);
					if(selectUI)
					{
						ui.jaRBChatView.append(st.toString(bf).trim() + '\n');
						ui.jaRBChatView.setCaretPosition(ui.jaRBChatView.getDocument().getLength());
					}
					else
					{
						wui.jtaCTCChatview.append(st.toString(bf).trim() + '\n');
						wui.jtaCTCChatview.setCaretPosition(wui.jtaCTCChatview.getDocument().getLength());
					}
					bf.position(60).limit(400);
					str = st.toString(bf).trim().split("/");
					for(int i=0;i<str.length;i++)
					{
						v.add(str[i]);
					}
					wui.jlRCLwaitlist.setListData(v);
					wui.jlaRCLWaitinform.setText("대기실 인원 : " + str.length + "명");
					v2 = new Vector();
					bf.position(400).limit(800);
					str = st.toString(bf).trim().split("/");
					for(int i=0;i<str.length;i++)
					{
						if(!str[i].equals("" +
								""))
						v2.add(str[i]);
					}
					wui.jlCBRoomlist.setListData(v2);
					wui.jlaCBRoominform.setText("방 목록 대기방 : "+v2.size());
					break;
				case "memlistdown":
					v = new Vector();
					bf.position(20).limit(60);
					if(selectUI)
					{
						ui.jaRBChatView.append(st.toString(bf).trim() + '\n');
						ui.jaRBChatView.setCaretPosition(ui.jaRBChatView.getDocument().getLength());
					}
					else
					{
						wui.jtaCTCChatview.append(st.toString(bf).trim() + '\n');
						wui.jtaCTCChatview.setCaretPosition(wui.jtaCTCChatview.getDocument().getLength());
					}
					bf.position(60).limit(1000);
					str = st.toString(bf).trim().split("/");
					for(int i=0;i<str.length;i++)
					{
						v.add(str[i]);
					}
					wui.jlRCLwaitlist.setListData(v);
					wui.jlaRCLWaitinform.setText("대기실 인원 : " + str.length + "명");
					break;
				case "serroomtrue":
					v = new Vector();
					bf.position(20).limit(300);
					str = st.toString(bf).trim().split("/");
					for(int i=0;i<str.length;i++)
					{
						v.add(str[i]);
					}
					wui.jlRCLwaitlist.setListData(v);
					wui.jlaRCLWaitinform.setText("대기실 인원 : " + str.length + "명");
					v2 = new Vector();
					bf.position(300).limit(600);
					str = st.toString(bf).trim().split("/");
					for(int i=0;i<str.length;i++)
					{
						v2.add(str[i]);
					}
					wui.jlCBRoomlist.setListData(v2);
					wui.jlaCBRoominform.setText("방 목록 대기방 : "+v2.size());
					break;
				case "roomlistup":
					v = new Vector();
					bf.position(20).limit(300);
					str = st.toString(bf).trim().split("/");
					for(int i=0;i<str.length;i++)
					{
						v.add(str[i]);
					}
					wui.jlRCLwaitlist.setListData(v);
					wui.jlaRCLWaitinform.setText("대기실 인원 : " + str.length + "명");
					v2 = new Vector();
					bf.position(300).limit(600);
					str = st.toString(bf).trim().split("/");
					for(int i=0;i<str.length;i++)
					{
						if(!str[i].isEmpty())
						v2.add(str[i]);
					}
					
					wui.jlCBRoomlist.setListData(v2);
					wui.jlaCBRoominform.setText("방 목록 대기방 : "+v2.size());
					break;
				case "roomjointrue":
					wui.setVisible(false);
					wui.jdreqpw.dispose();
					ui = new FastInputUI(wui);
					ui.jspRTlist.setListData(v);
					ui.setinfo(st);
					st.Mynicname += "(대기중)";
					if(thRoomtype.equals("true"))
					{
						ui.setroom(thRoomname,thRoompw,Integer.parseInt(thRoomMaxp),Integer.parseInt(thRoomNowp),true);
					}
					else
					{
						ui.setroom(thRoomname,thRoompw,Integer.parseInt(thRoomMaxp),Integer.parseInt(thRoomNowp),false);
					}
					selectUI = true;
					break;
				case "roomjoinfalse":
					JOptionPane.showConfirmDialog(wui, "선택하신 방 인원이 최대 인원이거나\n비밀번호가 올바르지 않습니다.",
							"경고", JOptionPane.CLOSED_OPTION,
							JOptionPane.WARNING_MESSAGE);
					break;
				case "fixpeople":
					v = new Vector();
					bf.position(20).limit(300);
					str = st.toString(bf).trim().split("/");
					for(int i=0;i<str.length;i++)
					{
						v.add(str[i]);
					}
					wui.jlRCLwaitlist.setListData(v);
					wui.jlaRCLWaitinform.setText("대기실 인원 : " + str.length + "명");
					v2 = new Vector();
					bf.position(300).limit(600);
					str = st.toString(bf).trim().split("/");
					for(int i=0;i<str.length;i++)
					{
						if(!str[i].isEmpty())
						v2.add(str[i]);
					}
					wui.jlCBRoomlist.setListData(v2);
					wui.jlaCBRoominform.setText("방 목록 대기방 : "+v2.size());
					break;
				case "serroomset":
					bf.position(20).limit(40);
					thRoomname = st.toString(bf).trim();
					bf.position(40).limit(60);
					thRoompw = st.toString(bf).trim();
					bf.position(60).limit(80);
					thRoomNowp = st.toString(bf).trim();
					bf.position(80).limit(100);
					thRoomMaxp = st.toString(bf).trim();
					bf.position(100).limit(120);
					thRoomtype = st.toString(bf).trim();
					v = new Vector();
					bf.position(120).limit(300);
					str = st.toString(bf).trim().split("/");
					for(int i=0;i<str.length;i++)
					{
						v.add(str[i]);
					}
					v3 = v;
					break;
				case "serroomset2":
					bf.position(20).limit(40);
					thRoomname = st.toString(bf).trim();
					bf.position(40).limit(60);
					thRoompw = st.toString(bf).trim();
					bf.position(60).limit(80);
					thRoomNowp = st.toString(bf).trim();
					bf.position(80).limit(100);
					thRoomMaxp = st.toString(bf).trim();
					bf.position(100).limit(120);
					thRoomtype = st.toString(bf).trim();
					v = new Vector();
					bf.position(120).limit(400);
					str = st.toString(bf).trim().split("/");
					for(int i=0;i<str.length;i++)
					{
						v.add(str[i]);
					}
					v3 = v;
					ui.jspRTlist.setListData(v);
					if(thRoomtype.equals("true"))
					{
						ui.setroom(thRoomname,thRoompw,Integer.parseInt(thRoomMaxp),Integer.parseInt(thRoomNowp),true);
					}
					else
					{
						ui.setroom(thRoomname,thRoompw,Integer.parseInt(thRoomMaxp),Integer.parseInt(thRoomNowp),false);
					}
					break;
				case "settingroom":
					String str3="";
					for(int i=0;i<st.Mynicname.length();i++)
					{
						if(st.Mynicname.charAt(i) =='(')
						{
							for(int j=0;j<i;j++)
							{
								str3+= st.Mynicname.charAt(j);
							}
						}
					}
					bf.position(20).limit(400);
					str = st.toString(bf).trim().split("/");
					v = new Vector();
					for(int i=0;i<str.length;i++)
					{
						if(str[0].equals(str3+"(방장)"))
						{
							ui.jbReady.setText("시작하기");
						}
						v.add(str[i]);
					}
					v3 = v;
					ui.jspRTlist.setListData(v);
					thRoomNowp = Integer.toString(v.size());
					if(thRoomtype.equals("true"))
					{
						ui.setroom(thRoomname,thRoompw,Integer.parseInt(thRoomMaxp),Integer.parseInt(thRoomNowp),true);
					}
					else
					{
						ui.setroom(thRoomname,thRoompw,Integer.parseInt(thRoomMaxp),Integer.parseInt(thRoomNowp),false);
					}
					break;
				case "sergameok":
					if(v3.size()<2)
					{
						System.out.println("게임시작안되");
						break;
					}
					System.out.println("게임시작");
					break;
				case "sergameok1":
					System.out.println("준비하기버튼누름");
					break;
				case "sergameok2":
					System.out.println("대기하기버튼누름");
					break;
				case "sergameunok":
					JOptionPane.showConfirmDialog(ui, "아직 전부 준비하지 않았습니다.\n모두 준비한 상태에서 게임시작버튼을 눌러주세요.",
							"경고", JOptionPane.CLOSED_OPTION,
							JOptionPane.WARNING_MESSAGE);
					break;
				default:
					bf.clear();
					if(selectUI)
					{
						ui.jaRBChatView.append(st.toString(bf).trim() + '\n');
						ui.jaRBChatView.setCaretPosition(ui.jaRBChatView.getDocument().getLength());
					}
					else
					{
						wui.jtaCTCChatview.append(st.toString(bf).trim() + '\n');
						wui.jtaCTCChatview.setCaretPosition(wui.jtaCTCChatview.getDocument().getLength());
					}
					break;
				}
				FastinputStart.initbuffer(bf);
			} catch (IOException e) {
				// TODO 자동 생성된 catch 블록
				e.printStackTrace();
			}
			
		}
	}
	
}
