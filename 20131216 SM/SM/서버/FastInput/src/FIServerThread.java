import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Hashtable;
import java.util.Vector;

import javax.naming.LimitExceededException;

public class FIServerThread extends Thread {

	public static final int WAIT_ROOM = 1001;
	public static final int PLAY_ROOM = 1002;
	private String id, password, name, ip, nicname, birthday;
	private String roomname, roompw, roompeople;
	private String infoid; // 인포에 값 넘겨줘서 닉네임 찾기 위해 필요한 변수
	private String foundid, foundpw;
	private ServerSocket ss;
	private ServerSocketChannel ssc;
	private SelectionKey key;
	private ByteBuffer bf;
	private Hashtable<Integer, FIServerRoomInfo> hash;
	private FIServerUI ui;
	private FIServerInfo info;
	private FIServerData data;
	private Integer Numcount;
	private Vector v; // 총 회원수
	private Vector v2; // 클라이언트에 보낼 대기방회원수(닉네임)
	private Vector v3;
	private SocketChannel incomingChannel;

	public FIServerThread(SelectionKey key, ByteBuffer bf,
			ServerSocketChannel ssc, ServerSocket ss, FIServerUI ui,
			Hashtable<Integer, FIServerRoomInfo> hash, Vector v,
			FIServerData data, Vector v2, Vector v3) {
		this.key = key;
		this.bf = bf;
		this.ssc = ssc;
		this.ss = ss;
		this.ui = ui;
		this.hash = hash;
		this.v = v;
		this.v2 = v2;
		this.v3 = v3;
		this.data = data;
	}

	@Override
	public void run() {
		// TODO 자동 생성된 메소드 스텁
		try {
			Numcount = v.size();
			ServerSocketChannel readyChannel = (ServerSocketChannel) key
					.channel();
			incomingChannel = readyChannel.accept();
			ui.setdata(data);
			while (true) {
				read(incomingChannel, key, bf, ui);
			}
		} catch (IOException e) {
		}
	}

	public void write(ByteBuffer buffer, SocketChannel sc, SelectionKey key,
			String check, int Channelnumber) {

		if (check.equals("Logintrue")) {
			String str = "serlogintrue";
			String str2 = info.getnicname();
			try {
				buffer.put(toByteBuffer(str));
				buffer.position(20);
				buffer.put(toByteBuffer(str2));
				buffer.flip();
				sc.write(buffer);
				buffer.clear();
			} catch (IOException e) {
				// TODO 자동 생성된 catch 블록
				e.printStackTrace();
			}
		} else if (check.equals("Loginfalse")) {
			String str = "serloginfalse";

			try {
				buffer.put(toByteBuffer(str));
				buffer.flip();
				sc.write(buffer);
				buffer.clear();
			} catch (IOException e) {
				// TODO 자동 생성된 catch 블록
				e.printStackTrace();
			}
		} else if (check.equals("Maketrue")) {
			String str = "sermaketrue";
		} else if (check.equals("idtrue")) {
			String str = "seridtrue";
			try {
				buffer.put(toByteBuffer(str));
				buffer.flip();
				sc.write(buffer);
				buffer.clear();
			} catch (IOException e) {
				// TODO 자동 생성된 catch 블록
				e.printStackTrace();
			}
		} else if (check.equals("idfalse")) {
			String str = "seridfalse";
			try {
				buffer.put(toByteBuffer(str));
				buffer.flip();
				sc.write(buffer);
				buffer.clear();
			} catch (IOException e) {
				// TODO 자동 생성된 catch 블록
				e.printStackTrace();
			}
		} else if (check.equals("nictrue")) {
			String str = "sernictrue";
			try {
				buffer.put(toByteBuffer(str));
				buffer.flip();
				sc.write(buffer);
				buffer.clear();
			} catch (IOException e) {
				// TODO 자동 생성된 catch 블록
				e.printStackTrace();
			}
		} else if (check.equals("nicfalse")) {
			String str = "sernicfalse";
			try {
				buffer.put(toByteBuffer(str));
				buffer.flip();
				sc.write(buffer);
				buffer.clear();
			} catch (IOException e) {
				// TODO 자동 생성된 catch 블록
				e.printStackTrace();
			}
		} else if (check.equals("findidtrue")) {
			String str = foundid;
			try {
				buffer.put(toByteBuffer(str));
				buffer.flip();
				sc.write(buffer);
				buffer.clear();
			} catch (IOException e) {
				// TODO 자동 생성된 catch 블록
				e.printStackTrace();
			}
		} else if (check.equals("findidfalse")) {
			String str = "serfindidfalse";
			try {
				buffer.put(toByteBuffer(str));
				buffer.flip();
				sc.write(buffer);
				buffer.clear();
			} catch (IOException e) {
				// TODO 자동 생성된 catch 블록
				e.printStackTrace();
			}
		} else if (check.equals("findpwtrue")) {
			String str = foundpw;
			try {
				buffer.put(toByteBuffer(str));
				buffer.flip();
				sc.write(buffer);
				buffer.clear();
			} catch (IOException e) {
				// TODO 자동 생성된 catch 블록
				e.printStackTrace();
			}
		} else if (check.equals("findpwfalse")) {
			String str = "serfindpwfalse";
			try {
				buffer.put(toByteBuffer(str));
				buffer.flip();
				sc.write(buffer);
				buffer.clear();
			} catch (IOException e) {
				// TODO 자동 생성된 catch 블록
				e.printStackTrace();
			}
		} else if (check.equals("Hello")) {
			String str;
			str = "memlistup";
			try {
				int channel = Channelnumber;
				buffer.put(toByteBuffer(str));
				str = " ## " + info.getnicname() + " ## 님께서 접속하셨습니다!";
				ui.jtaRTCWaitChatArea.append(str + '\n');
				ui.jtaRTCWaitChatArea.setCaretPosition(ui.jtaRTCWaitChatArea
						.getDocument().getLength());

				buffer.position(20);
				buffer.put(toByteBuffer(str));
				str = "";
				for (int i = 0; i < v2.size(); i++) {
					str += v2.get(i) + "/";
				}
				buffer.position(60);
				buffer.put(toByteBuffer(str));
				v3 = ui.getV3();
				str = "";
				for (int i = 0; i < v3.size(); i++) {
					str += v3.get(i) + "/";
				}
				buffer.position(400);
				buffer.put(toByteBuffer(str));
				for (int i = 0; i < v.size(); i++) {
					FIServerInfo info2 = (FIServerInfo) v.get(i);
					if (info2.getchannel() == channel) {
						buffer.flip();
						info2.write(buffer);
					}
				}
			} catch (Exception e) {
				// TODO 자동 생성된 catch 블록
				e.printStackTrace();
			}
			buffer.clear();
		} else if (check.equals("byebye")) {
			String str;
			str = "memlistdown";
			try {
				data.setBye(info.getnicname());
				int channel = Channelnumber;
				buffer.put(toByteBuffer(str));
				str = " ## " + info.getnicname() + " ## 님께서 나가셨습니다!";
				ui.jtaRTCWaitChatArea.append(str + '\n');
				ui.jtaRTCWaitChatArea.setCaretPosition(ui.jtaRTCWaitChatArea
						.getDocument().getLength());
				sc.close();
				v.remove(info);
				v2.remove(info.getnicname());
				ui.Setoption(info, v, 1, WAIT_ROOM);

				buffer.position(20);
				buffer.put(toByteBuffer(str));
				str = "";
				for (int i = 0; i < v2.size(); i++) {
					str += v2.get(i) + "/";
				}
				buffer.position(60);
				buffer.put(toByteBuffer(str));
				for (int i = 0; i < v.size(); i++) {
					FIServerInfo info2 = (FIServerInfo) v.get(i);
					if(channel == info2.getchannel())
					{
						buffer.flip();
						info2.write(buffer);
					}
				}
			} catch (Exception e) {
				// TODO 자동 생성된 catch 블록
				e.printStackTrace();
			}
			buffer.clear();
		} else if (check.equals("roombye")) {
			String str = " ## " + info.getnicname() + " ## 님께서 방에서 퇴장하셨습니다!";
			try {
				buffer.put(toByteBuffer(str));
				for (int i = 0; i < v.size(); i++) {
					FIServerInfo info2 = (FIServerInfo) v.get(i);
					if(info2.getchannel() == PLAY_ROOM)
					{
						buffer.flip();
						info2.write(buffer);
					}
				}
				FIServerStart.initbuffer(buffer);
				FIServerThread.sleep(300);
				str = "roomlistup";
				buffer.put(toByteBuffer(str));
				v2.add(info.getnicname());
				info.setchannel(WAIT_ROOM);
				str = "";
				for (int i = 0; i < v2.size(); i++) {
					str += v2.get(i) + "/";
				}
				buffer.position(20);
				buffer.put(toByteBuffer(str));
				v3 = ui.SetRoom(info, v, hash, false, null, null, 0);
				str = "";
				for (int i = 0; i < v3.size(); i++) {
					str += v3.get(i) + "/";
				}
				buffer.position(300);
				buffer.put(toByteBuffer(str));
				for (int i = 0; i < v.size(); i++) {
					buffer.flip();
					FIServerInfo info2 = (FIServerInfo) v.get(i);
					info2.write(buffer);
				}
			} catch (Exception e) {
				// TODO 자동 생성된 catch 블록
				e.printStackTrace();
			}
			ui.Setoption(info, v, 2, WAIT_ROOM);
			buffer.clear();
		} else if (check.equals("makeroomuse")) {
			String str = "serroomtrue";
			try {
				buffer.put(toByteBuffer(str));
				info.setchannel(PLAY_ROOM);
				v2.remove(info.getnicname());
				str = "";
				for (int i = 0; i < v2.size(); i++) {
					str += v2.get(i) + "/";
				}
				buffer.position(20);
				buffer.put(toByteBuffer(str));
				v3 = ui.SetRoom(info, v, hash, true, roomname, roompw,
						Integer.parseInt(roompeople));
				str = "";
				for (int i = 0; i < v3.size(); i++) {
					str += v3.get(i) + "/";
				}
				buffer.position(300);
				buffer.put(toByteBuffer(str));
				for (int i = 0; i < v.size(); i++) {
					buffer.flip();
					FIServerInfo info2 = (FIServerInfo) v.get(i);
					info2.write(buffer);
				}
				ui.Setoption(info, v, 2, PLAY_ROOM);
			} catch (IOException e) {
				// TODO 자동 생성된 catch 블록
				e.printStackTrace();
			}

			buffer.clear();
		} else if (check.equals("makeroomunuse")) {
			String str = "serroomtrue";
			try {
				buffer.put(toByteBuffer(str));
				info.setchannel(PLAY_ROOM);
				v2.remove(info.getnicname());
				str = "";
				for (int i = 0; i < v2.size(); i++) {
					str += v2.get(i) + "/";
				}
				buffer.position(20);
				buffer.put(toByteBuffer(str));
				v3 = ui.SetRoom(info, v, hash, true, roomname, roompw,
						Integer.parseInt(roompeople));
				str = "";
				for (int i = 0; i < v3.size(); i++) {
					str += v3.get(i) + "/";
				}
				buffer.position(300);
				buffer.put(toByteBuffer(str));
				for (int i = 0; i < v.size(); i++) {
					buffer.flip();
					FIServerInfo info2 = (FIServerInfo) v.get(i);
					info2.write(buffer);
				}
				ui.Setoption(info, v, 2, PLAY_ROOM);

			} catch (IOException e) {
				// TODO 자동 생성된 catch 블록
				e.printStackTrace();
			}
			buffer.clear();
		} else if (check.equals("joinroom")) {
			String str;
			try {
				if (ui.JoinRoom(info, v, roomname, roompw,buffer)) {
					str = "roomjointrue";
					buffer.put(toByteBuffer(str));
					buffer.flip();
					sc.write(buffer);
					FIServerStart.initbuffer(buffer);
					str = "fixpeople";
					buffer.put(toByteBuffer(str));
					info.setchannel(PLAY_ROOM);
					v2.remove(info.getnicname());
					str = "";
					for (int i = 0; i < v2.size(); i++) {
						str += v2.get(i) + "/";
					}
					buffer.position(20);
					buffer.put(toByteBuffer(str));
					v3 = ui.getV3();
					str = "";
					for (int i = 0; i < v3.size(); i++) {
						str += v3.get(i) + "/";
					}
					buffer.position(300);
					buffer.put(toByteBuffer(str));
					for (int i = 0; i < v.size(); i++) {
						buffer.flip();
						FIServerInfo info2 = (FIServerInfo) v.get(i);
						info2.write(buffer);
					}
					FIServerThread.sleep(300);
					FIServerStart.initbuffer(buffer);
					str = " ## " + info.getnicname() + " ## 님께서 방에 입장하셨습니다!";
					buffer.put(toByteBuffer(str));
					for (int i = 0; i < v.size(); i++) {
						FIServerInfo info2 = (FIServerInfo) v.get(i);
						if (info2.getchannel() == PLAY_ROOM) {
							buffer.flip();
							info2.write(buffer);
						}
					}
					ui.Setoption(info, v, 2, PLAY_ROOM);
				}
				else {
					str = "roomjoinfalse";
					buffer.put(toByteBuffer(str));
					buffer.flip();
					sc.write(buffer);
				}
			} catch (Exception e) {
				// TODO 자동 생성된 catch 블록
				e.printStackTrace();
			}
			buffer.clear();
		} else if (check.equals("pwjoinroom")) {
			String str;
			try {
				if (ui.JoinRoom(info, v, roomname, roompw,buffer)) {
					int channel = Channelnumber;
					str = "roomjointrue";
					buffer.put(toByteBuffer(str));
					buffer.flip();
					sc.write(buffer);
					FIServerStart.initbuffer(buffer);
					str = "fixpeople";
					buffer.put(toByteBuffer(str));
					info.setchannel(PLAY_ROOM);
					v2.remove(info.getnicname());
					str = "";
					for (int i = 0; i < v2.size(); i++) {
						str += v2.get(i) + "/";
					}
					buffer.position(20);
					buffer.put(toByteBuffer(str));
					v3 = ui.getV3();
					str = "";
					for (int i = 0; i < v3.size(); i++) {
						str += v3.get(i) + "/";
					}
					buffer.position(300);
					buffer.put(toByteBuffer(str));
					for (int i = 0; i < v.size(); i++) {
						buffer.flip();
						FIServerInfo info2 = (FIServerInfo) v.get(i);
						info2.write(buffer);
					}
					ui.Setoption(info, v, 2, PLAY_ROOM);
					FIServerStart.initbuffer(buffer);
					str = " ## " + info.getnicname() + " ## 님께서 방에 입장하셨습니다!";
					buffer.put(toByteBuffer(str));
					for (int i = 0; i < v.size(); i++) {
						FIServerInfo info2 = (FIServerInfo) v.get(i);
						if (info2.getchannel() == PLAY_ROOM) {
							buffer.flip();
							info2.write(buffer);
						}
					}
					
				}
				else {
					str = "roomjoinfalse";
					buffer.put(toByteBuffer(str));
					buffer.flip();
					sc.write(buffer);
				}
			} catch (Exception e) {
				// TODO 자동 생성된 catch 블록
				e.printStackTrace();
			}
			buffer.clear();
		} else {
			int channel = Channelnumber;
			for (int i = 0; i < v.size(); i++) {
				FIServerInfo info2 = (FIServerInfo) v.get(i);
				if (info2.getchannel() == channel) {
					buffer.flip();
					info2.write(buffer);
				}
			}
			/*
			 * for(int i=0;i<hash.size();i++) { buffer.flip(); FIServerInfo
			 * info2 = (FIServerInfo) hash.get(i); info2.write(buffer); }
			 */
			try {
				buffer.clear();
				ui.jtaRTCWaitChatArea.append(toString(buffer).trim() + '\n');
				ui.jtaRTCWaitChatArea.setCaretPosition(ui.jtaRTCWaitChatArea
						.getDocument().getLength());
			} catch (UnsupportedEncodingException e) {
				// TODO 자동 생성된 catch 블록
				e.printStackTrace();
			}
			buffer.clear();
		}

	}

	public void read(SocketChannel sc, SelectionKey key, ByteBuffer bf,
			FIServerUI ui) {
		try {
			sc.read(bf);
			bf.rewind();
			bf.limit(20);
			switch (toString(bf).trim()) {
			case "logincheck":
				bf.clear();
				bf.position(30).limit(60);
				id = toString(bf).trim();
				bf.position(60).limit(90);
				password = toString(bf).trim();
				FIServerStart.initbuffer(bf);
				if (data.LoginCheck(id, password)) {
					infoid = id;
					info = new FIServerInfo(WAIT_ROOM, infoid, this.ip,
							this.ss, this.ssc, this.bf, incomingChannel,
							this.key, this.v, this.data);
					write(bf, sc, key, "Logintrue", info.getchannel());
					v.add(info);
					v2.add(info.getnicname());
					ui.Setoption(info, v, 0, WAIT_ROOM);
					Numcount++;
					System.out.println("클라이언트접속함 " + Numcount + " 명");
					FIServerStart.initbuffer(bf);
					write(bf, sc, key, "Hello", info.getchannel());
				} else {
					write(bf, sc, key, "Loginfalse", 0);
				}
				FIServerStart.initbuffer(bf);
				break;
			case "bye":
				FIServerStart.initbuffer(bf);
				write(bf, sc, key, "byebye", info.getchannel());
				FIServerStart.initbuffer(bf);
				break;
			case "roombye":
				bf.clear();
				bf.position(20).limit(100);
				roomname = toString(bf).trim();
				FIServerStart.initbuffer(bf);
				ui.Roominset(roomname,bf);
				FIServerStart.initbuffer(bf);
				write(bf, sc, key, "roombye", info.getchannel());
				FIServerStart.initbuffer(bf);
				break;
			case "makemember":
				bf.clear();
				bf.position(20).limit(40);
				id = toString(bf).trim();
				bf.position(40).limit(60);
				password = toString(bf).trim();
				bf.position(60).limit(80);
				name = toString(bf).trim();
				bf.position(80).limit(100);
				nicname = toString(bf).trim();
				bf.position(100).limit(120);
				birthday = toString(bf).trim();
				ip = sc.socket().getInetAddress().getHostAddress().toString();
				data.InsertMember(id, password, name, ip, birthday, nicname);
				FIServerStart.initbuffer(bf);
				write(bf, sc, key, "Maketrue", 0);
				FIServerStart.initbuffer(bf);
				break;
			case "idcheck":
				bf.clear();
				bf.position(20).limit(40);
				id = toString(bf).trim();
				FIServerStart.initbuffer(bf);
				if (data.IDCheck(id)) {
					bf.clear();
					write(bf, sc, key, "idtrue", 0);
				} else {
					bf.clear();
					write(bf, sc, key, "idfalse", 0);
				}
				FIServerStart.initbuffer(bf);
				break;
			case "niccheck":
				bf.clear();
				bf.position(20).limit(40);
				nicname = toString(bf).trim();
				FIServerStart.initbuffer(bf);
				if (data.NICCheck(nicname)) {
					bf.clear();
					write(bf, sc, key, "nictrue", 0);
				} else {
					bf.clear();
					write(bf, sc, key, "nicfalse", 0);
				}
				FIServerStart.initbuffer(bf);
				break;
			case "findid":
				bf.clear();
				bf.position(20).limit(40);
				nicname = toString(bf).trim();
				bf.position(40).limit(60);
				password = toString(bf).trim();
				FIServerStart.initbuffer(bf);
				foundid = data.FindID(nicname, password);
				if (foundid != "falsefalse") {
					write(bf, sc, key, "findidtrue", 0);

				} else {
					write(bf, sc, key, "findidfalse", 0);
				}
				FIServerStart.initbuffer(bf);
				break;
			case "findpw":
				bf.clear();
				bf.position(20).limit(40);
				id = toString(bf).trim();
				bf.position(40).limit(60);
				name = toString(bf).trim();
				bf.position(60).limit(80);
				birthday = toString(bf).trim();
				FIServerStart.initbuffer(bf);
				foundpw = data.FindPW(id, name, birthday);
				if (foundpw != "falsefalse") {
					write(bf, sc, key, "findpwtrue", 0);
				} else {
					write(bf, sc, key, "findpwfalse", 0);
				}
				FIServerStart.initbuffer(bf);
				break;
			case "loginbye":
				FIServerStart.initbuffer(bf);
				sc.close();
				break;
			case "makeroomuse":
				bf.clear();
				bf.position(20).limit(40);
				roomname = toString(bf).trim();
				bf.position(40).limit(60);
				roompw = toString(bf).trim();
				bf.position(60).limit(80);
				roompeople = toString(bf).trim();
				FIServerStart.initbuffer(bf);
				write(bf, sc, key, "makeroomuse", info.getchannel());
				FIServerStart.initbuffer(bf);
				break;
			case "makeroomunuse":
				bf.clear();
				bf.position(20).limit(40);
				roomname = toString(bf).trim();
				bf.position(40).limit(60);
				roompeople = toString(bf).trim();
				roompw = "";
				FIServerStart.initbuffer(bf);
				write(bf, sc, key, "makeroomunuse", info.getchannel());
				FIServerStart.initbuffer(bf);
				break;
			case "joinroom":
				bf.clear();
				bf.position(20).limit(80);
				roomname = toString(bf).trim();
				FIServerStart.initbuffer(bf);
				write(bf, sc, key, "joinroom", info.getchannel());
				FIServerStart.initbuffer(bf);
				break;
			case "pwjoinroom":
				bf.clear();
				bf.position(20).limit(80);
				roomname = toString(bf).trim();
				bf.position(80).limit(100);
				roompw = toString(bf).trim();
				FIServerStart.initbuffer(bf);
				write(bf, sc, key, "pwjoinroom", info.getchannel());
				FIServerStart.initbuffer(bf);
				break;
			case "memberready":
				bf.clear();
				if(ui.Gamesetting("memberready",info,v))
				{
					FIServerStart.initbuffer(bf);
					bf.put(toByteBuffer("sergameok1"));
					bf.flip();
					this.info.write(bf);
					bf.clear();
					FIServerStart.initbuffer(bf);
				}
				break;
			case "memberunready":
				bf.clear();
				if(ui.Gamesetting("memberunready",info,v))
				{
					FIServerStart.initbuffer(bf);
					bf.put(toByteBuffer("sergameok2"));
					bf.flip();
					this.info.write(bf);
					bf.clear();
					FIServerStart.initbuffer(bf);
				}
				break;
			case "memberstart":
				bf.clear();
				if(ui.Gamesetting("memberstart",info,v))
				{
					FIServerStart.initbuffer(bf);
					bf.put(toByteBuffer("sergameok"));
					write(bf,sc,key,"",info.getchannel());
					FIServerStart.initbuffer(bf);
				}
				else
				{
					FIServerStart.initbuffer(bf);
					bf.put(toByteBuffer("sergameunok"));
					bf.flip();
					this.info.write(bf);
					bf.clear();
					FIServerStart.initbuffer(bf);
				}
				break;
			default:
				write(bf, sc, key, "", info.getchannel());
				FIServerStart.initbuffer(bf);
				break;
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
		}

	}

	private static String toString(ByteBuffer buffer)
			throws UnsupportedEncodingException {
		byte[] bytes = new byte[buffer.remaining()];
		buffer.get(bytes);
		return new String(bytes, "EUC-KR");
	}

	public static ByteBuffer toByteBuffer(String value)
			throws UnsupportedEncodingException {
		return ByteBuffer.wrap(value.getBytes("EUC-KR"));
	}

}
