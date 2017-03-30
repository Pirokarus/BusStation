package com.mySampleApplication.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.mySampleApplication.shared.Reqest;
import com.mySampleApplication.shared.Responce;

public interface MySampleApplicationServiceAsync {
    void getMessage(String msg, AsyncCallback<String> async);

    void myMessage(Reqest reqest, AsyncCallback<Responce> async);
}
