package kr.ac.dankook.mobsys.buttonwhacker;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class GameOverActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over); // GameOverActivity 레이아웃 설정

        // Intent로부터 점수 불러오기
        Intent intent = getIntent();
        int finalScore = intent.getIntExtra("FINAL_SCORE", 0);

        // 점수 표시 TextView
        TextView scoreTextView = findViewById(R.id.final_score_text);
        scoreTextView.setText("Your Score: " + finalScore); // 최종 점수 표시

        // 재시작 버튼 (GameActivity로 다시 돌아가기)
        Button restartButton = findViewById(R.id.restart_button);
        restartButton.setOnClickListener(v ->
        {
            Intent restartIntent = new Intent(GameOverActivity.this, kr.ac.dankook.mobsys.buttonwhacker.GameActivity.class);
            restartIntent.putExtra("difficulty", getIntent().getStringExtra("difficulty")); // 난이도를 다시 전달
            startActivity(restartIntent);
            finish(); // 현재 액티비티 종료하고 게임 재시작
        });

        // 메인 화면으로 돌아가기 버튼
        Button mainMenuButton = findViewById(R.id.main_menu_button);
        mainMenuButton.setOnClickListener(v ->
        {
            Intent mainMenuIntent = new Intent(GameOverActivity.this, kr.ac.dankook.mobsys.buttonwhacker.MainActivity.class);
            startActivity(mainMenuIntent);
            finish(); // 메인 화면으로 돌아가기
        });
    }
}
