import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

public class FIServerStart {
	private Selector selector = null;
	private ServerSocketChannel ssc = null;
	private ServerSocket ss = null;
	private SocketAddress sa = null;
	private FIServerUI ui;
	private FIServerData data;
	public static boolean roominfo[][] = new boolean[50][4];
	public static int roomcount = 0;
	protected Hashtable<Integer, FIServerRoomInfo> hash;
	private Vector v;
	private Vector v2;
	private Vector v3;
	public FIServerStart(FIServerUI ui)
	{
		this.ui = ui;
		
		try {
			selector = Selector.open();
			ssc = ServerSocketChannel.open();
			ss = ssc.socket();

			sa = new InetSocketAddress(8282);

			ss.bind(sa);
			ssc.configureBlocking(false);
			ssc.register(selector, SelectionKey.OP_ACCEPT);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void StartServer() {
		try {
			hash = new Hashtable<Integer,FIServerRoomInfo>();
			v = new Vector();
			v2 = new Vector();
			v3 = new Vector();
			data = new FIServerData();
			while (true) {
				int num;
				num = selector.select();
				if (num > 0) {
					Set readyKeys = selector.selectedKeys();
					Iterator it = readyKeys.iterator();
					while (it.hasNext()) {
						SelectionKey key = (SelectionKey) it.next();
						it.remove();
						ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
						FIServerThread fsh = new FIServerThread(key, buffer,
								ssc, ss,ui,hash,v,data,v2,v3);
						if (key.isAcceptable()) {
							System.out.println("로그인시도중");
							fsh.start();
							try {
								fsh.sleep(1000);
							} catch (InterruptedException e) {
							}
						} 
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
	}

	public static void initbuffer(ByteBuffer bf)
	{
		bf.clear();
		bf.put(new byte[1024]);
		bf.flip();
		bf.clear();
	}
	public static void main(String args[]) {
		FIServerUI ui = new FIServerUI();
		FIServerStart sts = new FIServerStart(ui);
		sts.StartServer();
	}
}
