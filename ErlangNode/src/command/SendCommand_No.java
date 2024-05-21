package command;

import com.ericsson.otp.erlang.OtpConnection;
import com.ericsson.otp.erlang.OtpErlangObject;
import com.ericsson.otp.erlang.OtpErlangString;
import com.ericsson.otp.erlang.OtpPeer;
import com.ericsson.otp.erlang.OtpSelf;

public class SendCommand_No {

	public static void main(String[] args) throws Exception {
		String cookie = args[0];
		String port = args[1];
		String Module = args[2];
		String FunName = args[3];
		OtpSelf self = new OtpSelf("java_node", cookie);
		OtpPeer other = new OtpPeer(port);
		OtpConnection connection = self.connect(other);
		OtpErlangObject[] msg = new OtpErlangObject[0];
		if (connection.isConnected()) 
		{
            connection.sendRPC(Module, FunName, msg);
		}
	}

}
