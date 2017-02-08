package com.sun.hellolady.demo.IM;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sun.hellolady.R;
import com.support.util.common.T;

import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ServerHandshake;

import hellolady.imsdk.DraftInfo;
import hellolady.imsdk.Listener.IConnectListener;
import hellolady.imsdk.Listener.IMessageListener;
import hellolady.imsdk.WebSocketClientUtils;

public class ImDemoActivity extends AppCompatActivity {

    WebSocketClientUtils webSocketClientUtils;
    Button btnConnect;
    Button btnLogin ;
    Button btnSend;
    EditText sendMsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_im_demo);

        btnConnect = (Button)findViewById(R.id.btnConnect);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnSend = (Button)findViewById(R.id.btnSend);
        sendMsg= (EditText)findViewById(R.id.sendMsg);
        T.showShort(getApplication(),"开始连接");

        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                T.showShort(getApplication(),"开始连接22");
                webSocketClientUtils = WebSocketClientUtils.getInstance();
                webSocketClientUtils= webSocketClientUtils
                        .setConnectListener(new IConnectListener() {
                            @Override
                            public void onSuccess(ServerHandshake serverHandshakeData) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        T.showShort(getApplication(),"连接成功");

                                        Log.e("wlf", "已经连接到服务器【"     + "】");


                                    }
                                });

                            }

                            @Override
                            public void onClose(int code, String reason, boolean remote) {

                            }

                            @Override
                            public void onError(Exception e) {

                            }
                        })
                        .setMessageListener(new IMessageListener() {
                            @Override
                            public void onMessage(final String message) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        T.showShort(getApplication(),"获取到服务器信息:"+message);

                                        Log.e("wlf", "获取到服务器信息【"+message+ "】");


                                    }
                                });


                            }
                        })
                        .setContext(getApplication())
                        .init("ws://120.25.161.238:9090/websocket",
                                new DraftInfo("WebSocket协议Draft_17", new Draft_17()));

                    webSocketClientUtils.getWebSocketClient().connect();


            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(webSocketClientUtils!=null){
                    webSocketClientUtils.login("7a3517d809010157f831d22ad646bf0c","7a3517d809010157f831d22ad646bf0c");
                }
            }
        });
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //String msg = sendMsg.getText().toString();
                    String sendStr =
                            String.format("{\"comand\":\"sendMessages\"," +
                                    "\"identitys\":\"7a3517d809010157f831d22ad646bf0c,18888afc02ce6dad7bfde25e9bdc012a\"," +
                                    "\"conversation\":\"0000001\"," +
                                    "\"sender\":\"7a3517d809010157f831d22ad646bf0c\"," +
                                    "\"messageCode\":\"001\"," +
                                    "\"messageContent\":\" \"{\\\"addSerialRev\\\":\\\"57\\\",\\\"msgcontent\\\":\\\"%s\\\",\\\"prestr\\\":\\\"【心卫士】\\\",\\\"sendTime\\\":\\\"%s\\\",\\\"srctermid\\\":\\\"15221382347\\\",\\\"systemPhone\\\":\\\"1069099957\\\",\\\"type\\\":\\\"yimeiruantong\\\"}\"\"," +
                                    "\"durable\":\"true\"}","ss","1000000");

//                    String phoneIME = PhoneUtil.getPhoneIME(getApplicationContext());
//                    String sendLoginStr = String.format("{\"comand\":\"login\"," +
//                            "\"identity\":\"%s\"," +
//                            "\"password\":\"%s\"," +
//                            "\"type\":\"android\"," +
//                            "\"deviceSerial\":\"%s\"}","7a3517d809010157f831d22ad646bf0c","7a3517d809010157f831d22ad646bf0c",phoneIME);
                    if(webSocketClientUtils!=null){
                        webSocketClientUtils.sendMsgStr(sendStr);
                        //webSocketClientUtils.login("7a3517d809010157f831d22ad646bf0c","7a3517d809010157f831d22ad646bf0c");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
    }
}
