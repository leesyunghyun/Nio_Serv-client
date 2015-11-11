import java.io.IOException;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Vector;

class FIServerRoomInfo{
	private String RoomName;
	private String RoomType;
	private String Roompw;
	private int MaxRoomPeople;
	private int NowRoomPeople;
	private String RoomSuper;
	private int Roomindex;
	private String Roomperson="";
	public FIServerRoomInfo(String RoomType, String RoomName, int NowRoomPeople,int MaxRoomPeople, String RoomSuper,int Roomindex,String roompw)
	{
		this.RoomType = RoomType;
		this.RoomName = RoomName;
		this.NowRoomPeople = NowRoomPeople;
		this.MaxRoomPeople = MaxRoomPeople;
		this.RoomSuper = RoomSuper;
		this.Roomindex = Roomindex;
		this.Roompw = roompw;
	}
	public String makeRoom()
	{
		String str = RoomType + "　　　　　　　" + RoomName + "　　　　　　　현재인원 : "+NowRoomPeople+"／" + MaxRoomPeople;
		return str;
	}
	public String getRoomName()
	{
		return this.RoomName;
	}
	public int getNowRoomPeople()
	{
		return this.NowRoomPeople;
	}
	public int getRoomindex()
	{
		return this.Roomindex;
	}
	public int getMaxRoomPeople()
	{
		return this.MaxRoomPeople;
	}
	public void setNowRoomPeople(int NowRoomPeople)
	{
		this.NowRoomPeople = NowRoomPeople;
	}
	public String getRoomType()
	{
		return this.RoomType;
	}
	public String getRoompw()
	{
		return this.Roompw;
	}
	public void setRoomperson(String person,boolean pm)
	{
		if(pm)
		{
			this.Roomperson += person+"/";
		}
		else
		{
			String str2[];
			str2 = this.Roomperson.split("/");
			this.Roomperson ="";
			for(int i=0;i<str2.length;i++)
			{
				if(str2[i].equals(person))
				{
					if(i == 0)
					{
						String str3="";
						for(int j=0;j<str2[i+1].length();j++)
							{
								if(str2[i+1].charAt(j) == '(')
								{
									for(int k=0;k<j;k++)
									{
										str3 += str2[i+1].charAt(k);
									}
								}
							}
						str2[i+1] = str3 + "(방장)";
					}
					continue;
				}
				this.Roomperson += str2[i] + "/";
			}
		}
		
	}
	public String getRoomperson()
	{
		return this.Roomperson;
	}
	
}

public class FIServerInfo {

	protected String id,ip;
	protected String nicname;
	protected String Roomname="";
	protected String Roomstate="";
	protected int RoominNum; //방 안에서의 순서
	protected ServerSocket ss;
	protected SocketChannel sc;
	protected SelectionKey key;
	protected ServerSocketChannel ssc;
	protected ByteBuffer bf;
	protected Vector v;
	private FIServerData data;
	protected int ChannelNumber = 0;
	public FIServerInfo(int Channelnumber,String id,String ip,ServerSocket ss, ServerSocketChannel ssc,ByteBuffer bf,SocketChannel sc,SelectionKey key,Vector v, FIServerData data)
	{
		this.id = id;
		this.ip = ip;
		this.ss = ss;
		this.ssc = ssc;
		this.bf = bf;
		this.sc = sc;
		this.key = key;
		this.v = v;
		this.data = data;
		this.ChannelNumber = Channelnumber;
	}

	public void write(ByteBuffer bf)
	{
		this.bf = bf;
		this.bf.rewind();
		try {
			sc.write(this.bf);
		} catch (IOException e) {
		}
	}
	public void setchannel(int ChannelNumber)
	{
		this.ChannelNumber = ChannelNumber;
	}
	public void setRoom(String roomname)
	{
		this.Roomname = roomname;
	}
	public String getRoom()
	{
		return this.Roomname;	
	}
	public void setRoominNum(int RoominNum)
	{
		this.RoominNum = RoominNum;
	}
	public void setRoomState(String state)
	{
		this.Roomstate = state;
	}
	public String getRoomState()
	{
		return this.Roomstate;
	}
	public int getRoominNum()
	{
		return RoominNum;
	}
	public int getchannel()
	{
		return this.ChannelNumber;
	}
	public String getnicname()
	{
		nicname = data.getnicname(id);
		return nicname;
	}
}
