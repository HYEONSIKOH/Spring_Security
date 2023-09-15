package com.example.security.Service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
@PropertySource("classpath:application.properties")
public class MailService {
    @Autowired
    JavaMailSender emailSender;

    public static final String ePw = createKey();

    private MimeMessage createMessage(String to) throws Exception{
        System.out.println("보내는 대상 : " + to);
        System.out.println("인증 번호 : " + ePw);

        String msgg = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "</head>\n" +
                "<body>\n" +
                "<header>\n" +
                "    <img src=\"https://seumu-s3-bucket.s3.ap-northeast-2.amazonaws.com/banner_pattern.svg\" style=\"width: 100%;\">\n" +
                "</header>\n" +
                "<div style=\"margin: 93px 0px 0px 160px;\">\n" +
                "    <div style=\"color: #000; font-size: 34px; font-weight: 700; line-height: normal; letter-spacing: 0.085px;\">\n" +
                "        OFI 인증메일입니다\n" +
                "    </div>\n" +
                "    <div style=\"margin-top: 46px; color: #000; font-size: 16px; font-weight: 400; line-height: normal; letter-spacing: 0.04px;\">\n" +
                "        안녕하세요 OFI 인증메일입니다.<br>\n" +
                "        5분 이내에 이메일에 있는 인증코드를 입력해 주세요.\n" +
                "    </div>\n" +
                "    <div style=\"margin-top: 46px; color: #000; font-size: 24px; font-weight: 700; line-height: normal;\">\n" +
                "        인증 코드: "+ePw+"\n" +
                "    </div>\n" +
                "    <div style=\"margin-top: 46px; color: #000; font-size: 16px; font-weight: 400; line-height: normal; letter-spacing: 0.04px;\">\n" +
                "        인증번호를 인증창에 직접 입력하시거나 인증키를 복사 후 회원가입 페이지의 입력창에 붙여넣기 해주세요.<br>\n" +
                "        타인에게 인증번호 유출을 주의해주세요.\n" +
                "    </div>\n" +
                "</div>\n" +
                "<div style=\"margin-top: 40px; bottom: 0; width: 100%; height: 100%; background: #F3EFF4; padding: 50px 614px 50px 164px; color:#929094; font-size: 12px; font-weight: 400; line-height: normal; letter-spacing: 0.048px;\"> " +
                "본 메일은 발신전용이며, 문의에 대한 회신은 처리되지 않습니다.<br> " +
                "(주)아웃핏인 | 주소: 충남 천안시 동남구 상명대로 & 서울특별시 종로구 홍지문2길 20 | 팀원: 김규민 박준형 안지수 오현식 이소윤 이수정<br><br> " +
                "Copyright ©OFI Corp. All rights reserved. " +
                "</div> " +
                "</body>\n" +
                "</html>";



        MimeMessage message = emailSender.createMimeMessage();

        message.addRecipients(RecipientType.TO, to);//보내는 대상
        message.setSubject("[OutFitIn] 회원가입 이메일 인증번호");//제목

        message.setText(msgg, "utf-8", "html");//내용
        message.setFrom(new InternetAddress("ks06891@naver.com","OutFitIn"));//보내는 사람

        return message;
    }

    public static String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 4; i++) { // 인증코드 8자리
            key.append((rnd.nextInt(10)));
        }

        return key.toString();
    }

    public String sendSimpleMessage(String to)throws Exception {
        // TODO Auto-generated method stub
        MimeMessage message = createMessage(to);
        try{//예외처리
            emailSender.send(message);
        }catch(MailException es){
            es.printStackTrace();
            throw new IllegalArgumentException();
        }
        return ePw;
    }
}
