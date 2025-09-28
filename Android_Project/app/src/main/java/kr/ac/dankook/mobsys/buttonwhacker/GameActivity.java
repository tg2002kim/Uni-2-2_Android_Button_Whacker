package kr.ac.dankook.mobsys.buttonwhacker;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.util.Log; // 로그 확인용
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class GameActivity extends AppCompatActivity
{

    private int score = 100; // 초기 점수
    private Button[][] buttons = new Button[5][5]; // 5x5 배열의 버튼
    private Handler handler = new Handler(); // Handler 사용
    private Random random = new Random(); // 랜덤 버튼 선택
    private Button activeButton; // 활성화된 버튼
    private Runnable gameOverRunnable; // 게임오버 처리용 Runnable
    private int timeLimit = 1000; // 제한시간 1초
    private TextView countdownText; // 카운트 다운 표시용 TextView

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // MainActivity에서 난이도 값을 받음
        Intent intent = getIntent();
        String difficulty = intent.getStringExtra("difficulty");

        // 난이도에 따른 시간 설정
        switch (difficulty)
        {
            case "Easy":
                timeLimit = 2000; // Easy = 2초
                break;
            case "Normal":
                timeLimit = 1000; // Normal = 1초
                break;
            case "Hard":
                timeLimit = 500; // Hard = 0.5초
                break;
        }

        // 난이도 로그 출력
        Log.d("GameActivity", "Selected Difficulty: " + difficulty + ", Time Limit: " + timeLimit + "ms");

        // 점수 표시 TextView
        TextView scoreText = findViewById(R.id.score_text);
        scoreText.setText("Score: " + score); // 초기 점수 표시

        // 카운트다운 텍스트뷰
        countdownText = findViewById(R.id.countdown_text);

        // 5x5 버튼 배열 설정 및 초기화 (+색깔 : 회색 설정)
        GridLayout buttonGrid = findViewById(R.id.button_grid);
        for (int row = 0; row < 5; row++)
        {
            for (int col = 0; col < 5; col++)
            {
                String buttonID = "button_" + (row * 5 + col);
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[row][col] = findViewById(resID); // 버튼 연결

                // 버튼 색깔 설정 : 회색
                buttons[row][col].setBackgroundColor(getResources().getColor(android.R.color.darker_gray));

                // 버튼 클릭 이벤트 처리
                buttons[row][col].setOnClickListener(v -> handleButtonClick((Button) v));
            }
        }

        // 카운트 다운 시작
        startCountdown();
    }

    // 카운트 다운 3, 2, 1 후에 게임 시작
    private void startCountdown()
    {
        countdownText.setText("3");
        handler.postDelayed(() -> countdownText.setText("2"), 1000);
        handler.postDelayed(() -> countdownText.setText("1"), 2000);
        handler.postDelayed(() ->
        {
            countdownText.setText(""); // 카운트 다운 완료 후 텍스트 제거
            activateRandomButton();
        }, 3000);
    }

    private void activateRandomButton()
    {
        // 이전 버튼 비활성화 (회색)
        if (activeButton != null)
        {
            activeButton.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
        }

        // 새로운 버튼 활성화 (노란색)
        int randomRow = random.nextInt(5);
        int randomCol = random.nextInt(5);
        activeButton = buttons[randomRow][randomCol];
        activeButton.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));

        // 제한 시간 내에 버튼이 눌리지 않으면 게임 오버 처리
        gameOverRunnable = new Runnable()
        {
            @Override
            public void run()
            {
                if (activeButton != null)
                {
                    gameOver();
                }
            }
        };
        handler.postDelayed(gameOverRunnable, timeLimit); // 난이도별 시간 적용
    }

    // 버튼 클릭 이벤트 처리
    private void handleButtonClick(final Button clickedButton)
    {
        // 클릭한 버튼이 활성화된 버튼인지 확인
        if (clickedButton == activeButton)
        {
            // 올바른 버튼 클릭 시 파란색으로 0.3초간 표시
            clickedButton.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
            score += 10; // 점수 증가
            updateScore();
            handler.removeCallbacks(gameOverRunnable); // 타이머 취소

            // 0.3초 후 버튼 색상 원래대로 돌리기
            handler.postDelayed(() ->
            {
                clickedButton.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                activateRandomButton(); // 새로운 버튼 활성화
            }, 300); // 0.3초 후 실행
        } else {
            // 잘못된 버튼 클릭 시 빨간색으로 0.3초간 표시
            clickedButton.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
            score -= 10; // 잘못된 클릭으로 감점
            updateScore();

            // 0.3초 후 버튼 색상 원래대로 돌리기
            handler.postDelayed(() ->
            {
                clickedButton.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                checkGameOver();
            }, 300); // 0.3초 후 실행
        }
    }

    private void updateScore()
    {
        TextView scoreText = findViewById(R.id.score_text);
        scoreText.setText("Score: " + score); // 점수 업데이트
    }

    private void checkGameOver()
    {
        if (score <= 0) { // 게임 오버 조건 : 0이하
            gameOver();
        }
    }

    private void gameOver()
    {
        // 게임 오버 처리
        Intent gameOverIntent = new Intent(GameActivity.this, GameOverActivity.class);
        gameOverIntent.putExtra("FINAL_SCORE", score); // 최종 점수 전달
        gameOverIntent.putExtra("difficulty", getIntent().getStringExtra("difficulty"));
        startActivity(gameOverIntent);
        finish(); // 현재 액티비티 종료
    }
}

