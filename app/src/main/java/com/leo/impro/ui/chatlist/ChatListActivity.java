package com.leo.impro.ui.chatlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.leo.impro.R;
import com.leo.impro.databinding.ActivityChatListBinding;
import com.leo.impro.ui.chat.ChatActivity;
import com.leo.impro.utils.swip.SwipItemHelper;
import com.lihang.nbadapter.BaseAdapter;

import java.util.ArrayList;

public class ChatListActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityChatListBinding binding;
    private TalkListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chat_list);
        initData();
        setListener();
    }

    private void setListener() {
        adapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener<String>() {
            @Override
            public void onItemClick(String item, int position) {
                startActivity(new Intent(ChatListActivity.this, ChatActivity.class));
            }
        });
    }

    private void initData() {
        adapter = new TalkListAdapter(this);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("");
        arrayList.add("");
        adapter.setDataList(arrayList);
        binding.recyclerView.setAdapter(adapter);
        SwipItemHelper swipItemHelper = new SwipItemHelper(adapter);
        swipItemHelper.attachRecyclerView(binding.recyclerView);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_collect:
                int positionCollect = (int) v.getTag();
                Toast.makeText(this, "置顶  position ==> " + positionCollect, Toast.LENGTH_SHORT).show();
                break;

            case R.id.txt_delete:
                int positionDelete = (int) v.getTag();
                Toast.makeText(this, "删除  position ==> " + positionDelete, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
