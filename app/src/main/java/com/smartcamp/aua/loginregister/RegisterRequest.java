package com.smartcamp.aua.loginregister;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

    private static final String REGISTER_REQUEST_URL = "https://alyonaharutyunyan.000webhostapp.com/Register.php";
    private Map<String, String> params;

    //Constructor
    public RegisterRequest(String name, String username, String email, String password, Response.Listener<String> listener){
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("name",name);
        params.put("username",username);
        params.put("password",password);
        params.put("email",email);
    }

    //when the request is executed, volley will call getparams which return params we already filled with the needed data
    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
