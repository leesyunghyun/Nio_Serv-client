import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.rmi.server.UID;

public class FastinputStart {

	protected SocketChannel channel;
	protected Selector selector;
	protected FastInputJoinUI jui;
	protected ByteBuffer buffer;
	protected FastInputWaitUI wui;
	protected String Mynicname;
	protected FastInputUI ui;
	public static String stroomname;
	public static String stroompw;
	public static int stroompeople;
	public FastinputStart(FastInputJoinUI jui) {
		try {
			this.jui = jui;
			channel = SocketChannel.open(new InetSocketAddress("localhost",
					8282));
			selector = Selector.open();
			channel.configureBlocking(false);
			buffer = ByteBuffer.allocateDirect(1024);
			buffer.clear();

		} catch (IOException e) {
			// TODO 자동 생성된 catch 블록
			e.printStackTrace();
		}
	}

	public static void initbuffer(ByteBuffer bf) {
		bf.clear();
		bf.put(new byte[1024]);
		bf.flip();
		bf.clear();
	}

	public FastInputWaitUI setinfo(FastInputWaitUI wui) {
		this.wui = wui;
		return this.wui;
	}

	public boolean OX(String id, String pw) // 아이디 비밀번호 체크함수
	{
		try {
			String str;
			str = "logincheck";
			initbuffer(buffer);
			buffer.put(toByteBuffer(str));
			buffer.position(30);
			buffer.put(toByteBuffer(id));
			buffer.position(60);
			buffer.put(toByteBuffer(pw));
			buffer.flip();
			channel.write(buffer);
			initbuffer(buffer);
			channel.register(selector, SelectionKey.OP_READ);
			selector.select();
			channel.read(buffer);
			buffer.rewind();
			buffer.limit(20);
			if (toString(buffer).trim().equals("serlogintrue")) {
				buffer.position(20).limit(40);
				Mynicname = toString(buffer).trim();
				initbuffer(buffer);
				return true;
			} else {
				initbuffer(buffer);
				return false;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public String findid(String nicname, String pw) {
		try {
			String str;
			String id = "false";
			str = "findid";
			buffer.put(toByteBuffer(str));
			buffer.position(20);
			buffer.put(toByteBuffer(nicname));
			buffer.position(40);
			buffer.put(toByteBuffer(pw));
			buffer.flip();
			channel.write(buffer);
			initbuffer(buffer);
			channel.register(selector, SelectionKey.OP_READ);
			selector.select();
			channel.read(buffer);
			buffer.rewind();
			if (toString(buffer).trim().equals("serfindidfalse")) {
				initbuffer(buffer);
				id = "false";
				return id;
			} else {
				buffer.rewind();
				id = toString(buffer).trim();
				initbuffer(buffer);
				return id;
			}
		} catch (Exception e) {
			// TODO 자동 생성된 catch 블록

		}
		return null;
	}

	public String findpw(String id, String name, String birthday) {
		try {
			String str;
			String pw = "false";
			str = "findpw";
			buffer.put(toByteBuffer(str));
			buffer.position(20);
			buffer.put(toByteBuffer(id));
			buffer.position(40);
			buffer.put(toByteBuffer(name));
			buffer.position(60);
			buffer.put(toByteBuffer(birthday));
			buffer.flip();
			channel.write(buffer);
			initbuffer(buffer);
			channel.register(selector, SelectionKey.OP_READ);
			selector.select();
			channel.read(buffer);
			buffer.rewind();
			
			if (toString(buffer).trim().equals("serfindpwfalse")) {
				initbuffer(buffer);
				pw = "false";
				return pw;
			} else {
				buffer.rewind();
				pw = toString(buffer).trim();
				initbuffer(buffer);
				return pw;
			}
		} catch (Exception e) {
			// TODO 자동 생성된 catch 블록

		}
		return null;
	}

	public void Makemember(String id, String pw, String name, String nicname,
			String birthday) {

		try {
			String str, str2;
			str = "makemember";
			// str2 =
			// channel.socket().getInetAddress().getHostAddress().toString();
			initbuffer(buffer);
			buffer.put(toByteBuffer(str));
			buffer.position(20);
			buffer.put(toByteBuffer(id));
			buffer.position(40);
			buffer.put(toByteBuffer(pw));
			buffer.position(60);
			buffer.put(toByteBuffer(name));
			buffer.position(80);
			buffer.put(toByteBuffer(nicname));
			buffer.position(100);
			buffer.put(toByteBuffer(birthday));
			// buffer.position(120);
			// buffer.put(toByteBuffer(str2));
			buffer.flip();
			channel.write(buffer);
			initbuffer(buffer);
		} catch (Exception e) {
			// TODO 자동 생성된 catch 블록
			e.printStackTrace();
		}

	}

	public boolean IDCheck(String id) {
		try {
			String str;
			str = "idcheck";
			buffer.put(toByteBuffer(str));
			buffer.position(20);
			buffer.put(toByteBuffer(id));
			buffer.flip();
			channel.write(buffer);
			initbuffer(buffer);
			channel.register(selector, SelectionKey.OP_READ);
			selector.select();
			channel.read(buffer);
			buffer.rewind();
			if (toString(buffer).trim().equals("seridtrue")) {
				initbuffer(buffer);
				return true;
			} else {
				initbuffer(buffer);
				return false;
			}
		} catch (Exception e) {
			// TODO 자동 생성된 catch 블록
			e.printStackTrace();
		}
		return false;
	}

	public boolean NicCheck(String nic) {
		try {
			String str;
			str = "niccheck";
			buffer.put(toByteBuffer(str));
			buffer.position(20);
			buffer.put(toByteBuffer(nic));
			buffer.flip();
			channel.write(buffer);
			initbuffer(buffer);
			channel.register(selector, SelectionKey.OP_READ);
			selector.select();
			channel.read(buffer);
			buffer.rewind();
			if (toString(buffer).trim().equals("sernictrue")) {
				initbuffer(buffer);
				return true;
			} else {
				initbuffer(buffer);
				return false;
			}
		} catch (Exception e) {
			// TODO 자동 생성된 catch 블록
			e.printStackTrace();
		}
		return false;
	}
	public void Joinroom(String roomname,String roompw)
	{
		String str="";
		char a = roomname.charAt(0);
		if(a == '공')
		{
			str = "joinroom";
			initbuffer(buffer);
			try {
					buffer.put(toByteBuffer(str));
					buffer.position(20);
					buffer.put(toByteBuffer(roomname));
					buffer.flip();
					channel.write(buffer);
					stroomname = roomname;
			} catch (Exception e) {
				// TODO 자동 생성된 catch 블록
				e.printStackTrace();
			}
		}
		else if(a =='비')
		{
			str = "pwjoinroom";
			initbuffer(buffer);
			try {
					buffer.put(toByteBuffer(str));
					buffer.position(20);
					buffer.put(toByteBuffer(roomname));
					buffer.position(80);
					buffer.put(toByteBuffer(roompw));
					buffer.flip();
					channel.write(buffer);
					stroomname = roomname;
					stroompw = roompw;
			} catch (Exception e) {
				// TODO 자동 생성된 catch 블록
				e.printStackTrace();
			}
		}
		
		initbuffer(buffer);
	}
	public boolean Makeroom(String roomname,String pw,int limitpeople,boolean passwd)
	{
		String str;
		str = "makeroom";
		initbuffer(buffer);
		try {
			if(passwd)
			{
				buffer.put(toByteBuffer(str+"use"));
				buffer.position(20);
				buffer.put(toByteBuffer(roomname));
				buffer.position(40);
				buffer.put(toByteBuffer(pw));
				buffer.position(60);
				buffer.put(toByteBuffer(Integer.toString(limitpeople)));
				buffer.flip();
				channel.write(buffer);
			}
			else
			{
				buffer.put(toByteBuffer(str+"unuse"));
				buffer.position(20);
				buffer.put(toByteBuffer(roomname));
				buffer.position(40);
				buffer.put(toByteBuffer(Integer.toString(limitpeople)));
				buffer.flip();
				channel.write(buffer);
			}
			initbuffer(buffer);
			channel.register(selector, SelectionKey.OP_READ);
			selector.select();
			channel.read(buffer);
			buffer.rewind();
			buffer.limit(20);
			if(toString(buffer).trim().equals("serroomtrue"))
			{
				stroomname = roomname;
				stroompw = pw;
				stroompeople = limitpeople;
				FastInputThread.selectUI=true;
				return true;
			}
			else{			
				return false;
			}
		} catch (Exception e) {
			// TODO 자동 생성된 catch 블록
			e.printStackTrace();
		}
		return false;
	}
	public void roombye(String nicname)
	{
		String str = "roombye";
		initbuffer(buffer);
		try {
			buffer.put(toByteBuffer(str));
			buffer.position(20);
			buffer.put(toByteBuffer(nicname));
			buffer.flip();
			channel.write(buffer);
			buffer.clear();
		} catch (Exception e) {
			// TODO 자동 생성된 catch 블록
			e.printStackTrace();
		}
		
	}
	public void send(String str) {
		try {
			initbuffer(buffer);
			buffer.put(toByteBuffer(str));
			buffer.flip();
			channel.register(selector, SelectionKey.OP_WRITE);
			channel.write(buffer);
			buffer.clear();
			initbuffer(buffer);
		} catch (IOException e) {
			// TODO 자동 생성된 catch 블록
			e.printStackTrace();
		}
	}

	public static String toString(ByteBuffer buffer)
			throws UnsupportedEncodingException {
		byte[] bytes = new byte[buffer.remaining()];
		buffer.get(bytes);
		return new String(bytes, "EUC-KR");
	}

	public static ByteBuffer toByteBuffer(String value)
			throws UnsupportedEncodingException {
		return ByteBuffer.wrap(value.getBytes("EUC-KR"));

	}

	public static void main(String args[]) {
		FastInputJoinUI jui = new FastInputJoinUI();
		FastinputStart st = new FastinputStart(jui);
		jui.setinfo(st);
	}
}
