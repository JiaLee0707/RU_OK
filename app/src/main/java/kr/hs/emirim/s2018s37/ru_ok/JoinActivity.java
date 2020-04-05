package kr.hs.emirim.s2018s37.ru_ok;


import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.regex.Pattern;

import kr.hs.emirim.s2018s37.ru_ok.UserModel.UserModel;

public class JoinActivity extends AppCompatActivity {

    // 비밀번호 정규식
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^[a-zA-Z0-9!@.#$%^&*?_~]{4,16}$");

    // 파이어베이스 인증 객체 생성
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseStorage firebaseStorage;

    private Uri imageUri;
    private String pathUri;

    // 이메일과 비밀번호
    private EditText editTextName;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextPasswordCheck;

    private String name = "";
    private String email = "";
    private String password = "";
    private String passwordCheck = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        // 파이어베이스 인증 객체 선언
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();

        editTextName = findViewById(R.id.name_EditText);
        editTextEmail = findViewById(R.id.email_EditText);
        editTextPassword = findViewById(R.id.password_EditText);
        editTextPasswordCheck = findViewById(R.id.password_EditText2);
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
//        updateUI(currentUser);
//    }

    public void singUp(View view) {
        name = editTextName.getText().toString();
        email = editTextEmail.getText().toString();
        password = editTextPassword.getText().toString();
        passwordCheck = editTextPasswordCheck.getText().toString();

        if (isValidName() && isValidEmail() && isValidPasswd()) {
            JoinUser(email, password);
        }
    }

    private void JoinUser(String email, String password) {
        firebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(JoinActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            // 회원가입 성공
//                            UserModel userModel = new UserModel();
//                            userModel.userName = name;
//
//                            String uid = task.getResult().getUser().getUid();
//                            FirebaseDatabase.getInstance().getReference().child("users").child(uid).setValue(userModel);
                            final String uid = task.getResult().getUser().getUid();
                            final Uri file = Uri.fromFile(new File("drawable/profile.png")); // path

                            // 스토리지에 방생성 후 선택한 이미지 넣음
                            StorageReference storageReference = firebaseStorage.getReference()
                                    .child("usersProfileImages").child("uid/"+file.getLastPathSegment());
                            storageReference.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                    final Task<Uri> imageUrl = task.getResult().getStorage().getDownloadUrl();
                                    while (!imageUrl.isComplete()) ;

                                    UserModel userModel = new UserModel();

                                    userModel.userName = name;
                                    userModel.uid = uid;
                                    userModel.profileImageUrl = imageUrl.getResult().toString();

                                    // database에 저장
                                    firebaseDatabase.getReference().child("users").child(uid)
                                            .setValue(userModel);
                                }

                            });

                            Toast.makeText(JoinActivity.this, "회원가입 완료", Toast.LENGTH_SHORT).show();
                            //finish();
                        } else {
                            // 회원가입 실패
                            Toast.makeText(JoinActivity.this, "회원가입 실패", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                });
    }

    // 이메일 유효성 검사
    private boolean isValidEmail() {
        if (email.isEmpty()) {
            // 이메일 공백
            Toast.makeText(getApplicationContext(), "이메일을 입력해주세요", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            // 이메일 형식 불일치
            Toast.makeText(getApplicationContext(), "이메일 형식이 맞지 않습니다", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    // 비밀번호 유효성 검사
    private boolean isValidPasswd() {
        if (password.isEmpty()) {
            // 비밀번호 공백
            Toast.makeText(getApplicationContext(), "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!PASSWORD_PATTERN.matcher(password).matches()) {
            // 비밀번호 형식 불일치
            Toast.makeText(getApplicationContext(), "비밀번호 형식이 맞지 않습니다", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            if (!password.equals(passwordCheck)) {
                Toast.makeText(getApplicationContext(), "비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show();
                return false;
            } else {
                return true;
            }
        }
    }

    private boolean isValidName() {
        if(name == null) {
            return false;
        } else {
            return true;
        }
    }
}
