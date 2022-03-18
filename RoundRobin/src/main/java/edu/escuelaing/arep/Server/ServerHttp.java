package edu.escuelaing.arep.Server;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class ServerHttp {

    private String[] ports = {":35001",":35002",":35003"};
    private int nServer = 0;
    private String url="";

    public String getMessage() throws UnirestException {
        HttpResponse<String> apiResponse = Unirest.get(url+ports[nServer]+"/arqempresarial").asString();
        nServer=(nServer + 1) % ports.length;
        return apiResponse.getBody();
    }

    public String postMessage(String message) throws UnirestException {
        HttpResponse<String> apiResponse = Unirest.post(url+ports[nServer]+"/arqempresarial")
                .body(message)
                .asString();
        nServer=(nServer + 1) % ports.length;
        return apiResponse.getBody();
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
