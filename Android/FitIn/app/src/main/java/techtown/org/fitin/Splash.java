package techtown.org.fitin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        // 앱 실행시 3초의 시간이 있다가 메인화면으로 넘어가게끔 스플래쉬 처리함
        Handler hd = new Handler();
        hd.postDelayed(new SplashHandler(), 3000);
    }

    // 스플래쉬 처리를 위해 선언한 inner class SplashHandler
    private class SplashHandler implements Runnable {
        @Override
        public void run() {
            startActivity(new Intent(getApplication(),MainActivity.class)); // 로딩이 끝난 후, MainActivity로 넘어감
            Splash.this.finish(); // 스플래쉬 페이지 Activity stack에서 제거
        }
    }

    @Override
    public void onBackPressed() {
        // 초반 스플래쉬 화면에서 넘어갈 때 뒤로가기 버튼 못 누르게 함
    }
}