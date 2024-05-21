# ProjectTD

使用java语言实现的	, 用于与Erlang节点通信或者远程调用的代码
ReceiveData.java 实现的java接收来自，erlang节点的
以这种方式发送的数据，gen_server:cast({NodeName, NodeSName}, {self(), Data});
并且保存到一个文件中

SendCommand_No.jave 实现的是通过java 远程调用Erlang节点的无参函数,
如：

open_print() ->
	io:format("~n 开启协议打印！", []).


SendCommand.jave 实现的是通过java 远程调用Erlang节点的带一个参数的函数（字符串）
如：
open_print(Str) ->
	io:format("~n 输出字符串: ~p", [Str]).

注意事项：
1.接收来自erlang的节点的消息，Java的Erlang节点与Erlang节点使用同一个cookie，
并且，erlang节点这边需要先建立连接，使用如 net_kernel:connect_node('java_node@127.0.0.1')
2.远程调用要求，Java的Erlang节点与Erlang节点使用同一个cookie

作为例子，希望能帮助到你！

## 许可证

MIT
