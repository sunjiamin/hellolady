package hellolady.imsdk;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.support.util.common.PhoneUtil;

import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

import hellolady.imsdk.Listener.IConnectListener;
import hellolady.imsdk.Listener.IMessageListener;
import hellolady.imsdk.Message.BaseMessage;

/**
 * 项目名称：HeartPro
 * 类描述：
 * 创建人：Jiamin.Sun
 * 创建时间：5/7/2016 8:13 PM
 * 修改人：Jiamin.Sun
 * 修改时间：5/7/2016 8:13 PM
 * 修改备注：
 */
public class WebSocketClientUtils {

    Context context;
    public static  WebSocketClient webSocketClient =null;
    public static WebSocketClientUtils mInstance;


    public IMessageListener messageListener;

    public IConnectListener connectListener;

    static Gson gson = new Gson();

    //标识当前是否在连接
    static boolean doConnecting = false;

    private WebSocketClientUtils(){
        //init("ws://120.25.161.238:9090/websocket",new DraftInfo("WebSocket协议Draft_17", new Draft_17()));
    }
    /**
     * 单列模式 获取客户端工具栏
     * @return
     */
    public static WebSocketClientUtils getInstance(){
        if(mInstance==null){
            synchronized (WebSocketClientUtils.class){
               if(mInstance==null){
                   mInstance = new WebSocketClientUtils();
               }
           }
        }
        return mInstance;
    }

    public WebSocketClientUtils setConnectListener(IConnectListener c){
        connectListener=c;
        return this;
    }

    public WebSocketClientUtils setMessageListener(IMessageListener  c){
        messageListener=c;
        return this;
    }

    public WebSocketClientUtils setContext(Context c){
        context=c;
        return this;
    }

    /**
     * 初始化webSocket 必须在初始化监听后才可以的
     * @param address
     * @param selectDraft
     */
    public WebSocketClientUtils init(String address, DraftInfo selectDraft){
        try {
            webSocketClient = new WebSocketClient(new URI(address),selectDraft.getDraft()) {
                @Override
                public void onOpen(ServerHandshake handshakedata) {
                    //当和服务器建立连接成功时候 已经连接到服务器
                    connectListener.onSuccess(handshakedata);
                }

                @Override
                public void onMessage(String message) {
                    //当收到消息的时候 获取到服务器信息
                    messageListener.onMessage(message);
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    //断开服务器连接
                    connectListener.onClose(code,reason,remote);
                }

                @Override
                public void onError(Exception ex) {
                    //连接发生了异常
                    connectListener.onError(ex);
                }
            };
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * 连接
     */
    public void connect(){
        if(webSocketClient!=null){
            try {
                doConnecting = true;
                webSocketClient.connect();
                doConnecting = false;
            }catch (Exception e){
                doConnecting = false;
            }

        }
    }


    /**
     * 关闭连接
     */
    public void close(){
        if(webSocketClient!=null){
            webSocketClient.close();
        }
    }

    /**
     * 登陆
     * @param userName
     * @param psd
     */
    public void login(String userName,String psd){
        try{
            //手机序列号
            String phoneIME = PhoneUtil.getPhoneIME(context);
            String sendLoginStr = String.format("{\"comand\":\"login\"," +
                    "\"identity\":\"%s\"," +
                    "\"password\":\"%s\"," +
                    "\"type\":\"android\"," +
                    "\"deviceSerial\":\"%s\"}",userName,psd,phoneIME);
            sendMsg(sendLoginStr);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void sendMsgStr(String messageStr){
        try{
            sendMsg(messageStr);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 发送消息
     * @param baseMessage
     */
    public void sendMsg(BaseMessage baseMessage){
        WebSocket webSocket =  webSocketClient.getConnection();
        if(webSocketClient!=null&&webSocket.isOpen()){
            String msgStr = gson.toJson(baseMessage);
            webSocketClient.send(msgStr);
        }else{
            Log.e("not con","没有连接");
        }
    }

    /**
     * 发送消息
     * @param messageStr
     */
    public void sendMsg(String messageStr){
        WebSocket webSocket =  webSocketClient.getConnection();
        if(webSocketClient!=null&&webSocket.isOpen()){
            webSocketClient.send(messageStr);
        }else{
            Log.e("not con","没有连接");
        }
    }

    public WebSocketClient getWebSocketClient(){
        return webSocketClient;
    }
}
