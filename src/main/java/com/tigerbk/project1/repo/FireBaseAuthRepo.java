package com.tigerbk.project1.repo;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.UserRecord.CreateRequest;
import com.google.firebase.auth.UserRecord.UpdateRequest;

import com.google.firebase.messaging.FirebaseMessaging;
import com.tigerbk.project1.exception.BadRequestException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@AllArgsConstructor
public class FireBaseAuthRepo {    

    public void deleteUserInfo(String uid) {
        try {
            FirebaseAuth.getInstance().deleteUser(uid);
            System.out.println("Successfully deleted user.");
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getFirebaseCustomToken(String uid) throws FirebaseAuthException{
        return FirebaseAuth.getInstance().createCustomToken(uid);
    }

    

    // 기본 적으로 유효기간은 1시간 이며 유저 정보를 이용해서 생성할 수 있는 방법이 어려개 있음. ( 공식문서 참고 )
    // https://firebase.google.com/docs/auth/admin/manage-users?hl=ko#java_4
    public String createFirebaseCustomToken(Map<String, Object> userInfo) {

        UserRecord userRecord;
        Boolean isMember = false;
        String uid = String.valueOf(userInfo.get("id"));
        String email = String.valueOf(userInfo.get("email"));
        String displayName = String.valueOf( userInfo.get("nickname"));
        String phoneNumber = String.valueOf(userInfo.get("phoneNumber"));
        String picture = String.valueOf(userInfo.get("picture"));
        if(email == null || email.equals("null") || email.equals("")){
            email = uid + "@tigerbk.com";
        }
        if(displayName == null || displayName.equals("null") || displayName.equals("")){
            displayName = "TigerBK";
        }
        if(picture == null || picture.equals("null") || picture.equals("")){
            picture = "https://tigerbk.com/images/default.png";
        }
        if(phoneNumber == null || phoneNumber.equals("null") || phoneNumber.equals("")){
            phoneNumber = "010-0000-0000";
        }

        log.debug(uid);
        log.debug(email);
        log.debug(displayName);
        log.debug(picture);

        try {      
            userRecord = FirebaseAuth.getInstance().getUser(uid);            
            isMember = true;
        } catch (FirebaseAuthException e) {
            // e.printStackTrace();
            isMember = false;
        } catch (Exception e){
           // e.printStackTrace();
            isMember = false;
        }

        log.debug("isMember : " + isMember );

        if (isMember) {
            try {
                UpdateRequest request = new UpdateRequest(uid)
                    .setEmail(email)
                    .setPhoneNumber("+82"+phoneNumber)
                    .setEmailVerified(false)
                    .setPassword("newPassword")
                    .setDisplayName(displayName)
                    .setPhotoUrl(picture)
                    .setDisabled(false);
                userRecord = FirebaseAuth.getInstance().updateUser(request);
                log.debug("Successfully updated user: " + userRecord.getUid());
            } catch (FirebaseAuthException e) {
              //  e.printStackTrace();
                log.debug("updateUser : " + e.getMessage() );
                throw new BadRequestException("Firebase updateUser 생성오류 :" + e.getMessage());
            } catch (Exception e) {
             //   e.printStackTrace();
                log.debug("updateUser : " + e.getMessage() );
                throw new BadRequestException("Firebase updateUser 생성오류 :" + e.getMessage());
            }
        } else {
            try {
                 CreateRequest request = new CreateRequest()
                    .setUid(uid)
                    .setEmail(email)
                    .setEmailVerified(false)
                    .setPassword("secretPassword")
                    .setPhoneNumber("+82"+phoneNumber)
                    .setDisplayName(displayName)
                    .setPhotoUrl(picture)
                    .setDisabled(false);
                userRecord = FirebaseAuth.getInstance().createUser(request);
                log.debug("Successfully created new user: " + userRecord.getUid());

            } catch (FirebaseAuthException e) {
              //  e.printStackTrace();
                log.debug("createUser : " + e.getMessage() );
                throw new BadRequestException("Firebase createUser 생성오류 :" + e.getMessage());
            } catch (Exception e) {
             //   e.printStackTrace();
                log.debug("createUser : " + e.getMessage() );
                throw new BadRequestException("Firebase createUser 생성오류 :" + e.getMessage());
            }
        }

        try { // 2. 전달받은 user 정보로 CustomToken을 발행한다.
            System.out.println("@@@ createCustomToken Start : " + userRecord.getUid());

            return FirebaseAuth.getInstance().createCustomToken(userRecord.getUid());
        } catch (FirebaseAuthException e) {
          //  e.printStackTrace();
            log.debug("createCustomToken : " + e.getMessage() );
            throw new BadRequestException("Firebase Token 생성오류 :" + e.getMessage());
        } catch (Exception e) {
         //   e.printStackTrace();
            log.debug("createCustomToken : " + e.getMessage() );
            throw new BadRequestException("Firebase Token 생성오류 :" + e.getMessage());
        }

    }

}