package command;

import java.io.FileWriter;

import com.ericsson.otp.erlang.OtpErlangList;
import com.ericsson.otp.erlang.OtpErlangObject;
import com.ericsson.otp.erlang.OtpErlangPid;
import com.ericsson.otp.erlang.OtpErlangString;
import com.ericsson.otp.erlang.OtpErlangTuple;
import com.ericsson.otp.erlang.OtpMbox;
import com.ericsson.otp.erlang.OtpNode;

public class ReceiveData {

	public static void main(String[] args) throws Exception {
		String cookie = args[0];
		String define_file_path = args[1];
		//String define_file_path = "D:\\ToolFile\\服务器管理工具\\path\\erlang_data\\";
		//String cookie = "gameserver_cookie";
		System.out.println(define_file_path);
		OtpNode node = new OtpNode("java_node@127.0.0.1", cookie);
		OtpMbox mbox = node.createMbox();
		mbox.registerName("java_node_name");
		OtpErlangObject o;
		OtpErlangTuple msg;
		OtpErlangTuple alldata;
		OtpErlangString filename = null;
		OtpErlangList data;
		String filestr = "";
		String filenode = "";
		while (true) {
			try {
				OtpErlangPid from = null;
				o = mbox.receive();
				filestr ="";
				filenode = "";
				if (o instanceof OtpErlangTuple) {
					msg = (OtpErlangTuple) o;
					Print(msg);
					alldata = (OtpErlangTuple)msg.elementAt(1);
					Print(alldata);
					if(alldata.elements().length==2)
					{
						try {
	                        from = (OtpErlangPid) alldata.elementAt(0);
	                        data= (OtpErlangList) alldata.elementAt(1);
	                        String node_name = from.node().toString();
	                        filenode = node_name.substring(0,node_name.indexOf("@"));
	                        for(OtpErlangObject typles:data)
	                        {
	                        	OtpErlangTuple typle = (OtpErlangTuple)typles;
	                        	filestr += typle.toString();
	                        }
	                    } catch (ClassCastException ex) {
	                    	Print("Error: Unable to extract Pid from message tuple");
	                    }
	                    if(filenode != "")
	                    {
	                    	Print(filenode);
	                    	// 设置为 false 表示每次写入都重新写入数据
	                    	FileWriter writer = new FileWriter(define_file_path + filenode+".txt", false);
	                    	// 设置为 false 表示每次写入都重新写入数据
	                    	//FileWriter writer = new FileWriter("D:\\ToolFile\\服务器管理工具\\path\\erlang_data\\"+filenode+".txt", false);
	                        writer.write(filestr);
	                        writer.close();
	                    }	
					}
					if(alldata.elements().length==3)//持久化存储数据
					{
						try {
							//System.out.println(alldata);
	                        from = (OtpErlangPid) alldata.elementAt(0);
	                        filename= (OtpErlangString) alldata.elementAt(1);
	                        data= (OtpErlangList) alldata.elementAt(2);
	                        String node_name = from.node().toString();
	                        filenode = node_name.substring(0,node_name.indexOf("@"));
	                        for(OtpErlangObject typles:data)
	                        {
	                        	OtpErlangObject typle = (OtpErlangObject)typles;
	                        	if (typle instanceof OtpErlangList) {
	                        		OtpErlangList list =(OtpErlangList)typle; 
	                        		for(OtpErlangObject typles2:list)
	                        		{
	                        			filestr += typles2.toString()+"\n\t";
	                        		}
	                        	}
	                        	else {
	                        		filestr += typle.toString()+"\n";
								}
	                        }
	                    } catch (ClassCastException ex) {
	                    	Print("Error: Unable to extract Pid from message tuple");
	                    }
	                    if(filenode != "")
	                    {
	                    	Print(filenode);
	                    	// 设置为 false 表示每次写入都重新写入数据
	                    	FileWriter writer = new FileWriter(define_file_path + filename.stringValue()+".txt", false);
	                    	// 设置为 false 表示每次写入都重新写入数据
	                    	//FileWriter writer = new FileWriter("D:\\ToolFile\\服务器管理工具\\path\\erlang_data\\"+filenode+".txt", false);
	                        writer.write(filestr);
	                        writer.close();
	                    }
					}
				}
			} catch (Exception e) {
			}
		}

	}
	
	public static void Print(Object x)
	{
		//System.out.println(x);
	}
	
	public static void Print(Object[] x)
	{
		//System.out.println(x);
	}

}
