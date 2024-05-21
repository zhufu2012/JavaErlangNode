package command;

import com.ericsson.otp.erlang.OtpConnection;
import com.ericsson.otp.erlang.OtpErlangObject;
import com.ericsson.otp.erlang.OtpErlangString;
import com.ericsson.otp.erlang.OtpPeer;
import com.ericsson.otp.erlang.OtpSelf;

public class SendCommand {

	public static void main(String[] args) throws Exception {
		String cookie = args[0];
		String port = args[1];
		String Module = args[2];
		String FunName = args[3];
		for(String str:args)
		{
			System.out.println(str);
		}
		OtpSelf self = new OtpSelf("java_node", cookie);
		OtpPeer other = new OtpPeer(port);
		OtpConnection connection = self.connect(other);
		OtpErlangObject[] msg = new OtpErlangObject[args.length-4];
		if (connection.isConnected()) 
		{
			for (int i = 0; i < msg.length; i++) {
				OtpErlangString string = new OtpErlangString(args[i+4]);
				msg[i] = string;
			}
            connection.sendRPC(Module, FunName, msg);
		}
	}

}
