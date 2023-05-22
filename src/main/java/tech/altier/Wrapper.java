package tech.altier;

import com.whatsapp.api.WhatsappApiFactory;
import com.whatsapp.api.domain.messages.Message;
import com.whatsapp.api.domain.messages.TextMessage;
import com.whatsapp.api.impl.WhatsappBusinessCloudApi;
import com.whatsapp.api.utils.Formatter;

public class Wrapper {
    public static void main(String[] args) {
        WhatsappApiFactory factory = WhatsappApiFactory.newInstance("a95fb9d5cd12a501c157aea016414ee5");

        WhatsappBusinessCloudApi whatsappBusinessCloudApi = factory.newBusinessCloudApi();

        var message = Message.MessageBuilder.builder()//
                .setTo("+94786241686")//
                .buildTextMessage(new TextMessage()//
                        .setBody(Formatter.bold("Hello world!") + "\nSome code here: \n" + Formatter.code("hello world code here"))//
                        .setPreviewUrl(false));


        whatsappBusinessCloudApi.sendMessage("+94788978085", message);
    }
}
