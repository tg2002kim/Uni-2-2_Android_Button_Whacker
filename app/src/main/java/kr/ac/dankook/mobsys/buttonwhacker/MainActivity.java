package kr.ac.dankook.mobsys.buttonwhacker;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
{

    private String difficultyLevel = "Normal"; // 기본 난이도는 Normal로 설정

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // MainActivity 레이아웃 설정

        // 난이도 선택 PopupMenu를 표시할 버튼
        Button selectDifficultyButton = findViewById(R.id.select_difficulty_button);
        selectDifficultyButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                showDifficultyPopupMenu(v); // PopupMenu 표시
            }
        });

        // 게임 시작 버튼
        Button startGameButton = findViewById(R.id.start_game_button);
        startGameButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // 난이도를 GameActivity로 전달하여 게임 시작
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                intent.putExtra("difficulty", difficultyLevel); // 난이도를 전달
                startActivity(intent);
            }
        });

        // 게임 종료 버튼
        Button exitGameButton = findViewById(R.id.exit_game_button);
        exitGameButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish(); // 현재 액티비티 종료
            }
        });
    }

    // 난이도 선택 PopupMenu를 생성
    private void showDifficultyPopupMenu(View view)
    {
        // PopupMenu 객체 생성
        PopupMenu popupMenu = new PopupMenu(MainActivity.this, view);
        // 메뉴 항목을 추가
        popupMenu.getMenu().add("Easy");
        popupMenu.getMenu().add("Normal");
        popupMenu.getMenu().add("Hard");

        // 메뉴 항목 클릭 시 이벤트 처리
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(MenuItem item)
            {
                // 선택한 항목에 따른 난이도 설정
                difficultyLevel = item.getTitle().toString();
                Toast.makeText(MainActivity.this, "Selected Difficulty: " + difficultyLevel, Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        // PopupMenu 표시
        popupMenu.show();
    }
}


