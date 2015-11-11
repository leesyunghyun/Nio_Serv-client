import javax.print.attribute.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;


public class FastInputDocument extends PlainDocument{
	
	private int limit;
	private boolean toUppercase = false;
	int type;
	String msg;
	
	public FastInputDocument(int limit)
	{
		super();
		this.limit = limit;
	}
	public FastInputDocument(int limit, String msg, int type)
	{
		super();
		this.limit = limit;
		this.type = type;
		this.msg = msg;
	}

	public FastInputDocument(int limit, boolean upper)
	{
		super();
		this.limit = limit;
		this.toUppercase = upper;
	}
	
	@Override
	public void insertString(int offs, String str,
			javax.swing.text.AttributeSet a) throws BadLocationException {
		// TODO 자동 생성된 메소드 스텁
		if(str == null){
			return ;
		}
		
		if(type == 1)
		{
			if(str.charAt(0) >= '0' && str.charAt(0) <= '9'){
				if((getLength() + str.length()) <= limit) {
					super.insertString(offs, str, a);
				}
			}
		}
		else if(type == 2)
		{
			if(str.charAt(0) < 256) {
				if((getLength() + str.length()) <= limit)
				{
					super.insertString(offs, str, a);
				}
			}
		}
		else
		{
			if(getLength() + str.length() <= limit)
				super.insertString(offs, str, a);
		}
		
	}

	
}
