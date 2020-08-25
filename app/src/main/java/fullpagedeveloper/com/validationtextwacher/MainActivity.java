package fullpagedeveloper.com.validationtextwacher;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    TextInputLayout nameLayout, phoneLayout, emailLayout, passwordLayout, confirmPasswordLayout;
    TextInputEditText nameUser, phoneNumber, emailUser, passwordUser, confirmPasswordUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialButton submit = findViewById(R.id.textView_Enter);

        //TextInputLayout
        nameLayout = (TextInputLayout) findViewById(R.id.editText_name_user_layout);
        phoneLayout = (TextInputLayout) findViewById(R.id.editText_number_phone_layout);
        emailLayout = (TextInputLayout) findViewById(R.id.editText_email_user_layout);
        passwordLayout = (TextInputLayout) findViewById(R.id.editText_password_user_layout);
        confirmPasswordLayout = (TextInputLayout) findViewById(R.id.editText_confirm_password_user_layout);

        //TextInputEditText
        nameUser = (TextInputEditText) findViewById(R.id.editText_name_user);
        phoneNumber = (TextInputEditText) findViewById(R.id.editText_number_phone);
        emailUser = (TextInputEditText) findViewById(R.id.editText_email_user);
        passwordUser = (TextInputEditText) findViewById(R.id.editText_password_user);
        confirmPasswordUser = (TextInputEditText) findViewById(R.id.editText_confirm_password_user);

        nameUser.addTextChangedListener(new ValidationTextWacher(nameUser));
        phoneNumber.addTextChangedListener(new ValidationTextWacher(phoneNumber));
        emailUser.addTextChangedListener(new ValidationTextWacher(emailUser));
        passwordUser.addTextChangedListener(new ValidationTextWacher(passwordUser));
        confirmPasswordUser.addTextChangedListener(new ValidationTextWacher(confirmPasswordUser));

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!validationNameUser() && !validationPhone() && !validationEmail() && !validationPassword() && !validationConfirmPassword()) {
                    return;
                }
            }
        });
    }


    /**
     * class Validation email and password using TextWatcher
     */
    private class ValidationTextWacher implements TextWatcher {

        private View view;

        public ValidationTextWacher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            switch (view.getId()) {
                case R.id.editText_name_user:
                    validationNameUser();
                    break;
                case R.id.editText_number_phone:
                    validationPhone();
                    break;
                case R.id.editText_email_user:
                    validationEmail();
                    break;
                case R.id.editText_password_user:
                    validationPassword();
                    break;
                case R.id.editText_confirm_password_user:
                    validationConfirmPassword();
                    break;
            }
        }
    }

    private boolean validationNameUser() {
        if (nameUser.getText().toString().trim().isEmpty()) {
            nameLayout.setError("Masukan nama lengkap.");
            nameUser.requestFocus();
            return false;
        } else {
            nameLayout.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validationPhone() {
        if (phoneNumber.getText().toString().trim().isEmpty()) {
            phoneLayout.setError("Masukan nomor handphone.");
            phoneNumber.requestFocus();
            return false;
        } else if (phoneNumber.getText().toString().trim().equals("1")) {
            phoneLayout.setError("Nomor handphone sudah terdaftar.");
            return false;
        } else {
            phoneLayout.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validationEmail() {
        if (emailUser.getText().toString().trim().isEmpty()) {
            emailLayout.setError("Masukan email");
            emailUser.requestFocus();
            return false;
        } else if (emailUser.getText().toString().equals("a@yahoo.com")) {
            emailLayout.setError("Email sudah terdaftar.");
            emailUser.requestFocus();
            return false;
        } else {
            emailLayout.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validationPassword() {
        if (passwordUser.getText().toString().trim().isEmpty()) {
            passwordLayout.setError("Password is required");
            passwordUser.requestFocus();
            return false;
        } else if (passwordUser.getText().toString().length() < 6) {
            passwordLayout.setError("Password can't be than 6 digit");
            passwordUser.requestFocus();
            return false;
        } else {
            passwordLayout.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validationConfirmPassword() {
        if (confirmPasswordUser.getText().toString().trim().isEmpty()) {
            confirmPasswordLayout.setError("Masukan konfirmasi kata sandi.");
            confirmPasswordUser.requestFocus();
            return false;
        } else if (passwordUser.getText().toString().length() < 6) {
            passwordLayout.setError("Password can't be than 6 digit");
            passwordUser.requestFocus();
            return false;
        } else {
            confirmPasswordLayout.setErrorEnabled(false);
        }

        return true;
    }

    /*private boolean validationEmail() {
        if (emailUser.getText().toString().trim().isEmpty()) {
            emailLayout.setErrorEnabled(false);
        }else {
            String emailId = emailUser.getText().toString();
            boolean isValid = Patterns.EMAIL_ADDRESS.matcher(emailId).matches();
            if (!isValid){
                emailLayout.setError("Invalid Email address, ex:abc@example.com");
                emailUser.requestFocus();
                return false;
            }else {
                emailLayout.setErrorEnabled(false);
            }
        }
        return true;
    }*/
}